# language: pt
Funcionalidade: Criar Cashbook
  Dado um Cashbook válido
  Quando fizer Post
  Então receber HTTP 201 Created

Cenário: Deve criar Cashbook com sucesso
  Dado um CashbookCreateDtoRequest válido, com ano 1950 e documento "47361120008"
  Quando fizer Post
  Então receber HTTP 201

Cenário: Deve converter CashbookCreateDtoRequest para Cashbook
  Dado um CashbookCreateDtoRequest válido, com ano 2025 e documento "30921744021"
  Quando converter CashbookCreateDtoRequest para Cashbook
  Então receber um Cashbook válido, com ano 2025 e documento "30921744021"

Cenário: Deve converter CashbookUpdateDtoRequest para Cashbook
  Dado um CashbookUpdateDtoRequest válido, com ano 2000 e documento "45104581004" e id "63ac9ef2-23b7-4b6b-92cb-0cf92cd5768a"
  Quando converter CashbookUpdateDtoRequest para Cashbook
  Então receber um Cashbook válido, com ano 2000 e documento "45104581004" e id "63ac9ef2-23b7-4b6b-92cb-0cf92cd5768a"