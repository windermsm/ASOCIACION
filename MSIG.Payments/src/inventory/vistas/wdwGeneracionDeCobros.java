/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventory.vistas;

import inventory.acceso.*;
import inventory.objetos.*;
import inventory.servicios.ServicioOperacionesMatematicas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class wdwGeneracionDeCobros extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    ObjetosCobro cobro = new ObjetosCobro();
    ObjetosPenalizacion penalizacion = new ObjetosPenalizacion();
    ObjetosLectura lectura = new ObjetosLectura();
    ObjetosAcumulados acumulado = new ObjetosAcumulados();
    
    AccesoArchivo archivo = new AccesoArchivo();
    AccesoCobro acceso_cobro = new AccesoCobro();
    AccesoPenalizacion acceso_penalizacion = new AccesoPenalizacion();
    AccesoProducto acceso_servicio = new AccesoProducto();
    AccesoLectura acceso_lectura = new AccesoLectura();
    AccesoAcumulado acceso_acumulado = new AccesoAcumulado();
    
    ServicioOperacionesMatematicas operacion = new ServicioOperacionesMatematicas();
    
    int total_registros;
    
    public wdwGeneracionDeCobros() {
        initComponents();
        cargarServicios();
    }

    private String convertirEstado(String x) {
        String valor = "";
        if (x.equals("J")) {
            valor = "Ajuste";
        }
        if (x.equals("A")) {
            valor = "Abono";
        }
        if (x.equals("I")) {
            valor = "Inactivo";
        }
        if (x.equals("C")){
            valor = "Cancelado";
        }
        if (x.equals("P")){
            valor = "Pendiente";
        }
        if(x.equals("G")){
            valor = "Generado";
        }
        if(x.equals("N")){
            valor = "Anulado";
        }
        if (x.equals("Abono")) {
            valor = "A";
        }
        if (x.equals("Inactivo")) {
            valor = "I";
        }
        if (x.equals("Cancelado")) {
            valor = "C";
        }
        if (x.equals("Pendiente")) {
            valor = "P";
        }
        if (x.equals("Generado")) {
            valor = "G";
        }
        if (x.equals("Anulado")) {
            valor = "N";
        }
        if (x.equals("Ajuste")) {
            valor = "J";
        } 
        return valor;
    }

    private void generarRegistros() {

        String servicio;
        double cobro;
        double penalizacion;
        double monto_penalizacion;
        double excedente;
        double acumulado;
        double total;
        
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosCobro> lista_cobros = new ArrayList();
        ArrayList<ObjetosPenalizacion> lista_penalizacion = new ArrayList();
        ArrayList<ObjetosLectura> lista_lectura = new ArrayList();
        ArrayList<ObjetosAcumulados> lista_acumulado = new ArrayList();
        
        if(txtIdServicio.getText().isEmpty()){
            servicio = "null";
        } else {
            servicio = txtIdServicio.getText();
        }

        lista_cobros = acceso_cobro.generaCobros(txtAnioCobro.getText(), txtMesCobro.getText(), servicio);
        lista_penalizacion = acceso_penalizacion.listarPenalizacion();
        lista_acumulado = acceso_acumulado.listaAcumulado(Integer.parseInt(txtIdServicio.getText()));
        
        tabla.addColumn("Cliente");
        tabla.addColumn("Suscripcion");
        tabla.addColumn("Servicio");
        tabla.addColumn("Fecha Puntual");
        tabla.addColumn("Fecha Maxima");
        tabla.addColumn("Dias Atrazo");
        tabla.addColumn("No. Contador");
        tabla.addColumn("Cobro Servicio");
        tabla.addColumn("Excedente");
        tabla.addColumn("Penalizacion");
        tabla.addColumn("Acumulado");
        tabla.addColumn("Total");
        tabla.addColumn("Forma de Pago");
        tabla.addColumn("Estado");
        
        tabla.setRowCount(lista_cobros.size());
        
        int fila = 0;
        double total_mes = 0.0;
        int barra = 0;

        for (ObjetosCobro x : lista_cobros) {
            
            cobro = 0.0;
            penalizacion = 0.0;
            monto_penalizacion = 0.0;
            excedente = 0.0;
            acumulado = 0.0;
            total = 0.0;

            tabla.setValueAt(x.getNombre_cliente(), fila, 0);
            tabla.setValueAt(x.getId_suscripcion(), fila, 1);
            tabla.setValueAt(x.getNombre_servicio(), fila, 2);
            tabla.setValueAt(x.getFecha_puntual_cobro(), fila, 3);
            tabla.setValueAt(x.getFecha_maxima_cobro(), fila, 4);
            tabla.setValueAt(x.getDias_atrazo_cobro(), fila, 5);
            tabla.setValueAt(x.getNumero_contador_cobro(), fila, 6);
            tabla.setValueAt(x.getValor_cobro(), fila, 7);
            
            if(x.getValor_excedente() < 0){
                excedente = 0.0;
            } else {
                excedente = x.getValor_excedente();
            }
            
            tabla.setValueAt(excedente, fila, 8);
            
            for (ObjetosPenalizacion y : lista_penalizacion) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    if (x.getDias_atrazo_cobro() >= y.getDias_atrazo_penalizacion()) {
                        penalizacion = Double.parseDouble(String.valueOf(y.getTaza_penalizacion()));
                    }
                }
            }
            
            tabla.setValueAt(penalizacion, fila, 9);

            cobro = Double.parseDouble(String.valueOf(x.getValor_cobro()));
            monto_penalizacion = (cobro + excedente) * (penalizacion / 100);
            
                
            
            //BUSCAR EL ACUMULADO PARA ALMACENAR LO PENDIENTE DE PAGO
            for(ObjetosAcumulados a : lista_acumulado){
                if(x.getId_cliente() == a.getId_cliente()){
                    acumulado = a.getTotal();
                }
            }

            total = operacion.redondear(cobro + excedente + monto_penalizacion, 2);
            
            tabla.setValueAt(acumulado, fila, 10);
            tabla.setValueAt(total, fila, 11);
            tabla.setValueAt(convertirEstado("P"), fila, 12);
            tabla.setValueAt(convertirEstado("P"), fila, 13);
            
            fila++;
            total_mes = total_mes + total;
        }
        
        txtTotal.setText(String.valueOf(operacion.redondear(total_mes,2)));
        tblSuscripcion.setModel(tabla);
        mensaje.manipulacionExcepciones("informacion", "Fueron generados " + tblSuscripcion.getRowCount() +  " registros ");
    }
    
    private boolean verificarRegistros(){
        
        boolean  valor = false;
        String servicio = "";
        ArrayList<ObjetosCobro> lista = new ArrayList();
        
        if(txtIdServicio.getText().isEmpty()){
            servicio = "null";
        } else {
            servicio = txtIdServicio.getText();
        }
        
        lista = acceso_cobro.verificarCobros(txtMesCobro.getText(), txtAnioCobro.getText(), servicio);
        
        if(lista.size() == 0) {
            valor = false;
        } else {
            valor = true;
        }
        
        return valor;
    }

    private void guardarRegistros() {
        
        total_registros = tblSuscripcion.getRowCount();
        prbBarra.setMaximum(tblSuscripcion.getRowCount());
        
        for (int fila = 0; fila < tblSuscripcion.getRowCount(); fila++) {

            double valor = 0.0;
            double excedente = 0.0;
            double penalizacion = 0.0;
            double acumulado = 0.0;
            double total = 0.0;
            
            cobro.setId_suscripcion(Integer.parseInt(tblSuscripcion.getValueAt(fila, 1).toString()));
            cobro.setAnio_cobro(Integer.parseInt(txtAnioCobro.getText()));
            cobro.setMes_cobro((Integer.parseInt(txtMesCobro.getText())));
            cobro.setFecha_puntual_cobro(tblSuscripcion.getValueAt(fila, 3).toString());
            cobro.setFecha_maxima_cobro(tblSuscripcion.getValueAt(fila, 4).toString());
            cobro.setDias_atrazo_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 5).toString()));
            cobro.setNumero_contador_cobro(tblSuscripcion.getValueAt(fila, 6).toString());
            cobro.setValor_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 7).toString()));
            cobro.setValor_excedente(Double.parseDouble(tblSuscripcion.getValueAt(fila, 8).toString()));

            valor        = Double.parseDouble(tblSuscripcion.getValueAt(fila, 7).toString());
            excedente    = Double.parseDouble(tblSuscripcion.getValueAt(fila, 8).toString());
            penalizacion = Double.parseDouble(tblSuscripcion.getValueAt(fila, 9).toString());
            acumulado    = Double.parseDouble(tblSuscripcion.getValueAt(fila, 10).toString());
            total        = (valor + excedente + acumulado) * (penalizacion / 100);

            cobro.setValor_penalizacion_cobro(total);
            cobro.setAcumulado_cobro(acumulado);
            cobro.setTotal_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 11).toString()));
            cobro.setForma_pago_cobro(tblSuscripcion.getValueAt(fila, 12).toString());
            cobro.setEstado_cobro("G");

            if (acceso_cobro.insertarCobro(cobro, Inventory.txtUsuario.getText()) == true) {
                tblSuscripcion.setValueAt(convertirEstado("G"), fila, 13);
            } else {
                mensaje.manipulacionExcepciones("critico", "Ocurrio un error al grabar el cobro de " + tblSuscripcion.getValueAt(fila, 0));
            }
        
            prbBarra.setValue(fila);
        }
        
        mensaje.manipulacionExcepciones("informacion", "Se insertaron " + tblSuscripcion.getRowCount() + " registros");
    }
    
    private void actualizarRegistros() {

        int fila = tblSuscripcion.getSelectedRow();
        cobro.setId_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 0).toString()));
        cobro.setId_suscripcion(Integer.parseInt(tblSuscripcion.getValueAt(fila, 1).toString()));
        cobro.setId_cliente(Integer.parseInt(tblSuscripcion.getValueAt(fila, 2).toString()));
        cobro.setNombre_cliente(tblSuscripcion.getValueAt(fila, 3).toString());
        cobro.setAnio_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 4).toString()));
        cobro.setMes_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 5).toString()));
        cobro.setFecha_puntual_cobro(tblSuscripcion.getValueAt(fila, 6).toString());
        cobro.setFecha_maxima_cobro(tblSuscripcion.getValueAt(fila, 7).toString());
        cobro.setDias_atrazo_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 8).toString()));
        cobro.setNumero_contador_cobro(tblSuscripcion.getValueAt(fila, 9).toString());
        cobro.setValor_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 10).toString()));
        cobro.setValor_excedente(Double.parseDouble(tblSuscripcion.getValueAt(fila, 11).toString()));
        cobro.setValor_penalizacion_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 12).toString()));
        cobro.setAcumulado_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 13).toString()));
        cobro.setTotal_cobro(Double.parseDouble(tblSuscripcion.getValueAt(fila, 14).toString()));
        cobro.setForma_pago_cobro(tblSuscripcion.getValueAt(fila, 15).toString());
        cobro.setEstado_cobro(convertirEstado(tblSuscripcion.getValueAt(fila, 16).toString()));
        cobro.setFactura_cobro(Integer.parseInt(tblSuscripcion.getValueAt(fila, 17).toString()));
        
        if (acceso_cobro.actualizarCobro(cobro, Inventory.txtUsuario.getText()) == true) {
            tblSuscripcion.setValueAt("Guardado", fila, 16);
        }
        
        mensaje.manipulacionExcepciones("informacion", "Registros guardados satisfactoriamente");
    }

    private void visualizarRegistros() {
        
        String servicio;
        double cobro;
        double penalizacion;
        double monto_penalizacion;
        double excedente;
        double acumulado;
        double total;
        
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosCobro> lista = new ArrayList();
        ArrayList<ObjetosPenalizacion> lista_penalizacion = new ArrayList();
        
        if(txtIdServicio.getText().isEmpty()){
            servicio = "null";
        } else {
            servicio = txtIdServicio.getText();
        }
        
        lista = acceso_cobro.verificarCobros(txtMesCobro.getText(), txtAnioCobro.getText(), servicio);
        lista_penalizacion = acceso_penalizacion.listarPenalizacion();
        
        tabla.addColumn("ID Cobro");
        tabla.addColumn("ID Suscripcion");
        tabla.addColumn("ID Cliente");
        tabla.addColumn("Nombre");
        tabla.addColumn("Año");
        tabla.addColumn("Mes");
        tabla.addColumn("Fecha Puntual");
        tabla.addColumn("Fecha Maxima");
        tabla.addColumn("Dias Atrazo");
        tabla.addColumn("No. Contdor");
        tabla.addColumn("Cobro Servicio");
        tabla.addColumn("Excedente");
        tabla.addColumn("Penalizacion");
        tabla.addColumn("Acumulado");
        tabla.addColumn("Total");
        tabla.addColumn("Forma de Pago");
        tabla.addColumn("Estado");
        tabla.addColumn("Factura");
        
        tabla.setRowCount(lista.size());
        int fila = 0;
        
        for(ObjetosCobro x : lista) {
            
            cobro = 0.0;
            penalizacion = 0.0;
            monto_penalizacion = 0.0;
            excedente = 0.0;
            total = 0.0;
            
            tabla.setValueAt(x.getId_cobro(), fila, 0);
            tabla.setValueAt(x.getId_suscripcion(), fila, 1);
            tabla.setValueAt(x.getId_cliente(), fila, 2);
            tabla.setValueAt(x.getNombre_cliente(), fila, 3);
            tabla.setValueAt(x.getAnio_cobro(), fila, 4);
            tabla.setValueAt(x.getMes_cobro(), fila, 5);
            tabla.setValueAt(x.getFecha_puntual_cobro(), fila, 6);
            tabla.setValueAt(x.getFecha_maxima_cobro(), fila, 7);
            tabla.setValueAt(x.getDias_atrazo_cobro(), fila, 8);
            tabla.setValueAt(x.getNumero_contador_cobro(), fila, 9);
            tabla.setValueAt(x.getValor_cobro(), fila, 10);
            tabla.setValueAt(x.getValor_excedente(), fila, 11);
            
            //MOSTRAR LA TASA DE PENALIZACION
            for (ObjetosPenalizacion y : lista_penalizacion) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    if (x.getDias_atrazo_cobro() >= y.getDias_atrazo_penalizacion()) {
                        penalizacion = Double.parseDouble(String.valueOf(y.getTaza_penalizacion()));
                    }
                }
            }
            
            excedente = x.getValor_excedente();
            cobro = Double.parseDouble(String.valueOf(x.getValor_cobro()));
            monto_penalizacion = (cobro + excedente) * (1 + (penalizacion / 100));
            total = cobro + excedente + monto_penalizacion;

            tabla.setValueAt(x.getValor_penalizacion_cobro(), fila, 12);
            tabla.setValueAt(x.getAcumulado_cobro(), fila, 13);
            tabla.setValueAt(x.getTotal_cobro(), fila, 14);
            tabla.setValueAt(x.getForma_pago_cobro(), fila, 15);
            tabla.setValueAt(convertirEstado(x.getEstado_cobro()), fila, 16);
            tabla.setValueAt(x.getFactura_cobro(), fila, 17);
            fila++;
            
        }
        
        tblSuscripcion.setModel(tabla);
        mensaje.manipulacionExcepciones("informacion", "Se encontraron " +  tblSuscripcion.getRowCount() + " registros");
    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdServicio.setText("");     
        txtMesCobro.setText("");
        txtAnioCobro.setText("");
    }
    
    private void cargarServicios() {
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.mostrarServicios();
        for(ObjetosProducto x : lista){
            cbxServicio.addItem(x.getNombre_servicio());
        }
    }
    
    private void buscarRegistros(){
        
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosCobro> lista = new ArrayList();
        
        lista = acceso_cobro.buscarCobros(txtIdCobro.getText(), txtMesCobro.getText(), txtAnioCobro.getText());
        
        tabla.addColumn("Id Cobro");
        tabla.addColumn("Id Suscripcion");
        tabla.addColumn("id_cliente");
        tabla.addColumn("Nombre");
        tabla.addColumn("Año");
        tabla.addColumn("Mes");
        tabla.addColumn("Fecha Puntual");
        tabla.addColumn("Fecha Maxima");
        tabla.addColumn("Dias Atrazo");
        tabla.addColumn("No. Contador");
        tabla.addColumn("Cobro Servicio");
        tabla.addColumn("Penalizacion");
        tabla.addColumn("Total");
        tabla.addColumn("Forma de Pago");
        tabla.addColumn("Estado");
        tabla.addColumn("Factura");
        
        tabla.setRowCount(lista.size());
        int fila = 0;
        
        for(ObjetosCobro x : lista) {
            tabla.setValueAt(x.getId_cobro(), fila, 0);
            tabla.setValueAt(x.getId_suscripcion(), fila, 1);
            tabla.setValueAt(x.getId_cliente(), fila, 2);
            tabla.setValueAt(x.getNombre_cliente(), fila, 3);
            tabla.setValueAt(x.getAnio_cobro(), fila, 4);
            tabla.setValueAt(x.getMes_cobro(), fila, 5);
            tabla.setValueAt(x.getFecha_puntual_cobro(), fila, 6);
            tabla.setValueAt(x.getFecha_maxima_cobro(), fila, 7);
            tabla.setValueAt(x.getDias_atrazo_cobro(), fila, 8);
            tabla.setValueAt(x.getNumero_contador_cobro(), fila, 9);
            tabla.setValueAt(x.getValor_cobro(), fila, 10);
            tabla.setValueAt(x.getValor_penalizacion_cobro(), fila, 11);
            tabla.setValueAt(x.getTotal_cobro(), fila, 12);
            tabla.setValueAt(x.getForma_pago_cobro(), fila, 13);
            tabla.setValueAt(convertirEstado(x.getEstado_cobro()), fila, 14);
            tabla.setValueAt(x.getFactura_cobro(), fila, 15);
            fila++;
        }
        
        tblSuscripcion.setModel(tabla);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtMesCobro = new javax.swing.JTextField();
        txtIdServicio = new javax.swing.JTextField();
        cbxServicio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtAnioCobro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbxFormaPagoCobro = new javax.swing.JComboBox();
        btnGenerar = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        prbBarra = new javax.swing.JProgressBar();
        pnlBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtIdCobro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuscripcion = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Genracion de Cobros");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/exportar.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 550));
        setMinimumSize(new java.awt.Dimension(700, 550));
        setPreferredSize(new java.awt.Dimension(700, 550));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMarcaProducto.setText("Servicio");

        lblDescProducto.setText("Mes");
        lblDescProducto.setToolTipText("");

        txtMesCobro.setToolTipText("Ingrese la descripcion del producto");

        txtIdServicio.setToolTipText("Ingrese la marca del Producto");

        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        jLabel2.setText("Año");

        jLabel1.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abono", "Ajuste", "Inactivo", "Pendiente", "Cancelado" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Pago");

        cbxFormaPagoCobro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pendiente", "Efectivo", "Cheque", "Tarjeta" }));
        cbxFormaPagoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFormaPagoCobroActionPerformed(evt);
            }
        });

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/exportar.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/nuevo.png"))); // NOI18N
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        txtTotal.setBackground(new java.awt.Color(102, 102, 102));
        txtTotal.setEditable(false);
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        jLabel5.setText("Total");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblDescProducto)
                        .addGap(18, 18, 18)
                        .addComponent(txtMesCobro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnioCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblMarcaProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(prbBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxEstado, 0, 83, Short.MAX_VALUE)
                    .addComponent(cbxFormaPagoCobro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMesCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto)
                    .addComponent(jLabel2)
                    .addComponent(txtAnioCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar)
                    .addComponent(lblMarcaProducto)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxFormaPagoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(btnVerificar)
                        .addComponent(jLabel5))
                    .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(prbBarra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar");

        txtIdCobro.setToolTipText("Ingrese la descripcion del producto");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdCobro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(jLabel4)
                    .addComponent(txtIdCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblSuscripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSuscripcion.getTableHeader().setReorderingAllowed(false);
        tblSuscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuscripcionCllicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuscripcion);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/impresora.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if(verificarRegistros() == true) {
            mensaje.manipulacionExcepciones("informacion", "Ya fueron generados recibos para este mes");
        } else {
            generarRegistros();
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        visualizarRegistros();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//GEN-FIRST:event_txtIdProveedorFocusLost
//GEN-LAST:event_txtIdProveedorFocusLost

    private void tblSuscripcionCllicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuscripcionCllicked
        btnGuardar.setEnabled(false);
        cbxFormaPagoCobro.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 15).toString());
        cbxEstado.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 16).toString());
    }//GEN-LAST:event_tblSuscripcionCllicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.buscarServiciosPorNombre(cbxServicio.getSelectedItem().toString());
        for (ObjetosProducto x : lista) {
            txtIdServicio.setText(String.valueOf(x.getId_servicio()));
        }
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarRegistros();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarRegistros();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizarRegistros();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        tblSuscripcion.setValueAt(cbxEstado.getSelectedItem().toString(), tblSuscripcion.getSelectedRow(), 16);
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void cbxFormaPagoCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFormaPagoCobroActionPerformed
        tblSuscripcion.setValueAt(cbxFormaPagoCobro.getSelectedItem().toString(), tblSuscripcion.getSelectedRow(), 15);
    }//GEN-LAST:event_cbxFormaPagoCobroActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        String empresa = "";
        String direccion = "";
        String serie = "";
        
        try {
            empresa = archivo.leer("[name]");
        } catch (IOException ex) {
            Logger.getLogger(wdwGeneracionDeCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            direccion = archivo.leer("[adress]");
        } catch (IOException ex) {
            Logger.getLogger(wdwGeneracionDeCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            serie = archivo.leer("[serie]");
        } catch (IOException ex) {
            Logger.getLogger(wdwGeneracionDeCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Conexion Acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/inventory/reportes/rptFacturaDobleNueva.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_MES", Integer.parseInt(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 5).toString()));
            parametro.put("P_ANIO", Integer.parseInt(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 4).toString()));
            parametro.put("P_ID_CLIENTE", Integer.parseInt(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 2).toString()));
            parametro.put("P_SERVICIO", Integer.parseInt(txtIdServicio.getText()));
            parametro.put("P_EMPRESA", empresa);
            parametro.put("P_DIRECCION", direccion);
            parametro.put("P_SERIE", "Serie " + serie);
            parametro.put("P_NO_FACTURA", tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 17).toString());
            parametro.put("P_FACTURACION", "30/" + tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 5).toString() + "/" + tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 4).toString());
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, Acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        } catch (JRException error) {
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al ejecutar el reporte -> " + error);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JComboBox cbxFormaPagoCobro;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JProgressBar prbBarra;
    private javax.swing.JTable tblSuscripcion;
    private javax.swing.JTextField txtAnioCobro;
    private javax.swing.JTextField txtIdCobro;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtMesCobro;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}