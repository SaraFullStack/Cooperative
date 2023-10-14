 /**
 * Clase que representa a un Comprador de tipo Distribuidor.
 * Hereda de la clase Comprador y proporciona un constructor para crear un objeto Distribuidor.
 * @author (Sara Cubero) 
 * @version (1.0)
*/

public class Distribuidor extends Comprador {

    /**
     * Constructor de la clase Distribuidor.
     * @param nombre nombre del comprador.
     * @param pedido pedido del comprador.
     */
    public Distribuidor(String nombre, Pedido pedido) {
        super(nombre, pedido);
    }
}
