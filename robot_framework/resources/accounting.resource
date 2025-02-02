*** Settings ***
Library             RequestsLibrary
Library             String

*** Keywords ***
Gerar UUID
    ${uuid}         Evaluate        str(uuid.uuid4())               modules=uuid
    Log             Uuid gerado: ${uuid}
    [RETURN]        ${uuid}

Gerar Ano
    ${ano}          Evaluate        random.randint(1800, 2025)      modules=random
    Log             Ano gerado: ${ano}
    [RETURN]        ${ano}

Gerar 11 numeros aleatorios
    ${numeros}      Generate Random String  length=11  chars=[NUMBERS]
    Log             Números aleatórios: ${numeros}
    [RETURN]        ${numeros}

Gerar Cashbook
    ${ano}           Gerar Ano
    ${numeros}       Gerar 11 numeros aleatorios
    ${body}          Create Dictionary      yearReference=${ano}  document=${numeros}
    Log              ${body}




