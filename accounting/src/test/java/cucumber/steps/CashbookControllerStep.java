package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

public class CashbookControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private Response response;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_NOMAD)
                .setBasePath(ConstantsTest.PATH_ACCOUNTING)
                .build();

        RestAssured.port = Integer.parseInt(System.getProperty("local.server.port", "0"));
    }

    @Dado("um ambiente de teste de Accounting ativado")
    public void um_ambiente_de_teste_de_accounting_ativado() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES", Integer.class);
        assertThat(count).isNotNull();
    }

    @Dado("uma requisição Post com CashbookCreateDtoRequest válido, com ano {int} e documento {string}")
    public void uma_requisicao_post_com_cashbook_create_dto_request_valido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);
    }

    @Quando("a requisição Post for feita no método create")
    public void a_requisicao_post_for_feita_no_metodo_create() {
        response = RestAssured
                .given().spec(requestSpecification)
                .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .body(cashbookCreateDtoRequest)
                .when()
                .post();
    }

    @Entao("receberei uma ResponseEntity com HTTP {int}")
    public void receberei_uma_response_entity_com_http(Integer status) {
        org.junit.jupiter.api.Assertions.assertEquals(status, response.getStatusCode());
    }

    @Entao("com um CashbookDtoResponse no body, com id e ano {int} e documento {string}")
    public void com_um_cashbook_dto_response_no_body_com_id_e_ano_e_documento(Integer ano, String documento) {
        CashbookDtoResponse body = response.as(CashbookDtoResponse.class);
        assertThat(body).isNotNull();
        assertThat(body.cashbookId()).isNotNull();
        assertThat(body.yearReference()).isEqualTo(Year.of(ano));
        assertThat(body.document()).isEqualTo(documento);
    }
}

