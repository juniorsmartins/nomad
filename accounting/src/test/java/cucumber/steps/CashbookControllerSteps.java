package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.time.Year;

public class CashbookControllerSteps {

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    @Dado("que está ativado o ambiente de teste de Accounting")
    public void que_está_ativado_o_ambiente_de_teste_de_accounting() {

    }

    @Dado("que enviarei um CashbookCreateDtoRequest válido, com ano {int} e documento {string}")
    public void que_enviarei_um_cashbook_create_dto_request_válido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);
    }

    @Quando("fizer requisição Post")
    public void fizer_requisição_post() {

    }

    @Então("receberei uma resposta com HTTP {int}")
    public void receberei_uma_resposta_com_http(Integer http) {

    }

    @Então("receberei um ResponseEntity com um CashbookDtoResponse no body, com id e ano {int} e documento {string}")
    public void receberei_um_response_entity_com_um_cashbook_dto_response_no_body_com_id_e_ano_e_documento(Integer ano, String documento) {

    }
}

