/**
 * Clase abstracta que representa a un Comprador. Proporciona funcionalidades
 * para agregar productos a un pedido, crear un nuevo pedido, imprimir elementos
 * de un comprador y mostrar información sobre los pedidos.
 * usuario y la manipulación de objetos.
 * @author (Sara Cubero) 
 * @version (1.0)
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Comprador {
    /* Constante para imprimir compradores */
    private static final String[] NOMBRES_ATRIBUTOS = { "nombre" };

    private String nombre;
    private Pedido pedido;

    /**
     * Constructor de la clase Comprador.
     * 
     * @param nombre nombre del comprador.
     * @param pedido pedido del comprador.
     */
    public Comprador(String nombre, Pedido pedido) {
        this.nombre = nombre;
        this.pedido = pedido;
    }

    /**
     * Extrae los nombres únicos de los productos de una lista de productos.
     * 
     * @param productos la lista de productos.
     * @return una lista que contiene los nombres únicos de los productos.
     */
    private static List<String> extraerProductosUnicos(List<Producto> productos) {
        Set<String> productosSet = new HashSet<>();
        for (Producto producto : productos) {
            productosSet.add(producto.getTipoProducto().getNombre());
        }
        return new ArrayList<>(productosSet);
    }

    /**
     * Agrega productos a un pedido. Permite al usuario seleccionar el productor, el producto y la cantidad.
     * 
     * @param productores  lista de productores disponibles.
     * @param kmCapital    kilómetros a la capital.
     * @param kmLocalidad  kilómetros a la localidad.
     * @param tipo         tipo de comprador (1: Consumidor Final, 2: Distribuidor).
     * @return una lista de elementos de pedido creados.
     */
    public static List<ElementoPedido> agregarProductosPedido(List<Productor> productores, double kmCapital,
            double kmLocalidad, int tipo, Productor productorElegido) {
        List<ElementoPedido> elementosPedidos = new ArrayList<ElementoPedido>();

        boolean opcion;
        do {
            List<Producto> productosDisponibles = productorElegido.getProductos();

            List<String> productosUnicos = extraerProductosUnicos(productosDisponibles);

            System.out.println("Productos disponibles del productor " + productorElegido.getNombre() + ":");
            for (int i = 0; i < productosUnicos.size(); i++) {
                System.out.println((i + 1) + ". " + productosUnicos.get(i));
                }
            int productoIndice = Utilidades.getInt("Introduzca el número del producto: ") - 1;

            String productoNombre = productosUnicos.get(productoIndice);
            Producto producto = null;

            int cantidadProducto = 0;

            for (Producto productoItem : productosDisponibles) {
                if (productoItem.getTipoProducto().getNombre().equals(productoNombre)) {
                    producto = productoItem;
                    cantidadProducto += productoItem.getCantidad();
                }
            }

            int cantidad = 0;
            int cantidadMaxima = Integer.MAX_VALUE;
            int cantidadMinima = 1;
            if (tipo == 1) {
                System.out.println("Cantidad máxima 100 kgr");
                cantidadMaxima = 100;
            } else {
                System.out.println("Cantidad mínima una tonelada");
                cantidadMinima = 1000;
            }

            System.out.println("Cantidad total de producto: " + cantidadProducto);
            do {
                cantidad = Utilidades.getInt("Cantidad: ");
            } while (cantidad > cantidadProducto || cantidad > cantidadMaxima || cantidad < cantidadMinima);

            ElementoPedido elementoPedido = new ElementoPedido(cantidad, producto, kmCapital, kmLocalidad);            
            elementosPedidos.add(elementoPedido);

            opcion = Utilidades.getBoolean("Agregar otro producto:");

        } while (opcion);
        return elementosPedidos;
    }



    /**
     * Crea un nuevo pedido a partir de una lista de elementos de pedido.
     * 
     * @param elementosPedidos lista de elementos de pedido.
     * @param tipo             tipo de comprador (Consumidor Final o Distribuidor).
     * @param nombre           nombre del comprador.
     * @param entrega          días de entrega del pedido.
     * @param productor         productor.
     * @return un objeto Comprador representando el pedido creado.
     */
    public static Comprador crearPedido(List<ElementoPedido> elementosPedidos, String tipo, String nombre,
            int entrega, Productor productor) {
        Pedido pedido = new Pedido(elementosPedidos, (LocalDate.now()).plusDays(entrega), productor);
        Comprador comprador = CompradorFactory.crearComprador(tipo, nombre, pedido);
        return comprador;
    }

    /**
     * Imprime los atributos de un comprador.
     * 
     * @param comprador objeto Comprador.
     */
    public static void imprimirElemento(Comprador comprador) {
        Utilidades.imprimirObjeto(comprador, NOMBRES_ATRIBUTOS);
    }

    /**
     * Imprime los compradores de un tipo específico (Consumidor Final o
     * Distribuidor).
     * 
     * @param compradores    lista de compradores.
     * @param esDistribuidor indica si se deben imprimir los compradores de tipo
     *                       Distribuidor.
     */
    public static void imprimirTipo(List<Comprador> compradores, boolean esDistribuidor) {
        for (Comprador comprador : compradores) {
            if (esDistribuidor && comprador instanceof Distribuidor) {
                Comprador.imprimirElemento(comprador);
            } else if (!esDistribuidor && comprador instanceof ConsumidorFinal) {
                Comprador.imprimirElemento(comprador);
            }
        }
    }

    /**
     * Imprime los pedidos disponibles y muestra información detallada sobre un
     * pedido seleccionado.
     * 
     * @param compradores lista de compradores.
     */
    public static void imprimirPedidos(List<Comprador> compradores) {
        System.out.println("Pedidos disponibles:");
        for (int i = 0; i < compradores.size(); i++) {
            System.out.println((i + 1) + ". " + compradores.get(i).getPedido().getId());
        }
        int pedidoIndice = Utilidades.getInt("¿Qué pedido imprimir? ") - 1;
        Comprador comprador = compradores.get(pedidoIndice);

        System.out.println("Comprador:" + comprador.getNombre());
        boolean distribuidor = true;
        if (comprador instanceof Distribuidor) {
            System.out.println("Tipo: Distribuidor");
        } else if (comprador instanceof ConsumidorFinal) {
            System.out.println("Tipo: Consumidor Final");
            distribuidor = false;
        }

        System.out.println("Fecha:" + comprador.getPedido().getFecha());
        System.out.println("Entrega:" + comprador.getPedido().getFechaEntrega());
        System.out.println("Entregado: " + (comprador.getPedido().getEntregado() ? "sí" : "no"));

        double totalKmGranLogistica = 0.0;
        double totalCosteGranLogistica = 0.0;
        double totalKmPequeñaLogistica = 0.0;
        double totalCostePequeñaLogistica = 0.0;
        double totalKmLogisticaPropia = 0.0;
        double totalCosteLogisticaPropia = 0.0;
        double totalCosteLogistica = 0.0;

        for (ElementoPedido elemento : comprador.getPedido().getElementos()) {
            System.out.println("Producto: " + elemento.getProducto().getTipoProducto());
            System.out.println("Cantidad: " + elemento.getCantidad());
            for (Logistica logistica : elemento.getLogistica()) {
                if (logistica instanceof GranLogistica) {
                    totalKmGranLogistica += logistica.getKm();
                    totalCosteGranLogistica += logistica.getCoste();
                } else if (logistica instanceof PequeñaLogistica) {
                    totalKmPequeñaLogistica += logistica.getKm();
                    totalCostePequeñaLogistica += logistica.getCoste();
                } else if (logistica instanceof LogisticaPropia) {
                    totalKmLogisticaPropia += logistica.getKm();
                    totalCosteLogisticaPropia += logistica.getCoste();
                }
                totalCosteLogistica += logistica.getCoste();
            }
            if (totalKmGranLogistica > 0 || totalCosteGranLogistica > 0) {
                System.out.println("Km totales Gran Logística: " + totalKmGranLogistica);
                System.out.println("Coste total Gran Logística: " + totalCosteGranLogistica);
            }

            if (totalKmPequeñaLogistica > 0 || totalCostePequeñaLogistica > 0) {
                System.out.println("Km totales Pequeña Logística: " + totalKmPequeñaLogistica);
                System.out.println("Coste total Pequeña Logística: " + totalCostePequeñaLogistica);
            }

            if (totalKmLogisticaPropia > 0 || totalCosteLogisticaPropia > 0) {
                System.out.println("Km totales Logística Propia: " + totalKmLogisticaPropia);
                System.out.println("Coste total Logística Propia: " + totalCosteLogisticaPropia);
            }
            totalKmGranLogistica = 0.0;
            totalCosteGranLogistica = 0.0;
            totalKmPequeñaLogistica = 0.0;
            totalCostePequeñaLogistica = 0.0;
            totalKmLogisticaPropia = 0.0;
            totalCosteLogisticaPropia = 0.0;

            double precio = 0.0;
            double beneficio = 0.0;
            if (distribuidor) {
                beneficio = (elemento.getProducto().getPrecio(comprador.getPedido().getFechaEntrega()) * PorcentajeTipoComprador.PORCENTAJE_DISTRIBUIDOR) / 100;
                precio = elemento.getProducto().getPrecio(comprador.getPedido().getFechaEntrega())
                        * elemento.getCantidad();
            } else {
                beneficio = (elemento.getProducto().getPrecio(comprador.getPedido().getFechaEntrega()) * PorcentajeTipoComprador.PORCENTAJE_CONSUMIDOR_FINAL) / 100;
                precio = elemento.getProducto().getPrecioIVA(comprador.getPedido().getFechaEntrega())
                        * elemento.getCantidad();
            }
            System.out.println("Beneficio de cooperativa:" + beneficio);
            System.out.println("Precio total con logistica:" + (precio + beneficio + totalCosteLogistica));
        }
    }

    /**
     * Obtiene el pedido asociado a un comprador.
     * 
     * @return el objeto Pedido asociado al comprador.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Obtiene el nombre del comprador.
     * 
     * @return el nombre del comprador.
     */
    public String getNombre() {
        return nombre;
    }
}
