/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author Isabel
 */
public class ContarDepart extends JInternalFrame {

    private static ContarDepart instancia;

    public static ContarDepart getInstancia() {
        // Si la instancia no está creada o la ventana está cerrada se crea la instancia

        if (instancia == null) {
            instancia = new ContarDepart();
        }
        return instancia;
    }

    private ContarDepart() {
        try {
            initComponents();
//            ConexionSingleton.getInstance("oracle", "localhost","bdisabel", "isabel", "isabel");
            String sql = "SELECT COUNT(*) AS  TOTAL FROM DEPARTAMENTOS ";
            try (PreparedStatement consulta = ConexionSingleton.conexion.prepareStatement(sql)) {
                try (ResultSet resultado = consulta.executeQuery()) {
                    resultado.next();
                    int total = resultado.getInt(1);
                    Total.setText(Integer.toString(total));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContarDepart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        BtnSalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("CONTAR DEPARTAMENTOS");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nº DE DEPARTAMENTOS");

        Total.setEditable(false);
        Total.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Total.setForeground(new java.awt.Color(0, 0, 153));
        Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Total.setEnabled(false);

        BtnSalir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addGap(64, 64, 64)
                .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSalir;
    private javax.swing.JTextField Total;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
