/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author Isabel
 */
import Clases.Paises;
import Clases.Jugadores;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Primera {

    public static void main(String[] args) {
        // Crear instancias para almacenar en BD
        String dep="Padel";
        
        ODB odb = ODBFactory.open("EQUIPOS.DB");// Abrir BD
        try{
            IQuery q= new CriteriaQuery(Jugadores.class,Where.equal("deporte",dep.toLowerCase()))
                    .orderByAsc("nombre");
            Objects<Jugadores> juga = odb.getObjects(q);
            if (!juga.hasNext()){
                System.out.println("Base de Datos vacía");

            }
            else{
            System.out.println("Hay "+juga.size()+" jugadores de "+dep.toLowerCase());
            while (juga.hasNext()){
                Jugadores j=juga.next();
                System.out.println("Nombre: "+j.getNombre()+"Pais: "+j.getPais().getNombrepais());
            }
        }
                
        }
        finally{
            odb.close(); // Cerrar BD				
        }

    }//fin main
}//fin clase

