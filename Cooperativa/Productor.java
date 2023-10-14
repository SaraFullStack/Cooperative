 

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Productor {
    /*Constante para imprmir*/
    private static final String[] NOMBRES_ATRIBUTOS = { "id", "nombre", "hectareas" };

    private UUID id;
    private String nombre;
    private double hectareas;
    private List<Producto> productos;
    
    /**
    * Constructor de la clase Productor.
    * 
    * @param nombre
    * @param hectareas
    * @param productos
    */
    public Productor(String nombre, double hectareas, List<Producto> productos) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.hectareas = hectareas;
        this.productos = productos;
    }

    /**
    * 
    */
    public static Productor crearProductor() {
        String nombre = Utilidades.getString("Nombre de productor: ");
        double hectareas = Utilidades.getDouble("Hectareas totales de productor: ", 0.0);
        Productor productor = ProductorFactory.crearProductor(nombre, hectareas, Producto.crearProductos(hectareas));
        imprimirElemento(productor);
        Producto.imprimirTodos(productor.productos);
        return productor;
    }
    /**
     * Crea un productor federado a partir de una lista de productores, un nombre de producto,
     * una lista de nombres de productores y un indicador de aleatoriedad.
     *
     * @param productores          la lista de productores disponibles.
     * @param producto             el nombre del producto deseado.
     * @param productoresNombres   la lista de nombres de productores.
     * @param random               un indicador de aleatoriedad.
     * @return el objeto ProductorFederado creado.
     * @throws IllegalArgumentException si los productores o el producto proporcionados no son correctos.
     */
    public static ProductorFederado crearProductorFederado(List<Productor> productores, String producto, List<String> productoresNombres, boolean random) {
        List<ProductorPequeño> productoresPequeños = new ArrayList<ProductorPequeño>();
        List<ProductorFederado> productoresFederados = new ArrayList<ProductorFederado>();
        Producto productoElegido = null;
        for (Productor productorItem : productores) {
            if (productorItem instanceof ProductorPequeño) {
                if (productoresNombres.contains(productorItem.getNombre())) {
                    for (Producto productoItem : productorItem.productos) {
                        if (productoItem.getTipoProducto().getNombre().equals(producto)) {
                            productoresPequeños.add((ProductorPequeño) productorItem);
                            productoElegido = productoItem;
                        }
                    }
                }
            } 
            else if (productorItem instanceof ProductorFederado) {
                productoresFederados.add((ProductorFederado) productorItem);
            }
        }

        if (productoElegido == null || productoresPequeños.size() == 0) {
            throw new IllegalArgumentException("Los productores o producto aportados no son correctos");
        }

        return ProductorFederado.setProductorFederado(productoresPequeños, productoresFederados, productoElegido, random);
    }
    /**
     * Imprime los detalles de todos los productores en la lista dada.
     *
     * @param listaProductores   la lista de productores a imprimir.
     */
    public static void imprimirTodos(List<Productor> listaProductores) {
        for (Productor productor : listaProductores) {
            imprimirElemento(productor);
            System.out.println("Tipo de productor: "+ tipoProductor(productor)+ "\n");
            System.out.println("Sus productos:");
            Producto.imprimirTodos(productor.productos);
            System.out.println("");
        }
    }
    /**
     * Imprime los detalles del productor dado.
     *
     * @param productor   el objeto Productor a imprimir.
     */
    public static void imprimirElemento(Productor productor) {
        Utilidades.imprimirObjeto(productor, NOMBRES_ATRIBUTOS);
    }
    /**
     * Borra un productor de la lista de productores.
     *
     * @param productores   la lista de productores.
     * @return la lista de productores actualizada después de eliminar el productor.
     */
    public static List<Productor> borrarProductor(List<Productor> productores) {
        for (int i = 0; i < productores.size(); i++) {
            System.out.println((i + 1) + "- " + productores.get(i).getNombre());
        }

        int indice = Utilidades.getInt("¿Qué productor desea borrar? (Ingrese el número)") - 1;

        if (indice >= 0 && indice < productores.size()) {
            Productor productorEliminado = productores.remove(indice);
            System.out.println("Productor " + productorEliminado.getNombre() + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró el productor.");
        }

        return productores;
    }
    /**
     * Devuelve el tipo de productor del productor dado.
     *
     * @param productor   el objeto Productor para el cual se desea obtener el tipo.
     * @return el tipo de productor.
     */
    public static String tipoProductor(Productor productor) {
        if (productor instanceof ProductorGrande) {
            return TipoProductor.GRANDE;
        } else if (productor instanceof ProductorPequeño) {
            return TipoProductor.PEQUEÑO;
        }
        return TipoProductor.FEDERADO;
    }
    /**
     * Obtiene el número total de hectáreas del productor.
     *
     * @return el número total de hectáreas del productor.
     */
    public String getNombre() {
        return nombre;
    }

    public double getHectareas() {
        return hectareas;
    }
    /**
     * Obtiene el ID del productor.
     *
     * @return el ID del productor.
     */
    public UUID getId() {
        return id;
    }
    /**
     * Obtiene la lista de productos del productor.
     *
     * @return la lista de productos del productor.
     */
    public List<Producto> getProductos() {
        return productos;
    }
    /**
     * Establece el nombre del productor.
     *
     * @param nombre   el nuevo nombre del productor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
