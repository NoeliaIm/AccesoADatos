/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class frmVehiculos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Vehiculos
     */
    public static frmVehiculos getInstancia() {

        if (instancia == null || instancia.isClosed) {
            instancia = new frmVehiculos();
        }
        return instancia;
    }
    
    private frmVehiculos() {
       
        modelo=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        //Devuelvo la conexion del principal
        conexion=frmPrincipal.devConexion();
        
        this.setTitle("Vehiculos");
        
        initComponents();
        
        MetodosComunes.bordeConTitulo(jPanel2, "Filtro Vehiculos");
        
        MetodosComunes.bordeConTitulo(jPanel1, "Infomacion Vehiculos");
        
        ButtonGroup grupo=new ButtonGroup();
        
        grupo.add(rdbEliminado);
        grupo.add(rdbNoEliminado);
        
        rdbNoEliminado.setSelected(true);
        
        rellenarTabla(CONSULTA+NOELIMINADO+devolverTipoPago()+devolverFiltro());
        
        //Evita pegar texto en los campos
        MetodosComunes.evitarPegar(txtFiltroMarca);
        MetodosComunes.evitarPegar(txtFiltroPotencia);
        MetodosComunes.evitarPegar(txtFiltroModelo);
    }

    //Rellena la tabla indicandole una consulta
    public void rellenarTabla(String consulta){
        
        //limpio la tabla antes de hacer nada mas
        MetodosComunes.limpiarTabla(modelo);
        
        conexion.ejecutarConsulta(consulta);
        
        conexion.rellenaJTableBD(modelo);
       
        //centrar
        MetodosComunes.centraCabecera(jTable1);
        MetodosComunes.centrarDatos(jTable1);
        
        //Ocultamos el id de la tabla(lo necesitamos para las acciones)
        MetodosComunes.ocultarColumnaJTable(jTable1, 0);
       
        //Cambio el tipo de pago y seguro para que sean mas legibles
        cambiarTipoSeguro();
        cambiarTipoPago();
        
        conexion.cerrarResult();
        conexion.cerrarSentencia();
        
    }
    
    //Cambia el tipo de pago segun la letra
    public void cambiarTipoPago(){
        
        for(int i=0;i<modelo.getRowCount();i++){
            
            String tipo=(String)modelo.getValueAt(i, 3);
            
            //Segun el tipo, cambio de letra
            switch(tipo){
                case "S":
                    modelo.setValueAt("Semestral",i, 3);
                    break;
                case "A":
                    modelo.setValueAt("Anual",i, 3);
                    break;
            }
            
        }
        
    }

    //Modifica la columna tipo seguro, eliminando el espacio
    private void cambiarTipoSeguro(){
        
        for(int i=0;i<modelo.getRowCount();i++){
            
            //elimino el espacio que se queda, al consultar en la BD
            modelo.setValueAt(modelo.getValueAt(i, 5).toString().trim(),i, 5);
            
            
        }
        
    }
    
     //Usado para completar la consultas
    private String devolverEstado(){

        if (rdbEliminado.isSelected()){
            return ELIMINADO;
        }else{
            return NOELIMINADO;
        }

    }
    
    //Usado para completar la consultas
    private String devolverTipoPago(){
        
        String estado="";
        
        switch(cmbTipoPago.getSelectedIndex()){
            case 1:
                estado= " and v.tipopago='S'";
                break;
            case 2:
                estado= " and v.tipopago='A'";;
                break;
                
        }
        
        return estado;
        
    }
    
    //Devuelve los datos del filtro
    private String devolverFiltro(){
        
        String condicion="";
        
        JTextField textos[]={txtFiltroPotencia, txtFiltroModelo, txtFiltroMarca};
        String condicionesSQL[]={
                                    " and v.potencia="+txtFiltroPotencia.getText()+"", 
                                    " and trim(upper(m.modelo)) like '%"+txtFiltroModelo.getText().toUpperCase().trim()+"%'", 
                                    " and trim(upper(ma.marca)) like '%"+txtFiltroMarca.getText().toUpperCase().trim()+"%'"
                                };
        
        for(int i=0;i<textos.length;i++){
            if(!textos[i].getText().equals("")){
                condicion+=condicionesSQL[i];
            }
        }
        
        if(cmbTipoPago.getSelectedIndex()!=0){
            condicion+=" and tipopago='"+cmbTipoPago.getSelectedItem().toString().charAt(0)+"'";
        }
        
        return condicion;
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        rdbEliminado = new javax.swing.JRadioButton();
        rdbNoEliminado = new javax.swing.JRadioButton();
        cmbTipoPago = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFiltroPotencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFiltroModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFiltroMarca = new javax.swing.JTextField();
        lblFiltroPotencia = new javax.swing.JLabel();
        lblFiltroModelo = new javax.swing.JLabel();
        lblFiltroMarca = new javax.swing.JLabel();
        btnFiltrar1 = new javax.swing.JButton();
        btnFiltrar2 = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jTable1.setModel(modelo);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        rdbEliminado.setText("Eliminado");
        rdbEliminado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbEliminadoItemStateChanged(evt);
            }
        });

        rdbNoEliminado.setText("No eliminado");

        cmbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Elige tipo de pago--", "Semestral", "Anual" }));
        cmbTipoPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPagoItemStateChanged(evt);
            }
        });
        cmbTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoPagoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de pago");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnNuevo)
                        .addGap(64, 64, 64)
                        .addComponent(btnModificar)
                        .addGap(72, 72, 72)
                        .addComponent(btnEliminar)
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbEliminado)
                            .addComponent(rdbNoEliminado))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(cmbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel2)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(rdbNoEliminado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbEliminado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnNuevo)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnModificar))))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel1.setText("Potencia");

        txtFiltroPotencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroPotenciaKeyTyped(evt);
            }
        });

        jLabel3.setText("Modelo");

        txtFiltroModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroModeloActionPerformed(evt);
            }
        });
        txtFiltroModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroModeloKeyTyped(evt);
            }
        });

        jLabel4.setText("Marca");

        txtFiltroMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroMarcaKeyTyped(evt);
            }
        });

        btnFiltrar1.setText("Limpiar filtro");
        btnFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar1ActionPerformed(evt);
            }
        });

        btnFiltrar2.setText("Filtrar");
        btnFiltrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblFiltroPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(txtFiltroPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltroModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltroModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(74, 74, 74)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblFiltroMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(txtFiltroMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(209, 209, 209))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFiltroModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addComponent(lblFiltroPotencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblFiltroMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnSalir.setText("Salir");
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
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
        if (jTable1.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que eliminar");
        
        }else{
        
            int fila=jTable1.getSelectedRow();

            if(fila!=-1){

                
                BigDecimal idVehiculo=(BigDecimal)jTable1.getValueAt(fila, 0);
              
                    int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de que quieres eliminar el vehiculo?"
                                                            + " Si lo haces tambien borraras los partes asociados", 
                                                            "Confirmacion", 
                                                            JOptionPane.YES_NO_OPTION);
            
                    if(seleccion==JOptionPane.YES_OPTION){

                        

                        //actuaizamos y hacemos commit
                        conexion.ejecutarInstruccion("update s_vehiculos "
                                                        + "set eliminado=1"
                                                        + "where idvehiculo="+idVehiculo);

                        
                        conexion.ejecutarInstruccion("update s_partes "
                                                        + "set eliminado=1"
                                                        + "where idvehiculo="+idVehiculo);
                        
                        conexion.commit();

                        JOptionPane.showMessageDialog(this, 
                                                    "Vehiculo eliminado", 
                                                    "Exito", 
                                                    JOptionPane.INFORMATION_MESSAGE);

                        //rellenamos la tabla de nuevo (Con el nuevo cambio)
                        rellenarTabla(CONSULTA+devolverTipoPago()+devolverEstado()+devolverFiltro());

                    }
                
                
                
                
            }else{
                JOptionPane.showMessageDialog(this, "No has selecccionado ninguna fila");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtFiltroPotenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroPotenciaKeyTyped
        
        MetodosComunes.escribirSoloNumeros(evt, lblFiltroPotencia);
        
    }//GEN-LAST:event_txtFiltroPotenciaKeyTyped

    private void txtFiltroModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroModeloKeyTyped
        
        MetodosComunes.escribirAlfanumerico(evt, lblFiltroModelo);
        
    }//GEN-LAST:event_txtFiltroModeloKeyTyped

    private void txtFiltroMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroMarcaKeyTyped
        
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblFiltroMarca);
        
    }//GEN-LAST:event_txtFiltroMarcaKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       
        InformacionVehiculos ventana=new InformacionVehiculos();
        
        ventana.setVisible(true);
        
        rellenarTabla(CONSULTA+devolverTipoPago()+devolverEstado()+devolverFiltro());
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        if (jTable1.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que modificar");
        
        }else{
        
            int fila=jTable1.getSelectedRow();

            if(fila!=-1){
                
                BigDecimal idVehiculo=(BigDecimal)jTable1.getValueAt(fila, 0);    
                
                //Le devuelvo el id del vehiculo (en la primera columna)
                InformacionVehiculos ventana=new InformacionVehiculos(idVehiculo.intValue());
        
                ventana.setVisible(true);
                
                rellenarTabla(CONSULTA+devolverTipoPago()+devolverEstado()+devolverFiltro());
        
            }else{
                JOptionPane.showMessageDialog(this, "No has selecccionado ninguna fila");
            }
        }
        
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar1ActionPerformed
        
        txtFiltroPotencia.setText("");
        txtFiltroModelo.setText("");
        txtFiltroMarca.setText("");
        
        rellenarTabla(CONSULTA+devolverTipoPago()+NOELIMINADO);
        
    }//GEN-LAST:event_btnFiltrar1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rdbEliminadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbEliminadoItemStateChanged
        
        if (ItemEvent.SELECTED==evt.getStateChange()){
            
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            
            rellenarTabla(CONSULTA+devolverTipoPago()+ELIMINADO+devolverFiltro());
            
        }else{
            
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            
            rellenarTabla(CONSULTA+devolverTipoPago()+NOELIMINADO+devolverFiltro());
            
        }
        
    }//GEN-LAST:event_rdbEliminadoItemStateChanged

    private void txtFiltroModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroModeloActionPerformed

    private void cmbTipoPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPagoItemStateChanged
       
        if(ItemEvent.SELECTED==evt.getStateChange()){
            
            //Cambia la tabla, segun el elemento elegido
            rellenarTabla(CONSULTA+devolverTipoPago()+devolverEstado()+devolverFiltro());
            
        }
        
    }//GEN-LAST:event_cmbTipoPagoItemStateChanged

    private void btnFiltrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar2ActionPerformed
       
        rellenarTabla(CONSULTA+devolverEstado()+devolverFiltro());
        
    }//GEN-LAST:event_btnFiltrar2ActionPerformed

    private void cmbTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPagoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar1;
    private javax.swing.JButton btnFiltrar2;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblFiltroMarca;
    private javax.swing.JLabel lblFiltroModelo;
    private javax.swing.JLabel lblFiltroPotencia;
    private javax.swing.JRadioButton rdbEliminado;
    private javax.swing.JRadioButton rdbNoEliminado;
    private javax.swing.JTextField txtFiltroMarca;
    private javax.swing.JTextField txtFiltroModelo;
    private javax.swing.JTextField txtFiltroPotencia;
    // End of variables declaration//GEN-END:variables
    private static frmVehiculos instancia;
    private DefaultTableModel modelo;
    private ConexionDB conexion;
    
    //Contantes
    private final String CONSULTA="select v.idvehiculo, v.matricula, v.potencia, v.tipopago, m.modelo, s.descripcion as tipo_seguro "
                                + "from s_vehiculos v, s_modelos m, s_tipo_seguro s, s_marcas ma "
                                + "where v.tipo_seguro=s.tipo_seguro and v.idmodelo=m.idmodelo and ma.idmarca=m.idmarca ";
    
    private final String ELIMINADO="and v.eliminado=1";
    private final String NOELIMINADO="and v.eliminado=0";
}
