*** Settings ***
Library        RequestsLibrary
Library        String
Library        cpf_generator.py

*** Keywords ***
Gerar CPF Válido
    ${cpf}=    Gerar CPF
    return ${cpf}

Criar um Cashbook novo
    ${cpf}     Gerar CPF Válido
    Log        ${cpf}
        

