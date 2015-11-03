/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidor;

/**
 *
 * @author Isabel
 */
import Formularios.*;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import Clases.Paises;
import Clases.Jugadores;

public class verBD {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
		// recuperamos todos los objetos
		Objects<Paises> equipos = odb.getObjects(Paises.class);
		System.out.println(equipos.size() + " Equipos:");

		// visualizar los objetos
		while (equipos.hasNext()) {
			Paises eq = equipos.next();
			System.out.println("\tID: " + eq.getId() + " ==> " + 
					eq.getNombrepais());
		}

		Objects<Jugadores> juga = odb.getObjects(Jugadores.class);
		System.out.println(juga.size() + " Jugadores:");

		while (juga.hasNext()) {
			Jugadores j = (Jugadores) juga.next();
			Paises e = j.getPais();
			System.out.println("\t" + j.getNombre() + "*" + j.getDeporte()
					+ "*" + j.getEdad() + "*" + e.getNombrepais());
		}

		odb.close(); // Cerrar BD
	}// fin main
}

