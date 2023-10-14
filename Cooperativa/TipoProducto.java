/**
 * Clase para establecer productos
 * @author (Sara Cubero) 
 * @version (1.0)
 */  

public enum TipoProducto {
    ACEITE(1, "Aceite de oliva", false, 1000, 0.75),
    ACEITUNAS(2, "Aceitunas", true, 2000, 0.2),
    ACELGA(3, "Acelga", true, 2000, 0.3),
    ALGODON(4, "Algodón", false, 3000, 0.05),
    ARROZ(5, "Arroz", false, 1000, 0.1),
    BROCOLI(6, "Brócoli", true, 1500, 0.5),
    CEBOLLA(7, "Cebolla", true, 5000, 0.2),
    CIRUELOS(8, "Ciruelos", true, 2000, 0.1),
    COLIFLOR(9, "Coliflor", true, 4400, 0.7),
    ESPINACAS(10, "Espinacas", true, 3690, 0.2),
    LECHUGA(11, "Lechuga", true, 1200, 0.2),
    MAIZ(12, "Maíz", false, 9000, 0.15),
    MANZANA(13, "Manzana", true, 3300, 0.2),
    MELOCOTONEROS(14, "Melocotoneros", true, 1700, 0.1),
    NARANJOS(15, "Naranjos", true, 1800, 0.2),
    TOMATE(16, "Tomate", true, 2500, 0.1),
    TRIGO(17, "Trigo", false, 4000, 0.05),
    ZANAHORIA(18, "Zanahoria", true, 2000, 0.1),
    PERA(19, "Pera", true, 2200, 0.2);
    
    /*Constante para imprimir*/
    protected static final String[] NOMBRES_ATRIBUTOS = { "id", "nombre", "esPerecedero", "rendimientoHectarea", "peso" };

    private final int id;
    private final String nombre;
    private final boolean esPerecedero;
    private final double rendimientoHectarea;
    private final double peso;

    /**
     * Constructor de la clase TipoProducto.
     *
     * @param id 
     * @param nombre
     * @param esPerecedero
     * @param rendimientoHectarea
     * @param peso
     */
    TipoProducto(int id, String nombre, boolean esPerecedero, double rendimientoHectarea, double peso) {
        this.id = id;
        this.nombre = nombre;
        this.esPerecedero = esPerecedero;
        this.rendimientoHectarea = rendimientoHectarea;
        this.peso = peso;
    }
    /**
     * Devuelve el id del tipo de producto
     *
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Devuelve el nombre del tipo de producto
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Devuelve si es perecedero el tipo de producto
     *
     * @return esPerecedero
     */
    public boolean getEsPerecedero() {
        return esPerecedero;
    }
    /**
     * Devuelve el rendimiento por hectarea de un tipo de producto.
     *
     * @return rendimientoHectarea
     */
    public double getRendimientoHectarea() {
        return rendimientoHectarea;
    }
    /**
     * Devuelve el peso de un producto.
     *
     * @return peso.
     */
    public double getPeso() {
        return peso;
    }
}
