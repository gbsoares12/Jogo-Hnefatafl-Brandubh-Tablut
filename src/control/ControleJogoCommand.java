/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Lutador;
import model.Peca;

/**
 *
 * @author 42519630833
 */
public abstract class ControleJogoCommand implements Command {

    protected int linhaAnterior;
    protected int colunaAnterior;
    protected int linhaAtual;
    protected int colunaAtual;
    protected MovimentoLutador movimentoLutador;
    protected ControleJogoImpl controle;
    protected Peca[][] tabuleiro;
    protected Peca pecaAtual;
    protected Lutador lutadorAnterior;
    protected int tipoTabuleiro;
    protected int posicaoReiLinha;
    protected int posicaoReiColuna;
    protected int limiteCasa;
    protected int tipoEspecialidadeMovimentoRei;
    
    public ControleJogoCommand(int linhaAnterior, int colunaAnterior, int linhaAtual, int colunaAtual, ControleJogoImpl controle, MovimentoLutador movimentoLutador, Peca[][] tabuleiro, Peca pecaAtual, Lutador lutadorAnterior, int tipoTabuleiro, int posicaoReiLinha, int posicaoReiColuna, int limiteCasa, int tipoEspecialidadeMovimentoRei) {
        
        this.linhaAnterior = linhaAnterior;
        this.colunaAnterior = colunaAnterior;
        this.linhaAtual = linhaAtual;
        this.colunaAtual = colunaAtual;
        this.controle = controle;
        this.movimentoLutador = movimentoLutador;
        this.tabuleiro = tabuleiro;
        this.pecaAtual = pecaAtual;
        this.lutadorAnterior = lutadorAnterior;
        this.tipoTabuleiro = tipoTabuleiro;
        this.posicaoReiLinha = posicaoReiLinha;
        this.posicaoReiColuna = posicaoReiColuna;
        this.limiteCasa = limiteCasa;
        this.tipoEspecialidadeMovimentoRei = tipoEspecialidadeMovimentoRei;
    }
    
    
}
