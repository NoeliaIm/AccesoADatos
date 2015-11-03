/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formularios;

import conexion.ConexionSingleton;
import funciones.MetodosComunes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ALUMNOM
 */
public class JD_DatosPrestamo extends javax.swing.JDialog {

    /**
     * Creates new form JD_DatosPrestamo
     */
    private String idPrestamo;
    private boolean esNuevo = false;
    private MetodosComunes funciones = new MetodosComunes();
    
    public JD_DatosPrestamo(java.awt.Frame parent, boolean modal,String idPrestamo) {
        super(parent, modal);
        this.idPrestamo = idPrestamo;
        if (this.idPrestamo.equals("0")) {
            esNuevo = true;
        }
        initComponents();
        rellenarCampos();
    }
    
     public void rellenarCampos() {
        try {
            if (!esNuevo) {
                String sql = " SELECT P.IDPRESTAMO,S.APELLIDOS,L.TITULO,P.FECHASALIDA,P.FECHAMAXIMA,P.FECHAENTREGA,P.DEVUELTO " +
                        " FROM BPRESTAMOS P,BLIBROS L , BSOCIOS S " +
                        " WHERE P.IDSOCIO = S.IDSOCIO " +
                        " AND P.IDLIBRO = L.IDLIBRO " +
                        " AND P.ELIMINADO = 0"+
                        " AND P.IDPRESTAMO ="+idPrestamo;
                ResultSet resultado;
                try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
                    resultado = consulta.executeQuery(sql);
                    resultado.next();
                    txtNombre.setText(resultado.getString("APELLIDOS"));
                    txtTitulo.setText(resultado.getString("TITULO"));
                    funciones.IntegerToDate(resultado.getInt("FECHASALIDA"), jDateFechaSalida);
                    funciones.IntegerToDate(resultado.getInt("FECHAMAXIMA"), jDateFechaMaxima);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JD_LibroNuevo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateFechaEntrada = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateFechaMaxima = new com.toedter.calendar.JDateChooser();
        jDateFechaSalida = new com.toedter.calendar.JDateChooser();
        txtNombre = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        btnDevolver = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre Socio");

        jLabel2.setText("Titulo Libro");

        jLabel4.setText("Fecha Salida");

        jLabel5.setText("Fecha Entrada");

        jLabel6.setText("Fecha Maxima");

        jDateFechaMaxima.setEnabled(false);

        jDateFechaSalida.setEnabled(false);

        txtNombre.setEnabled(false);

        txtTitulo.setEnabled(false);

        btnDevolver.setText("Realizar Devolucion");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/EXIT_ (23).jpg"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jDateFechaMaxima, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDateFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnDevolver)
                        .addGap(32, 32, 32)
                        .addComponent(btnLimpiar)
                        .addGap(31, 31, 31)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jDateFechaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jDateFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDevolver)
                    .addComponent(btnLimpiar)
                    .addComponent(btnSalir))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        // TODO add your handling code here:
        
        int fechaEntrada= funciones.DateToInteger(jDateFechaEntrada);
        int fechaActual = funciones.getFechaActual();
        int fechaMaxima = funciones.DateToInteger(jDateFechaMaxima);
        
        if(fechaEntrada>fechaActual){
            JOptionPane.showMessageDialog(null, "La fecha de entrada no puede ser mayor que la fecha actual");
        }else{
             
            if(fechaEntrada>fechaMaxima){
                try {
                    // Adivinamos el número de días que lleva el libro fuera para calcular la multa
                    int diasquellevafuera = fechaEntrada-fechaMaxima;
                    int idMulta = devolverIdMulta(diasquellevafuera);
                    if(idMulta!=-1){
                        Statement consulta = ConexionSingleton.conexion.createStatement();
                         String sql = "INSERT INTO BMULTASPRESTAMOS VALUES("+idPrestamo+","+idMulta+","+"'n'"+","+fechaActual+","+"0)";
                        System.out.println(sql);
                        int filas = consulta.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "El libro tiene una multa, consulte mulltas para mas información");
                        consulta.close();
                    }
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(JD_DatosPrestamo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
           
            sumarNroCopiasDisponibles(txtTitulo.getText());
            ponerDevuelto(idPrestamo);
            actualizarTablaPrestamos(idPrestamo);
            JOptionPane.showMessageDialog(null, "Libro devuelto con éxito");
            this.dispose();
        }
        
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        jDateFechaEntrada.setDate(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

     private void sumarNroCopiasDisponibles(String titulo){
        try {
            // TODO add your handling code here:
            
            String sql;
            Statement consulta = ConexionSingleton.conexion.createStatement();
            sql = "UPDATE BLIBROS SET NROCOPIASDISP=nrocopiasdisp+1 where titulo  ='"+titulo+"'";
            System.out.println(sql);
            int filas = consulta.executeUpdate(sql);
            consulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(JD_PrestamoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void ponerDevuelto(String idPrestamo){
        try {
            // TODO add your handling code here:
            
            String sql;
            Statement consulta = ConexionSingleton.conexion.createStatement();
            sql = "UPDATE BPRESTAMOS SET DEVUELTO='s' where idprestamo  ="+idPrestamo;
            System.out.println(sql);
            int filas = consulta.executeUpdate(sql);
            consulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(JD_PrestamoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void actualizarTablaPrestamos(String idPrestamo){
          int fechaEntrega = funciones.DateToInteger(jDateFechaEntrada);
          try {
            // TODO add your handling code here:
            
            String sql;
            Statement consulta = ConexionSingleton.conexion.createStatement();
            sql = "UPDATE BPRESTAMOS SET FECHAENTREGA="+fechaEntrega+" where idprestamo  ="+idPrestamo;
            System.out.println(sql);
            int filas = consulta.executeUpdate(sql);
            consulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(JD_PrestamoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      private int devolverIdMulta(int nDiasFuera) throws SQLException {
        String sql = "SELECT IDMULTA, DIASMIN,DIASMAX FROM BMULTAS WHERE ELIMINADO = 0";
        ResultSet filas;
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            filas = consulta.executeQuery(sql);
            while (filas.next()) {
                int nuevo[] = {filas.getInt("DIASMIN"),filas.getInt("DIASMAX")};
                if(nDiasFuera>nuevo[0]&& nDiasFuera<nuevo[1]){
                    return filas.getInt("IDMULTA");
                }
            }
        }
        return -1;
    }
     
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser jDateFechaEntrada;
    private com.toedter.calendar.JDateChooser jDateFechaMaxima;
    private com.toedter.calendar.JDateChooser jDateFechaSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
