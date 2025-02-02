*** Settings ***
Library        RequestsLibrary
Library        String

*** Keywords ***
Criar um usuário novo
	${palavra_aleatoria}			Generate Random String	length=10	chars=[LETTERS]
	${palavra_aleatoria_lower}		Convert To Lower Case	${palavra_aleatoria}
	Set Test Variable				${EMAIL_TESTE}		${palavra_aleatoria_lower}@emailteste.com
	Log 							${EMAIL_TESTE}

Cadastrar o usuário criado na ServeRest
	${body}							Create Dictionary

