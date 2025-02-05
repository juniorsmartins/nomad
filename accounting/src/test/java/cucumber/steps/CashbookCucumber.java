package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.out.CashbookDtoResponse;
import com.nomad.accounting.adapter.dto.out.RegistrationDtoResponse;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.core.domain.enums.CostCenter;
import com.nomad.accounting.application.core.domain.enums.TypeOperation;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CashbookCucumber {

    private final CentralMapper centralMapper = Mappers.getMapper(CentralMapper.class);

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private Cashbook cashbook;

    private CashbookUpdateDtoRequest cashbookUpdateDtoRequest;

    private Registration registration;

    private CashbookDtoResponse cashbookDtoResponse;

    @Dado("um CashbookCreateDtoRequest válido, com ano {int} e documento {string}")
    public void um_cashbook_create_dto_request_válido_com_ano_e_documento(Integer ano, String documento) {
        cashbookCreateDtoRequest = new CashbookCreateDtoRequest(Year.of(ano), documento);
    }

    @Quando("converter CashbookCreateDtoRequest para Cashbook")
    public void converter_cashbook_create_dto_request_para_cashbook() {
        cashbook = centralMapper.toCashbook(cashbookCreateDtoRequest);
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
        cashbook = centralMapper.toCashbook(cashbookUpdateDtoRequest);
    }

    @Então("receber um Cashbook válido, com ano {int} e documento {string} e id {string}")
    public void receber_um_cashbook_válido_com_ano_e_documento_e_id(Integer ano, String documento, String idUuid) {
        assertNotNull(cashbook);
        assertEquals(Cashbook.class, cashbook.getClass());
        assertEquals(UUID.fromString(idUuid), cashbook.getCashbookId());
        assertEquals(Year.of(ano), cashbook.getYearReference());
        assertEquals(documento, cashbook.getDocument());
    }

    @Dado("um Cashbook válido, com id {string} e ano {int} e documento {string}")
    public void um_cashbook_válido_com_id_e_ano_e_documento(String idUuid, Integer ano, String documento) {
        cashbook = Cashbook.builder()
                .cashbookId(UUID.fromString(idUuid))
                .yearReference(Year.of(ano))
                .document(documento)
                .build();
    }

    @Dado("um Cashbook com Registration válido, com id {string} e description {string} e amount {double} e typeOperation {string} e dateOperation {string} e costCenter {string} e supplier {string} e Registration com CashbookEntity com apenas cashbookId {string}")
    public void um_cashbook_com_registration_válido_com_id_e_description_e_amount_e_type_operation_e_date_operation_e_cost_center_e_supplier(String idUuid, String description, Double amount, String typeOperation, String dateOperation, String costCenter, String supplier, String cashbookId) {

        var cashbookEntity = CashbookEntity.builder()
                .cashbookId(UUID.fromString(cashbookId))
                .build();

        registration = Registration.builder()
                .registrationId(UUID.fromString(idUuid))
                .cashbook(cashbookEntity)
                .description(description)
                .amount(BigDecimal.valueOf(amount))
                .typeOperation(TypeOperation.valueOf(typeOperation))
                .dateOperation(LocalDate.parse(dateOperation))
                .costCenter(CostCenter.valueOf(costCenter))
                .supplier(supplier)
                .build();
        cashbook.setRegistrations(List.of(registration));
    }

    @Quando("converter Cashbook para CashbookDtoResponse")
    public void converter_cashbook_para_cashbook_dto_response() {
        cashbookDtoResponse = centralMapper.toCashbookDtoResponse(cashbook);
    }

    @Então("receber um CashbookDtoResponse válido, com id {string} e ano {int} e documento {string}")
    public void receber_um_cashbook_dto_response_válido_com_id_e_ano_e_documento(String idUuid, Integer ano, String documento) {

        Assertions.assertNotNull(cashbookDtoResponse);
        Assertions.assertEquals(CashbookDtoResponse.class, cashbookDtoResponse.getClass());
        Assertions.assertEquals(UUID.fromString(idUuid), cashbookDtoResponse.cashbookId());
        Assertions.assertEquals(Year.of(ano), cashbookDtoResponse.yearReference());
        Assertions.assertEquals(documento, cashbookDtoResponse.document());
    }

    @Então("receber um CashbookDtoResponse com Registration válido, com id {string} e description {string} e amount {double} e typeOperation {string} e dateOperation {string} e costCenter {string} e supplier {string} e Registration válido com CashbookEntity com apenas cashbookId {string}")
    public void receber_um_cashbook_dto_response_com_registration_válido_com_id_e_description_e_amount_e_type_operation_e_date_operation_e_cost_center_e_supplier_e_cashbook_válido(String idUuid, String description, Double amount, String typeOperation, String dateOperation, String costCenter, String supplier, String cashbookId) {
        var registrationDtoResponse = cashbookDtoResponse.registrations().getFirst();

        Assertions.assertNotNull(registrationDtoResponse);
        Assertions.assertEquals(RegistrationDtoResponse.class, registrationDtoResponse.getClass());
        Assertions.assertEquals(UUID.fromString(idUuid), registrationDtoResponse.registrationId());
        Assertions.assertEquals(UUID.fromString(cashbookId), registrationDtoResponse.cashbookId());
        Assertions.assertEquals(description, registrationDtoResponse.description());
        Assertions.assertEquals(BigDecimal.valueOf(amount), registrationDtoResponse.amount());
        Assertions.assertEquals(TypeOperation.valueOf(typeOperation), registrationDtoResponse.typeOperation());
        Assertions.assertEquals(LocalDate.parse(dateOperation), registrationDtoResponse.dateOperation());
        Assertions.assertEquals(CostCenter.valueOf(costCenter), registrationDtoResponse.costCenter());
        Assertions.assertEquals(supplier, registrationDtoResponse.supplier());
    }

    @Quando("fizer Post")
    public void fizer_post() {

    }

    @Então("receber HTTP {int}")
    public void receber_http(Integer int1) {

    }
}

