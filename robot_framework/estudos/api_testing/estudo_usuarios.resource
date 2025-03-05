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
	${resposta}						POST On Session
	...									alias=ServeRest
	...									url=/usuarios
	...									json=${body}
	...									expected_status=${status_code_desejado}
	Log								${resposta.json()}
	IF  ${resposta.status_code} == 201
		Set Test Variable			${ID_USUARIO}  ${resposta.json()["_id"]}
	END
	Set Test Variable               ${RESPOSTA}  ${resposta.json()}

Conferir se o usuário foi cadastrado corretamente
    Log                             ${RESPOSTA}
	Dictionary Should Contain Item  ${RESPOSTA}  message  Cadastro realizado com sucesso
	Dictionary Should Contain Key   ${RESPOSTA}  _id

Repetir o cadastro do usuário na ServeRest
	Cadastrar o usuário criado na ServeRest		status_code_desejado=400

Verificar se a aplicação não permitiu o cadastro repetido
	Dictionary Should Contain Item	${RESPOSTA}  message  Este email já está sendo usado

Consultar cadastro do novo usuário
	[Arguments]						${status_code_desejado}
	${resposta_consulta}			GET On Session
	...									alias=ServeRest
	...									url=/usuarios/${ID_USUARIO}
	...									expected_status=${status_code_desejado}
	Log									${resposta_consulta}
	Set Test Variable					${RESP_CONSULTA}  ${resposta_consulta}
	Set Test Variable					${RESP_CONSULTA_JSON}  ${resposta_consulta.json()}

Conferir os dados retornados
	Log								${RESP_CONSULTA.status_code}
	Log								${RESP_CONSULTA.reason}
	Log								${RESP_CONSULTA.content}
	Log								${RESP_CONSULTA.headers}
	Log								${RESP_CONSULTA.elapsed}
	Log								${RESP_CONSULTA_JSON}
	Dictionary Should Contain Item	${RESP_CONSULTA_JSON}	nome			Fulano da Silva
	Dictionary Should Contain Item	${RESP_CONSULTA_JSON}	email			${EMAIL_TESTE}
	Dictionary Should Contain Item	${RESP_CONSULTA_JSON}	password		1234
	Dictionary Should Contain Item	${RESP_CONSULTA_JSON}	administrador	true
	Dictionary Should Contain Item	${RESP_CONSULTA_JSON}	_id				${RESPOSTA["_id"]}



