/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.ControleJogoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 42519630833
 */
public class TabuleiroBrandubh extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    ControleJogoImpl controle = ControleJogoImpl.getInstance();

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            return controle.getPeca(col, row);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }

}
