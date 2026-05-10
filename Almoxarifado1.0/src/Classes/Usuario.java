package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author heitor
 */

public class Usuario {
    int ID_usuario;
    String nome;
    LocalDateTime data_hora_cadastro;
    int inativo;
    String login;
    String senha;
    int administrador;

    public Usuario() {
    }

    public Usuario(int ID_usuario, String nome, LocalDateTime data_hora_cadastro, int inativo, String login, String senha, int administrador) {
        this.ID_usuario = ID_usuario;
        this.nome = nome;
        this.data_hora_cadastro = data_hora_cadastro;
        this.inativo = inativo;
        this.login = login;
        this.senha = senha;
        this.administrador = administrador;
    }

    public int getID_usuario() {
        return ID_usuario;
    }

    public void setID_usuario(int ID_usuario) {
        this.ID_usuario = ID_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData_hora_cadastro() {
        return data_hora_cadastro;
    }

    public void setData_hora_cadastro(LocalDateTime data_hora_cadastro) {
        this.data_hora_cadastro = data_hora_cadastro;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }
   
}
