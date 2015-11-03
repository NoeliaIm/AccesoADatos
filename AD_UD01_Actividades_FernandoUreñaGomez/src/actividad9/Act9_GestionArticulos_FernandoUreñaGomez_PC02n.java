/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import milibreria.swing.MiSwing;
import milibreria.Clases.XML;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alumno
 */
public class Act9_GestionArticulos_FernandoUreñaGomez_PC02n extends javax.swing.JFrame {

    /**
     * Creates new form Act5_GestionArticulos_FernandoUreñaGomez_PC02n
     */
    
    public Act9_GestionArticulos_FernandoUreñaGomez_PC02n() {
        
        MiSwing.disenoGUI();
        
        this.setResizable(false);
        
        this.setTitle("Gestion Articulos");
        
        initComponents();        
        
        this.campos = new JTextField[4];
        campos[0]=campoID;
        campos[1]=campoNombre;
        campos[2]=campoPrecio;
        campos[3]=campoAlmacen;
        
        //Inicializo el objeto, no crea el fichero xml
        //xml=new XML("articulos");
        
        if(FICHEROXML.exists()){
            crearFicheroXML.setText("Actualizar fichero XML");
        }else{
            crearFicheroXML.setText("Crear fichero XML");
        }
        
        xml=new XML("articulos");
    }
    
    public boolean comprobarArticuloExistente(int id, String nombre, double precio, int cantAlmacen){
        
        boolean existe=false;
        if(FICHERO.exists()){
            try(DataInputStream dis=new DataInputStream(new FileInputStream(FICHERO))){
            
            while(true){
                int idActual=dis.readInt();
                String nombreActual=dis.readUTF();
                double precioActual=dis.readDouble();
                int cantAlmacenActual=dis.readInt();
               
                if(idActual==id && nombreActual.equals(nombre) && precioActual==precio && cantAlmacenActual==cantAlmacen){
                    existe=true;
                }
            }
            
            
            }catch(EOFException e){
                
            }catch(IOException e){

            }
        }
        return existe;
    }
    
