/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.vistas;

import inventory.acceso.AccesoCliente;
import inventory.acceso.Mensaje;
import inventory.acceso.Conexion;
import inventory.objetos.ObjetosCliente;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
public class wdwReportesAbonosCliente extends javax.swing.JInternalFrame {

    AccesoCliente acceso_cliente = new AccesoCliente();
    
    /**
     * Creates new form wdwReportesVentasPorFecha
     */
    public wdwReportesAbonosCliente() {
        initComponents();
        cargarClientes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlFormato = new javax.swing.JPanel();
        lblFormato = new javax.swing.JLabel();
        pnlParametros = new javax.swing.JPanel();
        lblFechaInicial = new javax.swing.JLabel();
        lblFechaFinal = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();
        txtAnio = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbxClientes = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setForeground(java.awt.Color.white);
        setIconifiable(true);
        setResizable(true);
        setTitle("Reporte");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/imagenes/reporte.png"))); // NOI18N

        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Abonos por Cliente");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFormato.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblFormato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFormato.setText("Hoja Carta 8.50 x 11.00 plgs");

        javax.swing.GroupLayout pnlFormatoLayout = new javax.swing.GroupLayout(pnlFormato);
        pnlFormato.setLayout(pnlFormatoLayout);
        pnlFormatoLayout.setHorizontalGroup(
            pnlFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFormato, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFormatoLayout.setVerticalGroup(
            pnlFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormatoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFormato)
                .addContainerGap())
        );

        pnlParametros.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblFechaInicial.setText("Año");

        lblFechaFinal.setText("Mes");
        lblFechaFinal.setToolTipText("Ingrese la Fecha Final");

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        cbxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClientesActionPerformed(evt);
            }
        });

        jLabel2.setText("Codigo");

        javax.swing.GroupLayout pnlParametrosLayout = new javax.swing.GroupLayout(pnlParametros);
        pnlParametros.setLayout(pnlParametrosLayout);
        pnlParametrosLayout.setHorizontalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlParametrosLayout.createSequentialGroup()
                        .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblFechaFinal)
                            .addComponent(lblFechaInicial)
                            .addComponent(jLabel2))
                        .addGap(14, 14, 14)
                        .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes)
                            .addComponent(txtAnio)
                            .addComponent(txtCodigoCliente)
                            .addComponent(cbxClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlParametrosLayout.setVerticalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParametrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaInicial)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaFinal)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(btnGenerarReporte)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFormato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlParametros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlParametros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarClientes() {
        ArrayList<ObjetosCliente> lista = new ArrayList();
        lista = acceso_cliente.listarClientes();
        for(ObjetosCliente x : lista){
            cbxClientes.addItem(x.getNombre_cliente());
        }
    }
    
    
    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        
       Mensaje Mensaje = new Mensaje();
       
        try {
            Conexion Acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/inventory/reportes/rptAbonosCliente.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            
            if(txtAnio.getText().isEmpty()){
                parametro.put("P_ANIO", null);
            } else {
                parametro.put("P_ANIO", Integer.parseInt(txtAnio.getText()));
            }
            
            if(txtMes.getText().isEmpty()){
                parametro.put("P_MES", null);
            }else{
                parametro.put("P_MES", Integer.parseInt(txtMes.getText()));
            }
            
            if(txtCodigoCliente.getText().isEmpty()){
                parametro.put("P_ID_CLIENTE", null);
            }else{
                parametro.put("P_ID_CLIENTE", Integer.parseInt(txtCodigoCliente.getText()));
            }

            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, Acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        } catch (JRException error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al ejecutar el reporte -> " + error);
        }
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void cbxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClientesActionPerformed
        ArrayList<ObjetosCliente> lista = new ArrayList();
        lista = acceso_cliente.buscarClientesPorNombre(cbxClientes.getSelectedItem().toString());
        for(ObjetosCliente x : lista){
            txtCodigoCliente.setText(String.valueOf(x.getId_cliente()));
        }
    }//GEN-LAST:event_cbxClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JComboBox cbxClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblFechaFinal;
    private javax.swing.JLabel lblFechaInicial;
    private javax.swing.JLabel lblFormato;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlFormato;
    private javax.swing.JPanel pnlParametros;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtMes;
    // End of variables declaration//GEN-END:variables
}