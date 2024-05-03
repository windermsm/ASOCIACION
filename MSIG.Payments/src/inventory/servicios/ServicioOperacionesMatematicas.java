/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.servicios;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ServicioOperacionesMatematicas {
    
    public double redondear(double numero, int decimales){
        return Math.round(numero*Math.pow(10, decimales))/Math.pow(10, decimales);
    }
    
}
