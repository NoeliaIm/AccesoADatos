/*
 * USO DEL PATRÓN SINGLETON 
 * PARA LA CONEXIÓN
 * PARÁMETROS:
 *      cadena: "oracle" para conectar a Oracle
 *              "mysql" para conestar a MySQL
 */
package conexion;

/**
 *
 * @author Isabel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionSingleton {

    public static Connection conexion; // atributo para guardar el objeto conexión
    private static ConexionSingleton INSTANCE = null;

    /**
     * Método constructor que ejecuta el método para conectar con la base de
     * datos
     *
     */
    private ConexionSingleton(String cadena, String host, String base, String usuario, String clave) {
        conectar(cadena, host, base, usuario, clave);
    }

    /**
     * Crea una instancia de la base de datos en caso de no estar creada.
     */
    private synchronized static void crearInstancia(String cadena, String host, String base, String usuario, String clave) {
        if (INSTANCE == null) {
            INSTANCE = new ConexionSingleton(cadena, host, base, usuario, clave);
        }
    }

    /**
     * Metodo para retorna una instancia de la conexion. Si no esta creada la
     * crea, y si esta creada la retorna
     *
     * @return retorna una instancia de la conexión a la base de datos
     */
    public static ConexionSingleton getInstance(String cadena, String host, String base, String usuario, String clave) {
        if (INSTANCE == null) {
            crearInstancia(cadena, host, base, usuario, clave);
        }
        return INSTANCE;
    }

    /**
     * Método que crea la conexión a la base de datos
     */
    public void conectar(String cadena, String host, String base, String usuario, String clave) {
        try { // preparamos la conexión
            switch (cadena.toLowerCase()) {
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + base, usuario, clave);
                    System.out.println("Conexión Realizada");
                    break;

                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521:" + base, usuario, clave);
                    System.out.println("Conexión Realizada");
                    break;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Error al abrir la conexión.");
        }
    }

    /**
     * Método para eliminar la instancia de la conexión
     *
     */
    public static void cerrar() {
        INSTANCE = null;
        cerrarConexion();
    }

    /**
     * Método para cerrar la conexión con la base de dades
     *
     */
    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }
}
