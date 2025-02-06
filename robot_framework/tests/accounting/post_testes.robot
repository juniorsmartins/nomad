*** Settings ***
Resource        ../../resources/accounting.resource

*** Variables ***

*** Test Cases ***
Cenario 1: cadastrar um novo Cashbook com sucesso
    Gerar body de Cashbook
    Post de Cashbook em Accounting                         status_code_desejado=201
    Conferir sucesso no Post de Cashbook em Accounting

Cenário 2: cadastrar um Cashbook existente com falha
    Gerar body de Cashbook
    Post de Cashbook em Accounting                         status_code_desejado=201
    Conferir sucesso no Post de Cashbook em Accounting
    Repetir Post de Cashbook em Accounting
    Conferir falha no Post repetido de Cashbook em Accounting

Cenário 3: consultar um Cashbook por Id com sucesso
    Gerar body de Cashbook
    Post de Cashbook em Accounting	                       status_code_desejado=201
    Conferir sucesso no Post de Cashbook em Accounting
    Consultar cadastro de Cashbook por Id em Accounting    status_code_desejado=200
    Conferir dados retornados do Get de Cashbook em Accounting


# Cenário 4: atualizar um Cashbook com sucesso

# Cenário 5: buscar todos os Cashbooks com sucesso

# Cenário 6: pesquisar Cashbook por documento com sucesso

# Cenário 7: deletar Cashbook com sucesso


