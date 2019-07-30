/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lutador;
import model.Peca;

/**
 *
 * @author 42519630833
 */
public class RealizarMovimentoCommand extends ControleJogoCommand {

    public RealizarMovimentoCommand(int linhaAnterior, int colunaAnterior, int linhaAtual, int colunaAtual, ControleJogoImpl controle, MovimentoLutador movimentoLutador, Peca[][] tabuleiro, Peca pecaAtual, Lutador lutadorAnterior, int tipoTabuleiro, int posicaoReiLinha, int posicaoReiColuna, int limiteCasa, int tipoEspecialidadeMovimentoRei) {
        super(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, controle, movimentoLutador, tabuleiro, pecaAtual, lutadorAnterior, tipoTabuleiro, posicaoReiLinha, posicaoReiColuna, limiteCasa, tipoEspecialidadeMovimentoRei);
    }

    @Override
    public boolean execute() {
        return controle.realizarMovimentacao(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, movimentoLutador, tabuleiro, pecaAtual, lutadorAnterior, tipoTabuleiro, posicaoReiLinha, posicaoReiColuna, limiteCasa, tipoEspecialidadeMovimentoRei);
    }

    @Override
    public void undo() {
        try {
            controle.desfazerMovimentacao(linhaAnterior, colunaAnterior, linhaAtual, colunaAtual, tabuleiro, pecaAtual, lutadorAnterior);
        } catch (Exception ex) {
            Logger.getLogger(RealizarMovimentoCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//fazer um metodo para salvar os movimentos em um arquivo texto, utilizando o Adapter
}
