package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.InvestmentCreateDtoRequest;
import com.nomad.accounting.adapter.repository.CashbookRepository;
import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import cucumber.config.ConstantsTest;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentControllerStep {

    private static RequestSpecification requestSpecification;

    @Autowired
    private CashbookRepository cashbookRepository;

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    private Response response;

    private InvestmentCreateDtoRequest investmentCreateDtoRequest;

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

    @Quando("uma requisição Post for feita no método create do InvestmentController")
    public void uma_requisicao_post_for_feita_no_metodo_create_do_investment_controller() {

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entao("receberei um ResponseEntity com HTTP {int} do InvestmentController")
    public void receberei_um_response_entity_com_http_do_investment_controller(Integer int1) {

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entao("com um InvestmentDtoResponse no body, com id e amount {int} e typeAction {string} e category {string}")
    public void com_um_investment_dto_response_no_body_com_id_e_amount_e_type_action_e_category(Integer int1, String string, String string2) {

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entao("o Investment foi criado, com amount {int} e typeAction {string} e category {string}, no banco de dados pelo InvestmentController")
    public void o_investment_foi_criado_com_amount_e_type_action_e_category_no_banco_de_dados_pelo_investment_controller(Integer int1, String string, String string2) {

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

