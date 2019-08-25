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
public class Pagamento {

    private double vTotal;
    private String datapagamento;
    private Ingresso ingresso;
    private int quant;

    public Pagamento() {
    }

    public Pagamento(double vTotal, String datapagamento, Ingresso ingresso, int quant) {
        this.vTotal = vTotal;
        this.datapagamento = datapagamento;
        this.ingresso = ingresso;
        this.quant = quant;
    }

    public double getvTotal() {
        return vTotal;
    }

    public void setvTotal(double vTotal) {
        this.vTotal = vTotal;
    }

    public String getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(String datapagamento) {
        this.datapagamento = datapagamento;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public void calculaTotal() {
        vTotal = vTotal + (quant * 30);
    }

    public void pagamentoVista() {
        vTotal = vTotal * 0.95;
    }

}
