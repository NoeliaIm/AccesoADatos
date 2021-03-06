/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.awt.Frame;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Beditoriales;
import modelo.Blibros;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ALUMNOM
 */
public class JI_Editoriales extends javax.swing.JInternalFrame {

    /**
     * Creates new form JI_Editoriales
     */
    private static JI_Editoriales instancia;
    public DefaultTableModel tablaEditoriales;

    public static JI_Editoriales getInstancia() {
        // Si la instancia no está creada o la ventana está cerrada se crea la instancia

        if (instancia == null) {
            instancia = new JI_Editoriales();
        }
        return instancia;
    }

    private JI_Editoriales() {
        initComponents();
        tablaEditoriales = (DefaultTableModel) jTableEditoriales.getModel();

        llenarTablaEditorialesInicioHibernate();
    }

    private void filtrar() {

        String sql = "FROM Beditoriales e";
        if (radioEliminados.isSelected()) {
            sql = sql + " WHERE e.eliminado =1";
        } else {
            sql = sql + " WHERE e.eliminado =0";
        }

        if (txtNombre.getText().length() > 0) {
            sql = sql + " and upper(e.nombre) like '%" + txtNombre.getText().toUpperCase() + "%'";
        }
        if (txtPoblacion.getText().length() > 0) {
            sql = sql + " and upper(e.pcodPobl.ppoblaciones.poblacion) like '%" + txtPoblacion.getText().toUpperCase() + "%'";
        }

        sql = sql + " order by e.nombre";

        llenarTablaEditorialesHibernate(sql);

    }

    private void llenarTablaEditorialesInicioHibernate() {
        String sql = "FROM Beditoriales e WHERE e.eliminado=0 ORDER BY e.nombre";
        llenarTablaEditorialesHibernate(sql);
    }

    private void llenarTablaEditorialesHibernate(String sql) {

        limpiarTabla(tablaEditoriales);
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery(sql);
        Iterator iter = consulta.iterate();
        while (iter.hasNext()) {
            Beditoriales edit = (Beditoriales) iter.next();

            Object nuevo[] = {Integer.toString(edit.getIdeditorial()), edit.getNombre(), edit.getDireccion(), edit.getPcodPobl().getPpoblaciones().getPoblacion(), Integer.toString(edit.getTelef1()), Integer.toString(edit.getTelef2()), edit.getEmail()};
            tablaEditoriales.addRow(nuevo);
        }
        sesion.close();
        
    }

