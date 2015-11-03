/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Isabel
 */
public class PC02_JI_GestionPacientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form PCXX_JI_GestionCarnet
     */
     private PC02_JI_GestionPacientes() {
        
         String[] columnas={"codigo","Nombre","DNI", "Poblacion", "Fecha nacimiento", "Sexo"};
         
         modelo=new DefaultTableModel(null, columnas){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
         
        initComponents();
        
        conexion=MiHibernateSingleton.getInstance();
        
        radioNoEliminado.setSelected(true);
        ButtonGroup grupo=new ButtonGroup();
        
        grupo.add(radioNoEliminado);
        grupo.add(radioEliminado);
        
        
        rellenarPacientes(CONSULTA_PACIENTES+devolverEstado()+ORDEN);
     
     }
     
     
      public static PC02_JI_GestionPacientes getInstancia() {

        if (instancia == null || instancia.isClosed) {
            instancia = new PC02_JI_GestionPacientes();
        }
        return instancia;
    }
      
      
     public void rellenarPacientes(String consulta){
         
         conexion.rellenarTablaObject(consulta, 6, modelo);
         
         MetodosComunes.ocultarColumnaJTable(JTablaPacientes, 0);
         
         cambiarFechaYsexo();
         
     }
     
     public String devolverEstado(){
         
         if(radioNoEliminado.isSelected()){
             return NOELIMINADO;
         }
         
         return ELIMINADO;
             
         
     }
     
     
     public String devolverFiltro(){
         
         String condicion="";
        
        if(!txtNombreP.getText().isEmpty()){
            condicion+=" and upper(nombrep) like '%"+txtNombreP.getText().toUpperCase()+"%'";
        }
        
        if(jDateFechaNac.getDate()!=null){
             int fecha= MetodosComunes.deFechaANumero(jDateFechaNac.getDate());
             condicion+=" and fechanac = "+fecha+" ";
        }
        
        if(!txtPoblacion.getText().isEmpty()){
             condicion+=" and upper(poblacion) like '%"+txtPoblacion.getText().toUpperCase()+"%'";
        }
        
        return condicion;
         
     }
     
     public void cambiarFechaYsexo(){
        
        Integer fecha;
        Date fechaActual;
        String valorActual;
        char sexoActual;
        
        for(int i=0;i<modelo.getRowCount();i++){
            
            fecha=(Integer)modelo.getValueAt(i, 4);
            if(fecha!=null){
                
                fechaActual=MetodosComunes.deNumeroAFecha(fecha);
            
                valorActual=fechaActual.getDate()+"/"+(fechaActual.getMonth()+1)+"/"+(fechaActual.getYear()+1900);

                modelo.setValueAt(valorActual, i, 4);
            }
            
            sexoActual=(char)modelo.getValueAt(i, 5);
            
            if(sexoActual=='H'){
                valorActual="Hombre";
            }else{
                valorActual="Mujer";
            }
            
            modelo.setValueAt(valorActual, i, 5);
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JTablaPacientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnTodos = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        radioEliminado = new javax.swing.JRadioButton();
        radioNoEliminado = new javax.swing.JRadioButton();
        txtNombreP = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDateFechaNac = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        lblControlNombre = new javax.swing.JLabel();
        lblControlPoblacion = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Gestión de Pacientes");

        JTablaPacientes.setModel(modelo);
        JTablaPacientes.setName("Articulos"); // NOI18N
        jScrollPane1.setViewportView(JTablaPacientes);

        jLabel4.setText("Nombre");

        btnTodos.setText("Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        radioEliminado.setText("Eliminados");
        radioEliminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEliminadoActionPerformed(evt);
            }
        });

        radioNoEliminado.setText("No Eliminados");
        radioNoEliminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNoEliminadoActionPerformed(evt);
            }
        });

        txtNombreP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombrePKeyTyped(evt);
            }
        });

        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addition.gif"))); // NOI18N
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deletion.gif"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Salir22x22.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Nacimiento");

        jLabel1.setText("Población");

        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPoblacionKeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblControlPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioEliminado)
                                        .addGap(12, 12, 12)
                                        .addComponent(radioNoEliminado)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnTodos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFiltrar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(59, 59, 59)
                                .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar)
                            .addComponent(btnAñadir)
                            .addComponent(btnSalir)
                            .addComponent(lblControlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAñadir, btnEliminar, btnModificar, btnSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblControlNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblControlPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTodos)
                    .addComponent(btnFiltrar)
                    .addComponent(radioEliminado)
                    .addComponent(radioNoEliminado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(41, 41, 41)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAñadir, btnEliminar, btnModificar, btnSalir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        
        txtNombreP.setText("");
        jDateFechaNac.setDate(null);
        txtPoblacion.setText("");
        
        rellenarPacientes(CONSULTA_PACIENTES+NOELIMINADO+ORDEN);
        radioNoEliminado.setSelected(true);
        
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
       
        rellenarPacientes(CONSULTA_PACIENTES+devolverEstado()+devolverFiltro()+ORDEN);
        
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        
        PC02_JD_Pacientes ventana=new PC02_JD_Pacientes();
        
        ventana.setVisible(true);
        
        rellenarPacientes(CONSULTA_PACIENTES+devolverEstado()+devolverFiltro()+ORDEN);
        
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       
         if(modelo.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que modificar");
            
        }else{
            
            int fila=JTablaPacientes.getSelectedRow();
            
            if(fila == -1){
                       
                JOptionPane.showMessageDialog(this, "No has selccionado ninguna fila");
         
            }else{
         
                int idpaciente=(int)JTablaPacientes.getValueAt(fila, 0);
                
                PC02_JD_Pacientes ventana=new PC02_JD_Pacientes(idpaciente);
        
                ventana.setVisible(true);
                
                rellenarPacientes(CONSULTA_PACIENTES+devolverEstado()+devolverFiltro()+ORDEN);
        
            }
         }
         
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        if(modelo.getRowCount()==0){
            
            JOptionPane.showMessageDialog(this, "No hay registros que eliminar");
            
        }else{
            
            int fila=JTablaPacientes.getSelectedRow();
            
            if(fila == -1){
                       
                JOptionPane.showMessageDialog(this, "No has selccionado ninguna fila");
         
            }else{
         
                int eleccion=JOptionPane.showConfirmDialog(this, "Confirmacion", "¿Estas seguro de que quieres eliminar el paciente?", JOptionPane.YES_NO_OPTION);
                
                if(eleccion==JOptionPane.YES_OPTION){
                 
                    int idpaciente=(int)JTablaPacientes.getValueAt(fila, 0);
                    
                    conexion.abrirSesionYTransaccion();
                    
                    conexion.getSesion().createQuery("update Hpaciente set eliminado=true where idpaciente="+idpaciente).executeUpdate();
                    
                    conexion.cerrarSesionYTransaccion();
                    
                }
                
                
                
                rellenarPacientes(CONSULTA_PACIENTES+devolverEstado()+devolverFiltro()+ORDEN);
        
            }
         }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void radioEliminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEliminadoActionPerformed
        
        rellenarPacientes(CONSULTA_PACIENTES+ELIMINADO+devolverFiltro()+ORDEN);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
    }//GEN-LAST:event_radioEliminadoActionPerformed

    private void radioNoEliminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNoEliminadoActionPerformed
        rellenarPacientes(CONSULTA_PACIENTES+NOELIMINADO+devolverFiltro()+ORDEN);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        
    }//GEN-LAST:event_radioNoEliminadoActionPerformed

    private void txtNombrePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePKeyTyped
        
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlNombre);
        
    }//GEN-LAST:event_txtNombrePKeyTyped

    private void txtPoblacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoblacionKeyTyped
        MetodosComunes.escribirSoloLetrasYEspacios(evt, lblControlPoblacion);
    }//GEN-LAST:event_txtPoblacionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTablaPacientes;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTodos;
    private com.toedter.calendar.JDateChooser jDateFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblControlNombre;
    private javax.swing.JLabel lblControlPoblacion;
    private javax.swing.JRadioButton radioEliminado;
    private javax.swing.JRadioButton radioNoEliminado;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
    private static PC02_JI_GestionPacientes instancia;
    private MiHibernateSingleton conexion;
    private DefaultTableModel modelo;
    
    private final String CONSULTA_PACIENTES="select idpaciente, nombrep, dni, poblacion, fechanac, sexo from Hpaciente where ";
    private final String ORDEN = " order by nombrep";
    
    private final String ELIMINADO=" eliminado = true ";
    private final String NOELIMINADO=" eliminado = false ";
    
}
