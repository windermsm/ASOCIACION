/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoPenalizacion;
import inventory.acceso.AccesoProducto;
import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosPenalizacion;
import inventory.objetos.ObjetosProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwCatalogoPenalizacion extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    ObjetosProducto servicio = new ObjetosProducto();
    ObjetosPenalizacion penalizacion = new ObjetosPenalizacion();
    AccesoProducto acceso_servicio = new AccesoProducto();
    AccesoPenalizacion acceso_penalizacion = new AccesoPenalizacion();

    /**
     * Creates new form wdwCatalogoProductos
     */
    public wdwCatalogoPenalizacion() {
        initComponents();
        mostrarRegistros();
        cargarServicios();
    }
    
    private String convertirEstado(String x) {
        String valor = "";
        if (x.equals("A")) {
            valor = "Activo";
        }
        if (x.equals("I")) {
            valor = "Inactivo";
        }
        if (x.equals("Activo")) {
            valor = "A";
        }
        if (x.equals("Inactivo")) {
            valor = "I";
        }
        return valor;
    }

    private void buscarRegistros() {

        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosPenalizacion> lista_penalizacion = new ArrayList();
        ArrayList<ObjetosProducto> lista_servicios = new ArrayList();

        lista_penalizacion = acceso_penalizacion.buscarPenalizacionPorServicio(txtBuscar.getText());

        tabla.addColumn("ID Penalizacion");
        tabla.addColumn("ID");
        tabla.addColumn("Servicio");
        tabla.addColumn("Dias de Atrazo");
        tabla.addColumn("Taza de Interes");
        tabla.addColumn("Estado");

        tabla.setRowCount(lista_penalizacion.size());
        

        for (ObjetosPenalizacion x : lista_penalizacion) {
            
            lista_servicios = acceso_servicio.buscarServiciosPorId(String.valueOf(x.getId_servicio()));

            tabla.setValueAt(x.getId_penalizacion(), fila, 0);
            tabla.setValueAt(x.getId_servicio(), fila, 1);
            for (ObjetosProducto y : lista_servicios) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    tabla.setValueAt(y.getNombre_servicio(), fila, 2);
                }
            }
            tabla.setValueAt(x.getDias_atrazo_penalizacion(), fila, 3);
            tabla.setValueAt(x.getTaza_penalizacion(), fila, 4);
            tabla.setValueAt(convertirEstado(x.getEstado_penalizacion()), fila, 5);
            fila++;
        }

        tblPenalizacion.setModel(tabla);
    }

    private void guardarRegistros() {
        penalizacion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        penalizacion.setDias_atrazo_penalizacion(Integer.parseInt(txtDiasAtrazoPenalizacion.getText()));
        penalizacion.setTaza_penalizacion(Integer.parseInt(txtTazaPenalizacion.getText()));
        penalizacion.setEstado_penalizacion(convertirEstado(cbxEstadoPenalizacion.getSelectedItem().toString()));
        acceso_penalizacion.insertarPenalizacion(penalizacion, Inventory.txtUsuario.getText());
    }

    private void actualizarRegistros() {
        penalizacion.setId_penalizacion(Integer.parseInt(txtIdPenalizacion.getText()));
        penalizacion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        penalizacion.setDias_atrazo_penalizacion(Integer.parseInt(txtDiasAtrazoPenalizacion.getText()));
        penalizacion.setTaza_penalizacion(Integer.parseInt(txtTazaPenalizacion.getText()));
        penalizacion.setEstado_penalizacion(convertirEstado(cbxEstadoPenalizacion.getSelectedItem().toString()));
        acceso_penalizacion.actualizarPenalizacion(penalizacion, Inventory.txtUsuario.getText());
    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdPenalizacion.setText("");
        txtIdServicio.setText("");
        txtDiasAtrazoPenalizacion.setText("");
        txtTazaPenalizacion.setText("");
    }
    
    private void cargarServicios() {
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.mostrarServiciosActivos();
        for(ObjetosProducto x : lista){
            cbxServicio.addItem(x.getNombre_servicio());
        }
    }

    private void mostrarRegistros() {
        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosPenalizacion> lista_penalizacion = new ArrayList();
        ArrayList<ObjetosProducto> lista_servicios = new ArrayList();

        lista_penalizacion = acceso_penalizacion.mostrarPenalizacionesActivas();

        tabla.addColumn("ID Penalizacion");
        tabla.addColumn("ID");
        tabla.addColumn("Servicio");
        tabla.addColumn("Dias de Atrazo");
        tabla.addColumn("Taza de Interes");
        tabla.addColumn("Estado");

        tabla.setRowCount(lista_penalizacion.size());
        

        for (ObjetosPenalizacion x : lista_penalizacion) {
            
            lista_servicios = acceso_servicio.buscarServiciosPorId(String.valueOf(x.getId_servicio()));

            tabla.setValueAt(x.getId_penalizacion(), fila, 0);
            tabla.setValueAt(x.getId_servicio(), fila, 1);
            for (ObjetosProducto y : lista_servicios) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    tabla.setValueAt(y.getNombre_servicio(), fila, 2);
                }
            }
            tabla.setValueAt(x.getDias_atrazo_penalizacion(), fila, 3);
            tabla.setValueAt(x.getTaza_penalizacion(), fila, 4);
            tabla.setValueAt(convertirEstado(x.getEstado_penalizacion()), fila, 5);
            fila++;
        }

        tblPenalizacion.setModel(tabla);
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
        lblIdProducto = new javax.swing.JLabel();
        txtIdPenalizacion = new javax.swing.JTextField();
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtDiasAtrazoPenalizacion = new javax.swing.JTextField();
        lblPrecioEstProducto = new javax.swing.JLabel();
        lblMinimoProducto = new javax.swing.JLabel();
        txtTazaPenalizacion = new javax.swing.JTextField();
        txtIdServicio = new javax.swing.JTextField();
        cbxServicio = new javax.swing.JComboBox();
        cbxEstadoPenalizacion = new javax.swing.JComboBox();
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
        tblPenalizacion = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Penalizacion");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/agente.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID");

        txtIdPenalizacion.setBackground(new java.awt.Color(153, 153, 153));
        txtIdPenalizacion.setForeground(new java.awt.Color(255, 255, 255));
        txtIdPenalizacion.setToolTipText("Ingrese el codigo del producto");

        lblMarcaProducto.setText("Servicio");

        lblDescProducto.setText("Dias Atrazo");
        lblDescProducto.setToolTipText("");

        txtDiasAtrazoPenalizacion.setToolTipText("Ingrese la descripcion del producto");

        lblPrecioEstProducto.setText("Taza");

        lblMinimoProducto.setText("Estado ");

        txtTazaPenalizacion.setToolTipText("Ingrese el porcentaje de ganancia Estandar o Normal");

        txtIdServicio.setBackground(new java.awt.Color(153, 153, 153));
        txtIdServicio.setEditable(false);
        txtIdServicio.setForeground(new java.awt.Color(255, 255, 255));
        txtIdServicio.setToolTipText("Ingrese la marca del Producto");

        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        cbxEstadoPenalizacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblIdProducto)
                        .addGap(28, 28, 28)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTazaPenalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(txtIdPenalizacion))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblMarcaProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblMinimoProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxEstadoPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiasAtrazoPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblPrecioEstProducto)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto)
                    .addComponent(txtDiasAtrazoPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTazaPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioEstProducto)
                    .addComponent(lblMinimoProducto)
                    .addComponent(cbxEstadoPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
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

        tblPenalizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPenalizacion.getTableHeader().setReorderingAllowed(false);
        tblPenalizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenalizacionCllicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPenalizacion);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarRegistros();
        mostrarRegistros();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarRegistros();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        actualizarRegistros();
        mostrarRegistros();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//GEN-FIRST:event_txtIdProveedorFocusLost
//GEN-LAST:event_txtIdProveedorFocusLost

    private void tblPenalizacionCllicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenalizacionCllicked
        btnGuardar.setEnabled(false);
        txtIdPenalizacion.setText(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 0).toString());
        txtIdServicio.setText(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 1).toString());
        cbxServicio.setSelectedItem(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 2));
        txtDiasAtrazoPenalizacion.setText(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 3).toString());
        txtTazaPenalizacion.setText(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 4).toString());
        cbxEstadoPenalizacion.setSelectedItem(tblPenalizacion.getValueAt(tblPenalizacion.getSelectedRow(), 5));
    }//GEN-LAST:event_tblPenalizacionCllicked

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.buscarServiciosPorNombre(cbxServicio.getSelectedItem().toString());
        for(ObjetosProducto x : lista){
            txtIdServicio.setText(String.valueOf(x.getId_servicio()));
        }  
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEstadoPenalizacion;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JLabel lblMinimoProducto;
    private javax.swing.JLabel lblPrecioEstProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblPenalizacion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDiasAtrazoPenalizacion;
    private javax.swing.JTextField txtIdPenalizacion;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtTazaPenalizacion;
    // End of variables declaration//GEN-END:variables
}