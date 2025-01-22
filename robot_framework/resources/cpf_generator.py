import random

def calcular_dv(cpf_base):
    """Calcula os dois dígitos verificadores do CPF"""
    soma1 = 0
    soma2 = 0
    for i in range(9):
        soma1 += int(cpf_base[i]) * (10 - i)
        soma2 += int(cpf_base[i]) * (11 - i)
    dv1 = (soma1 * 10) % 11
    if dv1 == 10:
        dv1 = 0
    dv2 = (soma2 * 10) % 11
    if dv2 == 10:
        dv2 = 0
    return f"{dv1}{dv2}"

def gerar_cpf():
    """Gera um CPF válido"""
    cpf_base = ''.join([str(random.randint(0, 9)) for _ in range(9)])
    dv = calcular_dv(cpf_base)
    return f"{cpf_base[:3]}.{cpf_base[3:6]}.{cpf_base[6:9]}-{dv}"


