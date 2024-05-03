/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetosCredito {
    
    private int id_credito;
    private int id_suscripcion;
    private String nombre_cliente;
    private String direccion_suscripcion;
    private String descripcion_credito;
    private double monto_credito;
    private int cantidad_cuotas_credito;
    private double monto_cuota_credito;
    private double saldo_credito;
    private double abonado_credito;
    private String estado_credito;

    public double getAbonado_credito() {
        return abonado_credito;
    }

    public void setAbonado_credito(double abonado_credito) {
        this.abonado_credito = abonado_credito;
    }

    public int getCantidad_cuotas_credito() {
        return cantidad_cuotas_credito;
    }

    public void setCantidad_cuotas_credito(int cantidad_cuotas_credito) {
        this.cantidad_cuotas_credito = cantidad_cuotas_credito;
    }

    public String getDescripcion_credito() {
        return descripcion_credito;
    }

    public void setDescripcion_credito(String descripcion_credito) {
        this.descripcion_credito = descripcion_credito;
    }

    public String getDireccion_suscripcion() {
        return direccion_suscripcion;
    }

    public void setDireccion_suscripcion(String direccion_suscripcion) {
        this.direccion_suscripcion = direccion_suscripcion;
    }

    public String getEstado_credito() {
        return estado_credito;
    }

    public void setEstado_credito(String estado_credito) {
        this.estado_credito = estado_credito;
    }

    public int getId_credito() {
        return id_credito;
    }

    public void setId_credito(int id_credito) {
        this.id_credito = id_credito;
    }

    public int getId_suscripcion() {
        return id_suscripcion;
    }

    public void setId_suscripcion(int id_suscripcion) {
        this.id_suscripcion = id_suscripcion;
    }

    public double getMonto_credito() {
        return monto_credito;
    }

    public void setMonto_credito(double monto_credito) {
        this.monto_credito = monto_credito;
    }

    public double getMonto_cuota_credito() {
        return monto_cuota_credito;
    }

    public void setMonto_cuota_credito(double monto_cuota_credito) {
        this.monto_cuota_credito = monto_cuota_credito;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public double getSaldo_credito() {
        return saldo_credito;
    }

    public void setSaldo_credito(double saldo_credito) {
        this.saldo_credito = saldo_credito;
    }
    
}
