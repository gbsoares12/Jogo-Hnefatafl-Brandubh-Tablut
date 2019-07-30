/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Usuario
 */
public abstract class EstadoLutador {

    protected Lutador lutador;

    public EstadoLutador(Lutador lutador) {
        this.lutador = lutador;
    }
     
    public abstract void proxEstado();
}
