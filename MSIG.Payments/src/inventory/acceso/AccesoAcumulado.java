/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import inventory.objetos.ObjetosAcumulados;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoAcumulado {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetosAcumulados> listaAcumulado(int id_servicio) {
        ArrayList lista = new ArrayList();
        Conexion Acceso = new Conexion();
        String sql = "select  b.id_cliente, sum(a.total_cobro) as total "
                + "from    cobro as a, suscripcion as b "
                + "where a.estado_cobro = 'G' "
                + "and a.id_suscripcion = b.id_suscripcion "
                + "and b.id_servicio = " + id_servicio + " group by b.id_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = Acceso.listarRegistros(sql);
            ObjetosAcumulados registro;
            while (tabla.next()) {
                registro = new ObjetosAcumulados();
                registro.setId_cliente(tabla.getInt("id_cliente"));
                registro.setTotal(tabla.getDouble("total"));
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