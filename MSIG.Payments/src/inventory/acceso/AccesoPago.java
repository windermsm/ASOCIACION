/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosPago;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Derwin Santa Cruz
 */
public class AccesoPago {
    
    private Mensaje mensaje = new Mensaje();
    
    public boolean insertarPago(ObjetosPago pago, String user) {
        boolean valor = false;
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_pago(" + pago.getNo_factura_pago() 
                    + ", '" + pago.getSerie_factura_pago() + "','" 
                    + pago.getNumero_doc_pago() + "', '" 
                    + pago.getTipo_doc_pago() + "',  '" 
                    + pago.getFecha_doc_pago() + "', " 
                    + pago.getMonto_doc_pago() + ", '" 
                    + pago.getCuenta_pago() + "', '" 
                    + pago.getMotivo_pago() + "', '" 
                    + pago.getEstado_pago() + "', '" + user + "') as result";
            mensaje.manipulacionExcepciones("informacion",acceso.ejecutarFuncion(sql));
            System.out.println("Ejecutando " + sql);
            valor = true;
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al guardar pago: " + error);
            valor = false;
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public boolean actualizarPago(ObjetosPago pago, String user) {
        String sql;
        boolean valor = false;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_pago(" + pago.getId_pago() 
                    + ", " + pago.getNo_factura_pago() 
                    + ", '" + pago.getSerie_factura_pago() 
                    + "', '" + pago.getNumero_doc_pago() 
                    + "', '" + pago.getTipo_doc_pago() 
                    + "', '" + pago.getFecha_doc_pago() 
                    + "', " + pago.getMonto_doc_pago() 
                    + ", '" + pago.getCuenta_pago() 
                    + "', '" + pago.getMotivo_pago() 
                    + "', '" + pago.getEstado_pago() 
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion",acceso.ejecutarFuncion(sql));
            valor = true;
        } catch (Exception error) {
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public ArrayList<ObjetosPago> listarPago() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from pago";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPago registro;
            while (tabla.next()) {
                registro = new ObjetosPago();
                registro.setId_pago(tabla.getInt("id_pago"));
                registro.setNo_factura_pago(tabla.getInt("no_factura_pago"));
                registro.setSerie_factura_pago(tabla.getString("serie_factura_pago"));
                registro.setNumero_doc_pago(tabla.getString("numero_doc_pago"));
                registro.setTipo_doc_pago(tabla.getString("tipo_doc_pago"));
                registro.setFecha_doc_pago(tabla.getString("fecha_doc_pago"));
                registro.setMonto_doc_pago(tabla.getFloat("monto_doc_pago"));
                registro.setCuenta_pago(tabla.getString("cuenta_pago"));
                registro.setMotivo_pago(tabla.getString("motivo_pago"));
                registro.setFecha_pago(tabla.getString("fecha_pago"));
                registro.setEstado_pago(tabla.getString("estado_pago"));
                lista.add(registro);
            }
        } catch (Exception error) {
            System.out.println("Error: " + error);
            return null;
        } finally {
            Acceso.desconectar();
        }
        return lista;
    }
    
    public ArrayList<ObjetosPago> buscarPago(String valor) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from pago where UPPER(motivo_pago) like '%" + valor + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPago registro;
            while (tabla.next()) {
                registro = new ObjetosPago();
                registro.setId_pago(tabla.getInt("id_pago"));
                registro.setNo_factura_pago(tabla.getInt("no_factura_pago"));
                registro.setSerie_factura_pago(tabla.getString("serie_factura_pago"));
                registro.setNumero_doc_pago(tabla.getString("numero_doc_pago"));
                registro.setTipo_doc_pago(tabla.getString("tipo_doc_pago"));
                registro.setFecha_doc_pago(tabla.getString("fecha_doc_pago"));
                registro.setMonto_doc_pago(tabla.getFloat("monto_doc_pago"));
                registro.setCuenta_pago(tabla.getString("cuenta_pago"));
                registro.setMotivo_pago(tabla.getString("motivo_pago"));
                registro.setFecha_pago(tabla.getString("fecha_pago"));
                registro.setEstado_pago(tabla.getString("estado_pago"));
                lista.add(registro);
            }
        } catch (Exception error) {
            System.out.println("Error: " + error);
            return null;
        } finally {
            Acceso.desconectar();
        }
        return lista;
    }

}
