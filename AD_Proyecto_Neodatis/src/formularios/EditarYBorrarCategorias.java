package formularios;

import clases.Idioma;
import clases.MiNeodatisSingleton;
import clases_bd.Categoria;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class EditarYBorrarCategorias extends javax.swing.JDialog {

    /**
     * Creates new form EditarYBorrarCategorias
     * @param frame
     * @param idioma
     */
    public EditarYBorrarCategorias(JFrame frame,Idioma idioma) {
        super(frame, true);
        
        modeloCategorias=new DefaultListModel<>();
        
        initComponents();
        
        int x = (frame.getWidth() - this.getWidth()) / 2;
        int y = (frame.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        this.idioma=idioma;
        idioma();
        
        conexion=MiNeodatisSingleton.getInstance("Diccionario.db", "USR_DICCIONARIO", "123456");
    }
    
    private void idioma(){
		
        labelPalabraBusqueda.setText(idioma.getProperty("editaryborrar_etiqueta"));
        btnBuscar.setText(idioma.getProperty("editaryborrar_botonBuscar"));
        btnSalir.setText(idioma.getProperty("editaryborrar_botonSalir"));
        coincidencias.setText(idioma.getProperty("editaryborrar_coincidencias"));

    }
    
   protected void buscaCoincidencias(String palabraBuscada){

        modeloCategorias.removeAllElements();

        ICriterion criterio=Where.like("descripcion", palabraBuscada);
          
        IQuery consulta= new CriteriaQuery(Categoria.class, criterio);
        
        Objects<Categoria> categorias= conexion.getOdb().getObjects(consulta);
        
        while(categorias.hasNext()){
            
            Categoria categoria=categorias.next();
            
            modeloCategorias.addElement(categoria.getDescripcion());
            
        }
        	
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        coincidencias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCoincidencias = new javax.swing.JList();
        busquedaPalabra = new javax.swing.JTextField();
        labelPalabraBusqueda = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnModEli = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        coincidencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencias.setText("jLabel1");

        listaCoincidencias.setModel(modeloCategorias);
        listaCoincidencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaCoincidencias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaCoincidenciasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaCoincidencias);

        labelPalabraBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPalabraBusqueda.setText("jLabel1");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnModEli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModEliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(busquedaPalabra)
                    .addComponent(labelPalabraBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModEli, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(coincidencias, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coincidencias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPalabraBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(busquedaPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(17, 17, 17)
                        .addComponent(btnModEli, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String palabraBuscada=busquedaPalabra.getText().toLowerCase().trim();

        buscaCoincidencias(palabraBuscada);

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnModEliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModEliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModEliActionPerformed

    private void listaCoincidenciasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaCoincidenciasValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listaCoincidenciasValueChanged

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    protected javax.swing.JButton btnModEli;
    private javax.swing.JButton btnSalir;
    protected javax.swing.JTextField busquedaPalabra;
    private javax.swing.JLabel coincidencias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPalabraBusqueda;
    protected javax.swing.JList listaCoincidencias;
    // End of variables declaration//GEN-END:variables
    protected DefaultListModel<String> modeloCategorias;
    protected Idioma idioma;
    protected MiNeodatisSingleton conexion;
}
