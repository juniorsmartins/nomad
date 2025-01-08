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
    Wait Until Element Is Visible      locator=${MENU_ELETRONICOS}

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

