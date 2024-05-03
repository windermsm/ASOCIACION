/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoCliente;
import inventory.acceso.AccesoProducto;
import inventory.acceso.AccesoSuscripcion;
import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosCliente;
import inventory.objetos.ObjetosProducto;
import inventory.objetos.ObjetosSuscripcion;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwCatalogoSuscripciones extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    ObjetosProducto servicio = new ObjetosProducto();
    ObjetosCliente cliente = new ObjetosCliente();
    ObjetosSuscripcion suscripcion = new ObjetosSuscripcion();
    AccesoProducto acceso_servicio = new AccesoProducto();
    AccesoCliente acceso_cliente = new AccesoCliente();
    AccesoSuscripcion acceso_suscripcion = new AccesoSuscripcion();

    /**
     * Creates new form wdwCatalogoProductos
     */
    public wdwCatalogoSuscripciones() {
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
        if (x.equals("Activo")) {
            valor = "A";
        }
        if (x.equals("Inactivo")) {
            valor = "I";
        }
        return valor;
    }

    private void buscarRegistros() {

        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosProducto> lista_servicios = new ArrayList();
        ArrayList<ObjetosCliente> lista_cliente = new ArrayList();
        ArrayList<ObjetosSuscripcion> lista_suscripciones = new ArrayList();

        lista_suscripciones = acceso_suscripcion.mostrarSuscripcionesPorCliente(txtBuscar.getText());

        tabla.addColumn("ID Suscripcion");
        tabla.addColumn("ID");
        tabla.addColumn("Servicio");
        tabla.addColumn("ID");
        tabla.addColumn("Cliente");
        tabla.addColumn("Contador");
        tabla.addColumn("Fecha Incial");
        tabla.addColumn("Fecha Final");
        tabla.addColumn("Direccion");
        tabla.addColumn("Estado");

        tabla.setRowCount(lista_suscripciones.size());
        int fila = 0;

        for (ObjetosSuscripcion x : lista_suscripciones) {
            lista_servicios = acceso_servicio.buscarServiciosPorId(String.valueOf(x.getId_servicio()));
            lista_cliente = acceso_cliente.buscarClientesPorId(String.valueOf(x.getId_cliente()));
            tabla.setValueAt(x.getId_suscripcion(), fila, 0);
            tabla.setValueAt(x.getId_servicio(), fila, 1);
            for (ObjetosProducto y : lista_servicios) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    tabla.setValueAt(y.getNombre_servicio(), fila, 2);
                }
            }
            tabla.setValueAt(x.getId_cliente(), fila, 3);
            for (ObjetosCliente z : lista_cliente) {
                if (x.getId_cliente() == z.getId_cliente()) {
                    tabla.setValueAt(z.getNombre_cliente(), fila, 4);
                }
            }
            tabla.setValueAt(x.getContador_suscripcion(), fila, 5);
            tabla.setValueAt(x.getFecha_inicio_suscripcion(), fila, 6);
            tabla.setValueAt(x.getFecha_final_suscripcion(), fila, 7);
            tabla.setValueAt(x.getDireccion_suscripcion(), fila, 8);
            tabla.setValueAt(convertirEstado(x.getEstado_suscripcion()), fila, 9);
            fila++;
        }

        tblSuscripcion.setModel(tabla);
    }

    private void guardarRegistros() {
        suscripcion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        suscripcion.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
        suscripcion.setDireccion_suscripcion(txtDireccionSuscripcion.getText());
        suscripcion.setContador_suscripcion(txtContadorSuscripcion.getText());
        suscripcion.setFecha_inicio_suscripcion(txtFechaIncioSuscripcion.getText());
        suscripcion.setFecha_final_suscripcion(txtFechaFinalSuscripcion.getText());
        suscripcion.setEstado_suscripcion(convertirEstado(cbxEstadoSuscripcion.getSelectedItem().toString()));
        acceso_suscripcion.insertarSuscripcion(suscripcion, Inventory.txtUsuario.getText());
    }

    private void actualizarRegistros() {
        suscripcion.setId_suscripcion(Integer.parseInt(txtIdSuscripcion.getText()));
        suscripcion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        suscripcion.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
        suscripcion.setDireccion_suscripcion(txtDireccionSuscripcion.getText());
        suscripcion.setContador_suscripcion(txtContadorSuscripcion.getText());
        suscripcion.setFecha_inicio_suscripcion(txtFechaIncioSuscripcion.getText());
        suscripcion.setFecha_final_suscripcion(txtFechaFinalSuscripcion.getText());
        suscripcion.setEstado_suscripcion(convertirEstado(cbxEstadoSuscripcion.getSelectedItem().toString()));
        acceso_suscripcion.actualizarSuscripcion(suscripcion, Inventory.txtUsuario.getText());
    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdSuscripcion.setText("");
        txtIdServicio.setText("");
        txtIdCliente.setText("");
        txtDireccionSuscripcion.setText("");
        txtContadorSuscripcion.setText("");
        txtFechaIncioSuscripcion.setText("");
        txtFechaFinalSuscripcion.setText("");
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
            cbxCliente.addItem(x.getNombre_cliente());
        }
    }

    private void mostrarRegistros() {
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosProducto> lista_servicios = new ArrayList();
        ArrayList<ObjetosCliente> lista_cliente = new ArrayList();
        ArrayList<ObjetosSuscripcion> lista_suscripciones = new ArrayList();

        lista_suscripciones = acceso_suscripcion.listarSuscripciones();

        tabla.addColumn("ID Suscripcion");
        tabla.addColumn("ID");
        tabla.addColumn("Servicio");
        tabla.addColumn("ID");
        tabla.addColumn("Cliente");
        tabla.addColumn("Contador");
        tabla.addColumn("Fecha Incial");
        tabla.addColumn("Fecha Final");
        tabla.addColumn("Direccion");
        tabla.addColumn("Estado");

        tabla.setRowCount(lista_suscripciones.size());
        int fila = 0;

        for (ObjetosSuscripcion x : lista_suscripciones) {
            lista_servicios = acceso_servicio.buscarServiciosPorId(String.valueOf(x.getId_servicio()));
            lista_cliente = acceso_cliente.buscarClientesPorId(String.valueOf(x.getId_cliente()));
            tabla.setValueAt(x.getId_suscripcion(), fila, 0);
            tabla.setValueAt(x.getId_servicio(), fila, 1);
            for (ObjetosProducto y : lista_servicios) {
                if (x.getId_servicio() == y.getId_servicio()) {
                    tabla.setValueAt(y.getNombre_servicio(), fila, 2);
                }
            }
            tabla.setValueAt(x.getId_cliente(), fila, 3);
            for (ObjetosCliente z : lista_cliente) {
                if (x.getId_cliente() == z.getId_cliente()) {
                    tabla.setValueAt(z.getNombre_cliente(), fila, 4);
                }
            }
            tabla.setValueAt(x.getContador_suscripcion(), fila, 5);
            tabla.setValueAt(x.getFecha_inicio_suscripcion(), fila, 6);
            tabla.setValueAt(x.getFecha_final_suscripcion(), fila, 7);
            tabla.setValueAt(x.getDireccion_suscripcion(), fila, 8);
            tabla.setValueAt(convertirEstado(x.getEstado_suscripcion()), fila, 9);
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
        lblIdProducto = new javax.swing.JLabel();
        txtIdSuscripcion = new javax.swing.JTextField();
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtDireccionSuscripcion = new javax.swing.JTextField();
        lblPrecioEstProducto = new javax.swing.JLabel();
        txtFechaFinalSuscripcion = new javax.swing.JTextField();
        lblMinimoProducto = new javax.swing.JLabel();
        txtFechaIncioSuscripcion = new javax.swing.JTextField();
        txtIdServicio = new javax.swing.JTextField();
        cbxServicio = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cbxCliente = new javax.swing.JComboBox();
        txtIdCliente = new javax.swing.JTextField();
        lblPrecioEstProducto1 = new javax.swing.JLabel();
        cbxEstadoSuscripcion = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtContadorSuscripcion = new javax.swing.JTextField();
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
        tblSuscripcion = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ingresar Suscripcion");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/historialCompras.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 550));
        setMinimumSize(new java.awt.Dimension(700, 550));
        setPreferredSize(new java.awt.Dimension(700, 550));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID");

        txtIdSuscripcion.setBackground(new java.awt.Color(153, 153, 153));
        txtIdSuscripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtIdSuscripcion.setToolTipText("Ingrese el codigo del producto");

        lblMarcaProducto.setText("Servicio");

        lblDescProducto.setText("Direccion");
        lblDescProducto.setToolTipText("");

        txtDireccionSuscripcion.setToolTipText("Ingrese la descripcion del producto");

        lblPrecioEstProducto.setText("Fecha Inical");

        txtFechaFinalSuscripcion.setToolTipText("Existencias actuales de este producto");

        lblMinimoProducto.setText("Estado");

        txtFechaIncioSuscripcion.setToolTipText("Ingrese el porcentaje de ganancia Estandar o Normal");

        txtIdServicio.setBackground(new java.awt.Color(153, 153, 153));
        txtIdServicio.setEditable(false);
        txtIdServicio.setForeground(new java.awt.Color(255, 255, 255));
        txtIdServicio.setToolTipText("Ingrese la marca del Producto");

        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        cbxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClienteActionPerformed(evt);
            }
        });

        txtIdCliente.setToolTipText("Ingrese la marca del Producto");

        lblPrecioEstProducto1.setText("Fecha Final");

        cbxEstadoSuscripcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        jLabel2.setText("Contador");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdProducto)
                    .addComponent(lblDescProducto)
                    .addComponent(lblPrecioEstProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(txtIdSuscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMarcaProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDireccionSuscripcion))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(cbxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtContadorSuscripcion)))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtFechaIncioSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(lblPrecioEstProducto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaFinalSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMinimoProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstadoSuscripcion, 0, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto)
                    .addComponent(jLabel2)
                    .addComponent(txtContadorSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioEstProducto)
                    .addComponent(txtFechaIncioSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioEstProducto1)
                    .addComponent(txtFechaFinalSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinimoProducto)
                    .addComponent(cbxEstadoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(txtBuscar)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
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

    private void tblSuscripcionCllicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuscripcionCllicked
        btnGuardar.setEnabled(false);
        txtIdSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 0).toString());
        txtIdServicio.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 1).toString());
        cbxServicio.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 2));
        txtIdCliente.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 3).toString());
        cbxCliente.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 4));
        txtContadorSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 5).toString());
        txtFechaIncioSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 6).toString());
        txtFechaFinalSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 7).toString());
        txtDireccionSuscripcion.setText(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 8).toString());
        cbxEstadoSuscripcion.setSelectedItem(tblSuscripcion.getValueAt(tblSuscripcion.getSelectedRow(), 9));
    }//GEN-LAST:event_tblSuscripcionCllicked

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
        ArrayList<ObjetosProducto> lista = new ArrayList();
        lista = acceso_servicio.buscarServiciosPorNombre(cbxServicio.getSelectedItem().toString());
        for(ObjetosProducto x : lista){
            txtIdServicio.setText(String.valueOf(x.getId_servicio()));
        }  
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void cbxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClienteActionPerformed
        ArrayList<ObjetosCliente> lista = new ArrayList();
        lista = acceso_cliente.buscarClientesPorNombre(cbxCliente.getSelectedItem().toString());
        for(ObjetosCliente x : lista){
            txtIdCliente.setText(String.valueOf(x.getId_cliente()));
            txtDireccionSuscripcion.setText(x.getAvenida_cliente() + " Ave. " + x.getCalle_cliente() + " calle casa no. " + x.getNum_casa_cliente() + " Col. " + x.getColonia_cliente() + " Zona " + x.getZona_cliente() + " " + x.getCiudad_cliente() + ", " + x.getDepto_cliente());
        }
    }//GEN-LAST:event_cbxClienteActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxCliente;
    private javax.swing.JComboBox cbxEstadoSuscripcion;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JLabel lblMinimoProducto;
    private javax.swing.JLabel lblPrecioEstProducto;
    private javax.swing.JLabel lblPrecioEstProducto1;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblSuscripcion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContadorSuscripcion;
    private javax.swing.JTextField txtDireccionSuscripcion;
    private javax.swing.JTextField txtFechaFinalSuscripcion;
    private javax.swing.JTextField txtFechaIncioSuscripcion;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtIdSuscripcion;
    // End of variables declaration//GEN-END:variables
}