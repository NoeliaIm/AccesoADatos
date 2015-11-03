/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alumno
 */
public class InformeConectores {
    
    private String nombreFichero; //sin extension
    private ConexionDB conexion;
    
    public InformeConectores(String nombreFichero, ConexionDB conexion){
        
        this.nombreFichero=nombreFichero;
        this.conexion = conexion;
        
    }
    
    //posicion 0 = nombreParametros
    //posicion 1 = ValorParametro
    //null si no tiene parametros
    public void crearInforme(Object[][] parametros){
        
        try {
            String archivo=getClass().getResource(nombreFichero+".jrxml").getPath();
            archivo=archivo.replace("%20"," ");
            JasperReport report = JasperCompileManager.compileReport(archivo);
            
            Map parametro=new HashMap();
            
            if (parametros!=null){
                for(int i=0;i<parametros.length;i++){
                    parametro.put(parametros[i][0],parametros[i][1]);
                } 
            }
            
            JasperPrint print = JasperFillManager.fillReport(report, parametro, conexion.getconexion());
            JasperViewer.viewReport(print, false);
            
        } catch (JRException jRException) {
            System.out.println(jRException.getMessage());
        }
        
        
    }
    
}
