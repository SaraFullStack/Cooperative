/**
 * Esta clase representa una Cooperativa con productores y compradores.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Esta clase representa una Cooperativa con productores y compradores.
 */
public class Cooperativa {
	// Mensajes constantes usados en la aplicación.
	private static final String AÑADIR_OPCION = "Ingrese una opción:";
	private static final String OPCION_INVALIDA = "Opción inválida.";
	// Listas para almacenar los productores y compradores.
	private static List<Productor> productores = new ArrayList<Productor>();
	private static List<Comprador> compradores = new ArrayList<Comprador>();

	/**
	 * Punto de entrada principal de la aplicación.
	 * 
	 * @param args argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		cargaPrincipal();
		menuPrincipal();
	}

	/**
	 * Este método muestra el menú principal y maneja la entrada del usuario.
	 */
	private static void menuPrincipal() {
		int opcion;
		do {
			try {
				Utilidades.imprimirMenu(Menus.MENU_PRINCIPAL);

				opcion = Utilidades.getInt(AÑADIR_OPCION);

				switch (opcion) {
				case 0:
					break;
				case 1:
					menuProductor();
					break;
				case 2:
					menuCompradores();
					break;
				case 3:
					menuEstadistica();
					break;
				default:
					System.out.println(OPCION_INVALIDA);
				}
			} catch (Exception e) {
				System.out.println("Ha habido un error: " + e.getMessage());
				opcion = -1;
			}
		} while (opcion != 0);
	}

	/**
	 * Presenta un menú para interactuar con objetos Productor. Permite al usuario
	 * elegir opciones como crear un nuevo Productor, imprimir todos los
	 * Productores, eliminar un Productor, establecer un límite para las hectáreas y
	 * actualizar el precio de un producto.
	 */
	private static void menuProductor() {
		int opcion;

		do {
			Utilidades.imprimirMenu(Menus.MENU_PRODUCTORES);
			opcion = Utilidades.getInt(AÑADIR_OPCION);

			switch (opcion) {
			case 0:
				break;
			case 1:
				menuCrearProductor();
				break;
			case 2:
				Productor.imprimirTodos(productores);
				break;
			case 3:
				Productor.borrarProductor(productores);
				break;
			case 4:
				menuLimiteHectareas();
				break;
			case 5:
				Set<String> productosUnicos = new HashSet<>();
				List<Producto> productos = Producto.extraerProductos(productores);
				for (Producto producto : productos) {
					productosUnicos.add(producto.getTipoProducto().getNombre());
				}

				System.out.println("Productos disponibles:");
				int i = 1;
				for (String productoUnico : productosUnicos) {
					System.out.println(i + ". " + productoUnico);
					i++;
				}

				int opcionProducto = Utilidades.getInt("Ingrese el número del producto que desea actualizar: ");
				String productoNombre = "";
				i = 1;
				for (String productoUnico : productosUnicos) {
					if (i == opcionProducto) {
						productoNombre = productoUnico;
						break;
					}
					i++;
				}

				double precio = Utilidades.getDouble("¿Qué precio desea poner?", 0.0);

				for (Producto producto : productos) {
					if (producto.getTipoProducto().getNombre().equalsIgnoreCase(productoNombre)) {
						Producto.actualizarPrecio(producto, precio);
						return;
					}
				}

				System.out.println("No se ha encontrado ningún producto con ese nombre.");
				break;
			default:
				System.out.println(OPCION_INVALIDA);
			}
			System.out.println();
		} while (opcion != 0);
	}

	/**
	 * Presenta un menú para crear nuevos objetos de Productor. El usuario puede
	 * elegir entre crear un Productor normal o crear un Productor Federado.
	 */
	private static void menuCrearProductor() {
		int opcion;

		do {
			Utilidades.imprimirMenu(Menus.MENU_CREAR_PRODUCTORES);
			opcion = Utilidades.getInt(AÑADIR_OPCION);

			switch (opcion) {
			case 0:
				break;
			case 1:
				productores.add(Productor.crearProductor());
				break;
			case 2:
				crearProductorFederado();
				break;
			default:
				System.out.println(OPCION_INVALIDA);
			}
			System.out.println();
		} while (opcion != 0);
	}

