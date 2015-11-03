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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class InformacionParte extends javax.swing.JDialog {

    /**
     * Creates new form InformacionParte
     */
    public InformacionParte() {
        
        this.setModal(true);
        
        initComponents();
        
        conexion=frmPrincipal.devConexion();
    
        codigoOperacion=frmPrincipal.INSERT;
        
        conexion.rellenaComboBox2Columnas(cmbMatricula, frmPartes.CONSULTA_MATRICULA, "-Elige una matricula-", "idvehiculo", "matricula");
        
        //Relleno el comboBox de comunidades
        conexion.rellenaComboBox2Columnas(cmbComunidad, 
                                        frmPartes.CONSULTA_COMUNIDADES, 
                                        "", 
                                        "idcomunidad", 
                                        "nombre_com");
        
        fechaParte.setDate(new Date());
        
        //Le asigno la primera posicion
        cmbComunidad.setSelectedIndex(0);
        
        rellenarComboBoxResponsable();
        
        MetodosComunes.evitarPegar(txtDireccion);
    }
    
    public InformacionParte(int idParte){
        
        this.setModal(true);
        
        initComponents();
        
        conexion=frmPrincipal.devConexion();
        
        codigoOperacion=frmPrincipal.UPDATE;
        
        rellenarComboBoxResponsable();
        
        
        conexion.rellenaComboBox2Columnas(cmbMatricula, frmPartes.CONSULTA_MATRICULA, "-Elige una matricula-", "idvehiculo", "matricula");
        
        //Relleno el comboBox de comunidades
        conexion.rellenaComboBox2Columnas(cmbComunidad, 
                                        frmPartes.CONSULTA_COMUNIDADES, 
                                        "", 
                                        "idcomunidad", 
                                        "nombre_com");
        
        //Le asigno la primera posicion
        cmbComunidad.setSelectedIndex(0);
        
        idParteActual=idParte;
        
        rellenaDatos(idParte);
        
        MetodosComunes.evitarPegar(txtDireccion);
        
    }
    
    private void rellenaDatos(int idParte){
        
        conexion.ejecutarConsulta("Select nro_parte, descripcion, fecha_parte, responsable, direccion, idvehiculo, idcod_pobl "
                                    + "from s_partes "
                                    + "where idparte="+idParte);
        
        
        ResultSet aux=conexion.getResultSet();
        
        try {        
            
            aux.next();
            
            MetodosComunes.asignarItemCmb2Columnas(cmbMatricula, aux.getInt("idvehiculo"));
            
            txtAreaDescripcion.setText(aux.getString("descripcion"));
            
            fechaParte.setDate(MetodosComunes.deNumeroAFecha(aux.getInt("fecha_parte")));
            
            if(aux.getString("responsable").equals("S")){
                cmbResponsable.setSelectedIndex(0);
            }else{
                cmbResponsable.setSelectedIndex(1);
            }
            
            txtDireccion.setText(aux.getString("direccion"));
            
            rellenarCodigoPostal(aux.getInt("idcod_pobl"));
            
        } catch (SQLException ex) {
            Logger.getLogger(InformacionParte.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        
    }
    
    private char devolverResponsable(){
        
        String[] newSelection = (String[])cmbResponsable.getSelectedItem();
        
        if(Boolean.parseBoolean(newSelection[0])){
           return 'S'; 
        }else{
           return 'N'; 
        }
        
    }
    
    private void rellenarComboBoxResponsable(){
        
        String datos[]=new String[2];
        
        datos[0]=Boolean.toString(true);
        datos[1]="Responsable";
        cmbResponsable.addItem(new String[] {datos[0],datos[1],});
        
        datos[0]=Boolean.toString(false);
        datos[1]="No Responsable";
        cmbResponsable.addItem(new String[] {datos[0],datos[1],});
        
        cmbResponsable.setRenderer (new DefaultListCellRenderer() 
            {
                @Override
                public java.awt.Component getListCellRendererComponent (
                JList l,Object o,int i,boolean s, boolean f)
                {
                    return new JLabel (((String[])o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });
        
        
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
                                            frmPartes.CONSULTA_COMUNIDADES, 
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
    
//    public int devolverNroParte(int idVehiculo){
//        
//        
//        return ;
//        
//        
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbMatricula = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        fechaParte = new com.toedter.calendar.JDateChooser();
        cmbResponsable = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        btnGrabar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        cmbComunidad = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmbCP = new javax.swing.JComboBox();
        cmbPoblacion = new javax.swing.JComboBox();
        cmbProvincia = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblControlLongitudDescripcion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblControlLongitudDireccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información parte");
        setResizable(false);

        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setLineWrap(true);
        txtAreaDescripcion.setRows(5);
        txtAreaDescripcion.setWrapStyleWord(true);
        txtAreaDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaDescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtAreaDescripcion);

        jLabel9.setText("Poblacion");

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        jLabel10.setText("Provincia");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

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

        jLabel7.setText("Codigo Postal");

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

        jLabel11.setText("Comunidad");

        jLabel1.setText("Matricula");

        jLabel2.setText("Descripción");

        jLabel3.setText("Fecha");

        jLabel4.setText("Responsable");

        jLabel5.setText("Dirección");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(10, 10, 10))
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2))
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPoblacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbComunidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbProvincia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(146, 146, 146))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblControlLongitudDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaParte, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                        .addGap(69, 69, 69))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblControlLongitudDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaParte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblControlLongitudDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed

        String error="";

        if (MetodosComunes.devolverCodigoComboBox(cmbMatricula)==-1){
            error+="- Debes elegir una matricula\n";

        }

        if(fechaParte==null){
            error+="- La fecha no puede ser nula\n";
        }

        if(txtDireccion.getText().isEmpty()){
            error+="- La direccion no puede ser vacia\n";
        }

        if (cmbCP.getSelectedIndex()==0){
            error +="- Debes eligir un CODIGO POSTAL\n";
        }

        //Si no hay errores, la operacion se puede completar
        if(error.equals("")){

            //Sacamos algunos datos necesarios

            int codigoPoblacion = MetodosComunes.devolverCodigoComboBox(cmbCP);

            int codigoVehiculo= MetodosComunes.devolverCodigoComboBox(cmbMatricula);
            
            char responsable=devolverResponsable();

            //Depende del codigo de operacion, haremos una operacion u otra
            if (codigoOperacion==frmPrincipal.INSERT){

                idParteActual=conexion.proximoIDDisponible("idparte", "s_partes");

                int nro_parte=conexion.cuentaRegistrosConsulta("nro_parte", "s_partes", " idvehiculo="+codigoVehiculo)+1;
                
                conexion.ejecutarInstruccion("insert into s_partes values("
                    + idParteActual + ", "
                    + nro_parte+", "
                    + "'"+txtAreaDescripcion.getText()+"', "
                    + MetodosComunes.deFechaANumero(fechaParte.getDate())+", "
                    + "'"+responsable+ "', "
                    + "'"+txtDireccion.getText()+"', "
                    + codigoPoblacion+ ", "
                    + codigoVehiculo+ ", "
                    + "0)");
                
                JOptionPane.showMessageDialog(this, "El parte ha sido añadido correctamente", "Parte añadido", JOptionPane.INFORMATION_MESSAGE);
    
            }else{

                conexion.ejecutarInstruccion("update s_partes "
                    + "set descripcion='" + txtAreaDescripcion.getText() + "', "
                    + "fecha_parte="+MetodosComunes.deFechaANumero(fechaParte.getDate()) + ", "
                    + "responsable='"+responsable+ "', "
                    + "direccion='"+txtDireccion.getText()+ "', "
                    + "idcod_pobl="+codigoPoblacion+ ", "
                    + "idvehiculo="+codigoVehiculo
                    + " where idParte="+idParteActual);

                
                
                JOptionPane.showMessageDialog(this, "El parte ha sido actualizado correctamente", "Parte actualizado", JOptionPane.INFORMATION_MESSAGE);
                
            }
            
            conexion.commit();

            this.setVisible(false);
            
        }else{
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

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

    private void cmbComunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbComunidadActionPerformed

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

    private void cmbPoblacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPoblacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPoblacionActionPerformed

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

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProvinciaActionPerformed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        MetodosComunes.noEscribirMasDe(txtDireccion, 100, evt, lblControlLongitudDireccion);
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtAreaDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaDescripcionKeyTyped
       MetodosComunes.noEscribirMasDe(txtAreaDescripcion, 200, evt, lblControlLongitudDescripcion);
    }//GEN-LAST:event_txtAreaDescripcionKeyTyped

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JComboBox cmbCP;
    private javax.swing.JComboBox cmbComunidad;
    private javax.swing.JComboBox cmbMatricula;
    private javax.swing.JComboBox cmbPoblacion;
    private javax.swing.JComboBox cmbProvincia;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JDateChooser fechaParte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblControlLongitudDescripcion;
    private javax.swing.JLabel lblControlLongitudDireccion;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtDireccion;
    // End of variables declaration//GEN-END:variables
    private ConexionDB conexion;
    private int codigoOperacion;
    private int idParteActual;
    
}
