package formularios;

import clases.Idioma;
import clases.MetodosComunes;
import clases_bd.Categoria;
import clases_bd.Idiomas;
import clases_bd.Traducciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class EditarTraduccion extends EditarYBorrarTraducciones {

    /**
     * Creates new form EditarTraduccion
     * @param frame
     * @param idioma
     */
    public EditarTraduccion(JFrame frame, Idioma idioma) {
		
        super(frame, idioma);
        initComponents();
        
        conexion.rellenarComboBoxCategorias(cmbCategorias, idioma.getProperty("anadirTraduccion_eleccionCategoria"), conexion);
        
        conexion.rellenarIdiomas(cmbIdiomas, "", -1);
        
        int x = (frame.getWidth() - this.getWidth()) / 2;
        int y = (frame.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        buscaCoincidencias("");
        
        eventosEspeciales();
        idioma();
        
        habilitarDeshabilitar(false);
    }
    
    public EditarTraduccion(JFrame frame, Idioma idioma, Traducciones tradu) {
        this(frame, idioma);
        
        Traducciones objeto;
        boolean encontrado=false;
        for(int i=0;i<listaCoincidencias.getModel().getSize() & !encontrado;i++){
            
            objeto = (Traducciones)listaCoincidencias.getModel().getElementAt(i);
            
            if(objeto == tradu){
                listaCoincidencias.setSelectedIndex(i);
                encontrado=true;
            }
            
        }
        
    }
    
    private void idioma(){
        
        panel.setBorder(BorderFactory.createTitledBorder(idioma.getProperty("anadirTraduccion_tituloPanel")));
        this.setTitle(idioma.getProperty("editarTraduccion_titulo"));
        palabraOrigenLabel.setText(idioma.getProperty("anadirTraduccion_textoOrigen"));
        lblPalabraMeta.setText(idioma.getProperty("anadirTraduccion_textoMeta"));
        lblCategorias.setText(idioma.getProperty("anadirTraduccion_categoria"));
        lblIdioma.setText(idioma.getProperty("anadirTraduccion_idioma"));
        descripcion.setText(idioma.getProperty("anadirTraduccion_descripcion"));
        btnModEli.setText(idioma.getProperty("editarTraduccion_btnModificar"));
    }
    
    public void eventosEspeciales(){
        
        btnModEli.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                 if(listaCoincidencias.getSelectedValue()==null){
                   JOptionPane.showMessageDialog(frame, idioma.getProperty("borrarTraduccion_sinFilaSeleccionada"), idioma.getProperty("anadirTraduccion_tituloMensaje1"), JOptionPane.ERROR_MESSAGE); 
                }else{
                    Traducciones t=(Traducciones)listaCoincidencias.getSelectedValue();
 
                    Idiomas idiomaTradu=(Idiomas)conexion.devolverObjeto(MetodosComunes.devolverCodigoComboBox(cmbIdiomas), "idIdioma", "clases_bd.Idiomas");
                    
                    Categoria categoria;
                    
                    if(cmbCategorias.getSelectedIndex()!=0){
                        categoria=(Categoria)conexion.devolverObjeto(cmbCategorias.getSelectedItem().toString(), "descripcion", "clases_bd.Categoria");
                    }else{
                        categoria=null;
                    }
                    
                    Traducciones t2=new Traducciones(
                                    palabraOriginal.getText(), 
                                    palabraTraducida.getText(), 
                                    idiomaTradu,
                                    textArea.getText(),
                                    categoria);

                    if(conexion.existeTraduccion(t2)){
                        JOptionPane.showMessageDialog(frame, idioma.getProperty("editarTraduccion_traduccionExistente"), idioma.getProperty("anadirTraduccion_Mensaje1"), JOptionPane.ERROR_MESSAGE);
                    }else{
                       
                        t.setPalabraMeta(palabraTraducida.getText());
                        t.setPalabraOrigen(palabraOriginal.getText());
                        t.setIdiomas(idiomaTradu);
                        t.setCategoria(categoria);
                        t.setDescripcion(textArea.getText());
                                
                        conexion.guardarObjeto(t);
                        
                        conexion.commit();
                        
                        JOptionPane.showMessageDialog(frame, idioma.getProperty("editarTraduccion_traduccionModificada"), idioma.getProperty("editarTraduccion_modificacion"), JOptionPane.INFORMATION_MESSAGE);
                        buscaCoincidencias(busquedaPalabra.getText());
                        
                        nuevo();
                        
                    }

                 }
                
            }

    });
        
        listaCoincidencias.addListSelectionListener(new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(listaCoincidencias.getSelectedValue()!=null){
                    
                    Traducciones t=(Traducciones)listaCoincidencias.getSelectedValue();
                
                    habilitarDeshabilitar(true);
                    
                    palabraOriginal.setText(t.getPalabraOrigen());
                    palabraTraducida.setText(t.getPalabraMeta());
                
                    if (t.getCategoria()!=null){
                        cmbCategorias.setSelectedItem(t.getCategoria().getDescripcion());
                    }else{
                        cmbCategorias.setSelectedIndex(0);
                    }
                    
                    cmbIdiomas.setSelectedItem(t.getIdiomas().getIdioma());
                    textArea.setText(t.getDescripcion()); 
                
                }else{
                    nuevo();
                    habilitarDeshabilitar(false);
                }
             
            }
            
        });
        
    }
    
    public void nuevo(){
        
        palabraTraducida.setText("");
        palabraOriginal.setText("");
        textArea.setText("");
        cmbIdiomas.setSelectedIndex(0);
        cmbCategorias.setSelectedIndex(0);
        
    }
    
    public void habilitarDeshabilitar(boolean estado){
        
        palabraOriginal.setEnabled(estado);
        palabraTraducida.setEnabled(estado);
        cmbCategorias.setEnabled(estado);
        cmbIdiomas.setEnabled(estado);
        textArea.setEnabled(estado);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        palabraTraducida = new javax.swing.JTextField();
        descripcion = new javax.swing.JLabel();
        lblIdioma = new javax.swing.JLabel();
        palabraOriginal = new javax.swing.JTextField();
        lblCategorias = new javax.swing.JLabel();
        cmbCategorias = new javax.swing.JComboBox();
        cmbIdiomas = new javax.swing.JComboBox();
        palabraOrigenLabel = new javax.swing.JLabel();
        lblPalabraMeta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        descripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        descripcion.setText("Descripcion");

        lblIdioma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIdioma.setText("Idioma");

        lblCategorias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategorias.setText("Categoria");

        palabraOrigenLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        palabraOrigenLabel.setText("Texto Origen");

        lblPalabraMeta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPalabraMeta.setText("Texto Meta");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPalabraMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbIdiomas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(palabraOrigenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(palabraTraducida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(palabraOriginal))))
                .addGap(27, 27, 27))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palabraOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(palabraOrigenLabel))
                .addGap(31, 31, 31)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palabraTraducida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPalabraMeta))
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategorias)
                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdioma)
                    .addComponent(cmbIdiomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcion)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbIdiomas;
    private javax.swing.JLabel descripcion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JLabel lblPalabraMeta;
    private javax.swing.JLabel palabraOrigenLabel;
    private javax.swing.JTextField palabraOriginal;
    private javax.swing.JTextField palabraTraducida;
    private javax.swing.JPanel panel;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
   
    private JFrame frame;
}
