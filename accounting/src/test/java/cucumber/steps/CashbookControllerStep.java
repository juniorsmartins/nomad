package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.Year;

public class CashbookControllerStep {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    @Dado("um ambiente de teste de Accounting ativado")
    public void um_ambiente_de_teste_de_accounting_ativado() {
        throw new io.cucumber.java.PendingException();
    }

    @Dado("uma requisição Post com CashbookCreateDtoRequest válido, com ano {int} e documento {string}")
    public void uma_requisição_post_com_cashbook_create_dto_request_válido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);
    }

    @Quando("a requisição Post for feita no método create")
    public void a_requisição_post_for_feita_no_método_create() {
        throw new io.cucumber.java.PendingException();
    }

    @Entao("receberei uma ResponseEntity com HTTP {int}")
    public void receberei_uma_response_entity_com_http(Integer int1) {
        throw new io.cucumber.java.PendingException();
    }

    @Entao("com um CashbookDtoResponse no body, com id e ano {int} e documento {string}")
    public void com_um_cashbook_dto_response_no_body_com_id_e_ano_e_documento(Integer ano, String documento) {
        throw new io.cucumber.java.PendingException();
    }
}

