package br.com.streaming.util;

public class Validador {

    public static boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    public static boolean validarNome(String nome) {
        if (nome == null) {
            return false;
        }
        return nome.length() >= 3;
    }

    public static boolean validarIndice(int indice, int tamanhoLista) {
        return indice >= 0 && indice < tamanhoLista;
    }
}
