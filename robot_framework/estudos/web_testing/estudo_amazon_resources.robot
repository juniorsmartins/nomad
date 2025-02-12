*** Settings ***
Documentation                          Bibliotecas utilizadas nos testes
Library                                SeleniumLibrary

*** Variables ***
${URL}                                 https://www.amazon.com.br/
${MENU_ELETRONICOS}                    //a[@href='/Eletronicos-e-Tecnologia/b/?ie=UTF8&node=16209062011&ref_=nav_cs_electronics'][contains(.,'Eletrônicos')]
${H2_ELETRONICOS}                      //h2[@class='a-size-base a-color-base apb-browse-refinements-indent-1 a-text-bold'][contains(.,'Eletrônicos e Tecnologia')]
${IMAGEM_COMPUTADORES}                 //img[contains(@alt,'Computadores e Informática')]
${BARRA_PESQUISAR}                     twotabsearchtextbox
${BUTTON_PESQUISAR}                    nav-search-submit-button

*** Keywords ***
Abrir o navegador
    Open Browser                       browser=chrome
    Maximize Browser Window

Fechar o navegador
    Capture Page Screenshot
    Close Browser

Acessar a home page do site amazon.com.br
    Go To                              url=${URL}
    Wait Until Element Is Visible      locator=${MENU_ELETRONICOS}    timeout=25

Entrar no menu "Eletrônicos"
    Click Element                      locator=${MENU_ELETRONICOS}

Verificar se aparece a frase "${FRASE}"
    Wait Until Page Contains           text=${FRASE}
    Wait Until Element Is Visible      locator=${H2_ELETRONICOS}

Verificar se o título da página fica "${TITULO}"
    Title Should Be                    title=${TITULO}

Verificar se aparece a categoria "Computadores e Informática"
    Element Should Be Visible          locator=${IMAGEM_COMPUTADORES} 

Digitar o nome do produto "${PRODUTO}" no campo de pesquisa
    Input Text                         locator=${BARRA_PESQUISAR}    text=${PRODUTO}

Clicar no botão de pesquisa
    Click Button                       locator=${BUTTON_PESQUISAR}

Verificar o resultado da pesquisa se está listando o produto "${PRODUTO}"
    Wait Until Element Is Visible      locator=(//span[contains(.,'${PRODUTO}')])[3]


# GHERKIN STEPS

Dado que estou na home page da amazon.com.br
    Acessar a home page do site amazon.com.br
    Verificar se o título da página fica "Amazon.com.br | Tudo pra você, de A a Z."

Quando acessar o menu "Eletrônicos"
    Entrar no menu "Eletrônicos"

Então o título da página deve ficar "${TITULO}"
    Verificar se o título da página fica "${TITULO}"

E o texto "Eletrônicos e Tecnologia" deve ser exibido na página
    Verificar se aparece a frase "Eletrônicos e Tecnologia"

E a categoria "Computadores e Informática" deve ser exibida na página
    Verificar se aparece a categoria "Computadores e Informática"

Quando pesquisar pelo produto "Xbox Series S"
    Digitar o nome do produto "Xbox Series S" no campo de pesquisa
    Clicar no botão de pesquisa

E um produto da linha "Xbox Series S" deve ser mostrado na página
    Verificar o resultado da pesquisa se está listando o produto "Xbox Series S"

