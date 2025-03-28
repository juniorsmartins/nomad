package cucumber.steps;

import com.nomad.accounting.adapter.dto.in.CashbookCreateDtoRequest;
import com.nomad.accounting.adapter.dto.in.CashbookUpdateDtoRequest;
import com.nomad.accounting.adapter.dto.in.InvestmentCreateDtoRequest;
import com.nomad.accounting.adapter.dto.out.*;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import com.nomad.accounting.adapter.entity.InvestmentEntity;
import com.nomad.accounting.adapter.mapper.CentralMapper;
import com.nomad.accounting.application.core.domain.Cashbook;
import com.nomad.accounting.application.core.domain.Investment;
import com.nomad.accounting.application.core.domain.Registration;
import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.CostCenterEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import com.nomad.accounting.application.core.domain.enums.TypeOperationEnum;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
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

public class CentralMapperSteps {

    private final CentralMapper centralMapper = Mappers.getMapper(CentralMapper.class);

    private CashbookCreateDtoRequest cashbookCreateDtoRequest;

    private Cashbook cashbook;

    private CashbookUpdateDtoRequest cashbookUpdateDtoRequest;

    private Registration registration;

    private CashbookDtoResponse cashbookDtoResponse;

    private InvestmentCreateDtoRequest investmentCreateDtoRequest;

    private Investment investment;

    private InvestmentEntity investmentEntity;

    private InvestmentDtoResponse investmentDtoResponse;

    private InvestmentFindDtoResponse investmentFindDtoResponse;

