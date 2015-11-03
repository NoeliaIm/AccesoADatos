/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author Isabel
 */
import Conexion.HibernateUtil;
import Modelo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class VisEmpleDept {

    public static void VisualizarEmpleado(Short pempNo) {
        SessionFactory instancia = HibernateUtil.getSessionFactory();
        Session sesion = instancia.openSession();
        Empleados e = (Empleados) sesion.createQuery("from Empleados where empNo=? ")
                .setShort(0, pempNo).uniqueResult();
        // Se accede al Nombre del Departamento(dNombre a través del objeto Departamentos
        if (e != null) {
            System.out.println("Nombre :" + e.getApellido() + "--Departamento:"
                    + e.getDepartamentos().getDnombre());
        } else {
            System.out.println("No existe el empleado");
        }
    }

    public static void main(String args[]) {
        Short empNo = 7369;
        VisualizarEmpleado(empNo);
    }
}
