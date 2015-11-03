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
import org.neodatis.odb.Objects;

public class EjemploCliente {	
    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.openClient("localhost", 8000, "base1");

            Paises p = new Paises("ESPAÑA", 1);

            Jugadores j4 = new Jugadores("Andrea", "padel", "Guadalajara",10, p);
            odb.store(j4);
            Objects<Jugadores> juga = odb.getObjects(Jugadores.class);
		System.out.println(juga.size() + " Jugadores:");

		while (juga.hasNext()) {
			Jugadores j = (Jugadores) juga.next();
			Paises e = j.getPais();
			System.out.println("\t" + j.getNombre() + "*" + j.getDeporte()
					+ "*" + j.getEdad() + "*" + e.getNombrepais());
		}

        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }//--main
}
