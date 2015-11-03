/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import conexion.HibernateUtil;
import funciones.MetodosComunes;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import modelo.Beditoriales;
import modelo.PcodPobl;
import modelo.Pcomunidad;
import modelo.Pprovincias;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ALUMNOM
 */
public class JD_EditorialNueva extends javax.swing.JDialog {

    /**
     * Creates new form JD_EditorialNueva
     */
    private String idEditorial;
    private boolean esNuevo = false;
    private MetodosComunes funciones = new MetodosComunes();

    public JD_EditorialNueva(java.awt.Frame parent, boolean modal, String idEditorial) {
        super(parent, modal);
        this.idEditorial = idEditorial;
        if (this.idEditorial.equals("0")) {
            esNuevo = true;
        }
        initComponents();
        llenarComunidadesHibernate();
        rellenarCamposHibernate();
    }

    private void rellenarCamposHibernate() {
        if (!esNuevo) {
            String sql = "FROM Beditoriales WHERE ideditorial = " + idEditorial;
            Session sesion = JF_Menu.instanciaHibernate.openSession();
            Query consulta = sesion.createQuery(sql);
            Iterator iter = consulta.iterate();
            Beditoriales edit = (Beditoriales) iter.next();
            txtNombre.setText(edit.getNombre());
            txtDireccion.setText(edit.getDireccion());
            cboComunidad.getModel().setSelectedItem(edit.getPcodPobl().getPprovincias().getPcomunidad());
            cboProvincia.getModel().setSelectedItem(edit.getPcodPobl().getPprovincias());
            cboPoblacion.getModel().setSelectedItem(edit.getPcodPobl().getPpoblaciones());
            cboCodigoPostal.getModel().setSelectedItem(edit.getPcodPobl().getPcodigopostales().getCodPostal());
            txtTelefono1.setText(Integer.toString(edit.getTelef1()));
            txtTelefono2.setText(Integer.toString(edit.getTelef2()));
            txtEmail.setText(edit.getEmail());
            sesion.close();
        }
    }


    private void llenarComunidadesHibernate() {
        cboComunidad.removeAllItems(); //Borra todos los Items
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery("FROM Pcomunidad p ORDER BY p.nombreCom");
        Iterator iter = consulta.iterate();
        Pcomunidad com;
        while (iter.hasNext()) {
            com = (Pcomunidad) iter.next();
            cboComunidad.addItem(com); //Los añade al Combo con dos columnas
        }
    }

    private void llenarProvinciaHibernate(String nombreCom) {
        cboProvincia.removeAllItems(); //Borra todos los Items
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery("FROM Pprovincias p WHERE p.pcomunidad.nombreCom='" + nombreCom + "' ORDER BY p.provincia");
        Iterator iter = consulta.iterate();
        Pprovincias pro;
        cboProvincia.addItem("-- Seleccione Provincia --");
        while (iter.hasNext()) {
            pro = (Pprovincias) iter.next();
            cboProvincia.addItem(pro); //Los añade al Combo con dos columnas
        }
    }

