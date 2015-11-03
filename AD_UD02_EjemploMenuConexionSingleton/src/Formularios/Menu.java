package Formularios;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isabel
 */
public class Menu extends javax.swing.JFrame {

    public Menu() {
        ConexionSingleton.getInstance("oracle", "localhost", "ORADAM2", "alumno2", "alumno2");
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jDeskPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuEmpleados = new javax.swing.JMenu();
        jMenuIContarEmple = new javax.swing.JMenuItem();
        jMenuIVisualizar = new javax.swing.JMenuItem();
        jMenuISalir = new javax.swing.JMenuItem();
        jMenuDepar = new javax.swing.JMenu();
        jMenuIContarDep = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jDeskPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jDeskPrincipal.setToolTipText("");
        jDeskPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/01-open folder.jpg"))); // NOI18N
        jMenuEmpleados.setText("Empleados");

        jMenuIContarEmple.setText("Contar Empleados");
        jMenuIContarEmple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIContarEmpleActionPerformed(evt);
            }
        });
        jMenuEmpleados.add(jMenuIContarEmple);

        jMenuIVisualizar.setText("Mantenimiento");
        jMenuIVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIVisualizarActionPerformed(evt);
            }
        });
        jMenuEmpleados.add(jMenuIVisualizar);

        jMenuISalir.setText("Salir");
        jMenuISalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuISalirActionPerformed(evt);
            }
        });
        jMenuEmpleados.add(jMenuISalir);

        jMenuBar1.add(jMenuEmpleados);

        jMenuDepar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/02-control panel1.gif"))); // NOI18N
        jMenuDepar.setText("Departamentos");

        jMenuIContarDep.setText("Contar Departamentos");
        jMenuIContarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIContarDepActionPerformed(evt);
            }
        });
        jMenuDepar.add(jMenuIContarDep);

        jMenuBar1.add(jMenuDepar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDeskPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jDeskPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuISalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuISalirActionPerformed
        ConexionSingleton.cerrar();
        System.exit(0);
    }//GEN-LAST:event_jMenuISalirActionPerformed

    private void jMenuIVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIVisualizarActionPerformed
        try {
            ConsultaEmpleados ConsEmple = ConsultaEmpleados.getInstancia();
            ConsEmple.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuIVisualizarActionPerformed

    private void jMenuIContarEmpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIContarEmpleActionPerformed

        jDeskPrincipal.removeAll();
        ContarEmple Contar_Emple = ContarEmple.getInstancia();
        jDeskPrincipal.add(Contar_Emple);
        Contar_Emple.show(); // Visualizar los JInternalFrames

    }//GEN-LAST:event_jMenuIContarEmpleActionPerformed

    private void jMenuIContarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIContarDepActionPerformed

        jDeskPrincipal.removeAll();
        ContarDepart Contar_Depart = ContarDepart.getInstancia();
        jDeskPrincipal.add(Contar_Depart);
        Contar_Depart.show(); // Visualizar los JInternalFrames
    }//GEN-LAST:event_jMenuIContarDepActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        ConexionSingleton.cerrar();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu M = new Menu();
                M.setExtendedState(MAXIMIZED_BOTH);
                M.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDeskPrincipal;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuDepar;
    private javax.swing.JMenu jMenuEmpleados;
    private javax.swing.JMenuItem jMenuIContarDep;
    private javax.swing.JMenuItem jMenuIContarEmple;
    private javax.swing.JMenuItem jMenuISalir;
    private javax.swing.JMenuItem jMenuIVisualizar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu jPopupMenu1;
    // End of variables declaration//GEN-END:variables
}
