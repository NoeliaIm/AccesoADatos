/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Conexion.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Isabel
 */
public class ObjetosConsultasListOracle {
    public static void main(String[] args) {
        SessionFactory instancia =
                HibernateUtil.getSessionFactory();
        Session sesion = instancia.openSession();
        Query cons = sesion.createQuery("select em.departamentos.deptNo,count(em.empNo),"
                + "avg(em.salario), em.departamentos.dnombre "
                + "from Empleados as em "
                + " group by em.departamentos.deptNo,em.departamentos.dnombre ");
        List<Object[]> filas = cons.list(); //Todas las filas
        for (int i = 0; i < filas.size(); i++){
            Object[] filaActual = filas.get(i); //Acceso a una fila
            System.out.println("Dep:" + filaActual[0] + "* Nombre:" + filaActual[3] + " * "+
                    "Media: "+filaActual[2] + "*Empleados:" + filaActual [1]);
        }
        sesion.close();
    }
}
