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
public abstract class Lutador extends Peca {
    
    private EstadoLutador estadoAtual;
    
    public Lutador(Icon imagem) {
        super(imagem);
        //this.estadoAtual = new Vivo(this); PADRÃO STATE
    }

    public EstadoLutador getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(EstadoLutador estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    //public abstract void morrer();
    
    public abstract boolean validaMover(Peca casaFinal);

}
