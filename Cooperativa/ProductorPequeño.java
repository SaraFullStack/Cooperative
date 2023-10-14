/**
* Clase que representa un Productor pequeño. Extiende la clase abstracta Productor.
* @author (Sara Cubero) 
* @version (1.0)
*/

import java.util.List;

public class ProductorPequeño extends Productor {
	/**
	 * Constante que define el número máximo de productos permitidos para un
	 * Productor pequeño.
	 */
	private static final int MAX_PRODUCTOS = 5;

	/**
	 * Constructor de la clase ProductorPequeño.
	 *
	 * @param nombre    El nombre del Productor.
	 * @param hectareas El número de hectáreas del Productor.
	 * @param productos La lista de productos del Productor.
	 */
	public ProductorPequeño(String nombre, double hectareas, List<Producto> productos) {
		super(nombre, hectareas, productos);
	}

	/**
	 * Obtiene el número máximo de productos permitidos para un Productor pequeño.
	 *
	 * @return El número máximo de productos permitidos.
	 */
	public static int getMaxProductos() {
		return MAX_PRODUCTOS;
	}

}
