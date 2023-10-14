/**
 * Clase que se encarga de generar y actualizar precios únicos para los
 * productos.
 * @author (Sara Cubero) 
 * @version (1.0)
*/ 

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class PrecioGenerator {

	/**
	 * Actualiza los precios únicos de los productos en la tabla de precios.
	 *
	 * @param productos La lista de productos.
	 */
	public static void actualizarPreciosUnicos(List<Producto> productos) {
		for (TipoProducto tipoProducto : TipoProducto.values()) {
			Producto producto = buscarProductoPorTipo(productos, tipoProducto);
			if (producto != null) {
				TablaPrecios.setPrecio(numeroRandom(), producto.getTipoProducto(), generarFechaAleatoria());
			}
		}
	}

	/**
	 * Busca un producto en la lista de productos por su tipo.
	 *
	 * @param productos    La lista de productos.
	 * @param tipoProducto El tipo de producto a buscar.
	 * @return El producto encontrado o `null` si no se encuentra.
	 */
	private static Producto buscarProductoPorTipo(List<Producto> productos, TipoProducto tipoProducto) {
		for (Producto producto : productos) {
			if (producto.getTipoProducto() == tipoProducto) {
				return producto;
			}
		}
		return null;
	}

	/**
	 * Genera un número aleatorio entre 0.1 y 1 (con dos decimales).
	 *
	 * @return El número aleatorio generado.
	 */
	private static double numeroRandom() {
		return Math.round((Math.random() * (1 - 0.1) + 0.1) * 100) / 100.0;
	}

	/**
	 * Genera una fecha aleatoria dentro del año actual.
	 *
	 * @return La fecha aleatoria generada.
	 */
	public static LocalDate generarFechaAleatoria() {
		Random random = new Random();
		int minDay = 1;
		int maxDay = LocalDate.now().getDayOfYear();

		int randomDay = random.nextInt(maxDay - minDay + 1) + minDay;
		return LocalDate.ofYearDay(LocalDate.now().getYear(), randomDay);
	}

}
