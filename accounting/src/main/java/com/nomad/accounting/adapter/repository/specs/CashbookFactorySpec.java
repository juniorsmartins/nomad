package com.nomad.accounting.adapter.repository.specs;

import com.nomad.accounting.adapter.dto.filter.CashbookFilter;
import com.nomad.accounting.adapter.entity.CashbookEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@RequiredArgsConstructor
public class CashbookFactorySpec {

    public static Specification<CashbookEntity> dynamicQuery(final CashbookFilter filter) {

        return ((root, query, criteriaBuilder) -> {

            var parameters = new ArrayList<Predicate>();

            if (!ObjectUtils.isEmpty(filter.cashBookId())) {
                parameters.add(criteriaBuilder.equal(root.get("cashbookId"), filter.cashBookId()));
            }

            if (!ObjectUtils.isEmpty(filter.yearReference())) {
                parameters.add(criteriaBuilder.equal(root.get("yearReference"), filter.yearReference()));
            }

            if (!ObjectUtils.isEmpty(filter.document())) {
                parameters.add(criteriaBuilder.equal(root.get("document"), filter.document()));
            }

            if (!ObjectUtils.isEmpty(filter.registration())) {

                if (!ObjectUtils.isEmpty(filter.registration().dateStart())) {
                    parameters.add(criteriaBuilder
                            .greaterThanOrEqualTo(root.get("registrations").get("dateOperation"), filter.registration().convertDateStart()));
                }

                if (!ObjectUtils.isEmpty(filter.registration().dateEnd())) {
                    parameters.add(criteriaBuilder
                            .lessThanOrEqualTo(root.get("registrations").get("dateOperation"), filter.registration().convertDateEnd()));
                }

                if (!ObjectUtils.isEmpty(filter.registration().typeOperationEnum())) {
                    parameters.add(criteriaBuilder
                            .equal(root.get("registrations").get("typeOperation"), filter.registration().typeOperationEnum()));
                }

                if (!ObjectUtils.isEmpty(filter.registration().costCenterEnum())) {
                    parameters.add(criteriaBuilder
                            .equal(root.get("registrations").get("costCenter"), filter.registration().costCenterEnum()));
                }
            }

            return criteriaBuilder.and(parameters.toArray(new Predicate[0]));
        });
    }
}

