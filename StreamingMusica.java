import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervo = new ArrayList<>();
    static Usuario usuarioLogado = new Usuario("Aluno"); // Criando um usuário padrão
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música\n2. Listar todas as músicas\n3. Buscar música");
        System.out.println("4. Criar playlist\n5. Gerenciar playlists\n6. Exibir estatísticas\n0. Sair");
        System.out.print("Escolha: ");
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMusica();
            case 2 -> listarAcervo();
            case 3 -> buscarMusica();
            case 4 -> {
                System.out.print("Nome da playlist: ");
                usuarioLogado.criarPlaylist(scanner.nextLine());
            }
            case 5 -> gerenciarPlaylists();
            case 6 -> exibirEstatisticas();
            case 0 -> System.out.println("Até logo!");
        }
    }

    // Refatoração: Agora criamos um OBJETO Musica e salvamos no acervo
    public static void cadastrarMusica() {
        System.out.print("Título: "); String t = scanner.nextLine();
        System.out.print("Artista: "); String a = scanner.nextLine();
        System.out.print("Duração (seg): "); int d = Integer.parseInt(scanner.nextLine());
        System.out.print("Gênero: "); String g = scanner.nextLine();
        
        acervo.add(new Musica(t, a, d, g));
        System.out.println("✅ Cadastrada!");
    }

    public static void gerenciarPlaylists() {
        System.out.println("\n=== GERENCIAR PLAYLISTS ===");
        usuarioLogado.listarPlaylists();
        System.out.print("Selecione o índice da playlist (ou -1 para voltar): ");
        int idx = Integer.parseInt(scanner.nextLine());
        Playlist p = usuarioLogado.getPlaylist(idx);
        
        if (p != null) {
            System.out.println("1. Adicionar música\n2. Remover música\n3. Detalhes");
            int subOp = Integer.parseInt(scanner.nextLine());
            if (subOp == 1) {
                listarAcervo();
                System.out.print("Índice da música no acervo: ");
                int mIdx = Integer.parseInt(scanner.nextLine());
                p.adicionarMusica(acervo.get(mIdx));
            } else if (subOp == 3) {
                p.listarMusicas();
                System.out.println("Total: " + p.getDuracaoTotal() + "s");
            }
        }
    }

    public static void listarAcervo() {
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print(i + ". ");
            acervo.get(i).exibir();
        }
    }

    // Adiciona dados iniciais para teste
    public static void adicionarMusicasTeste() {
        acervo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervo.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }

    public static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { return -1; }
    }
}