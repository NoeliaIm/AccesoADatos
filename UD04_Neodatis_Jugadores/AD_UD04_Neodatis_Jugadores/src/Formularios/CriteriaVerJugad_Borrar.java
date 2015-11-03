/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Jugadores;
import Clases.Paises;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


/**
 *
 * @author Isabel
 */

public class CriteriaVerJugad_Borrar {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
        // recuperamos todos los objetos
        IQuery q = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
        Jugadores juga = (Jugadores) odb.getObjects(q).getFirst();
        Paises e = juga.getPais();
        System.out.println("\t" + juga.getNombre() + "*" + juga.getDeporte()
                + "*" + juga.getEdad() + "*" + e.getNombrepais());

        odb.delete(juga);
        IQuery q2 = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
        Objects<Jugadores> j = odb.getObjects(q);
        if (!j.hasNext()) {
            System.out.println("No existe jugador");
        } else {
            Jugadores juga2 = j.getFirst();
            e = juga.getPais();
            System.out.println("\t" + juga2.getNombre() + "*" + juga2.getDeporte()
                    + "*" + juga2.getEdad() + "*" + e.getNombrepais());
        }
        odb.close(); // Cerrar BD
    }
}
