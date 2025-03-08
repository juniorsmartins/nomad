# language: pt
Funcionalidade: testar operações CRUD do CashbookController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Cashbooks

  Contexto:
    Dado um ambiente de teste de Accounting ativado

    Cenario: Post para criar Cashbook com sucesso
      Dado uma requisicao Post com CashbookCreateDtoRequest valido, com ano 1950 e documento "47361120008"
      Quando a requisicao Post for feita no metodo create
      Entao receberei uma ResponseEntity com HTTP 201
      E com um CashbookDtoResponse no body, com id e ano 1950 e documento "47361120008"

    Cenario: Get para consultar Cashbook com sucesso
      Dado cadastros de Cashbook, sem registrations, disponíveis na massa de dados
        |   yearReference   |     document      |
        |       1988        |   94090259070     |
        |       2001        |   04623846083     |
        |       1997        |   59022664082     |
      Dado um UUID referente ao ano 2001 e o documento "04623846083"
      Quando uma requisição Get válida for feita para o método findById
      Entao receberei uma ResponseEntity com HTTP 200
      E com um CashbookDtoResponse no body, com id e ano 2001 e documento "04623846083"


