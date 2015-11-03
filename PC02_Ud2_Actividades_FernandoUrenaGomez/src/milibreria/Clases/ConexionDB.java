/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Clases;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import milibreria.swing.MiSwing;

/**
 *
 * @author Alumno
 */
public class ConexionDB {
    
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultSet;

    public ConexionDB(String SGBD, String PC, String usuario, String clave, String SID){
        try {
            switch(SGBD){
                case "MySQL":
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection("jdbc:mysql://"+PC+"/"+SID,usuario,clave);
                    break;
                case "Oracle":
                     Class.forName("oracle.jdbc.driver.OracleDriver");
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@"+PC+":1521:"+SID,usuario,clave);
                    break;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void ejecutarConsulta(String consulta){
        try {
            sentencia = conexion.createStatement();
            resultSet = sentencia.executeQuery(consulta);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Cuenta el numero de filas, y vuelve a posicionar el puntero en el primer registro(da eerores)
//    public boolean consultaVacia(){
        
        
//        boolean vacia=true;
//        int contador = 0;
//      
//        ResultSet aux=resultSet;
//        try {
//            
//            while (aux.next()) {
//
//                contador++;
//
//            }
//            aux.beforeFirst();
//            
//        } catch (SQLException ex) {
//
//            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if(contador>0){
//            vacia=false;
//        }
//        
//        return vacia;
        
//    }
    
    public ResultSet getResultSet() {
        return resultSet;
    }
    
    //Es necesario hacer la consulta antes
    public void rellenaJTableBD(DefaultTableModel tabla){
        try {
            ResultSetMetaData metadatos = resultSet.getMetaData();
            
            tabla.setColumnCount(metadatos.getColumnCount());
            
            int numeroColumnas=tabla.getColumnCount();
            
            String[] etiquetas = new String[numeroColumnas];
            
            for (int i = 0; i < numeroColumnas; i++){
               etiquetas[i] = metadatos.getColumnLabel(i + 1); 
            }
            
            tabla.setColumnIdentifiers(etiquetas);
           
            while (resultSet.next()){
                    Object[] datosFila = new Object[tabla.getColumnCount()];
                    for (int i = 0; i < tabla.getColumnCount(); i++){
                        datosFila[i] = resultSet.getObject(i + 1);
                    }
                    tabla.addRow(datosFila);
            }
            
            cerrarResult();
        
        } catch (SQLException ex) {
            Logger.getLogger(MiSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    //Primero
    public void cerrarResult(){
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Segundo
    public void cerrarSentencia(){
        try {
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Ultimo
    public void cerrarConexion(){
        try {
            cerrarResult();
            cerrarSentencia();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
