/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventory.acceso;

import inventory.objetos.ObjetosLectura;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoLectura {

    private Mensaje mensaje = new Mensaje();
    
    public void insertarLectura(ObjetosLectura lectura, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_lectura(" + lectura.getId_suscripcion() 
                    + ", " + lectura.getAnio_lectura() + ", " + lectura.getMes_lectura() 
                    + ", " + lectura.getAnterior_lectura() + ", " + lectura.getNueva_lectura() 
                    + ", '" + lectura.getEstado_lectura() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarLectura(ObjetosLectura lectura, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_lectura(" + lectura.getId_lectura() + "," + lectura.getId_suscripcion() 
                    + ", " + lectura.getAnio_lectura() + ", " + lectura.getMes_lectura() 
                    + ", " + lectura.getAnterior_lectura() + ", " + lectura.getNueva_lectura() 
                    + ", '" + lectura.getEstado_lectura() + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosLectura> listarLecturas() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select  a.*, c.nombre_cliente, d.nombre_servicio "
                + "from    lectura as a, suscripcion as b, cliente as c, servicio as d "
                + "where   a.id_suscripcion = b.id_suscripcion and b.id_cliente = c.id_cliente and b.id_servicio = d.id_servicio";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosLectura registro;
            while (tabla.next()) {
                registro = new ObjetosLectura();
                registro.setId_lectura(tabla.getInt("id_lectura"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setAnio_lectura(tabla.getInt("anio_lectura"));
                registro.setMes_lectura(tabla.getInt("mes_lectura"));
                registro.setAnterior_lectura(tabla.getInt("anterior_lectura"));
                registro.setNueva_lectura(tabla.getInt("nueva_lectura"));
                registro.setEstado_lectura(tabla.getString("estado_lectura"));
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
    
    public ArrayList<ObjetosLectura> BuscarLecturaAnterior( String suscripcion, int anio, int mes) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from lectura where id_suscripcion = " + suscripcion + " and anio_lectura = " 
                + anio + " and mes_lectura = " + mes;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosLectura registro;
            while (tabla.next()) {
                registro = new ObjetosLectura();
                registro.setId_lectura(tabla.getInt("id_lectura"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setAnio_lectura(tabla.getInt("anio_lectura"));
                registro.setMes_lectura(tabla.getInt("mes_lectura"));
                registro.setAnterior_lectura(tabla.getInt("anterior_lectura"));
                registro.setNueva_lectura(tabla.getInt("nueva_lectura"));
                registro.setEstado_lectura(tabla.getString("estado_lectura"));
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
    
    public ArrayList<ObjetosLectura> buscarLecturas(String x, String m, String a) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select  a.id_lectura, a.id_suscripcion, d.nombre_servicio, "
                + "c.nombre_cliente, a.anio_lectura, a.mes_lectura, a.anterior_lectura, "
                + "a.nueva_lectura, a.estado_lectura from    lectura as a,"
                + " suscripcion as b, cliente as c, servicio as d "
                + "where   a.id_suscripcion = b.id_suscripcion "
                + "and b.id_cliente = c.id_cliente and b.id_servicio = d.id_servicio "
                + "and c.nombre_cliente like '%" + x + "%' and a.mes_lectura = " + m + " and a.anio_lectura = " + a;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosLectura registro;
            while (tabla.next()) {
                registro = new ObjetosLectura();
                registro.setId_lectura(tabla.getInt("id_lectura"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setAnio_lectura(tabla.getInt("anio_lectura"));
                registro.setMes_lectura(tabla.getInt("mes_lectura"));
                registro.setAnterior_lectura(tabla.getInt("anterior_lectura"));
                registro.setNueva_lectura(tabla.getInt("nueva_lectura"));
                registro.setEstado_lectura(tabla.getString("estado_lectura"));
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
    
    public int verifarLectura(String id_suscripcion, String mes, String anio) {
        int valor = 0;
        Conexion Acceso = new Conexion();
        String sql = "select count(id_lectura) as valor from lectura where id_suscripcion = " + id_suscripcion + " and mes_lectura = " + mes + " and anio_lectura = " + anio;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosLectura registro;
            while (tabla.next()) {
                valor = tabla.getInt("valor");
            }
        } catch (Exception error) {
            System.out.println("Error: " + error);
            return 0;
        } finally {
            Acceso.desconectar();
        }
        return valor;
    }
}