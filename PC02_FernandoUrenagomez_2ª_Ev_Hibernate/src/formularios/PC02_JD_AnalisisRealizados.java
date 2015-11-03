/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelos.Hanalrealiz;
import org.hibernate.Query;

/**
 *
 * @author Isabel
 */
public class PC02_JD_AnalisisRealizados extends javax.swing.JDialog {

    /**
     * Creates new form PCXX_JD_GestionClases
     */
    public PC02_JD_AnalisisRealizados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        conexion=MiHibernateSingleton.getInstance();
        
        rellenarCombos();
    }
    
    public void rellenarCombos(){
        
        String sql="select idpaciente, nombrep from Hpaciente where eliminado=false";
        
        conexion.rellenarCMBObjectInt(comboPaciente, sql, "Seleccione paciente");
        
        sql="select idanalisis, descripcion from Hanalisis where eliminado=false";
        
        conexion.rellenarCMBObjectInt(comboAnalisis, sql, "Seleccione analisis");
        
        sql="select idmedico, nombrem from Hmedicos where eliminado=false";
        
        conexion.rellenarCMBObjectInt(comboMedico, sql, "Seleccione medico");
        
    }
    
    public boolean comprobarIguales(){
        
        int idpaciente=MetodosComunes.devolverCodigoComboBox(comboPaciente);
        int idanalisis=MetodosComunes.devolverCodigoComboBox(comboAnalisis);
        int idmedico=MetodosComunes.devolverCodigoComboBox(comboMedico);
        int fecha=MetodosComunes.deFechaANumero(jDateFechaNac.getDate());

        conexion.abrirSesion();
        Query consulta=conexion.getSesion().createQuery("from Hanalrealiz where idpaciente="+idpaciente+" and idanalisis="+idanalisis+" and idmedico="+idmedico+" and fechaanalisis="+fecha);
        
        boolean existe=consulta.list().size()>0;
        
        conexion.cerrarSesion();
        
        return existe;
    }
    
    public void nuevo(){
        comboPaciente.setSelectedIndex(0);
        comboAnalisis.setSelectedIndex(0);
        comboMedico.setSelectedIndex(0);
        jDateFechaNac.setDate(null);
        comboResultado.setSelectedIndex(0);
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
        jLabel3 = new javax.swing.JLabel();
        comboPaciente = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jDateFechaNac = new com.toedter.calendar.JDateChooser();
        btnAnadir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboAnalisis = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        comboMedico = new javax.swing.JComboBox();
        comboResultado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Clases");

        jLabel1.setText("Paciente");

        jLabel3.setText("Resultado");

        comboPaciente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Paciente" }));

        jLabel5.setText("Fecha Análisis");

        btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addition.gif"))); // NOI18N
        btnAnadir.setText("Añadir");
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salir22x22.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setText("Análisis");

        comboAnalisis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Análisis" }));

        jLabel6.setText("Medico");

        comboMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Médico" }));

        comboResultado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Resultado", "Positivo", "Negativo" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboPaciente, 0, 189, Short.MAX_VALUE)
                                        .addComponent(comboAnalisis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnadir)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo)
                                .addGap(68, 68, 68)
                                .addComponent(btnSalir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnadir)
                    .addComponent(btnNuevo)
                    .addComponent(btnSalir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed
       
        String error="";
        
        JComboBox[] combos={comboPaciente, comboAnalisis, comboMedico, comboResultado};
        String[] mensajes={"paciente", "analisis", "medico", "resultado"};
        for(int i=0;i<combos.length;i++){
            
            if(combos[i].getSelectedIndex()==0){
                error+="- Debes elegir algun "+mensajes[i]+"\n";
            }
            
        }
        
        if(jDateFechaNac.getDate()==null){
            error+="- Elige una fecha\n";
        }else if(!MetodosComunes.fechaMayorqueHoy(jDateFechaNac.getDate())){
            error+="- La fecha no puede ser mayor que hoy\n";
        }else if(comprobarIguales()){
             error+="- Ya existe un registro con estos datos\n";
        }
        
        if(error.equals("")){
            
            int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de que quieres añadir el registro?", 
                                                            "Confirmacion", 
                                                            JOptionPane.YES_NO_OPTION);

            if(seleccion==JOptionPane.YES_OPTION){

                conexion.abrirSesionYTransaccion();
                
                int idpaciente=MetodosComunes.devolverCodigoComboBox(comboPaciente);
                int idanalisis=MetodosComunes.devolverCodigoComboBox(comboAnalisis);
                int idmedico=MetodosComunes.devolverCodigoComboBox(comboMedico);
                int fecha=MetodosComunes.deFechaANumero(jDateFechaNac.getDate());
                char resultado=comboResultado.getSelectedItem().toString().charAt(0);

                Hanalrealiz analisisRealizado=new Hanalrealiz();

                int proximoId=conexion.proximoIDDisponible("idanalrealiz", "Hanalrealiz");

                analisisRealizado.setIdanalrealiz(proximoId);
                analisisRealizado.setIdanalisis(idanalisis);
                analisisRealizado.setIdpaciente(idpaciente);
                analisisRealizado.setIdmedico(idmedico);
                analisisRealizado.setFechaanalisis(fecha);
                analisisRealizado.setResultado(resultado);
                analisisRealizado.setEliminado(false);

                conexion.insertar(analisisRealizado);

                JOptionPane.showMessageDialog(this, "analisis realizado correcto");

                conexion.cerrarSesionYTransaccion();
                
                nuevo();
                
            }
            
           
        }else{
            JOptionPane.showMessageDialog(this, error);
        }
        
        
        
        
    }//GEN-LAST:event_btnAnadirActionPerformed

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
            java.util.logging.Logger.getLogger(PC02_JD_AnalisisRealizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_AnalisisRealizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_AnalisisRealizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_AnalisisRealizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PC02_JD_AnalisisRealizados dialog = new PC02_JD_AnalisisRealizados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox comboAnalisis;
    private javax.swing.JComboBox comboMedico;
    private javax.swing.JComboBox comboPaciente;
    private javax.swing.JComboBox comboResultado;
    private com.toedter.calendar.JDateChooser jDateFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
    private MiHibernateSingleton conexion;
    
}
