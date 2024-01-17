package com.igma.customerbase.service;

public class ValidacaoCPF {

    public static boolean isValido(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove caracteres não numéricos

        if (cpf.length() != 11 || temDigitosRepetidos(cpf)) {
            return false;
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeros[i] * (10 - i);
        }

        int resultado = soma % 11;
        if (resultado < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resultado;
        }

        if (resultado != numeros[9]) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeros[i] * (11 - i);
        }

        resultado = soma % 11;
        if (resultado < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resultado;
        }

        return resultado == numeros[10];
    }

    private static boolean temDigitosRepetidos(String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }
}