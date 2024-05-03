/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class ObjetosProducto {
    
    private int id_servicio;
    private int id_categoria;
    private int id_empresa;
    private String nombre_servicio;
    private String descripcion_servicio;
    private double costo_servicio;
    private double precio_servicio;
    private int cantidad_servicio;
    private double precio_excedente_servicio;
    private int dias_credito_puntual_servicio;
    private int dias_credito_maximo_servicio;
    private String estado_servicio;

    public int getCantidad_servicio() {
        return cantidad_servicio;
    }

    public void setCantidad_servicio(int cantidad_servicio) {
        this.cantidad_servicio = cantidad_servicio;
    }

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public int getDias_credito_maximo_servicio() {
        return dias_credito_maximo_servicio;
    }

    public void setDias_credito_maximo_servicio(int dias_credito_maximo_servicio) {
        this.dias_credito_maximo_servicio = dias_credito_maximo_servicio;
    }

    public int getDias_credito_puntual_servicio() {
        return dias_credito_puntual_servicio;
    }

    public void setDias_credito_puntual_servicio(int dias_credito_puntual_servicio) {
        this.dias_credito_puntual_servicio = dias_credito_puntual_servicio;
    }

    public String getEstado_servicio() {
        return estado_servicio;
    }

    public void setEstado_servicio(String estado_servicio) {
        this.estado_servicio = estado_servicio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public double getPrecio_excedente_servicio() {
        return precio_excedente_servicio;
    }

    public void setPrecio_excedente_servicio(double precio_excedente_servicio) {
        this.precio_excedente_servicio = precio_excedente_servicio;
    }

    public double getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(double precio_servicio) {
        this.precio_servicio = precio_servicio;
    }
}
