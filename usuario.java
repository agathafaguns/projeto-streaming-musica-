import java.util.ArrayList;

public class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    void listarPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + ". " + playlists.get(i).nome);
        }
    }
}