/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_t2_act4_gestionempleados_nombre._pc02;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
                case "OBDC": //No es necesario pasarle ningun datos mas, cuidado con MYSQL_ODBC
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    conexion=DriverManager.getConnection("jdbc:odbc:MYSQL_ODBC");
                    break;
            }
            
            conexion.setAutoCommit(false);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public Statement getSentencia(){
        return sentencia;
    }
    
    public Connection getconexion(){
        return conexion;
    }
   
    public void commit(){
        
        try {
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void roolback(){
        
        try {
            conexion.rollback();
        } catch (SQLException ex) {
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
    
    public int ejecutarInstruccion(String instruccion){
        
        int filas=0;
        
        try {
            //Cuidado, ya que es posible que lo sobrescribas
            sentencia=conexion.createStatement();
            filas=sentencia.executeUpdate(instruccion);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filas;
    }
    
    public int ejecutarInstruccionCommit(String instruccion, boolean commit){
        
        int filas=0;
        
        try {
            //Cuidado, ya que es posible que lo sobrescribas
            sentencia=conexion.createStatement();
            filas=sentencia.executeUpdate(instruccion);
            
            if(commit){
                commit();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filas;
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
   
    //Seria para valores unicos (string)
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
    
    public boolean masOIgualQueUno(String query){
        
        boolean vacio =false;
        
        Statement sentenciaAux;
        try {
            sentenciaAux = conexion.createStatement();
            
            ResultSet aux= sentenciaAux.executeQuery(query);
        
            aux.next();

            if (aux.getInt(1)>=1){
                vacio=true;
            }

            aux.close();
            sentenciaAux.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vacio;
        
    }
    
    
    public boolean consultaVacia(DefaultTableModel modelo){
        
        if (modelo.getRowCount()==0){
            return true;
        }
        
        return false;
        
    }
    
    //Le paso una consulta en un String antes de ejecutar la consulta, la consulta debe ser un count
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
    
    public int ultimoID(String columnaID, String tabla){
        
        int IDMaximo=-1;
        Statement sm;
        try {
            sm = conexion.createStatement();
            ResultSet rs=sm.executeQuery("select max("+columnaID+") as "+columnaID+" from "+tabla+"");
            rs.next();
            IDMaximo=rs.getInt(columnaID);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return IDMaximo;
        
    }
    
    public int ultimoIDSinEliminar(String columnaEliminado, String columnaID, String tabla){
        
        int IDMaximo=-1;
        Statement sm;
        try {
            sm = conexion.createStatement();
            ResultSet rs=sm.executeQuery("select max("+columnaID+") as "+columnaID+" from "+tabla+" where "+columnaEliminado+"=0");
            rs.next();
            IDMaximo=rs.getInt(columnaID);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return IDMaximo;
        
    }
    
    public int primerID(String columnaID, String tabla){
        
        int IDMaximo=-1;
        Statement sm;
        try {
            sm = conexion.createStatement();
            ResultSet rs=sm.executeQuery("select min("+columnaID+") as "+columnaID+" from "+tabla+"");
            rs.next();
            IDMaximo=rs.getInt(columnaID);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return IDMaximo;
        
    }
    
    public int primerIDSinEliminar(String columnaEliminado,String columnaID, String tabla){
        
        int IDMaximo=-1;
        Statement sm;
        try {
            sm = conexion.createStatement();
            ResultSet rs=sm.executeQuery("select min("+columnaID+") as "+columnaID+" from "+tabla+" where "+columnaEliminado+"=0");
            rs.next();
            IDMaximo=rs.getInt(columnaID);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return IDMaximo;
        
    }
    
     //De momento, solo para String
    public String minimoDe(String columna, String tabla, String condicion){
        
        String resultado="";
        
        Statement sm;
        try {
            sm = conexion.createStatement();
            
            ResultSet rs;
            if(condicion.equals("")){
                rs=sm.executeQuery("select min("+columna+") as "+columna+" from "+tabla+"");
            }else{
                rs=sm.executeQuery("select min("+columna+") as "+columna+" from "+tabla+" where "+condicion);
            }
            
            rs.next();
            resultado=rs.getString(columna);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
    
    //De momento, solo para String
    public String maximoDe(String columna, String tabla, String condicion){
        
        
        String resultado="";
        
        Statement sm;
        try {
            sm = conexion.createStatement();
            
            ResultSet rs;
            if(condicion.equals("")){
                rs=sm.executeQuery("select max("+columna+") as "+columna+" from "+tabla+"");
            }else{
                rs=sm.executeQuery("select max("+columna+") as "+columna+" from "+tabla+" where "+condicion);
            }
            
            rs.next();
            resultado=rs.getString(columna);
            
            rs.close();
            sm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
    
    
    //debe devolver un tipo de valor (String)
    //la condicion sirve para poder seleccionar el item que corresponda
    public void rellenaComboBoxBDString(JComboBox cmb, String tabla, String columnaASacar, String condicion){
        
        cmb.removeAllItems();
        
        Statement sm;
        try {
            sm = conexion.createStatement();
            
            ResultSet consulta=sm.executeQuery("select distinct "+columnaASacar+" from "+tabla);
            
            ResultSet correspondiente=null;
            
            if(!condicion.equals("")){
                
                Statement smAux=conexion.createStatement();
                
                correspondiente=sm.executeQuery("select distinct "+columnaASacar+" from "+tabla+" where "+condicion);
                correspondiente.next();
                
                while(consulta.next()){
                
                    cmb.addItem(consulta.getString(columnaASacar));
                    if(correspondiente.getString(columnaASacar).equals(consulta.getString(columnaASacar))){
                        cmb.setSelectedItem(correspondiente.getString(columnaASacar));
                    }
                }
                
                 correspondiente.close();
                 smAux.close();
            }else{
                
                while(consulta.next()){
                
                    cmb.addItem(consulta.getString(columnaASacar));
                
                }
                
            }
              
            consulta.close();
            sm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //debe devolver un tipo de valor (int)
    //la condicion sirve para poder seleccionar el item que corresponda
    public void rellenaComboBoxBDInt(JComboBox cmb, String tabla, String columnaASacar, String condicion){
        
        cmb.removeAllItems();
        
        Statement sm;
        try {
            sm = conexion.createStatement();
            
            ResultSet consulta=sm.executeQuery("select distinct "+columnaASacar+" from "+tabla);
            
            ResultSet correspondiente=null;
            
            if(!condicion.equals("")){
                
                Statement smAux=conexion.createStatement();
                
                correspondiente=smAux.executeQuery("select distinct "+columnaASacar+" from "+tabla+" where "+condicion);
                correspondiente.next();
                
                while(consulta.next()){
                
                    cmb.addItem(consulta.getInt(columnaASacar));
                    if(correspondiente.getInt(columnaASacar)==consulta.getInt(columnaASacar)){
                        cmb.setSelectedItem(correspondiente.getInt(columnaASacar));
                    }
                }
                
                correspondiente.close();
                smAux.close();
            }else{
                
                while(consulta.next()){
                
                    cmb.addItem(consulta.getInt(columnaASacar));
                
                }
                
            }
              
            consulta.close();
           
            sm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rellenaComboBox2Columnas(JComboBox cmb, String consulta, String inicio, String columnaNoVisible, String columnaVisible){
        //"select distinct apellido, emp_no from empleados"
        String datos[]=new String[2];
        try {
            cmb.removeAllItems(); //Borra todos los Items
            Statement aux=conexion.createStatement();
            ResultSet resultado = aux.executeQuery(consulta);
            
            //Dato inicial
            datos[0]=Integer.toString(-1); 
            datos[1]=inicio;  
            cmb.addItem(new String[] {datos[0],datos[1],});
            
            while(resultado.next()){  
                datos[0]=Integer.toString(resultado.getInt(columnaNoVisible));
                datos[1]=resultado.getString(columnaVisible);  
                cmb.addItem(new String[] {datos[0],datos[1],}); 
            }
            
            cmb.setRenderer (new DefaultListCellRenderer() 
            {
                @Override
                public java.awt.Component getListCellRendererComponent (
                JList l,Object o,int i,boolean s, boolean f)
                {
                    return new JLabel (((String[])o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }   
        
    }
    
    //Es necesario hacer la consulta antes con resultSet
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
            if(resultSet!=null){
                cerrarResult();
            }
            if(sentencia!=null){
                cerrarSentencia();
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
