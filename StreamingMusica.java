package br.com.streaming.principal;

import br.com.streaming.modelo.*;
import br.com.streaming.servico.GeradorRecomendacoes;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervo = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;
    static Scanner teclado = new Scanner(System.in);
    static GeradorRecomendacoes gerador = new GeradorRecomendacoes();

    public static void main(String[] args) {
        acervo.add(new Musica("Blue Jeans", "Lana Del Rey", 209, "Indie Pop"));
        acervo.add(new Musica("West Coast", "Lana Del Rey", 257, "Psychedelic Rock"));
        acervo.add(new Musica("Dark Paradise", "Lana Del Rey", 244, "Baroque Pop"));
        acervo.add(new Musica("Ultraviolence", "Lana Del Rey", 281, "Dream Pop"));
        acervo.add(new Musica("Doin' Time", "Lana Del Rey", 202, "Trip Hop"));

        System.out.println("=== BEM VINDO AO STREAMING ===");

        int opcao;
        do {
            if (usuarioLogado == null) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Criar novo usuario");
                System.out.println("2. Fazer login");
                System.out.println("3. Listar usuarios");
                System.out.println("4. Ver estatisticas");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                opcao = Integer.parseInt(teclado.nextLine());

                if (opcao == 1) {
                    criarUsuario();
                } else if (opcao == 2) {
                    fazerLogin();
                } else if (opcao == 3) {
                    listarUsuarios();
                } else if (opcao == 4) {
                    exibirEstatisticas();
                }
            } else {
                System.out.println("\n=== MENU ===");
                System.out.println("Usuario: " + usuarioLogado.getNome());
                System.out.println("Tipo: " + usuarioLogado.getTipoUsuario());

                System.out.println("\n1. Ouvir musica");
                System.out.println("2. Ver historico");
                System.out.println("3. Criar playlist");
                System.out.println("4. Ver playlists");
                System.out.println("5. Listar musicas");
                System.out.println("6. Criar playlist automatica");

                if (usuarioLogado instanceof UsuarioPremium) {
                    System.out.println("7. Baixar musica");
                    System.out.println("8. Ver baixadas");
                } else {
                    System.out.println("7. Virar Premium");
                    System.out.println("8. Nao disponivel");
                }

                System.out.println("9. Logout");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                opcao = Integer.parseInt(teclado.nextLine());

                if (opcao == 1) {
                    reproduzirMusica();
                } else if (opcao == 2) {
                    usuarioLogado.verHistorico();
                } else if (opcao == 3) {
                    criarPlaylist();
                } else if (opcao == 4) {
                    usuarioLogado.listarPlaylists();
                } else if (opcao == 5) {
                    listarMusicas();
                } else if (opcao == 6) {
                    criarPlaylistAutomatica();
                } else if (opcao == 7) {
                    if (usuarioLogado instanceof UsuarioPremium) {
                        baixarMusica();
                    } else {
                        fazerUpgrade();
                    }
                } else if (opcao == 8) {
                    if (usuarioLogado instanceof UsuarioPremium) {
                        ((UsuarioPremium) usuarioLogado).verBaixadas();
                    }
                } else if (opcao == 9) {
                    fazerLogout();
                }
            }
        } while (opcao != 0);

        System.out.println("Ate logo!");
        teclado.close();
    }

    static void criarUsuario() {
        System.out.println("\n--- CRIAR USUARIO ---");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.print("Email: ");
        String email = teclado.nextLine();

        System.out.println("\n1. Conta Free");
        System.out.println("2. Conta Premium");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(teclado.nextLine());

        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
            System.out.println("Conta Free criada!");
        } else {
            System.out.println("Plano: 1-Mensal 2-Anual 3-Familiar");
            int op = Integer.parseInt(teclado.nextLine());
            String plano;
            if (op == 1) {
                plano = "Mensal";
            } else if (op == 2) {
                plano = "Anual";
            } else {
                plano = "Familiar";
            }

            usuarios.add(new UsuarioPremium(nome, email, plano));
            System.out.println("Conta Premium criada!");
        }
    }

    static void fazerLogin() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado!");
            return;
        }

        System.out.println("\n--- LOGIN ---");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + " (" + usuarios.get(i).getTipoUsuario() + ")");
        }

        System.out.print("Escolha: ");
        int escolha = Integer.parseInt(teclado.nextLine());

        if (escolha > 0 && escolha <= usuarios.size()) {
            usuarioLogado = usuarios.get(escolha - 1);
            System.out.println("Login: " + usuarioLogado.getNome());
        }
    }

    static void listarUsuarios() {
        System.out.println("\n--- USUARIOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println((i + 1) + ". " + u.getNome() + " - " + u.getTipoUsuario());
        }
    }

    static void exibirEstatisticas() {
        int totalFree = 0;
        int totalPremium = 0;
        int reproFree = 0;
        int reproPremium = 0;
        int anuncios = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioPremium) {
                totalPremium++;
                reproPremium = reproPremium + u.getTotalReproducoes();
            } else if (u instanceof UsuarioFree) {
                totalFree++;
                reproFree = reproFree + u.getTotalReproducoes();
                anuncios = anuncios + ((UsuarioFree) u).getAnunciosExibidos();
            }
        }

        System.out.println("\n=== ESTATISTICAS ===");
        System.out.println("Total usuarios: " + (totalFree + totalPremium));
        System.out.println("Free: " + totalFree);
        System.out.println("Premium: " + totalPremium);
        System.out.println("Total reproducoes: " + (reproFree + reproPremium));
        System.out.println("Anuncios exibidos: " + anuncios);
    }

    static void reproduzirMusica() {
        System.out.println("\n--- ESCOLHA UMA MUSICA ---");
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print(i + ". ");
            acervo.get(i).exibir();
        }
        System.out.print("Numero: ");
        int num = Integer.parseInt(teclado.nextLine());
        if (num >= 0 && num < acervo.size()) {
            usuarioLogado.reproduzirMusica(acervo.get(num));
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = teclado.nextLine();
        usuarioLogado.criarPlaylist(nome);
    }

    static void listarMusicas() {
        System.out.println("\n--- MUSICAS DISPONIVEIS ---");
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print(i + ". ");
            acervo.get(i).exibir();
        }
    }

    static void criarPlaylistAutomatica() {
        System.out.println("\n=== PLAYLISTS AUTOMATICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas");
        System.out.println("3. Mais Recentes");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(teclado.nextLine());

        PlaylistAutomatica playlist = null;
        if (tipo == 1) {
            playlist = new PlaylistAutomatica("Top 10", "top");
        } else if (tipo == 2) {
            playlist = new PlaylistAutomatica("Recomendadas", "recomendadas");
        } else if (tipo == 3) {
            playlist = new PlaylistAutomatica("Recentes", "recentes");
        }

        if (playlist != null) {
            playlist.atualizar(acervo);
            usuarioLogado.getPlaylists().add(playlist);
            System.out.println("Playlist criada e adicionada!");
        }
    }

    static void baixarMusica() {
        System.out.println("\n--- ESCOLHA UMA MUSICA ---");
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print(i + ". ");
            acervo.get(i).exibir();
        }
        System.out.print("Numero: ");
        int num = Integer.parseInt(teclado.nextLine());
        if (num >= 0 && num < acervo.size()) {
            ((UsuarioPremium) usuarioLogado).baixar(acervo.get(num));
        }
    }

    static void fazerUpgrade() {
        System.out.println("\n--- VIRAR PREMIUM ---");
        System.out.println("Beneficios:");
        System.out.println("- Musicas ilimitadas");
        System.out.println("- Sem anuncios");
        System.out.println("- Downloads");
        System.out.println("- Playlists ilimitadas");

        System.out.println("\nPlanos:");
        System.out.println("1. Mensal");
        System.out.println("2. Anual");
        System.out.println("3. Familiar");
        System.out.print("Escolha: ");
        int planoOp = Integer.parseInt(teclado.nextLine());

        String plano;
        if (planoOp == 1) {
            plano = "Mensal";
        } else if (planoOp == 2) {
            plano = "Anual";
        } else {
            plano = "Familiar";
        }

        UsuarioPremium novo = new UsuarioPremium(usuarioLogado.getNome(), usuarioLogado.getEmail(), plano);
        novo.getPlaylists().addAll(usuarioLogado.getPlaylists());

        int index = usuarios.indexOf(usuarioLogado);
        usuarios.set(index, novo);
        usuarioLogado = novo;
        System.out.println("Agora voce e Premium!");
    }

    static void fazerLogout() {
        System.out.println("Logout: " + usuarioLogado.getNome());
        usuarioLogado = null;
    }
}