    private void llenarPoblacionHibernate(String nombreProvincia) {

        cboPoblacion.removeAllItems(); //Borra todos los Items
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery(" SELECT DISTINCT pcp.ppoblaciones.poblacion FROM PcodPobl pcp WHERE pcp.pprovincias.provincia='" + nombreProvincia + "' ORDER BY pcp.ppoblaciones.poblacion");
        Iterator iter = consulta.iterate();
        String poblacion;
        cboPoblacion.addItem("-- Seleccione Población --");
        while (iter.hasNext()) {
            poblacion = (String) iter.next();
            cboPoblacion.addItem(poblacion); //Los añade al Combo con dos columnas
        }

    
    }
    private void llenarCodigoPostalHibernate(String poblacion){
        cboCodigoPostal.removeAllItems(); //Borra todos los Items
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery("FROM PcodPobl pcp WHERE pcp.ppoblaciones.poblacion='"+poblacion+"' ORDER BY pcp.pcodigopostales.codPostal");
        Iterator iter = consulta.iterate();
        PcodPobl pcp;
        cboCodigoPostal.addItem("-- Seleccione codigoPostal --");
        while (iter.hasNext()) {
            pcp = (PcodPobl) iter.next();
            cboCodigoPostal.addItem(pcp.getPcodigopostales()); //Los añade al Combo con dos columnas
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cboComunidad = new javax.swing.JComboBox();
        cboProvincia = new javax.swing.JComboBox();
        cboPoblacion = new javax.swing.JComboBox();
        cboCodigoPostal = new javax.swing.JComboBox();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtMensajes = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVA EDITORIAL");

        jLabel1.setText("Nombre");

        jLabel2.setText("Dirección");

        jLabel3.setText("Comunidad");

        jLabel4.setText("Provincia");

        jLabel5.setText("Poblacion");

        jLabel6.setText("Código Postal");

        jLabel7.setText("Telefono 1");

        jLabel8.setText("Telefono 2");

        jLabel9.setText("Email");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        cboComunidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboComunidadItemStateChanged(evt);
            }
        });
        cboComunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboComunidadActionPerformed(evt);
            }
        });

        cboProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciaItemStateChanged(evt);
            }
        });

        cboPoblacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPoblacionItemStateChanged(evt);
            }
        });

        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        txtTelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono2KeyTyped(evt);
            }
        });

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        btnNuevo.setText("Añadir");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/EXIT_ (23).jpg"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel10.setText("MENSAJES: ");

        txtMensajes.setEditable(false);
        txtMensajes.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnNuevo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addGap(62, 62, 62)
                                .addComponent(btnSalir))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion)
                            .addComponent(txtTelefono2)
                            .addComponent(txtTelefono1)
                            .addComponent(txtEmail)
                            .addComponent(cboComunidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCodigoPostal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboPoblacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cboPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnLimpiar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboComunidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboComunidadItemStateChanged
       
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            llenarProvinciaHibernate(cboComunidad.getSelectedItem().toString());
        }
        if (cboComunidad.getSelectedIndex()==0) {
            cboProvincia.setEnabled(false);
        } else {
            cboProvincia.setEnabled(true);
        }


    }//GEN-LAST:event_cboComunidadItemStateChanged

    private void cboProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProvinciaItemStateChanged
       
        if(evt.getStateChange()==ItemEvent.SELECTED){
            llenarPoblacionHibernate(cboProvincia.getSelectedItem().toString());
        }
        if(cboProvincia.getSelectedIndex()==0){
            cboPoblacion.setEnabled(false);
        }else{
            cboPoblacion.setEnabled(true);
        }

    }//GEN-LAST:event_cboProvinciaItemStateChanged

    private void cboPoblacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPoblacionItemStateChanged
                  
            if(evt.getStateChange()==ItemEvent.SELECTED){
                llenarCodigoPostalHibernate(cboPoblacion.getSelectedItem().toString());
            }
            if(cboPoblacion.getSelectedIndex()==0){
                cboCodigoPostal.setEnabled(false);
            }else{
                cboCodigoPostal.setEnabled(true);
            }
    }//GEN-LAST:event_cboPoblacionItemStateChanged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
           this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboComunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboComunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboComunidadActionPerformed
