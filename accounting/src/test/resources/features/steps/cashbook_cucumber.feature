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

Cenário: Deve converter Cashbook para CashbookDtoResponse
  Dado um Cashbook válido, com id "47a5a756-a116-463c-8dab-0d08bda0f174" e ano 1990 e documento "86874553054"
  Dado um Cashbook com Registration válido, com id "f831f54e-4df2-4ca1-afce-944039ec55ff" e description "Corte de cabelo" e amount 20.50 e typeOperation "OUTPUT" e dateOperation "1990-01-22" e costCenter "BARBERSHOP" e supplier "Barbearia do Careca" e Registration com CashbookEntity com apenas cashbookId "7ab93b84-697b-43b9-9f66-8a796b83fd52"
  Quando converter Cashbook para CashbookDtoResponse
  Então receber um CashbookDtoResponse válido, com id "47a5a756-a116-463c-8dab-0d08bda0f174" e ano 1990 e documento "86874553054"
  Então receber um CashbookDtoResponse com Registration válido, com id "f831f54e-4df2-4ca1-afce-944039ec55ff" e description "Corte de cabelo" e amount 20.50 e typeOperation "OUTPUT" e dateOperation "1990-01-22" e costCenter "BARBERSHOP" e supplier "Barbearia do Careca" e Registration válido com CashbookEntity com apenas cashbookId "7ab93b84-697b-43b9-9f66-8a796b83fd52"




