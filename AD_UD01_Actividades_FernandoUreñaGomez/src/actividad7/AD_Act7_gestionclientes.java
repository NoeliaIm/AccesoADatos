/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad7;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import milibreria.Numeros.Numeros;
import milibreria.swing.MiSwing;

/**
 *
 * @author Alumno
 */
public class AD_Act7_gestionclientes extends javax.swing.JFrame {

    /**
     * Creates new form AD_Act7_gestionclientes
     */
    public AD_Act7_gestionclientes() {
        
        MiSwing.disenoGUI();
        
        initComponents();
        
        campos=new JTextField[6];
        campos[0]=campoIDCliente;
        campos[1]=campoNombre;
        campos[2]=campoDireccion;
        campos[3]=campoPoblacion;
        campos[4]=campoDNI;
        campos[5]=campoDescuento;
        
        botonBorrar.setEnabled(false);
        botonActualizar.setEnabled(false);
    }
    
    public boolean comprobarCliente(){
        
        boolean existe=false;
        
        try(RandomAccessFile raf=new RandomAccessFile(FICHERO, "rw")){
            
            long pos=(Integer.parseInt(campoIDCliente.getText())-1)*201;
            raf.seek(pos);

            int idActual=raf.readInt();
            if(idActual==Integer.parseInt(campoIDCliente.getText())){
                existe=true;
            }
            
        }catch(IOException e){
            
        }
        
        return existe;
    }
    
    public boolean clienteBorrado(){
        
        
        try(RandomAccessFile raf=new RandomAccessFile(FICHERO, "rw")){
            
            long pos=(Integer.parseInt(campoIDCliente.getText())-1)*201;
            raf.seek(pos+200);
            
            return raf.readBoolean();
           
            
        }catch(IOException e){
            
        }
        return true;
    }
    
    public void anadirCliente(){
        
        try(RandomAccessFile raf=new RandomAccessFile(FICHERO, "rw")){
            
            //Comprobar si con un buffer es necesario o es uno por cadena
            StringBuffer buffer=null;
            
            long pos=(Integer.parseInt(campoIDCliente.getText())-1)*201;
            raf.seek(pos);
            
            System.out.println(">>posicion: "+pos);
            
            raf.writeInt(Integer.parseInt(campoIDCliente.getText()));
            
            buffer=new StringBuffer(campoNombre.getText());
            buffer.setLength(40);
            
            raf.writeChars(buffer.toString());
            
            buffer=new StringBuffer(campoDireccion.getText());
            buffer.setLength(20);
            
            raf.writeChars(buffer.toString());
            
            buffer=new StringBuffer(campoPoblacion.getText());
            buffer.setLength(25);
            
            raf.writeChars(buffer.toString());
            
            buffer=new StringBuffer(campoDNI.getText());
            buffer.setLength(9);
            
            raf.writeChars(buffer.toString());
            
            raf.writeDouble(Double.parseDouble(campoDescuento.getText()));
            
            raf.writeBoolean(false);
            
        }catch(IOException e){
            
        }
    }
    
    public void buscarCliente(){
        
        try(RandomAccessFile raf=new RandomAccessFile(FICHERO, "rw")){
            
          
            long pos=(Integer.parseInt(campoIDCliente.getText())-1)*201;
            raf.seek(pos);
              
            campoIDCliente.setText(String.valueOf(raf.readInt()));
            
            String nombre="";
            for(int i=0;i<40;i++){
                 nombre+=raf.readChar();
            }
            
            campoNombre.setText(nombre);
            
            String direccion="";
            for(int i=0;i<20;i++){
                direccion+=raf.readChar();
            }
            
            campoDireccion.setText(direccion);
            
            String poblacion="";    
            for(int i=0;i<25;i++){
                poblacion+=raf.readChar();
            }
            
            campoPoblacion.setText(poblacion);
            
            String DNI="";
            for(int i=0;i<9;i++){
                DNI+=raf.readChar();
            }
            
            campoDNI.setText(DNI);
                
            campoDescuento.setText(String.valueOf(raf.readDouble()));
            
        }catch(IOException e){
            
        }
    }
    
