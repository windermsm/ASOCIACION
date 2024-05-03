/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosIngreso;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Derwin Santa Cruz
 */
public class AccesoIngreso {
    
    private Mensaje mensaje = new Mensaje();
    
    public boolean insertarIngreso(ObjetosIngreso ingreso, String user) {
        boolean valor = false;
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_ingreso ('" + ingreso.getSerie_factura() 
                    + "', " + ingreso.getNo_factura() + ",'" 
                    + ingreso.getNit_cliente() + "', '" 
                    + ingreso.getNombre_cliente() + "',  '" 
                    + ingreso.getDireccion() + "', '"
                    + ingreso.getFecha_factura() + "', " 
                    + ingreso.getMonto() + ", '" 
                    + ingreso.getCuenta() + "', '" 
                    + ingreso.getMotivo() + "', '" 
                    + ingreso.getEstado() + "', '" + user + "') as result";
            mensaje.manipulacionExcepciones("informacion",acceso.ejecutarFuncion(sql));
            System.out.println("Ejecutando " + sql);
            valor = true;
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al guardar ingreso: " + error);
            valor = false;
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public boolean actualizarIngreso(ObjetosIngreso ingreso, String user) {
        String sql;
        boolean valor = false;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_ingreso(" + ingreso.getId_ingreso()
                    + ", '" + ingreso.getSerie_factura() 
                    + "', " + ingreso.getNo_factura() 
                    + ", '" + ingreso.getNit_cliente() 
                    + "', '" + ingreso.getNombre_cliente() 
                    + "', '" + ingreso.getDireccion()
                    + "', '" + ingreso.getFecha_factura()
                    + "', " + ingreso.getMonto()
                    + ", '" + ingreso.getCuenta() 
                    + "', '" + ingreso.getMotivo() 
                    + "', '" + ingreso.getEstado()
                    + "', '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion",acceso.ejecutarFuncion(sql));
            valor = true;
        } catch (Exception error) {
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Error al actualizar el ingreso: " + error);
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public ArrayList<ObjetosIngreso> listarPago() {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from ingreso";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosIngreso registro;
            while (tabla.next()) {
                registro = new ObjetosIngreso();
                registro.setId_ingreso(tabla.getInt("id_ingreso"));
                registro.setSerie_factura(tabla.getString("serie_factura_ingreso"));
                registro.setNo_factura(tabla.getInt("no_factura_ingreso"));
                registro.setNit_cliente(tabla.getString("nit_cliente_ingreso"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente_ingreso"));
                registro.setDireccion(tabla.getString("direccion_ingreso"));
                registro.setFecha_factura(tabla.getString("fecha_factura_ingreso"));
                registro.setMonto(tabla.getFloat("monto_ingreso"));
                registro.setCuenta(tabla.getString("cuenta_ingreso"));
                registro.setMotivo(tabla.getString("motivo_ingreso"));
                registro.setEstado(tabla.getString("estado_ingreso"));
                registro.setFecha(tabla.getString("fecha_ingreso"));
                lista.add(registro);
            }
        } catch (Exception error) {
            System.out.println("Error listar ingreso: " + error);
            return null;
        } finally {
            Acceso.desconectar();
        }
        return lista;
    }
    
    public ArrayList<ObjetosIngreso> buscarPago(String valor) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select * from ingreso where UPPER(motivo_ingreso) like '%" + valor + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosIngreso registro;
            while (tabla.next()) {
                registro = new ObjetosIngreso();
                registro.setId_ingreso(tabla.getInt("id_ingreso"));
                registro.setSerie_factura(tabla.getString("serie_factura_ingreso"));
                registro.setNo_factura(tabla.getInt("no_factura_ingreso"));
                registro.setNit_cliente(tabla.getString("nit_cliente_ingreso"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente_ingreso"));
                registro.setDireccion(tabla.getString("direccion_ingreso"));
                registro.setFecha_factura(tabla.getString("fecha_factura_ingreso"));
                registro.setMonto(tabla.getFloat("monto_ingreso"));
                registro.setCuenta(tabla.getString("cuenta_ingreso"));
                registro.setMotivo(tabla.getString("motivo_ingreso"));
                registro.setEstado(tabla.getString("estado_ingreso"));
                registro.setFecha(tabla.getString("fecha_ingreso"));
                lista.add(registro);
            }
        } catch (Exception error) {
            System.out.println("Error buscar ingreso: " + error);
            return null;
        } finally {
            Acceso.desconectar();
        }
        return lista;
    }
    
}
