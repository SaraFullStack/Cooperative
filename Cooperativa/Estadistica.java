/**
 * Clase que contiene métodos para calcular diversas estadísticas relacionadas
 * con los pedidos, ventas y precios.
 * @author (Sara Cubero) 
 * @version (1.0)
*/

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Estadistica {

    /**
     * Calcula las ventas totales por producto en un período de tiempo específico.
     * 
     * @param pedidos la lista de pedidos a analizar
     */
    public static void ventasTotales(List<Pedido> pedidos) {
        LocalDate fechaInicio, fechaFin;
        do {
            String inicio = Utilidades.getString("Introduzca la fecha de inicio (formato YYYY-MM-DD):");
            String fin = Utilidades.getString("Introduzca la fecha final (formato YYYY-MM-DD):");

            fechaInicio = LocalDate.parse(inicio);
            fechaFin = LocalDate.parse(fin);
        } while (fechaInicio.isAfter(fechaFin));

        Map<String, Integer> ventasPorProducto = new HashMap<>();

        for (Pedido pedido : pedidos) {
            if (!pedido.getFechaEntrega().isBefore(fechaInicio) && !pedido.getFechaEntrega().isAfter(fechaFin)) {
                for (ElementoPedido elemento : pedido.getElementos()) {
                    String nombreProducto = elemento.getProducto().getTipoProducto().getNombre();
                    int ventas = elemento.getCantidad();
                    ventasPorProducto.put(nombreProducto, ventasPorProducto.getOrDefault(nombreProducto, 0) + ventas);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : ventasPorProducto.entrySet()) {
            System.out.println("Producto: " + entry.getKey() + ", Ventas: " + entry.getValue());
        }

        if (ventasPorProducto.isEmpty()) {
            System.out.println("No se ha vendido ningun producto en ese período");
        }
    }
    /**
     * Calcula los ingresos obtenidos por cada productor en función de los pedidos entregados.
     *
     * @param pedidos la lista de pedidos a analizar.
     * @param lista de productores para buscar los productores si hay un pedido a una federacion
     */
    public static void importesObtenidosProductor(List<Pedido> pedidos, List<Productor> productores) {
        Map<String, Map<String, Double>> ingresosPorProductoPorProductor = new HashMap<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getEntregado()) {
                Productor productor = pedido.getProductor();
                
                if( productor instanceof ProductorFederado ) {
                    double total = 0;
                    for (int i = 0; i < productores.size(); i++) {
                        if( productor.getNombre().contains(productores.get(i).getNombre()) && !productor.getNombre().equals(productores.get(i).getNombre()) ) {
                            Map<String, Double> ingresosPorProducto = ingresosPorProductoPorProductor.getOrDefault(productores.get(i).getNombre(), new HashMap<>());

                            String stringPattern = "\\((.*?)\\)";
                            Pattern pattern = Pattern.compile(stringPattern);
                            if( total == 0 ) {
                                String[] hectareasProductores = productor.getNombre().split("-");
                                for( String hectareasProductor : hectareasProductores ) {
                                    Matcher match = pattern.matcher(hectareasProductor);
                                    if (match.find()) {
                                        total += Double.parseDouble(match.group(1));
                                    }
                                }    
                            }
                            String hectareasProductor = productor.getNombre().split(productores.get(i).getNombre())[1];
                            Matcher match = pattern.matcher(hectareasProductor);
                            Double hectareas = 1.0;
                            if (match.find()) {
                                hectareas = Double.parseDouble(match.group(1));
                            }
                            
                            for (ElementoPedido elemento : pedido.getElementos()) {
                                double ingreso = ( ( elemento.getProducto().getPrecio(pedido.getFechaEntrega()) * elemento.getCantidad() ) * hectareas ) / total;
                                String nombreProducto = elemento.getProducto().getTipoProducto().getNombre();
                                ingresosPorProducto.put(nombreProducto, ingresosPorProducto.getOrDefault(nombreProducto, 0.0) + ingreso);
                            }

                            ingresosPorProductoPorProductor.put(productores.get(i).getNombre(), ingresosPorProducto);
                        }
                    }
                } else {
                    Map<String, Double> ingresosPorProducto = ingresosPorProductoPorProductor.getOrDefault(productor.getNombre(), new HashMap<>());

                    for (ElementoPedido elemento : pedido.getElementos()) {
                        double ingreso = elemento.getProducto().getPrecio(pedido.getFechaEntrega()) * elemento.getCantidad();
                        String nombreProducto = elemento.getProducto().getTipoProducto().getNombre();
                        ingresosPorProducto.put(nombreProducto, ingresosPorProducto.getOrDefault(nombreProducto, 0.0) + ingreso);
                    }
               
                    ingresosPorProductoPorProductor.put(productor.getNombre(), ingresosPorProducto);
                }
            }
        }

        for (Map.Entry<String, Map<String, Double>> entryProductor : ingresosPorProductoPorProductor.entrySet()) {
            String nombreProductor = entryProductor.getKey();
            System.out.println("Productor: " + nombreProductor);
            
            for (Map.Entry<String, Double> entryProducto : entryProductor.getValue().entrySet()) {
                System.out.println("Producto: " + entryProducto.getKey() + ", Ingreso obtenido: " + entryProducto.getValue());
            }
        }

    }

    /**
     * Calcula los importes obtenidos por cada tipo de logística en función de los
     * pedidos entregados.
     * 
     * @param pedidos la lista de pedidos a analizar
     */
    public static void importesObtenidosLogistica(List<Pedido> pedidos) {
        Map<String, Double> costesPorLogistica = new HashMap<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getEntregado()) {
                for (ElementoPedido elemento : pedido.getElementos()) {
                    for (Logistica logistica : elemento.getLogistica()) {
                        double coste = logistica.getCoste();

                        String nombreClaseLogistica = logistica.getClass().getSimpleName();

                        costesPorLogistica.put(nombreClaseLogistica,
                                costesPorLogistica.getOrDefault(nombreClaseLogistica, 0.0) + coste);
                    }
                }
            }
        }

        for (Map.Entry<String, Double> entry : costesPorLogistica.entrySet()) {
            System.out.println("Logística: " + entry.getKey() + ", Importe obtenido: " + entry.getValue());
        }
    }

    /**
     * Calcula los beneficios totales obtenidos por la cooperativa por cada
     * producto.
     * 
     * @param compradores la lista de compradores
     */
    public static void beneficiosCooperativaPorProducto(List<Comprador> compradores) {
        Map<String, Double> beneficiosPorProducto = new HashMap<>();

        for (Comprador comprador : compradores) {
            if (comprador.getPedido().getEntregado()) {
                for (ElementoPedido elemento : comprador.getPedido().getElementos()) {
                    double beneficio = 0.0;
                    if (comprador instanceof Distribuidor) {
                        beneficio = (elemento.getProducto().getPrecio(comprador.getPedido().getFechaEntrega()) * 5)
                                / 100;
                    } else if (comprador instanceof ConsumidorFinal) {
                        beneficio = (elemento.getProducto().getPrecio(comprador.getPedido().getFechaEntrega()) * 15)
                                / 100;
                    }
                    beneficio *= elemento.getCantidad();
                    String tipoProducto = elemento.getProducto().getTipoProducto().getNombre();

                    beneficiosPorProducto.put(tipoProducto,
                            beneficiosPorProducto.getOrDefault(tipoProducto, 0.0) + beneficio);
                }
            }

            for (Map.Entry<String, Double> entry : beneficiosPorProducto.entrySet()) {
                System.out.println("Producto: " + entry.getKey() + ", Beneficio: " + entry.getValue());
            }
        }
    }

    /**
     * Calcula la evolución de precios de cada producto.
     * 
     * @param productos la lista de productos a analizar
     */
    public static void evolucionPreciosProducto(List<Producto> productos) {
        boolean cambiosEnPrecios = false;
        Set<TipoProducto> productosImpresos = new HashSet<>();

        for (Producto producto : productos) {
            if (!productosImpresos.contains(producto.getTipoProducto())) {
                double precioActual = TablaPrecios.getPrecio(producto.getTipoProducto(), LocalDate.now());
                System.out.println("Precio actual de " + producto.getTipoProducto().getNombre() + ": " + precioActual);

                List<Map<LocalDate, Map<TipoProducto, Double>>> preciosAnteriores = TablaPrecios
                        .getPreciosAnteriores(producto.getTipoProducto());
                if (!preciosAnteriores.isEmpty()) {
                    cambiosEnPrecios = true;
                    System.out.println("Evolución de precios para " + producto.getTipoProducto().getNombre() + ":");

                    for (Map<LocalDate, Map<TipoProducto, Double>> registroPrecios : preciosAnteriores) {
                        LocalDate fecha = registroPrecios.keySet().iterator().next();
                        Map<TipoProducto, Double> preciosMap = registroPrecios.get(fecha);
                        double precioAnterior = preciosMap.get(producto.getTipoProducto());

                        if (precioAnterior != precioActual) {
                            System.out.println("Fecha: " + fecha + ", Precio:" + precioAnterior);
                        }
                        precioActual = precioAnterior;
                    }
                }
                System.out.println();
                productosImpresos.add(producto.getTipoProducto());
            }
        }

        if (!cambiosEnPrecios) {
            System.out.println("No se produjeron cambios en los precios.");
        }
    }

}
