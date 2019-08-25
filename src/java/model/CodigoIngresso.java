/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Takeshi
 */
public class CodigoIngresso {

    public String gerarCodigo() {
        String codigo = "";
        final String alphabet = "A1B2C3D4E5F6G7H8I9";
        final int N = alphabet.length();
        Random rd = new Random();
        int iLength = 10;
        StringBuilder sb = new StringBuilder(iLength);
        for (int i = 0; i < iLength; i++) {
            codigo = sb.append(alphabet.charAt(rd.nextInt(N))).toString();
        }
        return codigo;
    }
}
