/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetosLectura {
    
    private int id_lectura;
    private int id_suscripcion;
    private int anio_lectura;
    private int mes_lectura;
    private int anterior_lectura;
    private int nueva_lectura;
    private String estado_lectura;
    
    //variables para consultar las lecturas
    private int id_cliente;
    private String nombre_cliente;
    private String nombre_servicio;

    public int getAnio_lectura() {
        return anio_lectura;
    }

    public void setAnio_lectura(int anio_lectura) {
        this.anio_lectura = anio_lectura;
    }

    public int getAnterior_lectura() {
        return anterior_lectura;
    }

    public void setAnterior_lectura(int anterior_lectura) {
        this.anterior_lectura = anterior_lectura;
    }

    public String getEstado_lectura() {
        return estado_lectura;
    }

    public void setEstado_lectura(String estado_lectura) {
        this.estado_lectura = estado_lectura;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_lectura() {
        return id_lectura;
    }

    public void setId_lectura(int id_lectura) {
        this.id_lectura = id_lectura;
    }

    public int getId_suscripcion() {
        return id_suscripcion;
    }

    public void setId_suscripcion(int id_suscripcion) {
        this.id_suscripcion = id_suscripcion;
    }

    public int getMes_lectura() {
        return mes_lectura;
    }

    public void setMes_lectura(int mes_lectura) {
        this.mes_lectura = mes_lectura;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public int getNueva_lectura() {
        return nueva_lectura;
    }

    public void setNueva_lectura(int nueva_lectura) {
        this.nueva_lectura = nueva_lectura;
    }
    
    
}
