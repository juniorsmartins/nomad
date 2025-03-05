*** Settings ***
Library             RequestsLibrary
Library             String
Library             Collections

*** Keywords ***
Gerar UUID
    ${uuid}         Evaluate        str(uuid.uuid4())               modules=uuid
    Log             Uuid gerado: ${uuid}
    RETURN          ${uuid}

Gerar Ano
    ${ano}          Evaluate        random.randint(1800, 2025)      modules=random
    Log             Ano gerado: ${ano}
    Set Test Variable    ${ANO_TESTE}  ${ano}
    RETURN          ${ano}

Gerar 11 numeros aleatorios
    ${numeros}      Generate Random String  length=11  chars=[NUMBERS]
    Log             Números aleatórios: ${numeros}
    Set Test Variable    ${NUMEROS_TESTE}  ${numeros} 
    RETURN          ${numeros}

Gerar body de Cashbook
    ${ano}           Gerar Ano
    ${numeros}       Gerar 11 numeros aleatorios
    ${body}          Create Dictionary
    ...                 yearReference=${ano}
    ...                 document=${numeros}
    Log              ${body}
    Set Test Variable   ${BODY_TESTE}   ${body}
    RETURN           ${body}

Criar sessão no Accounting
    ${headers}					Create Dictionary
    ...								accept=application/json
    ...								Content-Type=application/json
    Log							${headers}
    Create Session              alias=Accounting    url=http://localhost:9201/api/v1/accounting    headers=${headers}

Post de Cashbook em Accounting
    [Arguments]                 ${status_code_desejado}
    Criar sessão no Accounting
    ${resposta}                 Post On Session
    ...                            alias=Accounting
    ...                            url=/cashbook
    ...                            json=${BODY_TESTE}
    ...                            expected_status=${status_code_desejado}
    Log                         ${resposta.json()}
    Set Test Variable           ${RESPOSTA}  ${resposta.json()}

Conferir sucesso no Post de Cashbook em Accounting
    Log                         ${RESPOSTA}
    ${ANO_STRING}=  Convert To String  ${ANO_TESTE}
    Dictionary Should Contain Item  ${RESPOSTA}  yearReference  ${ANO_STRING}
    Dictionary Should Contain Item  ${RESPOSTA}  document  ${NUMEROS_TESTE}

Repetir Post de Cashbook em Accounting
    Log                         ${BODY_TESTE}
    Post de Cashbook em Accounting      status_code_desejado=500

Conferir falha no Post repetido de Cashbook em Accounting
    Log                             ${RESPOSTA}
    Dictionary Should Contain Item  ${RESPOSTA}     error   Internal Server Error

Consultar cadastro de Cashbook por Id em Accounting
    [Arguments]                 ${status_code_desejado}
    ${resposta_consulta}        GET On Session
    ...                            alias=Accounting
    ...                            url=/cashbook/${RESPOSTA["cashbookId"]}
    ...                            expected_status=${status_code_desejado}
    Log                         ${resposta_consulta}
    Log                         ${resposta_consulta.json()}
    Set Test Variable           ${RESP_CONSULTA}    ${resposta_consulta.json()}

Conferir dados retornados do Get de Cashbook em Accounting
    Log                                 ${RESP_CONSULTA}
    ${ANO_STRING}=  Convert To String  ${ANO_TESTE}
    Dictionary Should Contain Item      ${RESP_CONSULTA}    cashbookId      ${RESPOSTA["cashbookId"]}
    Dictionary Should Contain Item      ${RESP_CONSULTA}    yearReference   ${ANO_STRING}
    Dictionary Should Contain Item      ${RESP_CONSULTA}    document        ${NUMEROS_TESTE}


