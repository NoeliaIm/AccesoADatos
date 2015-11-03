
/* Descargado de www.javawebmas.blogspot.com  */
package ud02_jtable_visualizaremple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {

    private Connection conexion = null;

    /**
     * Establecemos la conexión a la base de datos identificando el Driver.
     */
    public Connection conectar(String driver, String servidor, String bd, String usuario, String clave) {
        try {
            switch (driver) {
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:mysql://" + servidor + "/" + bd, usuario, clave);
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + servidor + ":1521:" + bd, usuario, clave);
                    break;
            }
            System.out.println("Conexión Realizada");
            return conexion;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Error en la Conexión: " + e.getMessage());
            return null;
        }
    }

    public void cerrar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
