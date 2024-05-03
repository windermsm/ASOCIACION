/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosProducto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoProducto {

    private Mensaje mensaje = new Mensaje();

    public void insertarServicio(ObjetosProducto servicio, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_servicio(" + servicio.getId_categoria()
                    + ", " + servicio.getId_empresa() + ", '" + servicio.getNombre_servicio()
                    + "', '" + servicio.getDescripcion_servicio() + "', " + servicio.getCosto_servicio()
                    + ", " + servicio.getPrecio_servicio() + ", " + servicio.getCantidad_servicio() 
                    + ", " + servicio.getPrecio_excedente_servicio() + ", " + servicio.getDias_credito_puntual_servicio()
                    + ", " + servicio.getDias_credito_maximo_servicio() + ", '" + servicio.getEstado_servicio()
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarServicio(ObjetosProducto servicio, String user) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select actualizar_servicio(" + servicio.getId_servicio() + ", " + servicio.getId_categoria()
                    + ", " + servicio.getId_empresa() + ", '" + servicio.getNombre_servicio()
                    + "', '" + servicio.getDescripcion_servicio() + "', " + servicio.getCosto_servicio()
                    + ", " + servicio.getPrecio_servicio() + ", " + servicio.getCantidad_servicio() 
                    + ", " + servicio.getPrecio_excedente_servicio() + ", " + servicio.getDias_credito_puntual_servicio()
                    + ", " + servicio.getDias_credito_maximo_servicio() + ", '" + servicio.getEstado_servicio()
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error: " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetosProducto> mostrarServicios() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select a.* "
                + "from servicio as a, categoria as b, empresa as c "
                + "where a.id_categoria = b.id_categoria "
                + "and a.id_empresa = c.id_empresa and estado_categoria = 'A' "
                + "and c.estado_empresa = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosProducto registro;
            while (tabla.next()) {
                registro = new ObjetosProducto();
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registro.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registro.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                registro.setCantidad_servicio(tabla.getInt("cantidad_servicio"));
                registro.setPrecio_excedente_servicio(tabla.getDouble("precio_excedente_servicio"));
                registro.setDias_credito_puntual_servicio(tabla.getInt("dias_credito_puntual_servicio"));
                registro.setDias_credito_maximo_servicio(tabla.getInt("dias_credito_maximo_servicio"));
                registro.setEstado_servicio(tabla.getString("estado_servicio"));
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
    
    public ArrayList<ObjetosProducto> mostrarServiciosActivos() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from servicio where estado_servicio = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosProducto registro;
            while (tabla.next()) {
                registro = new ObjetosProducto();
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registro.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registro.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                registro.setCantidad_servicio(tabla.getInt("cantidad_servicio"));
                registro.setPrecio_excedente_servicio(tabla.getDouble("precio_excedente_servicio"));
                registro.setDias_credito_puntual_servicio(tabla.getInt("dias_credito_puntual_servicio"));
                registro.setDias_credito_maximo_servicio(tabla.getInt("dias_credito_maximo_servicio"));
                registro.setEstado_servicio(tabla.getString("estado_servicio"));
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

    public ArrayList<ObjetosProducto> buscarServicios(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from servicio as a, categoria as b, empresa as c "
                + "where a.id_categoria = b.id_categoria and a.id_empresa = c.id_empresa "
                + "and b.estado_categoria = 'A' and c.estado_empresa = 'A' "
                + "and upper(a.nombre_servicio) like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosProducto registro;
            while (tabla.next()) {
                registro = new ObjetosProducto();
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registro.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registro.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                registro.setDias_credito_puntual_servicio(tabla.getInt("dias_credito_puntual_servicio"));
                registro.setDias_credito_maximo_servicio(tabla.getInt("dias_credito_maximo_servicio"));
                registro.setEstado_servicio(tabla.getString("estado_servicio"));
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

    public ArrayList<ObjetosProducto> buscarServiciosPorId(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from servicio where id_servicio = " + x;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosProducto registro;
            while (tabla.next()) {
                registro = new ObjetosProducto();
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registro.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registro.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                registro.setCantidad_servicio(tabla.getInt("cantidad_servicio"));
                registro.setPrecio_excedente_servicio(tabla.getDouble("precio_excedente_servicio"));
                registro.setDias_credito_puntual_servicio(tabla.getInt("dias_credito_puntual_servicio"));
                registro.setDias_credito_maximo_servicio(tabla.getInt("dias_credito_maximo_servicio"));
                registro.setEstado_servicio(tabla.getString("estado_servicio"));
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
    
    public ArrayList<ObjetosProducto> buscarServiciosPorNombre(String x) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from servicio where nombre_servicio = '" + x + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosProducto registro;
            while (tabla.next()) {
                registro = new ObjetosProducto();
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_categoria(tabla.getInt("id_categoria"));
                registro.setId_empresa(tabla.getInt("id_empresa"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registro.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registro.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                registro.setCantidad_servicio(tabla.getInt("cantidad_servicio"));
                registro.setPrecio_excedente_servicio(tabla.getDouble("precio_excedente_servicio"));
                registro.setDias_credito_puntual_servicio(tabla.getInt("dias_credito_puntual_servicio"));
                registro.setDias_credito_maximo_servicio(tabla.getInt("dias_credito_maximo_servicio"));
                registro.setEstado_servicio(tabla.getString("estado_servicio"));
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
