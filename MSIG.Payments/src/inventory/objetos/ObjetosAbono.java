/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetosAbono {
    int id_abono;
    int id_cobro;
    int mes_abono;
    int anio_abono;
    String fecha_abono;
    double pago_abono;
    double saldo_abono;
    String factura;

    public int getAnio_abono() {
        return anio_abono;
    }

    public void setAnio_abono(int anio_abono) {
        this.anio_abono = anio_abono;
    }

    public String getFecha_abono() {
        return fecha_abono;
    }

    public void setFecha_abono(String fecha_abono) {
        this.fecha_abono = fecha_abono;
    }

    public int getId_abono() {
        return id_abono;
    }

    public void setId_abono(int id_abono) {
        this.id_abono = id_abono;
    }

    public int getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(int id_cobro) {
        this.id_cobro = id_cobro;
    }

    public int getMes_abono() {
        return mes_abono;
    }

    public void setMes_abono(int mes_abono) {
        this.mes_abono = mes_abono;
    }

    public double getPago_abono() {
        return pago_abono;
    }

    public void setPago_abono(double pago_abono) {
        this.pago_abono = pago_abono;
    }

    public double getSaldo_abono() {
        return saldo_abono;
    }

    public void setSaldo_abono(double saldo_abono) {
        this.saldo_abono = saldo_abono;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
}
