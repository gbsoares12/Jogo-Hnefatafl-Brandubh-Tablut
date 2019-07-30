/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Morto extends EstadoLutador {

    public Morto(Lutador lutador) {
        super(lutador);
        this.lutador.setImagem(new ImageIcon("imagens/grama.png"));
    }

    @Override
    public void proxEstado() {
        lutador.setEstadoAtual(new Vivo(lutador));
    }
    
}
