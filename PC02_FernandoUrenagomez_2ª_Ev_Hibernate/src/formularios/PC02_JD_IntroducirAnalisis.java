/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import javax.swing.JOptionPane;
import modelos.Hanalisis;

/**
 *
 * @author Isabel
 */
public class PC02_JD_IntroducirAnalisis extends javax.swing.JDialog {

    /**
     * Creates new form PC02_JD_IntroducirAnalisis
     */
    public PC02_JD_IntroducirAnalisis() {
        initComponents();
        
        this.setModal(true);
        
        conexion=MiHibernateSingleton.getInstance();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblControlLongitudAnalisis = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Análisis");

        jLabel1.setText("Descripción Análisis");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addition.gif"))); // NOI18N
        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salir22x22.png"))); // NOI18N
        btnSalir.setText("Salir");
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
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrabar)
                        .addGap(37, 37, 37)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblControlLongitudAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblControlLongitudAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
       
        
        String error="";
        
        if(txtDescripcion.getText().isEmpty()){
            error+="- Escribe el nombre de un analisis/n";
        }
            
        if(conexion.ExisteValor(txtDescripcion.getText().toUpperCase(), "descripcion", "Hanalisis")){
            error+="- El analisis ya existe/n";
        }
        
        
        if(error.equals("")){
            
             int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de que quieres añadir el registro?", 
                                                            "Confirmacion", 
                                                            JOptionPane.YES_NO_OPTION);

                if(seleccion==JOptionPane.YES_OPTION){
                    
                    conexion.abrirSesionYTransaccion();

                    Hanalisis analisis=new Hanalisis();

                    int idanalisis=conexion.proximoIDDisponible("idanalisis", "Hanalisis");

                    analisis.setIdanalisis(idanalisis);
                    analisis.setDescripcion(txtDescripcion.getText().toUpperCase());
                    analisis.setEliminado(false);

                    conexion.insertar(analisis);

                    conexion.cerrarSesionYTransaccion();

                    JOptionPane.showMessageDialog(this, "Analisis introducido");

                    this.setVisible(false);
                }
            
            
        }else{
            JOptionPane.showMessageDialog(this, error);
        }
        
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        MetodosComunes.noEscribirMasDe(txtDescripcion, 50, evt, lblControlLongitudAnalisis);
    }//GEN-LAST:event_txtDescripcionKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_IntroducirAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_IntroducirAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_IntroducirAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_IntroducirAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PC02_JD_IntroducirAnalisis dialog = new PC02_JD_IntroducirAnalisis();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblControlLongitudAnalisis;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
    private MiHibernateSingleton conexion;
}
