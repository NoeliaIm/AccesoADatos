/*
 * USO DEL PATRÓN SINGLETON 
 * PARA LA CONEXIÓN
 */
package conexionunica;

/**
 *
 * @author Isabel
 */

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public final class Conexion {
 
    public static Connection conexion; // atributo para guardar el objeto conexión
    private static Conexion INSTANCE = null;
 
    /**Método constructor que ejecuta el método para conectar con la base de datos
     *
     */
    private Conexion(String cadena,String host,String bd,String usuario,String clave) {
        conectar(cadena,host,bd,usuario, clave);
    }
 
 
    /**Crea una instancia de la base de datos en caso de no estar creada.
     */
    private synchronized static void createInstance(String cadena,String host,String bd,String usuario,String clave) {
        if (INSTANCE == null) {
            INSTANCE = new Conexion(cadena,host,bd,usuario, clave);
        }
    }
 
    /**Metodo para retorna una instancia de la conexion. Si no esta creada la crea, y si esta creada la retorna
     * @return retorna una instancia de la conexión a la base de datos
     */
    public static Conexion getInstance(String cadena,String host,String bd,String usuario,String clave) {
        if (INSTANCE == null) {
            createInstance(cadena,host,bd,usuario, clave);
        }
        return INSTANCE;
    }
 

    /**Método que crea la conexión a la base de datos     */
    
    public void conectar(String cadena,String host,String bd,String usuario,String clave) {
 
        cadena=cadena.toLowerCase();
        try { // preparamos la conexión
            switch (cadena.toLowerCase()) {
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+bd,usuario,clave);
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":1521:"+bd,usuario,clave);
                    break;
            }
            System.out.println("Conexión Realizada");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Error al abrir la conexión.");
        }
    }

     /**Método para eliminar la instancia de la conexión
     *
     */
    public void cerrar() {
        INSTANCE = null;
        closeConnection();
    }
 
 
    /**Método para cerrar la conexión con la base de dades
     *
     */
    public static void closeConnection() {
        try {
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }

    
} 