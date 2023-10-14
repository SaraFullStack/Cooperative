/**
* Esta clase representa el límite máximo de hectáreas que un productor puede tener en la cooperativa empresarial.
* Esta clase es implementada como un patrón Singleton para garantizar que solo exista una instancia del límite de hectáreas.
* @author (Sara Cubero) 
* @version (1.0)
*/
public class LimiteHectareas {
	
	/**
	* Límite de hectáreas
	*/
	private double limite;
	/**
	* Instancia única de la clase
	*/
	private static LimiteHectareas instance = null;
	/**
	* Constante para el límite por defecto
	*/
	private final static double LIMITE_DEFAULT = 5;

	private LimiteHectareas(double limite) {
		this.limite = limite;
	}

	/*
	 * Método para obtener la instancia única de la clase
	 */
	public static LimiteHectareas getInstance() {
		if (instance == null) {
			instance = new LimiteHectareas(LIMITE_DEFAULT);
		}
		return instance;
	}

	/**
	 * Método para establecer un límite nuevo
	 * 
	 * @param limite (double)
	 */
	public void setLimite(double limite) {
		this.limite = limite;
	}

	/**
	 * Método para obtener el límite actual
	 * 
	 * @return limite (double)
	 */
	public double getLimite() {
		return this.limite;
	}
}