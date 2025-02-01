from random import randint

class CPFGenerator:
    def generate_cpf(self):
        def calculate_digit(digits):
            s = sum((len(digits)+1 - i) * int(d) for i, d in enumerate(digits))
            return str((s * 10 % 11) % 10)

        cpf = [str(randint(0, 9)) for _ in range(9)]
        cpf.append(calculate_digit(cpf))
        cpf.append(calculate_digit(cpf))
        return ''.join(cpf)