    public void borrarCliente(){
        
        try(RandomAccessFile raf=new RandomAccessFile(FICHERO, "rw")){
            
            long pos=(Integer.parseInt(campoIDCliente.getText())-1)*201;
            raf.seek(pos+200);
            
            raf.writeBoolean(true);
            
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        campoIDCliente = new javax.swing.JTextField();
        campoNombre = new javax.swing.JTextField();
        campoDireccion = new javax.swing.JTextField();
        campoPoblacion = new javax.swing.JTextField();
        campoDNI = new javax.swing.JTextField();
        campoDescuento = new javax.swing.JTextField();
        botonAnadir = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de clientes");

        jLabel1.setText("ID_Cliente");

        jLabel2.setText("Nombre");

        jLabel3.setText("Direccion");

        jLabel4.setText("Poblacion");

        jLabel5.setText("DNI");

        jLabel6.setText("Descuento");

        campoIDCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoIDClienteKeyTyped(evt);
            }
        });

        campoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreKeyTyped(evt);
            }
        });

        campoDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDireccionKeyTyped(evt);
            }
        });

        campoPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPoblacionKeyTyped(evt);
            }
        });

        campoDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDNIActionPerformed(evt);
            }
        });
        campoDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDNIKeyTyped(evt);
            }
        });

        campoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDescuentoKeyTyped(evt);
            }
        });

        botonAnadir.setText("Añadir");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });
        botonActualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                botonActualizarKeyTyped(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoPoblacion)
                            .addComponent(campoNombre)
                            .addComponent(campoDireccion)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAnadir)
                        .addGap(18, 18, 18)
                        .addComponent(botonBuscar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(botonActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(botonLimpiar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(campoIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(campoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnadir)
                    .addComponent(botonBuscar)
                    .addComponent(botonBorrar)
                    .addComponent(botonActualizar)
                    .addComponent(botonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(botonSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        
        if(MiSwing.comprobarVacios(campos)){
            JOptionPane.showMessageDialog(this, "Hay un campo vacio");
        }else{
            if(comprobarCliente()){
                JOptionPane.showMessageDialog(this, "El cliente existe");
            }else{
                if(Numeros.comprobarDNI(campoDNI.getText())){
                   anadirCliente();
                   JOptionPane.showMessageDialog(this, "El cliente ha sido añadido");
                }else{
                     JOptionPane.showMessageDialog(this, "El DNI no es correcto");
                }
            }
        }
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void campoIDClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIDClienteKeyTyped
        MiSwing.escribirSoloNumeros(evt);
        MiSwing.noEscribirMasDe(campoIDCliente, 5, evt);
    }//GEN-LAST:event_campoIDClienteKeyTyped

    private void campoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreKeyTyped
        MiSwing.noEscribirMasDe(campoNombre, 40, evt);
    }//GEN-LAST:event_campoNombreKeyTyped

    private void campoDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDireccionKeyTyped
        MiSwing.noEscribirMasDe(campoDireccion, 20, evt);
    }//GEN-LAST:event_campoDireccionKeyTyped

    private void campoPoblacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPoblacionKeyTyped
        MiSwing.noEscribirMasDe(campoPoblacion, 25, evt);
    }//GEN-LAST:event_campoPoblacionKeyTyped

    private void campoDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDNIKeyTyped
        MiSwing.noEscribirMasDe(campoDNI, 9, evt);
    }//GEN-LAST:event_campoDNIKeyTyped

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void campoDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDNIActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        botonBorrar.setEnabled(false);
        botonActualizar.setEnabled(false);
        
        MiSwing.limpiaCampos(campos);
        
        campoIDCliente.requestFocus();
        campoIDCliente.setEditable(true);
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void campoDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDescuentoKeyTyped
        MiSwing.escribirSoloDoubles(evt);
        MiSwing.noEscribirMasDe(campoDescuento, 5, evt);
    }//GEN-LAST:event_campoDescuentoKeyTyped

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        if(!MiSwing.comprobarVacio(campoIDCliente, "El campo ID Cliente no puede esta vacio, inserta otro")){
            if(!comprobarCliente()){
               JOptionPane.showMessageDialog(this, "El Cliente no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(clienteBorrado()){
                    JOptionPane.showMessageDialog(this, "Este ha sido borrado", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    buscarCliente();
                    botonBorrar.setEnabled(true);
                    botonActualizar.setEnabled(true);
                    campoIDCliente.setEditable(false);
                }
                
            }
        }  
        
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        int seleccion=JOptionPane.showConfirmDialog(this, "¿Quieres borrar el registro?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if(seleccion==JOptionPane.YES_OPTION){
            borrarCliente();
            JOptionPane.showMessageDialog(this, "El cliente ha sido borrado", "Eliminacion correcta", JOptionPane.INFORMATION_MESSAGE);
            botonBorrar.setEnabled(false);
            botonActualizar.setEnabled(false);
            campoIDCliente.setEditable(true);
            MiSwing.limpiaCampos(campos);
            campoIDCliente.requestFocus();
           
        }
        
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void botonActualizarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonActualizarKeyTyped
        
        
        
    }//GEN-LAST:event_botonActualizarKeyTyped

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
       int seleccion=JOptionPane.showConfirmDialog(this, "¿Quieres actualizar el registro?", "Confirmar Actualizar", JOptionPane.YES_NO_OPTION);
        if(seleccion==JOptionPane.YES_OPTION){
            if(Numeros.comprobarDNI(campoDNI.getText())){
                anadirCliente();
                JOptionPane.showMessageDialog(this, "El cliente ha sido actualizado", "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
            }else{
                 JOptionPane.showMessageDialog(this, "El DNI no es correcto");
            }
            
        }else{
            botonBorrar.setEnabled(false);
            botonActualizar.setEnabled(false);
            campoIDCliente.setEditable(true);
            MiSwing.limpiaCampos(campos);
            campoIDCliente.requestFocus();
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AD_Act7_gestionclientes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField campoDNI;
    private javax.swing.JTextField campoDescuento;
    private javax.swing.JTextField campoDireccion;
    private javax.swing.JTextField campoIDCliente;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoPoblacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
    private final File FICHERO=new File("Clientes.dat");
    private JTextField campos[];
}
