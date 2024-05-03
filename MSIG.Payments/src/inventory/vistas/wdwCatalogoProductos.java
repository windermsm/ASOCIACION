/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoCategoria;
import inventory.acceso.AccesoEmpresa;
import inventory.acceso.AccesoProducto;
import inventory.acceso.Mensaje;
import inventory.objetos.ObjetosCategoria;
import inventory.objetos.ObjetosEmpresa;
import inventory.objetos.ObjetosProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math.stat.descriptive.summary.Product;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class wdwCatalogoProductos extends javax.swing.JInternalFrame {

    Mensaje mensaje = new Mensaje();
    ObjetosProducto servicio = new ObjetosProducto();
    ObjetosCategoria categoria = new ObjetosCategoria();
    ObjetosEmpresa empresa = new ObjetosEmpresa();
    AccesoProducto acceso = new AccesoProducto();
    AccesoCategoria acceso_categoria = new AccesoCategoria();
    AccesoEmpresa acceso_empresa = new AccesoEmpresa();

    /**
     * Creates new form wdwCatalogoProductos
     */
    public wdwCatalogoProductos() {
        initComponents();
        mostrarRegistros();
        cargarCategorias();
        cargarEmpresas();
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
        ArrayList<ObjetosProducto> lista = new ArrayList();
        ArrayList<ObjetosCategoria> lista_categoria = new ArrayList();
        ArrayList<ObjetosEmpresa> lista_empresa = new ArrayList();

        try {
            lista = acceso.buscarServicios(txtBuscar.getText());
        } catch (Error error) {
            mensaje.manipulacionExcepciones("critico", "No se a podido listar los registros");
        }

        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("critico", "No existen datos almacenados");
        } else {
            tabla.addColumn("ID Servicio");
            tabla.addColumn("ID");
            tabla.addColumn("Categoria");
            tabla.addColumn("ID");
            tabla.addColumn("Empresa");
            tabla.addColumn("Servicio");
            tabla.addColumn("Descripcion");
            tabla.addColumn("Costo");
            tabla.addColumn("Precio");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Precio Excedente");
            tabla.addColumn("Credito Puntual");
            tabla.addColumn("Credito Maximo");
            tabla.addColumn("Estado");

            tabla.setRowCount(lista.size());
            int fila = 0;

            for (ObjetosProducto x : lista) {
                lista_categoria = acceso_categoria.buscarCategoriasPorId(String.valueOf(x.getId_categoria()));
                lista_empresa = acceso_empresa.buscarCategoriasPorId(String.valueOf(x.getId_empresa()));
                tabla.setValueAt(x.getId_servicio(), fila, 0);
                tabla.setValueAt(x.getId_categoria(), fila, 1);
                for(ObjetosCategoria y : lista_categoria) {
                    if(x.getId_categoria() == y.getId_categoria()) {
                        tabla.setValueAt(y.getNombre_categoria(), fila, 2);
                    }
                }
                tabla.setValueAt(x.getId_empresa(), fila, 3);
                for(ObjetosEmpresa z : lista_empresa) {
                    if(x.getId_empresa() == z.getId_empresa()) {
                        tabla.setValueAt(z.getNombre_empresa(), fila, 4);
                    }
                }
                tabla.setValueAt(x.getNombre_servicio(), fila, 5);
                tabla.setValueAt(x.getDescripcion_servicio(), fila, 6);
                tabla.setValueAt(x.getCosto_servicio(), fila, 7);
                tabla.setValueAt(x.getPrecio_servicio(), fila, 8);
                tabla.setValueAt(x.getCantidad_servicio(), fila, 9);
                tabla.setValueAt(x.getPrecio_excedente_servicio(), fila, 10);
                tabla.setValueAt(x.getDias_credito_puntual_servicio(), fila, 11);
                tabla.setValueAt(x.getDias_credito_maximo_servicio(), fila, 12);
                tabla.setValueAt(convertirEstado(x.getEstado_servicio()), fila, 13);
                fila++;
            }
            
            tblProducto.setModel(tabla);
        }
    }

    private void guardarRegistros() {
        servicio.setId_categoria(Integer.parseInt(txtIdCategoria.getText()));
        servicio.setId_empresa(Integer.parseInt(txtIdEmpresa.getText()));
        servicio.setNombre_servicio(txtNombreServicio.getText());
        servicio.setDescripcion_servicio(txtDescripcionServicio.getText());
        servicio.setCosto_servicio(Double.parseDouble(txtCostoServicio.getText()));
        servicio.setPrecio_servicio(Double.parseDouble(txtPrecioServicio.getText()));
        servicio.setCantidad_servicio(Integer.parseInt(txtCantidadServicio.getText()));
        servicio.setPrecio_excedente_servicio(Double.parseDouble(txtPrecioExcedenteServicio.getText()));
        servicio.setDias_credito_puntual_servicio(Integer.parseInt(txtDiasCreditoPuntualServicio.getText()));
        servicio.setDias_credito_maximo_servicio(Integer.parseInt(txtDiasCreditoMaximoServicio.getText()));
        servicio.setEstado_servicio(convertirEstado(cbxEstadoServicio.getSelectedItem().toString()));
        acceso.insertarServicio(servicio, Inventory.txtUsuario.getText());
    }

    private void actualizarRegistros() {
        servicio.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        servicio.setId_categoria(Integer.parseInt(txtIdCategoria.getText()));
        servicio.setId_empresa(Integer.parseInt(txtIdEmpresa.getText()));
        servicio.setNombre_servicio(txtNombreServicio.getText());
        servicio.setDescripcion_servicio(txtDescripcionServicio.getText());
        servicio.setCosto_servicio(Double.parseDouble(txtCostoServicio.getText()));
        servicio.setPrecio_servicio(Double.parseDouble(txtPrecioServicio.getText()));
        servicio.setCantidad_servicio(Integer.parseInt(txtCantidadServicio.getText()));
        servicio.setPrecio_excedente_servicio(Double.parseDouble(txtPrecioExcedenteServicio.getText()));
        servicio.setDias_credito_puntual_servicio(Integer.parseInt(txtDiasCreditoPuntualServicio.getText()));
        servicio.setDias_credito_maximo_servicio(Integer.parseInt(txtDiasCreditoMaximoServicio.getText()));
        servicio.setEstado_servicio(convertirEstado(cbxEstadoServicio.getSelectedItem().toString()));
        acceso.actualizarServicio(servicio, Inventory.txtUsuario.getText());
    }

    private void limpiarCampos() {
        btnGuardar.setEnabled(true);
        txtIdServicio.setText("");
        txtIdCategoria.setText("");
        txtIdEmpresa.setText("");
        txtNombreServicio.setText("");
        txtDescripcionServicio.setText("");
        txtCostoServicio.setText("");
        txtPrecioServicio.setText("");
        txtCantidadServicio.setText("");
        txtPrecioExcedenteServicio.setText("");
        txtDiasCreditoPuntualServicio.setText("");
        txtDiasCreditoMaximoServicio.setText("");
    }
    
    private void cargarCategorias() {
        ArrayList<ObjetosCategoria> lista = new ArrayList();
        lista = acceso_categoria.listarCategoriasActivas();
        for(ObjetosCategoria x : lista){
            cbxCategoria.addItem(x.getNombre_categoria());
        }
    }
    
    private void cargarEmpresas() {
        ArrayList<ObjetosEmpresa> lista = new ArrayList();
        lista = acceso_empresa.listarEmpresasActivas();
        for(ObjetosEmpresa x : lista){
            cbxEmpresa.addItem(x.getNombre_empresa());
        }
    }

    private void mostrarRegistros() {
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetosProducto> lista = new ArrayList();
        ArrayList<ObjetosCategoria> lista_categoria = new ArrayList();
        ArrayList<ObjetosEmpresa> lista_empresa = new ArrayList();

        try {
            lista = acceso.mostrarServicios();
        } catch (Error error) {
            mensaje.manipulacionExcepciones("critico", "No se a podido listar los registros");
        }

        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("critico", "No existen datos almacenados");
        } else {
            tabla.addColumn("ID Servicio");
            tabla.addColumn("ID");
            tabla.addColumn("Categoria");
            tabla.addColumn("ID");
            tabla.addColumn("Empresa");
            tabla.addColumn("Servicio");
            tabla.addColumn("Descripcion");
            tabla.addColumn("Costo");
            tabla.addColumn("Precio");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Precio Excedente");
            tabla.addColumn("Credito Puntual");
            tabla.addColumn("Credito Maximo");
            tabla.addColumn("Estado");

            tabla.setRowCount(lista.size());
            int fila = 0;

            for (ObjetosProducto x : lista) {
                lista_categoria = acceso_categoria.buscarCategoriasPorId(String.valueOf(x.getId_categoria()));
                lista_empresa = acceso_empresa.buscarCategoriasPorId(String.valueOf(x.getId_empresa()));
                tabla.setValueAt(x.getId_servicio(), fila, 0);
                tabla.setValueAt(x.getId_categoria(), fila, 1);
                for(ObjetosCategoria y : lista_categoria) {
                    if(x.getId_categoria() == y.getId_categoria()) {
                        tabla.setValueAt(y.getNombre_categoria(), fila, 2);
                    }
                }
                tabla.setValueAt(x.getId_empresa(), fila, 3);
                for(ObjetosEmpresa z : lista_empresa) {
                    if(x.getId_empresa() == z.getId_empresa()) {
                        tabla.setValueAt(z.getNombre_empresa(), fila, 4);
                    }
                }
                tabla.setValueAt(x.getNombre_servicio(), fila, 5);
                tabla.setValueAt(x.getDescripcion_servicio(), fila, 6);
                tabla.setValueAt(x.getCosto_servicio(), fila, 7);
                tabla.setValueAt(x.getPrecio_servicio(), fila, 8);
                tabla.setValueAt(x.getCantidad_servicio(), fila, 9);
                tabla.setValueAt(x.getPrecio_excedente_servicio(), fila, 10);
                tabla.setValueAt(x.getDias_credito_puntual_servicio(), fila, 11);
                tabla.setValueAt(x.getDias_credito_maximo_servicio(), fila, 12);
                tabla.setValueAt(convertirEstado(x.getEstado_servicio()), fila, 13);
                fila++;
            }

            tblProducto.setModel(tabla);
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
        lblIdProducto = new javax.swing.JLabel();
        txtIdServicio = new javax.swing.JTextField();
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtNombreServicio = new javax.swing.JTextField();
        lblPrecioCompraProducto = new javax.swing.JLabel();
        txtDescripcionServicio = new javax.swing.JTextField();
        lblPrecioEstProducto = new javax.swing.JLabel();
        lblIdProveedor = new javax.swing.JLabel();
        lblExiProducto = new javax.swing.JLabel();
        txtPrecioServicio = new javax.swing.JTextField();
        lblMinimoProducto = new javax.swing.JLabel();
        txtDiasCreditoPuntualServicio = new javax.swing.JTextField();
        txtCostoServicio = new javax.swing.JTextField();
        txtIdCategoria = new javax.swing.JTextField();
        cbxCategoria = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cbxEmpresa = new javax.swing.JComboBox();
        txtIdEmpresa = new javax.swing.JTextField();
        lblPrecioEstProducto1 = new javax.swing.JLabel();
        txtDiasCreditoMaximoServicio = new javax.swing.JTextField();
        cbxEstadoServicio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtCantidadServicio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrecioExcedenteServicio = new javax.swing.JTextField();
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
        tblProducto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Servicios");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/productos.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 550));
        setMinimumSize(new java.awt.Dimension(700, 550));
        setPreferredSize(new java.awt.Dimension(700, 550));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID");

        txtIdServicio.setBackground(new java.awt.Color(153, 153, 153));
        txtIdServicio.setForeground(new java.awt.Color(255, 255, 255));
        txtIdServicio.setToolTipText("Ingrese el codigo del producto");

        lblMarcaProducto.setText("Categoria");

        lblDescProducto.setText("Nombre");
        lblDescProducto.setToolTipText("");

        txtNombreServicio.setToolTipText("Ingrese la descripcion del producto");

        lblPrecioCompraProducto.setText("Descripcion");

        txtDescripcionServicio.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        lblPrecioEstProducto.setText("Costo");

        lblIdProveedor.setText("Dias Credito Puntual");
        lblIdProveedor.setToolTipText("");

        lblExiProducto.setText("Dias Credito Maximo");

        txtPrecioServicio.setToolTipText("Existencias actuales de este producto");

        lblMinimoProducto.setText("Estado Servicio");

        txtCostoServicio.setToolTipText("Ingrese el porcentaje de ganancia Estandar o Normal");

        txtIdCategoria.setBackground(new java.awt.Color(153, 153, 153));
        txtIdCategoria.setEditable(false);
        txtIdCategoria.setForeground(new java.awt.Color(255, 255, 255));
        txtIdCategoria.setToolTipText("Ingrese la marca del Producto");

        cbxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setText("Empresa");

        cbxEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEmpresaActionPerformed(evt);
            }
        });

        txtIdEmpresa.setBackground(new java.awt.Color(153, 153, 153));
        txtIdEmpresa.setEditable(false);
        txtIdEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        txtIdEmpresa.setToolTipText("Ingrese la marca del Producto");

        lblPrecioEstProducto1.setText("Precio");

        cbxEstadoServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        jLabel2.setText("Cantidad Pemitida");

        jLabel3.setText("Precio Excedente");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioCompraProducto)
                    .addComponent(lblIdProducto)
                    .addComponent(lblPrecioEstProducto)
                    .addComponent(lblDescProducto)
                    .addComponent(lblPrecioEstProducto1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreServicio)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMarcaProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEmpresa, 0, 174, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEncabezadoLayout.createSequentialGroup()
                                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrecioServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(txtCostoServicio, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdProveedor)
                                    .addComponent(lblMinimoProducto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDiasCreditoPuntualServicio)
                                    .addComponent(cbxEstadoServicio, 0, 92, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblExiProducto)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioExcedenteServicio)
                            .addComponent(txtCantidadServicio)
                            .addComponent(txtDiasCreditoMaximoServicio))))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioCompraProducto)
                    .addComponent(txtDescripcionServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidadServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioEstProducto)
                    .addComponent(txtDiasCreditoPuntualServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdProveedor)
                    .addComponent(lblExiProducto)
                    .addComponent(txtDiasCreditoMaximoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioEstProducto1)
                    .addComponent(lblMinimoProducto)
                    .addComponent(cbxEstadoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecioExcedenteServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProducto.getTableHeader().setReorderingAllowed(false);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoCllicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void tblProductoCllicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoCllicked
        btnGuardar.setEnabled(false);
        txtIdServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 0).toString());
        txtIdCategoria.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 1).toString());
        cbxCategoria.setSelectedItem(tblProducto.getValueAt(tblProducto.getSelectedRow(), 2));
        txtIdEmpresa.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 3).toString());
        cbxEmpresa.setSelectedItem(tblProducto.getValueAt(tblProducto.getSelectedRow(), 4));
        txtNombreServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 5).toString());
        txtDescripcionServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 6).toString());
        txtCostoServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 7).toString());
        txtPrecioServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 8).toString());
        txtCantidadServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 9).toString());
        txtPrecioExcedenteServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 10).toString());
        txtDiasCreditoPuntualServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(),11).toString());
        txtDiasCreditoMaximoServicio.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 12).toString());
        cbxEstadoServicio.setSelectedItem(tblProducto.getValueAt(tblProducto.getSelectedRow(), 13));
    }//GEN-LAST:event_tblProductoCllicked

    private void cbxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCategoriaActionPerformed
        ArrayList<ObjetosCategoria> lista = new ArrayList();
        lista = acceso_categoria.buscarCategoriasPorNombre(cbxCategoria.getSelectedItem().toString());
        for(ObjetosCategoria x : lista){
            txtIdCategoria.setText(String.valueOf(x.getId_categoria()));
        }  
    }//GEN-LAST:event_cbxCategoriaActionPerformed

    private void cbxEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEmpresaActionPerformed
        ArrayList<ObjetosEmpresa> lista = new ArrayList();
        lista = acceso_empresa.buscarEmpresaPorNombre(cbxEmpresa.getSelectedItem().toString());
        for(ObjetosEmpresa x : lista){
            txtIdEmpresa.setText(String.valueOf(x.getId_empresa()));
        }
    }//GEN-LAST:event_cbxEmpresaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxCategoria;
    private javax.swing.JComboBox cbxEmpresa;
    private javax.swing.JComboBox cbxEstadoServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblExiProducto;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIdProveedor;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JLabel lblMinimoProducto;
    private javax.swing.JLabel lblPrecioCompraProducto;
    private javax.swing.JLabel lblPrecioEstProducto;
    private javax.swing.JLabel lblPrecioEstProducto1;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidadServicio;
    private javax.swing.JTextField txtCostoServicio;
    private javax.swing.JTextField txtDescripcionServicio;
    private javax.swing.JTextField txtDiasCreditoMaximoServicio;
    private javax.swing.JTextField txtDiasCreditoPuntualServicio;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtIdServicio;
    private javax.swing.JTextField txtNombreServicio;
    private javax.swing.JTextField txtPrecioExcedenteServicio;
    private javax.swing.JTextField txtPrecioServicio;
    // End of variables declaration//GEN-END:variables
}