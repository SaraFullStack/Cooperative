/**
 * Clase que representa un elemento de un pedido.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.util.List;

public class ElementoPedido {
	
	/** La cantidad del producto en el pedido */
	private int cantidad;
	
	/** El producto del elemento */
	private Producto producto;
	
	/** La lista de logísticas asociadas al elemento */
	private List<Logistica> logisticas;

	/**
	 * Constructor de la clase ElementoPedido.
	 * 
	 * @param cantidad La cantidad del producto en el pedido.
	 * @param producto El producto del elemento.
	 * @param kmCapital Los kilómetros hacia la capital.
	 * @param kmLocalidad Los kilómetros hacia la localidad.
	 */
	public ElementoPedido(int cantidad, Producto producto, double kmCapital, double kmLocalidad) {
		this.cantidad = cantidad;
		this.producto = producto;
		logisticas = Logistica.generarEnvio(producto, kmCapital, kmLocalidad, cantidad);
	}
	
	/**
	 * Obtiene el nombre del producto del elemento.
	 * 
	 * @return El nombre del producto.
	 */
	public String getProductoNombre() {
		return producto.getTipoProducto().getNombre();
	}
	
	/**
	 * Obtiene la cantidad del producto en el elemento.
	 * 
	 * @return La cantidad del producto.
	 */
	public int getCantidad() {
		return cantidad;
	}
	
	/**
	 * Obtiene el peso total del elemento.
	 * 
	 * @return El peso total del elemento.
	 */
	public double getPeso() {
		return cantidad * producto.getTipoProducto().getPeso();
	}

	/**
	 * Obtiene el producto del elemento.
	 * 
	 * @return El producto del elemento.
	 */
	public Producto getProducto() {
		return producto;
	}
	
	/**
	 * Obtiene la lista de logísticas asociadas al elemento.
	 * 
	 * @return La lista de logísticas.
	 */
	public List<Logistica> getLogistica() {
		return logisticas;
	}

	/**
	 * Establece la cantidad del producto en el elemento.
	 * 
	 * @param cantidad La nueva cantidad del producto.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
