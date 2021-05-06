/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacaousuarios;

public class Usuario {
    private String login;
    private String email;
    private String nome;
    private String senha;
    private String pontos;

    public Usuario() {
    }

    public Usuario(String login, String email, String nome, String senha, String pontos) {
        this.login = login;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.pontos = pontos;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", pontos=" + pontos + '}';
    }
        
}
