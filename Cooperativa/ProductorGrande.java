/**
* Clase que representa un Productor grande.
* Extiende la clase abstracta Productor.
* @author (Sara Cubero) 
* @version (1.0)
*/
import java.util.List;

public class ProductorGrande extends Productor
{
     /**
     * Constructor de la clase ProductorGrande.
     *
     * @param nombre     El nombre del Productor.
     * @param hectareas  El número de hectáreas del Productor.
     * @param productos  La lista de productos del Productor.
     */
	public ProductorGrande(String nombre, double hectareas, List<Producto> productos) {
		super(nombre, hectareas,productos);
	}
    
}
