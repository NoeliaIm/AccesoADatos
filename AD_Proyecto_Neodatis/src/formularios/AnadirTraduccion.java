package formularios;


import clases.Idioma;
import clases.MetodosComunes;
import clases.MiNeodatisSingleton;
import clases_bd.Categoria;
import clases_bd.Idiomas;
import clases_bd.Traducciones;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class AnadirTraduccion extends javax.swing.JDialog {

    /**
     * Creates new form AnadirTraduccion
     * @param frame
     * @param idioma
     */
    public AnadirTraduccion(MDApp frame, Idioma idioma) {
        
        this.setModal(true);
        this.setTitle(idioma.getProperty("anadirTraduccion_titulo"));
        
        initComponents();
        
        int x = (frame.getWidth() - this.getWidth()) / 2;
        int y = (frame.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        this.idioma=idioma;
        //this.diccionario=diccionario;
        
        conexion = MiNeodatisSingleton.getInstance("Diccionario.db", "USR_DICCIONARIO", "123456");
        
        conexion.rellenarComboBoxCategorias(cmbCategorias, idioma.getProperty("anadirTraduccion_eleccionCategoria"), conexion);
        
        conexion.rellenarIdiomas(cmbIdiomas, idioma.getProperty("anadirTraduccion_eleccionIdioma"), MDApp.codigoIdiomaActual);
       
        idioma(idioma);
    }
    
    private void idioma(Idioma idioma){
        
        palabraOrigenLabel.setText(idioma.getProperty("anadirTraduccion_textoOrigen"));
        lblPalabraMeta.setText(idioma.getProperty("anadirTraduccion_textoMeta"));
        lblCategorias.setText(idioma.getProperty("anadirTraduccion_categoria"));
        lblIdioma.setText(idioma.getProperty("anadirTraduccion_idioma"));
        descripcion.setText(idioma.getProperty("anadirTraduccion_descripcion"));
        okButton.setText(idioma.getProperty("anadirTraduccion_botonOk"));
        btnAadirOtraMas.setText(idioma.getProperty("anadirTraduccion_botonAnadirOtra"));
        cancelButton.setText(idioma.getProperty("anadirTraduccion_botonCancel"));
    }
    
    
    public void nuevo(){
        
        palabraOrigen.setText("");
        PalabraMeta.setText("");
        textArea.setText("");
        cmbCategorias.setSelectedIndex(0);
        cmbIdiomas.setSelectedIndex(0);
        
        palabraOrigen.requestFocus();
        
    }
    
    public void anadir(){
        
        if(palabraOrigen.getText().equals("") || PalabraMeta.getText().equals("") || cmbIdiomas.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this.getContentPane(), idioma.getProperty("soporteTecnico_mensajeE1"),  idioma.getProperty("anadirTraduccion_tituloMensaje1"), JOptionPane.ERROR_MESSAGE);
        }else{
            
            Idiomas idiomas=(Idiomas)conexion.devolverObjeto(MetodosComunes.devolverCodigoComboBox(cmbIdiomas), "idIdioma", "clases_bd.Idiomas");
            Traducciones traduccionNueva=new Traducciones(getPalabraOrigen().toLowerCase(), getPalabraMeta().toLowerCase(),idiomas, getDescripcion());
            
            boolean existe;
            Categoria categoria=null;
            if(cmbCategorias.getSelectedIndex()!=0){
                
                categoria=(Categoria)conexion.devolverObjeto(getCategoria(), "descripcion", "clases_bd.Categoria");
                
            }
            
            existe=conexion.existeTraduccion(traduccionNueva);
            
            if(existe){
                JOptionPane.showMessageDialog(this.getContentPane(), idioma.getProperty("anadirTraduccion_Mensaje1"), idioma.getProperty("anadirTraduccion_tituloMensaje2"), JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this.getContentPane(), idioma.getProperty("anadirTraduccion_exito"), idioma.getProperty("tituloExitoPDF"), JOptionPane.INFORMATION_MESSAGE);
                
                int idTraduccion=conexion.proximoId("idTraduccion", "clases_bd.Traducciones");
                
                traduccionNueva.setIdTraduccion(idTraduccion);
                
                if(cmbCategorias.getSelectedIndex()!=0){
                    traduccionNueva.setCategoria(categoria);
                }
                
                conexion.guardarObjeto(traduccionNueva);

                conexion.commit();
                
            }

        }
        
        
    }

   
    public String getPalabraOrigen() {
        if(palabraOrigen.getText()==null){
            return "";
        }else{
            return palabraOrigen.getText();
        }

    }

    public String getPalabraMeta() {
        if(palabraOrigen.getText()==null){
                return "";
        }else{
                return PalabraMeta.getText();
        }
    }

    public String getCategoria(){
        if(cmbCategorias.getSelectedIndex()==0){
            return "";
        }else{
            return cmbCategorias.getSelectedItem().toString();
        }
        
    }

    public String getDescripcion() {
        if(textArea.getText()==null){
            return "";
        }else{
            return textArea.getText();
        }
    }

    public String getIdiomas() {
        return cmbIdiomas.getSelectedItem().toString();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        palabraOrigen = new javax.swing.JTextField();
        PalabraMeta = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        cmbCategorias = new javax.swing.JComboBox();
        cmbIdiomas = new javax.swing.JComboBox();
        palabraOrigenLabel = new javax.swing.JLabel();
        lblPalabraMeta = new javax.swing.JLabel();
        lblCategorias = new javax.swing.JLabel();
        lblIdioma = new javax.swing.JLabel();
        descripcion = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        btnAadirOtraMas = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        palabraOrigenLabel.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        palabraOrigenLabel.setText("Texto Origen");

        lblPalabraMeta.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblPalabraMeta.setText("Texto Meta");

        lblCategorias.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblCategorias.setText("Categoria");

        lblIdioma.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblIdioma.setText("Idioma");

        descripcion.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        descripcion.setText("Descripcion");

        okButton.setText("Añadir");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        btnAadirOtraMas.setText("Añadir otra");
        btnAadirOtraMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAadirOtraMasActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(palabraOrigenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(lblPalabraMeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdioma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PalabraMeta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(palabraOrigen)
                    .addComponent(cmbCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbIdiomas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAadirOtraMas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palabraOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(palabraOrigenLabel))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PalabraMeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPalabraMeta))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategorias))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdioma))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcion)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(btnAadirOtraMas)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        
        anadir();
        this.setVisible(false);
        
    }//GEN-LAST:event_okButtonActionPerformed

    private void btnAadirOtraMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAadirOtraMasActionPerformed
       
        anadir();
        nuevo();
        
    }//GEN-LAST:event_btnAadirOtraMasActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PalabraMeta;
    private javax.swing.JButton btnAadirOtraMas;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbIdiomas;
    private javax.swing.JLabel descripcion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JLabel lblPalabraMeta;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField palabraOrigen;
    private javax.swing.JLabel palabraOrigenLabel;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
    private Idioma idioma;
    private MiNeodatisSingleton conexion;
}
