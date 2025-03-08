package com.nomad.accounting.adapter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = {InvestmentController.URI_INVESTMENT})
@RequiredArgsConstructor
public class InvestmentController {

    protected static final String URI_INVESTMENT = "/api/v1/accounting/investment";


}

