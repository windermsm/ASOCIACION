/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.servicios;

import inventory.acceso.Mensaje;
import inventory.librerias.CsvReader;
import inventory.objetos.ObjetosProducto;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author  Megabyte Soluciones Integrales Guatemala
 */
public class ServicioLeerArchivoExcel {

    public ArrayList leer_archivo_excel(String archivo){
        
        Mensaje Mensaje = new Mensaje();
        ArrayList lista_productos = new ArrayList();
        CsvReader LectorDeArchivo = null;

        try {
            LectorDeArchivo = new CsvReader(archivo,',');
        } catch (FileNotFoundException error) {
            Mensaje.manipulacionExcepciones("critico", "Error al leer el Archivo -> "+error);
        }

        try {
            LectorDeArchivo.readHeaders();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try { 
            ObjetosProducto Producto;
            
            while (LectorDeArchivo.readRecord()){ 
                System.out.println("TRATANDO DE INGRESAR LOS DATOS A LA TABLA");
                Producto = new ObjetosProducto();
                //Producto.setId_producto(String.valueOf(LectorDeArchivo.get("ID Producto")));
                //Producto.setId_provedor(Integer.parseInt(LectorDeArchivo.get("ID Proveedor")));
                //Producto.setMarca_producto(String.valueOf(LectorDeArchivo.get("Marca")));
                //Producto.setDesc_producto(String.valueOf(LectorDeArchivo.get("Descripcion")));
                //Producto.setPrecio_compra_producto(Float.parseFloat(LectorDeArchivo.get("Precio Compra")));
                //Producto.setPrj_est_producto(Float.parseFloat(LectorDeArchivo.get("% Estandar")));
                //Producto.setPrecio_est_producto(Float.parseFloat(LectorDeArchivo.get("Precio Estandar")));
                //Producto.setPrj_min_producto(Float.parseFloat(LectorDeArchivo.get("% Minimo")));
                //Producto.setPrecio_min_producto(Float.parseFloat(LectorDeArchivo.get("Precio Minimo")));
                //Producto.setExi_producto(Float.parseFloat(LectorDeArchivo.get("Existencia")));
                //Producto.setUnidad_medida_producto(String.valueOf(LectorDeArchivo.get("Unidad Medida")));
                //Producto.setMaximo_producto(Float.parseFloat(LectorDeArchivo.get("Maximo")));
                //Producto.setMinimo_producto(Float.parseFloat(LectorDeArchivo.get("Minimo")));
                lista_productos.add(Producto);
            }
            
        } catch (IOException error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al ingresar los registros -> "+error);
        }

        LectorDeArchivo.close();

        return lista_productos;
    }
    
    public void escribir_archivo_excel(JTable pTabla, File pArchivo) throws IOException{
        
        TableModel modelo = pTabla.getModel();
        FileWriter archivo = new FileWriter(pArchivo);
        BufferedWriter contenido = new BufferedWriter(archivo);
        
        for(int cFila = 0; cFila<modelo.getColumnCount(); cFila++){
            contenido.write(modelo.getColumnName(cFila).toString()+"\t");
        }
        
        contenido.write("\n");
        
        for(int cFila = 0; cFila<modelo.getRowCount(); cFila++){
            for(int cColumna = 0; cColumna<modelo.getColumnCount(); cColumna++){
                contenido.write(modelo.getValueAt(cFila, cColumna).toString()+"\t");
            }
            contenido.write("\n");
        }
        
        contenido.close();
    }
}