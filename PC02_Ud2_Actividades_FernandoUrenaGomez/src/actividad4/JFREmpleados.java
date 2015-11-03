/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad4;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import milibreria.Numeros.Numeros;
import milibreria.fechas.Fechas;
import milibreria.swing.MiSwing;
/**
 *
 * @author Fernando
 */
public class JFREmpleados extends javax.swing.JFrame {

    /**
     * Creates new form JFREmpleados
     */
    public JFREmpleados() {
        
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.out.println("Error setting Java LAF: "+e);
        }
        
        initComponents();
        
        this.setTitle("Gestión de empleados");
        
        conexion=new ConexionDB("Oracle", "LOCALHOST", "alumno", "alumno", "ORADAM2");
        
        //usado para limpiar los campos
        campos=new JTextField[4];
        campos[0]=txtApellido;
        campos[1]=txtOficio;
        campos[2]=txtComision;
        campos[3]=txtSalario;
         
        //Usado para activar o desactivar los campos
        controles=new Component[7];
        controles[0]=txtApellido;
        controles[1]=txtOficio;
        controles[2]=cldFecha;
        controles[3]=txtComision;
        controles[4]=txtSalario;
        controles[5]=cmbDir;
        controles[6]=cmbDept;
       
        //Lleno los comboBox
        conexion.rellenaComboBox2Columnas(cmbDir, "select distinct apellido, emp_no from empleados", "--Director--", "emp_no", "apellido");
        conexion.rellenaComboBox2Columnas(cmbDept, "SELECT iddepartamento,dnombre FROM departamentos ORDER BY dnombre", "--Elija Departamento--", "iddepartamento","dnombre");
        
        
        btnAnterior.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGrabar.setEnabled(false);
        
