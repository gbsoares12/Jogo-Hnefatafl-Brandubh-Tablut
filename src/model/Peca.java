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
public abstract class Peca {
    private boolean isSelecionada = false;

    public boolean isIsSelecionada() {
        return isSelecionada;
    }

    public void setIsSelecionada(boolean isSelecionada) {
        this.isSelecionada = isSelecionada;
    }
    
    private Icon imagem;

    public Peca(Icon imagem) {
        this.imagem = imagem;
    }

    public Icon getImagem() {
        return imagem;
    }

    public void setImagem(Icon imagem) {
        this.imagem = imagem;
    }
    
}
