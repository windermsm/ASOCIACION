/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosCategoria;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoCategoria {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarCategoria(ObjetosCategoria categoria, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_categoria('" + categoria.getNombre_categoria() 
                    + "','" + categoria.getEstado_categoria() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarCategoria(ObjetosCategoria categoria, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_categoria(" + categoria.getId_categoria() 
                    + ", '" + categoria.getNombre_categoria() + "', '" + categoria.getEstado_categoria() 
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosCategoria> listarCategorias() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from categoria";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCategoria registro;
            while (tabla.next()) {
                registro = new ObjetosCategoria();
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setNombre_categoria(tabla.getString("nombre_categoria"));
                registro.setEstado_categoria(tabla.getString("estado_categoria"));
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
    
    public ArrayList<ObjetosCategoria> listarCategoriasActivas() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from categoria where estado_categoria = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCategoria registro;
            while (tabla.next()) {
                registro = new ObjetosCategoria();
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setNombre_categoria(tabla.getString("nombre_categoria"));
                registro.setEstado_categoria(tabla.getString("estado_categoria"));
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
    
    public ArrayList<ObjetosCategoria> buscarCategorias(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from categoria where upper(nombre_categoria) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCategoria registro;
            while (tabla.next()) {
                registro = new ObjetosCategoria();
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setNombre_categoria(tabla.getString("nombre_categoria"));
                registro.setEstado_categoria(tabla.getString("estado_categoria"));
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
    
    public ArrayList<ObjetosCategoria> buscarCategoriasPorNombre(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from categoria where upper(nombre_categoria) = '" + x + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCategoria registro;
            while (tabla.next()) {
                registro = new ObjetosCategoria();
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setNombre_categoria(tabla.getString("nombre_categoria"));
                registro.setEstado_categoria(tabla.getString("estado_categoria"));
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
    
    public ArrayList<ObjetosCategoria> buscarCategoriasPorId(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from categoria where id_categoria = " + x ;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCategoria registro;
            while (tabla.next()) {
                registro = new ObjetosCategoria();
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setNombre_categoria(tabla.getString("nombre_categoria"));
                registro.setEstado_categoria(tabla.getString("estado_categoria"));
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