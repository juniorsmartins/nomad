# language: pt
Funcionalidade: Criar Cashbook
  Dado um Cashbook válido
  Quando fizer Post
  Então receber HTTP 201 Created

Cenário: Deve criar Cashbook com sucesso
  Dado um Cashbook válido
  Quando fizer Post
  Então receber HTTP 201 Created



