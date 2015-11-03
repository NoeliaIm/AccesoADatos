/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class InformacionMarca extends javax.swing.JDialog {

    /**
     * Creates new form InformacionMarca
     */
    public InformacionMarca() {
        
        this.setTitle("Informacion marca");
       
        initComponents();
        
        this.setModal(true);
        
        codigoOperacion=frmPrincipal.INSERT;
        
        conexion=frmPrincipal.devConexion();
        
        //Evito pegar en el campo marca
        MetodosComunes.evitarPegar(txtMarca);
        
    }
    
    public InformacionMarca(int idMarca) {
        
        initComponents();
        
        this.setTitle("Informacion marca");
        
        this.setModal(true);
        
        codigoOperacion=frmPrincipal.UPDATE;
                
        idMarcaActual=idMarca;
    
        conexion=frmPrincipal.devConexion();
        
        //Escribo la marca con el id pasado
        txtMarca.setText(conexion.devolverValorString("marca", "s_marcas", "idmarca = "+idMarca));
        
        //Evito pegar en el campo marca
        MetodosComunes.evitarPegar(txtMarca);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblControlMarca = new javax.swing.JLabel();
        lblControlLongitudMarca = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });

        jLabel1.setText("Marca");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblControlLongitudMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(lblControlMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblControlMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblControlLongitudMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
       
        String error="";
        
        if(txtMarca.getText().equals("")){
            error+="No puedes dejar el nombre de la marca vacia \n";
        }
        
        if(conexion.existeValor(txtMarca.getText().toUpperCase().trim(), "s_marcas", "marca")){
            error+="Ya existe una marca con ese nombre \n";
        }
        
        //Si no hay errores, completa la operacion
        if(error.equals("")){
            
            if(codigoOperacion==frmPrincipal.INSERT){
                
                idMarcaActual=conexion.proximoIDDisponible("idmarca", "s_marcas");
                
                conexion.ejecutarInstruccion("insert into s_marcas values("
                                            + ""+idMarcaActual+","
                                            + "'"+txtMarca.getText().toUpperCase()+"',"
                                            + "0)");
                
                conexion.commit();
                
                JOptionPane.showMessageDialog(this, "Marca insertada");
                
                this.setVisible(false);
                
            }else{
                
                
                conexion.ejecutarInstruccion("update s_marcas "
                                            + "set marca='"+txtMarca.getText().toUpperCase()+"' "
                                            + "where idmarca="+idMarcaActual+"");
                
                conexion.commit();
                
                JOptionPane.showMessageDialog(this, "Marca actualizada");
                
                this.setVisible(false);
                
            }
            
        }else{
            
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        
        }
        
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlMarca);
        MetodosComunes.noEscribirMasDe(txtMarca, 50, evt, lblControlLongitudMarca);
        
    }//GEN-LAST:event_txtMarcaKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblControlLongitudMarca;
    private javax.swing.JLabel lblControlMarca;
    private javax.swing.JTextField txtMarca;
    // End of variables declaration//GEN-END:variables
    private int codigoOperacion;
    private int idMarcaActual;
    private ConexionDB conexion;
}