//    private String ponerStringNulo(JTextField txt, boolean verdad) {
//        String valor = "";
//        if (txt.getText().isEmpty()) {
//            valor = null;
//        } else {
//            valor = txt.getText();
//        }
//        return valor;
//    }
//
//    private String ponerStringNulo(JTextField txt) {
//        String valor = "'";
//        if (txt.getText().isEmpty()) {
//            valor = null;
//        } else {
//            valor = valor + txt.getText() + "'";
//        }
//        return valor;
//    }

   
    public PcodPobl devolverIdCodPoblHibernate(String poblacion,String Provincia,String cod_postal){
        
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        String sql = "FROM PcodPobl pcp WHERE pcp.ppoblaciones.poblacion='"+poblacion+"'"+
                    " AND pcp.pprovincias.provincia='"+Provincia+"'"+
                    " AND pcp.pcodigopostales.codPostal='"+cod_postal+"'";
        Query consulta = sesion.createQuery(sql);
        Iterator iter = consulta.iterate();
        PcodPobl pcp= null;
        while (iter.hasNext()) {
           pcp = (PcodPobl)iter.next();
           
        }
        sesion.close();
        return pcp;
    }
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoHibernate();

    }//GEN-LAST:event_btnNuevoActionPerformed
    public void nuevoHibernate(){

        if (!isEmpty()) {
             String poblacion =(cboPoblacion.getSelectedItem().toString());
             String provincia = (cboProvincia.getSelectedItem().toString());
             String cod_pobl = (cboCodigoPostal.getSelectedItem().toString());
             PcodPobl pcp = devolverIdCodPoblHibernate(poblacion,provincia,cod_pobl); 
            
                if (!esNuevo) {
                    Session sesion = JF_Menu.instanciaHibernate.openSession();
                    Transaction transaccion = sesion.beginTransaction();
                    Beditoriales edit= (Beditoriales) sesion.load(Beditoriales.class, Integer.parseInt(idEditorial));
                    edit.setNombre(txtNombre.getText());
                    edit.setDireccion(txtDireccion.getText());
                    edit.setPcodPobl(pcp);
                    edit.setTelef1(Integer.parseInt(txtTelefono1.getText()));
                    edit.setTelef2(Integer.parseInt(txtTelefono2.getText()));
                    edit.setEmail(txtEmail.getText());
                    sesion.update(edit);
                    transaccion.commit();
                    sesion.close();
                    JOptionPane.showMessageDialog(null, "Registro Actualizado");
                    this.dispose();

                } else {

                    if (!existe_editorial_hibernate(txtNombre.getText(), "e.nombre")) {
                        SessionFactory instancia = HibernateUtil.getSessionFactory();
                        Session sesion = instancia.openSession();
                        Transaction tx = sesion.beginTransaction();
                        System.out.println("Inserto una fila en la tabla BEDITORIALES");
                        Beditoriales edit = new Beditoriales();
                        Integer idEditorial = maximo_hibernate("Beditoriales", "ideditorial") + 1;
                        edit.setIdeditorial(idEditorial);
                        edit.setNombre(txtNombre.getText());
                        edit.setDireccion(txtDireccion.getText());
                        edit.setPcodPobl(pcp);
                        edit.setTelef1(Integer.parseInt(txtTelefono1.getText()));
                        edit.setTelef2(Integer.parseInt(txtTelefono2.getText()));
                        edit.setEmail(txtEmail.getText());
                        edit.setEliminado(false);
                        sesion.save(edit);
                        tx.commit();
                        sesion.close();
                        JOptionPane.showMessageDialog(null, "Registro creado");
                        this.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe la editorial");
                    }
                    
                }
           
        } else {
            comprobarVacios();
        }

    
   

    }
    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
        if (funciones.validarEmail(txtEmail.getText())) {
            lblEmail.setForeground(Color.green);
            lblEmail.setText("Email correcto");
        } else {
            lblEmail.setForeground(Color.red);
            lblEmail.setText("Introduzca un email valido");
        }
        funciones.limitarCaracteres(evt, "EMAIL", 100, txtMensajes, txtEmail);
    }//GEN-LAST:event_txtEmailKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        funciones.limitarCaracteres(evt, "NOMBRE", 60, txtMensajes, txtNombre);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
        
            if (existe_editorial_hibernate(txtNombre.getText(), "e.nombre")) {
                txtMensajes.setText("NOMBRE: Ya existe esa EDITORIAL");
            }
       
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        funciones.limitarCaracteres(evt, "DIRECCION", 80, txtMensajes, txtDireccion);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTelefono1.requestFocus();
        }

    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        // TODO add your handling code here:
        funciones.escribirSoloEnterosLimitado(evt, "TELEFONO1", 9, txtMensajes, txtTelefono1);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtTelefono2.requestFocus();
        }
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtTelefono2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono2KeyTyped
        // TODO add your handling code here:
        funciones.escribirSoloEnterosLimitado(evt, "TELEFONO2", 9, txtMensajes, txtTelefono2);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtTelefono2KeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void limpiar() {
        txtNombre.setText("");
        txtDireccion.setText("");
        cboComunidad.setSelectedIndex(0);
        cboPoblacion.setSelectedIndex(0);
        cboProvincia.setSelectedIndex(0);
        cboCodigoPostal.setSelectedIndex(0);
        txtTelefono1.setText("");
        txtTelefono2.setText("");
        txtEmail.setText("");
    }

    private boolean comprobarVacios() {
        boolean estanVacios = false;
        String texto = "ERROR:\n";
        if (txtNombre.getText().isEmpty()) {
            texto = texto + "Rellene el campo Nombre\n";
            estanVacios = true;
        }
        if (cboComunidad.getSelectedIndex() == 0) {
            texto = texto + "Seleccione Comunidad\n";
            estanVacios = true;
        }
        if (cboPoblacion.getSelectedIndex() == 0) {
            texto = texto + "Seleccione Poblacion\n";
            estanVacios = true;
        }
        if (cboProvincia.getSelectedIndex() == 0) {
            texto = texto + "Seleccione Provincia\n";
            estanVacios = true;
        }
        if (cboCodigoPostal.getSelectedIndex() == 0) {
            texto = texto + " Seleccione Codigo Postal\n";
            estanVacios = true;
        }
        if (!funciones.validarEmail(txtEmail.getText())) {
            texto = texto + "Introduzca un email válido";
            estanVacios = true;
        }
        if (estanVacios) {
            JOptionPane.showMessageDialog(null, texto);
        }
        return estanVacios;

    }

   
    
     private Integer maximo_hibernate(String tabla, String id) {
        String sql = "SELECT MAX(" + id + ") FROM " + tabla;
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Integer max = (Integer) sesion.createQuery(sql).uniqueResult();
        sesion.close();
        if(max==null){
            return 0;
        }else{
            return max;
        }

    }

    private boolean isEmpty() {
        if (txtNombre.getText().isEmpty()
                || cboComunidad.getSelectedIndex() == 0
                || cboPoblacion.getSelectedIndex() == 0
                || cboProvincia.getSelectedIndex() == 0
                || cboCodigoPostal.getSelectedIndex() == 0) {
            return true;
        } else {
            return false;
        }
    }
     private boolean existe_editorial_hibernate(String campo,String columna) {
        String sql = "SELECT COUNT(*) FROM Beditoriales e WHERE "+columna+"  ='" + campo+"'";
        int c = 0;
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery(sql);
        Iterator iter = consulta.iterate();
        Long l = (Long) iter.next();
        if (l==0) {
            return false;
        } else {
            return true;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboCodigoPostal;
    private javax.swing.JComboBox cboComunidad;
    private javax.swing.JComboBox cboPoblacion;
    private javax.swing.JComboBox cboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMensajes;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
