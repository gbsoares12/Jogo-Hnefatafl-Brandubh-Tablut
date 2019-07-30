/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.Icon;

/**
 *
 * @author 42519630833
 */
public interface ControleJogo {

    void inicializar(int tipoTabuleiro, String playerDefensor, String playerMercenario, int tipoEspecialidadeMovimento, int tipoEspecialidadeMovimentoRei) throws Exception;

    Icon getPeca(int col, int row) throws Exception;

    void selecionarCasa(Integer linha, Integer coluna) throws Exception;

    void run() throws Exception;
    
    void addObservador(Observador obs);

    void resetarJogo() throws Exception;
   
    void voltarRodada() throws Exception;
    
//    void acceptDefensoresMortos(ProcuraDefensor visitor);
//    
//    void acceptMercenariosMortos(ProcuraMercenario visitor);
    
}
