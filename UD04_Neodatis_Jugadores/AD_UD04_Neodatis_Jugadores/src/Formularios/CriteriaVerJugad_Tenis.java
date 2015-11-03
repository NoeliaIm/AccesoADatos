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

public class CriteriaVerJugad_Tenis {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
        
        
        IQuery q= new CriteriaQuery(Jugadores.class,Where.equal("deporte","tenis"));
        Objects<Jugadores> juga = odb.getObjects(q);
        System.out.println(juga.size() + " Jugadores:");

        while (juga.hasNext()) {
            Jugadores j = (Jugadores) juga.next();
            Paises e = j.getPais();
            System.out.println("\t" + j.getNombre() + "*" + j.getDeporte()
                    + "*" + j.getEdad() + "*" + e.getNombrepais());
        }

        odb.close(); // Cerrar BD
    }
}
