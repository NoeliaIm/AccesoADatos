/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class InformacionModelo extends javax.swing.JDialog {

    /**
     * Creates new form InformacionModelo
     */
    public InformacionModelo() {
        
        initComponents();
        
        this.setModal(true);
        
        this.setTitle("Añadir Modelo");
        
        conexion=frmPrincipal.devConexion();
        
        //Relleno las marcas disponibles
        conexion.rellenaComboBox2Columnas(cmbMarca, 
                                        CONSULTAMARCA,
                                        "--Elige Marca--", 
                                        "idmarca",
                                        "marca");
        
        codigoOperacion=frmPrincipal.INSERT;
        
        //Evito pegar en el campo modelo
        MetodosComunes.evitarPegar(txtModelo);
        
    }
    
    public InformacionModelo(int idModelo){
        
        initComponents();
        
        this.setTitle("Modificar Modelo");
        
        this.setModal(true);
        
        conexion=frmPrincipal.devConexion();
        
        //Relleno marcas
        conexion.rellenaComboBox2Columnas(cmbMarca, 
                                        CONSULTAMARCA,
                                        "--Elige Marca--", 
                                        "idmarca",
                                        "marca");
        
        idModeloActual=idModelo;
        
        int idMarca=conexion.devolverValorInt("idmarca", "s_modelos", "idmodelo="+idModeloActual);
        
        //Asigno la marca, con el id pasado
        MetodosComunes.asignarItemCmb2Columnas(cmbMarca, idMarca);
        
        //Escribo el modelo con el id pasado
        txtModelo.setText(conexion.devolverValorString("modelo", "s_modelos", "idmodelo="+idModeloActual));
        
        codigoOperacion=frmPrincipal.UPDATE;
        
        //Evito pegar en el campo modelo
        MetodosComunes.evitarPegar(txtModelo);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbMarca = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGrabar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblControlModelo = new javax.swing.JLabel();
        lblControlLongitudModelo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion modelo");
        setResizable(false);

        cmbMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMarcaItemStateChanged(evt);
            }
        });
        cmbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcaActionPerformed(evt);
            }
        });

        jLabel1.setText("Marca");

        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });

        jLabel2.setText("Modelo");

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtModelo)
                            .addComponent(cmbMarca, 0, 150, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblControlLongitudModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(lblControlModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblControlModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblControlLongitudModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcaActionPerformed

    }//GEN-LAST:event_cmbMarcaActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed

        String error="";

        if(MetodosComunes.devolverCodigoComboBox(cmbMarca)==-1){
            error+="No puedes dejar el nombre del modelo vacio \n";
        }else if(txtModelo.getText().equals("")){
            error+="No puedes dejar el nombre del modelo vacio \n";
        }

        if(conexion.existeValor(txtModelo.getText().toUpperCase().trim(), "s_modelos", "modelo")){
            error+="Ya existe un modelo con ese nombre \n";
        }

        //Si no hay errores, completa la operacion
        if(error.equals("")){

            if(codigoOperacion==frmPrincipal.INSERT){

                idModeloActual=conexion.proximoIDDisponible("idmodelo", "s_modelos");

                conexion.ejecutarInstruccion("insert into s_modelos values("
                    + ""+idModeloActual+","
                    + "'"+txtModelo.getText().toUpperCase()+"',"
                    + ""+MetodosComunes.devolverCodigoComboBox(cmbMarca)+","
                    + "0)");

                conexion.commit();

                JOptionPane.showMessageDialog(this, "Modelo insertado");

                this.setVisible(false);

            }else{

                conexion.ejecutarInstruccion("update s_modelos "
                    + "set modelo='"+txtModelo.getText().toUpperCase()+"' "
                    + "where idmodelo="+idModeloActual+"");

                conexion.commit();

                JOptionPane.showMessageDialog(this, "Modelo actualizado");

                this.setVisible(false);

            }

        }else{

            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMarcaItemStateChanged
     
        
        if(evt.getStateChange()==ItemEvent.SELECTED){
            
            if(MetodosComunes.devolverCodigoComboBox(cmbMarca)!=-1){
                txtModelo.setEnabled(true);
            }else{
                txtModelo.setEnabled(false);
            }
        }
        
        
    }//GEN-LAST:event_cmbMarcaItemStateChanged

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped
       MetodosComunes.escribirAlfanumerico(evt, lblControlModelo);
       MetodosComunes.noEscribirMasDe(txtModelo, 50, evt, lblControlLongitudModelo);
    }//GEN-LAST:event_txtModeloKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JComboBox cmbMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblControlLongitudModelo;
    private javax.swing.JLabel lblControlModelo;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
    private ConexionDB conexion;
    private int codigoOperacion;
    private int idModeloActual;
    
    //Constantes
    private final String CONSULTAMARCA="select idmarca, marca "
                                        + "from s_marcas "
                                        + "where eliminado=0";
        
}
