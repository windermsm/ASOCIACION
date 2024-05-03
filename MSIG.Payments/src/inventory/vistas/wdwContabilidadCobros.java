/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosIngreso;
import inventory.acceso.AccesoIngreso;
import inventory.acceso.Conexion;
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
public class wdwContabilidadCobros extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    AccesoIngreso acceso = new AccesoIngreso();
    ObjetosIngreso objeto = new ObjetosIngreso();

    public wdwContabilidadCobros() {
        initComponents();
        mostrarRegistros();
    }

    private void guardarRegistro() {
        objeto.setSerie_factura(txtSerie.getText());
        objeto.setNo_factura(Integer.parseInt(txtNoFactura.getText()));
        objeto.setNit_cliente(txtNit.getText());
        objeto.setNombre_cliente(txtNombre.getText());
        objeto.setDireccion(txtDireecion.getText());
        objeto.setFecha_factura(txtFechaDocumento.getText());
        objeto.setMonto(Float.parseFloat(txtMonto.getText()));
        objeto.setCuenta(cbxCuenta.getSelectedItem().toString());
        objeto.setMotivo(txtMotivo.getText());
        objeto.setEstado(cbxEstado.getSelectedItem().toString());
        acceso.insertarIngreso(objeto, Inventory.txtUsuario.getText());
    }

    private void buscarRegistros() {

        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosIngreso> lista = new ArrayList();

        lista = acceso.buscarPago(txtBuscar.getText().toUpperCase());

        tabla.setRowCount(lista.size());
        tabla.addColumn("ID");
        tabla.addColumn("Serie");
        tabla.addColumn("Factura");
        tabla.addColumn("NIT");
        tabla.addColumn("Cliente");
        tabla.addColumn("Direccion");
        tabla.addColumn("Fecha");
        tabla.addColumn("Monto");
        tabla.addColumn("Cuenta");
        tabla.addColumn("Motivo");
        tabla.addColumn("Estado");
        tabla.addColumn("Fecha Grabacion");


        for (ObjetosIngreso x : lista) {
            tabla.setValueAt(x.getId_ingreso(), fila, 0);
            tabla.setValueAt(x.getSerie_factura(), fila, 1);
            tabla.setValueAt(x.getNo_factura(), fila, 2);
            tabla.setValueAt(x.getNit_cliente(), fila, 3);
            tabla.setValueAt(x.getNombre_cliente(), fila, 4);
            tabla.setValueAt(x.getDireccion(), fila, 5);
            tabla.setValueAt(x.getFecha_factura(), fila, 6);
            tabla.setValueAt(x.getMonto(), fila, 7);
            tabla.setValueAt(x.getCuenta(), fila, 8);
            tabla.setValueAt(x.getMotivo(), fila, 9);
            tabla.setValueAt(x.getEstado(), fila, 10);
            tabla.setValueAt(x.getFecha(), fila, 11);
            fila++;
        }

        tblPago.setModel(tabla);

    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdPago.setText("");
        txtSerie.setText("");
        txtNit.setText("");
        txtNombre.setText("");
        txtDireecion.setText("");
        txtFechaDocumento.setText("");
        txtMonto.setText("");
        txtMotivo.setText("");
    }

    public void mostrarRegistros() {

        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosIngreso> lista = new ArrayList();

        lista = acceso.listarPago();

        tabla.setRowCount(lista.size());
        tabla.addColumn("ID");
        tabla.addColumn("Serie");
        tabla.addColumn("Factura");
        tabla.addColumn("NIT");
        tabla.addColumn("Cliente");
        tabla.addColumn("Direccion");
        tabla.addColumn("Fecha");
        tabla.addColumn("Monto");
        tabla.addColumn("Cuenta");
        tabla.addColumn("Motivo");
        tabla.addColumn("Estado");
        tabla.addColumn("Fecha Grabacion");


        for (ObjetosIngreso x : lista) {
            tabla.setValueAt(x.getId_ingreso(), fila, 0);
            tabla.setValueAt(x.getSerie_factura(), fila, 1);
            tabla.setValueAt(x.getNo_factura(), fila, 2);
            tabla.setValueAt(x.getNit_cliente(), fila, 3);
            tabla.setValueAt(x.getNombre_cliente(), fila, 4);
            tabla.setValueAt(x.getDireccion(), fila, 5);
            tabla.setValueAt(x.getFecha_factura(), fila, 6);
            tabla.setValueAt(x.getMonto(), fila, 7);
            tabla.setValueAt(x.getCuenta(), fila, 8);
            tabla.setValueAt(x.getMotivo(), fila, 9);
            tabla.setValueAt(x.getEstado(), fila, 10);
            tabla.setValueAt(x.getFecha(), fila, 11);
            fila++;
        }

        tblPago.setModel(tabla);
        
    }

    private void actualizarRegistro() {
        objeto.setId_ingreso(Integer.parseInt(txtIdPago.getText()));
        objeto.setSerie_factura(txtSerie.getText());
        objeto.setNo_factura(Integer.parseInt(txtNoFactura.getText()));
        objeto.setNit_cliente(txtNit.getText());
        objeto.setNombre_cliente(txtNombre.getText());
        objeto.setDireccion(txtDireecion.getText());
        objeto.setFecha_factura(txtFechaDocumento.getText());
        objeto.setMonto(Float.parseFloat(txtMonto.getText()));
        objeto.setCuenta(cbxCuenta.getSelectedItem().toString());
        objeto.setMotivo(txtMotivo.getText());
        objeto.setEstado(cbxEstado.getSelectedItem().toString());
        acceso.actualizarIngreso(objeto, Inventory.txtUsuario.getText());
    }
    
    private boolean verificarRegistros() {
        boolean grabar = true;
        if(txtNit.getText().isEmpty()){ grabar = false; }
        if(txtSerie.getText().isEmpty()){ grabar = false; }
        if(txtNit.getText().isEmpty()){ grabar = false; }
        if(txtNombre.getText().isEmpty()){ grabar = false; }
        if(txtDireecion.getText().isEmpty()){ grabar = false; }
        if(txtMonto.getText().isEmpty()){ grabar = false; }
        if(txtFechaDocumento.getText().isEmpty()){ grabar = false; }
        if(txtMotivo.getText().isEmpty()){ grabar = false; }
        return grabar;
    }
    
    private void imprimirFactura(){
        try {
            mensaje.manipulacionExcepciones("critio", ""+txtNoFactura.getText().length());
            if (txtNoFactura.getText().length() == 0) {
                mensaje.manipulacionExcepciones("critico", "Ingrese el numero de la factura");
            } else {
                Date fecha = new Date();
                String hoy = fecha.getDate() + "/" +  (fecha.getMonth()+1) + "/" +  (1900 + fecha.getYear());
                Conexion Acceso = new Conexion();
                URL url_reporte = this.getClass().getResource("/inventory/reportes/rptFacturaIngreso.jasper");
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
        txtIdPago = new javax.swing.JTextField();
        lblContraseniaUsuario = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        txtNoFactura = new javax.swing.JFormattedTextField();
        lblContraseniaUsuario1 = new javax.swing.JLabel();
        lblNombreUsuario1 = new javax.swing.JLabel();
        txtFechaDocumento = new javax.swing.JTextField();
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
        tblPago = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cobros");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/spending.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreUsuario.setText("ID Cobro");
        lblNombreUsuario.setToolTipText("");

        txtIdPago.setBackground(new java.awt.Color(153, 153, 153));
        txtIdPago.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPago.setToolTipText("Ingrese la descripcion del producto");

        lblContraseniaUsuario.setText("Factura");

        txtSerie.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        jLabel1.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        txtNoFactura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblContraseniaUsuario1.setText("Cliente");

        lblNombreUsuario1.setText("Fecha");
        lblNombreUsuario1.setToolTipText("");

        lblContraseniaUsuario3.setText("Monto");

        lblContraseniaUsuario4.setText("Cuenta");

        cbxCuenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alquiler", "Conexion", "Contador", "Impresion", "Servicio", "Tonel", "Reconexion", "Otros", "Venta" }));

        lblNombreUsuario2.setText("Motivo");
        lblNombreUsuario2.setToolTipText("");

        txtMotivo.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        txtNit.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        lblContraseniaUsuario2.setText("Direccion");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNombreUsuario1)
                        .addComponent(lblNombreUsuario2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                            .addComponent(txtIdPago))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario3)
                                .addGap(18, 18, 18)
                                .addComponent(txtMonto))
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtMotivo))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEncabezadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContraseniaUsuario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContraseniaUsuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCuenta, 0, 118, Short.MAX_VALUE))
                            .addComponent(txtNombre)))
                    .addComponent(txtDireecion))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtIdPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario)
                    .addComponent(txtNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario1)
                    .addComponent(txtFechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario3)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireecion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario2)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario4)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
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

        tblPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPago.getTableHeader().setReorderingAllowed(false);
        tblPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPago);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
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
        if(verificarRegistros()==true){
            guardarRegistro();
            mostrarRegistros();
            imprimirFactura();
        } else {
            mensaje.manipulacionExcepciones("critico", "Porfavor llene todos los campos");
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

    private void tblPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagoMouseClicked
        btnGuardar.setEnabled(false);
        txtIdPago.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 0)));
        txtSerie.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 1)));
        txtNoFactura.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 2)));
        txtNit.setText(tblPago.getValueAt(tblPago.getSelectedRow(), 3).toString());
        txtNombre.setText(tblPago.getValueAt(tblPago.getSelectedRow(), 4).toString());
        txtDireecion.setText(tblPago.getValueAt(tblPago.getSelectedRow(), 5).toString());
        txtFechaDocumento.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 6)));
        txtMonto.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 7)));
        cbxCuenta.setSelectedItem(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 8)));
        txtMotivo.setText(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 9)));
        cbxEstado.setSelectedItem(String.valueOf(tblPago.getValueAt(tblPago.getSelectedRow(), 10)));
    }//GEN-LAST:event_tblPagoMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizarRegistro();
        mostrarRegistros();
        imprimirFactura();
    }//GEN-LAST:event_btnModificarActionPerformed

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
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblNombreUsuario1;
    private javax.swing.JLabel lblNombreUsuario2;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblPago;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireecion;
    private javax.swing.JTextField txtFechaDocumento;
    private javax.swing.JTextField txtIdPago;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNit;
    private javax.swing.JFormattedTextField txtNoFactura;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
