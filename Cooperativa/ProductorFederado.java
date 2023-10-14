/**
* Clase que representa a un Productor Federado, que es una agrupación de
* Productores Pequeños.
* @author (Sara Cubero) 
* @version (1.0)
*/

import java.util.ArrayList;
import java.util.List;

public class ProductorFederado extends Productor {
	/**
	 * Crea un objeto ProductorFederado con el nombre, hectáreas y productos
	 * especificados.
	 *
	 * @param nombre    El nombre del productor federado.
	 * @param hectareas Las hectáreas totales del productor federado.
	 * @param productos Los productos del productor federado.
	 */
	public ProductorFederado(String nombre, double hectareas, List<Producto> productos) {
		super(nombre, hectareas, productos);
	}

	/**
	 * Crea un objeto ProductorFederado a partir de una lista de Productores
	 * Pequeños y un Producto. Los Productores Pequeños se agrupan en un Productor
	 * Federado, sumando sus hectáreas y creando un nuevo Producto con las hectáreas
	 * totales.
	 *
	 * @param productores          La lista de Productores Pequeños.
	 * @param productoresFederados La lista de Productores Federados existentes.
	 * @param producto             El Producto que se utilizará para crear el nuevo
	 *                             Productor Federado.
	 * @return El objeto ProductorFederado creado.
	 * @throws IllegalArgumentException Si la suma de las hectáreas de los
	 *                                  Productores Pequeños supera el límite de un
	 *                                  Productor Pequeño.
	 */
	public static ProductorFederado setProductorFederado(List<ProductorPequeño> productores,
			List<ProductorFederado> productoresFederados, Producto producto, boolean random) {
		LimiteHectareas limite = LimiteHectareas.getInstance();
		double limiteHectareas = limite.getLimite();
		double totalHectareas = 0;

		if (totalHectareas > limiteHectareas) {
			throw new IllegalArgumentException("No se puede crear el productor federado, "
					+ "ya que la suma de las hectareas supera el límite de productor pequeño");
		}

		String nombreFederado = "";
		double hectareas = 1.0;
		for (Productor productor : productores) {
			do {
				if (!random) {
					System.out.println("Hectareas totales del productor:" + productor.getHectareas());
					hectareas = Utilidades.getDouble("Cantidad de hectareas de " + productor.getNombre(), 0.0);
				}
			} while (hectareas > productor.getHectareas());
			if (nombreFederado.isEmpty()) {
				nombreFederado = productor.getNombre() + " (" + hectareas + ")";
			} else {
				nombreFederado += " - " + productor.getNombre() + " (" + hectareas + ")";
			}
			totalHectareas += hectareas;
		}

		List<Producto> productos = new ArrayList<Producto>();
		Producto productoFederado = new Producto(producto.getTipoProducto(), totalHectareas);
		productos.add(productoFederado);

		return new ProductorFederado(nombreFederado, totalHectareas, productos);
	}

}
