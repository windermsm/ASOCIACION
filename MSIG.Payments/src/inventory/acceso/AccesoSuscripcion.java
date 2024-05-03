/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosSuscripcion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoSuscripcion {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarSuscripcion(ObjetosSuscripcion suscripcion, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_suscripcion(" + suscripcion.getId_servicio() 
                    + ", " + suscripcion.getId_cliente() + ", '" + suscripcion.getContador_suscripcion() + "', '" + suscripcion.getFecha_inicio_suscripcion() 
                    + "', '" + suscripcion.getFecha_final_suscripcion() + "', '" + suscripcion.getDireccion_suscripcion() 
                    + "', '" + suscripcion.getEstado_suscripcion() + "', '" + user +"') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarSuscripcion(ObjetosSuscripcion suscripcion, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_suscripcion( " + suscripcion.getId_suscripcion() + " ," + suscripcion.getId_servicio() 
                    + ", " + suscripcion.getId_cliente() + ", '" + suscripcion.getContador_suscripcion() + "', '" + suscripcion.getFecha_inicio_suscripcion() 
                    + "', '" + suscripcion.getFecha_final_suscripcion() + "', '" + suscripcion.getDireccion_suscripcion() 
                    + "', '" + suscripcion.getEstado_suscripcion() + "', '" + user +"') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosSuscripcion> listarSuscripciones() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.*, b.nombre_cliente from suscripcion as a, cliente as b where a.id_cliente = b.id_cliente order by b.nombre_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosSuscripcion registro;
            while (tabla.next()) {
                registro = new ObjetosSuscripcion();
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setContador_suscripcion(tabla.getString("contador_suscripcion"));
                registro.setFecha_inicio_suscripcion(tabla.getString("fecha_inicio_suscripcion"));
                registro.setFecha_final_suscripcion(tabla.getString("fecha_final_suscripcion"));
                registro.setDireccion_suscripcion(tabla.getString("direccion_suscripcion"));
                registro.setEstado_suscripcion(tabla.getString("estado_suscripcion"));
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
    
    public ArrayList<ObjetosSuscripcion> listarSuscripcionesSinRepetir() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select distinct b.nombre_cliente as nombre from suscripcion as a, cliente as b where a.id_cliente = b.id_cliente order by b.nombre_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosSuscripcion registro;
            while (tabla.next()) {
                registro = new ObjetosSuscripcion();
                registro.setNombre_cliente(tabla.getString("nombre"));
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
    
    public ArrayList<ObjetosSuscripcion> mostrarSuscripcionesPorCliente(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* from suscripcion as a, cliente as b where a.id_cliente = b.id_cliente and upper(b.nombre_cliente) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosSuscripcion registro;
            while (tabla.next()) {
                registro = new ObjetosSuscripcion();
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setContador_suscripcion(tabla.getString("contador_suscripcion"));
                registro.setFecha_inicio_suscripcion(tabla.getString("fecha_inicio_suscripcion"));
                registro.setFecha_final_suscripcion(tabla.getString("fecha_final_suscripcion"));
                registro.setDireccion_suscripcion(tabla.getString("direccion_suscripcion"));
                registro.setEstado_suscripcion(tabla.getString("estado_suscripcion"));
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
    
    public ArrayList<ObjetosSuscripcion> buscarInformacionSuscripcion(String id_suscripcion) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* from suscripcion as a, cliente as b where a.id_cliente = b.id_cliente and a.id_suscripcion =" + id_suscripcion;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosSuscripcion registro;
            while (tabla.next()) {
                registro = new ObjetosSuscripcion();
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setContador_suscripcion(tabla.getString("contador_suscripcion"));
                registro.setFecha_inicio_suscripcion(tabla.getString("fecha_inicio_suscripcion"));
                registro.setFecha_final_suscripcion(tabla.getString("fecha_final_suscripcion"));
                registro.setDireccion_suscripcion(tabla.getString("direccion_suscripcion"));
                registro.setEstado_suscripcion(tabla.getString("estado_suscripcion"));
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
    
    public ArrayList<ObjetosSuscripcion> buscarIdSuscripcion(String id_servicio, String id_cliente) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* from suscripcion as a, cliente as b, servicio as c "
                + "where  a.id_cliente = b.id_cliente and a.id_servicio = c.id_servicio "
                + "and a.id_cliente = " + id_cliente + " and a.id_servicio = " + id_servicio;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosSuscripcion registro;
            while (tabla.next()) {
                registro = new ObjetosSuscripcion();
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setContador_suscripcion(tabla.getString("contador_suscripcion"));
                registro.setFecha_inicio_suscripcion(tabla.getString("fecha_inicio_suscripcion"));
                registro.setFecha_final_suscripcion(tabla.getString("fecha_final_suscripcion"));
                registro.setDireccion_suscripcion(tabla.getString("direccion_suscripcion"));
                registro.setEstado_suscripcion(tabla.getString("estado_suscripcion"));
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