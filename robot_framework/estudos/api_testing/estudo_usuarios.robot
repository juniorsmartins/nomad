*** Settings ***
Resource        estudo_usuarios.resource

*** Variables ***

*** Test Cases ***
Cenário 01: cadastrar um novo usuário com sucesso na ServeRest
    Criar um usuário novo
    Cadastrar o usuário criado na ServeRest     status_code_desejado=201
    Conferir se o usuário foi cadastrado corretamente

Cenário 02: cadastrar um usuário já existente
    Criar um usuário novo
    Cadastrar o usuário criado na ServeRest     status_code_desejado=201
    Conferir se o usuário foi cadastrado corretamente
    Repetir o cadastro do usuário na ServeRest
    Verificar se a API não permitiu o cadastro repetido


