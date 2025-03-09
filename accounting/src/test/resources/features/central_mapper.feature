# language: pt
Funcionalidade: testar operações de conversão da CentralMapper
  Dado uma operação específica de conversão
  Quando executá-la
  Então receber a classe específica com os dados corretos

  Cenário: deve converter CashbookCreateDtoRequest para Cashbook
    Dado um CashbookCreateDtoRequest válido, com ano 2025 e documento "30921744021"
    Quando converter CashbookCreateDtoRequest para Cashbook
    Então receber um Cashbook válido, com ano 2025 e documento "30921744021"

  Cenário: deve converter CashbookUpdateDtoRequest para Cashbook
    Dado um CashbookUpdateDtoRequest válido, com ano 2000 e documento "45104581004" e id "63ac9ef2-23b7-4b6b-92cb-0cf92cd5768a"
    Quando converter CashbookUpdateDtoRequest para Cashbook
    Então receber um Cashbook válido, com ano 2000 e documento "45104581004" e id "63ac9ef2-23b7-4b6b-92cb-0cf92cd5768a"

  Cenário: deve converter Cashbook para CashbookDtoResponse
    Dado um Cashbook válido, com id "47a5a756-a116-463c-8dab-0d08bda0f174" e ano 1990 e documento "86874553054"
    Dado um Cashbook com Registration válido, com id "f831f54e-4df2-4ca1-afce-944039ec55ff" e description "Corte de cabelo" e amount 20.50 e typeOperation "OUTPUT" e dateOperation "1990-01-22" e costCenter "BARBERSHOP" e supplier "Barbearia do Careca" e Registration com CashbookEntity com apenas cashbookId "7ab93b84-697b-43b9-9f66-8a796b83fd52"
    Quando converter Cashbook para CashbookDtoResponse
    Então receber um CashbookDtoResponse válido, com id "47a5a756-a116-463c-8dab-0d08bda0f174" e ano 1990 e documento "86874553054"
    Então receber um CashbookDtoResponse com Registration válido, com id "f831f54e-4df2-4ca1-afce-944039ec55ff" e description "Corte de cabelo" e amount 20.50 e typeOperation "OUTPUT" e dateOperation "1990-01-22" e costCenter "BARBERSHOP" e supplier "Barbearia do Careca" e Registration válido com CashbookEntity com apenas cashbookId "7ab93b84-697b-43b9-9f66-8a796b83fd52"

  Cenario: deve converter InvestmentCreateDtoRequest para Investment
    Dado um InvestmentCreateDtoRequest válido, com description "XPTO" e amount 75 e typeAction "INVESTMENT" e category "CDB" e supplier "XPTO"
    Quando converter InvestmentCreateDtoRequest para Investment
    Entao receber um Investment válido, com description "XPTO" e amount 75 e typeAction "INVESTMENT" e category "CDB" e supplier "XPTO"

  Cenario: deve converter Investment para InvestmentEntity
    Dado um Investment válido, com description "C3PO" e amount 80 e typeAction "DISINVESTMENT" e category "CDB" e supplier "C3PO"
    Quando converter Investment para InvestmentEntity
    Entao receber um InvestmentEntity válido, com description "C3PO" e amount 80 e typeAction "DISINVESTMENT" e category "CDB" e supplier "C3PO"

  Cenario: deve converter InvestmentEntity para Investment
    Dado um InvestmentEntity válido, com description "XPTO2" e amount 220 e typeAction "INVESTMENT" e category "DOLAR" e supplier "XPTO2"
    Quando converter InvestmentEntity para Investment
    Entao receber um Investment válido, com description "XPTO2" e amount 220 e typeAction "INVESTMENT" e category "DOLAR" e supplier "XPTO2"

    Cenario: deve converter Investment para InvestmentDtoResponse
      Dado um Investment válido, com description "D2Z2" e amount 22 e typeAction "DISINVESTMENT" e category "POUPANCA" e supplier "D2Z2"
      Quando converter Investment para InvestmentDtoResponse
      Entao receber um InvestmentDtoResponse válido, com description "D2Z2" e amount 22 e typeAction "DISINVESTMENT" e category "POUPANCA" e supplier "D2Z2"

    Cenario: deve converter InvestmentEntity para InvestmentFindDtoResponse
      Dado um InvestmentEntity válido, com description "XXX" e amount 15 e typeAction "INVESTMENT" e category "FUNDO" e supplier "X2X"
      Quando converter InvestmentEntity para InvestmentFindDtoResponse
      Entao receber um InvestmentFindDtoResponse válido, com description "XXX" e amount 15 e typeAction "INVESTMENT" e category "FUNDO" e supplier "X2X"

    Cenario: deve converter InvestmentEntity para InvestmentSearchDtoResponse
      Dado um InvestmentEntity válido, com description "X25" e amount 258 e typeAction "INVESTMENT" e category "BOLSA" e supplier "X7X8"
      Quando converter InvestmentEntity para InvestmentSearchDtoResponse
      Entao receber um InvestmentSearchDtoResponse válido, com description "X25" e amount 258 e typeAction "INVESTMENT" e category "BOLSA" e supplier "X7X8"


