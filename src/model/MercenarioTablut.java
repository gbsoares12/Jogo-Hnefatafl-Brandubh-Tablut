/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.Icon;

/**
 *
 * @author 42519630833
 */
public class MercenarioTablut extends Mercenario {
    
    public MercenarioTablut(Icon imagem) {
        super(imagem);
    }

    @Override
    public boolean validaMover(Peca casaFinal) {
        return casaFinal.getClass() == Campo.class || casaFinal.getClass() == RefugioNormal.class;
    }

//    @Override
//    public void morrer() {
//        this.getEstadoAtual().proxEstado();
//    }
//    
}
