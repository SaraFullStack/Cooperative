/**
 * Clase que representa un producto y sus atributos.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	/* Nombres de los atributos para imprimir */
	private static final String[] NOMBRES_ATRIBUTOS = { "hectareas", "cantidad", "pesoTotal" };
	/* Iva estándar */
	private static final double IVA = 0.10;

	private TipoProducto tipoProducto;
	private double hectareas;

	/**
	 * Constructor de la clase Producto.
	 *
	 * @param tipoProducto El tipo de producto.
	 * @param hectareas    Las hectáreas del producto.
	 */
	public Producto(TipoProducto tipoProducto, double hectareas) {
		this.tipoProducto = tipoProducto;
		this.hectareas = hectareas;
	}

	/**
	 * Crea una lista de productos a partir de las hectáreas totales disponibles.
	 *
	 * @param hectareasTotales Las hectáreas totales disponibles.
	 * @return La lista de productos creados.
	 */
	public static List<Producto> crearProductos(double hectareasTotales) {
		List<Producto> productos = new ArrayList<Producto>();
		boolean opcion;
		do {
			System.out.println("Datos de producto:");
			TipoProducto[] tiposProducto = TipoProducto.values();
			for (int i = 0; i < tiposProducto.length; i++) {
				System.out.println((i + 1) + "- " + tiposProducto[i].getNombre());
			}
			int indiceTipoProducto = Utilidades.getInt("Ingrese el número correspondiente al tipo de producto: ") - 1;
			TipoProducto tipoProducto = tiposProducto[indiceTipoProducto];
			double hectareas = validarHectareas(hectareasTotales);
			hectareasTotales -= hectareas;
			Producto producto = new Producto(tipoProducto, hectareas);
			productos.add(producto);
			opcion = Utilidades.getBoolean("Agregar otro producto:");
			System.out.println();
		} while (opcion);
		return productos;
	}

	/**
	 * Valida el número de hectáreas ingresado por el usuario.
	 *
	 * @param hectareasTotales Las hectáreas totales disponibles.
	 * @return Las hectáreas válidas ingresadas por el usuario.
	 */
	public static double validarHectareas(double hectareasTotales) {
		boolean valido = false;
		double hectareas;
		do {
			hectareas = Utilidades.getDouble("Hectareas: ", 0.0);
			if (hectareas <= hectareasTotales) {
				return hectareas;
			}
			System.out.println("Las hectareas de producto no pueden ser más de las totales");
		} while (!valido);
		return hectareas;
	}

	/**
	 * Imprime todos los productos de la lista.
	 *
	 * @param productos La lista de productos a imprimir.
	 */
	public static void imprimirTodos(List<Producto> productos) {
		for (Producto producto : productos) {
			imprimirElemento(producto);
		}
	}

	/**
	 * Imprime los atributos de un producto.
	 *
	 * @param producto El producto a imprimir.
	 */
	public static void imprimirElemento(Producto producto) {
		Utilidades.imprimirObjeto(producto.tipoProducto, TipoProducto.NOMBRES_ATRIBUTOS);
		Utilidades.imprimirObjeto(producto, NOMBRES_ATRIBUTOS);
	}

	/**
	 * Actualiza el precio de un producto en la tabla de precios.
	 *
	 * @param producto El producto cuyo precio se actualizará.
	 * @param precio   El nuevo precio del producto.
	 */
	public static void actualizarPrecio(Producto producto, double precio) {
		TablaPrecios.setPrecio(precio, producto.getTipoProducto(), LocalDate.now());
		System.out.println("El precio del producto " + producto.getTipoProducto().getNombre()
				+ " ha sido actualizado a " + precio);
	}

	/**
	 * Extrae todos los productos de todos los productores.
	 * 
	 * @return Una lista de todos los productos.
	 */
	public static List<Producto> extraerProductos(List<Productor> productores) {
		List<Producto> productos = new ArrayList<Producto>();
		for (Productor productor : productores) {
			productos.addAll(productor.getProductos());
		}
		return productos;
	}

	/**
	 * Devuelve el tipo de producto.
	 *
	 * @return El tipo de producto.
	 */
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Devuelve el precio del producto para una fecha especificada.
	 *
	 * @param fecha La fecha para la cual se obtendrá el precio.
	 * @return El precio del producto.
	 */

	public double getPrecio(LocalDate fecha) {
		return TablaPrecios.getPrecio(tipoProducto, fecha);
	}

	/**
	 * Devuelve el precio del producto con el IVA incluido para una fecha
	 * especificada.
	 *
	 * @param fecha La fecha para la cual se obtendrá el precio con IVA.
	 * @return El precio del producto con IVA.
	 */

	public double getPrecioIVA(LocalDate fecha) {
		return (getPrecio(fecha) * IVA) + getPrecio(fecha);
	}

	/**
	 * Devuelve la cantidad de productos basándose en las hectáreas y el rendimiento
	 * por hectárea.
	 *
	 * @return La cantidad de productos.
	 */
	public int getCantidad() {
		return (int) Math.round(tipoProducto.getRendimientoHectarea() * hectareas);
	}

	/**
	 * Devuelve el peso total de los productos.
	 *
	 * @return El peso total de los productos.
	 */

	public double getPesoTotal() {
		return Math.round(tipoProducto.getPeso() * getCantidad());
	}

	/**
	 * Devuelve las hectáreas del producto.
	 *
	 * @return Las hectáreas del producto.
	 */
	public double getHectareas() {
		return hectareas;
	}
}
