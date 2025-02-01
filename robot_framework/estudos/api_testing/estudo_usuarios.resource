*** Settings ***
Library        RequestsLibrary
Library        String

*** Keywords ***
Criar um usuário novo
	${palavra_aleatoria}		Generate Random String	length=10	chars=[LETTERS]
	Log 						${palavra_aleatoria}

