/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_act11_leerficheroarticulosxs;

import com.thoughtworks.xstream.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class Act11_CrearXMLArticulosXS_FernandoUrenaGomez_PC02 {
    
    private ListaArticulos listaArt;
    
    public Act11_CrearXMLArticulosXS_FernandoUrenaGomez_PC02(){
        
        listaArt= new ListaArticulos();
        
    }
    
    
    public void copiaInformacionFichero(String nombreFicheroOriginal){
        
        //Copiamos toda la informacion a la lista de articulos
        try (ObjectInputStream FicheroArticulos=new ObjectInputStream(new FileInputStream(new File(nombreFicheroOriginal)))){
            while (true) {
                Articulo articulo=(Articulo) FicheroArticulos.readObject();
                listaArt.anadir(articulo);
            }
        }catch (EOFException eo) {
            
        } catch (IOException ex) {
            Logger.getLogger(Act11_LeerXMLArticulosXS_FernandoUrenaGomez_PC02.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Act11_LeerXMLArticulosXS_FernandoUrenaGomez_PC02.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void creaFichero(String nombreFichero){
        
        copiaInformacionFichero("articulos.dat");
        
        try {
            XStream xstream = new XStream();
            //cambiar de nombre a las etiquetas XML
            //xstream.alias("Articulos", ListaArticulos.class);
            xstream.alias("Articulos", listaArt.getClass());
            xstream.alias("Articulo", Articulo.class);
            //quitar etiqueta lista
            xstream.addImplicitCollection(ListaArticulos.class, "lista");
            //Insrtar los objetos en el XML
            xstream.toXML(listaArt, new FileOutputStream(nombreFichero));
            System.out.println("Creado fichero XML....");
            }catch (Exception e){
                {e.printStackTrace();}
            } 
    }
}
