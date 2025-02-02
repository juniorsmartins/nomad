*** Settings ***
Library        RequestsLibrary
Library        String

*** Keywords ***
Criar um usuário novo
	${palavra_aleatoria}			Generate Random String	length=10	chars=[LETTERS]
	${palavra_aleatoria_lower}		Convert To Lower Case	${palavra_aleatoria}
	Set Test Variable				${EMAIL_TESTE}		${palavra_aleatoria_lower}@emailteste.com
	Log 							${EMAIL_TESTE}

Criar sessão na ServeRest
	${headers}						Create Dictionary
	...									accept=application/json
	...									Content-Type=application/json
	Log								${headers}
	Create Session					alias=ServeRest  url=https://serverest.dev  headers=${headers}

Criar body para ServeRest usuarios
	${body}							Create Dictionary
	...									nome=Fulano da Silva
	...									email=${EMAIL_TESTE}
	...									password=1234
	...									administrador=true
	Log 							${body}
	[RETURN]						${body}

Cadastrar o usuário criado na ServeRest
	Criar sessão na ServeRest
	${body}							Criar body para ServeRest usuarios
	${resposta}						Post On Session
	...									alias=ServeRest
	...									url=/usuarios
	...									json=${body}
	Log								${resposta.json()}


