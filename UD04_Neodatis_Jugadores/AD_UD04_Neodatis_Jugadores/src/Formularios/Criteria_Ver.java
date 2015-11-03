/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.Jugadores;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;


/**
 *
 * @author Isabel
 */

public class Criteria_Ver {

public static void main(String[] args) {
    ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
    // recuperamos todos los objetos

    Values valores = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).field("nombre").field("ciudad"));
    while (valores.hasNext()) {
        ObjectValues objectValues = (ObjectValues) valores.next();
        System.out.println(objectValues.getByAlias("nombre")
                + " ciudad" + objectValues.getByIndex(1));
    }

    odb.close(); // Cerrar BD
}
}
