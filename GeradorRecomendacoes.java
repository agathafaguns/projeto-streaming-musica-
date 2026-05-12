package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import java.util.ArrayList;
import java.util.Random;

public class GeradorRecomendacoes {
    private Random random;

    public GeradorRecomendacoes() {
        this.random = new Random();
    }

    public ArrayList<Musica> gerarRecomendacoes(ArrayList<Musica> acervo, int quantidade) {
        ArrayList<Musica> recomendacoes = new ArrayList<>();
        ArrayList<Musica> copiaAcervo = new ArrayList<>(acervo);

        int limite = quantidade;
        if (copiaAcervo.size() < quantidade) {
            limite = copiaAcervo.size();
        }

        for (int i = 0; i < limite; i++) {
            int indice = random.nextInt(copiaAcervo.size());
            recomendacoes.add(copiaAcervo.remove(indice));
        }

        return recomendacoes;
    }
}
