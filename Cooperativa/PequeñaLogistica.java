/**
 * Clase que representa la logística de tipo pequeña. Esta clase extiende la
 * clase abstracta Logistica.
 * @author (Sara Cubero) 
 * @version (1.0)
 */
public class PequeñaLogistica extends Logistica {

	/** Precio por kilómetro por kilogramo para la logística pequeña */
	private static final double PRECIO_KM_KG = 0.01;

	/**
	 * Constructor de la clase PequeñaLogistica.
	 * 
	 * @param coste El costo de la logística.
	 * @param kg    El peso en kilogramos.
	 * @param km    Los kilómetros recorridos.
	 */
	public PequeñaLogistica(double coste, double kg, double km) {
		super(coste, kg, km);
	}

	/**
	 * Obtiene el precio por kilómetro por kilogramo para la logística pequeña.
	 * 
	 * @return El precio por kilómetro por kilogramo.
	 */
	public static double getPrecioKmKg() {
		return PRECIO_KM_KG;
	}

}