	/**
	 * Crea un nuevo Productor Federado a partir de varios Productores Pequeños. El
	 * usuario puede seleccionar los productores y el producto que se federará.
	 */
	private static void crearProductorFederado() {
		List<Productor> productoresPequeños = new ArrayList<>();
		Set<String> nombresProductosUnicos = new HashSet<>();

		for (Productor productor : productores) {
			if (productor instanceof ProductorPequeño) {
				productoresPequeños.add(productor);
				for (Producto producto : productor.getProductos()) {
					nombresProductosUnicos.add(producto.getTipoProducto().getNombre());
				}
			}
		}

		List<String> nombresProductosLista = new ArrayList<>(nombresProductosUnicos);
		for (int i = 0; i < nombresProductosLista.size(); i++) {
			System.out.println((i + 1) + "- " + nombresProductosLista.get(i));
		}

		int indiceProducto = Utilidades.getInt("¿Qué producto desea federar? (Ingrese el número)") - 1;
		String producto = nombresProductosLista.get(indiceProducto);

		boolean otro = false;
		List<String> productoresNombres = new ArrayList<String>();

		Iterator<Productor> iterator = productoresPequeños.iterator();
		while (iterator.hasNext()) {
			Productor productor = iterator.next();
			boolean tieneProducto = false;
			for (Producto prod : productor.getProductos()) {
				if (prod.getTipoProducto().getNombre().equals(producto)) {
					tieneProducto = true;
					break;
				}
			}
			if (!tieneProducto) {
				iterator.remove();
			}
		}

		if (productoresPequeños.isEmpty()) {
			throw new IllegalArgumentException("El producto ingresado es incorrecto.");
		} else {
			do {
				for (int i = 0; i < productoresPequeños.size(); i++) {
					System.out.println((i + 1) + "- " + productoresPequeños.get(i).getNombre());
				}

				int indiceElegido = Utilidades.getInt("¿De qué productor? (Ingrese el número)") - 1;
				if (indiceElegido >= 0 && indiceElegido < productoresPequeños.size()) {
					Productor productorElegido = productoresPequeños.get(indiceElegido);
					productoresNombres.add(productorElegido.getNombre());
					productoresPequeños.remove(indiceElegido);
				} else {
					System.out.println("?ndice de productor inválido.");
				}

				if (productoresPequeños.size() == 0) {
					otro = false;
				} else {
					otro = Utilidades.getBoolean("¿Agregar otro productor?");
				}
			} while (otro);
		}

		Productor productorFederado = Productor.crearProductorFederado(productores, producto, productoresNombres,
				false);
		for (Productor productorItem : productores) {
			if (productorItem instanceof ProductorPequeño) {
				ProductorPequeño productorPequeño = (ProductorPequeño) productorItem;
				if (productorPequeño.getProductos().stream()
						.anyMatch(p -> p.getTipoProducto().getNombre().equals(producto))) {
					for (Producto productoItem : productorPequeño.getProductos()) {
						if (productoItem.getTipoProducto().getNombre().equals(producto)) {
							productorPequeño.getProductos().remove(productoItem);
							break;
						}
					}
				}

			}
		}
		productores.add(productorFederado);
	}

	/**
	 * Presenta un menú para establecer un límite en las hectáreas. El usuario puede
	 * cambiar el valor del límite.
	 */
	private static void menuLimiteHectareas() {
		int opcion;

		do {
			LimiteHectareas limite = LimiteHectareas.getInstance();
			double limiteHectareas = limite.getLimite();

			String[] opcionesMenu = { "LÍMITE HECTAREAS",
					"1. Cambiar valor",
					"0. Volver",
					"Valor actual:".concat(String.valueOf(limiteHectareas)), };

			Utilidades.imprimirMenu(opcionesMenu);
			opcion = Utilidades.getInt(AÑADIR_OPCION);

			switch (opcion) {
			case 0:
				break;
			case 1:
				double valor = Utilidades.getDouble("Ingrese un valor: ", 0.0);
				if (valor <= 0) {
					System.out.print("No es un valor válido");
				}
				limite.setLimite(valor);
				List<Productor> productoresAux = new ArrayList<Productor>();
				for (Productor productor : productores) {
					productoresAux.add(ProductorFactory.crearProductor(productor.getNombre(), productor.getHectareas(),
							productor.getProductos()));
				}
				productores = productoresAux;
				break;
			default:
				System.out.println(OPCION_INVALIDA);
			}
			System.out.println();
		} while (opcion != 0);
	}

