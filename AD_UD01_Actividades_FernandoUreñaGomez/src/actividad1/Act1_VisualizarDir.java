/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package act1_visualizardir;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumno
 */
public class Act1_VisualizarDir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String rutaDirectorio=JOptionPane.showInputDialog("Introduce un directorio"); 
        
        File directorio=new File(rutaDirectorio);
        if (!directorio.exists()){
            JOptionPane.showMessageDialog(null, "Error, el directorio no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            File[] lista=directorio.listFiles();
        
            for(int i=0;i<lista.length;i++){
                if(lista[i].isFile()){
                    System.out.println(lista[i].getName());   
                }
            
            }   
        }
        
    }
}
