package cucumber;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.application.core.domain.Cashbook;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import utils.GenerateCPF;

import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

public class CashbookCucumber {

    private CashbookCreateDtoRequest cashbookRequest;

    private int contador = 0;

    @Dado("um Cashbook válido")
    public void um_cashbook_válido() {
        cashbookRequest = new CashbookCreateDtoRequest(Year
                .of(ThreadLocalRandom
                        .current()
                        .nextInt(1000, 2025)),
                GenerateCPF.gerarCPF());
    }

    @Quando("fizer Post")
    public void fizer_post() {

    }

    @Então("receber HTTP {int} Created")
    public void receber_http_created(Integer int1) {

    }

    @Dado("que o valor do contador é {int}")
    public void que_o_valor_do_contador_é(Integer int1) {
        contador = int1;
    }

    @Quando("eu incrementar em {int}")
    public void eu_incrementar_em(Integer int1) {
        contador = contador + int1;
    }

    @Então("o valor do contador será {int}")
    public void o_valor_do_contador_será(Integer int1) {
        Assertions.assertEquals(int1, contador);
    }
}

