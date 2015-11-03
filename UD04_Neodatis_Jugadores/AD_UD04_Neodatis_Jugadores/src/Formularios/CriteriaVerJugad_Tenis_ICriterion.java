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
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


/**
 *
 * @author Isabel
 */

public class CriteriaVerJugad_Tenis_ICriterion {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
        
       	ICriterion criterio = Where.not(Where.like("nombre","M%"));

        IQuery q= new CriteriaQuery(Jugadores.class,criterio);
        Objects<Jugadores> juga = odb.getObjects(q);
        System.out.println(juga.size() + " Jugadores:");

        while (juga.hasNext()) {
            Jugadores j = (Jugadores) juga.next();
            Paises e = j.getPais();
            System.out.println("\t" + j.getNombre()+"*"+j.getCiudad() + "*" + j.getDeporte()
                    + "*" + j.getEdad() + "*" + e.getNombrepais());
        }

        odb.close(); // Cerrar BD
    }
}
