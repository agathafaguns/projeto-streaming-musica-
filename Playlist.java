package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;
import java.util.ArrayList;

public class Playlist extends ItemReproducao implements Reproduzivel {
    protected ArrayList<Musica> musicas;
    protected int duracaoTotal;

    public Playlist(String nome) {
        super(nome);
        this.musicas = new ArrayList<>();
        this.duracaoTotal = 0;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("  Tocando: " + m.getTitulo());
        }
    }

    @Override
    public void pausar() {
        System.out.println("Playlist pausada: " + nome);
    }

    @Override
    public void parar() {
        System.out.println("Playlist parada: " + nome);
    }

    @Override
    public int getDuracaoTotal() {
        duracaoTotal = 0;
        for (Musica m : musicas) {
            duracaoTotal = duracaoTotal + m.getDuracaoSegundos();
        }
        return duracaoTotal;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
        System.out.println("Musica " + musica.getTitulo() + " adicionada na playlist " + nome);
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            Musica removida = musicas.remove(indice);
            System.out.println("Musica " + removida.getTitulo() + " removida");
        } else {
            System.out.println("Indice invalido!");
        }
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).exibir();
            }
        }
    }

    @Override
    public void exibirInfo() {
        int total = getDuracaoTotal();
        int minutos = total / 60;
        int segundos = total % 60;
        System.out.println("Playlist: " + nome + " | Musicas: " + musicas.size() + " | Duracao: " + minutos + "min " + segundos + "s");
    }
}
