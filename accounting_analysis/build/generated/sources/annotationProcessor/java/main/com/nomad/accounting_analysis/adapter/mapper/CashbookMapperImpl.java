package com.nomad.accounting_analysis.adapter.mapper;

import com.nomad.accounting_analysis.adapter.dto.in.CashbookDtoResponse;
import com.nomad.accounting_analysis.adapter.dto.in.RegistrationDtoResponse;
import com.nomad.accounting_analysis.adapter.dto.response.BalanceCashbookDtoResponse;
import com.nomad.accounting_analysis.application.core.domain.BalanceCashbook;
import com.nomad.accounting_analysis.application.core.domain.Cashbook;
import com.nomad.accounting_analysis.application.core.domain.Registration;
import com.nomad.accounting_analysis.application.core.domain.enums.CostCenter;
import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T15:39:36-0400",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class CashbookMapperImpl implements CashbookMapper {

    @Override
    public Cashbook toCashbook(CashbookDtoResponse cashbookDtoResponse) {
        if ( cashbookDtoResponse == null ) {
            return null;
        }

        Cashbook cashBook = new Cashbook();

        cashBook.setCashbookId( cashbookDtoResponse.cashBookId() );
        cashBook.setYearReference( cashbookDtoResponse.yearReference() );
        cashBook.setDocument( cashbookDtoResponse.document() );
        cashBook.setRegistrations( registrationDtoResponseListToRegistrationList( cashbookDtoResponse.registrations() ) );

        return cashBook;
    }

    @Override
    public BalanceCashbookDtoResponse toBalanceCashbookDtoResponse(BalanceCashbook balanceCashbook) {
        if ( balanceCashbook == null ) {
            return null;
        }

        BigDecimal annualSumCredits = null;
        BigDecimal annualSumDebts = null;
        BigDecimal annualBalance = null;
        Map<CostCenter, BigDecimal> annualSumDebitsByCostCenter = null;
        Map<Month, BigDecimal> monthlySumCredits = null;
        Map<Month, BigDecimal> monthlySumDebits = null;
        Map<Month, BigDecimal> monthlyBalance = null;

        annualSumCredits = balanceCashbook.getAnnualSumCredits();
        annualSumDebts = balanceCashbook.getAnnualSumDebts();
        annualBalance = balanceCashbook.getAnnualBalance();
        Map<CostCenter, BigDecimal> map = balanceCashbook.getAnnualSumDebitsByCostCenter();
        if ( map != null ) {
            annualSumDebitsByCostCenter = new LinkedHashMap<CostCenter, BigDecimal>( map );
        }
        Map<Month, BigDecimal> map1 = balanceCashbook.getMonthlySumCredits();
        if ( map1 != null ) {
            monthlySumCredits = new LinkedHashMap<Month, BigDecimal>( map1 );
        }
        Map<Month, BigDecimal> map2 = balanceCashbook.getMonthlySumDebits();
        if ( map2 != null ) {
            monthlySumDebits = new LinkedHashMap<Month, BigDecimal>( map2 );
        }
        Map<Month, BigDecimal> map3 = balanceCashbook.getMonthlyBalance();
        if ( map3 != null ) {
            monthlyBalance = new LinkedHashMap<Month, BigDecimal>( map3 );
        }

        BalanceCashbookDtoResponse balanceCashBookDtoResponse = new BalanceCashbookDtoResponse( annualSumCredits, annualSumDebts, annualBalance, annualSumDebitsByCostCenter, monthlySumCredits, monthlySumDebits, monthlyBalance );

        return balanceCashBookDtoResponse;
    }

    protected Registration registrationDtoResponseToRegistration(RegistrationDtoResponse registrationDtoResponse) {
        if ( registrationDtoResponse == null ) {
            return null;
        }

        Registration registration = new Registration();

        registration.setDescription( registrationDtoResponse.description() );
        registration.setAmount( registrationDtoResponse.amount() );
        registration.setTypeOperation( registrationDtoResponse.typeOperation() );
        registration.setDateOperation( registrationDtoResponse.dateOperation() );
        registration.setCostCenter( registrationDtoResponse.costCenter() );
        registration.setSupplier( registrationDtoResponse.supplier() );

        return registration;
    }

    protected List<Registration> registrationDtoResponseListToRegistrationList(List<RegistrationDtoResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Registration> list1 = new ArrayList<Registration>( list.size() );
        for ( RegistrationDtoResponse registrationDtoResponse : list ) {
            list1.add( registrationDtoResponseToRegistration( registrationDtoResponse ) );
        }

        return list1;
    }
}
