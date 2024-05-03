/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosAbona;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Derwin Santa Cruz
 */
public class AccesoAbona {
    
    private Mensaje mensaje = new Mensaje();
    
    public void insertaAbona(ObjetosAbona abona, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_abona(" + abona.getId_credito() + ", " + abona.getCantidad_cuotas_abona() 
                    + ", " + abona.getMonto_abona() + ", '" + abona.getFecha_factura_abona() 
                    + "', '" + abona.getSerie_factura_abona() + "', " + abona.getNumero_factura_abona() 
                    + ", '" + abona.getNit_cliente_abona() + "', '" + abona.getNombre_cliente_abona() 
                    + "', '" + abona.getDireccion_cliente_abona() + "', '" + abona.getMotivo_abona() 
                    + "', '" + abona.getEstado_abona() +  "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarAbona(ObjetosAbona abona, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_abona(" + abona.getId_abona() + " ," + abona.getId_credito() + ", " + abona.getCantidad_cuotas_abona() 
                    + ", " + abona.getMonto_abona() + ", '" + abona.getFecha_factura_abona() 
                    + "', '" + abona.getSerie_factura_abona() + "', " + abona.getNumero_factura_abona() 
                    + ", '" + abona.getNit_cliente_abona() + "', '" + abona.getNombre_cliente_abona() 
                    + "', '" + abona.getDireccion_cliente_abona() + "', '" + abona.getMotivo_abona() 
                    + "', '" + abona.getEstado_abona() +  "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosAbona> listarAbona( String id_credito) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from abona where id_credito = " + id_credito;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosAbona registro;
            while (tabla.next()) {
                registro = new ObjetosAbona();
                registro.setId_abona(tabla.getInt("id_abona"));
                registro.setId_credito(tabla.getInt("id_credito"));
                registro.setCantidad_cuotas_abona(tabla.getInt("cantidad_cuotas_abona"));
                registro.setMonto_abona(tabla.getDouble("monto_abona"));
                registro.setFecha_factura_abona(tabla.getString("fecha_factura_abona"));
                registro.setSerie_factura_abona(tabla.getString("serie_factura_abona"));
                registro.setNumero_factura_abona(tabla.getInt("numero_factura_abona"));
                registro.setNit_cliente_abona(tabla.getString("nit_cliente_abona"));
                registro.setNombre_cliente_abona(tabla.getString("nombre_cliente_abona"));
                registro.setDireccion_cliente_abona(tabla.getString("direccion_cliente_abona"));
                registro.setMotivo_abona(tabla.getString("motivo_abona"));
                registro.setEstado_abona(tabla.getString("estado_abona"));
                registro.setUsuario_abona(tabla.getString("usuario_abona"));
                registro.setFecha_abona(tabla.getString("fecha_abona"));
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
