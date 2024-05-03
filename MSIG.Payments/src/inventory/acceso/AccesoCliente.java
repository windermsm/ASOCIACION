/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosCliente;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoCliente {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarCliente(ObjetosCliente cliente, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_cliente('" + cliente.getDpi_cliente() + "','" 
                    + cliente.getNit_cliente() + "', '" + cliente.getNombre_cliente() 
                    + "', '" + cliente.getDepto_cliente() + "', '" + cliente.getMunicipio_cliente() 
                    + "', '" + cliente.getCiudad_cliente() + "', '" + cliente.getZona_cliente() 
                    + "', '" + cliente.getColonia_cliente() + "', '" + cliente.getAvenida_cliente() 
                    + "', '" + cliente.getCalle_cliente() + "', '" + cliente.getNum_casa_cliente() 
                    + "', '" + cliente.getDireccion_cliente() + "', " + cliente.getTelefono_cliente() 
                    + ", " + cliente.getMovil_cliente() + ", '" + cliente.getCorreo_cliente() 
                    + "', '" + cliente.getEstado_cliente() + "','" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarCliente(ObjetosCliente cliente, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_cliente(" + cliente.getId_cliente() + ",'" + cliente.getDpi_cliente() + "','" 
                    + cliente.getNit_cliente() + "', '" + cliente.getNombre_cliente() 
                    + "', '" + cliente.getDepto_cliente() + "', '" + cliente.getMunicipio_cliente() 
                    + "', '" + cliente.getCiudad_cliente() + "', '" + cliente.getZona_cliente() 
                    + "', '" + cliente.getColonia_cliente() + "', '" + cliente.getAvenida_cliente() 
                    + "', '" + cliente.getCalle_cliente() + "', '" + cliente.getNum_casa_cliente() 
                    + "', '" + cliente.getDireccion_cliente() + "', " + cliente.getTelefono_cliente() 
                    + ", " + cliente.getMovil_cliente() + ", '" + cliente.getCorreo_cliente() 
                    + "', '" + cliente.getEstado_cliente() + "','" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosCliente> listarClientes() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCliente registro;
            while (tabla.next()) {
                registro = new ObjetosCliente();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setDpi_cliente(tabla.getString("dpi_cliente"));
                registro.setNit_cliente(tabla.getString("nit_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setDepto_cliente(tabla.getString("depto_cliente"));
                registro.setMunicipio_cliente(tabla.getString("municipio_cliente"));
                registro.setCiudad_cliente(tabla.getString("ciudad_cliente"));
                registro.setZona_cliente(tabla.getString("zona_cliente"));
                registro.setColonia_cliente(tabla.getString("colonia_cliente"));
                registro.setAvenida_cliente(tabla.getString("avenida_cliente"));
                registro.setCalle_cliente(tabla.getString("calle_cliente"));
                registro.setNum_casa_cliente(tabla.getString("num_casa_cliente"));
                registro.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registro.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registro.setMovil_cliente(tabla.getInt("movil_cliente"));
                registro.setCorreo_cliente(tabla.getString("correo_cliente"));
                registro.setEstado_cliente(tabla.getString("estado_cliente"));
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
    
    public ArrayList<ObjetosCliente> buscarClientes(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from cliente where upper(nombre_cliente) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCliente registro;
            while (tabla.next()) {
                registro = new ObjetosCliente();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setDpi_cliente(tabla.getString("dpi_cliente"));
                registro.setNit_cliente(tabla.getString("nit_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setDepto_cliente(tabla.getString("depto_cliente"));
                registro.setMunicipio_cliente(tabla.getString("municipio_cliente"));
                registro.setCiudad_cliente(tabla.getString("ciudad_cliente"));
                registro.setZona_cliente(tabla.getString("zona_cliente"));
                registro.setColonia_cliente(tabla.getString("colonia_cliente"));
                registro.setAvenida_cliente(tabla.getString("avenida_cliente"));
                registro.setCalle_cliente(tabla.getString("calle_cliente"));
                registro.setNum_casa_cliente(tabla.getString("num_casa_cliente"));
                registro.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registro.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registro.setMovil_cliente(tabla.getInt("movil_cliente"));
                registro.setCorreo_cliente(tabla.getString("correo_cliente"));
                registro.setEstado_cliente(tabla.getString("estado_cliente"));
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
    
    public ArrayList<ObjetosCliente> buscarClientesPorId(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from cliente where id_cliente = " + x;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCliente registro;
            while (tabla.next()) {
                registro = new ObjetosCliente();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setDpi_cliente(tabla.getString("dpi_cliente"));
                registro.setNit_cliente(tabla.getString("nit_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setDepto_cliente(tabla.getString("depto_cliente"));
                registro.setMunicipio_cliente(tabla.getString("municipio_cliente"));
                registro.setCiudad_cliente(tabla.getString("ciudad_cliente"));
                registro.setZona_cliente(tabla.getString("zona_cliente"));
                registro.setColonia_cliente(tabla.getString("colonia_cliente"));
                registro.setAvenida_cliente(tabla.getString("avenida_cliente"));
                registro.setCalle_cliente(tabla.getString("calle_cliente"));
                registro.setNum_casa_cliente(tabla.getString("num_casa_cliente"));
                registro.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registro.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registro.setMovil_cliente(tabla.getInt("movil_cliente"));
                registro.setCorreo_cliente(tabla.getString("correo_cliente"));
                registro.setEstado_cliente(tabla.getString("estado_cliente"));
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
    
    public ArrayList<ObjetosCliente> buscarClientesPorNombre(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from cliente where nombre_cliente = '" + x + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCliente registro;
            while (tabla.next()) {
                registro = new ObjetosCliente();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setDpi_cliente(tabla.getString("dpi_cliente"));
                registro.setNit_cliente(tabla.getString("nit_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setDepto_cliente(tabla.getString("depto_cliente"));
                registro.setMunicipio_cliente(tabla.getString("municipio_cliente"));
                registro.setCiudad_cliente(tabla.getString("ciudad_cliente"));
                registro.setZona_cliente(tabla.getString("zona_cliente"));
                registro.setColonia_cliente(tabla.getString("colonia_cliente"));
                registro.setAvenida_cliente(tabla.getString("avenida_cliente"));
                registro.setCalle_cliente(tabla.getString("calle_cliente"));
                registro.setNum_casa_cliente(tabla.getString("num_casa_cliente"));
                registro.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registro.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registro.setMovil_cliente(tabla.getInt("movil_cliente"));
                registro.setCorreo_cliente(tabla.getString("correo_cliente"));
                registro.setEstado_cliente(tabla.getString("estado_cliente"));
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