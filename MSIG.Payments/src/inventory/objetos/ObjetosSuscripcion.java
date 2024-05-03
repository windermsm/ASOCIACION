/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetosSuscripcion {
    
    private int id_suscripcion;
    private int id_servicio;
    private int id_cliente;
    private String nombre_cliente;
    private String contador_suscripcion;
    private String fecha_inicio_suscripcion;
    private String fecha_final_suscripcion;
    private String direccion_suscripcion;
    private String estado_suscripcion;

    public String getContador_suscripcion() {
        return contador_suscripcion;
    }

    public void setContador_suscripcion(String contador_suscripcion) {
        this.contador_suscripcion = contador_suscripcion;
    }

    public String getDireccion_suscripcion() {
        return direccion_suscripcion;
    }

    public void setDireccion_suscripcion(String direccion_suscripcion) {
        this.direccion_suscripcion = direccion_suscripcion;
    }

    public String getEstado_suscripcion() {
        return estado_suscripcion;
    }

    public void setEstado_suscripcion(String estado_suscripcion) {
        this.estado_suscripcion = estado_suscripcion;
    }

    public String getFecha_final_suscripcion() {
        return fecha_final_suscripcion;
    }

    public void setFecha_final_suscripcion(String fecha_final_suscripcion) {
        this.fecha_final_suscripcion = fecha_final_suscripcion;
    }

    public String getFecha_inicio_suscripcion() {
        return fecha_inicio_suscripcion;
    }

    public void setFecha_inicio_suscripcion(String fecha_inicio_suscripcion) {
        this.fecha_inicio_suscripcion = fecha_inicio_suscripcion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_suscripcion() {
        return id_suscripcion;
    }

    public void setId_suscripcion(int id_suscripcion) {
        this.id_suscripcion = id_suscripcion;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
    
}
