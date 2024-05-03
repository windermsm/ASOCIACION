/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.*;
import inventory.objetos.ObjetosCliente;
import inventory.objetos.ObjetosLectura;
import inventory.objetos.ObjetosProducto;
import inventory.objetos.ObjetosSuscripcion;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwMovimientoIngresoLecturas extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    
    ObjetosLectura lectura = new ObjetosLectura();
    ObjetosProducto servicio = new ObjetosProducto();
    ObjetosCliente cliente = new ObjetosCliente();
    ObjetosSuscripcion suscripcion = new ObjetosSuscripcion();
    
    AccesoLectura acceso_lectura = new AccesoLectura();
    AccesoProducto acceso_servicio = new AccesoProducto();
    AccesoCliente acceso_cliente = new AccesoCliente();
    AccesoSuscripcion acceso_suscripcion = new AccesoSuscripcion();
    
    public wdwMovimientoIngresoLecturas() {
        initComponents();
        cargarServicios();
        cargarClientes();
    }
    
    private String convertirEstado(String x) {
        String valor = "";
        if (x.equals("A")) {
            valor = "Activo";
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
        if (x.equals("Activo")) {
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
        return valor;
    }
    
    private boolean verificarLectura(){
        boolean valor = false;
        int lecturas = acceso_lectura.verifarLectura(txtIdSuscripcion.getText(), txtMesLectura.getText(), txtAnioLectura.getText());
        //verificar si ya ingresaron una lectura
        if(lecturas > 0){
            valor = false;
            mensaje.manipulacionExcepciones("critico", "Ya tiene lectura ingresada");
        } else {
            valor = true;
        }
        System.out.println("Lecturas encontradas: "+lecturas);
        return valor;
    }

    private void guardarRegistros() {
        if(verificarLectura()){
        lectura.setId_suscripcion(Integer.parseInt(txtIdSuscripcion.getText()));
        lectura.setAnio_lectura(Integer.parseInt(txtAnioLectura.getText()));
        lectura.setMes_lectura(Integer.parseInt(txtMesLectura.getText()));
        lectura.setAnterior_lectura(Integer.parseInt(txtAnteriorLectura.getText()));
        lectura.setNueva_lectura(Integer.parseInt(txtNuevaLectura.getText()));
        lectura.setEstado_lectura(convertirEstado(cbxEstado.getSelectedItem().toString()));
        acceso_lectura.insertarLectura(lectura, Inventory.txtUsuario.getText());
        }
    }
    
    private void actualizarRegistros(){
        lectura.setId_lectura(Integer.parseInt(txtIdLectura.getText()));
        lectura.setId_suscripcion(Integer.parseInt(txtIdSuscripcion.getText()));
        lectura.setAnio_lectura(Integer.parseInt(txtAnioLectura.getText()));
        lectura.setMes_lectura(Integer.parseInt(txtMesLectura.getText()));
        lectura.setAnterior_lectura(Integer.parseInt(txtAnteriorLectura.getText()));
        lectura.setNueva_lectura(Integer.parseInt(txtNuevaLectura.getText()));
        lectura.setEstado_lectura(convertirEstado(cbxEstado.getSelectedItem().toString()));
        acceso_lectura.actualizarLectura(lectura, Inventory.txtUsuario.getText());
    }
    
    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdServicio.setText("");     
        txtMesLectura.setText("");
        txtAnioLectura.setText("");
    }
    
    private void cargarServicios() {
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.mostrarServicios();
        for(ObjetosProducto x : lista){
            cbxServicio.addItem(x.getNombre_servicio());
        }
    }
    
    private void cargarClientes() {
        ArrayList<ObjetosCliente> lista = new ArrayList();
        lista = acceso_cliente.listarClientes();
        for(ObjetosCliente x : lista){
            cbxClientes.addItem(x.getNombre_cliente());
        }
    }
    
    private void mostrarSuscripcion() {
        ArrayList<ObjetosSuscripcion> lista = new ArrayList();
        lista = acceso_suscripcion.buscarIdSuscripcion(txtIdServicio.getText(), txtIdCliente.getText());
        if (lista.isEmpty()) {
            txtIdSuscripcion.setText("");
        } else {
            for (ObjetosSuscripcion x : lista) {
                txtIdSuscripcion.setText(String.valueOf(x.getId_suscripcion()));
            }
        }
    }
    
    private void buscarRegistros(){
        
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosLectura> lista = new ArrayList();
        
        lista = acceso_lectura.buscarLecturas(txtBuscar.getText(), txtMesLectura.getText(), txtAnioLectura.getText());
        
        tabla.addColumn("ID Lectura");
        tabla.addColumn("ID Suscripcion");
        tabla.addColumn("Cliente");
        tabla.addColumn("Servicio");
        tabla.addColumn("Año");
        tabla.addColumn("Mes");
        tabla.addColumn("Lectura Anterior");
        tabla.addColumn("Lectura Nueva");
        tabla.addColumn("Estado");
        
        tabla.setRowCount(lista.size());
        int fila = 0;
        
        for(ObjetosLectura x : lista) {
            tabla.setValueAt(x.getId_lectura(), fila, 0);
            tabla.setValueAt(x.getId_suscripcion(), fila, 1);
            tabla.setValueAt(x.getNombre_cliente(), fila, 2);
            tabla.setValueAt(x.getNombre_servicio(), fila, 3);
            tabla.setValueAt(x.getAnio_lectura(), fila, 4);
            tabla.setValueAt(x.getMes_lectura(), fila, 5);
            tabla.setValueAt(x.getAnterior_lectura(), fila, 6);
            tabla.setValueAt(x.getNueva_lectura(), fila, 7);
            tabla.setValueAt(convertirEstado(x.getEstado_lectura()), fila, 8);
            fila++;
        }
        
        tblSuscripcion.setModel(tabla);
        tblSuscripcion.getColumnModel().getColumn(2).setPreferredWidth(200);
    }
    
    private void buscarLectura(){
        
        ArrayList<ObjetosLectura> lista = new ArrayList();
        int mes, anio;
        
        //VERIFICAR SI EL MES ES ENERO ENTONCES RESTAR EL AÑO Y EL MES
        if(txtMesLectura.getText().equals("1")){
            mes = 12;
            anio = Integer.parseInt(txtAnioLectura.getText()) - 1;
        } else {
            mes = Integer.parseInt(txtMesLectura.getText()) - 1;
            anio = Integer.parseInt(txtAnioLectura.getText());
        }
        
        lista = acceso_lectura.BuscarLecturaAnterior(txtIdSuscripcion.getText(), anio, mes);
        
        if (lista.isEmpty()) {
            txtAnteriorLectura.setText("0");
        } else {
            for (ObjetosLectura x : lista) {
                txtAnteriorLectura.setText(String.valueOf(x.getNueva_lectura()));
            }
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
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtMesLectura = new javax.swing.JTextField();
        txtIdServicio = new javax.swing.JTextField();
        cbxServicio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtAnioLectura = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbxClientes = new javax.swing.JComboBox();
        txtIdCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAnteriorLectura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNuevaLectura = new javax.swing.JTextField();
        txtIdSuscripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtIdLectura = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuscripcion = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ingreso de Lecturas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/nuevo.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(775, 550));
        setMinimumSize(new java.awt.Dimension(775, 550));
        setPreferredSize(new java.awt.Dimension(775, 550));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMarcaProducto.setText("Servicio");

        lblDescProducto.setText("Mes");
        lblDescProducto.setToolTipText("");

        txtMesLectura.setToolTipText("Ingrese la descripcion del producto");

        txtIdServicio.setBackground(new java.awt.Color(102, 102, 102));
        txtIdServicio.setEditable(false);
        txtIdServicio.setForeground(new java.awt.Color(255, 255, 255));
        txtIdServicio.setToolTipText("Ingrese la marca del Producto");

        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        jLabel2.setText("Año");

        txtAnioLectura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnioLecturaLostFocus(evt);
            }
        });

        jLabel1.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo", "Cancelado" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jLabel4.setText("Cliente");

        cbxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClientesActionPerformed(evt);
            }
        });

        txtIdCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtIdCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CodigoClienteLostFocus(evt);
            }
        });

        jLabel5.setText("Lectura Anterior");

        txtAnteriorLectura.setBackground(new java.awt.Color(102, 102, 102));
        txtAnteriorLectura.setEditable(false);
        txtAnteriorLectura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Lectura Nueva");

        txtIdSuscripcion.setBackground(new java.awt.Color(102, 102, 102));
        txtIdSuscripcion.setEditable(false);
        txtIdSuscripcion.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("ID Lectura");

        txtIdLectura.setBackground(new java.awt.Color(102, 102, 102));
        txtIdLectura.setEditable(false);
        txtIdLectura.setForeground(new java.awt.Color(255, 255, 255));
        txtIdLectura.setToolTipText("Ingrese la descripcion del producto");

        jLabel8.setText("Suscripcion");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNuevaLectura)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtIdLectura, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMesLectura, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnioLectura, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAnteriorLectura, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblMarcaProducto)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxClientes, 0, 199, Short.MAX_VALUE)
                    .addComponent(cbxServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(cbxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(txtIdServicio)
                    .addComponent(txtIdSuscripcion))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMesLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto)
                    .addComponent(jLabel2)
                    .addComponent(txtAnioLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(jLabel7)
                    .addComponent(txtIdLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtAnteriorLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtNuevaLectura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(14, 14, 14))
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

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Buscar");

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//GEN-FIRST:event_txtIdProveedorFocusLost
//GEN-LAST:event_txtIdProveedorFocusLost

    private void tblSuscripcionCllicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuscripcionCllicked
        btnGuardar.setEnabled(false);
        txtIdLectura.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 0).toString());
        txtIdSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 1).toString());
        txtAnioLectura.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 4).toString());
        txtMesLectura.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 5).toString());
        txtAnteriorLectura.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 6).toString());
        txtNuevaLectura.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 7).toString());
        cbxEstado.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 8).toString());
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
        tblSuscripcion.setValueAt(cbxEstado.getSelectedItem().toString(), tblSuscripcion.getSelectedRow(), 12);
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void cbxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClientesActionPerformed
        ArrayList<ObjetosCliente> lista = new ArrayList();
        lista = acceso_cliente.buscarClientes(cbxClientes.getSelectedItem().toString());
        for (ObjetosCliente x : lista) {
            txtIdCliente.setText(String.valueOf(x.getId_cliente()));
        }
        mostrarSuscripcion();
    }//GEN-LAST:event_cbxClientesActionPerformed

    private void txtAnioLecturaLostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnioLecturaLostFocus
        buscarLectura();
    }//GEN-LAST:event_txtAnioLecturaLostFocus

    private void CodigoClienteLostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CodigoClienteLostFocus
        mostrarSuscripcion();
        buscarLectura();
    }//GEN-LAST:event_CodigoClienteLostFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxClientes;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblSuscripcion;
    private javax.swing.JTextField txtAnioLectura;
    private javax.swing.JTextField txtAnteriorLectura;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdLectura;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtIdSuscripcion;
    private javax.swing.JTextField txtMesLectura;
    private javax.swing.JTextField txtNuevaLectura;
    // End of variables declaration//GEN-END:variables
}