/**
 * Clase que se encarga de crear objetos de tipo Productor según los parámetros
 * proporcionados.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.util.List;

public class ProductorFactory {
	/**
	 * Crea un objeto Productor según los parámetros especificados.
	 *
	 * @param nombre    El nombre del productor.
	 * @param hectareas Las hectáreas del productor.
	 * @param productos Los productos del productor.
	 * @return El objeto Productor creado.
	 * @throws IllegalArgumentException Si se intenta crear un ProductorPequeño con
	 *                                  más de 5 productos.
	 */
	public static Productor crearProductor(String nombre, double hectareas, List<Producto> productos) {
		LimiteHectareas limite = LimiteHectareas.getInstance();
		double limiteHectareas = limite.getLimite();

		if (hectareas > limiteHectareas) {
			return new ProductorGrande(nombre, hectareas, productos);
		} else {
			if (productos.size() > ProductorPequeño.getMaxProductos()) {
				throw new IllegalArgumentException("No se puede crear un ProductorPequeño con más de 5 productos");
			}
			return new ProductorPequeño(nombre, hectareas, productos);
		}
	}
}
