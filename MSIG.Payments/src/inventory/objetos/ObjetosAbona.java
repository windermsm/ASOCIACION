/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetosAbona {
    
    private int id_abona;
    private int id_credito;
    private int cantidad_cuotas_abona;
    private double monto_abona;
    private String fecha_factura_abona;
    private String serie_factura_abona;
    private int numero_factura_abona;
    private String nit_cliente_abona;
    private String nombre_cliente_abona;
    private String direccion_cliente_abona;
    private String motivo_abona;
    private String estado_abona;
    private String usuario_abona;
    private String fecha_abona;

    public int getCantidad_cuotas_abona() {
        return cantidad_cuotas_abona;
    }

    public void setCantidad_cuotas_abona(int cantidad_cuotas_abona) {
        this.cantidad_cuotas_abona = cantidad_cuotas_abona;
    }

    public String getDireccion_cliente_abona() {
        return direccion_cliente_abona;
    }

    public void setDireccion_cliente_abona(String direccion_cliente_abona) {
        this.direccion_cliente_abona = direccion_cliente_abona;
    }

    public String getEstado_abona() {
        return estado_abona;
    }

    public void setEstado_abona(String estado_abona) {
        this.estado_abona = estado_abona;
    }

    public String getFecha_abona() {
        return fecha_abona;
    }

    public void setFecha_abona(String fecha_abona) {
        this.fecha_abona = fecha_abona;
    }

    public String getFecha_factura_abona() {
        return fecha_factura_abona;
    }

    public void setFecha_factura_abona(String fecha_factura_abona) {
        this.fecha_factura_abona = fecha_factura_abona;
    }

    public int getId_abona() {
        return id_abona;
    }

    public void setId_abona(int id_abona) {
        this.id_abona = id_abona;
    }

    public int getId_credito() {
        return id_credito;
    }

    public void setId_credito(int id_credito) {
        this.id_credito = id_credito;
    }

    public double getMonto_abona() {
        return monto_abona;
    }

    public void setMonto_abona(double monto_abona) {
        this.monto_abona = monto_abona;
    }

    public String getMotivo_abona() {
        return motivo_abona;
    }

    public void setMotivo_abona(String motivo_abona) {
        this.motivo_abona = motivo_abona;
    }

    public String getNit_cliente_abona() {
        return nit_cliente_abona;
    }

    public void setNit_cliente_abona(String nit_cliente_abona) {
        this.nit_cliente_abona = nit_cliente_abona;
    }

    public String getNombre_cliente_abona() {
        return nombre_cliente_abona;
    }

    public void setNombre_cliente_abona(String nombre_cliente_abona) {
        this.nombre_cliente_abona = nombre_cliente_abona;
    }

    public int getNumero_factura_abona() {
        return numero_factura_abona;
    }

    public void setNumero_factura_abona(int numero_factura_abona) {
        this.numero_factura_abona = numero_factura_abona;
    }

    public String getSerie_factura_abona() {
        return serie_factura_abona;
    }

    public void setSerie_factura_abona(String serie_factura_abona) {
        this.serie_factura_abona = serie_factura_abona;
    }

    public String getUsuario_abona() {
        return usuario_abona;
    }

    public void setUsuario_abona(String usuario_abona) {
        this.usuario_abona = usuario_abona;
    }
    
}