        //Lo uso para que al pulsar siguiente, aparezca el primer empleado (que es mayor que A)
        empleadoActual="A";
        idempleado=conexion.primerIDSinEliminar("eliminado", "idempleado", "empleados");
    }
  
    public void consulta(String query){
        
        
        //Compruebo antes de que no este la tabla vacia
        if(conexion.consultaVaciaV2("select count(*) from empleados where eliminado=0")){
            JOptionPane.showMessageDialog(this, "Tabla vacia");
            MiSwing.activa_DesactivaControles(controles, false);
            btnGrabar.setEnabled(false);
            btnEliminar.setEnabled(false);
            MiSwing.limpiaCampos(campos);   
            cldFecha.setDate(null);
            cmbDir.setSelectedIndex(0);
            cmbDept.setSelectedIndex(0);
            
            
        }else{
            
            //Si no esta vacia la tabla, muestro los dato correspondientes
            conexion.ejecutarConsulta(query);
        
            try {
            
                conexion.getResultSet().next();

                empleadoActual=conexion.getResultSet().getString("apellido");

               if(conexion.getResultSet().getInt("eliminado")==0){

                   idempleado=conexion.getResultSet().getInt("idempleado");

                   txtEmp_NO.setText(String.valueOf(conexion.getResultSet().getInt("EMP_NO"))); 

                   txtApellido.setText(conexion.getResultSet().getString("apellido"));

                   txtOficio.setText(conexion.getResultSet().getString("oficio"));

                   asignarItem(cmbDir,conexion.getResultSet().getInt("dir"));

                   cldFecha.setDate(Fechas.deNumeroAFecha(conexion.getResultSet().getInt("fecha_alt")));

                   txtSalario.setText(String.valueOf(conexion.getResultSet().getInt("salario")));

                   txtComision.setText(String.valueOf(conexion.getResultSet().getInt("comision")));

                   asignarItem(cmbDept,conexion.getResultSet().getInt("iddepartamento"));

                   MiSwing.activa_DesactivaControles(controles, true);

               }else{
                   
                   //Solo si el empleado tiene eliminado=1, pasa por aqui
                   
                   JOptionPane.showMessageDialog(this, "Empleado borrado");
                   MiSwing.activa_DesactivaControles(controles, false);
                   btnGrabar.setEnabled(false);
                   btnEliminar.setEnabled(false);
                   MiSwing.limpiaCampos(campos);   
                   cldFecha.setDate(null);
                   cmbDir.setSelectedIndex(0);
                   cmbDept.setSelectedIndex(0);
                   empleadoActual="A";
               }

               } catch (SQLException ex) {
                   Logger.getLogger(JFREmpleados.class.getName()).log(Level.SEVERE, null, ex);
               }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmp_NO = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtOficio = new javax.swing.JTextField();
        cmbDept = new javax.swing.JComboBox();
        txtComision = new javax.swing.JTextField();
        txtSalario = new javax.swing.JTextField();
        cmbDir = new javax.swing.JComboBox();
        btnInicio = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnFinal = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();
        brnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblControlEmp_NO = new javax.swing.JLabel();
        cldFecha = new com.toedter.calendar.JDateChooser();
        lblControlApellido = new javax.swing.JLabel();
        lblControlOficio = new javax.swing.JLabel();
        lblControlSalario = new javax.swing.JLabel();
        lblControlComision = new javax.swing.JLabel();
        lblControlLetrasApellido = new javax.swing.JLabel();
        lblControlLetrasOficio = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("EMP_NO");

        jLabel2.setText("Apellido");

        jLabel3.setText("Oficio");

        jLabel4.setText("Director");

        jLabel5.setText("Fecha Alta");

        jLabel6.setText("Salario");

        jLabel7.setText("Comision");

        jLabel8.setText("Departamento");

        txtEmp_NO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmp_NOActionPerformed(evt);
            }
        });
        txtEmp_NO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmp_NOKeyTyped(evt);
            }
        });

        txtApellido.setEnabled(false);
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtOficio.setEnabled(false);
        txtOficio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOficioKeyTyped(evt);
            }
        });

        cmbDept.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDept.setEnabled(false);

        txtComision.setEnabled(false);
        txtComision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComisionKeyTyped(evt);
            }
        });

        txtSalario.setEnabled(false);
        txtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioKeyTyped(evt);
            }
        });

        cmbDir.setEnabled(false);

        btnInicio.setText("<<");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnFinal.setText(">>");
        btnFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalActionPerformed(evt);
            }
        });

        btnGrabar.setText("Grabar");
        btnGrabar.setEnabled(false);
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        brnNuevo.setText("Nuevo");
        brnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cldFecha.setDateFormatString("dd/MM/yyyy");
        cldFecha.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(28, 28, 28)
                                .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmp_NO, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblControlEmp_NO))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblControlSalario))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblControlComision))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtOficio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblControlOficio))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cldFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbDir, javax.swing.GroupLayout.Alignment.LEADING, 0, 128, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblControlLetrasApellido)
                                            .addComponent(lblControlApellido)
                                            .addComponent(lblControlLetrasOficio)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnGrabar)
                        .addGap(32, 32, 32)
                        .addComponent(brnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmp_NO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblControlEmp_NO))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtApellido)
                            .addComponent(lblControlApellido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtOficio)
                                .addComponent(lblControlOficio))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblControlSalario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblControlComision))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblControlLetrasApellido)
                        .addGap(18, 18, 18)
                        .addComponent(lblControlLetrasOficio)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInicio)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente)
                    .addComponent(btnFinal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar)
                    .addComponent(brnNuevo)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmp_NOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmp_NOActionPerformed
        
        //Comprobamos que el texto no es vacio
         if(!txtEmp_NO.getText().equals("")){
            if(conexion.existeValor(Integer.parseInt(txtEmp_NO.getText()), "EMPLEADOS", "EMP_NO")){

                btnEliminar.setEnabled(true);
                
                btnGrabar.setEnabled(true);
                
                consulta("select * "
                        + " from empleados e "
                        + "where EMP_NO=" + txtEmp_NO.getText()+"");
                
                //Como existe el empleado, si queremos actualizar no tendremos problemas
                esInsertar=false;
                
            }else{
                
                MiSwing.limpiaCampos(campos);
                cldFecha.setDate(null);
                esInsertar=true;
                btnEliminar.setEnabled(false);
                btnGrabar.setEnabled(true);
                MiSwing.activa_DesactivaControles(controles, true);
                cmbDir.setSelectedIndex(0);
                cmbDept.setSelectedIndex(0);
                empleadoActual="A";
                
           }

            //Compruebo el anterior y siguiente por si es necesario deshabilitar algun boton de direccion
            
            if(conexion.consultaVaciaV2("select count(*) "
                                        + "from (select * "
                                                + " from empleados"
                                                + " where apellido>'" + empleadoActual+"' and "
                                                        + "eliminado=0 "
                                                + "order by apellido asc) "
                                        + "where rownum=1")){
                
                btnSiguiente.setEnabled(false);
                btnAnterior.setEnabled(true);
            
            }

            if(conexion.consultaVaciaV2("select count(*) "
                                        + "from (select * "
                                            + " from empleados"
                                            + " where apellido<'" + empleadoActual+"' and "
                                                    + "eliminado=0 "
                                            + "order by apellido desc) "
                                    + "where rownum=1")){
                
                btnAnterior.setEnabled(false);
                btnSiguiente.setEnabled(true);
            
            }
         }else{
             JOptionPane.showMessageDialog(this, "Introduce un numero de empleado");
         }
    }//GEN-LAST:event_txtEmp_NOActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        
        consulta(PRIMERO);
        
        btnAnterior.setEnabled(false);
        btnSiguiente.setEnabled(true);
        
        btnGrabar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        if(conexion.soloUno("select count(*)"
                                    + " from (select * "
                                            + " from empleados "
                                            + "where eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1")){
            btnSiguiente.setEnabled(false);
        }
        
        
        MiSwing.activa_DesactivaControles(controles, false);
        
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
       
        consulta("select * "
                + "from (select * "
                        + " from empleados "
                            + "where apellido<'" + empleadoActual+"' and "
                                + "eliminado=0 "
                        + "order by apellido desc) "
                + "where rownum=1");
        
        btnSiguiente.setEnabled(true);
        
        //Si el anterior no existe deshabilitamos el boton
        
        if(conexion.consultaVaciaV2("select count(*) "
                                           + "from (select * "
                                                    + " from empleados "
                                                + "where apellido<'" + empleadoActual+"' and "
                                                + "eliminado=0 "
                                            + "order by apellido desc) "
                                    + "where rownum=1")){
            
             btnAnterior.setEnabled(false);
        
        }
        
        if(conexion.soloUno("select count(*)"
                                    + " from (select * "
                                            + " from empleados "
                                            + "where eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1")){
            btnSiguiente.setEnabled(false);
        }
        
        esInsertar=false;
        
        btnGrabar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        MiSwing.activa_DesactivaControles(controles, false);
        
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        
        consulta("select * "
                + "from (select * "
                    + " from empleados "
                        + "where apellido>'" + empleadoActual+"' and "
                            + "eliminado=0 "
                    + "order by apellido asc) "
                + "where rownum=1");

        btnAnterior.setEnabled(true);

        //Si el siguiente no existe, el boton se deshabilita
        
        if(conexion.consultaVaciaV2("select count(*)"
                                    + " from (select * "
                                            + " from empleados "
                                            + "where apellido>'" + empleadoActual+"' and eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1")){
            btnSiguiente.setEnabled(false);
        }
        
        if(conexion.soloUno("select count(*)"
                                    + " from (select * "
                                            + " from empleados "
                                            + "where eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1")){
            btnAnterior.setEnabled(false);
        }
            
        
        esInsertar=false;
        
        btnGrabar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        MiSwing.activa_DesactivaControles(controles, false);
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalActionPerformed
        
        consulta(ULTIMO);
        
        btnSiguiente.setEnabled(false);
        btnAnterior.setEnabled(true);
        
        esInsertar=false;
        
        MiSwing.activa_DesactivaControles(controles, false);
        
        if(conexion.soloUno("select count(*)"
                                    + " from (select * "
                                            + " from empleados "
                                            + "where eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1")){
            btnAnterior.setEnabled(false);
        }
        
        btnGrabar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnFinalActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        
        //Matriz usada para indicar un mensaje personalizado en caso de vacio
        Object matriz[][]={{txtEmp_NO, txtApellido}, 
                                {" El campo EMP_NO no puede estar vacio",
                                 " El campo OFICIO no puede estar vacio"
                                }
                            };
        
        //Devolvemos el codigo del jefe
        String jefe=String.valueOf(devolverCodigoComboBox(cmbDir));
        
        //Comprobamos los errores
        String error=comprobarVacios(matriz);
        
        //Comprobamos si se ha seleccionado algun departamento
        if(devolverCodigoComboBox(cmbDept)==-1){
            error+="- Debes seleccionar un DEPARTAMENTO\n";
        }
        
        //Comprobamos si el director es el mismo que el empleado
        if(devolverNombreComboBox(cmbDir).equals(txtApellido.getText())){
            
            error+="- El empleado no puede ser su propio jefe\n";
        
        }
        
        //Comprobamos la fecha
        if(cldFecha.getDate()==null || Numeros.cuentaCifras(Fechas.deFechaANumero(cldFecha.getDate()))>8){
            
            error+="- La fecha introducida no es valida\n";
            
        }
        
        //Comprobamos si hay mas de un jefe
        if(conexion.masOIgualQueUno("select count(*) from empleados where dir is null") && devolverCodigoComboBox(cmbDir)==-1){
            
            error+="- No puede haber mas de un presidente, elije un director\n";
        }
        
        //Si no hay errores, inserta o actualiza(segun el caso) y sino muestra un mensaje con todos los errores
        if(error.equals("")){
            
            //true =inserta
            //false= actualiza
            if(esInsertar){
                int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguro de querer grabar el registro?",
                                                            "Confirmar",
                                                            JOptionPane.YES_NO_OPTION);
            
                if(seleccion==JOptionPane.YES_OPTION){

                    if(jefe.equals("-1")){
                        jefe="null";
                    }
                    
                    //Inserto el registro, sabiendo que ya no tiene errores
                    conexion.ejecutarInstruccion("insert into empleados values("
                            +(conexion.ultimoID("idempleado", "empleados")+1)+","
                            + ""+txtEmp_NO.getText()+","
                            + " '"+txtApellido.getText().toUpperCase()+"',"
                            + " '"+txtOficio.getText().toUpperCase()+"',"
                            + " "+jefe+","
                            + ""+Fechas.deFechaANumero(cldFecha.getDate())+","
                            + ""+devolverEstado(txtSalario)+","
                            + ""+devolverEstado(txtComision)+","
                            + ""+devolverCodigoComboBox(cmbDept)+","
                            + "0)");

                    conexion.commit();
                    
                    JOptionPane.showMessageDialog(this, "Empleado insertado");
                }
                
            }else{
                
                int seleccion=JOptionPane.showConfirmDialog(this, 
                                                            "¿Estas seguros de querer actualizar el registro?",
                                                            "Confirmar",
                                                            JOptionPane.YES_NO_OPTION);

                if(seleccion==JOptionPane.YES_OPTION){

                    if(jefe.equals("-1")){
                        jefe="null";
                    }
                    
                    //Actualizo el regsitro, sabiendo que ya no tiene errores
                    conexion.ejecutarInstruccion("update empleados "
                        + "set EMP_NO="+txtEmp_NO.getText()+","
                        + "Apellido= '"+txtApellido.getText()+"',"
                        + "Oficio= '"+txtOficio.getText()+"',"
                        + "dir= "+jefe+","
                        + "fecha_alt="+Fechas.deFechaANumero(cldFecha.getDate())+","
                        + "salario="+txtSalario.getText()+","
                        + "comision="+devolverEstado(txtComision)+","
                        + "iddepartamento="+devolverCodigoComboBox(cmbDept)+
                        " where idempleado="+idempleado);

                    conexion.commit();
                    
                    JOptionPane.showMessageDialog(this, "Empleado actualizado");
                }
                
            }
            
        }else{
            JOptionPane.showMessageDialog(this, error);
        }
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void brnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnNuevoActionPerformed
       
        MiSwing.limpiaCampos(campos);
        txtEmp_NO.setText("");
        
        cldFecha.setDate(null);

        esInsertar=true;

        txtEmp_NO.requestFocus();
        
        btnEliminar.setEnabled(false);
        btnGrabar.setEnabled(false);
        
        cmbDir.setSelectedIndex(0);
        cmbDept.setSelectedIndex(0);
        
        MiSwing.activa_DesactivaControles(controles, false);
        
        empleadoActual="A";
        
    }//GEN-LAST:event_brnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int seleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de borrar el registro?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        
        if(seleccion==JOptionPane.YES_OPTION){
            conexion.ejecutarInstruccionCommit("update empleados set eliminado=1 where EMP_NO="+txtEmp_NO.getText()+"", true);
            
            //Al eliminar, se muestra el siguiente o el anterior segun este disponible
            
            if(conexion.consultaVaciaV2("select count(*) "
                                        + "from (select * "
                                            + " from empleados "
                                            + " where apellido>'" + empleadoActual+"' and "
                                                    + "eliminado=0 "
                                            + "order by apellido asc) "
                                        + "where rownum=1")){
                
                consulta("select * "
                        + "from (select * "
                                    + " from empleados"
                                    + " where apellido<'" + empleadoActual+"' and eliminado=0 "
                                    + "order by apellido desc) "
                        + "where rownum=1");
            
            }else{
                
                consulta("select * "
                        + "from (select *"
                                    + " from empleados"
                                    + " where apellido>'" + empleadoActual+"' and eliminado=0 "
                                    + "order by apellido asc) "
                        + "where rownum=1");
            }
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(conexion!=null){
            conexion.cerrarConexion();
        }
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtEmp_NOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmp_NOKeyTyped
        if (evt.getKeyChar()==KeyEvent.VK_ENTER){
            //Permite Enter
        }else{
            MiSwing.escribirSoloNumeros(evt, lblControlEmp_NO);
            MiSwing.noEscribirMasDe(txtEmp_NO, 4, evt, lblControlEmp_NO);
        }
        
    }//GEN-LAST:event_txtEmp_NOKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        
        MiSwing.escribirSoloLetrasYEspacios(evt, lblControlApellido);
        MiSwing.noEscribirMasDe(txtApellido, 50, evt, lblControlLetrasApellido);
        
        
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtOficioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOficioKeyTyped
        
        MiSwing.escribirSoloLetrasYEspacios(evt, lblControlOficio);
        MiSwing.noEscribirMasDe(txtOficio, 30, evt, lblControlLetrasOficio);
        
    }//GEN-LAST:event_txtOficioKeyTyped

    private void txtSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyTyped
        
        MiSwing.escribirSoloDoubles(txtSalario, evt, lblControlSalario, 4, 2);
        
    }//GEN-LAST:event_txtSalarioKeyTyped

    private void txtComisionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComisionKeyTyped
        
        MiSwing.escribirSoloDoubles(txtComision, evt, lblControlComision, 4, 2);
        
    }//GEN-LAST:event_txtComisionKeyTyped

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(conexion!=null){
            conexion.cerrarConexion();
        }
        System.exit(0);        
    }//GEN-LAST:event_formWindowClosed

    //Devuelve el codigo del comboBox de dos columnas
    public int devolverCodigoComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return Integer.parseInt(newSelection[0]);
        
    }
    
    //Devuelve el valor visible del comboBox de dos columnas
    public String devolverNombreComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return newSelection[1];
        
    }
    
    //Devuelve null si el campo esta vacio
    public String devolverEstado(JTextField txt){
        
        if(txt.getText().isEmpty()){
            return null;
        }else{
            return txt.getText();
        }
        
    }
    
    //Asigna el item segun el codigo
    public void asignarItem(JComboBox cmb,int codigo){
        
        boolean encontrado=false;
        
        for(int i=1;i<cmb.getItemCount() && !encontrado;i++){
            String[] seleccion=(String[])cmb.getItemAt(i);
            
            if(codigo==Integer.parseInt(seleccion[0])){
                cmb.setSelectedIndex(i);
                encontrado=true;
            }
        }
        
        if(!encontrado){
            cmb.setSelectedIndex(0);
        }
        
    }
    
    //Comprueba los datos y devuelve los correspondientes errores
    public static String comprobarVacios(Object matriz[][]){
        
        String error="";
        
        for(int i=0;i<matriz[0].length;i++){
            JTextField c=(JTextField)matriz[0][i];
            
            if(c.getText().isEmpty()){
                error+="-"+(String)matriz[1][i]+"\n";
            }
        }
        
        return error;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFREmpleados().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnNuevo;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFinal;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguiente;
    private com.toedter.calendar.JDateChooser cldFecha;
    private javax.swing.JComboBox cmbDept;
    private javax.swing.JComboBox cmbDir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblControlApellido;
    private javax.swing.JLabel lblControlComision;
    private javax.swing.JLabel lblControlEmp_NO;
    private javax.swing.JLabel lblControlLetrasApellido;
    private javax.swing.JLabel lblControlLetrasOficio;
    private javax.swing.JLabel lblControlOficio;
    private javax.swing.JLabel lblControlSalario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtComision;
    private javax.swing.JTextField txtEmp_NO;
    private javax.swing.JTextField txtOficio;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
    private ConexionDB conexion;
    private JTextField[] campos;
    private Component[] controles;
    private String empleadoActual;
    private int idempleado;
    private boolean esInsertar; //usado para saber si el registro es para modificar o insertar
    //Constantes
    
    //Primer registro
    public final String PRIMERO="select * "
                                    + "from (select * "
                                            + "from empleados "
                                            + "where eliminado=0 "
                                            + "order by apellido asc) "
                                    + "where rownum=1";
    
    //Ultimo registro
    public final String ULTIMO="select * "
                                + "from (select * "
                                        + "from empleados "
                                        + "where eliminado=0 "
                                        + "order by apellido desc) "
                                + "where rownum=1";
}
