/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author Isabel
 */
import Clases.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class ObjectValues_FuncionesGrupo {
    public static void main(String[] args) {
        
        ODB odb = ODBFactory.open("equipos.db");// Abrir BD     

        //SUMA 	     
        Values val = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).sum("edad"));
        ObjectValues ov = val.nextValues();
        BigDecimal value = (BigDecimal) ov.getByAlias("edad");
        System.out.println("Suma de edad : " + value.longValue());

        //cuenta
        Values val2 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).count("nombre"));
        ObjectValues ov2 = val2.nextValues();
        BigInteger value2 = (BigInteger) ov2.getByAlias("nombre");
        System.out.println("Numero de jugadores : " + value2.intValue());

        //media
        Values val3 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class).avg("edad"));
        ObjectValues ov3 = val3.nextValues();
        BigDecimal value3 = (BigDecimal) ov3.getByAlias("edad");
        System.out.println("Edad media : " + value3.floatValue());

        //min
        Values val4 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class)
                .min("edad", "edad_min")
                .max("edad", "edad_max"));

        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxima = (BigDecimal) ov4.getByAlias("edad_max");
        BigDecimal minima = (BigDecimal) ov4.getByAlias("edad_min");

        System.out.println("Edad maxima: " + maxima.intValue()
                + "  Edad minima: " + minima.intValue());


        //
        System.out.println("--------------------------");
        Values valores = odb.getValues(new ValuesCriteriaQuery(Jugadores.class)
                .field("nombre", "n").field("ciudad"));
        while (valores.hasNext()) {
            ObjectValues objectValues2 = (ObjectValues) valores.next();
            String nombre= (String)objectValues2.getByAlias("n");
            String ciudad= (String)objectValues2.getByIndex(1);
            System.out.println("Nombre--"+nombre + " Ciudad " + ciudad);
        }
        //

        //GROUP BY
        System.out.println("******group by ***********");
        Values groupby =
                odb.getValues(new ValuesCriteriaQuery(Jugadores.class).
                    field("ciudad").count("nombre").groupBy("ciudad"));
        //.count("nombre")
        //.avg("edad")
        //.groupBy("ciudad"));

        while (groupby.hasNext()) {
            ObjectValues objetos = (ObjectValues) groupby.next();
            System.out.println(objetos.getByAlias("ciudad")
                    + "*" + objetos.getByIndex(1));
        }
        System.out.println("*****FINAL************");
        String pais="ESPAÑA";
        int edad=15;
        Values val5 = odb.getValues(new ValuesCriteriaQuery(Jugadores.class,
                    new And().
                    add(Where.equal("pais.nombrepais", pais)).
                    add(Where.ge("edad", edad)))
                .field("nombre")
		.field("edad")
		.field("pais.nombrepais","p"));
	while (val5.hasNext()) {
		ObjectValues objectValues = (ObjectValues) val5.next();
		System.out.println("Nombre: "+ objectValues.getByAlias("nombre")
		   +" Edad: " + objectValues.getByAlias("edad") 
		   +" Pais: " + objectValues.getByAlias("p"));
        }
        odb.close(); // Cerrar BD				
        
    }
}
