package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.repository.CashbookRepository;
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
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class CashbookControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CashbookRepository cashbookRepository;

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    private Response response;

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private CashbookEntity cashbookEntity;

    private UUID idCashbook;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_NOMAD)
                .setBasePath(ConstantsTest.PATH_ACCOUNTING_CASHBOOK)
                .setPort(port)
                .build();
    }

    @Dado("um ambiente de teste de Accounting ativado")
    public void um_ambiente_de_teste_de_accounting_ativado() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES", Integer.class);
        assertThat(count).isNotNull();
    }

    @Dado("uma requisicao Post com CashbookCreateDtoRequest valido, com ano {int} e documento {string}")
    public void uma_requisicao_post_com_cashbook_create_dto_request_valido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);

        assertThat(cashbookCreateDtoRequest).isNotNull();
    }

    @Quando("a requisicao Post for feita no metodo create do CashbookController")
    public void a_requisicao_post_for_feita_no_metodo_create_do_cashbookcontroller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                    .body(cashbookCreateDtoRequest)
                .when()
                    .post();

        assertThat(response).isNotNull();
    }

    @Entao("receberei uma ResponseEntity com HTTP {int} do CashbookController")
    public void receberei_do_cashbookcontroller_uma_response_entity_com_http(Integer status) {
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

    @Dado("cadastros de Cashbook, sem registrations, disponíveis na massa de dados")
    public void cadastros_de_cashbook_sem_registrations_disponiveis_na_massa_de_dados(io.cucumber.datatable.DataTable dataTable) {
        cashbookRepository.deleteAll();

        List<Map<String, String>> cashbooksData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : cashbooksData) {
            int yearReference = Integer.parseInt(row.get("yearReference"));
            String document = row.get("document");

            var cashbookEntity = CashbookEntity.builder()
                    .yearReference(Year.of(yearReference))
                    .document(document)
                    .registrations(null)
                    .build();

            cashbookRepository.save(cashbookEntity);
        }

        assertThat(cashbookRepository.count()).isEqualTo(cashbooksData.size());
    }

    @Dado("um UUID referente ao ano {int} e o documento {string}")
    public void um_uuid_referente_ao_ano_e_o_documento(Integer ano, String documento) {
        cashbookEntity = cashbookRepository.findByYearReferenceAndDocument(Year.of(ano), documento).get();
    }

    @Quando("uma requisição Get for feita no método findById do CashbookController")
    public void uma_requisicao_get_for_feita_no_metodo_find_by_id_do_cashbookcontroller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .get("/" + cashbookEntity.getCashbookId());

        assertThat(response).isNotNull();
    }

    @Dado("um UUID de Cashbook, com ano {int} e document {string}")
    public void um_uuid_de_cashbook_com_ano_e_document(Integer yearReference, String document) {
        idCashbook = cashbookRepository.findByYearReferenceAndDocument(Year.of(yearReference), document)
                .get().getCashbookId();

        assertThat(idCashbook).isNotNull();
    }

    @Quando("a requisição Delete for feita no método delete do CashbookController")
    public void a_requisicao_delete_for_feita_no_metodo_delete_do_cashbook_controller() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .delete("/" + idCashbook);

        assertThat(response).isNotNull();
    }

    @Entao("o Cashbook terá sido apagado do banco de dados pelo CashbookController")
    public void o_cashbook_terá_sido_apagado_do_banco_de_dados() {
        var cashbookEntity = cashbookRepository.findById(idCashbook);
        assertThat(cashbookEntity).isEmpty();
    }
}

