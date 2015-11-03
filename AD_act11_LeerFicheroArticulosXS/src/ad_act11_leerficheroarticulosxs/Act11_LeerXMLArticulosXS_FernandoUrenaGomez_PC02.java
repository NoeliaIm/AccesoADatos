/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_act11_leerficheroarticulosxs;

import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Fernando
 */
public class Act11_LeerXMLArticulosXS_FernandoUrenaGomez_PC02 {
    
    private XStream xstream;
    private JTextArea textArea;
    
    
    public Act11_LeerXMLArticulosXS_FernandoUrenaGomez_PC02(JTextArea textArea){
        xstream = new XStream();
        this.textArea=textArea;
    }
    
    public void mostrarFichero(String nombreXML){
        
        //cambiar de nombre a las etiquetas XML
        xstream.alias("Articulos", ListaArticulos.class); 
        xstream.alias("Articulo", Articulo.class);
        xstream.addImplicitCollection(ListaArticulos.class, "lista");
        
        ListaArticulos listadoTodas = null;
        try {
            listadoTodas = (ListaArticulos) xstream.fromXML(new FileInputStream(nombreXML));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Act11_LeerXMLArticulosXS_FernandoUrenaGomez_PC02.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
        listaArticulos = (ArrayList<Articulo>) listadoTodas.getListaPersonas();
        
        
        Iterator iterador = listaArticulos.listIterator();
        //Recorro la lista con el iterador que acabo de crear
        while( iterador.hasNext() ) {
            Articulo  art= (Articulo) iterador.next();
            //Obtengo el elemento contenido
            textArea.append("ID: "+art.getId()+"\nNombre: "+art.getNombre()+"\nPrecio: "+art.getPrecio()+"\nCantidad Almacen: "+art.getCantAlmacen()+"\n");
        }
    }

}   
        

