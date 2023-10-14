/**
 * Clase que representa un pedido realizado por un productor.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Pedido {
	/** El plazo máximo en días para la entrega del pedido */
	private static final int PLAZO_MAXIMO_DIAS = 10;
	/** El identificador único del pedido */
	private UUID id;
	/** La fecha y hora en que se realizó el pedido */
	private LocalDateTime fechaPedido;
	/** La fecha de entrega del pedido */
	private LocalDate fechaEntrega;
	/** Indica si el pedido ha sido entregado */
	private boolean entregado;
	/** La lista de elementos de pedido */
	private List<ElementoPedido> elementosPedidos;
	/** El productor que realizó el pedido */
	private Productor productor;

	/**
	 * Constructor de la clase Pedido.
	 * 
	 * @param elementosPedidos La lista de elementos de pedido.
	 * @param fechaEntrega     La fecha de entrega del pedido.
	 */
	public Pedido(List<ElementoPedido> elementosPedidos, LocalDate fechaEntrega, Productor productor) {
		this.id = UUID.randomUUID();
		this.fechaPedido = LocalDateTime.now();
		this.elementosPedidos = elementosPedidos;
		this.productor = productor;
		this.fechaEntrega = fechaEntrega == null ? (LocalDate.now()).plusDays(PLAZO_MAXIMO_DIAS) : fechaEntrega;
	}
	
	/**
	 * Extrae todos los pedidos de todos los compradores.
	 * 
	 * @return Una lista de todos los pedidos.
	 */
	public static List<Pedido> extraerPedidos(List<Comprador> compradores) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		for (Comprador comprador : compradores) {
			pedidos.add(comprador.getPedido());
		}
		return pedidos;
	}


	/**
	 * Obtiene el identificador del pedido.
	 * 
	 * @return El identificador del pedido.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Obtiene la fecha y hora en que se realizó el pedido.
	 * 
	 * @return La fecha y hora del pedido.
	 */
	public LocalDateTime getFecha() {
		return fechaPedido;
	}

	/**
	 * Indica si el pedido ha sido entregado.
	 * 
	 * @return `true` si el pedido ha sido entregado, `false` en caso contrario.
	 */
	public boolean getEntregado() {
		return entregado;
	}

	/**
	 * Establece el estado de entrega del pedido.
	 * 
	 * @param entregado El estado de entrega del pedido.
	 */
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	/**
	 * Obtiene el peso total del pedido.
	 * 
	 * @return El peso total del pedido.
	 */
	public double getPesoTotal() {
		double pesoTotal = 0;
		for (ElementoPedido elemento : elementosPedidos) {
			pesoTotal += elemento.getPeso();
		}
		return pesoTotal;
	}

	/**
	 * Obtiene la lista de elementos de pedido.
	 * 
	 * @return La lista de elementos de pedido.
	 */
	public List<ElementoPedido> getElementos() {
		return elementosPedidos;
	}

	/**
	 * Obtiene el productor.
	 * 
	 * @return el productor.
	 */
	public Productor getProductor() {
		return productor;
	}

	/**
	 * Obtiene la fecha de entrega.
	 * 
	 * @return la fecha de entrega.
	 */
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

}
