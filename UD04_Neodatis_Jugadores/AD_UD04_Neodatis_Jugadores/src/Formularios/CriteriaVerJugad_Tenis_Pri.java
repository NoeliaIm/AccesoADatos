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

public class CriteriaVerJugad_Tenis_Pri {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
        // recuperamos todos los objetos
        String valor= "baloncesto";
        int ed=14;
        IQuery q1 = new CriteriaQuery(Jugadores.class, Where.equal("edad",ed));
        IQuery q2 = new CriteriaQuery(Jugadores.class, Where.equal("deporte", valor));
        Jugadores juga = (Jugadores) odb.getObjects(q1).getFirst();
        Paises e = juga.getPais();
        System.out.println("\t" + juga.getNombre() + "*" + juga.getDeporte()
                + "*" + juga.getEdad() + "*" + e.getNombrepais());
        odb.close(); // Cerrar BD
    }
}
