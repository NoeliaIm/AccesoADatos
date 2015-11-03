package formularios;


import clases.Idioma;
import clases_bd.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class BorrarCategoria extends EditarYBorrarCategorias {

    /**
     * Creates new form BorrarCategoria
     * @param frame
     * @param idioma
     */
    public BorrarCategoria(JFrame frame, Idioma idioma) {
        super(frame, idioma);
        initComponents();
        
        int x = (frame.getWidth() - this.getWidth()) / 2;
        int y = (frame.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        
        idioma();
        eventosEspeciales();
        buscaCoincidencias(busquedaPalabra.getText());
    }
    
    private void eventosEspeciales(){
        
        btnModEli.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
               
               if(listaCoincidencias.getSelectedValue()==null){
                   JOptionPane.showMessageDialog(frame, idioma.getProperty("borrarCategoria_sinFilaSeleccionada"), idioma.getProperty("anadirTraduccion_tituloMensaje1"), JOptionPane.ERROR_MESSAGE); 
               }else{
                    String cadena=(String)listaCoincidencias.getSelectedValue();
                    int eleccion=JOptionPane.showConfirmDialog(frame, idioma.getProperty("borrarTraduccion_mensajeConfirmar"), idioma.getProperty("borrarTraduccion_mensajeConfirmarTitulo"), JOptionPane.YES_NO_OPTION);
                    if(eleccion==JOptionPane.YES_OPTION){
                        
                        Categoria categoriaActual=(Categoria)conexion.devolverObjeto((String)listaCoincidencias.getSelectedValue(), "descripcion", "clases_bd.Categoria");
                        
                        conexion.actulizarCategoriasTraducciones(categoriaActual);
                        
                        conexion.borrarObjeto(categoriaActual);
                        
                        conexion.commit();
                        modeloCategorias.removeElement(cadena);
                        JOptionPane.showMessageDialog(frame, idioma.getProperty("borrarCategoria_mensaje"), idioma.getProperty("borrarTraduccion_borradoExitoTitulo"), JOptionPane.INFORMATION_MESSAGE);
                      
                }
               }
                

            }

        });
        
    }
    
    private void idioma(){
        
        this.setTitle(idioma.getProperty("borrarCategoria_titulo"));
        btnModEli.setText(idioma.getProperty("borrarTraduccion_borrar"));
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private JFrame frame;
}