    public static void setOcultarColumnasJTable(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

    private void limpiarTabla(DefaultTableModel tabla) {
        int a = tabla.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            tabla.removeRow(i);
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
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPoblacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEditoriales = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiarFiltros = new javax.swing.JButton();
        radioEliminados = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("EDITORIALES");

        jLabel1.setText("FILTROS");

        jLabel2.setText("Nombre");

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel3.setText("Poblacion");

        txtPoblacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPoblacionFocusLost(evt);
            }
        });
        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPoblacionKeyReleased(evt);
            }
        });

        jTableEditoriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IDEDITORIAL", "NOMBRE", "DIRECCION", "POBLACION", "TELEF1", "TELEF2", "EMAIL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEditoriales);

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

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/EXIT_ (23).jpg"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiarFiltros.setText("Limpiar Filtros");
        btnLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltrosActionPerformed(evt);
            }
        });

        radioEliminados.setText("Eliminados");
        radioEliminados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioEliminadosStateChanged(evt);
            }
        });
        radioEliminados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEliminadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnLimpiarFiltros)))
                        .addGap(182, 182, 182))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSalir)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(radioEliminados))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarFiltros)
                    .addComponent(radioEliminados))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btnSalir)
                        .addGap(225, 225, 225))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtPoblacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoblacionKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtPoblacionKeyReleased

    private void txtPoblacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPoblacionFocusLost
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_txtPoblacionFocusLost

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Frame f = JOptionPane.getFrameForComponent(this);
        JD_EditorialNueva dialogEditorialNueva = new JD_EditorialNueva(f, true, "0");
        dialogEditorialNueva.dispose();
        dialogEditorialNueva.setVisible(true);
        // Actualizamos el jTable
        llenarTablaEditorialesInicioHibernate();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (radioEliminados.isSelected()) {
            eliminarHibernate(false, "restaurar");
        } else {
            eliminarHibernate(true, "eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    public void eliminarHibernate(boolean eliminado, String texto) {
        if (jTableEditoriales.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay editoriales");
        } else {

            if (jTableEditoriales.getSelectedRow() != -1) {

                //devuelve el valor de la fila selecciona y la columna 0
                int fila = jTableEditoriales.getSelectedRow();
                if (comprobarSiExisteHibernate(jTableEditoriales.getValueAt(fila, 0).toString())) {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar, ya hay un libro con esa editorial");
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea " + texto + " el registro : " + jTableEditoriales.getValueAt(fila, 1) + "?");
                    Session sesion = JF_Menu.instanciaHibernate.openSession();
                    if (opcion == JOptionPane.YES_OPTION) {
                        Transaction tx = sesion.beginTransaction();
                        Beditoriales edit = new Beditoriales();
                        edit = (Beditoriales) sesion.get(Beditoriales.class, Integer.parseInt(jTableEditoriales.getValueAt(fila, 0).toString()));
                        edit.setEliminado(eliminado);
                        tx.commit();
                        System.out.println("Editorial " + jTableEditoriales.getValueAt(fila, 0) + " borrada");
                        tablaEditoriales.removeRow(fila);
                        String texto1 = "";
                        if (texto.equals("eliminar")) {
                            texto1 = "eliminado";
                        } else {
                            texto1 = "restaurado";
                        }
                        JOptionPane.showMessageDialog(null, "Registro " + texto1);

                    }
                    sesion.close();

                }

            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna");
            }
        }
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (jTableEditoriales.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay Editorial");
        } else {
            if (jTableEditoriales.getSelectedRow() != -1) {
                //devuelve el valor de la fila selecciona y la columna que es código departamento
                String idEditorial = String.valueOf(jTableEditoriales.getValueAt(jTableEditoriales.getSelectedRow(), 0));
                Frame f = JOptionPane.getFrameForComponent(this);
                JD_EditorialNueva editorialNueva = new JD_EditorialNueva(f, true, idEditorial);
                editorialNueva.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna");
            }
        }
        llenarTablaEditorialesInicioHibernate();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltrosActionPerformed
        limpiarFiltros();
        llenarTablaEditorialesInicioHibernate();
    }//GEN-LAST:event_btnLimpiarFiltrosActionPerformed

    private void radioEliminadosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioEliminadosStateChanged
        // TODO add your handling code here:
        if (radioEliminados.isSelected()) {
            btnEliminar.setText("Restaurar");
        } else {
            btnEliminar.setText("Eliminar");
        }
        filtrar();
    }//GEN-LAST:event_radioEliminadosStateChanged

    private void radioEliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEliminadosActionPerformed
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_radioEliminadosActionPerformed
    private void limpiarFiltros() {
        txtNombre.setText("");
        txtPoblacion.setText("");
    }

    public boolean comprobarSiExisteHibernate(String filaSeleccionada) {
        boolean existe = false;
        System.out.println(filaSeleccionada);
        String sql = "FROM Blibros l WHERE l.beditoriales.ideditorial = " + filaSeleccionada
                + "AND l.eliminado=0";
        Session sesion = JF_Menu.instanciaHibernate.openSession();
        Query consulta = sesion.createQuery(sql);
        Iterator iter = consulta.iterate();
        while (iter.hasNext()) {
            Blibros lib = (Blibros) iter.next();
            String nuevo = lib.getBeditoriales().getNombre();
            if (!nuevo.equals("")) {
                existe = true;
            }
        }
        sesion.close();
        return existe;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEditoriales;
    private javax.swing.JRadioButton radioEliminados;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
}
