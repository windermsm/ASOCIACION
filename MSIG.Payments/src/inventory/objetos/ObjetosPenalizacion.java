/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetosPenalizacion {
    
    private int id_penalizacion;
    private int id_servicio;
    private int dias_atrazo_penalizacion;
    private int taza_penalizacion;
    private String estado_penalizacion;

    public int getDias_atrazo_penalizacion() {
        return dias_atrazo_penalizacion;
    }

    public void setDias_atrazo_penalizacion(int dias_atrazo_penalizacion) {
        this.dias_atrazo_penalizacion = dias_atrazo_penalizacion;
    }

    public String getEstado_penalizacion() {
        return estado_penalizacion;
    }

    public void setEstado_penalizacion(String estado_penalizacion) {
        this.estado_penalizacion = estado_penalizacion;
    }

    public int getId_penalizacion() {
        return id_penalizacion;
    }

    public void setId_penalizacion(int id_penalizacion) {
        this.id_penalizacion = id_penalizacion;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getTaza_penalizacion() {
        return taza_penalizacion;
    }

    public void setTaza_penalizacion(int taza_penalizacion) {
        this.taza_penalizacion = taza_penalizacion;
    }
    
}
