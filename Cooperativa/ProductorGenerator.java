/**
* Clase que se encarga de generar Productores aleatorios.
* @author (Sara Cubero) 
* @version (1.0)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductorGenerator {
    private static final List<TipoProducto> PRODUCTOS = Arrays.asList(TipoProducto.values());
    private static final Random RAND = new Random();
    /**
     * Genera un Productor aleatorio.
     *
     * @param isSmall Indica si se debe generar un Productor pequeño.
     * @return El objeto Productor generado.
     */
    public static Productor generarProductorRandom(boolean isSmall) {
        String nombre = generarNombreRandom();
        double hectareas = isSmall ? 2 : generarHectareasRandom();
        List<Producto> productos = new ArrayList<>();
        TipoProducto producto = PRODUCTOS.get(RAND.nextInt(PRODUCTOS.size()));
        double cantidad = isSmall ? 2 : Math.max(0.1, Math.min(Math.round(RAND.nextDouble() * 10), hectareas));
        productos.add(new Producto(producto, cantidad));
        return ProductorFactory.crearProductor(nombre, hectareas, productos);
    }
    /**
     * Genera un nombre aleatorio para un Productor.
     *
     * @return El nombre generado.
     */
    private static String generarNombreRandom() {
    	String[] nombres = { "Juan", "Pedro", "Luis", "Ana", "María", "Lucía", "Miguel", "José", "Carla", "Elena",
				"Pablo", "Alberto" };
		String[] apellidos = { "García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Sánchez", "Ramírez",
				"Torres", "Flores", "Vargas", "Castillo" };

		int indexNombre = new Random().nextInt(nombres.length);
		int indexApellido = new Random().nextInt(apellidos.length);

		return nombres[indexNombre] + " " + apellidos[indexApellido];
    }
    /**
     * Genera un número aleatorio de hectáreas para un Productor.
     *
     * @return El número de hectáreas generado.
     */
    private static double generarHectareasRandom() {
        double hectareas = 0;
        while (hectareas == 0) {
            hectareas = Math.round(RAND.nextDouble() * 20);
        }
        return hectareas;
    }
}
