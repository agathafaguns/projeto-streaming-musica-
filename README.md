# Sistema de Streaming de Musica

Sistema de streaming de musica feito em Java para a disciplina de POO.

## Funcionalidades

- Cadastro de musicas
- Criacao de playlists personalizadas e automaticas
- Usuarios Free (com limite de 30 musicas por dia e anuncios)
- Usuarios Premium (com musicas ilimitadas, downloads e playlists ilimitadas)
- Historico de musicas ouvidas
- Sistema de login e logout
- Estatisticas de uso
- Downloads de musicas (so Premium)

## Estrutura do Projeto

O projeto esta organizado nos seguintes pacotes:

- modelo: classes principais (Musica, Playlist, Usuario, etc)
- servico: interfaces e servicos (Reproduzivel, Baixavel, GeradorRecomendacoes)
- util: classes utilitarias (Validador, FormatadorTempo)
- principal: classe principal com o menu do sistema

## Conceitos de POO usados

- Encapsulamento: atributos privados com getters e setters
- Heranca: UsuarioFree e UsuarioPremium herdam de Usuario, PlaylistPersonalizada e PlaylistAutomatica herdam de Playlist
- Polimorfismo: metodo reproduzirMusica se comporta diferente em cada tipo de usuario
- Interfaces: Reproduzivel e Baixavel
- Classes Abstratas: ItemReproducao e Usuario

## Como executar

Entre na pasta src e compile:
