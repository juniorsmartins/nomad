*** Settings ***
Library        RequestsLibrary
Library        String
Library    Collections

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
	[Arguments]						${email}
	${body}							Create Dictionary
	...									nome=Fulano da Silva
	...									email=${email}
	...									password=1234
	...									administrador=true
	Log 							${body}
	RETURN		   				    ${body}

Cadastrar o usuário criado na ServeRest
	[Arguments]						${status_code_desejado}
	Criar sessão na ServeRest
	${body}							Criar body para ServeRest usuarios	email=${EMAIL_TESTE}
	${resposta}						Post On Session
	...									alias=ServeRest
	...									url=/usuarios
	...									json=${body}
	...									expected_status=${status_code_desejado}
	Log								${resposta.json()}
	Set Test Variable               ${RESPOSTA}  ${resposta.json()}

Conferir se o usuário foi cadastrado corretamente
    Log                             ${RESPOSTA}
	Dictionary Should Contain Item  ${RESPOSTA}  message  Cadastro realizado com sucesso
	Dictionary Should Contain Key   ${RESPOSTA}  _id

Repetir o cadastro do usuário na ServeRest
	Cadastrar o usuário criado na ServeRest		status_code_desejado=400

#Verificar se a API não permitiu o cadastro repetido



