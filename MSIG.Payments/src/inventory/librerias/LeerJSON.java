/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.librerias;

import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosLectura;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class LeerJSON {
    
    Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetosLectura> obtenerJSON(String cadena) {

        JSONParser parser = new JSONParser();
        ArrayList<ObjetosLectura> lista = new ArrayList();

        try {
            
            JSONArray a = (JSONArray) parser.parse(cadena);
            
            for (Object o : a) {
                
                ObjetosLectura lectura = new ObjetosLectura();
                JSONObject dato = (JSONObject) o;

                String anio = (String) dato.get("anio");
                String mes = (String) dato.get("mes");
                String suscripcion = (String) dato.get("sus");
                String anterior = (String) dato.get("ant");
                String nueva = (String) dato.get("nue");

                //enviar datos al objeto lectura
                lectura.setAnio_lectura(Integer.parseInt(anio));
                lectura.setMes_lectura(Integer.parseInt(mes));
                lectura.setId_suscripcion(Integer.parseInt(suscripcion));
                lectura.setAnterior_lectura(Integer.parseInt(anterior));
                lectura.setNueva_lectura(Integer.parseInt(nueva));
                lectura.setEstado_lectura("A");

                //ingresarlos a la lista de valores
                lista.add(lectura);
            }

        } catch (Exception e) {
            System.out.println("Error al convertir JSON: " + e.getMessage());
            mensaje.manipulacionExcepciones("critico", "1");
        }
        return lista;
    }
}
