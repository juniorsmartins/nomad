package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.RegistrationCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.repository.CashbookRepository;
import com.nomad.accounting.adapter.repository.RegistrationRepository;
import com.nomad.accounting.application.core.domain.enums.CostCenterEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperationEnum;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

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

    @Então("receberei do RegistrationController uma ResponseEntity com HTTP {int}")
    public void receberei_do_registration_controller_uma_response_entity_com_http(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode());
    }

    @Então("com um RegistrationDtoResponse no body, com amount {int} e typeOperation {string}")
    public void com_um_registration_dto_response_no_body_com_amount_e_type_operation(Integer amount, String typeOperation) {
        var body = response.as(RegistrationDtoResponse.class);
        assertThat(body).isNotNull();
        assertThat(body.cashbookId()).isNotNull();
        assertThat(body.registrationId()).isNotNull();
        assertThat(body.amount()).isEqualTo(BigDecimal.valueOf(amount));
        assertThat(body.typeOperationEnum()).isEqualTo(TypeOperationEnum.valueOf(typeOperation));
    }
}

