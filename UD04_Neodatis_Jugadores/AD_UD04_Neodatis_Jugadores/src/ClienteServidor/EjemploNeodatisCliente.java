/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidor;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.ODBServer;

/**
 *
 * @author Isabel
 */
public class EjemploNeodatisCliente {
public static void main(String[] args) {
     ODB odb = null;
     ODBServer server = null;
     try {
      // Crea el servidor en el Puerto 80
      server = ODBFactory.openServer(8000);
      // BD a usar y el nombre que se usará para refererirse a ella
      server.addBase("base", "D:/NEODATIS/EQUIPOS.DB");
      // Se ejecuta el servidor 
      server.startServer(true);	
	 
      // Se abre la BD 
      odb = server.openClient("base");

      // Se llama al método que visualize los datos 
	VisualizaDatos(odb);
	 	 
	} finally {
        if (odb != null) {
          // Primero se cierra el cliente
          odb.close();        
        }
        if (server != null) {
          // Y luego el servidor
          server.close();
        }
	}	    
    }
static void VisualizaDatos(ODB odb){
      //recuperamos todos los objetos
      Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
      System.out.println(objects.size() + " Jugadores:");
        
      int i = 1;
      // visualizar los objetos		
      while(objects.hasNext()){
	   Jugadores jug = objects.next();
           Paises p=jug.getPais();
         System.out.println((i++) + "\t: " + jug.getNombre() + "*" + 
              jug.getDeporte()+ "t*" + jug.getCiudad()+ "*" + jug.getEdad()+"* Pais: "+p.getNombrepais());
      }  
   }
}

