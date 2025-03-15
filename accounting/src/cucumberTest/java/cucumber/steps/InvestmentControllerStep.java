package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.InvestmentCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.InvestmentDtoResponse;
import com.nomad.accounting.adapter.dto.out.InvestmentFindDtoResponse;
import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.adapter.repository.CashbookRepository;
import com.nomad.accounting.adapter.repository.InvestmentRepository;
import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private CashbookRepository cashbookRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    private Response response;

    private InvestmentCreateDtoRequest investmentCreateDtoRequest;

    private UUID idInvestment;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_NOMAD)
                .setBasePath(ConstantsTest.PATH_ACCOUNTING_INVESTMENT)
                .setPort(port)
                .build();
    }

    @Dado("uma requisição Post com InvestmentCreateDtoRequest válido, com amount {int} e typeAction {string} e category {string}")
    public void uma_requisicao_post_com_investment_create_dto_request_valido_com_amount_e_type_action_e_category(
            Integer amount, String typeAction, String category) {

        investmentCreateDtoRequest = new InvestmentCreateDtoRequest(
                "Descrição qualquer", BigDecimal.valueOf(amount), TypeActionEnum.valueOf(typeAction),
                LocalDate.now(), CategoryEnum.valueOf(category), "Fornecedor qualquer");

        assertThat(investmentCreateDtoRequest).isNotNull();
    }

    @Quando("a requisição Post for feita no método create, para cashbook com ano {int} e document {string}, no InvestmentController")
    public void a_requisicao_post_for_feita_no_metodo_create_para_cashbook_com_ano_e_document_no_investment_controller(
            Integer yearReferente, String document) {

        var idCashbook = cashbookRepository.findByYearReferenceAndDocument(
                Year.of(yearReferente), document).get().getCashbookId();

        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                    .body(investmentCreateDtoRequest)
                .when()
                    .post("/" + idCashbook);

        assertThat(response).isNotNull();
    }

    @Entao("receberei um ResponseEntity com HTTP {int} do InvestmentController")
    public void receberei_um_response_entity_com_http_do_investment_controller(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode());
    }

    @Entao("com um InvestmentDtoResponse no body, com id e amount {int} e typeAction {string} e category {string}")
    public void com_um_investment_dto_response_no_body_com_id_e_amount_e_type_action_e_category(
            Integer amount, String typeAction, String category) {
        var body = response.as(InvestmentDtoResponse.class);
        idInvestment = body.investmentId();

        assertThat(body).isNotNull();
        assertThat(body.cashbookId()).isNotNull();
        assertThat(body.investmentId()).isNotNull();
        assertThat(body.amount()).isEqualTo(BigDecimal.valueOf(amount));
        assertThat(body.typeActionEnum()).isEqualTo(TypeActionEnum.valueOf(typeAction));
        assertThat(body.categoryEnum()).isEqualTo(CategoryEnum.valueOf(category));
    }

    @Entao("o Investment foi criado, com amount {int} e typeAction {string} e category {string}, no banco de dados pelo InvestmentController")
    public void o_investment_foi_criado_com_amount_e_type_action_e_category_no_banco_de_dados_pelo_investment_controller(Integer int1, String string, String string2) {
        var investmentEntity = investmentRepository.findById(idInvestment);
        assertThat(investmentEntity).isNotEmpty();
    }

    @Dado("um UUID de Investiment, com amount {int} e typeAction {string} e category {string}, de um Cashbook, com ano {int} e documento {string}")
    public void um_uuid_de_investiment_com_amount_e_type_action_e_category_de_um_cashbook_com_ano_e_documento(
            Integer amount, String typeAction, String category, Integer yearReference, String document) {

        var cashbookEntity = cashbookRepository
                .findByYearReferenceAndDocument(Year.of(yearReference), document).get();

        var investmentEntity = InvestmentEntity.builder()
                .cashbook(cashbookEntity)
                .description("Descrição qualquer")
                .amount(BigDecimal.valueOf(amount))
                .typeActionEnum(TypeActionEnum.valueOf(typeAction))
                .dateOperation(LocalDate.now())
                .categoryEnum(CategoryEnum.valueOf(category))
                .supplier("Fornecedor qualquer")
                .build();

        var investmentSave = investmentRepository.save(investmentEntity);
        idInvestment = investmentSave.getInvestmentId();

        assertThat(cashbookEntity).isNotNull();
        assertThat(investmentSave).isNotNull();
        assertThat(idInvestment).isNotNull();
    }

    @Quando("a requisição Get for feita no método findById do InvestmentController")
    public void a_requisição_get_for_feita_no_método_find_by_id_do_investment_controller() {

        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .get("/" + idInvestment);

        assertThat(response).isNotNull();
    }

    @Entao("com um InvestmentFindDtoResponse no body, com amount {int} e typeAction {string} e category {string}")
    public void com_um_investment_find_dto_response_no_body_com_amount_e_type_action_e_category(
            Integer amount, String typeAction, String category) {

        var body = response.as(InvestmentFindDtoResponse.class);
        assertThat(body).isNotNull();
        assertThat(body.investmentId()).isNotNull();
        assertThat(body.amount().setScale(0, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(amount));
        assertThat(body.typeActionEnum()).isEqualTo(TypeActionEnum.valueOf(typeAction));
        assertThat(body.categoryEnum()).isEqualTo(CategoryEnum.valueOf(category));
    }

    @Quando("a requisição Delete for feita no método delete do InvestmentController")
    public void a_requisicao_delete_for_feita_no_metodo_delete_do_investment_controller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .delete("/" + idInvestment);

        assertThat(response).isNotNull();
    }

    @Entao("o Investment foi apagado do banco de dados pelo InvestmentController")
    public void o_investment_foi_apagado_do_banco_de_dados_pelo_investment_controller() {
        var registrationEntity = investmentRepository.findById(idInvestment);
        assertThat(registrationEntity).isEmpty();
    }
}

