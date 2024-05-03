/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoUsuario;
import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosUsuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwCatalogoUsuarios extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    AccesoUsuario acceso = new AccesoUsuario();
    ObjetosUsuario usuario = new ObjetosUsuario();

    public wdwCatalogoUsuarios() {
        initComponents();
        mostrarRegistros();
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

    private void guardarRegistro() {
        usuario.setNombre_usuario(txtNombreUsuario.getText());
        usuario.setContrasenia_usuario(txtContraseniaUsuario.getText());
        usuario.setTipo_usuario(convertirEstado(cbxTipoUsuario.getSelectedItem().toString()));
        usuario.setEstado_usuario(convertirEstado(cbxEstadoUsuario.getSelectedItem().toString()));
        acceso.insertarUsuario(usuario, Inventory.txtUsuario.getText());
    }

    private void buscarRegistros() {
        
        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosUsuario> listado = new ArrayList();

        listado = acceso.buscarUsuario(txtBuscar.getText().toUpperCase());

        if (listado.isEmpty()) {
            mensaje.manipulacionExcepciones("critico", "No existen datos de usuarios almacenados");
        } else {

            tabla.setRowCount(listado.size());
            tabla.addColumn("Nombre");
            tabla.addColumn("Contraseña");
            tabla.addColumn("Tipo de Usuario");
            tabla.addColumn("Estado");

            for (ObjetosUsuario x : listado) {
                tabla.setValueAt(x.getNombre_usuario(), fila, 0);
                tabla.setValueAt(x.getContrasenia_usuario(), fila, 1);
                tabla.setValueAt(x.getTipo_usuario(), fila, 2);
                tabla.setValueAt(convertirEstado(x.getEstado_usuario()), fila, 3);
                fila++;
            }

            tblUsuario.setModel(tabla);
        }
    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtNombreUsuario.setText("");
        txtContraseniaUsuario.setText("");
    }

    public void mostrarRegistros() {

        int fila = 0;
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosUsuario> listado = new ArrayList();

        listado = acceso.listarUsuarios();

        if (listado.isEmpty()) {
            mensaje.manipulacionExcepciones("critico", "No existen datos de usuarios almacenados");
        } else {

            tabla.setRowCount(listado.size());
            tabla.addColumn("Nombre");
            tabla.addColumn("Contraseña");
            tabla.addColumn("Tipo de Usuario");
            tabla.addColumn("Estado");

            for (ObjetosUsuario x : listado) {
                tabla.setValueAt(x.getNombre_usuario(), fila, 0);
                tabla.setValueAt(x.getContrasenia_usuario(), fila, 1);
                tabla.setValueAt(x.getTipo_usuario(), fila, 2);
                tabla.setValueAt(convertirEstado(x.getEstado_usuario()), fila, 3);
                fila++;
            }

            tblUsuario.setModel(tabla);
        }
    }

    private void actualizarRegistro() {
        usuario.setNombre_usuario(txtNombreUsuario.getText());
        usuario.setContrasenia_usuario(txtContraseniaUsuario.getText());
        usuario.setTipo_usuario(cbxTipoUsuario.getSelectedItem().toString());
        usuario.setEstado_usuario(convertirEstado(cbxEstadoUsuario.getSelectedItem().toString()));
        acceso.actualizarUsuario(usuario, Inventory.txtUsuario.getText());
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
        txtNombreUsuario = new javax.swing.JTextField();
        lblContraseniaUsuario = new javax.swing.JLabel();
        txtContraseniaUsuario = new javax.swing.JTextField();
        lblTipoUsuario = new javax.swing.JLabel();
        cbxTipoUsuario = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cbxEstadoUsuario = new javax.swing.JComboBox();
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
        tblUsuario = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/clientes.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreUsuario.setText("Nombre");
        lblNombreUsuario.setToolTipText("");

        txtNombreUsuario.setToolTipText("Ingrese la descripcion del producto");

        lblContraseniaUsuario.setText("Contraseña");

        txtContraseniaUsuario.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        lblTipoUsuario.setText("Tipo");

        cbxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Supervisor", "Auxiliar Oficina" }));

        jLabel1.setText("Estado");

        cbxEstadoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoUsuario)
                    .addComponent(lblNombreUsuario))
                .addGap(10, 10, 10)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreUsuario)
                    .addComponent(cbxTipoUsuario, 0, 261, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContraseniaUsuario)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContraseniaUsuario)
                    .addComponent(cbxEstadoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseniaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContraseniaUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoUsuario)
                    .addComponent(cbxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtContraseniaUsuario.getAccessibleContext().setAccessibleName("txtPrecioCompra");

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

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsuario.getTableHeader().setReorderingAllowed(false);
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarRegistro();
        mostrarRegistros();
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

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        btnGuardar.setEnabled(false);
        txtNombreUsuario.setText(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 0)));
        txtContraseniaUsuario.setText(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 1)));
        cbxTipoUsuario.setSelectedItem(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 2)));
        cbxEstadoUsuario.setSelectedItem(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 3)));
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtNombreUsuario.getText().isEmpty()) {
            mensaje.manipulacionExcepciones("informacion", "Debe seleccionar el registro a modificar");
        } else {
            actualizarRegistro();
            mostrarRegistros();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEstadoUsuario;
    private javax.swing.JComboBox cbxTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContraseniaUsuario;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContraseniaUsuario;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}