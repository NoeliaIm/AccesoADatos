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

public class combobox_departamento extends javax.swing.JFrame {
    //Declarando el modelo de datos.
    private javax.swing.DefaultComboBoxModel modeloCboDepartamentos;
    /** Creates new form PrincipalV2 */
    public combobox_departamento() {
        modeloCboDepartamentos = new javax.swing.DefaultComboBoxModel(new String[] {});    
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mostrando datos de una base de datos en un JComboBox"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Departamentos");

        jLabel2.setText("Seleccione el Departamento");

        txtMensaje.setEditable(false);

        cboDep.setModel(        modeloCboDepartamentos);
        cboDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
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
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        txtMensaje.setText("Se ha elegido: "+newSelection[1]+"Código: "+ newSelection[0]);
    }//GEN-LAST:event_cboDepActionPerformed

    private void LlenarDepartamentos() {
        
        ConexionBd BD = new ConexionBd();
        String datos[]=new String[2];
        try {
            cboDep.removeAllItems(); //Borra todos los Items
            Connection conexion=BD.conectar("oracle");   
            Statement sentencia=conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT dept_no,dnombre FROM departamentos ORDER BY dnombre");
            while(resultado.next()){  //Rellena el ComboBox con los valores de la tabla DEPARTAMENTOS 
                datos[0]=Integer.toString(resultado.getInt("dept_no")); // Almacena el código del departamento
                datos[1]=resultado.getString("dnombre");  //Almacena el nombre
                cboDep.addItem(new String[] {datos[0],datos[1],}); //Los añade al Combo con dos columnas
            }
            
            cboDep.setRenderer (new DefaultListCellRenderer()  //Renderiza el Combo 
            {
                @Override
                public java.awt.Component getListCellRendererComponent (
                JList l,Object o,int i,boolean s, boolean f)
                {
                    return new JLabel (((String[])o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });
            BD.cerrar();
        } catch (Exception e) {
            
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboDep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
		new combobox_departamento().setVisible(true);
    }
}