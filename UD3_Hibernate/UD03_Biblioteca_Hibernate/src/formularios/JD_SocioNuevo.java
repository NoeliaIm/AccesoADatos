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
import modelo.Bsocios;
import modelo.PcodPobl;
import modelo.Pcomunidad;
import modelo.Pprovincias;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Juan
 */
public class JD_SocioNuevo extends javax.swing.JDialog {

    private String idSocio;
    private boolean esNuevo = false;
    private MetodosComunes funciones = new MetodosComunes();
   
    /**
     * Creates new form JD_SocioNuevo
     */
    public JD_SocioNuevo(java.awt.Frame parent, boolean modal, String idSocio) {
       
        super(parent, modal);
        this.idSocio = idSocio;
        if (this.idSocio.equals("0")) {
            esNuevo = true;
        }
        initComponents();
        llenarComunidadesHibernate();
        rellenaCamposHibernate();
    }
    public void rellenaCamposHibernate(){
        if(!esNuevo){
           String sql = "FROM Bsocios WHERE idsocio="+idSocio;
            Session sesion = JF_Menu.instanciaHibernate.openSession();
            Query consulta = sesion.createQuery(sql);
            Iterator iter = consulta.iterate();
            Bsocios soc = (Bsocios)iter.next();
            txtDni.setText(soc.getDni());
            txtApellidos.setText(soc.getApellidos());
            txtNombre.setText(soc.getNombre());
            txtDireccion.setText(soc.getDireccion());
            cboComunidad.getModel().setSelectedItem(soc.getPcodPobl().getPprovincias().getPcomunidad());
            cboProvincia.getModel().setSelectedItem(soc.getPcodPobl().getPprovincias());
            cboPoblacion.getModel().setSelectedItem(soc.getPcodPobl().getPpoblaciones());
            cboCodigoPostal.getModel().setSelectedItem(soc.getPcodPobl().getPcodigopostales().getCodPostal());
            funciones.IntegerToDate(soc.getFechanac(), jDateFecha);
            txtFijo.setText(Integer.toString(soc.getTelfijo()));
            txtMovil.setText(Integer.toString(soc.getTelmovil()));
            txtEmail.setText(soc.getEmail());
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cboComunidad = new javax.swing.JComboBox();
        cboProvincia = new javax.swing.JComboBox();
        cboPoblacion = new javax.swing.JComboBox();
        cboCodigoPostal = new javax.swing.JComboBox();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        txtFijo = new javax.swing.JTextField();
        txtMovil = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtError = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblDNI = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVO SOCIO");

        jLabel1.setText("Dni");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Nombre");

        jLabel4.setText("Dirección");

        jLabel5.setText("Comunidad");

        jLabel6.setText("Provincia");

        jLabel7.setText("Poblacion");

        jLabel8.setText("Codigo Postal");

        jLabel9.setText("Fecha Nacimiento");

        jLabel10.setText("Telefono Fijo");

        jLabel11.setText("Telefono Móvil");

        jLabel12.setText("Email");

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

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

        jDateFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jDateFechaKeyTyped(evt);
            }
        });

        txtFijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFijoKeyTyped(evt);
            }
        });

        txtMovil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMovilKeyTyped(evt);
            }
        });

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });

        jLabel13.setText("MENSAJES:");

        txtError.setEditable(false);
        txtError.setForeground(new java.awt.Color(255, 0, 0));

        btnNuevo.setText("Añadir");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/EXIT_ (23).jpg"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDNI)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addGap(113, 113, 113)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cboComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 228, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9))
                    .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo)
                        .addComponent(btnLimpiar)))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cboComunidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboComunidadItemStateChanged
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
               
            if(evt.getStateChange()==ItemEvent.SELECTED){
                llenarCodigoPostalHibernate(cboPoblacion.getSelectedItem().toString());
            }
            if(cboPoblacion.getSelectedIndex()==0){
                cboCodigoPostal.setEnabled(false);
            }else{
                cboCodigoPostal.setEnabled(true);
            }
    }//GEN-LAST:event_cboPoblacionItemStateChanged

   
    private void nuevoHibernate(){
         if (!isEmpty()) {
             String poblacion =(cboPoblacion.getSelectedItem().toString());
             String provincia = (cboProvincia.getSelectedItem().toString());
             String cod_pobl = (cboCodigoPostal.getSelectedItem().toString());
             PcodPobl pcp = devolverIdCodPoblHibernate(poblacion,provincia,cod_pobl); 
            
                if (!esNuevo) {
                    Session sesion = JF_Menu.instanciaHibernate.openSession();
                    Transaction transaccion = sesion.beginTransaction();
                    Bsocios soc= (Bsocios) sesion.load(Bsocios.class, Integer.parseInt(idSocio));
                    soc.setDni(txtDni.getText());
                    soc.setApellidos(txtApellidos.getText());
                    soc.setNombre(txtNombre.getText());
                    soc.setDireccion(txtDireccion.getText());
                    soc.setPcodPobl(pcp);
                    int fecha = funciones.DateToInteger(jDateFecha);
                    soc.setFechanac(fecha);
                    soc.setTelfijo(Integer.parseInt(txtFijo.getText()));
                    soc.setTelmovil(Integer.parseInt(txtMovil.getText()));
                    soc.setEmail(txtEmail.getText());
                    sesion.update(soc);
                    transaccion.commit();
                    sesion.close();
                    JOptionPane.showMessageDialog(null, "Registro Actualizado");
                    this.dispose();

                } else {

                        SessionFactory instancia = HibernateUtil.getSessionFactory();
                        Session sesion = instancia.openSession();
                        Transaction tx = sesion.beginTransaction();
                        System.out.println("Inserto una fila en la tabla BEDITORIALES");
                        Bsocios soc = new Bsocios();
                        Integer idSocio = maximo_hibernate("Bsocios", "idsocio") + 1;
                        soc.setIdsocio(idSocio);
                        
                        soc.setDni(txtDni.getText());
                        soc.setApellidos(txtApellidos.getText());
                        soc.setNombre(txtNombre.getText());
                        soc.setDireccion(txtDireccion.getText());
                        soc.setPcodPobl(pcp);
                        int fecha = funciones.DateToInteger(jDateFecha);
                        soc.setFechanac(fecha);
                        soc.setTelfijo(Integer.parseInt(txtFijo.getText()));
                        soc.setTelmovil(Integer.parseInt(txtMovil.getText()));
                        soc.setEmail(txtEmail.getText());
                        soc.setEliminado(false);                    
                        sesion.save(soc);
                        tx.commit();
                        sesion.close();
                        JOptionPane.showMessageDialog(null, "Registro creado");
                        this.dispose();
                    
                }
           
        } else {
            comprobarVacios();
        }
    }
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

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        if(funciones.validarDNI(txtDni.getText())){
            lblDNI.setForeground(Color.green);
            lblDNI.setText("DNI correcto");
        }else{
             lblDNI.setForeground(Color.red);
             lblDNI.setText("Introduzca un DNI valido");     
        }
        funciones.limitarCaracteres(evt, "DNI", 9, txtError, txtDni);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtApellidos.requestFocus();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        // TODO add your handling code here:
        funciones.limitarCaracteres(evt, "APELLIDOS", 80, txtError, txtApellidos);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        funciones.limitarCaracteres(evt, "NOMBRE", 40, txtError, txtNombre);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        funciones.limitarCaracteres(evt, "DIRECCION", 80, txtError, txtDireccion);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jDateFecha.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtFijoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFijoKeyTyped
        // TODO add your handling code here:
        funciones.escribirSoloEnterosLimitado(evt, "TELEFONO FIJO", 9, txtError, txtFijo);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtMovil.requestFocus();
        }
    }//GEN-LAST:event_txtFijoKeyTyped

    private void txtMovilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMovilKeyTyped
        // TODO add your handling code here:
        funciones.escribirSoloEnterosLimitado(evt,"TELEFONO MOVIL",9,txtError,txtMovil);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        }
    }//GEN-LAST:event_txtMovilKeyTyped

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        // TODO add your handling code here:
         if(funciones.validarEmail(txtEmail.getText())){
            lblEmail.setForeground(Color.green);
            lblEmail.setText("Email correcto");
        }else{
             lblEmail.setForeground(Color.red);
             lblEmail.setText("Introduzca un email valido");     
        }
        funciones.limitarCaracteres(evt, "EMAIL", 100, txtError, txtEmail);
    }//GEN-LAST:event_txtEmailKeyTyped

    private void jDateFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateFechaKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtFijo.requestFocus();
        }
    }//GEN-LAST:event_jDateFechaKeyTyped

 private Integer maximo_hibernate(String tabla,String id){
        String sql = "SELECT MAX(" +id+ ") FROM "+ tabla;
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Integer max = (Integer) sesion.createQuery(sql).uniqueResult();
        
        sesion.close();
        if(max==null){
            return 0;
        }else{
            return max;
        }
       
    }
    
    private boolean comprobarVacios(){
        boolean estanVacios = false;
        String texto = "ERROR:\n";
        if(txtDni.getText().isEmpty()){
            texto = texto +"Rellene el campo DNI\n";
            estanVacios = true;
        }
        if(funciones.validarDNI(txtDni.getText())){
            texto = texto+"Introduzca un DNI válido\n";
            estanVacios = true;
        }
        if(txtNombre.getText().isEmpty()){
            texto =texto+ "Rellene el campo Nombre\n";  
            estanVacios = true;
        }
        if(cboComunidad.getSelectedIndex()==0){
            texto = texto+ "Seleccione Comunidad\n";
            estanVacios = true;
        }
        if(cboPoblacion.getSelectedIndex()==0){
            texto = texto+ "Seleccione Poblacion\n";
            estanVacios = true;
        }
        if(cboProvincia.getSelectedIndex()==0){
            texto = texto+ "Seleccione Provincia\n";
            estanVacios = true;
        }
        if(cboCodigoPostal.getSelectedIndex()==0){
            texto = texto+" Seleccione Codigo Postal\n";
            estanVacios = true;
        }
        if(funciones.validarEmail(txtEmail.getText())){
            texto = texto+ "Introduzca un email válido\n";
            estanVacios = true;
        }
        if(jDateFecha.getDate()==null){
            texto = texto +"Introduzca una fecha válida\n";
            estanVacios = true;
        }
        if(estanVacios){
            JOptionPane.showMessageDialog(null, texto);
        }
        return estanVacios;
        
    }
    
    private boolean isEmpty(){
       if(txtNombre.getText().isEmpty()
               || txtDni.getText().isEmpty()
               || cboComunidad.getSelectedIndex()==0
               || cboPoblacion.getSelectedIndex()==0
               || cboProvincia.getSelectedIndex()==0
               || cboCodigoPostal.getSelectedIndex()==0
               || jDateFecha.getDate()==null){
           return true;  
       }else{
           return false;
       }
   }
    
    
//    private String ponerStringNulo(JTextField txt, boolean verdad){
//       String valor="";
//           if(txt.getText().isEmpty()){
//                valor = null;
//           } else{
//               valor = txt.getText();
//           }
//        return valor;
//    }
//    private String ponerStringNulo(JTextField txt){
//       String valor="'";
//           if(txt.getText().isEmpty()){
//                valor = null;
//           } else{
//               valor =valor+ txt.getText()+"'";
//           }
//        return valor;
//    }
  
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboCodigoPostal;
    private javax.swing.JComboBox cboComunidad;
    private javax.swing.JComboBox cboPoblacion;
    private javax.swing.JComboBox cboProvincia;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtError;
    private javax.swing.JTextField txtFijo;
    private javax.swing.JTextField txtMovil;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
