/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AD_act4_mezclaficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Alumno
 */
public class Act4_MezclaFicheros extends javax.swing.JFrame {

    /**
     * Creates new form Act4_MezclaFicheros
     */
    public Act4_MezclaFicheros() {
        initComponents();
        areaTextoFichero1.setText(mostrarFichero(RUTA_FICHERO1));
        
        areaTextoFichero2.setText(mostrarFichero(RUTA_FICHERO2));
        
        areaTextoFichero3.setText(mostrarFichero(RUTA_FICHERO3));
    }

    public void anadirNumFichero(String rutaFichero, int num){
        
        try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(rutaFichero,true))){
            
            dos.writeInt(num);
            
        }catch(IOException e){
            
        }
        
    }
    
    public String mostrarFichero(String rutaFichero){
        String texto="";
        try(DataInputStream dis=new DataInputStream(new FileInputStream(rutaFichero))){
            
            while(true){
                texto+=String.valueOf(dis.readInt())+"\n";
            }
            
        }catch(EOFException e ){
            
        }catch(IOException e ){
            
        }
        return texto;
    }
    
    public int numElementos(String rutaFichero){
        
        int contador=0;
        try(DataInputStream dis=new DataInputStream(new FileInputStream(rutaFichero))){
            
            while(true){
                dis.readInt();
                contador++;
            }
            
        }catch(EOFException e ){
            
        }catch(IOException e ){
            
        }
        return contador;
    }
    
    public void mezclarFicheros(String fichero1, String fichero2, String ficheroFinal){
        
        numeros=new int[numElementos(fichero1)+numElementos(fichero2)];
        
        int indice=0;
        try(DataInputStream dis=new DataInputStream(new FileInputStream(fichero1))){
           
            while(true){
                numeros[indice]=dis.readInt();
                indice++;
            }
            
        }catch(EOFException e ){
            
            try(DataInputStream dis2=new DataInputStream(new FileInputStream(fichero2))){
                
                while(true){
                    numeros[indice]=dis2.readInt();
                    indice++;
                }
                
            }catch(EOFException f ){
                
            }catch(IOException f ){
            
            }   
            
        }catch(IOException e ){
            
        }
        
        
        Arrays.sort(numeros);
        
        for(int i=0;i<numeros.length;i++){
            anadirNumFichero(ficheroFinal, numeros[i]);
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
        campoFichero1 = new javax.swing.JTextField();
        campoFichero2 = new javax.swing.JTextField();
        botonFichero1 = new javax.swing.JButton();
        botonFichero2 = new javax.swing.JButton();
        botonFichero3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaTextoFichero2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTextoFichero3 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaTextoFichero1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero 1");

        jLabel2.setText("Numero 2");

        botonFichero1.setText("Añadir 1º Fichero");
        botonFichero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFichero1ActionPerformed(evt);
            }
        });

        botonFichero2.setText("Añadir 2º Fichero");
        botonFichero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFichero2ActionPerformed(evt);
            }
        });

        botonFichero3.setText("Mezclar ficheros");
        botonFichero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFichero3ActionPerformed(evt);
            }
        });

        areaTextoFichero2.setColumns(20);
        areaTextoFichero2.setRows(5);
        jScrollPane1.setViewportView(areaTextoFichero2);

        areaTextoFichero3.setColumns(20);
        areaTextoFichero3.setRows(5);
        jScrollPane2.setViewportView(areaTextoFichero3);

        areaTextoFichero1.setColumns(20);
        areaTextoFichero1.setRows(5);
        jScrollPane3.setViewportView(areaTextoFichero1);

        jLabel3.setText("Fichero 1");

        jLabel4.setText("Fichero 2");

        jLabel5.setText("Fichero final");

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoFichero1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(campoFichero2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonFichero1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonFichero2)
                                .addGap(38, 38, 38)
                                .addComponent(botonFichero3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel3)
                .addGap(92, 92, 92)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(325, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoFichero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonFichero1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFichero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonFichero2)
                    .addComponent(botonFichero3))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonSalir)
                .addGap(17, 17, 17))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(148, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonFichero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFichero1ActionPerformed
        anadirNumFichero(RUTA_FICHERO1, Integer.parseInt(campoFichero1.getText()));
        areaTextoFichero1.setText(mostrarFichero(RUTA_FICHERO1));
    }//GEN-LAST:event_botonFichero1ActionPerformed

    private void botonFichero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFichero2ActionPerformed
        anadirNumFichero(RUTA_FICHERO2, Integer.parseInt(campoFichero2.getText()));
        areaTextoFichero2.setText(mostrarFichero(RUTA_FICHERO2));
    }//GEN-LAST:event_botonFichero2ActionPerformed

    private void botonFichero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFichero3ActionPerformed
        mezclarFicheros(RUTA_FICHERO1, RUTA_FICHERO2, RUTA_FICHERO3);
        areaTextoFichero3.setText(mostrarFichero(RUTA_FICHERO3));
    }//GEN-LAST:event_botonFichero3ActionPerformed

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
            java.util.logging.Logger.getLogger(Act4_MezclaFicheros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Act4_MezclaFicheros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Act4_MezclaFicheros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Act4_MezclaFicheros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Act4_MezclaFicheros().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaTextoFichero1;
    private javax.swing.JTextArea areaTextoFichero2;
    private javax.swing.JTextArea areaTextoFichero3;
    private javax.swing.JButton botonFichero1;
    private javax.swing.JButton botonFichero2;
    private javax.swing.JButton botonFichero3;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField campoFichero1;
    private javax.swing.JTextField campoFichero2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    private final String RUTA_FICHERO1="fichero1.dat";
    private final String RUTA_FICHERO2="fichero2.dat";
    private final String RUTA_FICHERO3="fichero3.dat";
    private int numeros[];
}
