package utils;

import java.util.Random;

public class GenerateCPF {

    // Gera um CPF válido
    public static String gerarCPF() {
        Random random = new Random();

        // Gera os 9 primeiros dígitos do CPF
        int[] cpfArray = new int[11]; // Agora com 11 posições!
        for (int i = 0; i < 9; i++) {
            cpfArray[i] = random.nextInt(10); // Gera um dígito entre 0 e 9
        }

        // Calcula os dígitos verificadores
        cpfArray[9] = calcularDigitoVerificador(cpfArray, 10);
        cpfArray[10] = calcularDigitoVerificador(cpfArray, 11);

        // Converte o array de inteiros para uma string
        StringBuilder cpf = new StringBuilder();
        for (int digito : cpfArray) {
            cpf.append(digito);
        }

        return cpf.toString();
    }

    // Calcula um dígito verificador do CPF
    private static int calcularDigitoVerificador(int[] cpfArray, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpfArray[i] * (pesoInicial - i);
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    // Formata o CPF (adiciona pontos e traço)
    public static String formatarCPF(String cpf) {
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9);
    }
}
