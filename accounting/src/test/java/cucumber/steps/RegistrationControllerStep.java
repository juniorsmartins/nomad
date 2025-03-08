package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.dto.out.RegistrationFindDtoResponse;
import com.nomad.accounting.adapter.entity.RegistrationEntity;
import com.nomad.accounting.adapter.repository.CashbookRepository;
import com.nomad.accounting.adapter.repository.RegistrationRepository;
import com.nomad.accounting.application.core.domain.enums.CostCenterEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperationEnum;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
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

public class RegistrationControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private CashbookRepository cashbookRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    private Response response;

    private RegistrationCreateDtoRequest registrationCreateDtoRequest;

    private UUID idRegistration;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_NOMAD)
                .setBasePath(ConstantsTest.PATH_ACCOUNTING_REGISTRATION)
                .setPort(port)
                .build();
    }

    @Dado("uma requisição Post com RegistrationCreateDtoRequest válido, com amount {int} e typeOperation {string}")
    public void uma_requisicao_post_com_registration_create_dto_request_valido_com_amount_e_type_operation(Integer amount, String typeOperation) {

        registrationCreateDtoRequest = new RegistrationCreateDtoRequest(
                "Pagamento de barbearia", BigDecimal.valueOf(amount), TypeOperationEnum.valueOf(typeOperation),
                LocalDate.now(), CostCenterEnum.BARBERSHOP, "Yuris Barbershop");

        assertThat(registrationCreateDtoRequest).isNotNull();
    }

    @Quando("a requisição Post for feita no método create, para cashbook com ano {int} e document {string}")
    public void a_requisicao_post_for_feita_no_metodo_create_para_cashbook_com_ano_e_document(Integer yearReference, String document) {
        var idCashbook = cashbookRepository.findByYearReferenceAndDocument(Year.of(yearReference), document)
                .get().getCashbookId();

        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                    .body(registrationCreateDtoRequest)
                .when()
                    .post("/" + idCashbook);

        assertThat(response).isNotNull();
    }

    @Entao("receberei do RegistrationController uma ResponseEntity com HTTP {int}")
    public void receberei_do_registration_controller_uma_response_entity_com_http(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode());
    }

    @Entao("com um RegistrationDtoResponse no body, com amount {int} e typeOperation {string}")
    public void com_um_registration_dto_response_no_body_com_amount_e_type_operation(Integer amount, String typeOperation) {
        var body = response.as(RegistrationDtoResponse.class);
        assertThat(body).isNotNull();
        assertThat(body.cashbookId()).isNotNull();
        assertThat(body.registrationId()).isNotNull();
        assertThat(body.amount()).isEqualTo(BigDecimal.valueOf(amount));
        assertThat(body.typeOperationEnum()).isEqualTo(TypeOperationEnum.valueOf(typeOperation));
    }

    @Dado("um UUID de Registration, com amount {int} e typeOperation {string}, referente a um Cashbook, com ano {int} e documento {string}")
    public void um_uuid_de_registration_com_amount_e_type_operation_referente_a_um_cashbook_com_ano_e_documento(
            Integer amount, String typeOperation, Integer yearReference, String document) {

        var cashbookEntity = cashbookRepository
                .findByYearReferenceAndDocument(Year.of(yearReference), document).get();

        var registrationEntity = RegistrationEntity.builder()
                .cashbook(cashbookEntity)
                .amount(BigDecimal.valueOf(amount))
                .typeOperationEnum(TypeOperationEnum.valueOf(typeOperation))
                .description("Qualquer descrição")
                .dateOperation(LocalDate.now())
                .costCenterEnum(CostCenterEnum.WAGE)
                .supplier("Qualquer fornecedor")
                .build();

        var registrationSave = registrationRepository.save(registrationEntity);
        idRegistration = registrationSave.getRegistrationId();

        assertThat(registrationSave).isNotNull();
        assertThat(idRegistration).isNotNull();
    }

    @Quando("a requisição Get for feita no método findById do RegistrationController")
    public void a_requisicao_get_for_feita_no_metodo_find_by_id_do_registrationcontroller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .get("/" + idRegistration);

        assertThat(response).isNotNull();
    }

    @Entao("com um RegistrationFindDtoResponse no body, com amount {int} e typeOperation {string}")
    public void com_um_registration_find_dto_response_no_body_com_amount_e_type_operation(Integer amount, String typeOperation) {
        var body = response.as(RegistrationFindDtoResponse.class);
        assertThat(body).isNotNull();
        assertThat(body.registrationId()).isNotNull();
        assertThat(body.amount().setScale(0, RoundingMode.HALF_UP)).isEqualTo(BigDecimal.valueOf(amount));
        assertThat(body.typeOperationEnum()).isEqualTo(TypeOperationEnum.valueOf(typeOperation));
    }

    @Quando("a requisição Delete for feita no método delete do RegistrationController")
    public void a_requisicao_delete_for_feita_no_metodo_delete_do_registrationcontroller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                .delete("/" + idRegistration);

        assertThat(response).isNotNull();
    }

    @Entao("o Registration terá sido apagado do banco de dados")
    public void o_registration_tera_sido_apagado_do_banco_de_dados() {
        var registrationEntity = registrationRepository.findById(idRegistration);
        assertThat(registrationEntity).isEmpty();
    }
}

