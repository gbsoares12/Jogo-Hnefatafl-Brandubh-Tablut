/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.MercenarioNormal;
import model.MercenarioTablut;
import model.Peca;

/**
 *
 * @author Usuario
 */
public class ProcuraMercenario implements Visitor {

    private int quantidadeMortos;
    private int quantidadeVivos;
    private int quantidadeVivosAchados;
    private ControleJogoImpl controle;

    @Override
    public void visit(ControleJogoImpl controle) {
        this.controle = controle;
        Peca[][] tabuleiro = controle.getTabuleiro();

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {

                if (tabuleiro[i][j].getClass() == MercenarioNormal.class || tabuleiro[i][j].getClass() == MercenarioTablut.class) {
                    quantidadeVivosAchados++;
                }

            }
        }
    }

    public int getQuantidadeMortos() {
        
        switch (controle.getTabuleiroAtual()) {
            case 1:
                quantidadeVivos = 24; 
                break;
            case 2:
                quantidadeVivos = 8;
                break;
            case 3:
                quantidadeVivos = 16;
                break;
        }
        
        quantidadeMortos = quantidadeVivos - quantidadeVivosAchados;
        
        return quantidadeMortos;
    }

}
