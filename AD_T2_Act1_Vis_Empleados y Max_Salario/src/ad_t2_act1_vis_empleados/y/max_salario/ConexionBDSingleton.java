/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_t2_act1_vis_empleados.y.max_salario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import milibreria.swing.MiSwing;

/**
 *
 * @author Alumno
 */
public final class ConexionBDSingleton {
    
    private static String SGBD;
    private static String usuario;
    private static String clave;
    private static String servidor;
    private static String SID;
    
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultSet;
    
    private static ConexionBDSingleton INSTANCE;
    
    private ConexionBDSingleton(String SGBD, String servidor, String SID, String usuario, String clave) {
        
        try {
            switch(SGBD){
                case "MySQL":
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+SID,usuario,clave);
                    break;
                case "Oracle":
                     Class.forName("oracle.jdbc.driver.OracleDriver");
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@"+servidor+":1521:"+SID,usuario,clave);
                    break;
            }
            
            //Indicamos que no haga autocommit
            conexion.setAutoCommit(false);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionBDSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    public static ConexionBDSingleton getInstance(String pSGBD, String pServidor, String pSID, String pUsuario, String pClave) {
       
        createInstance(pSGBD, pServidor, pSID, pUsuario, pClave);
        return INSTANCE;
        
    }
    
    private synchronized static void createInstance(String pSGBD, String pServidor, String pSID, String pUsuario, String pClave) {
        
        synchronized(ConexionBDSingleton.class) {
            if (INSTANCE == null) { 
                
                SGBD=pSGBD;
                usuario=pUsuario;
                clave=pClave;
                servidor=pServidor;
                SID=pSID;

                INSTANCE=new ConexionBDSingleton(SGBD, servidor, SID, usuario,clave);

            }
        }
        
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(); 
    }
    
    public void commit(){
        
        try {
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void roolback(){
        
        try {
            conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDSingleton.class.getName()).log(Level.SEVERE, null, ex);
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
    
    //Seria para valores unicos (string)
    public boolean existeValor(String valor, String tabla, String columna){
        
        boolean existe=false;
        
        Statement sentenciaAux;
        try {
            sentenciaAux = conexion.createStatement();
            
            ResultSet aux= sentenciaAux.executeQuery("select count(*) from "+tabla+" where "+columna+"='"+valor+"'");
        
            aux.next();

            if (aux.getInt(1)>=1){
                existe=true;
            }

            aux.close();
            sentenciaAux.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return existe;
    }
    
    //Seria para valores unicos (int)
    public boolean existeValor(int valor, String tabla, String columna){
        
        boolean existe=false;
        
        Statement sentenciaAux;
        try {
            sentenciaAux = conexion.createStatement();
            
            ResultSet aux= sentenciaAux.executeQuery("select count(*) from "+tabla+" where "+columna+"="+valor+"");
        
            aux.next();

            if (aux.getInt(1)>=1){
                existe=true;
            }

            aux.close();
            sentenciaAux.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return existe;
    }
    
    
    public boolean consultaVacia(DefaultTableModel modelo){
        
        if (modelo.getRowCount()==0){
            return true;
        }
        
        return false;
        
    }
    
    //Le paso una consulta en un String antes de ejecutar la consulta
    public boolean consultaVaciaV2(String query){
        
        boolean vacio =false;
        
        Statement sentenciaAux;
        try {
            sentenciaAux = conexion.createStatement();
            
            ResultSet aux= sentenciaAux.executeQuery(query);
        
            aux.next();

            if (aux.getInt(1)==0){
                vacio=true;
            }

            aux.close();
            sentenciaAux.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vacio;
        
    }
    
    public ResultSet getResultSet() {
        return resultSet;
    }
    
    //Es necesario hacer la consulta antes
    public void rellenaJTableBD(DefaultTableModel tabla){
        try {
            
            //Cabecera
            
            ResultSetMetaData metadatos = resultSet.getMetaData();
            
            tabla.setColumnCount(metadatos.getColumnCount());
            
            int numeroColumnas=tabla.getColumnCount();
            
            String[] etiquetas = new String[numeroColumnas];
            
            for (int i = 0; i < numeroColumnas; i++){
               etiquetas[i] = metadatos.getColumnLabel(i + 1); 
            }
            
            tabla.setColumnIdentifiers(etiquetas);
           
            //Contenido
            
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
