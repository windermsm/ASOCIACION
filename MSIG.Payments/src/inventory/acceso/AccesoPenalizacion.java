/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosPenalizacion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoPenalizacion {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarPenalizacion(ObjetosPenalizacion pena, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_penalizacion(" + pena.getId_servicio() 
                    + ", " + pena.getDias_atrazo_penalizacion() + ", " + pena.getTaza_penalizacion() 
                    + ", '" + pena.getEstado_penalizacion() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarPenalizacion(ObjetosPenalizacion pena, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_penalizacion(" + pena.getId_penalizacion() + ", " + pena.getId_servicio() 
                    + ", " + pena.getDias_atrazo_penalizacion() + ", " + pena.getTaza_penalizacion() 
                    + ", '" + pena.getEstado_penalizacion() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosPenalizacion> listarPenalizacionesActivas() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from penalizacion where estado_penalizacion = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPenalizacion registro;
            while (tabla.next()) {
                registro = new ObjetosPenalizacion();
                registro.setId_penalizacion(tabla.getInt("id_penalizacion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setDias_atrazo_penalizacion(tabla.getInt("dias_atrazo_penalizacion"));
                registro.setTaza_penalizacion(tabla.getInt("taza_penalizacion"));
                registro.setEstado_penalizacion(tabla.getString("estado_penalizacion"));
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
    
    public ArrayList<ObjetosPenalizacion> listarPenalizacion() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from penalizacion";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPenalizacion registro;
            while (tabla.next()) {
                registro = new ObjetosPenalizacion();
                registro.setId_penalizacion(tabla.getInt("id_penalizacion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setDias_atrazo_penalizacion(tabla.getInt("dias_atrazo_penalizacion"));
                registro.setTaza_penalizacion(tabla.getInt("taza_penalizacion"));
                registro.setEstado_penalizacion(tabla.getString("estado_penalizacion"));
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
    
    public ArrayList<ObjetosPenalizacion> mostrarPenalizacionesActivas() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* from penalizacion as a, servicio as b, empresa as c "
                + "where a.id_servicio = b.id_servicio and b.id_empresa = c.id_empresa "
                + " and b.estado_servicio = 'A' and c.estado_empresa = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPenalizacion registro;
            while (tabla.next()) {
                registro = new ObjetosPenalizacion();
                registro.setId_penalizacion(tabla.getInt("id_penalizacion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setDias_atrazo_penalizacion(tabla.getInt("dias_atrazo_penalizacion"));
                registro.setTaza_penalizacion(tabla.getInt("taza_penalizacion"));
                registro.setEstado_penalizacion(tabla.getString("estado_penalizacion"));
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
    
    public ArrayList<ObjetosPenalizacion> buscarPenalizacionPorServicio(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* from penalizacion as a, servicio as b, empresa as c "
                + "where a.id_servicio = b.id_servicio and b.id_empresa = c.id_empresa "
                + "and b.estado_servicio = 'A' and c.estado_empresa = 'A' "
                + "and upper(b.nombre_servicio) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPenalizacion registro;
            while (tabla.next()) {
                registro = new ObjetosPenalizacion();
                registro.setId_penalizacion(tabla.getInt("id_penalizacion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setDias_atrazo_penalizacion(tabla.getInt("dias_atrazo_penalizacion"));
                registro.setTaza_penalizacion(tabla.getInt("taza_penalizacion"));
                registro.setEstado_penalizacion(tabla.getString("estado_penalizacion"));
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
    
   
    public ArrayList<ObjetosPenalizacion> buscarPenalizacionPorId(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from penalizacion where id_penalizacion = " + x ;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosPenalizacion registro;
            while (tabla.next()) {
                registro = new ObjetosPenalizacion();
                registro.setId_penalizacion(tabla.getInt("id_penalizacion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setDias_atrazo_penalizacion(tabla.getInt("dias_atrazo_penalizacion"));
                registro.setTaza_penalizacion(tabla.getInt("taza_penalizacion"));
                registro.setEstado_penalizacion(tabla.getString("estado_penalizacion"));
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