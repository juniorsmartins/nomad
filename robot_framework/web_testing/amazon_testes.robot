*** Settings ***
Documentation  Essa suite testa o site da Amazon.com.br
Resource       amazon_resources.robot
Test Setup     Abrir o navegador
Test Teardown  Fechar o navegador

*** Test Cases ***
Caso de Teste 01 - acesso ao menu "Eletrônicos"
    [Documentation]  Verifica o menu eletrônico do site da Amazon.com.br
    ...              e verifica a categoria Computadores e Informática
    [Tags]           menus  categorias
    Acessar a home page do site amazon.com.br
#     Entrar no menu "Eletrônicos"
#     Verificar se o título da página fica "Eletrônicos e Tecnologia | Amazon.com.br"
#     Verificar se aparece a frase "Eletrônicos e Tecnologia"
#     Verificar se aparece a categoria "Computadores e Informática"

# Caso de Teste 02 - Pesquisa de um produto
#     [Documentation]  Verifica a busca de um produto no site da Amazon.com.br
#     [Tags]           busca_produtos  lista_busca
#     Acessar a home page do site amazon.com.br
#     Digitar o nome do produto "Xbox Series 5" no campo de pesquisa
#     Clicar no botão de pesquisa
#     Verificar se o resultado da pesquisa está listando o produto pesquisado






