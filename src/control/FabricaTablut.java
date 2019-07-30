/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.ImageIcon;
import model.Defensor;
import model.DefensorNormal;
import model.Mercenario;
import model.MercenarioTablut;
import model.Rei;
import model.ReiNormal;

/**
 *
 * @author 42519630833
 */
public class FabricaTablut extends FabricaAbstrata {

    @Override
    public Rei criarRei() {
        return new ReiNormal(new ImageIcon("imagens/RefugioComRei.png"));
    }

    @Override
    public Mercenario criarMercenario() {
        return new MercenarioTablut(new ImageIcon("imagens/MercenarioRefugio.png"));
    }

    @Override
    public Defensor criarDefensor() {
        return new DefensorNormal(new ImageIcon("imagens/Defensor.png"));
    }
    
}
