/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import conexion.ConexionSingleton;
import funciones.MetodosComunes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author ALUMNOM
 */
public class JD_PrestamoNuevo extends javax.swing.JDialog {

    /**
     * Creates new form JD_PrestamoNuevo
     */
    private String idPrestamo;
    private boolean esNuevo = false;
    private MetodosComunes funciones = new MetodosComunes();

    public JD_PrestamoNuevo(java.awt.Frame parent, boolean modal, String idPrestamo) {
        super(parent, modal);
        this.idPrestamo = idPrestamo;
        if (this.idPrestamo.equals("0")) {
            esNuevo = true;
        }
        initComponents();
        llenarSocio();
        llenarLibro();
        rellenarCampos();
    }
    
    public void rellenarCampos(){
        try {
            if (!esNuevo) {
                String sql = " SELECT * "
                    + " FROM BPRESTAMOS P,BLIBROS L , BSOCIOS S "
                    + " WHERE P.IDSOCIO = S.IDSOCIO "
                    + " AND P.IDLIBRO = L.IDLIBRO "
                    + " AND P.IDPRESTAMO="+idPrestamo;
                ResultSet resultado;
                try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
                    resultado = consulta.executeQuery(sql);
                    resultado.next();
                     String [] seleccionSocio  = {resultado.getString("idsocio"),resultado.getString("apellidos")};
                    cboSocio.getModel().setSelectedItem(seleccionSocio);
                    String [] seleccionLibro = {resultado.getString("idlibro"),resultado.getString("titulo")};
                    cboLibro.getModel().setSelectedItem(seleccionLibro);
                    funciones.IntegerToDate(resultado.getInt("fechasalida"), jDateFecha);
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JD_MultaNueva.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarSocio() {
        String datos[] = new String[2];
        String cabecera[] = new String[2]; // Cabecera del comboBox
        try {
            cboSocio.removeAllItems(); //Borra todos los Items
            Statement sentencia = ConexionSingleton.conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT idsocio,apellidos FROM bsocios WHERE ELIMINADO = 0 ORDER BY apellidos");

            // Cabecera del comboBox con codigo -1
            cabecera[0] = "-1";
            cabecera[1] = "--Seleccione Socio--";
            cboSocio.addItem(new String[]{cabecera[0], cabecera[1],});

            //Rellena el ComboBox con los valores de la tabla DEPARTAMENTOS 
            while (resultado.next()) {
                datos[0] = Integer.toString(resultado.getInt("idsocio")); // Almacena el código del departamento
                datos[1] = resultado.getString("apellidos");  //Almacena el nombre

                cboSocio.addItem(new String[]{datos[0], datos[1],}); //Los añade al Combo con dos columnas
            }
            //Renderiza el Combo 
            cboSocio.setRenderer(new DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(
                        JList l, Object o, int i, boolean s, boolean f) {
                    return new JLabel(((String[]) o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });

        } catch (Exception e) {

        }

    }

    public void llenarLibro() {
        String datos[] = new String[2];
        String cabecera[] = new String[2]; // Cabecera del comboBox
        try {
            cboLibro.removeAllItems(); //Borra todos los Items
            Statement sentencia = ConexionSingleton.conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT idlibro,titulo FROM blibros WHERE eliminado =0 AND nrocopiasdisp>=1 ORDER BY titulo");

            // Cabecera del comboBox con codigo -1
            cabecera[0] = "-1";
            cabecera[1] = "--Seleccione Libro--";
            cboLibro.addItem(new String[]{cabecera[0], cabecera[1],});

            //Rellena el ComboBox con los valores de la tabla DEPARTAMENTOS 
            while (resultado.next()) {
                datos[0] = Integer.toString(resultado.getInt("idlibro")); // Almacena el código del departamento
                datos[1] = resultado.getString("titulo");  //Almacena el nombre

                cboLibro.addItem(new String[]{datos[0], datos[1],}); //Los añade al Combo con dos columnas
            }
            //Renderiza el Combo 
            cboLibro.setRenderer(new DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(
                        JList l, Object o, int i, boolean s, boolean f) {
                    return new JLabel(((String[]) o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });

        } catch (Exception e) {

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
        cboSocio = new javax.swing.JComboBox();
        cboLibro = new javax.swing.JComboBox();
        jDateFecha = new com.toedter.calendar.JDateChooser();
        btnGrabar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRESTAMO DE LIBRO");

        jLabel1.setText("Socio");

        jLabel2.setText("Libro");

        jLabel3.setText("Fecha Salida");

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cboSocio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cboLibro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnGrabar)
                        .addGap(40, 40, 40)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jDateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrabar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        insertarNuevoPrestamo();
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        cboSocio.setSelectedIndex(0);
        cboLibro.setSelectedIndex(0);
        jDateFecha.setDate(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void restarNroCopiasDisponibles() {
        try {
            // TODO add your handling code here:
            String[] newSelection = (String[]) cboLibro.getSelectedItem();
            String idLibro = newSelection[0];
            String sql;
            Statement consulta = ConexionSingleton.conexion.createStatement();
            sql = "UPDATE BLIBROS SET NROCOPIASDISP=nrocopiasdisp-1 where idlibro  =" + idLibro;
            System.out.println(sql);
            int filas = consulta.executeUpdate(sql);
            consulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(JD_PrestamoNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarNuevoPrestamo() {

        String[] newSelectionLibro = (String[]) cboLibro.getSelectedItem();
        String idLibro = newSelectionLibro[0];
        String[] newSelectionSocio = (String[]) cboSocio.getSelectedItem();
        String idSocio = newSelectionSocio[0];
        int fechaSalida = funciones.DateToInteger(jDateFecha);
        int fechaMaxima = fechaSalida + 10;

        if (!isEmpty()) {
            if (!tieneMultasPorPagar(idSocio)) {
                if (!tieneLibrosSinDevolver(idSocio)) {
                    try {
                        String sql;
                        Statement consulta = ConexionSingleton.conexion.createStatement();
                        if (!esNuevo) {

                            sql = "UPDATE BPRESTAMOS  SET IDSOCIO=" + idSocio + "," + "IDLIBRO=" + idLibro + "," + "FECHASALIDA=" + fechaSalida
                                    + " WHERE IDPRESTAMO= " + idPrestamo;

                            System.out.println(sql);
                            int filas = consulta.executeUpdate(sql);

                            consulta.close();
                            JOptionPane.showMessageDialog(null, "Registro Actualizado");
                            // Restamos la cantidad de copias disponibles de ese libro
                           
                            this.dispose();

                        } else {

                            Integer idPrestamo = maximo("BPRESTAMOS", "IDPRESTAMO") + 1;
                            sql = "INSERT INTO BPRESTAMOS VALUES (" + idPrestamo + "," + idSocio + "," + idLibro + "," + fechaSalida
                                    + "," + fechaMaxima + "," + null + "," + "'n'"
                                    + ",0)";
                            System.out.println(sql);
                            int filas = consulta.executeUpdate(sql);
                            consulta.close();
                            JOptionPane.showMessageDialog(null, "Registro creado");
                            // Restamos las copias disponibles de ese libro
                            restarNroCopiasDisponibles();
                            //this.dispose();

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(JD_PrestamoNuevo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El socio tiene libros sin devolver");
                }

            } else {
                JOptionPane.showMessageDialog(null, "El socio tiene multas por pagar");
            }

        } else {
            comprobarVacios();
        }
         
    }

    private boolean isEmpty() {
        if (cboSocio.getSelectedIndex() == 0
                || cboLibro.getSelectedIndex() == 0
                || jDateFecha.getDate() == null) {
            return true;
        } else {
            return false;
        }

    }

    private boolean comprobarVacios() {
        boolean estanVacios = false;
        String texto = "ERROR:\n";
        if (cboLibro.getSelectedIndex() == 0) {
            texto = texto + " Seleccione LIBRO\n";
            estanVacios = true;
        }
        if (cboSocio.getSelectedIndex() == 0) {
            texto = texto + " Seleccione SOCIO\n";
            estanVacios = true;
        }
        if (jDateFecha.getDate() == null) {
            texto = texto + " Seleccione FECHA\n";
            estanVacios = true;
        }
        if (estanVacios) {
            JOptionPane.showMessageDialog(null, texto);
        }
        return estanVacios;

    }

    private Integer maximo(String tabla, String id) throws SQLException {
        String sql = "SELECT MAX(" + id + ") maximo FROM " + tabla;
        ResultSet resultado;
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            resultado = consulta.executeQuery(sql);
            resultado.next();
            return resultado.getInt("maximo");
        }

    }

    private boolean tieneMultasPorPagar(String idSocio) {
        ResultSet filas;
        String sql = "SELECT P.IDSOCIO FROM BPRESTAMOS P,BMULTASPRESTAMOS MP"
                + " WHERE MP.IDPRESTAMO= P.IDPRESTAMO"
                + " AND MP.PAGADA='n'"
                + " AND IDSOCIO = " + idSocio
                + " AND P.ELIMINADO= 0";
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            filas = consulta.executeQuery(sql);
            while (filas.next()) {
                Object nuevo[] = {filas.getInt("IDSOCIO")};
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JI_Socios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean tieneLibrosSinDevolver(String idSocio) {
        ResultSet filas;
        String sql = "SELECT IDSOCIO FROM BPRESTAMOS P"
                + " WHERE P.DEVUELTO = 'n'"
                + " AND P.FECHAMAXIMA<" + funciones.getFechaActual()
                + " AND IDSOCIO= " + idSocio
                + " AND ELIMINADO= 0";
        try (Statement consulta = ConexionSingleton.conexion.createStatement()) {
            filas = consulta.executeQuery(sql);
            while (filas.next()) {
                Object nuevo[] = {filas.getInt("IDSOCIO")};
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JI_Socios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboLibro;
    private javax.swing.JComboBox cboSocio;
    private com.toedter.calendar.JDateChooser jDateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
