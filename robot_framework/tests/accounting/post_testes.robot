*** Settings ***
Resource        ../../resources/accounting.resource

*** Variables ***

*** Test Cases ***
Cenario 0: verificar palavras chaves
    Gerar UUID
    Gerar Ano

Cenario 1: cadastrar um novo Cashbook com sucesso
    Gerar Cashbook



#    Criar um Cashbook novo
    # Cadastrar novo Cashbook
    # Conferir se novo Cashbook foi cadastrado corretamente

# Cenário 2: atualizar um Cashbook com sucesso

# Cenário 3: buscar todos os Cashbooks com sucesso

# Cenário 4: consultar um Cashbook por Id com sucesso

# Cenário 5: pesquisar Cashbook por documento com sucesso

# Cenário 6: deletar Cashbook com sucesso


