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

Gerar Cashbook
    ${ano}          Gerar Ano
    Log             Ano: ${ano}




