/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 42519630833
 */
public interface DefineTabuleiro {
    AbstractTableModel configuraTabuleiro(int tipoTabuleiro) throws Exception;
}
