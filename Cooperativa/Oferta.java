/**
* Clase que representa las ofertas por cantidad de productos con su respectivo
* descuento.
* @author (Sara Cubero) 
* @version (1.0)
*/

import java.util.HashMap;
import java.util.Map;


public class Oferta {

	/**
	 * Mapa que almacena las ofertas por cantidad de productos y su respectivo
	 * descuento. La clave representa la cantidad de productos y el valor representa
	 * el descuento.
	 */
	public static final Map<Double, Double> OFERTAS_POR_CANTIDAD_DESCUENTO = new HashMap<>();

	static {
		// Agregar las ofertas por cantidad y descuento al mapa
		OFERTAS_POR_CANTIDAD_DESCUENTO.put(100.0, 0.25);
		OFERTAS_POR_CANTIDAD_DESCUENTO.put(50.0, 0.15);
		OFERTAS_POR_CANTIDAD_DESCUENTO.put(25.0, 0.5);
	}
}
