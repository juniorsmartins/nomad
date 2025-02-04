package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import cucumber.utils.GenerateCPF;

import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

public class CashbookCucumber {

    private CashbookCreateDtoRequest cashbookRequest;

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
}

