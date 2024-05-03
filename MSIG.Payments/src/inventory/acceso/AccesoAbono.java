/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosAbono;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoAbono {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarAbono(ObjetosAbono abono, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_abono(" + abono.getId_cobro() +"," + abono.getMes_abono() 
                    + "," + abono.getAnio_abono() + ", " + abono.getPago_abono() 
                    + " , " + abono.getSaldo_abono() + " , " + abono.getFactura() + " ,'" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
  
}