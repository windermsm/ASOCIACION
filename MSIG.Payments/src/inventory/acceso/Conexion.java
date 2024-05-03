/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import inventory.acceso.AccesoArchivo;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class Conexion {
    
    private String driver = "com.mysql.jdbc.Driver";
    private String ip = "";
    private String port = "";
    private String db = "";
    private String url = "";
    private String usuario = "root";
    private String contrasenia = "Derwin2012mg";
    
    
    public void obtener_parametros(){
        
        AccesoArchivo archivo = new AccesoArchivo();
        
        try{
            System.out.println("Buscando parametros de conexion");
            this.ip = archivo.leer("[IP]");
            this.port = archivo.leer("[Port]");
            this.db = archivo.leer("[DataBase]");
            this.url = "jdbc:mysql://"+ip+":"+port+"/"+db+"";
        }catch(Exception e){
            System.out.println("Ocurrio un error durante la conexion");
            e.printStackTrace();
        }

        System.out.println("Conectado: " + this.url);
        
    }
    
    Connection conexion = null;

    public Connection conectar() {
        
        obtener_parametros();
        
        try {
            Class.forName(driver).newInstance();
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
            return conexion;
        } catch (Exception error) {
            System.out.println("EC ACCESO_INVENTARIO:CONEXION " + error);
            return null;
        }
    }
    
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(Exception error){
            System.out.println("Error de conexion "+ error);
        }
    }
    
    public String ejecutarFuncion(String funcion){
        String result;
        String valor = "";
        try{
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(funcion);
            ResultSet registros = acceso_datos.executeQuery();
            while(registros.next()){
                valor = registros.getString("result");
                System.out.println(valor);
            }
            result = valor;
            conexion.close();
        }catch(Exception error){
            result = "Error al ejecutar funcion " + funcion + " : " + error;
        }
        return result;
    }

    public ResultSet listarRegistros(String pConsulta) {
        try {
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(pConsulta);
            ResultSet registros = acceso_datos.executeQuery();
            return registros;
        } catch (Exception error) {
            System.out.println("EC ACCESO_INVENTARIO:LISTAR_REGISTROS " + error);
            return null;
        }
    }

    public String ejecutarConsulta(String pScript) {
        try {
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(pScript);
            int registros = acceso_datos.executeUpdate();
            return "Registros Actualizados: " + registros;
        } catch (Exception error) {
            return "ERROR : " + error;
        }
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (Exception error) {
            System.out.println("EC ACCESO_INVENTARIO:DESCONECTAR " + error);
        }
    }
}