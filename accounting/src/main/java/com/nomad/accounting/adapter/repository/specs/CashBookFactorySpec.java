package com.nomad.accounting.adapter.repository.specs;

import com.nomad.accounting.adapter.dto.filter.CashBookFilter;
import com.nomad.accounting.adapter.entity.CashBookEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class CashBookFactorySpec {

    public static Specification<CashBookEntity> dynamicQuery(final CashBookFilter filter) {

        return ((root, query, criteriaBuilder) -> {

            var parameters = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(filter.cashBookId())) {
                parameters.add(criteriaBuilder.equal(root.get("cashBookId"), filter.cashBookId()));
            }

            if(!ObjectUtils.isEmpty(filter.yearReference())) {
                parameters.add(criteriaBuilder.equal(root.get("yearReference"), filter.yearReference()));
            }

            if(!ObjectUtils.isEmpty(filter.document())) {
                parameters.add(criteriaBuilder.equal(root.get("document"), filter.document()));
            }

            if(!ObjectUtils.isEmpty(filter.registration())) {

                var registerFilter = filter.registration();

                if(!ObjectUtils.isEmpty(registerFilter.dateStart())) {
                    parameters.add(criteriaBuilder
                            .greaterThanOrEqualTo(root.get("registrations").get("dateOperation"), registerFilter.convertDateStart()));
                }

                if(!ObjectUtils.isEmpty(registerFilter.dateEnd())) {
                    parameters.add(criteriaBuilder
                            .lessThanOrEqualTo(root.get("registrations").get("dateOperation"), registerFilter.convertDateEnd()));
                }

                if(!ObjectUtils.isEmpty(registerFilter.typeOperation())) {
                    parameters.add(criteriaBuilder
                            .equal(root.get("registrations").get("typeOperation"), registerFilter.typeOperation()));
                }

                if(!ObjectUtils.isEmpty(registerFilter.costCenter())) {
                    parameters.add(criteriaBuilder
                            .equal(root.get("registrations").get("costCenter"), registerFilter.costCenter()));
                }
            }

            return criteriaBuilder.and(parameters.toArray(new Predicate[0]));
        });
    }
}

