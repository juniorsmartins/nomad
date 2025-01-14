*** Settings ***
Documentation                                  Exemplo de escopo de variáveis: GLOBAIS, SUITE, TESTE (test case) e LOCAL (keyword)
Library              String

*** Variables ***
${GLOBAL_INSTANCIADA}                          Minha variável GLOBAL_INSTANCIADA foi instanciada para a suite

*** Test Cases ***
Caso de teste de exemplo 01
    Uma keyword qualquer 01
    Uma keyword qualquer 02

Caso de teste de exemplo 02
    Uma keyword qualquer 02
    Uma keyword qualquer 03

Caso de teste de exemplo 03
    Uma keyword qualquer 04

*** Keywords ***
Uma keyword qualquer 01
    ${GLOBAL_CRIADA_EM_TEMPO_EXECUCAO}         Generate Random String
    # A variável "global" pode ser usada em todas as suítes em execução
    Set Global Variable                        ${GLOBAL_CRIADA_EM_TEMPO_EXECUCAO}
    # A variável "suíte" pode ser usada somente nos testes da suíte em execução
    Set Suite Variable                         ${SUITE_CRIADA_EM_TEMPO_EXECUCAO}            Variável da suíte
    # A variável "test" pode ser usada somente nas keywords do teste em execução
    Set Test Variable                          ${TESTE_01}                                  Variável do teste 01
    Log                                        ${TESTE_01}
    # A variável "local" pode ser usada somente na keyword em execução
    ${LOCAL}                                   Set Variable                                 Variável local da keyword 01
    Log                                        ${LOCAL}

Uma keyword qualquer 02
    ${LOCAL}                                   Set Variable                                 Variável local da keyword 02
    Log                                        ${LOCAL}
    Log                                     ${GLOBAL_INSTANCIADA} / ${GLOBAL_CRIADA_EM_TEMPO_EXECUCAO} / ${SUITE_CRIADA_EM_TEMPO_EXECUCAO} / ${LOCAL}

Uma keyword qualquer 03
    ${LOCAL}                                   Set Variable                                 Variável local da keyword 03
    Log                                        ${LOCAL}
    Log                                     ${GLOBAL_INSTANCIADA} / ${GLOBAL_CRIADA_EM_TEMPO_EXECUCAO} / ${SUITE_CRIADA_EM_TEMPO_EXECUCAO} / ${LOCAL}

Uma keyword qualquer 04
    ${LOCAL}                                   Set Variable                                 Variável local da keyword 04
    Log                                        ${LOCAL}