    private InvestmentSearchDtoResponse investmentSearchDtoResponse;

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
                .typeOperationEnum(TypeOperationEnum.valueOf(typeOperation))
                .dateOperation(LocalDate.parse(dateOperation))
                .costCenterEnum(CostCenterEnum.valueOf(costCenter))
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
        Assertions.assertEquals(TypeOperationEnum.valueOf(typeOperation), registrationDtoResponse.typeOperationEnum());
        Assertions.assertEquals(LocalDate.parse(dateOperation), registrationDtoResponse.dateOperation());
        Assertions.assertEquals(CostCenterEnum.valueOf(costCenter), registrationDtoResponse.costCenterEnum());
        Assertions.assertEquals(supplier, registrationDtoResponse.supplier());
    }

    @Dado("um InvestmentCreateDtoRequest válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void um_investment_create_dto_request_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        investmentCreateDtoRequest = new InvestmentCreateDtoRequest(
                description, BigDecimal.valueOf(amount), TypeActionEnum.valueOf(typeAction),
                LocalDate.now(), CategoryEnum.valueOf(category), supplier);

        assertNotNull(investmentCreateDtoRequest);
    }

    @Quando("converter InvestmentCreateDtoRequest para Investment")
    public void converter_investment_create_dto_request_para_investment() {
        investment = centralMapper.toInvestment(investmentCreateDtoRequest);

        assertNotNull(investment);
    }

    @Entao("receber um Investment válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void receber_um_investment_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        assertEquals(description, investment.getDescription());
        assertEquals(BigDecimal.valueOf(amount), investment.getAmount());
        assertEquals(TypeActionEnum.valueOf(typeAction), investment.getTypeActionEnum());
        assertEquals(CategoryEnum.valueOf(category), investment.getCategoryEnum());
        assertEquals(supplier, investment.getSupplier());
    }

    @Dado("um Investment válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void um_investment_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        investment = Investment.builder()
                .description(description)
                .amount(BigDecimal.valueOf(amount))
                .typeActionEnum(TypeActionEnum.valueOf(typeAction))
                .dateOperation(LocalDate.now())
                .categoryEnum(CategoryEnum.valueOf(category))
                .supplier(supplier)
                .build();

        assertNotNull(investment);
    }

    @Quando("converter Investment para InvestmentEntity")
    public void converter_investment_para_investment_entity() {

        investmentEntity = centralMapper.toInvestmentEntity(investment);

        assertNotNull(investmentEntity);
    }

    @Entao("receber um InvestmentEntity válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void receber_um_investment_entity_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        assertEquals(description, investmentEntity.getDescription());
        assertEquals(BigDecimal.valueOf(amount), investmentEntity.getAmount());
        assertEquals(TypeActionEnum.valueOf(typeAction), investmentEntity.getTypeActionEnum());
        assertEquals(CategoryEnum.valueOf(category), investmentEntity.getCategoryEnum());
        assertEquals(supplier, investmentEntity.getSupplier());
    }

    @Quando("converter Investment para InvestmentDtoResponse")
    public void converter_investment_para_investment_dto_response() {
        investmentDtoResponse = centralMapper.toInvestmentDtoResponse(investment);

        assertNotNull(investmentDtoResponse);
    }

    @Entao("receber um InvestmentDtoResponse válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void receber_um_investment_dto_response_válido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        assertEquals(description, investmentDtoResponse.description());
        assertEquals(BigDecimal.valueOf(amount), investmentDtoResponse.amount());
        assertEquals(TypeActionEnum.valueOf(typeAction), investmentDtoResponse.typeActionEnum());
        assertEquals(CategoryEnum.valueOf(category), investmentDtoResponse.categoryEnum());
        assertEquals(supplier, investmentDtoResponse.supplier());
    }

    @Dado("um InvestmentEntity válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void um_investment_entity_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        investmentEntity = InvestmentEntity.builder()
                .description(description)
                .amount(BigDecimal.valueOf(amount))
                .typeActionEnum(TypeActionEnum.valueOf(typeAction))
                .dateOperation(LocalDate.now())
                .categoryEnum(CategoryEnum.valueOf(category))
                .supplier(supplier)
                .build();

        assertNotNull(investmentEntity);
    }

    @Quando("converter InvestmentEntity para Investment")
    public void converter_investment_entity_para_investment() {
        investment = centralMapper.toInvestment(investmentEntity);

        assertNotNull(investment);
    }

    @Quando("converter InvestmentEntity para InvestmentFindDtoResponse")
    public void converter_investment_entity_para_investment_find_dto_response() {
        investmentFindDtoResponse = centralMapper.toInvestmentFindDtoResponse(investmentEntity);

        assertNotNull(investmentFindDtoResponse);
    }

    @Entao("receber um InvestmentFindDtoResponse válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void receber_um_investment_find_dto_response_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        assertEquals(description, investmentFindDtoResponse.description());
        assertEquals(BigDecimal.valueOf(amount), investmentFindDtoResponse.amount());
        assertEquals(TypeActionEnum.valueOf(typeAction), investmentFindDtoResponse.typeActionEnum());
        assertEquals(CategoryEnum.valueOf(category), investmentFindDtoResponse.categoryEnum());
        assertEquals(supplier, investmentFindDtoResponse.supplier());
    }

    @Quando("converter InvestmentEntity para InvestmentSearchDtoResponse")
    public void converter_investment_entity_para_investment_search_dto_response() {
        investmentSearchDtoResponse = centralMapper.toInvestmentSearchDtoResponse(investmentEntity);

        assertNotNull(investmentSearchDtoResponse);
    }

    @Entao("receber um InvestmentSearchDtoResponse válido, com description {string} e amount {int} e typeAction {string} e category {string} e supplier {string}")
    public void receber_um_investment_search_dto_response_valido_com_description_e_amount_e_type_action_e_category_e_supplier(
            String description, Integer amount, String typeAction, String category, String supplier) {

        assertEquals(description, investmentSearchDtoResponse.description());
        assertEquals(BigDecimal.valueOf(amount), investmentSearchDtoResponse.amount());
        assertEquals(TypeActionEnum.valueOf(typeAction), investmentSearchDtoResponse.typeActionEnum());
        assertEquals(CategoryEnum.valueOf(category), investmentSearchDtoResponse.categoryEnum());
        assertEquals(supplier, investmentSearchDtoResponse.supplier());
    }
}