	/**
	 * Presenta un menú para interactuar con objetos Comprador. Permite al usuario
	 * elegir opciones como imprimir Compradores, imprimir Pedidos, crear un nuevo
	 * Pedido, y verificar las entregas y precios.
	 */
	private static void menuCompradores() {
		int opcion;

		do {
			Utilidades.imprimirMenu(Menus.MENU_COMPRADORES);
			opcion = Utilidades.getInt(AÑADIR_OPCION);

			switch (opcion) {
			case 0:
				break;
			case 1:
				Comprador.imprimirTipo(compradores, true);
				break;
			case 2:
				Comprador.imprimirTipo(compradores, false);
				break;
			case 3:
				Comprador.imprimirPedidos(compradores);
				break;
			case 4:
				int tipo = Utilidades.getInt("Tipo de comprador: 1- Consumidor Final 2-Distribuidor");
				String nombre = Utilidades.getString("Nombre de comprador: ");
				double kmCapital = Utilidades.getDouble("KM a capital: ", 0.0);
				double kmLocalidad = Utilidades.getDouble("KM a localidad: ", 100.0);
				int entrega = Utilidades.getInt("Días de envio (estándar - 10): ");
				System.out.println("Productores disponibles:");
				for (int i = 0; i < productores.size(); i++) {
					boolean tieneFederacion = false;
					for (int j = i + 1; j < productores.size(); j++) {
						if( productores.get(j) instanceof ProductorFederado ) {
							if( productores.get(j).getNombre().contains(productores.get(i).getNombre()) ) {
								if( productores.get(j).getProductos().size() == productores.get(i).getProductos().size() ) {
									boolean esta = false;
									for( Producto productoI : productores.get(i).getProductos() ) {
										for( Producto productoJ : productores.get(j).getProductos() ) {
											if( productoI.getTipoProducto().getNombre().equals(productoJ.getTipoProducto().getNombre()) ) {
												esta = true;
											}
										}
										if( !esta ) {
											break;
										}
									}
									if( esta ) {
										tieneFederacion = true;
									}
								}
							}
						}
					}
					
					if( !tieneFederacion ) {
						System.out.println((i + 1) + ". " + productores.get(i).getNombre());
					}
				}
				int productorIndice = Utilidades.getInt("Introduzca el número del productor: ") - 1;

				Productor productorElegido = productores.get(productorIndice);
				compradores.add(Comprador.crearPedido(
						Comprador.agregarProductosPedido(productores, kmCapital, kmLocalidad, tipo, productorElegido),
						(tipo == 1) ? TipoComprador.CONSUMIDOR_FINAL : TipoComprador.DISTRIBUIDOR, nombre, entrega,
						productorElegido));
				break;
			case 5:
				verificarEntregasYPrecios(null, true);
				break;
			default:
				System.out.println(OPCION_INVALIDA);
			}
			System.out.println();
		} while (opcion != 0);
	}

	/**
	 * Presenta un menú para interactuar con las estadísticas. Permite al usuario
	 * elegir opciones como visualizar las ventas totales, los ingresos obtenidos
	 * por productor, los ingresos obtenidos por logística, los beneficios de la
	 * cooperativa por producto, y la evolución de los precios del producto.
	 */
	private static void menuEstadistica() {
		int opcion;

		do {
			Utilidades.imprimirMenu(Menus.MENU_ESTADISTICA);
			opcion = Utilidades.getInt(AÑADIR_OPCION);

			switch (opcion) {
			case 0:
				break;
			case 1:
				Estadistica.ventasTotales(Pedido.extraerPedidos(compradores));
				break;
			case 2:
				Estadistica.importesObtenidosProductor(Pedido.extraerPedidos(compradores), productores);
				break;
			case 3:
				Estadistica.importesObtenidosLogistica(Pedido.extraerPedidos(compradores));
				break;
			case 4:
				Estadistica.beneficiosCooperativaPorProducto(compradores);
				break;
			case 5:
				Estadistica.evolucionPreciosProducto(Producto.extraerProductos(productores));
				break;
			default:
				System.out.println(OPCION_INVALIDA);
			}
			System.out.println();
		} while (opcion != 0);
	}

	/**
	 * Carga los datos iniciales de la aplicación. Crea productores y compradores de
	 * prueba y actualiza los precios de los productos.
	 */
	private static void cargaPrincipal() {
		for (int i = 0; i < 5; i++) {
			productores.add(ProductorGenerator.generarProductorRandom(false));
		}
		for (int i = 0; i < 5; i++) {
			productores.add(ProductorGenerator.generarProductorRandom(true));
		}

		Productor duplicado = new ProductorPequeño("Curro", productores.get(9).getHectareas(),
				productores.get(9).getProductos());
		List<String> productoresNombres = new ArrayList<String>();
		productoresNombres.add(duplicado.getNombre());
		productoresNombres.add(productores.get(9).getNombre());
		productores.add(duplicado);
		productores.add(Productor.crearProductorFederado(productores,
				duplicado.getProductos().get(0).getTipoProducto().getNombre(), productoresNombres, true));
		compradores = CompradorGenerator.generarCompradores(productores);
		PrecioGenerator.actualizarPreciosUnicos(Producto.extraerProductos(productores));
		verificarEntregasYPrecios(LocalDate.now().plusDays(10), false);
	}

	/**
	 * Verifica las entregas y precios en una fecha determinada. Si se proporciona
	 * una fecha, verifica las entregas y precios en esa fecha. Si no se proporciona
	 * una fecha, verifica las entregas y precios en la fecha actual.
	 */
	private static void verificarEntregasYPrecios(LocalDate fechaAutomatica, boolean imprimir) {
		LocalDate fecha = fechaAutomatica;
		if (fechaAutomatica == null) {
			String fechaString = Utilidades.getString("Por favor, ingrese una fecha en el formato YYYY-MM-DD:");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fecha = LocalDate.parse(fechaString, formatter);
		}

		for (Comprador comprador : compradores) {
			Pedido pedido = comprador.getPedido();
			if (pedido.getFechaEntrega().compareTo(fecha) <= 0 && !pedido.getEntregado()) {
				pedido.setEntregado(true);
				if (imprimir) {
					System.out.println("Pedido entregado:" + pedido.getId());
				}
			}
		}
	}
}
