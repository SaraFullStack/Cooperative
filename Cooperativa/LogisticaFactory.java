/**
 * Clase que se encarga de crear objetos de logística según los parámetros
 * proporcionados.
 * @author (Sara Cubero) 
 * @version (1.0)
 */ 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class LogisticaFactory {

	/* Constantes valores peso y kms */
	private static final int KMS_MAXIMOS = 100;
	private static final int KMS_TRAMO = 50;
	private static final int PESO_MAXIMO = 1000;

	/**
	 * Crea una lista de objetos de logística según los parámetros especificados.
	 *
	 * @param kmCapital   Los kilómetros en ruta hacia la capital.
	 * @param kmLocalidad Los kilómetros en ruta hacia la localidad.
	 * @param producto    El producto a enviar.
	 * @param cantidad    La cantidad de productos a enviar.
	 * @return Una lista de objetos de tipo Logistica que representan el envío.
	 */
	public static List<Logistica> crearLogistica(double kmCapital, double kmLocalidad, Producto producto,
			int cantidad) {
		List<Logistica> logisticas = new ArrayList<Logistica>();

		double kmTotales = kmCapital + kmLocalidad;
		double pesoTotal = cantidad * producto.getTipoProducto().getPeso();
		if (kmTotales <= KMS_MAXIMOS) {
			while (pesoTotal > PESO_MAXIMO) {
				logisticas.add(new LogisticaPropia(PESO_MAXIMO, 0));
				pesoTotal -= PESO_MAXIMO;
			}

			if (pesoTotal > 0) {
				logisticas.add(new LogisticaPropia(pesoTotal, 0));
			}

			logisticas.add(new LogisticaPropia(0, kmTotales));
		} else {
			logisticas = calcularLogisticaConCoste(kmCapital, kmLocalidad, producto, pesoTotal);
		}

		return logisticas;
	}

	/**
	 * Calcula la logística con costo y devuelve una lista de objetos de logística.
	 *
	 * @param kmCapital   Los kilómetros en ruta hacia la capital.
	 * @param kmLocalidad Los kilómetros en ruta hacia la localidad.
	 * @param producto    El producto a enviar.
	 * @param pesoTotal   El peso total de los productos.
	 * @return La lista de objetos de logística.
	 */
	private static List<Logistica> calcularLogisticaConCoste(double kmCapital, double kmLocalidad, Producto producto,
			double pesoTotal) {

		List<Logistica> logisticas = new ArrayList<Logistica>();

		while (kmLocalidad > KMS_MAXIMOS) {
			kmLocalidad -= KMS_MAXIMOS;
			kmCapital += KMS_MAXIMOS;
		}

		logisticas.add(
				new PequeñaLogistica(calculaCoste(kmLocalidad, producto, false, pesoTotal), pesoTotal, kmLocalidad));

		if (producto.getTipoProducto().getEsPerecedero()) {
			logisticas = calcularLogisticaPorKg(logisticas, kmCapital, producto, pesoTotal);
		} else {
			while (kmCapital > KMS_TRAMO) {
				logisticas = calcularLogisticaPorKg(logisticas, KMS_TRAMO, producto, pesoTotal);
				kmCapital -= KMS_TRAMO;
			}

			if (kmCapital > 0) {
				logisticas.add(new PequeñaLogistica(calculaCoste(kmCapital, producto, false, pesoTotal), pesoTotal,
						kmCapital));
			}
		}
		return logisticas;
	}

	/**
	 * Calcula la logística por kilogramo y agrega los objetos de logística a la
	 * lista existente.
	 *
	 * @param logisticas La lista de objetos de logística existente.
	 * @param kmCapital  Los kilómetros en ruta hacia la capital.
	 * @param producto   El producto a enviar.
	 * @param pesoTotal  El peso total de los productos.
	 * @return La lista de objetos de logística actualizada.
	 */
	private static List<Logistica> calcularLogisticaPorKg(List<Logistica> logisticas, double kmCapital,
			Producto producto, double pesoTotal) {
		while (pesoTotal > PESO_MAXIMO) {
			pesoTotal -= PESO_MAXIMO;
			logisticas.add(new GranLogistica(calculaCoste(kmCapital, producto, true, PESO_MAXIMO), PESO_MAXIMO, 0));
		}
		if (pesoTotal > 0) {
			logisticas.add(new GranLogistica(calculaCoste(kmCapital, producto, true, pesoTotal), pesoTotal, 0));
		}

		logisticas.add(new GranLogistica(calculaCoste(kmCapital, producto, true, 0), 0, kmCapital));

		return logisticas;
	}

	/**
	 * Calcula el costo de una operación de logística en función de los kilómetros,
	 * el producto y la cantidad.
	 *
	 * @param km            Los kilómetros en ruta.
	 * @param producto      El producto a enviar.
	 * @param granLogistica Indica si es una operación de logística de gran tamaño.
	 * @param cantidad      La cantidad de productos a enviar.
	 * @return El costo de la operación de logística.
	 */
	private static double calculaCoste(double km, Producto producto, boolean granLogistica, double cantidad) {
		double costo;
		boolean puedeDescuento = false;
		if (!granLogistica) {
			costo = cantidad * km * PequeñaLogistica.getPrecioKmKg();
		} else {
			if (cantidad != 0) {
				costo = 0.5 * producto.getPrecio(LocalDate.now()) * cantidad;
				puedeDescuento = true;
			} else {
				costo = km * 0.05 * GranLogistica.getPrecioKm();
			}
		}

		if (puedeDescuento) {
			double descuentoAcumulado = 0.0;
			for (Entry<Double, Double> entry : Oferta.OFERTAS_POR_CANTIDAD_DESCUENTO.entrySet()) {
				double cantidadOferta = entry.getKey();
				double descuento = entry.getValue();
				if (cantidad >= cantidadOferta && descuentoAcumulado == 0.0) {
					costo -= costo * descuento;
					descuentoAcumulado = descuento;
				}
			}
		}
		return costo;
	}

}