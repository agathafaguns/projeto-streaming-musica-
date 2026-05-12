package br.com.streaming.modelo;

public abstract class ItemReproducao {
    protected String nome;

    public ItemReproducao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract void exibirInfo();
}
