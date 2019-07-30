/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.DefineTabuleiro;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario
 */
public class DefineTabuleiroImpl  implements  DefineTabuleiro{

    @Override
    public AbstractTableModel configuraTabuleiro(int tipoTabuleiro) throws Exception {
        switch (tipoTabuleiro) {
            case 1:
                return new TabuleiroHnefatafl();
            case 2:
                return new TabuleiroBrandubh();
            case 3:
                return new TabuleiroTablut();
            default:
                throw new Exception("Tabuleiro não encontrado!"); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
