# language: pt
Funcionalidade: Criar Cashbook
  Dado um Cashbook válido
  Quando fizer Post
  Então receber HTTP 201 Created

Cenário:
  Dado um Cashbook válido
  Quando fizer Post
  Então receber HTTP 201 Created

Cenário:
  Dado que o valor do contador é 15
  Quando eu incrementar em 3
  Então o valor do contador será 18


