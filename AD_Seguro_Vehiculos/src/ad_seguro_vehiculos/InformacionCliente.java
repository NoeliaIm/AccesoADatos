/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class InformacionCliente extends javax.swing.JDialog {
    
    public InformacionCliente () {

        this.setTitle("Informacion cliente");
        
        this.setModal(true);
        
        initComponents();
        
        //Devuelvo la conexion de Principal
        conexion=frmPrincipal.devConexion();
        
        codigoOperacion=frmPrincipal.INSERT;
        
        //Relleno el comboBox de comunidades
        conexion.rellenaComboBox2Columnas(cmbComunidad, 
                                        CONSULTA_COMUNIDADES, 
                                        "", 
                                        "idcomunidad", 
                                        "nombre_com");
        
        //Le asigno la primera posicion
        cmbComunidad.setSelectedIndex(0);
        
        evitarPegado();
    }
    
    //MODIFICAR
    public InformacionCliente (int idCliente) {
       
        this.setTitle("Informacion cliente");
        this.setModal(true);
        initComponents();
        
        conexion=frmPrincipal.devConexion();
        
        codigoOperacion=frmPrincipal.UPDATE;
        idclienteActual=idCliente;
        rellenarTextBox(idCliente);
        
        evitarPegado();
    }

    //Rellena los textbox si pinchamos en modificar
    private void rellenarTextBox(int id){
        
        //Obtenemos los datos
        conexion.ejecutarConsulta("select * "
                                + "from s_clientes"
                                + " where idcliente="+id+"");
        
        ResultSet aux= conexion.getResultSet();
        
        //Rellenamos los datos
        try {
        
            aux.next();
            
            txtDNI.setText(aux.getString("DNI"));

            txtNombre.setText(aux.getString("NOMBRE"));

            txtApellidos.setText(aux.getString("APELLIDOS"));

            dcFecha.setDate(MetodosComunes.deNumeroAFecha(aux.getInt("FECHA_NACIM")));

            String sexo=aux.getString("SEXO");

            if(sexo.equals("H")){
                cmbSexo.setSelectedIndex(1);
            }else{
                cmbSexo.setSelectedIndex(2);
            }

            txtTelefono.setText(String.valueOf(aux.getInt("telefono")));

            txtCorreo.setText(aux.getString("CORREO"));

            txtDireccion.setText(aux.getString("DIRECCION"));
        
            rellenarCodigoPostal(aux.getInt("idcod_pobl"));
            
        } catch (SQLException ex) {
            Logger.getLogger(InformacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void evitarPegado(){
        
        //En estos campos no se puede copiar texto
        MetodosComunes.evitarPegar(txtDNI);
        MetodosComunes.evitarPegar(txtNombre);
        MetodosComunes.evitarPegar(txtApellidos);
        MetodosComunes.evitarPegar(txtTelefono);
        MetodosComunes.evitarPegar(txtCorreo);
        MetodosComunes.evitarPegar(txtDireccion);
        
        
    }
    
    
    private void rellenarCodigoPostal(int id){
        
        //relleno los codigosPostales
        String sql="select cp.idcod_postal, cp.cod_postal "
                + "from s_cod_pobl cpp, s_codigopostales cp "
                + "where cpp.idcod_postal=cp.idcod_postal and cpp.idcod_postal="+id+"";
        
        conexion.rellenaComboBox2Columnas(cmbCP, 
                                            sql, 
                                            "", 
                                            "idcod_postal", 
                                            "cod_postal");

        //relleno las poblaciones
        sql="select p.idpoblacion, p.poblacion "
                + "from s_cod_pobl cpp, s_poblaciones p "
                + "where p.idpoblacion=cpp.idpoblacion and idcod_postal="+id+"";
        
        conexion.rellenaComboBox2Columnas(cmbPoblacion, 
                                            sql, 
                                            "", 
                                            "idpoblacion", 
                                            "poblacion");
        
        
        //Obtengo la poblacion
        int codigoPob=conexion.devolverValorInt("p.idpoblacion", 
                                                "s_cod_pobl cpp, s_poblaciones p", 
                                                "p.idpoblacion=cpp.idpoblacion and idcod_postal="+id+"");

        
        //relleno las provincias
        sql="select pro.idprovincia, pro.provincia "
                + "from s_provincias pro, s_cod_pobl cpp "
                + "where pro.idprovincia=cpp.idprovincia and cpp.idpoblacion="+codigoPob+"";
        
        conexion.rellenaComboBox2Columnas(cmbProvincia, 
                                            sql, 
                                            "", 
                                            "idprovincia", 
                                            "provincia");
        
        
        //Obtengo la provincia
        int codigoPro=conexion.devolverValorInt("pro.idprovincia", 
                                                "s_provincias pro, s_cod_pobl cpp", 
                                                "pro.idprovincia=cpp.idprovincia and cpp.idpoblacion="+codigoPob+"");

        
        conexion.rellenaComboBox2Columnas(cmbComunidad, 
                                            CONSULTA_COMUNIDADES, 
                                            "", 
                                            "idcomunidad", 
                                            "nombre_com");
        
        //Obtengo la comunidad
       int codigoCom=conexion.devolverValorInt("com.idcomunidad", 
                                                "s_provincias pro, s_comunidad com", 
                                                "pro.idcomunidad=com.idcomunidad and pro.idprovincia="+codigoPro+"");

       
        //Asigno todos los combobox con los ids obtenidos
        MetodosComunes.asignarItemCmb2Columnas(cmbComunidad, codigoCom);
      
        MetodosComunes.asignarItemCmb2Columnas(cmbProvincia, codigoPro);
       
        MetodosComunes.asignarItemCmb2Columnas(cmbPoblacion,codigoPob);
       
        MetodosComunes.asignarItemCmb2Columnas(cmbCP,id);
        
    }
    
    private void confirmarRegistro(){
        
        int seleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres grabar el registro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            
        if(seleccion==JOptionPane.YES_OPTION){

            JOptionPane.showMessageDialog(this, "Cliente guardado", "Exito", JOptionPane.INFORMATION_MESSAGE);

            conexion.commit();

            this.setVisible(false);

        }else{

           conexion.roolback();

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

        txtDNI = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        dcFecha = new com.toedter.calendar.JDateChooser();
        cmbSexo = new javax.swing.JComboBox();
        btnGrabar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Telefono = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbCP = new javax.swing.JComboBox();
        cmbPoblacion = new javax.swing.JComboBox();
        cmbProvincia = new javax.swing.JComboBox();
        cmbComunidad = new javax.swing.JComboBox();
        lblControlDNI = new javax.swing.JLabel();
        lblControlLongitudDNI = new javax.swing.JLabel();
        lblControlLongitudNombre = new javax.swing.JLabel();
        lblControlNombre = new javax.swing.JLabel();
        lblControlApellidos = new javax.swing.JLabel();
        lblControlLongitudApellidos = new javax.swing.JLabel();
        lblControlTelefono = new javax.swing.JLabel();
        lblControlLongitudTelefono = new javax.swing.JLabel();
        lblControlLongitudCorreo = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblControlLongitudDireccion = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setTitle("Informacion cliente");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Elige el sexo--", "Hombre", "Mujer" }));

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("DNI");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellidos");

        jLabel4.setText("Fecha nacimiento");

        jLabel5.setText("Sexo");

        Telefono.setText("Telefono");

        jLabel6.setText("Correo");

        jLabel7.setText("Codigo Postal");

        cmbCP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbPoblacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPoblacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPoblacionItemStateChanged(evt);
            }
        });
        cmbPoblacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPoblacionActionPerformed(evt);
            }
        });

        cmbProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProvinciaItemStateChanged(evt);
            }
        });
        cmbProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
            }
        });

        cmbComunidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbComunidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbComunidadItemStateChanged(evt);
            }
        });
        cmbComunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbComunidadActionPerformed(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel8.setText("Direccion");

        jLabel9.setText("Poblacion");

        jLabel10.setText("Provincia");

        jLabel11.setText("Comunidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblControlLongitudCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(2, 2, 2)))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbComunidad, 0, 218, Short.MAX_VALUE)
                                            .addComponent(cmbProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbPoblacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(Telefono)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(161, 161, 161)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblControlLongitudTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblControlTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblControlLongitudNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                .addComponent(lblControlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblControlLongitudDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblControlDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblControlLongitudApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblControlApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblControlDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblControlLongitudDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblControlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblControlLongitudNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lblControlLongitudApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblControlApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Telefono))
                    .addComponent(lblControlTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(lblControlLongitudTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(lblControlLongitudCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        
    }//GEN-LAST:event_txtDNIActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        
        String error="";
        
        //Hago esta matriz para controlar que los campos no esten vacios
        Object matriz[][]={
                            {txtDNI, txtNombre, txtApellidos,txtTelefono, txtCorreo, txtDireccion},
                            {"El DNI no puede estar vacio",
                             "El NOMBRE no puede estar vacio",
                             "Los APELLIDOS no pueden estar vacios",
                             "El TELEFONO no puede estar vacio",
                             "El CORREO no puede estar vacio",
                             "La DIRECCION no puede estar vacia"}
                            };
        
        //Si hay errores, se añadiran a error
        error+=MetodosComunes.comprobarVacios(matriz);
        
        //Compruebo el DNI
        if (!MetodosComunes.comprobarDNI(txtDNI.getText().toUpperCase())){
            error+="- El DNI no es correcto\n";
        
        }else if(conexion.existeValor(txtDNI.getText().toUpperCase(), "s_clientes", "dni") && codigoOperacion==frmPrincipal.INSERT){
            error+="- El DNI ya existe";
            
        }
        
        if(cmbSexo.getSelectedIndex()==0){
            error+="- Indica el sexo\n";
        }
        
        if(!MetodosComunes.emailCorrecto(txtCorreo.getText())){
            error+="- El email no tiene el formato correcto\n";
        }
        
        if(dcFecha.getDate()==null){
            error+="- Debes introducir una fecha\n";
        }
        
        if(txtTelefono.getText().length()<9){
            error+="- El TELEFONO deben ser de 9 digitos\n";
        }
        
        if (cmbCP.getSelectedIndex()==0){
            error +="- Debes eligir un CODIGO POSTAL\n";
        }
        
        //Si no hay errores, la operacion se puede completar
        if(error.equals("")){
            
            //Sacamos algunos datos necesarios
            
            int codigoPoblacion = MetodosComunes.devolverCodigoComboBox(cmbCP);
            
            char sexo=cmbSexo.getSelectedItem().toString().charAt(0);
            
            //Depende del codigo de operacion, haremos una operacion u otra
            if (codigoOperacion==frmPrincipal.INSERT){
                
                int id=conexion.proximoIDDisponible("idcliente", "s_clientes");
                
                conexion.ejecutarInstruccion("insert into s_clientes values("
                                                                + id + ", "
                                                                + "'"+txtDNI.getText().toUpperCase()+"', "
                                                                + "'"+txtNombre.getText()+"', "
                                                                + "'"+txtApellidos.getText()+"', "
                                                                + MetodosComunes.deFechaANumero(dcFecha.getDate())+ ", "
                                                                + "'"+sexo+"', "
                                                                + txtTelefono.getText()+ ", "
                                                                + "'"+txtDireccion.getText()+"', "
                                                                + "'"+txtCorreo.getText()+ "', "
                                                                + codigoPoblacion + ", "
                                                                + "0"
                                                                + ")");

            }else{
                
               conexion.ejecutarInstruccion("update s_clientes "
                                                               + "set nombre='" + txtNombre.getText() + "', "
                                                               + "dni='"+txtDNI.getText().toUpperCase()+ "', "
                                                                + "apellidos='"+txtApellidos.getText() + "', "
                                                                + "fecha_nacim="+MetodosComunes.deFechaANumero(dcFecha.getDate())+ ", "
                                                                + "sexo='"+sexo+ "', "
                                                                + "telefono="+txtTelefono.getText()+ ", "
                                                                + "correo='"+txtCorreo.getText()+ "', "
                                                                + "direccion='"+txtDireccion.getText()+"', "
                                                                + "idcod_pobl="+codigoPoblacion
                                                                + " where idcliente="+idclienteActual);
                
            }
            
            confirmarRegistro();
            
        }else{
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbPoblacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPoblacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPoblacionActionPerformed

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProvinciaActionPerformed

    private void cmbComunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbComunidadActionPerformed

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        
        MetodosComunes.escribirAlfanumerico(evt, lblControlDNI);
        MetodosComunes.noEscribirMasDe(txtDNI, 9, evt, lblControlLongitudDNI);
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        MetodosComunes.escribirSoloNumeros(evt, lblControlTelefono);
        MetodosComunes.noEscribirMasDe(txtTelefono, 9, evt, lblControlLongitudTelefono);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
       
        MetodosComunes.noEscribirMasDe(txtCorreo, 100, evt, lblControlLongitudCorreo);
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlNombre);
        MetodosComunes.noEscribirMasDe(txtNombre, 25, evt, lblControlLongitudNombre);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlApellidos);
        MetodosComunes.noEscribirMasDe(txtApellidos, 50, evt, lblControlLongitudApellidos);
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        
        MetodosComunes.noEscribirMasDe(txtDireccion, 100, evt, lblControlLongitudDireccion);
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void cmbComunidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbComunidadItemStateChanged
        
        if(evt.getStateChange()==ItemEvent.SELECTED){
            
            //Devuelve el codigo de comunidad
            int codigoCom=MetodosComunes.devolverCodigoComboBox(cmbComunidad);

            //Rellenamos las provincias
            conexion.rellenaComboBox2Columnas(cmbProvincia,
                                                "select idprovincia, provincia "
                                                + "from s_provincias "
                                                + "where idcomunidad="+codigoCom+" or idprovincia=-1",
                                                "",
                                                "idprovincia",
                                                "provincia");
        }
       
    }//GEN-LAST:event_cmbComunidadItemStateChanged

    private void cmbPoblacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPoblacionItemStateChanged
        
        
        if(evt.getStateChange()==ItemEvent.SELECTED){
            
            //Devuelve el codigo de Poblacion
            int codigoPob=MetodosComunes.devolverCodigoComboBox(cmbPoblacion);
            
            //Relleno los codigos postales de esa poblacion
            String sql="select distinct cp.idcod_postal, cp.cod_postal "
                    + "from s_codigopostales cp, s_cod_pobl cpp "
                    + "where cp.idcod_postal=cpp.idcod_postal "
                    + "and "
                    + "cpp.idpoblacion="+codigoPob+" or cp.idcod_postal=-1";
            
            conexion.rellenaComboBox2Columnas(cmbCP,
                                                sql,
                                                "",
                                                "idcod_postal",
                                                "cod_postal");
        }

    }//GEN-LAST:event_cmbPoblacionItemStateChanged

    private void cmbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProvinciaItemStateChanged
        
        if(evt.getStateChange()==ItemEvent.SELECTED){
            
            //Devuelve el codigo de la provincia
            int codigoPro=MetodosComunes.devolverCodigoComboBox(cmbProvincia);

            //relleno las poblaciones de esa provincia
            String sql="select distinct p.idpoblacion, p.poblacion "
                    + "from s_poblaciones p, s_cod_pobl cpp "
                    + "where p.idpoblacion=cpp.idpoblacion "
                    + "and "
                    + "cpp.idprovincia="+codigoPro+" or p.idpoblacion=-1 "
                    + "order by p.poblacion";

            conexion.rellenaComboBox2Columnas(cmbPoblacion,
                                            sql,
                                            "",
                                            "idpoblacion",
                                            "poblacion");
        }
    }//GEN-LAST:event_cmbProvinciaItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Telefono;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JComboBox cmbCP;
    private javax.swing.JComboBox cmbComunidad;
    private javax.swing.JComboBox cmbPoblacion;
    private javax.swing.JComboBox cmbProvincia;
    private javax.swing.JComboBox cmbSexo;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblControlApellidos;
    private javax.swing.JLabel lblControlDNI;
    private javax.swing.JLabel lblControlLongitudApellidos;
    private javax.swing.JLabel lblControlLongitudCorreo;
    private javax.swing.JLabel lblControlLongitudDNI;
    private javax.swing.JLabel lblControlLongitudDireccion;
    private javax.swing.JLabel lblControlLongitudNombre;
    private javax.swing.JLabel lblControlLongitudTelefono;
    private javax.swing.JLabel lblControlNombre;
    private javax.swing.JLabel lblControlTelefono;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    private ConexionDB conexion;
    private int idclienteActual;
    private int codigoOperacion;
    
    //Constantes
    private final String CONSULTA_COMUNIDADES="select idcomunidad, nombre_com from s_comunidad";
    
}