    public void anadeArticulo(){
        
        try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(FICHERO, FICHERO.exists()))){

            dos.writeInt(Integer.parseInt(campos[0].getText()));
            dos.writeUTF(campos[1].getText());
            dos.writeDouble(Double.parseDouble(campos[2].getText()));
            dos.writeInt(Integer.parseInt(campos[3].getText()));
            
            dos.flush();
        }catch(IOException e){
            
        }
    }
    
    //
    public void meterDatosFicheroXML(){
        
        try(DataInputStream dis=new DataInputStream(new FileInputStream(FICHERO))){
            
            while(true){
                
                int idActual=dis.readInt();
                String nombreActual=dis.readUTF();
                double precioActual=dis.readDouble();
                int cantAlmacenActual=dis.readInt();

                Element raiz=xml.getDocumento().createElement("articulo");
                xml.getDocumento().getDocumentElement().appendChild(raiz);

                xml.CrearElemento("id", Integer.toString(idActual), raiz);

                xml.CrearElemento("nombre", nombreActual, raiz);

                xml.CrearElemento("precio", Double.toString(precioActual), raiz);

                xml.CrearElemento("cantAlmacen", Integer.toString(cantAlmacenActual), raiz);
            }
       
        }catch(EOFException e){
            
        }catch(IOException e){
            
        }
        
    }
    
    
    public void leerFicheroXML(){
        
        //Obtenemos 
        xml.obtenerRaiz();
        
        //Nos devuelve la lista de nodos
        NodeList articulos=xml.listaNodos("articulo");
        
        //Limpio el area de texto antes de volver a escribir
        areaTexto.setText("");
        
        //Escribimos en el area de texto
        for(int i=0;i<articulos.getLength();i++){
            
            Node articulo=articulos.item(i);
            if(articulo.getNodeType()==Node.ELEMENT_NODE){
                Element elemento=(Element) articulo;
                areaTexto.setText(areaTexto.getText()+
                                    xml.getNodo("id", elemento)+"\n"+
                                    xml.getNodo("nombre", elemento)+"\n"+
                                    xml.getNodo("precio", elemento)+"\n"+
                                    xml.getNodo("cantAlmacen", elemento)+"\n\n");
                
            }
        }
        
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoID = new javax.swing.JTextField();
        campoNombre = new javax.swing.JTextField();
        campoPrecio = new javax.swing.JTextField();
        campoAlmacen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        crearFicheroXML = new javax.swing.JButton();
        verFicheroXML = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        errorIDLabel = new javax.swing.JLabel();
        errorNombreLabel = new javax.swing.JLabel();
        errorLimitePrecioLabel = new javax.swing.JLabel();
        errorLimiteCantAlmacenLabel = new javax.swing.JLabel();
        errorCaracterCantAlmacenLabel = new javax.swing.JLabel();
        errorCaracteresPrecioLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IdArticulo");

        jLabel2.setText("NombreArtic");

        jLabel3.setText("Precio Unitario");

        jLabel4.setText("CantAlmacen");

        campoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoIDKeyTyped(evt);
            }
        });

        campoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreKeyTyped(evt);
            }
        });

        campoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPrecioKeyTyped(evt);
            }
        });

        campoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoAlmacenKeyTyped(evt);
            }
        });

        jButton1.setText("Grabar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        crearFicheroXML.setText("Crear fichero XML");
        crearFicheroXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearFicheroXMLActionPerformed(evt);
            }
        });

        verFicheroXML.setText("Ver fichero XML");
        verFicheroXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verFicheroXMLActionPerformed(evt);
            }
        });

        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        jScrollPane1.setViewportView(areaTexto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(errorLimiteCantAlmacenLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(errorCaracterCantAlmacenLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(errorCaracteresPrecioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(errorLimitePrecioLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(errorNombreLabel))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(errorIDLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(crearFicheroXML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verFicheroXML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorNombreLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorLimitePrecioLabel)
                    .addComponent(errorCaracteresPrecioLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(errorLimiteCantAlmacenLabel))
                    .addComponent(errorCaracterCantAlmacenLabel))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(crearFicheroXML)
                        .addGap(53, 53, 53)
                        .addComponent(verFicheroXML)
                        .addGap(83, 83, 83))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIDKeyTyped
      MiSwing.escribirSoloNumeros(evt,errorIDLabel);
    }//GEN-LAST:event_campoIDKeyTyped

    private void campoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPrecioKeyTyped
        
        MiSwing.escribirSoloDoubles(evt,errorLimitePrecioLabel);
        MiSwing.noEscribirMasDe(campoPrecio, 9, evt,errorCaracteresPrecioLabel);
    }//GEN-LAST:event_campoPrecioKeyTyped

    private void campoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyTyped
      
        MiSwing.noEscribirMasDe(campoNombre, 50, evt,errorNombreLabel);
    }//GEN-LAST:event_campoNombreKeyTyped

    private void campoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoAlmacenKeyTyped
        MiSwing.escribirSoloNumeros(evt, errorCaracterCantAlmacenLabel);
        MiSwing.noEscribirMasDe(campoAlmacen, 5, evt,errorLimiteCantAlmacenLabel);
    }//GEN-LAST:event_campoAlmacenKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (MiSwing.comprobarVacios(campos)){
            JOptionPane.showMessageDialog(this, "Uno de los campos esta vacio");
        }else{
            if(comprobarArticuloExistente(Integer.parseInt(campos[0].getText()),
                    campos[1].getText(),
                    Double.parseDouble(campos[2].getText()),
                    Integer.parseInt(campos[3].getText()))){
                
                JOptionPane.showMessageDialog(this, "El articulo ya existe");
            }else{
                anadeArticulo();
                JOptionPane.showMessageDialog(this, "articulo añadido");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MiSwing.limpiaCampos(campos);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void crearFicheroXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearFicheroXMLActionPerformed
        
        //meto los datos en el fichero XML
        meterDatosFicheroXML();
        //Creo el fichero
        if(xml.creaFichero()){
            JOptionPane.showMessageDialog(this, "Fichero generado correctamente");
        }
        
    }//GEN-LAST:event_crearFicheroXMLActionPerformed

    private void verFicheroXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verFicheroXMLActionPerformed
        if(FICHEROXML.exists()){
            leerFicheroXML();
        }else{
            JOptionPane.showMessageDialog(this, "El fichero no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_verFicheroXMLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Act9_GestionArticulos_FernandoUreñaGomez_PC02n().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JTextField campoAlmacen;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPrecio;
    private javax.swing.JButton crearFicheroXML;
    private javax.swing.JLabel errorCaracterCantAlmacenLabel;
    private javax.swing.JLabel errorCaracteresPrecioLabel;
    private javax.swing.JLabel errorIDLabel;
    private javax.swing.JLabel errorLimiteCantAlmacenLabel;
    private javax.swing.JLabel errorLimitePrecioLabel;
    private javax.swing.JLabel errorNombreLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton verFicheroXML;
    // End of variables declaration//GEN-END:variables
    private JTextField campos[];
    private final File FICHERO=new File("articulos.dat");
    private final File FICHEROXML=new File("articulos.xml");
    private XML xml;
}
