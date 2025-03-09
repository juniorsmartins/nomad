package com.nomad.accounting.adapter.dto.filter;

import com.nomad.accounting.application.core.domain.enums.CategoryEnum;
import com.nomad.accounting.application.core.domain.enums.TypeActionEnum;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record InvestmentFilter(

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        String dateStart,

        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        String dateEnd,

        TypeActionEnum typeActionEnum,

        CategoryEnum categoryEnum
) {
        public LocalDate convertDateStart() {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(this.dateStart, formatter);
        }

        public LocalDate convertDateEnd() {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(this.dateEnd, formatter);
        }
}

