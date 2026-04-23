import java.util.ArrayList;
import java.util.Scanner;

public class AppStreaming {
    static ArrayList<Musica> acervo = new ArrayList<>();
    static Usuario usuario;
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Adiciona algumas músicas para teste
        acervo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervo.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        acervo.add(new Musica("Imagine", "John Lennon", 183, "Pop"));
        acervo.add(new Musica("Sweet Child O' Mine", "Guns N' Roses", 356, "Rock"));

        System.out.println("=== BEM-VINDO AO STREAMING ===");
        
        // Criar conta
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Email: ");
        String email = teclado.nextLine();
        
        System.out.println("\n1. Conta Free");
        System.out.println("2. Conta Premium");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(teclado.nextLine());
        
        if (tipo == 1) {
            usuario = new UsuarioFree(nome, email);
            System.out.println("✅ Conta Free criada!");
        } else {
            System.out.println("\nPlanos:");
            System.out.println("1. Mensal");
            System.out.println("2. Anual");
            System.out.println("3. Familiar");
            System.out.print("Escolha: ");
            int op = Integer.parseInt(teclado.nextLine());
            String plano;
            if (op == 1) plano = "Mensal";
            else if (op == 2) plano = "Anual";
            else plano = "Familiar";
            
            usuario = new UsuarioPremium(nome, email, plano);
            System.out.println("✅ Conta Premium criada!");
        }

        // Menu principal
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("Usuário: " + usuario.nome);
            System.out.println("1. Ouvir música");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist");
            System.out.println("4. Ver playlists");
            System.out.println("5. Listar músicas");
            
            if (usuario instanceof UsuarioPremium) {
                System.out.println("6. Baixar música");
                System.out.println("7. Ver baixadas");
            } else {
                System.out.println("6. Virar Premium");
            }
            
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(teclado.nextLine());
            
            if (opcao == 1) {
                // Ouvir música
                System.out.println("\n--- ESCOLHA UMA MÚSICA ---");
                for (int i = 0; i < acervo.size(); i++) {
                    System.out.print(i + ". ");
                    acervo.get(i).exibir();
                }
                System.out.print("Número: ");
                int num = Integer.parseInt(teclado.nextLine());
                if (num >= 0 && num < acervo.size()) {
                    usuario.reproduzirMusica(acervo.get(num));
                }
            }
            else if (opcao == 2) {
                usuario.verHistorico();
            }
            else if (opcao == 3) {
                System.out.print("Nome da playlist: ");
                String nomePlay = teclado.nextLine();
                usuario.criarPlaylist(nomePlay);
            }
            else if (opcao == 4) {
                usuario.listarPlaylists();
            }
            else if (opcao == 5) {
                System.out.println("\n--- MÚSICAS DISPONÍVEIS ---");
                for (int i = 0; i < acervo.size(); i++) {
                    System.out.print(i + ". ");
                    acervo.get(i).exibir();
                }
            }
            else if (opcao == 6) {
                if (usuario instanceof UsuarioPremium) {
                    // Baixar música
                    System.out.println("\n--- ESCOLHA UMA MÚSICA ---");
                    for (int i = 0; i < acervo.size(); i++) {
                        System.out.print(i + ". ");
                        acervo.get(i).exibir();
                    }
                    System.out.print("Número: ");
                    int num = Integer.parseInt(teclado.nextLine());
                    if (num >= 0 && num < acervo.size()) {
                        ((UsuarioPremium) usuario).baixarMusica(acervo.get(num));
                    }
                } else {
                    // Upgrade para Premium
                    System.out.println("\n--- VIRAR PREMIUM ---");
                    System.out.println("Benefícios:");
                    System.out.println("- Músicas ilimitadas");
                    System.out.println("- Sem anúncios");
                    System.out.println("- Downloads");
                    System.out.println("- Playlists ilimitadas");
                    
                    System.out.println("\n1. Plano Mensal");
                    System.out.println("2. Plano Anual");
                    System.out.println("3. Plano Familiar");
                    System.out.print("Escolha: ");
                    int planoOp = Integer.parseInt(teclado.nextLine());
                    
                    String plano;
                    if (planoOp == 1) plano = "Mensal";
                    else if (planoOp == 2) plano = "Anual";
                    else plano = "Familiar";
                    
                    // Cria novo usuário Premium
                    UsuarioPremium novo = new UsuarioPremium(usuario.nome, usuario.email, plano);
                    
                    // Copia playlists e histórico
                    novo.playlists = usuario.playlists;
                    novo.historico = usuario.historico;
                    
                    usuario = novo;
                    System.out.println("✅ Agora você é Premium!");
                }
            }
            else if (opcao == 7 && usuario instanceof UsuarioPremium) {
                ((UsuarioPremium) usuario).verBaixadas();
            }
            
        } while (opcao != 0);
        
        System.out.println("Até logo!");
        teclado.close();
    }
}
