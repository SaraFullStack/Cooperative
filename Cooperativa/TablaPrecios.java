/**
 * Clase para establecer y actualizar precios derivados de productos
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaPrecios {
    // Listas para almacenar los precios actuales.
    private static final Map<TipoProducto, Double> precios = new HashMap<>();
    // Listas para almacenar los precios anteriores.
    private static final List<Map<LocalDate, Map<TipoProducto, Double>>> preciosAnteriores = new ArrayList<>();

    static {
        precios.put(TipoProducto.ACEITE, 3.0);
        precios.put(TipoProducto.ACEITUNAS, 2.5);
        precios.put(TipoProducto.ACELGA, 1.0);
        precios.put(TipoProducto.ALGODON, 1.8);
        precios.put(TipoProducto.ARROZ, 0.60);
        precios.put(TipoProducto.BROCOLI, 2.0);
        precios.put(TipoProducto.CEBOLLA, 0.40);
        precios.put(TipoProducto.CIRUELOS, 0.80);
        precios.put(TipoProducto.COLIFLOR, 2.0);
        precios.put(TipoProducto.ESPINACAS, 1.80);
        precios.put(TipoProducto.LECHUGA, 0.60);
        precios.put(TipoProducto.MAIZ, 0.80);
        precios.put(TipoProducto.MANZANA, 0.65);
        precios.put(TipoProducto.MELOCOTONEROS, 0.90);
        precios.put(TipoProducto.NARANJOS, 0.68);
        precios.put(TipoProducto.TOMATE, 0.85);
        precios.put(TipoProducto.TRIGO, 0.40);
        precios.put(TipoProducto.ZANAHORIA, 0.10);
        precios.put(TipoProducto.PERA, 0.60);
    }
    /**
     * Obtiene si el precio del producto que se ajusta mas a la fecha requerida.
     * @return precio
     */
    public static double getPrecio(TipoProducto tipoProducto, LocalDate fecha) {
        double precio = precios.getOrDefault(tipoProducto, 0.0);

        for (Map<LocalDate, Map<TipoProducto, Double>> registroPrecios : preciosAnteriores) {
            if (registroPrecios.containsKey(fecha)) {
                Map<TipoProducto, Double> preciosFecha = registroPrecios.get(fecha);
                if (preciosFecha.containsKey(tipoProducto)) {
                    precio = preciosFecha.get(tipoProducto);
                    break;
                }
            }
        }
        return precio;
    }

    /**
     * Actualiza el precio de un tipo de producto
     * 
     */
    public static void setPrecio(double precio, TipoProducto tipoProducto, LocalDate fecha) {
        Map<TipoProducto, Double> preciosAnterioresMap = new HashMap<>(precios);
        Map<LocalDate, Map<TipoProducto, Double>> registroPrecios = new HashMap<>();
        registroPrecios.put(fecha, preciosAnterioresMap);
        preciosAnteriores.add(registroPrecios);
        precios.put(tipoProducto, precio);
    }

    public static List<Map<LocalDate, Map<TipoProducto, Double>>> getPreciosAnteriores(TipoProducto tipoProducto) {
        return preciosAnteriores;
    }
    /**
     * Obtiene si el precio del producto a sufrido cambios.
     * 
     * @return boolean
     */
    public static boolean productoTieneCambios(TipoProducto tipoProducto) {
        List<Map<LocalDate, Map<TipoProducto, Double>>> preciosAnteriores = TablaPrecios
                .getPreciosAnteriores(tipoProducto);
        if (preciosAnteriores.isEmpty()) {
            return false;
        }
        Map<LocalDate, Map<TipoProducto, Double>> preciosMap = preciosAnteriores.get(preciosAnteriores.size() - 1);
        if (!preciosMap.containsKey(LocalDate.now())) {
            return false;
        }
        double precioActual = TablaPrecios.getPrecio(tipoProducto, LocalDate.now());
        double precioAnterior = preciosMap.get(LocalDate.now()).get(tipoProducto);
        return precioActual != precioAnterior;
    }
}
