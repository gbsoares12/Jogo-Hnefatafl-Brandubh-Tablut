/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.DefensorNormal;
import model.Peca;

/**
 *
 * @author Usuario
 */
public class ProcuraDefensor implements Visitor {
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

                if (tabuleiro[i][j].getClass() == DefensorNormal.class) {
                    quantidadeVivosAchados++;
                }

            }
        }
    }

    public int getQuantidadeMortos() {
        
        switch (controle.getTabuleiroAtual()) {
            case 1:
                quantidadeVivos = 13; 
                break;
            case 2:
                quantidadeVivos = 5;
                break;
            case 3:
                quantidadeVivos = 9;
                break;
        }
        
        quantidadeMortos = quantidadeVivos - quantidadeVivosAchados;
        
        return quantidadeMortos;
    }
    
}
