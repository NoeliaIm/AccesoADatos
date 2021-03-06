/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.util.Date;
import javax.swing.JOptionPane;
import modelos.Hpaciente;

/**
 *
 * @author Isabel
 */
public class PC02_JD_Pacientes extends javax.swing.JDialog {

    /**
     * Creates new form PCXX_GestionVendedores
     */
    
    

    public PC02_JD_Pacientes() {
       
        this.setModal(true);
        
        initComponents();
        
        conexion=MiHibernateSingleton.getInstance();
        codigoOperacion=INSERT;
        idPacienteActual=-1;
        
        dniInicial="";
    }
    
    public PC02_JD_Pacientes(int id) {
       
        this.setModal(true);
        
        initComponents();
        
        conexion=MiHibernateSingleton.getInstance();
        codigoOperacion=UPDATE;
        idPacienteActual=id;
        rellenarDatos();
    }
    
    public void rellenarDatos(){
        
        conexion.abrirSesion();
        
        Hpaciente paciente=(Hpaciente)conexion.getSesion().load(Hpaciente.class, idPacienteActual);
        
        txtDNIP.setText(paciente.getDni());
        txtNombreP.setText(paciente.getNombrep());
        txtNroSegSoc.setText(paciente.getNrosegsoc());
        txtDireccion.setText(paciente.getDireccion());
        txtPoblacion.setText(paciente.getPoblacion());
        
        txtProvincia.setText(paciente.getProvincia());
        
        if(paciente.getFechanac()!=null){
            jDateFechaNac.setDate(MetodosComunes.deNumeroAFecha(paciente.getFechanac()));
        }
        
        if(paciente.getSexo()=='H'){
            comboSexo.setSelectedIndex(1);
        }else{
            comboSexo.setSelectedIndex(2);
        }
        
        dniInicial=paciente.getDni();
        
        conexion.cerrarSesion();
        
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
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtDNIP = new javax.swing.JTextField();
        txtNombreP = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jDateFechaNac = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNroSegSoc = new javax.swing.JTextField();
        txtProvincia = new javax.swing.JTextField();
        lblcontrolLongitudProvincia = new javax.swing.JLabel();
        lblControlNombre = new javax.swing.JLabel();
        lblControlLongitudPoblacion = new javax.swing.JLabel();
        lblControlLongitudNombre = new javax.swing.JLabel();
        lblControlLongitudDireccion = new javax.swing.JLabel();
        lblcontrolPoblacion = new javax.swing.JLabel();
        lblControlLongitudSS = new javax.swing.JLabel();
        lblControlLongitudDNI = new javax.swing.JLabel();
        lblcontrolProvincia = new javax.swing.JLabel();
        lblControlSS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Pacientes");

        jLabel1.setText("DNI");

        jLabel2.setText("NOMBRE");

        jLabel3.setText("DIRECCIÓN");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salir22x22.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletion.gif"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtDNIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIPKeyTyped(evt);
            }
        });

        txtNombreP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombrePKeyTyped(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addition.gif"))); // NOI18N
        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        jLabel6.setText("POBLACIÓN");

        jLabel7.setText("SEXO");

        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPoblacionKeyTyped(evt);
            }
        });

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Sexo", "Hombre", "Mujer" }));

        jLabel8.setText("FECHA NACIMIENTO");

        jLabel5.setText("Nº SEG. SOCIAL");

        jLabel10.setText("PROVINCIA");

        txtNroSegSoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroSegSocKeyTyped(evt);
            }
        });

        txtProvincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinciaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(txtProvincia)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblcontrolProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(62, 62, 62)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblControlLongitudNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblControlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtDNIP, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblControlLongitudDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(157, 157, 157))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6))
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtNroSegSoc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblControlLongitudPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblcontrolPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGrabar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCancelar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 44, 44)
                        .addComponent(btnSalir))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblControlSS, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(303, 303, 303)
                            .addComponent(lblControlLongitudSS, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblcontrolLongitudProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDNIP)
                        .addComponent(lblControlLongitudDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblControlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblControlLongitudNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNroSegSoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblControlSS, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblControlLongitudSS, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPoblacion)
                    .addComponent(lblcontrolPoblacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblControlLongitudPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(txtProvincia)
                    .addComponent(lblcontrolProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblcontrolLongitudProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
       
            String error="";

            Object[][] matriz={
                                    {txtDNIP, txtNombreP, txtNroSegSoc},
                                    {"Escribe un DNI", "Escribe un nombre", "Escribe un numero de seguridad social"}
            };

            error+=MetodosComunes.comprobarVacios(matriz);

            if(comboSexo.getSelectedIndex()==0){
                error+="- Elige un sexo\n";
            }

            if(!MetodosComunes.comprobarDNI(txtDNIP.getText().toUpperCase())){
                error+="- DNI incorrecto\n";

            }else if(conexion.ExisteValor(txtDNIP.getText().toUpperCase(), "dni", "Hpaciente") && !dniInicial.equals(txtDNIP.getText().toUpperCase())){
                error+="- El DNI ya existe\n";
            }

            
            if(jDateFechaNac.getDate()!=null && !MetodosComunes.fechaMayorqueHoy(jDateFechaNac.getDate())){
                error+="- La fecha no puede ser mayor que hoy\n";
            }      
            
            if(error.equals("")){

                String mensaje;
                if(codigoOperacion==INSERT){
                    mensaje="añadir";
                }else{
                    mensaje="modificar";
                }
                    
                int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de que quieres "+mensaje+" el registro?", 
                                                            "Confirmacion", 
                                                            JOptionPane.YES_NO_OPTION);

                if(seleccion==JOptionPane.YES_OPTION){
                 
                    conexion.abrirSesionYTransaccion();

                    Hpaciente paciente;
                    
                    String DNI=txtDNIP.getText().toUpperCase();
                    String nombre=txtNombreP.getText().toUpperCase();
                    String numSS=txtNroSegSoc.getText().toUpperCase();
                    String direccion = txtDireccion.getText().toUpperCase();
                    String poblacion = txtPoblacion.getText().toUpperCase();
                    String provincia=txtProvincia.getText().toUpperCase();
                    
                    Integer fecha;
                    if(jDateFechaNac.getDate()!=null){
                        fecha=MetodosComunes.deFechaANumero(jDateFechaNac.getDate());
                    }else{
                        fecha=null;
                    }
                    
                    
                    char sexo=comboSexo.getSelectedItem().toString().charAt(0);

                    if(codigoOperacion==INSERT){

                        paciente=new Hpaciente();

                        idPacienteActual=conexion.proximoIDDisponible("idpaciente", "Hpaciente");

                        paciente.setIdpaciente(idPacienteActual);
                        
                        paciente.setDni(DNI);
                        paciente.setFechanac(fecha);
                        paciente.setNrosegsoc(numSS);
                        paciente.setDireccion(direccion);
                        paciente.setPoblacion(poblacion);
                        paciente.setNombrep(nombre);
                        paciente.setProvincia(provincia);
                        paciente.setSexo(sexo);

                        paciente.setEliminado(false);

                        conexion.insertar(paciente);

                        mensaje="Paciente insertado";

                    }else{

                        paciente=(Hpaciente)conexion.getSesion().load(Hpaciente.class, idPacienteActual);

                        paciente.setDni(DNI);
                        paciente.setFechanac(fecha);
                        paciente.setNrosegsoc(numSS);
                        paciente.setDireccion(direccion);
                        paciente.setPoblacion(poblacion);
                        paciente.setNombrep(nombre);
                        paciente.setProvincia(provincia);
                        paciente.setSexo(sexo);

                        conexion.update(paciente);

                         mensaje="Paciente actualizado";
                    }

                    conexion.cerrarSesionYTransaccion();

                    JOptionPane.showMessageDialog(this, mensaje);

                    this.setVisible(false);
                }
                
                
            }else{
                JOptionPane.showMessageDialog(this, error);
            }

            
            
        
        
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtDNIPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIPKeyTyped
       
        MetodosComunes.noEscribirMasDe(txtDNIP, 9, evt, lblControlLongitudDNI);
      
    }//GEN-LAST:event_txtDNIPKeyTyped

    private void txtNombrePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePKeyTyped
        
        MetodosComunes.noEscribirMasDe(txtNombreP, 40, evt, lblControlLongitudNombre);
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlNombre);
        
    }//GEN-LAST:event_txtNombrePKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
       
        MetodosComunes.noEscribirMasDe(txtDireccion, 50, evt, lblControlLongitudDireccion);
        
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtPoblacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoblacionKeyTyped
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblcontrolPoblacion);
        MetodosComunes.noEscribirMasDe(txtPoblacion, 50, evt, lblControlLongitudPoblacion);
    }//GEN-LAST:event_txtPoblacionKeyTyped

    private void txtProvinciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinciaKeyTyped
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblcontrolProvincia);
        MetodosComunes.noEscribirMasDe(txtProvincia, 40, evt, lblcontrolLongitudProvincia);
    }//GEN-LAST:event_txtProvinciaKeyTyped

    private void txtNroSegSocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroSegSocKeyTyped
       
        MetodosComunes.escribirAlfanumerico(evt, lblControlSS);
        MetodosComunes.noEscribirMasDe(txtNroSegSoc, 12, evt, lblControlLongitudSS);
    }//GEN-LAST:event_txtNroSegSocKeyTyped

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
            java.util.logging.Logger.getLogger(PC02_JD_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PC02_JD_Pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PC02_JD_Pacientes dialog = new PC02_JD_Pacientes();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox comboSexo;
    private com.toedter.calendar.JDateChooser jDateFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblControlLongitudDNI;
    private javax.swing.JLabel lblControlLongitudDireccion;
    private javax.swing.JLabel lblControlLongitudNombre;
    private javax.swing.JLabel lblControlLongitudPoblacion;
    private javax.swing.JLabel lblControlLongitudSS;
    private javax.swing.JLabel lblControlNombre;
    private javax.swing.JLabel lblControlSS;
    private javax.swing.JLabel lblcontrolLongitudProvincia;
    private javax.swing.JLabel lblcontrolPoblacion;
    private javax.swing.JLabel lblcontrolProvincia;
    private javax.swing.JTextField txtDNIP;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtNroSegSoc;
    private javax.swing.JTextField txtPoblacion;
    private javax.swing.JTextField txtProvincia;
    // End of variables declaration//GEN-END:variables
    private MiHibernateSingleton conexion;
    
    private int idPacienteActual;
    private String dniInicial;
    private int codigoOperacion;
    private final int INSERT=0;
    private final int UPDATE=1;
}
