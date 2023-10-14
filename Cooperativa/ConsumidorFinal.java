/**
 * Clase que representa a un Comprador de tipo Consumidor Final.
 * Hereda de la clase Comprador y proporciona un constructor para crear un objeto ConsumidorFinal.
 * @author (Sara Cubero) 
 * @version (1.0)
*/

/**
 * Clase que representa a un Comprador de tipo Consumidor Final.
 * Hereda de la clase Comprador y proporciona un constructor para crear un objeto ConsumidorFinal.
 */
public class ConsumidorFinal extends Comprador {

    /**
     * Constructor de la clase ConsumidorFinal.
     * @param nombre nombre del comprador.
     * @param pedido pedido del comprador.
     */
    public ConsumidorFinal(String nombre, Pedido pedido) {
        super(nombre, pedido);
    }
}
