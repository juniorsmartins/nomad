package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.mapper.CashbookMapperIn;
import com.nomad.accounting.application.core.domain.Cashbook;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.mapstruct.factory.Mappers;

import java.time.Year;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CashbookCucumber {

    private final CashbookMapperIn cashbookMapperIn = Mappers.getMapper(CashbookMapperIn.class);

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private Cashbook cashbook;

    private CashbookUpdateDtoRequest cashbookUpdateDtoRequest;

    @Dado("um CashbookCreateDtoRequest válido, com ano {int} e documento {string}")
    public void um_cashbook_create_dto_request_válido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);
    }

    @Quando("converter CashbookCreateDtoRequest para Cashbook")
    public void converter_cashbook_create_dto_request_para_cashbook() {
        cashbook = cashbookMapperIn.toCashbook(cashbookCreateDtoRequest);
    }

    @Então("receber um Cashbook válido, com ano {int} e documento {string}")
    public void receber_um_cashbook_válido_com_ano_e_documento(Integer ano, String documento) {
        assertNotNull(cashbook);
        assertEquals(Cashbook.class, cashbook.getClass());
        assertEquals(Year.of(ano), cashbook.getYearReference());
        assertEquals(documento, cashbook.getDocument());
    }

    @Dado("um CashbookUpdateDtoRequest válido, com ano {int} e documento {string} e id {string}")
    public void um_cashbook_update_dto_request_válido_com_ano_e_documento_e_id(Integer ano, String documento, String idUuid) {
        cashbookUpdateDtoRequest = new CashbookUpdateDtoRequest(UUID.fromString(idUuid), Year.of(ano), documento);
    }

    @Quando("converter CashbookUpdateDtoRequest para Cashbook")
    public void converter_cashbook_update_dto_request_para_cashbook() {
        cashbook = cashbookMapperIn.toCashbook(cashbookUpdateDtoRequest);
    }

    @Então("receber um Cashbook válido, com ano {int} e documento {string} e id {string}")
    public void receber_um_cashbook_válido_com_ano_e_documento_e_id(Integer ano, String documento, String idUuid) {
        assertNotNull(cashbook);
        assertEquals(Cashbook.class, cashbook.getClass());
        assertEquals(UUID.fromString(idUuid), cashbook.getCashbookId());
        assertEquals(Year.of(ano), cashbook.getYearReference());
        assertEquals(documento, cashbook.getDocument());
    }

    @Quando("fizer Post")
    public void fizer_post() {

    }

    @Então("receber HTTP {int}")
    public void receber_http(Integer int1) {

    }
}

