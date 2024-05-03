/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosCredito;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Derwin Santa Cruz
 */
public class AccesoCredito {
    
    private Mensaje mensaje = new Mensaje();
    
    public void insertarCredito(ObjetosCredito credito, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_credito(" + credito.getId_suscripcion() + ", "
                    + "'" + credito.getDescripcion_credito() + "', " + credito.getMonto_credito() 
                    + ", " + credito.getCantidad_cuotas_credito() + ", " + credito.getMonto_cuota_credito() 
                    + ", " + credito.getSaldo_credito() + ", " + credito.getAbonado_credito() 
                    + ", '" + credito.getEstado_credito() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosCredito> listarCredito(String suscripcion) {
        
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = ""; 
        
        if(suscripcion.equals("")) {
            sql = "select  a.*, c.nombre_cliente, b.direccion_suscripcion from credito as a, suscripcion as b, cliente as c where   a.id_suscripcion = b.id_suscripcion and b.id_cliente = c.id_cliente and estado_credito = 'Pendiente'";
        } else {
            sql = "select  a.*, c.nombre_cliente, b.direccion_suscripcion from credito as a, suscripcion as b, cliente as c where   a.id_suscripcion = b.id_suscripcion and b.id_cliente = c.id_cliente and estado_credito = 'Pendiente' and upper(c.nombre_cliente) like upper('%" + suscripcion + "%')";
        }
        
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCredito registro;
            while (tabla.next()) {
                registro = new ObjetosCredito();
                registro.setId_credito(tabla.getInt("id_credito"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setDireccion_suscripcion(tabla.getString("direccion_suscripcion"));
                registro.setDescripcion_credito(tabla.getString("descripcion_credito"));
                registro.setMonto_credito(tabla.getDouble("monto_credito"));
                registro.setCantidad_cuotas_credito(tabla.getInt("cantidad_cuotas_credito"));
                registro.setMonto_cuota_credito(tabla.getDouble("monto_cuota_credito"));
                registro.setSaldo_credito(tabla.getDouble("saldo_credito"));
                registro.setAbonado_credito(tabla.getDouble("abonado_credito"));
                registro.setEstado_credito(tabla.getString("estado_credito"));
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
    
    
    public int cuotasRecibidas(String id_credito) {
        
        int resultado = 0;
        Conexion Acceso = new Conexion();
        String sql = "select ifnull(sum(cantidad_cuotas_abona),0) total from abona where estado_abona = 'Activo' and id_credito = " + id_credito; 
        
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            while (tabla.next()) {
                resultado = tabla.getInt("total");
            }
        } catch (Exception error) {
            System.out.println("Error: " + error);
        } finally {
            Acceso.desconectar();
        }
        return resultado;
    }
    
}
