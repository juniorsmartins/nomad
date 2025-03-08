# language: pt
Funcionalidade: testar operações CRUD do RegistrationController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Registrations

  Contexto:
    Dado um ambiente de teste de Accounting ativado
    Dado cadastros de Cashbook, sem registrations, disponíveis na massa de dados
      |   yearReference   |     document      |
      |       1990        |   94090259070     |
      |       2021        |   04623846083     |
      |       1994        |   59022664082     |

    Cenario: Post para criar Registration com sucesso
      Dado uma requisição Post com RegistrationCreateDtoRequest válido, com amount 25 e typeOperation "OUTPUT"
      Quando a requisição Post for feita no método create, para cashbook com ano 2021 e document "04623846083"
      Entao receberei do RegistrationController uma ResponseEntity com HTTP 201
      E com um RegistrationDtoResponse no body, com amount 25 e typeOperation "OUTPUT"

    Cenario: Get para consultar Registration com sucesso
      Dado um UUID de Registration, com amount 30 e typeOperation "INPUT", referente a um Cashbook, com ano 1994 e documento "59022664082"
      Quando a requisição Get for feita no método findById
      Entao receberei do RegistrationController uma ResponseEntity com HTTP 200
      E com um RegistrationFindDtoResponse no body, com amount 30 e typeOperation "INPUT"

    Cenario: Delete para apagar Registration com sucesso
      Dado um UUID de Registration, com amount 30 e typeOperation "INPUT", referente a um Cashbook, com ano 1990 e documento "94090259070"
      Quando a requisição Delete for feita no método delete
      Entao receberei do RegistrationController uma ResponseEntity com HTTP 204
      E o Registration terá sido apagado do banco de dados



