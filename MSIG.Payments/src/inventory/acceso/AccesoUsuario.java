/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoUsuario {

    Mensaje mensaje = new Mensaje();

    public void insertarUsuario(ObjetosUsuario usuario, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_usuario('" + usuario.getNombre_usuario() + "','" 
                    + usuario.getContrasenia_usuario() + "','" + usuario.getTipo_usuario() 
                    + "','" + usuario.getEstado_usuario() + "','" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarUsuario(ObjetosUsuario usuario, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select actualizar_usuario('" + usuario.getNombre_usuario() + "','" + usuario.getContrasenia_usuario() + "','" + usuario.getTipo_usuario() + "','" + usuario.getEstado_usuario() + "','" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosUsuario> ingresoUsuario(String nombre, String contrasenia) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from usuario where nombre_usuario = '" + nombre + "' and contrasenia_usuario = '" + contrasenia + "' and estado_usuario = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetosUsuario registro;
            while (result.next()) {
                registro = new ObjetosUsuario();
                registro.setNombre_usuario(result.getString("nombre_usuario"));
                registro.setContrasenia_usuario("contrasenia_usuario");
                registro.setTipo_usuario(result.getString("tipo_usuario"));
                registro.setEstado_usuario("estado_usuario");
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetosUsuario> listarUsuarios(){       
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from usuario";  
        try{
            System.out.println("Ejecutando: " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetosUsuario registros;
            while(tabla.next()){
                registros = new ObjetosUsuario();
                registros.setNombre_usuario(tabla.getString("nombre_usuario"));
                registros.setContrasenia_usuario(tabla.getString("contrasenia_usuario"));
                registros.setTipo_usuario(tabla.getString("tipo_usuario"));
                registros.setEstado_usuario(tabla.getString("estado_usuario"));
                lista.add(registros);
            }
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetosUsuario> buscarUsuario(String x){       
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from usuario where upper(nombre_usuario) like '%" + x + "%'";  
        try{
            System.out.println("Ejecutando: " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetosUsuario registros;
            while(tabla.next()){
                registros = new ObjetosUsuario();
                registros.setNombre_usuario(tabla.getString("nombre_usuario"));
                registros.setContrasenia_usuario(tabla.getString("contrasenia_usuario"));
                registros.setTipo_usuario(tabla.getString("tipo_usuario"));
                registros.setEstado_usuario(tabla.getString("estado_usuario"));
                lista.add(registros);
            }
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}