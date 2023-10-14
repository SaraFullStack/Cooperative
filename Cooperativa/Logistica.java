/**
 * Clase abstracta que representa una entidad de logística. Esta clase sirve
 * como base para implementar diferentes tipos de logística.
 * @author (Sara Cubero) 
 * @version (1.0)
 */

import java.util.List;


public abstract class Logistica {

	private double coste;
	private double km;
	private double kg;

	/**
	 * Constructor de la clase Logistica.
	 *
	 * @param coste El coste de la logística.
	 * @param kg    Los kilogramos de carga de la logística.
	 * @param km    Los kilómetros recorridos en la logística.
	 */
	public Logistica(double coste, double kg, double km) {
		this.coste = coste;
		this.kg = kg;
		this.km = km;
	}

	/**
	 * Método estático para generar un envío de logística. Este método utiliza la
	 * factoría de logística para crear el envío.
	 *
	 * @param producto    El producto a enviar.
	 * @param kmCapital   Los kilómetros en ruta hacia la capital.
	 * @param kmLocalidad Los kilómetros en ruta hacia la localidad.
	 * @param cantidad    La cantidad de productos a enviar.
	 * @return Una lista de objetos de tipo Logistica que representan el envío.
	 */
	public static List<Logistica> generarEnvio(Producto producto, double kmCapital, double kmLocalidad, int cantidad) {
		return LogisticaFactory.crearLogistica(kmCapital, kmLocalidad, producto, cantidad);
	}

	/**
	 * Obtiene los kilómetros recorridos en la logística.
	 *
	 * @return Los kilómetros recorridos.
	 */
	public double getKm() {
		return km;
	}

	/**
	 * Obtiene los kilogramos de carga de la logística.
	 *
	 * @return Los kilogramos de carga.
	 */
	public double getKg() {
		return kg;
	}

	/**
	 * Obtiene el coste de la logística.
	 *
	 * @return El coste de la logística.
	 */
	public double getCoste() {
		return coste;
	}

	/**
	 * Obtiene el tipo de logística.
	 *
	 * @return El tipo de logística como una cadena de texto.
	 */
	public String getTipo() {
		return this.getClass().getSimpleName();
	}
}
