package cucumber.estudos;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;
import java.util.Date;

public class AprenderCucumber {

    private int contador = 0;

    private Date entrega = new Date();

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

    @Dado("que a entrega será dia {int}\\/{int}\\/{int}")
    public void que_a_entrega_será_dia(Integer dia, Integer mes, Integer ano) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.YEAR, ano);
        entrega = cal.getTime();
    }

    @Quando("a entrega atrasar em {int} dias")
    public void a_entrega_atrasar_em_dias(Integer dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        entrega = cal.getTime();
    }

    @Então("a entrega será efetuada em {int}\\/{int}\\/{int}")
    public void a_entrega_será_efetuada_em(Integer dia, Integer mes, Integer ano) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.YEAR, ano);

        Assertions.assertEquals(cal.getTime().toString(), entrega.toString());
    }
}

