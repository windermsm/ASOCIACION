/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetosCobro {
    private int id_cobro;
    private int id_suscripcion;
    private int anio_cobro;
    private int mes_cobro;
    private String fecha_puntual_cobro;
    private String fecha_maxima_cobro;
    private int dias_atrazo_cobro;
    private String numero_contador_cobro;
    private double valor_cobro;
    private double valor_excedente;
    private double valor_penalizacion_cobro;
    private double acumulado_cobro;
    private double total_cobro;
    private String forma_pago_cobro;
    private String estado_cobro;
    private int factura_cobro;
    
    //campos del procedimiento
    private int id_cliente;
    private String nombre_cliente;
    private int id_servicio;
    private String nombre_servicio;

    public double getAcumulado_cobro() {
        return acumulado_cobro;
    }

    public void setAcumulado_cobro(double acumulado_cobro) {
        this.acumulado_cobro = acumulado_cobro;
    }

    public int getAnio_cobro() {
        return anio_cobro;
    }

    public void setAnio_cobro(int anio_cobro) {
        this.anio_cobro = anio_cobro;
    }

    public int getDias_atrazo_cobro() {
        return dias_atrazo_cobro;
    }

    public void setDias_atrazo_cobro(int dias_atrazo_cobro) {
        this.dias_atrazo_cobro = dias_atrazo_cobro;
    }

    public String getEstado_cobro() {
        return estado_cobro;
    }

    public void setEstado_cobro(String estado_cobro) {
        this.estado_cobro = estado_cobro;
    }

    public String getFecha_maxima_cobro() {
        return fecha_maxima_cobro;
    }

    public void setFecha_maxima_cobro(String fecha_maxima_cobro) {
        this.fecha_maxima_cobro = fecha_maxima_cobro;
    }

    public String getFecha_puntual_cobro() {
        return fecha_puntual_cobro;
    }

    public void setFecha_puntual_cobro(String fecha_puntual_cobro) {
        this.fecha_puntual_cobro = fecha_puntual_cobro;
    }

    public String getForma_pago_cobro() {
        return forma_pago_cobro;
    }

    public void setForma_pago_cobro(String forma_pago_cobro) {
        this.forma_pago_cobro = forma_pago_cobro;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(int id_cobro) {
        this.id_cobro = id_cobro;
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

    public int getMes_cobro() {
        return mes_cobro;
    }

    public void setMes_cobro(int mes_cobro) {
        this.mes_cobro = mes_cobro;
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

    public String getNumero_contador_cobro() {
        return numero_contador_cobro;
    }

    public void setNumero_contador_cobro(String numero_contador_cobro) {
        this.numero_contador_cobro = numero_contador_cobro;
    }

    public double getTotal_cobro() {
        return total_cobro;
    }

    public void setTotal_cobro(double total_cobro) {
        this.total_cobro = total_cobro;
    }

    public double getValor_cobro() {
        return valor_cobro;
    }

    public void setValor_cobro(double valor_cobro) {
        this.valor_cobro = valor_cobro;
    }

    public double getValor_excedente() {
        return valor_excedente;
    }

    public void setValor_excedente(double valor_excedente) {
        this.valor_excedente = valor_excedente;
    }

    public double getValor_penalizacion_cobro() {
        return valor_penalizacion_cobro;
    }

    public void setValor_penalizacion_cobro(double valor_penalizacion_cobro) {
        this.valor_penalizacion_cobro = valor_penalizacion_cobro;
    }

    public int getFactura_cobro() {
        return factura_cobro;
    }

    public void setFactura_cobro(int factura_cobro) {
        this.factura_cobro = factura_cobro;
    }

}
