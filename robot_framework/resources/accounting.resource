*** Settings ***
Library             RequestsLibrary
Library             String
Library             FakerLibrary
Library             cpf_generator.py

*** Keywords ***
Gerar UUID
    ${uuid}=        Evaluate        str(uuid.uuid4())       modules=uuid
    [Return]        ${uuid}

Gerar UUID
    ${uuid}=        Evaluate        str(uuid.uuid4())       modules=uuid
    [Return]        ${uuid}
