# language: pt
Funcionalidade: testar operações CRUD do CashbookController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Cashbooks

  Contexto:
    Dado um ambiente de teste de Accounting ativado
    Dado cadastros de Cashbook disponíveis na massa de dados
      |   yearReference   |     document      |
      |       1988        |   94090259070     |
      |       2001        |   04623846083     |
      |       1997        |   59022664082     |

    Cenario: Post para criar Cashbook com sucesso pelo CashbookController
      Dado uma requisicao Post com CashbookCreateDtoRequest valido, com ano 1950 e documento "47361120008"
      Quando a requisicao Post for feita no metodo create do CashbookController
      Entao receberei um ResponseEntity com HTTP 201 do CashbookController
      E com um CashbookDtoResponse no body, com id e ano 1950 e documento "47361120008"

    Cenario: Get para consultar Cashbook com sucesso pelo CashbookController
      Dado um UUID referente ao ano 2001 e o documento "04623846083"
      Quando uma requisição Get for feita no método findById do CashbookController
      Entao receberei um ResponseEntity com HTTP 200 do CashbookController
      E com um CashbookDtoResponse no body, com id e ano 2001 e documento "04623846083"

    Cenario: Delete para apagar Cashbook com sucesso pelo CashbookController
      Dado um UUID de Cashbook, com ano 2001 e document "04623846083"
      Quando a requisição Delete for feita no método delete do CashbookController
      Entao receberei um ResponseEntity com HTTP 204 do CashbookController
      E o Cashbook foi apagado do banco de dados pelo CashbookController

    Cenario: Update para atualizar Cashbook com sucesso pelo CashbookController
      Dado um UUID de Cashbook, com ano 1997 e document "59022664082"
      E um body com CashbookUpdateDtoRequest válido, com ano 1800 e documento "74157567030"
      Quando a requisição Put for feita no método update do CashbookController
      Entao receberei um ResponseEntity com HTTP 200 do CashbookController
      E com um CashbookUpdateDtoResponse no body, com id e ano 1800 e documento "74157567030"
      E o Cashbook foi atualizado, com ano 1800 e documento "74157567030", no banco de dados pelo CashbookController

