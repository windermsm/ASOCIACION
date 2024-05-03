/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoAbona;
import inventory.acceso.Mensaje;
import inventory.acceso.Conexion;
import inventory.objetos.ObjetosAbona;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwContabilidadAbonoCredito extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    AccesoAbona acceso = new AccesoAbona();
    ObjetosAbona objeto = new ObjetosAbona();

    public wdwContabilidadAbonoCredito() {
        initComponents();
        txtCompleto.setText("NO");
        //mostrar la fecha del dia segun la PC
        Date fecha = new Date();
        String mes = "";
        String dia = "";
        
        if (fecha.getMonth() + 1 < 10) {
            mes = "0" + (fecha.getMonth()+1);
        } else {
            mes = String.valueOf(fecha.getMonth()+1);
        }
        
        if (fecha.getDate() < 10) {
            dia = "0" + fecha.getDate();
        } else {
            dia = String.valueOf(fecha.getDate());
        }

        String hoy = (1900 + fecha.getYear()) + "-" + mes + "-" +dia;
        txtFecha.setText(hoy);
    }

    private void guardarRegistro() {
        if(verificarRegistros()) {
            objeto.setId_credito(Integer.parseInt(txtIdCredito.getText()));
            objeto.setCantidad_cuotas_abona(Integer.parseInt(txtCantidadCuotas.getText()));
            objeto.setMonto_abona(Double.parseDouble(txtMonto.getText()));
            objeto.setFecha_factura_abona(txtFecha.getText());
            objeto.setSerie_factura_abona(txtSerie.getText());
            objeto.setNumero_factura_abona(Integer.parseInt(txtNoFactura.getText()));
            objeto.setNit_cliente_abona(txtNit.getText());
            objeto.setNombre_cliente_abona(txtNombre.getText());
            objeto.setDireccion_cliente_abona(txtDireecion.getText());
            objeto.setMotivo_abona(txtMotivo.getText());
            objeto.setEstado_abona(cbxEstado.getSelectedItem().toString());
            objeto.setUsuario_abona(Inventory.txtUsuario.getText());
            acceso.insertaAbona(objeto, Inventory.txtUsuario.getText()); 
        }
    }

    private void buscarRegistros() {
        mostrarRegistros();
    }

    public void mostrarRegistros() {

        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosAbona> lista = new ArrayList();

        lista = acceso.listarAbona(txtIdCredito.getText());

        tabla.setRowCount(lista.size());
        tabla.addColumn("ID Abona");
        tabla.addColumn("ID Credito");
        tabla.addColumn("Cuotas");
        tabla.addColumn("Monto");
        tabla.addColumn("Fecha");
        tabla.addColumn("Serie");
        tabla.addColumn("Numero");
        tabla.addColumn("NIT");
        tabla.addColumn("Nombre");
        tabla.addColumn("Direccion");
        tabla.addColumn("Motivo");
        tabla.addColumn("Estado");
        tabla.addColumn("Usuario");
        tabla.addColumn("Fecha");

        for (ObjetosAbona x : lista) {
            tabla.setValueAt(x.getId_abona(), fila, 0);
            tabla.setValueAt(x.getId_credito(), fila, 1);
            tabla.setValueAt(x.getCantidad_cuotas_abona(), fila, 2);
            tabla.setValueAt(x.getMonto_abona(), fila, 3);
            tabla.setValueAt(x.getFecha_factura_abona(), fila, 4);
            tabla.setValueAt(x.getSerie_factura_abona(), fila, 5);
            tabla.setValueAt(x.getNumero_factura_abona(), fila, 6);
            tabla.setValueAt(x.getNit_cliente_abona(), fila, 7);
            tabla.setValueAt(x.getNombre_cliente_abona(), fila, 8);
            tabla.setValueAt(x.getDireccion_cliente_abona(), fila, 9);
            tabla.setValueAt(x.getMotivo_abona(), fila, 10);
            tabla.setValueAt(x.getEstado_abona(), fila, 11);
            tabla.setValueAt(x.getUsuario_abona(), fila, 12);
            tabla.setValueAt(x.getFecha_abona(), fila, 13);
            fila++;
        }

        tblAbona.setModel(tabla);
        
    }

    private void actualizarRegistro() {
        if(txtIdAbona.getText().isEmpty()) {
            mensaje.manipulacionExcepciones("informacion", "Seleccione un abono para actualizar");
        } else {
            objeto.setId_abona(Integer.parseInt(txtIdAbona.getText()));
            objeto.setId_credito(Integer.parseInt(txtIdCredito.getText()));
            objeto.setCantidad_cuotas_abona(Integer.parseInt(txtCantidadCuotas.getText()));
            objeto.setMonto_abona(Double.parseDouble(txtMonto.getText()));
            objeto.setFecha_factura_abona(txtFecha.getText());
            objeto.setSerie_factura_abona(txtSerie.getText());
            objeto.setNumero_factura_abona(Integer.parseInt(txtNoFactura.getText()));
            objeto.setNit_cliente_abona(txtNit.getText());
            objeto.setNombre_cliente_abona(txtNombre.getText());
            objeto.setDireccion_cliente_abona(txtDireecion.getText());
            objeto.setMotivo_abona(txtMotivo.getText());
            objeto.setEstado_abona(cbxEstado.getSelectedItem().toString());
            objeto.setUsuario_abona(Inventory.txtUsuario.getText());
            acceso.actualizarAbona(objeto, Inventory.txtUsuario.getText());
        }
    }
    
    private boolean verificarRegistros() {
        boolean grabar = true;
        if(txtSerie.getText().isEmpty()){ grabar = false; }
        if(txtNoFactura.getText().isEmpty()) { grabar = false; }
        if(txtNit.getText().isEmpty()){ grabar = false; }
        if(txtFecha.getText().isEmpty()){ grabar = false; }
        if(txtCantidadCuotas.getText().isEmpty()) { grabar = false; }
        if(txtNombre.getText().isEmpty()){ grabar = false; }
        if(txtDireecion.getText().isEmpty()){ grabar = false; }
        if(txtMonto.getText().isEmpty()){ grabar = false; }
        if(txtMotivo.getText().isEmpty()){ grabar = false; }
        return grabar;
    }
    
    private void limpiarCampos(){
        btnGuardar.setEnabled(true);
        txtIdAbona.setText("");
        txtSerie.setText("");
        txtNoFactura.setText("");
        txtNit.setText("");
        txtFecha.setText("");
        txtCantidadCuotas.setText("");
        txtCantidadCuotas.setText("");
    }
    
    private void imprimirFactura(){
        System.out.println("Intentando genera la factua");
        try {
            mensaje.manipulacionExcepciones("critio", ""+txtNoFactura.getText().length());
            if (txtNoFactura.getText().length() == 0) {
                mensaje.manipulacionExcepciones("critico", "Ingrese el numero de la factura");
            } else {
                Date fecha = new Date();
                String hoy = fecha.getDate() + "/" +  (fecha.getMonth()+1) + "/" +  (1900 + fecha.getYear());
                Conexion Acceso = new Conexion();
                URL url_reporte = this.getClass().getResource("/inventory/reportes/rptFacturaAbona.jasper");
                JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
                HashMap parametro = new HashMap();
                parametro.put("P_FACTURACION", hoy);
                parametro.put("P_SERIE", txtSerie.getText());
                parametro.put("P_NO_FACTURA", Integer.parseInt(txtNoFactura.getText()));
                JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, Acceso.conectar());
                JasperViewer visualizador = new JasperViewer(pantalla, false);
                visualizador.show();
            }
        } catch (JRException error) {
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al ejecutar el reporte -> " + error);
        }
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
        lblNombreUsuario = new javax.swing.JLabel();
        txtIdAbona = new javax.swing.JTextField();
        lblContraseniaUsuario = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        txtNoFactura = new javax.swing.JFormattedTextField();
        lblContraseniaUsuario1 = new javax.swing.JLabel();
        lblNombreUsuario1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblContraseniaUsuario3 = new javax.swing.JLabel();
        lblContraseniaUsuario4 = new javax.swing.JLabel();
        cbxCuenta = new javax.swing.JComboBox();
        lblNombreUsuario2 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        txtDireecion = new javax.swing.JTextField();
        lblContraseniaUsuario2 = new javax.swing.JLabel();
        txtCantidadCuotas = new javax.swing.JTextField();
        lblContraseniaUsuario5 = new javax.swing.JLabel();
        txtIdCredito = new javax.swing.JTextField();
        lblContraseniaUsuario6 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        lblNombreUsuario3 = new javax.swing.JLabel();
        txtCuotasPendientes = new javax.swing.JTextField();
        lblNombreUsuario4 = new javax.swing.JLabel();
        txtCuotasTotales = new javax.swing.JTextField();
        lblNombreUsuario5 = new javax.swing.JLabel();
        txtCompleto = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAbona = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Abona credito");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/coins.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreUsuario.setText("ID Abona");
        lblNombreUsuario.setToolTipText("");

        txtIdAbona.setBackground(new java.awt.Color(153, 153, 153));
        txtIdAbona.setForeground(new java.awt.Color(255, 255, 255));
        txtIdAbona.setToolTipText("Ingrese la descripcion del producto");

        lblContraseniaUsuario.setText("Factura");

        txtSerie.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        jLabel1.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        txtNoFactura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblContraseniaUsuario1.setText("Cliente");

        lblNombreUsuario1.setText("Fecha");
        lblNombreUsuario1.setToolTipText("");

        lblContraseniaUsuario3.setText("Cuotas");

        lblContraseniaUsuario4.setText("Cuenta");

        cbxCuenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Credito" }));

        lblNombreUsuario2.setText("Motivo");
        lblNombreUsuario2.setToolTipText("");

        txtMotivo.setBackground(new java.awt.Color(153, 153, 153));
        txtMotivo.setEditable(false);
        txtMotivo.setForeground(new java.awt.Color(255, 255, 255));
        txtMotivo.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        txtMonto.setBackground(new java.awt.Color(153, 153, 153));
        txtMonto.setEditable(false);
        txtMonto.setForeground(new java.awt.Color(255, 255, 255));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));

        txtNit.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        txtDireecion.setBackground(new java.awt.Color(153, 153, 153));
        txtDireecion.setEditable(false);
        txtDireecion.setForeground(new java.awt.Color(255, 255, 255));

        lblContraseniaUsuario2.setText("Direccion");

        txtCantidadCuotas.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");
        txtCantidadCuotas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CantidadCuotasSetFocus(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CantidadCuotasLostFocus(evt);
            }
        });

        lblContraseniaUsuario5.setText("ID Credito");

        txtIdCredito.setBackground(new java.awt.Color(153, 153, 153));
        txtIdCredito.setEditable(false);
        txtIdCredito.setForeground(new java.awt.Color(255, 255, 255));

        lblContraseniaUsuario6.setText("Saldo");

        txtSaldo.setBackground(new java.awt.Color(153, 153, 153));
        txtSaldo.setEditable(false);
        txtSaldo.setForeground(new java.awt.Color(255, 255, 255));

        lblNombreUsuario3.setText("Abona");
        lblNombreUsuario3.setToolTipText("");

        txtCuotasPendientes.setBackground(new java.awt.Color(153, 153, 153));
        txtCuotasPendientes.setEditable(false);
        txtCuotasPendientes.setForeground(new java.awt.Color(255, 255, 255));

        lblNombreUsuario4.setText("de");
        lblNombreUsuario4.setToolTipText("");

        txtCuotasTotales.setBackground(new java.awt.Color(153, 153, 153));
        txtCuotasTotales.setEditable(false);
        txtCuotasTotales.setForeground(new java.awt.Color(255, 255, 255));

        lblNombreUsuario5.setText("Completo");
        lblNombreUsuario5.setToolTipText("");

        txtCompleto.setBackground(new java.awt.Color(153, 153, 153));
        txtCompleto.setEditable(false);
        txtCompleto.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(cbxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreUsuario1)
                            .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(txtIdAbona)))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblContraseniaUsuario5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txtIdCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblContraseniaUsuario4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxCuenta, 0, 128, Short.MAX_VALUE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCantidadCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(txtMonto)))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblContraseniaUsuario6)
                        .addGap(21, 21, 21)
                        .addComponent(txtSaldo)))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblContraseniaUsuario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContraseniaUsuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreUsuario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreUsuario3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                    .addComponent(txtDireecion)
                    .addComponent(txtMotivo)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtCuotasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNombreUsuario4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCuotasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNombreUsuario5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtIdAbona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario)
                    .addComponent(txtNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario3)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireecion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario2)
                    .addComponent(txtCantidadCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario2)
                    .addComponent(lblContraseniaUsuario6)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario5)
                    .addComponent(txtIdCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario4)
                    .addComponent(cbxCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario3)
                    .addComponent(txtCuotasPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario4)
                    .addComponent(txtCuotasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario5)
                    .addComponent(txtCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtSerie.getAccessibleContext().setAccessibleName("txtPrecioCompra");

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblBuscar.setText("Buscar");

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

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBuscar.setToolTipText("Ingrese el nombre del producto que desea buscar");

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(btnSalir)
                        .addComponent(btnLimpiar)
                        .addComponent(btnModificar)
                        .addComponent(btnGuardar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblAbona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAbona.getTableHeader().setReorderingAllowed(false);
        tblAbona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAbonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAbona);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(verificarRegistros()==true  && txtCompleto.getText().equals("SI")){
            guardarRegistro();
            mostrarRegistros();
            imprimirFactura();
        } else {
            mensaje.manipulacionExcepciones("critico", "Porfavor llene todos los campos o verifique la informacion");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarRegistros();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblAbonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAbonaMouseClicked
        btnGuardar.setEnabled(false);
        txtIdAbona.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 0)));
        txtIdCredito.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 1)));
        txtCantidadCuotas.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 2)));
        txtMonto.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 3)));
        txtFecha.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 4)));
        txtSerie.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 5)));
        txtNoFactura.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 6)));
        txtNit.setText(tblAbona.getValueAt(tblAbona.getSelectedRow(), 7).toString());
        txtNombre.setText(tblAbona.getValueAt(tblAbona.getSelectedRow(), 8).toString());
        txtDireecion.setText(tblAbona.getValueAt(tblAbona.getSelectedRow(), 9).toString());
        txtMotivo.setText(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 10)));
        cbxEstado.setSelectedItem(String.valueOf(tblAbona.getValueAt(tblAbona.getSelectedRow(), 11)));
    }//GEN-LAST:event_tblAbonaMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizarRegistro();
        mostrarRegistros();
        imprimirFactura();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void CantidadCuotasLostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CantidadCuotasLostFocus
        
        double v_monto = Double.parseDouble(txtMonto.getText());
        
        int v_cantidad = Integer.parseInt(txtCantidadCuotas.getText());
        int v_pendiente = Integer.parseInt(txtCuotasPendientes.getText());
        int v_cuotas = v_cantidad + v_pendiente;
        
        double v_resultado = v_monto * v_cantidad;
        double v_saldo = Double.parseDouble(txtSaldo.getText()) - v_resultado;
        
        txtMonto.setText(String.valueOf(v_resultado));
        txtMotivo.setText("ABONO CONTADOR NO. " + v_cuotas + " DE " +  txtCuotasTotales.getText());
        txtCuotasPendientes.setText(String.valueOf(v_cuotas));
        txtSaldo.setText(String.valueOf(v_saldo));
        
        txtCompleto.setText("SI");
        
    }//GEN-LAST:event_CantidadCuotasLostFocus

    private void CantidadCuotasSetFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CantidadCuotasSetFocus

        txtCompleto.setText("NO");
        
        double v_monto = Double.parseDouble(txtMonto.getText());
        double v_saldo = Double.parseDouble(txtSaldo.getText());
        
        int v_cantidad = Integer.parseInt(txtCantidadCuotas.getText());
        int v_pendiente = Integer.parseInt(txtCuotasPendientes.getText());
        
        double v_resultado = v_monto / v_cantidad;
        double v_suma = v_monto + v_saldo;
        
        int v_cuotas = v_pendiente - v_cantidad;
        
        txtMonto.setText(String.valueOf(v_resultado));
        txtCuotasPendientes.setText(String.valueOf(v_cuotas));
        txtCantidadCuotas.setText("1");
        txtSaldo.setText(String.valueOf(v_suma));
        
    }//GEN-LAST:event_CantidadCuotasSetFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxCuenta;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContraseniaUsuario;
    private javax.swing.JLabel lblContraseniaUsuario1;
    private javax.swing.JLabel lblContraseniaUsuario2;
    private javax.swing.JLabel lblContraseniaUsuario3;
    private javax.swing.JLabel lblContraseniaUsuario4;
    private javax.swing.JLabel lblContraseniaUsuario5;
    private javax.swing.JLabel lblContraseniaUsuario6;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblNombreUsuario1;
    private javax.swing.JLabel lblNombreUsuario2;
    private javax.swing.JLabel lblNombreUsuario3;
    private javax.swing.JLabel lblNombreUsuario4;
    private javax.swing.JLabel lblNombreUsuario5;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblAbona;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCantidadCuotas;
    public static javax.swing.JTextField txtCompleto;
    public static javax.swing.JTextField txtCuotasPendientes;
    public static javax.swing.JTextField txtCuotasTotales;
    public static javax.swing.JTextField txtDireecion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIdAbona;
    public static javax.swing.JTextField txtIdCredito;
    public static javax.swing.JTextField txtMonto;
    public static javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNit;
    private javax.swing.JFormattedTextField txtNoFactura;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
