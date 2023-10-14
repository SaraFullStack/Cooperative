/**
 * Fábrica para crear objetos Comprador. Proporciona un método estático para
 * crear diferentes tipos de compradores según el tipo especificado.
 * @author (Sara Cubero) 
 * @version (1.0)
 */

public class CompradorFactory {
	/**
	 * Crea un objeto Comprador según el tipo especificado.
	 * 
	 * @param tipo   tipo de comprador (DISTRIBUIDOR o CONSUMIDOR_FINAL).
	 * @param nombre nombre del comprador.
	 * @param pedido pedido asociado al comprador.
	 * @return un objeto Comprador creado según el tipo especificado.
	 */
	public static Comprador crearComprador(String tipo, String nombre, Pedido pedido) {
		if (tipo.equalsIgnoreCase(TipoComprador.DISTRIBUIDOR)) {
			return new Distribuidor(nombre, pedido);
		} else if (tipo.equalsIgnoreCase(TipoComprador.CONSUMIDOR_FINAL)) {
			return new ConsumidorFinal(nombre, pedido);
		} else {
			return null;
		}
	}
}
