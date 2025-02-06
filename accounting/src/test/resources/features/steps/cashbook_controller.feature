# language: pt
Funcionalidade: testar operações Crud de Api Rest
  Dado uma requisição
  Quando executá-la
  Então receber a resposta HTTP adequada

  Cenário: deve criar Cashbook com sucesso
    Dado que está ativado o ambiente de teste de Accounting
    E que enviarei um CashbookCreateDtoRequest válido, com ano 1950 e documento "47361120008"
    Quando fizer requisição Post
    Então receberei uma resposta com HTTP 201
    E receberei um ResponseEntity com um CashbookDtoResponse no body, com id e ano 1950 e documento "47361120008"



