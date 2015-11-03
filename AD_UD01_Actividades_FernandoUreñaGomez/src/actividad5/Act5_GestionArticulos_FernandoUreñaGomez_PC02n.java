/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad5;

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

/**
 *
 * @author Alumno
 */
public class Act5_GestionArticulos_FernandoUreñaGomez_PC02n extends javax.swing.JFrame {

    /**
     * Creates new form Act5_GestionArticulos_FernandoUreñaGomez_PC02n
     */
    
    public Act5_GestionArticulos_FernandoUreñaGomez_PC02n() {
        
        MiSwing.disenoGUI();
        
        this.setResizable(false);
        
        this.setTitle("Gestion Articulos");
        
        initComponents();        
        
        this.campos = new JTextField[4];
        campos[0]=campoID;
        campos[1]=campoNombre;
        campos[2]=campoPrecio;
        campos[3]=campoAlmacen;
        
    }

    public boolean comprobarVacios(JTextField campos[]){
        boolean vacio=false;
        
        for(int i=0;i<campos.length && !vacio;i++){
            if(campos[i].getText().isEmpty()){
                vacio=true;
            }
        }
        return vacio;
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIDKeyTyped
      MiSwing.escribirSoloNumeros(evt);
    }//GEN-LAST:event_campoIDKeyTyped

    private void campoPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPrecioKeyTyped
        
        MiSwing.escribirSoloDoubles(evt);
        MiSwing.noEscribirMasDe(campoPrecio, 9, evt);
    }//GEN-LAST:event_campoPrecioKeyTyped

    private void campoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyTyped
      
        MiSwing.noEscribirMasDe(campoNombre, 50, evt);
    }//GEN-LAST:event_campoNombreKeyTyped

    private void campoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoAlmacenKeyTyped
        MiSwing.escribirSoloNumeros(evt);
        MiSwing.noEscribirMasDe(campoAlmacen, 5, evt);
    }//GEN-LAST:event_campoAlmacenKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (comprobarVacios(campos)){
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Act5_GestionArticulos_FernandoUreñaGomez_PC02n().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoAlmacen;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPrecio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
    private JTextField campos[];
    private final File FICHERO=new File("articulos.dat");
}
