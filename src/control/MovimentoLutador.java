/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Campo;
import model.DefensorNormal;
import model.Lutador;
import model.Peca;
import model.Time;

/**
 *
 * @author 42519630833
 */
public abstract class MovimentoLutador {

    protected Lutador peca;
    protected Time time;
    private int coluna;
    private int linha;

    public MovimentoLutador(int linha, int coluna,Lutador peca, Time timeDaPeca) {
        this.peca = peca;
        this.linha = linha;
        this.coluna = coluna;
        this.time = timeDaPeca;
    }

    public Lutador getPeca() {
        return peca;
    }

    protected abstract boolean moverPeca(Peca casaAtual);

    public void zerarDeslocamento() {
        this.coluna = 0;
        this.linha = 0;
    }
    
    public int getX() {
        return coluna;
    }

    public int getY() {
        return linha;
    }
}
