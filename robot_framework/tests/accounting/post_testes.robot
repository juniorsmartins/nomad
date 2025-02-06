*** Settings ***
Resource        ../../resources/accounting.resource

*** Variables ***

*** Test Cases ***
Cenario 1: cadastrar um novo Cashbook com sucesso
    Gerar body de Cashbook
    Post de Cashbook em Accounting      status_code_desejado=201
    Conferir sucesso no Post de Cashbook em Accounting

Cenário 2: cadastrar um Cashbook existente com falha
    Gerar body de Cashbook
    Post de Cashbook em Accounting      status_code_desejado=201
    Conferir sucesso no Post de Cashbook em Accounting
    Repetir Post de Cashbook em Accounting
    Conferir falha no Post repetido de Cashbook em Accounting

# Cenário 2: atualizar um Cashbook com sucesso

# Cenário 3: buscar todos os Cashbooks com sucesso

# Cenário 4: consultar um Cashbook por Id com sucesso

# Cenário 5: pesquisar Cashbook por documento com sucesso

# Cenário 6: deletar Cashbook com sucesso


