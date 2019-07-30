/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Peca;

/**
 *
 * @author 42519630833
 */
public interface Observador {

    void resetouJogo();

    void fimDeJogo(String msgErro);
    
    void mudouTabuleiro();
    
    void reiRotaFuga();
    
    void reiEmPerigo();
    
    void reiCapturado();
    
    void reiFugiu();

}
