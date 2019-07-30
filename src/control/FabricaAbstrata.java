
package control;

import model.Defensor;
import model.Mercenario;
import model.Rei;

/**
 *
 * @author 42519630833
 */
public abstract class FabricaAbstrata {

    public abstract Rei criarRei();

    public abstract Mercenario criarMercenario();

    public abstract Defensor criarDefensor();
}
