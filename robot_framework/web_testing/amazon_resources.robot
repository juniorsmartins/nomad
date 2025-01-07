*** Settings ***
Documentation                        Bibliotecas utilizadas nos testes
Library                              SeleniumLibrary

*** Variables ***
${URL}                               https://www.amazon.com.br/
${MENU_ELETRONICOS}                  //a[@href='/Eletronicos-e-Tecnologia/b/?ie=UTF8&node=16209062011&ref_=nav_cs_electronics'][contains(.,'Eletrônicos')]
${TEXT_ELETRONICOS}                  Eletrônicos e Tecnologia
${H2_ELETRONICOS}                    //h2[@class='a-size-base a-color-base apb-browse-refinements-indent-1 a-text-bold'][contains(.,'Eletrônicos e Tecnologia')]

*** Keywords ***
Abrir o navegador
    Open Browser                     browser=chrome
    Maximize Browser Window

Fechar o navegador
    Close Browser

Acessar a home page do site amazon.com.br
    Go To                            url=${URL}
    Wait Until Element Is Visible    locator=${MENU_ELETRONICOS}

Entrar no menu "Eletrônicos"
    Click Element                    locator=${MENU_ELETRONICOS}

Verificar se aparece a frase "Eletrônicos e Tecnologia"
    Wait Until Page Contains         text=${TEXT_ELETRONICOS}
    Wait Until Element Is Visible    locator=${H2_ELETRONICOS}


