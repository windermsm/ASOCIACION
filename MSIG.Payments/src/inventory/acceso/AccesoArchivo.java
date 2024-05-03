/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.acceso;

import java.io.*;

/**
 *
 * @author Derwin Santa Cruz
 */
public class AccesoArchivo {

    public String leer(String Identificador) throws IOException {
        String valor = null;
        FileReader LectorArchivo = null;
        try {
            //obtener el string de conexcion del archivo confi.ini
            File Archivo = new File("C://MSIG Payments/config.ini");
            LectorArchivo = new FileReader(Archivo);
            BufferedReader PilaLectora = new BufferedReader(LectorArchivo);
            //lectura del fichero
            String linea = null;
            while ((linea = PilaLectora.readLine()) != null) {
                if (linea.contains(Identificador)) {
                    valor = linea.substring(Identificador.length() + 1, linea.length());
                    System.out.println(Identificador + ":" + valor);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error leer archivo: " + ex);
        } finally {
            try {
                LectorArchivo.close();
            } catch (IOException ex) {
                System.out.println("Error buscar archivo: " + ex);
            }
        }
        return valor;
    }
}
