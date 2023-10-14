/**
 * La clase Menus contiene arrays de strings que representan distintos menús para el usuario.
 * @author (Sara Cubero) 
 * @version (1.0)
 */
public class Menus {

    /**
     * Array de strings que representa el menú principal de la aplicación.
     */
    protected static final String[] MENU_PRINCIPAL = {
        "COOPERATIVA EMPRESARIAL",
        "¿Qué tipo de gestión desea realizar?",
        "1. Productores",
        "2. Distribuidores y Consumidores Finales",
        "3. Estadísticas",
        "0. Salir",
    };
    
    /**
     * Array de strings que representa el menú de gestión de productores.
     */
    protected static final String[] MENU_PRODUCTORES = {
        "PRODUCTORES",
        "1. Crear Productor",
        "2. Imprimir Productores",
        "3. Borrar Productor",
        "4. Límite en Hectáreas",
        "5. Actualizar Precios",
        "0. Volver",
    };
    
    /**
     * Array de strings que representa el menú de creación de productores.
     */
    protected static final String[] MENU_CREAR_PRODUCTORES = {
        "CREAR PRODUCTOR",
        "1. Productor",
        "2. Productor federado",
        "0. Volver",
    };
    
    /**
     * Array de strings que representa el menú de gestión de distribuidores y consumidores finales.
     */
    protected static final String[] MENU_COMPRADORES = {
        "DISTRIBUIDORES Y CONSUMIDORES FINALES",
        "1. Imprimir Distribuidores",
        "2. Imprimir Consumidores finales",
        "3. Imprimir pedidos",
        "4. Crear pedido",
        "5. Forzar entregas",
        "0. Volver",
    };

    
    /**
     * Array de strings que representa el menú de gestión de estadística.
     */
    protected static final String[] MENU_ESTADISTICA = {
        "ESTADÍSTICAS",
        "1. Ventas totales",
        "2. Importes productores",
        "3. Importes logística",
        "4. Beneficios",
        "5. Evolución precios",
        "0. Volver",
    };
}
