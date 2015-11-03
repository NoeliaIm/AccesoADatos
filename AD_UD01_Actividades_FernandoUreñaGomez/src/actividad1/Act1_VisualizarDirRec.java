/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package act1_visualizardir;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class Act1_VisualizarDirRec {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String rutaDirectorio=JOptionPane.showInputDialog("Introduce un directorio"); 
        
        File directorio=new File(rutaDirectorio);  
        
         ficherosRecursivos(directorio, "");
    }
    
    public static void ficherosRecursivos(File f, String separador){
        File[] ficheros = f.listFiles();
                                
                for (int x=0;x<ficheros.length;x++){                    
                        System.out.println(separador + ficheros[x].getName());
                        
                        if (ficheros[x].isDirectory()){
                                String nuevo_separador;
                                nuevo_separador = separador + " ";
                                ficherosRecursivos(ficheros[x],nuevo_separador);
                        }
                }
    }
}
