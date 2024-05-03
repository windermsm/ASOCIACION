/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosCobro;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoCobro {

    private Mensaje mensaje = new Mensaje();
    
    public boolean insertarCobro(ObjetosCobro cobro, String user) {
        boolean valor = false;
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_cobro(" + cobro.getId_suscripcion() 
                    + ", " + cobro.getAnio_cobro() + ", " + cobro.getMes_cobro() 
                    + ", '" + cobro.getFecha_puntual_cobro() + "', '" + cobro.getFecha_maxima_cobro() 
                    + "', " + cobro.getDias_atrazo_cobro() +", '" + cobro.getNumero_contador_cobro() 
                    + "', " + cobro.getValor_cobro() + ", " + cobro.getValor_excedente() 
                    + ", " + cobro.getValor_penalizacion_cobro() + ", " + cobro.getAcumulado_cobro()
                    + ", " + cobro.getTotal_cobro() + ", '" + cobro.getForma_pago_cobro() 
                    + "', '" + cobro.getEstado_cobro() + "', '" + user + "') as result";
            acceso.ejecutarFuncion(sql);
            System.out.println("Ejecutando " + sql);
            valor = true;
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al guardar cobro suscripcion " + cobro.getId_suscripcion());
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
            valor = false;
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public boolean actualizarCobro(ObjetosCobro cobro, String user) {
        String sql;
        boolean valor = false;
        Conexion acceso = new Conexion();
        try { 
            sql = "select actualizar_cobro(" + cobro.getId_cobro() + ", " + cobro.getId_suscripcion() 
                    + ", " + cobro.getAnio_cobro() + ", " + cobro.getMes_cobro() 
                    + ", '" + cobro.getFecha_puntual_cobro() + "', '" + cobro.getFecha_maxima_cobro() 
                    + "', " + cobro.getDias_atrazo_cobro() + ", '" + cobro.getNumero_contador_cobro() 
                    + "', " + cobro.getValor_cobro() + "," + cobro.getValor_excedente() 
                    + " , " + cobro.getValor_penalizacion_cobro() + ", " + cobro.getAcumulado_cobro()
                    + ", " + cobro.getTotal_cobro() + ", '" + cobro.getForma_pago_cobro() 
                    + "', '" + cobro.getEstado_cobro() + "', " + cobro.getFactura_cobro() + ", '" + user + "') as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
            valor = true;
        } catch (Exception error) {
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public boolean actualizarFactura(int factura, int id_cobro) {
        String sql;
        boolean valor = false;
        Conexion acceso = new Conexion();
        try { 
            sql = "update cobro set factura_cobro = " + factura + " where id_cobro = " + id_cobro + ";";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarConsulta(sql);
            valor = true;
        } catch (Exception error) {
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public boolean anularCobro(ObjetosCobro cobro) {
        String sql;
        boolean valor = false;
        Conexion acceso = new Conexion();
        try { 
            sql = "UPDATE cobro SET estado_cobro = 'N' "
                    + "WHERE estado_cobro in ('P','G') "
                    + "and id_suscripcion = " + cobro.getId_suscripcion();
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
            valor = true;
        } catch (Exception error) {
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Error: " + error);
        }
        acceso.cerrarConexion();
        return valor;
    }
    
    public ArrayList<ObjetosCobro> generaCobros(String anio, String mes, String servicio) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        
        String str_servicio = null;
        
        if(servicio.equals("0")){
            str_servicio = "null";
        } else {
            str_servicio = servicio;
        }
        
        String sql = "call generar_cobros('" + mes + "', '" + anio + "', " + str_servicio + ")";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCobro registro; 
            while (tabla.next()) {
                registro = new ObjetosCobro();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setNombre_servicio(tabla.getString("nombre_servicio"));
                registro.setFecha_puntual_cobro(tabla.getString("fecha_puntual"));
                registro.setFecha_maxima_cobro(tabla.getString("fecha_maxima"));
                registro.setDias_atrazo_cobro(tabla.getInt("dias_atrazo"));
                registro.setNumero_contador_cobro(tabla.getString("contador_suscripcion"));
                registro.setValor_cobro(tabla.getDouble("precio_servicio"));
                registro.setValor_excedente(tabla.getDouble("excedente"));
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

    public ArrayList<ObjetosCobro> verificarCobros(String mes, String anio, String servicio) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        
        String str_servicio = null;
        
        if(servicio.equals("0")){
            str_servicio = "null";
        } else {
            str_servicio = servicio;
        }
        
        String sql = "call verificar_cobros(" + mes + ", " + anio + ", " + str_servicio + ")";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCobro registro; 
            while (tabla.next()) {
                registro = new ObjetosCobro();
                registro.setId_cobro(tabla.getInt("id_cobro"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_servicio(tabla.getInt("id_servicio"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setAnio_cobro(tabla.getInt("anio_cobro"));
                registro.setMes_cobro(tabla.getInt("mes_cobro"));
                registro.setFecha_puntual_cobro(tabla.getString("fecha_puntual_cobro"));
                registro.setFecha_maxima_cobro(tabla.getString("fecha_maxima_cobro"));
                registro.setDias_atrazo_cobro(tabla.getInt("dias_atrazo_cobro"));
                registro.setNumero_contador_cobro(tabla.getString("numero_contador_cobro"));
                registro.setValor_cobro(tabla.getDouble("valor_cobro"));
                registro.setValor_excedente(tabla.getDouble("valor_excedente_cobro"));
                registro.setValor_penalizacion_cobro(tabla.getDouble("valor_penalizacion_cobro"));
                registro.setAcumulado_cobro(tabla.getDouble("acumulado_cobro"));
                registro.setTotal_cobro(tabla.getDouble("total_cobro"));
                registro.setForma_pago_cobro(tabla.getString("forma_pago_cobro"));
                registro.setEstado_cobro(tabla.getString("estado_cobro"));
                registro.setFactura_cobro(tabla.getInt("factura_cobro"));
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
    
    public ArrayList<ObjetosCobro> buscarCobros(String id, String mes, String anio) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        
        String sql = " select a.*, c.id_cliente, c.nombre_cliente "
                + "from cobro as a, suscripcion as b, cliente as c "
                + "where a.id_suscripcion = b.id_suscripcion "
                + "and b.id_cliente = c.id_cliente "
                + "and c.nombre_cliente like '%" + id + "%' "
                + "and a.mes_cobro = " + mes + " and anio_cobro = " + anio;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCobro registro; 
            while (tabla.next()) {
                registro = new ObjetosCobro();
                registro.setId_cobro(tabla.getInt("id_cobro"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setAnio_cobro(tabla.getInt("anio_cobro"));
                registro.setMes_cobro(tabla.getInt("mes_cobro"));
                registro.setFecha_puntual_cobro(tabla.getString("fecha_puntual_cobro"));
                registro.setFecha_maxima_cobro(tabla.getString("fecha_maxima_cobro"));
                registro.setDias_atrazo_cobro(tabla.getInt("dias_atrazo_cobro"));
                registro.setNumero_contador_cobro(tabla.getString("numero_contador_cobro"));
                registro.setValor_cobro(tabla.getDouble("valor_cobro"));
                registro.setValor_excedente(tabla.getDouble("valor_excedente_cobro"));
                registro.setValor_penalizacion_cobro(tabla.getDouble("valor_penalizacion_cobro"));
                registro.setAcumulado_cobro(tabla.getDouble("acumulado_cobro"));
                registro.setTotal_cobro(tabla.getDouble("total_cobro"));
                registro.setForma_pago_cobro(tabla.getString("forma_pago_cobro"));
                registro.setEstado_cobro(tabla.getString("estado_cobro"));
                registro.setFactura_cobro(tabla.getInt("factura_cobro"));
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
    
    public ArrayList<ObjetosCobro> listarCobros(String colonia, String mes, String anio, String servicio) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        
        String sql = "select * from cobro as a, suscripcion as b, cliente as c "
                + "where a.id_suscripcion = b.id_suscripcion"
                + " and b.id_cliente = c.id_cliente"
                + " and a.estado_cobro = 'G'"
                + " and b.id_servicio = " + servicio
                + " and a.mes_cobro = " + mes
                + " and a.anio_cobro = " + anio
                + " and c.colonia_cliente = '" + colonia + "' order by a.id_cobro";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosCobro registro; 
            while (tabla.next()) {
                registro = new ObjetosCobro();
                registro.setId_cobro(tabla.getInt("id_cobro"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setNombre_cliente(tabla.getString("nombre_cliente"));
                registro.setAnio_cobro(tabla.getInt("anio_cobro"));
                registro.setMes_cobro(tabla.getInt("mes_cobro"));
                registro.setFecha_puntual_cobro(tabla.getString("fecha_puntual_cobro"));
                registro.setFecha_maxima_cobro(tabla.getString("fecha_maxima_cobro"));
                registro.setDias_atrazo_cobro(tabla.getInt("dias_atrazo_cobro"));
                registro.setNumero_contador_cobro(tabla.getString("numero_contador_cobro"));
                registro.setValor_cobro(tabla.getDouble("valor_cobro"));
                registro.setValor_excedente(tabla.getDouble("valor_excedente_cobro"));
                registro.setValor_penalizacion_cobro(tabla.getDouble("valor_penalizacion_cobro"));
                registro.setAcumulado_cobro(tabla.getDouble("acumulado_cobro"));
                registro.setTotal_cobro(tabla.getDouble("total_cobro"));
                registro.setForma_pago_cobro(tabla.getString("forma_pago_cobro"));
                registro.setEstado_cobro(tabla.getString("estado_cobro"));
                registro.setFactura_cobro(tabla.getInt("factura_cobro"));
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
    
    public Double buscarSaldo(String id_suscripcion){
        ArrayList<String> lista = new ArrayList();
        Double saldo = null;
        Conexion conexion = new Conexion();
        String sql = "select a.mes_cobro as mes,"
                + " a.anio_cobro as anio, "
                + " a.numero_contador_cobro as numero_contador,"
                + " a.valor_cobro as cargo_mes,"
                + " a.acumulado_cobro as saldo_agua,"
                + " a.valor_cobro + a.acumulado_cobro as total_agua,"
                + " a.valor_excedente_cobro as cobros_varios,"
                + " a.valor_penalizacion_cobro as saldo_varios,"
                + " Round((a.valor_cobro + a.acumulado_cobro + (a.valor_excedente_cobro * 1) + a.valor_penalizacion_cobro ), 2) as total_a_pagar,"
                + " ifnull(a.fecha_maxima_cobro, '') as fecha_vence,"
                + " a.estado_cobro as estado"
                + " from cobro as a"
                + " where a.estado_cobro = 'G'"
                + " and a.id_suscripcion = " + id_suscripcion;
        
        try{
            System.out.println("Ejecutando: "+sql);
            ResultSet tabla = conexion.listarRegistros(sql);
            while(tabla.next()){
                saldo = tabla.getDouble("total_a_pagar");
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al buscar el saldo");
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al buscar saldo " + e.toString());
            saldo = 0.0;
        } finally {
            conexion.cerrarConexion();
        }
        return saldo;
    }
    
    public String cancelarTodosLosCobros(String id_suscripcion, String no_factura){
        Conexion conexion = new Conexion();
        String sql = "update cobro "
                + "   set    estado_cobro = 'C', "
                + "          factura_cobro = " + no_factura
                + "   where  estado_cobro in ('G') "
                + "      and factura_cobro = 0"
                + "      and id_suscripcion = " + id_suscripcion;
        System.out.println("Ejecutando: "+sql);
        return conexion.ejecutarConsulta(sql);
    }
    
    public String anularCobroPorSuscripcionMesAnio(String id_suscripcion, String mes, String anio){
        Conexion conexion = new Conexion();
        String sql = "update cobro set estado_cobro = 'A' "
                + "where id_suscripcion = " + id_suscripcion 
                + " and mes_cobro = " + mes + " and anio_cobro = " + anio;
        System.out.println("Ejecutando: "+sql);
        return conexion.ejecutarConsulta(sql);
    }
    
    public String ajustarCobroPorSuscripcionMesAnio(String id_suscripcion, String mes, String anio, String saldo, String nota, String usuario){
        Conexion conexion = new Conexion();
        String sql = "update cobro set estado_cobro = 'J' where id_suscripcion = " + id_suscripcion + " and mes_cobro = " + mes + " and anio_cobro = " + anio;
        System.out.println("Ejecutando: "+sql);
        String ajs = "select insertar_ajuste ("+id_suscripcion+", "+mes+", "+anio+", "+saldo+", '"+nota+"', '"+usuario+"') as result";
        conexion.ejecutarFuncion(ajs);
        System.out.println("Ejecutando: "+ajs);
        return conexion.ejecutarConsulta(sql);
    }
    
    
    public ObjetosCobro buscarCobrosCliente(String id, String mes, String anio) {
        Conexion Acceso = new Conexion();
        ObjetosCobro registro = new ObjetosCobro(); 
        
        String sql = " select * "
                + "    from   cobro "
                + "    where  id_suscripcion = " + id + " and estado_cobro = 'G' " 
                + "       and mes_cobro = " + mes + " and anio_cobro = " + anio;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            while (tabla.next()) {
                registro.setId_cobro(tabla.getInt("id_cobro"));
                registro.setId_suscripcion(tabla.getInt("id_suscripcion"));
                registro.setAnio_cobro(tabla.getInt("anio_cobro"));
                registro.setMes_cobro(tabla.getInt("mes_cobro"));
                registro.setFecha_puntual_cobro(tabla.getString("fecha_puntual_cobro"));
                registro.setFecha_maxima_cobro(tabla.getString("fecha_maxima_cobro"));
                registro.setDias_atrazo_cobro(tabla.getInt("dias_atrazo_cobro"));
                registro.setNumero_contador_cobro(tabla.getString("numero_contador_cobro"));
                registro.setValor_cobro(tabla.getDouble("valor_cobro"));
                registro.setValor_excedente(tabla.getDouble("valor_excedente_cobro"));
                registro.setValor_penalizacion_cobro(tabla.getDouble("valor_penalizacion_cobro"));
                registro.setAcumulado_cobro(tabla.getDouble("acumulado_cobro"));
                registro.setTotal_cobro(tabla.getDouble("total_cobro"));
                registro.setForma_pago_cobro(tabla.getString("forma_pago_cobro"));
                registro.setEstado_cobro(tabla.getString("estado_cobro"));
                registro.setFactura_cobro(tabla.getInt("factura_cobro"));
            }
        } catch (Exception error) {
            System.out.println("Error: " + error);
            return null;
        } finally {
            Acceso.desconectar();
        }
        return registro;
    }
}