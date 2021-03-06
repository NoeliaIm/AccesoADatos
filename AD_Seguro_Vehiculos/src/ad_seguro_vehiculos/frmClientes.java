/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class frmClientes extends javax.swing.JInternalFrame {

    
    public static frmClientes getInstancia() {

        if (instancia == null || instancia.isClosed) {
            instancia = new frmClientes();
        }
        return instancia;
    }
    
    private frmClientes() {
        
        modelo=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        this.setTitle("Informacion cliente");
        
        conexion=frmPrincipal.devConexion();
        
        initComponents();
        
        MetodosComunes.bordeConTitulo(jPanel1, "Filtro Clientes");
        
        MetodosComunes.bordeConTitulo(jPanel2, "Infomacion Clientes");
        
        ButtonGroup grupo=new ButtonGroup();
        
        grupo.add(rdbEliminado);
        grupo.add(rdbNoEliminado);
        
        rdbNoEliminado.setSelected(true);
        
        rellenarTabla(CONSULTA+NOELIMINADO);
        
        //Evito que se pegue texto en estos campos
        MetodosComunes.evitarPegar(txtFiltroApellidos);
        MetodosComunes.evitarPegar(txtFiltroDireccion);
        
    }
    
    public void rellenarTabla(String consulta){
        
        MetodosComunes.limpiarTabla(modelo);
        
        conexion.ejecutarConsulta(consulta);
        
        conexion.rellenaJTableBD(modelo);
       
        //Oculto el id de cliente, centro los datos y la cabcera
        MetodosComunes.centraCabecera(jTable1);
        MetodosComunes.centrarDatos(jTable1);
        MetodosComunes.ocultarColumnaJTable(jTable1, 0);
       
        //Ajusto el tamaño de los campos para aprovechar el espacio
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(1);
 
        //Modifico el codigo postal de todas los clientes
        modificaCP();
        
        //Cambio de fecha cientifica a fecha normal
        modificarFecha();
        
       
        
        conexion.cerrarResult();
        conexion.cerrarSentencia();
        
    }
    
    //Cambio de fecha cientifica a real en todas las filas
    public void modificarFecha(){
        
        for(int i=0;i<modelo.getRowCount();i++){
            
            BigDecimal fecha=(BigDecimal)modelo.getValueAt(i, 4);
            
            Date fechaActual=MetodosComunes.deNumeroAFecha(fecha.intValue());
            
            String valorActual=fechaActual.getDate()+"/"+(fechaActual.getMonth()+1)+"/"+(fechaActual.getYear()+1900);
            
            modelo.setValueAt(valorActual, i, 4);
            
        }
            
    }
    
    //Cambio el codigo postal
    public void modificaCP(){
        
        for(int i=0;i<modelo.getRowCount();i++){
            
            BigDecimal CPActual=(BigDecimal)modelo.getValueAt(i, 9);
            
            String CP=conexion.devolverValorString("p.poblacion", "s_cod_pobl cpp, s_poblaciones p", "p.idpoblacion=cpp.idpoblacion and cpp.idcod_postal="+CPActual+"");
            
            modelo.setValueAt(CP, i, 9);
            
        }
        
        
    }

    //Indico si hay que mostrar eliminados o no eliminados
    private String devolverEstado(){
        
        if (rdbEliminado.isSelected()){
            return ELIMINADO;
        }else{
            return NOELIMINADO;
        }
        
    }
    
    //Devuelvo los datos del filtro
    private String devolverFiltro(){
        
        String condicion="";
        
        if(!txtFiltroDireccion.getText().isEmpty()){
            condicion+=" and trim(Upper(direccion)) like '%"+txtFiltroDireccion.getText().toUpperCase().trim()+"%'";
        }
        
        if(!txtFiltroApellidos.getText().isEmpty()){
            condicion+=" and trim(Upper(apellidos)) like '%"+txtFiltroApellidos.getText().toUpperCase().trim()+"%'";
        }
        
        return condicion;
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFiltroDireccion = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFiltroApellidos = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rdbNoEliminado = new javax.swing.JRadioButton();
        rdbEliminado = new javax.swing.JRadioButton();
        informe = new javax.swing.JButton();
        botonGrabar3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        txtFiltroDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroDireccionKeyTyped(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Apellidos");

        jLabel2.setText("Direccion");

        txtFiltroApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroApellidosKeyTyped(evt);
            }
        });

        btnLimpiar.setText("Limpiar filtro");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFiltroDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33))
                            .addComponent(txtFiltroApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFiltrar)
                        .addGap(23, 23, 23)
                        .addComponent(btnLimpiar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltroDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

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

        jTable1.setModel(modelo);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        rdbNoEliminado.setText("No eliminado");
        rdbNoEliminado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbNoEliminadoItemStateChanged(evt);
            }
        });
        rdbNoEliminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoEliminadoActionPerformed(evt);
            }
        });

        rdbEliminado.setText("Eliminado");
        rdbEliminado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbEliminadoItemStateChanged(evt);
            }
        });

        informe.setText("Informe");
        informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbEliminado)
                    .addComponent(rdbNoEliminado))
                .addGap(43, 43, 43)
                .addComponent(informe, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnModificar)
                            .addComponent(btnEliminar))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(rdbNoEliminado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdbEliminado)
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(informe)
                                .addGap(18, 18, 18)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        botonGrabar3.setText("Salir");
        botonGrabar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGrabar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(botonGrabar3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonGrabar3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
       
        if (jTable1.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que modificar");
        
        }else{
        
            int fila=jTable1.getSelectedRow();

            if(fila!=-1){

                BigDecimal idCliente=(BigDecimal)jTable1.getValueAt(fila, 0);

                InformacionCliente informacion=new InformacionCliente(idCliente.intValue());

                informacion.setVisible(true);   
                
                rellenarTabla(CONSULTA+devolverEstado()+devolverFiltro());
                
            }else{
                JOptionPane.showMessageDialog(this, "No has selecccionado ninguna fila");
            }
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        InformacionCliente informacion=new InformacionCliente();
        
        informacion.setVisible(true);
        
        rellenarTabla(CONSULTA+devolverEstado()+devolverFiltro());
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        if (jTable1.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que eliminar");
        
        }else{
        
            int fila=jTable1.getSelectedRow();

            if(fila!=-1){
 
                BigDecimal idCliente=(BigDecimal)jTable1.getValueAt(fila, 0);
                
                String sql="select count(*) "
                        + "from s_vehiculos "
                        + "where eliminado=0 and idcliente="+idCliente.intValue();
                
                //Compruebo si hay clientes activos en vehiculos
                if(!conexion.consultaVaciaV2(sql)){
                    
                    JOptionPane.showMessageDialog(this, 
                                                "No se puede eliminar a este cliente, existen vehiculos del cliente.", 
                                                "Error", 
                                                JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    
                     int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de que quieres eliminar el registro?", 
                                                            "Confirmacion", 
                                                            JOptionPane.YES_NO_OPTION);

                    if(seleccion==JOptionPane.YES_OPTION){


                        //Actualizo eliminado
                        conexion.ejecutarInstruccion("update s_clientes "
                                                    + "set eliminado=1"
                                                    + "where idCliente="+idCliente);

                        conexion.commit();

                        JOptionPane.showMessageDialog(this, 
                                                    "Cliente eliminado", 
                                                    "Exito", 
                                                    JOptionPane.INFORMATION_MESSAGE);


                    }

                    rellenarTabla(CONSULTA+devolverEstado()+devolverFiltro());
                    
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "No has selecccionado ninguna fila");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void botonGrabar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGrabar3ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_botonGrabar3ActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        
        rellenarTabla(CONSULTA+devolverEstado()+devolverFiltro());
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtFiltroApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroApellidosKeyTyped
        
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblApellidos);
        
    }//GEN-LAST:event_txtFiltroApellidosKeyTyped

    private void txtFiltroDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroDireccionKeyTyped
       
    }//GEN-LAST:event_txtFiltroDireccionKeyTyped

    private void rdbEliminadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbEliminadoItemStateChanged
       
        if (ItemEvent.SELECTED==evt.getStateChange()){
            
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            
            rellenarTabla(CONSULTA+ELIMINADO+devolverFiltro());
        
       }else{
            
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            
            rellenarTabla(CONSULTA+NOELIMINADO+devolverFiltro());
            
        }
       
    }//GEN-LAST:event_rdbEliminadoItemStateChanged

    private void rdbNoEliminadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbNoEliminadoItemStateChanged
       
        
    }//GEN-LAST:event_rdbNoEliminadoItemStateChanged

    private void rdbNoEliminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNoEliminadoActionPerformed
    
    }//GEN-LAST:event_rdbNoEliminadoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       
        txtFiltroDireccion.setText("");
        
        txtFiltroApellidos.setText("");
        
        rellenarTabla(CONSULTA+devolverEstado());
        
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informeActionPerformed
        
        
         if (jTable1.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que mostrar");
        
        }else{
        
            int fila=jTable1.getSelectedRow();

            if(fila!=-1){
                
                fila=jTable1.getSelectedRow();
                
                BigDecimal idCliente=(BigDecimal)jTable1.getValueAt(fila, 0);

                InformeConectores informe=new InformeConectores("ListadoClientes", conexion);

                Object parametros[][]=new Object[1][2];

                parametros[0][0]="idcliente";
                parametros[0][1]=idCliente.intValue();

                informe.crearInforme(parametros);
                
            }else{
                
                JOptionPane.showMessageDialog(this, "No has seleccionado ninguna fila");
                
            }
        
        }  
        
        
        
    }//GEN-LAST:event_informeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGrabar3;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton informe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JRadioButton rdbEliminado;
    private javax.swing.JRadioButton rdbNoEliminado;
    private javax.swing.JTextField txtFiltroApellidos;
    private javax.swing.JTextField txtFiltroDireccion;
    // End of variables declaration//GEN-END:variables
    private static frmClientes instancia;
    private DefaultTableModel modelo;
    
    private ConexionDB conexion;
    
    //Constantes
    private final String CONSULTA="select idcliente, dni, nombre, apellidos, fecha_nacim, sexo, telefono, direccion, correo, idcod_pobl as Poblacion "
                                + "from s_clientes ";
    private final String ELIMINADO="where eliminado=1";
    private final String NOELIMINADO="where eliminado=0";
    
}
