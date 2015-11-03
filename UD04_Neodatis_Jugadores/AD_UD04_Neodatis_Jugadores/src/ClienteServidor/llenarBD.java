/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidor;

/**
 *
 * @author Isabel
 */

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class llenarBD {

    public static void main(String[] args) {
        // Crear instancias para almacenar en BD

        Paises eq1 = new Paises("ESPAÑA", 1);
        Paises eq2 = new Paises("FRANCIA", 2);
        Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, eq1);
        Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, eq1);
        Jugadores j3 = new Jugadores("Mario",  "baloncesto", "Guadalajara", 15, eq1);
        Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14, eq1);
        Jugadores j5 = new Jugadores("Enzo", "padel", "Paris", 14, eq2);
        Jugadores j6 = new Jugadores("Michel", "padel", "Rennes", 16, eq2);

        ODB odb = ODBFactory.open("F:/NEODATIS/EQUIPOS.DB");// Abrir BD

        odb.store(eq1);
        //odb.store(eq2);

        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        
        odb.store(eq2);
        
        odb.store(j4);
        odb.store(j5);
        odb.store(j6);

        odb.close(); // Cerrar BD				
    }//fin main
}//fin clase

