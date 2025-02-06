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
    Verificar se a aplicação não permitiu o cadastro repetido

Cenário 03: consultar os dados de um novo usuário
    Criar um usuário novo
    Cadastrar o usuário criado na ServeRest	    status_code_desejado=201
    Conferir se o usuário foi cadastrado corretamente
    Consultar cadastro do novo usuário
#    Conferir os dados retornados

