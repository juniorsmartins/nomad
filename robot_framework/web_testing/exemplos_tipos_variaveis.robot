*** Settings ***
Documentation            Exemplos de tipos de variáveis: SIMPLES, LISTAS E DICIONÁRIOS

*** Variables ***
# Simples
${SIMPLES}               Vamos ver os tipos de variáveis no Robot!

# Tipo lista - valores separados por espaço duplo
@{FRUTAS}                morango  banana  maça  uva  abacaxi

# Tipo Dicionário
&{PESSOA}                nome=Apollo Creed  email=apollo_creed@email.com  idade=32  sexo=masculino

*** Test Cases ***
Caso de teste de exemplo 01
    Uma keyword qualquer 01


*** Keywords ***
Uma keyword qualquer 01
    # Simples
    Log        ${SIMPLES}

    # Lista
    Log        Essa tem que ser maça: ${FRUTAS[2]} e essa tem que ser morango: ${FRUTAS[0]}

    # Dicionário
    Log        Nome: ${PESSOA.nome} e email: ${PESSOA.email}



