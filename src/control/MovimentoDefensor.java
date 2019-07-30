/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Lutador;
import model.Peca;
import model.Time;

/**
 *
 * @author 42519630833
 */

public class MovimentoDefensor extends MovimentoLutador {

    public MovimentoDefensor(int linha, int coluna, Lutador peca, Time timeDaPeca) {
        super(linha, coluna, peca, timeDaPeca);
    }

  
    @Override
    protected boolean moverPeca(Peca casaAtual) {
        Peca casaFinal;
        try {
            casaFinal = super.time.validaCasa(casaAtual);
            return super.peca.validaMover(casaFinal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
