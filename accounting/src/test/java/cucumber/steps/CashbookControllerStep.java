package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.repository.CashbookRepository;
import com.nomad.accounting.application.core.domain.Cashbook;
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

import static org.assertj.core.api.Assertions.assertThat;

public class CashbookControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CashbookRepository cashbookRepository;

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private Response response;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader(ConstantsTest.HEADER_PARAM_ORIGIN, ConstantsTest.ORIGIN_NOMAD)
                .setBasePath(ConstantsTest.PATH_ACCOUNTING)
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

    @Quando("a requisicao Post for feita no metodo create")
    public void a_requisicao_post_for_feita_no_metodo_create() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                    .body(cashbookCreateDtoRequest)
                .when()
                    .post();

        assertThat(response).isNotNull();
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

    @Dado("tendo cadastros de Cashbook, sem registrations, disponíveis na massa de dados")
    public void tendo_cadastros_de_cashbook_sem_registrations_disponíveis_na_massa_de_dados(io.cucumber.datatable.DataTable dataTable) {
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

    @Quando("uma requisição Get válida for feita para o método findAll")
    public void uma_requisicao_get_valida_for_feita_para_o_metodo_find_all() {
        response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(ConstantsTest.CONTENT_TYPE_JSON)
                .when()
                    .get();

        assertThat(response).isNotNull();
    }

    @Entao("uma página com CashbookFindDtoResponse no body")
    public void uma_pagina_com_cashbook_find_dto_response_no_body() {


    }
}

