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
public abstract class Mercenario extends Lutador{

    public Mercenario(Icon imagem) {
        super(imagem);
    }
    public abstract boolean validaMover(Peca casaFinal);
}
