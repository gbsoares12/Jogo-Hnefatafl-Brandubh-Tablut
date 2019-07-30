/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Vivo  extends EstadoLutador{

    public Vivo(Lutador lutador) {
        
        super(lutador);
        
        if(lutador.getClass() == DefensorNormal.class){
            this.lutador.setImagem(new ImageIcon("imagens/Defensor.png"));
        }else if(lutador.getClass() == MercenarioNormal.class){
            this.lutador.setImagem(new ImageIcon("imagens/Mercenario.png"));
        }else if(lutador.getClass() == MercenarioTablut.class){
            this.lutador.setImagem(new ImageIcon("imagens/MercenarioRefugio.png"));
        }else if(lutador.getClass() == ReiNormal.class){
            this.lutador.setImagem(new ImageIcon("imagens/RefugioComRei.png"));
        }
        
    }

    @Override
    public void proxEstado() {
        lutador.setEstadoAtual(new Morto(lutador));
    }
    
}
