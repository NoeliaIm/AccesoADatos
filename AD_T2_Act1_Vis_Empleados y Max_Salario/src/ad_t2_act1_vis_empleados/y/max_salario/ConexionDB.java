/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_t2_act1_vis_empleados.y.max_salario;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Alumno
 */
public class ConexionDB {
    
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultSet;

    /**
     * Constructor
     * @param SGBD Oracle, MySQL, OBDC
     * @param PC IP o localhost
     * @param usuario usuario con permisos en la BD
     * @param clave 
     * @param SID En oracle ORADAM2 y en MySQL la base de datos usada
     */
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
                case "SQLite": //Los atributos PC, usuario y clave no se usan
                    Class.forName("org.sqlite.JDBC");
                    conexion = DriverManager.getConnection("jdbc:sqlite:"+SID);
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
    
    /**
     * Devuelve al resultset los resultados de una consulta
     * @param consulta 
     */
    public void ejecutarConsulta(String consulta){
        try {
            //Cuidado, puedes sobrescribirlo
            sentencia = conexion.createStatement();
            resultSet = sentencia.executeQuery(consulta);
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Devuelve el numero de filas afectadas por un delete, update o insert
     * No hace commit
     * @param instruccion
     * @return 
     */
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
    
    /**
     * Devuelve el numero de filas afectadas por un delete, update o insert
     * Podemos indicar si queremos hacer o no commit
     * @param instruccion
     * @param commit 
     * @return 
     */
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
    
    /**
     * Indica si exite el valor que le indicamos
     * Recomendable para valores unicos de String
     * @param valor
     * @param tabla
     * @param columna
     * @return 
     */
    public boolean existeValor(String valor, String tabla, String columna){
        
        boolean existe=false;
        
        Statement sentenciaAux;
        try {
            sentenciaAux = conexion.createStatement();
            
            ResultSet aux= sentenciaAux.executeQuery("select count(*) from "+tabla+" where upper("+columna+")='"+valor+"'");
        
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
   
    /**
     * Indica si exite el valor que le indicamos
     * Recomendable para valores unicos de int
     * @param valor
     * @param tabla
     * @param columna
     * @return 
     */
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
    
    /**
     * Indica si el resultado es uno o mas
     * La consulta debe llevar un count
     * @param query
     * @return 
     */
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
    
    //Debe ser un valor unico
    public int devolverValorInt(String columna, String tabla, String condicion){
        
        try (Statement sentenciaAux = conexion.createStatement();
                ResultSet aux = sentenciaAux.executeQuery("select "+columna+" from "+tabla+" where "+condicion);) {
            
            if(consultaVaciaV2("select count("+columna+") from "+tabla+" where "+condicion)){
                JOptionPane.showMessageDialog(null, "Error, consulta vacia");
                return -1;
            }else{
                aux.next();
                return aux.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }
    
    
    public int cuentaRegistrosConsulta(String columna, String tabla, String condicion){
        
        String consulta;
        
        if (condicion.equals("")){
            consulta="select "+columna+" from "+tabla;
        }else{
            consulta="select "+columna+" from "+tabla+" where "+condicion;
        }
        
        try (Statement sentenciaAux = conexion.createStatement();
                ResultSet aux = sentenciaAux.executeQuery(consulta);) {
            
            int contador=0;
            
            while(aux.next()){
                contador++;
            }
            
            return contador;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
               
    }
    
     /**
      * Devuelve un array de int con todos los valores int de la columna
      * @param columna
      * @param tabla
      * @param condicion
      * @return 
      */
    public int[] devolverValoresInt(String columna, String tabla, String condicion){
        
        try (Statement sentenciaAux = conexion.createStatement();
                ResultSet aux = sentenciaAux.executeQuery("select "+columna+" from "+tabla+" where "+condicion);) {
            
            
            if(consultaVaciaV2("select count("+columna+") from "+tabla+" where "+condicion)){
                JOptionPane.showMessageDialog(null, "Error, consulta vacia");
                return null;
            }else{
                
                int total=cuentaRegistrosConsulta(columna, tabla, condicion); 
                
                int valores[]=new int[total];
                
                for(int i=0;aux.next();i++){
                    valores[i]=aux.getInt(1);
                }
                
                return valores;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
     /**
      * Devuelve un array de String con todos los valores String de la columna
      * @param columna
      * @param tabla
      * @param condicion
      * @return 
      */
    public String[] devolverValoresString(String columna, String tabla, String condicion){
        
        try (Statement sentenciaAux = conexion.createStatement();
                ResultSet aux = sentenciaAux.executeQuery("select "+columna+" from "+tabla+" where "+condicion);) {
            
            
            if(consultaVaciaV2("select count("+columna+") from "+tabla+" where "+condicion)){
                JOptionPane.showMessageDialog(null, "Error, consulta vacia");
                return null;
            }else{
                
                int total=cuentaRegistrosConsulta(columna, tabla, condicion); 
                
                String valores[]=new String[total];
                
                for(int i=0;aux.next();i++){
                    valores[i]=aux.getString(1);
                }
                
                return valores;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    /**
     * Devuelve un unico valor String
     * @param columna
     * @param tabla
     * @param condicion
     * @return 
     */
    public String devolverValorString(String columna, String tabla, String condicion){
        
        try (Statement sentenciaAux = conexion.createStatement();
                ResultSet aux = sentenciaAux.executeQuery("select "+columna+" from "+tabla+" where "+condicion);) {
            
            if(consultaVaciaV2("select "+columna+" from "+tabla+" where "+condicion)){
                JOptionPane.showMessageDialog(null, "Error, consulta vacia");
                return null;
            }else{
                aux.next();
                
                return aux.getString(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    /**
     * Indica si la consulta es vacia, usando el elemento DefaultTableModel
     * @param modelo
     * @return 
     */
    public boolean consultaVacia(DefaultTableModel modelo){
        
        if (modelo.getRowCount()==0){
            return true;
        }
        
        return false;
        
    }
    
    /**
     * Le paso una consulta en un String antes de ejecutar la consulta
     * La consulta debe ser un count
     * @param query
     * @return 
     */
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
    
    /**
     * Indica el ultimo ID disponible.
     * No tiene en cuenta si esta o no eliminado
     * @param columnaID
     * @param tabla
     * @return 
     */
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
    
    /**
     * Devuelve el proximo ID sobre el que podemos escribir
     * Si no hay registros empieza en 1
     * @param columnaID
     * @param tabla
     * @return 
     */
    public int proximoIDDisponible(String columnaID, String tabla){
        
        int id=ultimoID(columnaID, tabla);
        
        if(id==-1){
            return 1;
        }else{
            return id+1;    
        }
        
    }
    
    /**
     * Devuelve el ultimo id que no esta eliminado
     * @param columnaEliminado
     * @param columnaID
     * @param tabla
     * @return 
     */
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
    
    /**
     * Devuelve el primer ID que no esta eliminado
     * @param columnaEliminado
     * @param columnaID
     * @param tabla
     * @return 
     */
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
    
    /**
     * Devuelve el registro menor de una tabla
     * Ordenado alfabeticamente 
     * @param columna
     * @param tabla
     * @param condicion
     * @return 
     */
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
    
    /**
     * Devuelve el registro mayor de una tabla
     * Ordenado alfabeticamente 
     * @param columna
     * @param tabla
     * @param condicion
     * @return 
     */
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
    
    
    /**
     * Rellena un combobox de String
     * La columna debe der del tipo String
     * Solo una columna
     * @param cmb
     * @param tabla
     * @param columnaASacar
     * @param condicion 
     */
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
    
    /**
     * Rellena un combobox de Integer
     * La columna debe der del tipo Int
     * Solo una columna
     * @param cmb
     * @param tabla
     * @param columnaASacar
     * @param condicion 
     */
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
    
    /**
     * Rellena un combobox con dos columnas, el id(no visible) y el nombre
     * La consulta debe devolver el codigo y un nombre (por ese orden)
     * @param cmb
     * @param consulta
     * @param inicio
     * @param columnaNoVisible
     * @param columnaVisible 
     */
    public void rellenaComboBox2Columnas(JComboBox cmb, String consulta, String inicio, String columnaNoVisible, String columnaVisible){
        
        String datos[]=new String[2];
        try {
            cmb.removeAllItems(); //Borra todos los Items
            Statement aux=conexion.createStatement();
            ResultSet resultado = aux.executeQuery(consulta);
            
            //Dato inicial
            if(!inicio.equals("")){
                datos[0]=Integer.toString(-1); 
                datos[1]=inicio;  
                cmb.addItem(new String[] {datos[0],datos[1],});
            }
            
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
                    return new JLabel (((String[])o)[1]); 
                }
            });

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }   
        
    }
    
    /**
     * Rellena un defaultTableModel de una consulta.
     * Se adapta automaticamente al numero de columnas
     * Se debe ejecutar el metodo "ejecutarConsulta" antes de llamar a este metodo
     * @param tabla 
     */
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
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
     //Atencion: para casos de null no funciona, adaptarlo segun las necesidades
    public int ejecutarInstruccionPreparada(Object[] datos, String intruccionPreparada){
        
        int filas=0;
        
        try {
            
            PreparedStatement aux=conexion.prepareStatement(intruccionPreparada);
            
            String[] tipos=tiposDatos(datos);
            
            for(int i=0;i<tipos.length;i++){
                
                String tipoActual=tipos[i];
                
                switch(tipoActual){
                    case "Character":
                    case "String":
                        aux.setString(i+1, (String)datos[i]);
                        break;
                    case "Integer":
                        aux.setInt(i+1, (Integer)datos[i]);
                        break;
                    case "Long":
                        aux.setLong(i+1, (Long)datos[i]);
                        break;
                    case "Double":
                        aux.setDouble(i+1, (Double)datos[i]);
                        break;
                    case "Short":
                        aux.setShort(i+1, (Short)datos[i]);
                        break;
                    case "Byte":
                        aux.setByte(i+1, (Byte)datos[i]);
                        break;
                    case "Float":
                        aux.setFloat(i+1, (Float)datos[i]);
                        break;
                    case "Boolean":
                        aux.setBoolean(i+1, (Boolean)datos[i]);
                        break;
                }
                
                
            }
            
            filas=aux.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filas;
        
    }
            
    private String[] tiposDatos(Object[] array){
        
        String tipos[]=new String[array.length];
        
        for(int i=0;i<array.length;i++){
            
            tipos[i]=devuelveTipoDato(array[i].getClass().getName());
            
        }
        
        return tipos;
        
    }
    
    private String devuelveTipoDato(String tipoCompleto){
        
        String tipo;
        
        switch(tipoCompleto){
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer": 
            case "java.lang.Long":
            case "java.lang.Double":
            case "java.lang.Float":
            case "java.lang.String":
            case "java.lang.Character":
            case "java.lang.Boolean":
                tipo=tipoCompleto.substring(10);
                break;
            default:
                tipo="Desconocido";
        }
        
        return tipo;
        
        
    }       
        
    /**
     * Cierra el ResultSet
     */
    public void cerrarResult(){
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cierra la sentencia
     */
    public void cerrarSentencia(){
        try {
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cierra la conexion
     */
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
