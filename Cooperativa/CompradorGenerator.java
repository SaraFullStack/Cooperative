/**
 * Generador de compradores aleatorios. Proporciona un método estático para
 * generar una lista de compradores con información aleatoria, como tipo de
 * comprador, nombre y elementos de pedido.
 * @author (Sara Cubero) 
 * @version (1.0)
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompradorGenerator {
	/* Constante random */
	private static final Random random = new Random();

	/**
	 * Genera una lista de compradores aleatorios basados en la lista de productos
	 * proporcionada.
	 * 
	 * @param productores lista de productores disponibles.
	 * @return una lista de compradores generados aleatoriamente.
	 */
	public static List<Comprador> generarCompradores(List<Productor> productores) {
		List<Comprador> compradores = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			String tipo = generarTipoComprador();
			String nombre = generarNombreComprador();
			Productor productor = productores.get(i);
			
			for (int j = i; j < productores.size(); j++) {
				if( productores.get(j) instanceof ProductorFederado ) {
					if( productores.get(j).getNombre().contains(productores.get(i).getNombre()) ) {
						productor = productores.get(j);
						break;
					}
				}
			}
			
			List<ElementoPedido> elementosPedido = agregarElementosPedido(productor.getProductos());
			Comprador comprador = Comprador.crearPedido(elementosPedido, tipo, nombre, 10, productor);
			compradores.add(comprador);
		}

		return compradores;
	}

	/**
	 * Agrega elementos de pedido aleatorios a una lista de elementos de pedido.
	 * 
	 * @param productos lista de productos disponibles.
	 * @return una lista de elementos de pedido generados aleatoriamente.
	 */
	private static List<ElementoPedido> agregarElementosPedido(List<Producto> productos) {
		List<ElementoPedido> elementosPedido = new ArrayList<>();

		if (!productos.isEmpty()) {
			int numProductos = random.nextInt(productos.size()) + 1;
			for (int i = 0; i < numProductos; i++) {
				Producto producto = productos.get(random.nextInt(productos.size()));
				int cantidad = random.nextInt(10) + 1;
				double kmCapital = Math.round(random.nextDouble() * 100);
				double kmLocalidad = Math.round(random.nextDouble() * 50);
				elementosPedido.add(new ElementoPedido(cantidad, producto, kmCapital, kmLocalidad));
			}
		}

		return elementosPedido;
	}

	/**
	 * Genera un tipo de comprador aleatorio (DISTRIBUIDOR o CONSUMIDOR_FINAL).
	 * 
	 * @return el tipo de comprador generado aleatoriamente.
	 */
	private static String generarTipoComprador() {
		if (random.nextBoolean()) {
			return TipoComprador.DISTRIBUIDOR;
		} else {
			return TipoComprador.CONSUMIDOR_FINAL;
		}
	}

	/**
	 * Genera un nombre de comprador aleatorio utilizando una combinación de nombres
	 * y apellidos.
	 * 
	 * @return el nombre de comprador generado aleatoriamente.
	 */
	private static String generarNombreComprador() {
		String[] nombres = { "Juan", "Pedro", "Luis", "Ana", "María", "Lucía", "Miguel", "José", "Carla", "Elena",
				"Pablo", "Alberto" };
		String[] apellidos = { "García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Sánchez", "Ramírez",
				"Torres", "Flores", "Vargas", "Castillo" };

		int indexNombre = new Random().nextInt(nombres.length);
		int indexApellido = new Random().nextInt(apellidos.length);

		return nombres[indexNombre] + " " + apellidos[indexApellido];
	}

}
