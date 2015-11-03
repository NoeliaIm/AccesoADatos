
/* Descargado de www.javawebmas.blogspot.com  */


package combobox_departamento;



import java.sql.*;

public class ConexionBd
{   private String servidor="localhost:3306";
    private String usuario="alumno";
    private String contrasena="alumno";
    private String bd="empleados";
    private Connection conexion = null;

    /**
     * Establecemos la conexión a la base de datos identificando el Driver.
     */
    public  Connection conectar(String BD) {
        try {
            switch (BD) {
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+bd,usuario,contrasena);
                    break;
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                    conexion = DriverManager.getConnection("jdbc:oracle:thin:@LOCALHOST:1521:ORADAM2",usuario,contrasena);
                    break;
            }
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