# language: pt
Funcionalidade: testar operações CRUD de InvestmentController
  Como cliente dessa API Rest, quero fazer requisições Post, Get, Put e Delete
  Dessa forma, obter os resultados padrões desejados
  Para meu sistema cadastrar, ler, atualizar e deletar Investments

  Contexto:
    Dado um ambiente de teste de Accounting ativado
    Dado cadastros de Cashbook disponíveis na massa de dados
      |   yearReference   |     document      |
      |       1870        |   69309144017     |
      |       1855        |   83305660058     |
      |       1820        |   35052427050     |

    Cenario: Post para criar Investment com sucesso pelo InvestmentController
      Dado uma requisição Post com InvestmentCreateDtoRequest válido, com amount 45 e typeAction "INVESTMENT" e category "FUNDO"
      Quando a requisição Post for feita no método create, para cashbook com ano 1855 e document "83305660058", no InvestmentController
      Entao receberei um ResponseEntity com HTTP 201 do InvestmentController
      E com um InvestmentDtoResponse no body, com id e amount 45 e typeAction "INVESTMENT" e category "FUNDO"
      E o Investment foi criado, com amount 45 e typeAction "INVESTMENT" e category "FUNDO", no banco de dados pelo InvestmentController

    Cenario: Get para consultar Investment com sucesso
      Dado um UUID de Investiment, com amount 80 e typeAction "INVESTMENT" e category "DOLAR", de um Cashbook, com ano 1820 e documento "35052427050"
      Quando a requisição Get for feita no método findById do InvestmentController
      Entao receberei um ResponseEntity com HTTP 200 do InvestmentController
      E com um InvestmentFindDtoResponse no body, com amount 80 e typeAction "INVESTMENT" e category "DOLAR"

