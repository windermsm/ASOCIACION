/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosEmpresa;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoEmpresa {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarEmpresa(ObjetosEmpresa empresa, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_empresa('" + empresa.getNombre_empresa() 
                    + "', '" + empresa.getEstado_empresa() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarCategoria(ObjetosEmpresa empresa, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_empresa(" + empresa.getId_empresa() 
                    + ", '" + empresa.getNombre_empresa() + "', '" + empresa.getEstado_empresa() 
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosEmpresa> listarEmpresa() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from empresa";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosEmpresa registro;
            while (tabla.next()) {
                registro = new ObjetosEmpresa();
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_empresa(tabla.getString("nombre_empresa"));
                registro.setEstado_empresa(tabla.getString("estado_empresa"));
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
    
    public ArrayList<ObjetosEmpresa> listarEmpresasActivas() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from empresa where estado_empresa = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosEmpresa registro;
            while (tabla.next()) {
                registro = new ObjetosEmpresa();
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_empresa(tabla.getString("nombre_empresa"));
                registro.setEstado_empresa(tabla.getString("estado_empresa"));
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
    
    public ArrayList<ObjetosEmpresa> buscarEmpresa(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from empresa where upper(nombre_empresa) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosEmpresa registro;
            while (tabla.next()) {
                registro = new ObjetosEmpresa();
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_empresa(tabla.getString("nombre_empresa"));
                registro.setEstado_empresa(tabla.getString("estado_empresa"));
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
    
    public ArrayList<ObjetosEmpresa> buscarEmpresaPorNombre(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from empresa where upper(nombre_empresa) = '" + x + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosEmpresa registro;
            while (tabla.next()) {
                registro = new ObjetosEmpresa();
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_empresa(tabla.getString("nombre_empresa"));
                registro.setEstado_empresa(tabla.getString("estado_empresa"));
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
    
    public ArrayList<ObjetosEmpresa> buscarCategoriasPorId(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from empresa where id_empresa = " + x ;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosEmpresa registro;
            while (tabla.next()) {
                registro = new ObjetosEmpresa();
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_empresa(tabla.getString("nombre_empresa"));
                registro.setEstado_empresa(tabla.getString("estado_empresa"));
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