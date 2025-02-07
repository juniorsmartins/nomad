# language: pt
Funcionalidade: testar operações CRUD do CashbookController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Cashbooks

  Contexto:
    Dado um ambiente de teste de Accounting ativado

    Cenario: Post para criar Cashbook com sucesso
      E uma requisição Post com CashbookCreateDtoRequest válido, com ano 1950 e documento "47361120008"
      Quando a requisição Post for feita no método create
      Entao receberei uma ResponseEntity com HTTP 201
      E com um CashbookDtoResponse no body, com id e ano 1950 e documento "47361120008"

    Cenario: Get para listar Cashbooks, sem registrations, com sucesso
      E tendo cadastros de Cashbook, sem registrations, disponíveis na massa de dados
        |   yearReference   |     document      |
        |       1988        |   94090259070     |
        |       2001        |   04623846083     |
        |       1997        |   59022664082     |
      Quando uma requisição Get válida for feita para o método findAll
      Entao receberei uma ResponseEntity com HTTP 200
      E uma página com CashbookFindDtoResponse no body


