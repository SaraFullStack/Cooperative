/**
 * Clase que representa una logística de gran tamaño para el transporte de productos.
 * Hereda de la clase Logistica.
 * @author (Sara Cubero) 
 * @version (1.0)
 */
public class GranLogistica extends Logistica {

    private static final double PRECIO_KM = 0.05;

    /**
     * Constructor de la clase GranLogistica.
     * @param coste el coste de la logística
     * @param kg el peso en kg de la logística
     * @param km la distancia en km de la logística
     * @param producto el producto asociado a la logística
     */
    public GranLogistica(double coste, double kg, double km) {
        super(coste, kg, km);
    }

    /**
     * Obtiene el precio por kilómetro de la logística de gran tamaño.
     * @return el precio por kilómetro
     */
    public static double getPrecioKm() {
        return PRECIO_KM;
    }
}
