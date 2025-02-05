# language: pt
Funcionalidade: Aprender Cucumber
  Como um aluno
  Eu quero aprender a utilizar Cucumber
  Para que eu possa automatizar critérios de aceitação

Esquema do Cenário: Deve incrementar contador
  Dado que o valor do contador é <valor_inicial>
  Quando eu incrementar em <incremento>
  Então o valor do contador será <valor_final>

Exemplos:
  | valor_inicial | incremento | valor_final |
  |     15        |     3      |      18     |
  |     123       |     35     |      158    |

Cenário: Deve calcular atraso na entrega
  Dado que a entrega será dia 24/12/25
  Quando a entrega atrasar em 2 dias
  Então a entrega será efetuada em 26/12/25

