# language: pt
Funcionalidade: testar operações CRUD do RegistrationController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Registrations

  Contexto:
    Dado um ambiente de teste de Accounting ativado

    Cenario: Post para criar Registration com sucesso
      Dado cadastros de Cashbook, sem registrations, disponíveis na massa de dados
        |   yearReference   |     document      |
        |       1990        |   94090259070     |
        |       2021        |   04623846083     |
        |       1994        |   59022664082     |
      E uma requisição Post com RegistrationCreateDtoRequest válido, com amount 25 e typeOperation "OUTPUT"
      Quando a requisição Post for feita no método create, para cashbook com ano 2021 e document "04623846083"
      Então receberei do RegistrationController uma ResponseEntity com HTTP 201
      E com um RegistrationDtoResponse no body, com amount 25 e typeOperation "OUTPUT"



