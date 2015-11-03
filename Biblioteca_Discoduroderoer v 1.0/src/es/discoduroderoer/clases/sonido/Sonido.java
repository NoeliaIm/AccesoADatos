/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.discoduroderoer.clases.sonido;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player; 
/**
 *
 * @author DiscoDurodeRoer
 */
public class Sonido {
    
    /**
    * Reproduce un archivo
    * @param rutaArchivo Ruta del archivo de sonido
    * @param tiempo Tiempo de espera
    */
   public static void reproduceVoz(File rutaArchivo, long tiempo){
        try(FileInputStream fis=new FileInputStream(rutaArchivo)){

            Player player=new Player(fis);
            Thread.sleep(tiempo);
            player.play();
            
        }catch(IOException | JavaLayerException | InterruptedException e1){
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, e1);
        }
   }

}
