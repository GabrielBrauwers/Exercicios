/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

public class Configuracao implements java.io.Serializable {

    private String server = "";
    private String porta = "";
    private String banco = "";
    private String user = "";
    private String password = "";
    private String ldapserver = "";
    private String ldapporta = "";
    private String ldapuser = "";
    private String ldappassword = "";
    private String ListaOUAlunos = "";
    private String ListaOUProfes = "";

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLdapserver() {
        return ldapserver;
    }

    public void setLdapserver(String ldapserver) {
        this.ldapserver = ldapserver;
    }

    public String getLdapporta() {
        return ldapporta;
    }

    public void setLdapporta(String ldapporta) {
        this.ldapporta = ldapporta;
    }

    public String getLdapuser() {
        return ldapuser;
    }

    public void setLdapuser(String ldapuser) {
        this.ldapuser = ldapuser;
    }

    public String getLdappassword() {
        return ldappassword;
    }

    public void setLdappassword(String ldappassword) {
        this.ldappassword = ldappassword;
    }

    public String getListaOUAlunos() {
        return ListaOUAlunos;
    }

    public void setListaOUAlunos(String ListaOUAlunos) {
        this.ListaOUAlunos = ListaOUAlunos;
    }

    public String getListaOUProfes() {
        return ListaOUProfes;
    }

    public void setListaOUProfes(String ListaOUProfes) {
        this.ListaOUProfes = ListaOUProfes;
    }

    public Configuracao() {
    }

    
    
}
