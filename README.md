# FilmeApi

API e aplicação web para cadastro, listagem e gerenciamento de filmes, com integração à API do TMDB para busca automática de capas.

## Funcionalidades

- Cadastro e remoção de filmes
- Listagem de filmes cadastrados
- Busca automática de capa pelo título via TMDB
- Internacionalização (i18n) em português e inglês
- Interface web simples

## Tecnologias

- Java 17+
- Spring Boot
- Spring MVC
- Migration
- Gradle
- Banco de dados relacional (PostgreSQL)
- Integração com [The Movie Database (TMDB)](https://www.themoviedb.org/)

## Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/JuanPabloRuiz583/filmeApi_projetoDiamante.git

2. **Configure as seguintes variaveis de ambiente para funcionar a autenticacao no google e github:**
   ```bash
   GITHUB_CLIENT_ID = Ov23liV6tV6se3vfr0TE 
   GITHUB_CLIENT_SECRET = 8fda519bb92c956be0319519a94f11eeabcb613e
   GOOGLE_CLIENT_ID = 412634895320-9l0d1sscgkoao1ffhbl270ughvpifpp2.apps.googleusercontent.com
   GOOGLE_CLIENT_SECRET = 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


3. **Abra o docker desktop antes de executar o projeto**
   
4. **rode o projeto**

5. **Acesse no navegador:**
Apos rodar a aplicação, acesse: http://localhost:8080/filmes
