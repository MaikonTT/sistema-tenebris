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
public class Cupom {
    private int id;
    private String descri;
    private String codigo;
    private double desconto;
    private String estado;

    public Cupom(int id, String descri, String codigo, double desconto, String estado) {
        this.id = id;
        this.descri = descri;
        this.codigo = codigo;
        this.desconto = desconto;
        this.estado = estado;
    }

    public Cupom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void manterCupom(){
    
    }    
}
