/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Takeshi
 */
public class Gerente{
    private int id;
    private String nivel;
    private String login;
    private String senha;

    public Gerente(int id, String nivel, String login, String senha) {
        this.id = id;
        this.nivel = nivel;
        this.login = login;
        this.senha = senha;
    }   

    public Gerente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
    
    public void manterCliente() {

    }

    public void manterFuncionario() {

    }
}
