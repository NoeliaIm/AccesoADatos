/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import conexion.ConexionSingleton;
import static formularios.JI_Multas.setOcultarColumnasJTable;
import funciones.MetodosComunes;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class JI_PagarMultas extends javax.swing.JInternalFrame {

    private static JI_PagarMultas instancia;
    public DefaultTableModel tablaPagarMultas;
    MetodosComunes funciones  =new MetodosComunes();
    /**
     * Creates new form JI_PagarMultas
     */
    public static JI_PagarMultas getInstancia() {
        // Si la instancia no está creada o la ventana está cerrada se crea la instancia

        if (instancia == null) {
            instancia = new JI_PagarMultas();
        }
        return instancia;
    }
    private JI_PagarMultas() {
        initComponents();
        tablaPagarMultas = (DefaultTableModel) jTablePagarMultas.getModel();
        llenarTablaPagarMultasInicio();
        eventoFilaSeleccionada();
    }
    
    private void llenarTablaPagarMultasInicio() {
        try {
            String sql = " SELECT MP.IDPRESTAMO,MP.IDMULTA, S.APELLIDOS,S.DNI,L.TITULO,MP.PAGADA"+
                          " FROM BMULTASPRESTAMOS MP,BPRESTAMOS P, BSOCIOS S, BLIBROS L, BMULTAS M"+
                          " WHERE MP.IDMULTA = M.IDMULTA"+
                          " AND MP.IDPRESTAMO = P.IDPRESTAMO"+
                          " AND P.IDSOCIO = S.IDSOCIO"+
                          " AND P.IDLIBRO = L.IDLIBRO"
                        + " AND MP.ELIMINADO= 0";
            llenarTablaPagarMultas(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JI_PagarMultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void llenarTablaPagarMultas(String sql) throws SQLException {
        limpiarTabla(tablaPagarMultas);
        ResultSet filas;
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            filas = consulta.executeQuery(sql);
            while (filas.next()) {
                Object nuevo[] = {filas.getInt("IDPRESTAMO"), filas.getInt("IDMULTA"), filas.getString("APELLIDOS"), filas.getString("DNI"), filas.getString("TITULO"),filas.getString("PAGADA")};
                tablaPagarMultas.addRow(nuevo);
            }
        }
        filas.close();
        setOcultarColumnasJTable(jTablePagarMultas, new int[]{0,1});
    }
     public static void setOcultarColumnasJTable(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

    private void limpiarTabla(DefaultTableModel tabla) {
        int a = tabla.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }
    public void eventoFilaSeleccionada(){
        ListSelectionModel rowSM = jTablePagarMultas.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

    public void valueChanged(ListSelectionEvent e) {
        //Ignore extra messages.
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm =
            (ListSelectionModel)e.getSource();

        if (lsm.isSelectionEmpty()) {
            //no rows are selected
        } else {
            int selectedRow = lsm.getMinSelectionIndex();
            //selectedRow is selected
            String idPrestamo = String.valueOf(jTablePagarMultas.getValueAt(jTablePagarMultas.getSelectedRow(), 0));
            String idMulta = String.valueOf(jTablePagarMultas.getValueAt(jTablePagarMultas.getSelectedRow(), 1));
            if(estaPagada(idPrestamo,idMulta)){
                btnPagar.setEnabled(false);
            }else{
                btnPagar.setEnabled(true);
            }
        }
    }

          
           
        });
    }
    
    
    private boolean estaPagada(String idPrestamo, String idMulta){
        String sql = " SELECT PAGADA FROM BMULTASPRESTAMOS" +
                     " WHERE BMULTASPRESTAMOS.IDPRESTAMO =" +idPrestamo+
                     " AND BMULTASPRESTAMOS.IDMULTA ="+idMulta;
         ResultSet filas;
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            filas = consulta.executeQuery(sql);
            while (filas.next()) {
                Object nuevo[] = {filas.getString("PAGADA")};
                if(nuevo[0].equals("s")){
                    return true;
                }
            }
            filas.close();
        } catch (SQLException ex) {
            Logger.getLogger(JI_PagarMultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePagarMultas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtPagado = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("PAGAR MULTAS");

        jTablePagarMultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPrestamo", "IdMulta", "Apellidos", "Dni", "Titulo", "Pagado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePagarMultas);

        jLabel1.setText("FILTROS");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Dni");

        jLabel4.setText("Titulo");

        jLabel5.setText("Pagado");

        btnPagar.setText("Pagar Multa");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/EXIT_ (23).jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(5, 5, 5)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(7, 7, 7)
                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPagar)
                            .addComponent(jButton1))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        // TODO add your handling code here:
         if (jTablePagarMultas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay Multas");
        } else {
            if (jTablePagarMultas.getSelectedRow() != -1) {
                try {
                    //devuelve el valor de la fila selecciona y la columna que es código departamento
                    String idPrestamo = String.valueOf(jTablePagarMultas.getValueAt(jTablePagarMultas.getSelectedRow(), 0));
                    String idMulta = String.valueOf(jTablePagarMultas.getValueAt(jTablePagarMultas.getSelectedRow(), 1));
                    
                    if(!estaPagada(idPrestamo,idMulta)){
                        Statement consulta = ConexionSingleton.conexion.createStatement();
                        String sql = " UPDATE BMULTASPRESTAMOS SET PAGADA='s',FECHAPAGO="+funciones.getFechaActual()+
                                 " WHERE IDPRESTAMO= "+idPrestamo+ "AND IDMULTA= "+idMulta;
                        int filas = consulta.executeUpdate(sql);
                        consulta.close();
                        JOptionPane.showMessageDialog(null, "Multa pagada con éxito");
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(JI_PagarMultas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna");
            }
        }
         llenarTablaPagarMultasInicio();
        
       
    }//GEN-LAST:event_btnPagarActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePagarMultas;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtPagado;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
