package combobox_departamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Isabel
 */

public class JComboboxDepartamento extends javax.swing.JFrame {
    public ConexionBd BD = new ConexionBd();    
    public Connection conexion=BD.conectar("oracle");   
    public JComboboxDepartamento() {
        initComponents();
        LlenarDepartamentos(); //Cargando con datos al JComboBox.
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextField();
        cboDep = new javax.swing.JComboBox();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mostrando datos de una base de datos en un JComboBox"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Departamentos");

        jLabel2.setText("Seleccione el Departamento");

        txtMensaje.setEditable(false);

        cboDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 62, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSalir))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("DATOS DE DEPATAMENTOS");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDepActionPerformed
        // 
        String[] newSelection = (String[])cboDep.getSelectedItem(); // Recoge lo seleccionado
        //newSelection[1] es el nombre y newSelection[0] es el código
        txtMensaje.setText("Se ha elegido: "+newSelection[1]+"   Código: "+ newSelection[0]);
    }//GEN-LAST:event_cboDepActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        BD.cerrar();
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    private void LlenarDepartamentos() {
        String datos[]=new String[2];
        try {
            cboDep.removeAllItems(); //Borra todos los Items
            Statement sentencia=conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT dept_no,dnombre FROM departamentos ORDER BY dnombre");
            //Rellena el ComboBox con los valores de la tabla DEPARTAMENTOS 
            while(resultado.next()){  
                datos[0]=Integer.toString(resultado.getInt("dept_no")); // Almacena el código del departamento
                datos[1]=resultado.getString("dnombre");  //Almacena el nombre
                cboDep.addItem(new String[] {datos[0],datos[1],}); //Los añade al Combo con dos columnas
            }
             //Renderiza el Combo 
            cboDep.setRenderer (new DefaultListCellRenderer() 
            {
                @Override
                public java.awt.Component getListCellRendererComponent (
                JList l,Object o,int i,boolean s, boolean f)
                {
                    return new JLabel (((String[])o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });

        } catch (Exception e) {
            
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboDep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
		new JComboboxDepartamento().setVisible(true);
    }
}