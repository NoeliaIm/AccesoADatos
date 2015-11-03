/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_act11_leerficheroarticulosxs;

/**
 *
 * @author Fernando
 */
import java.util.ArrayList;
import java.util.List;
public class ListaArticulos {
    
    private ArrayList<Articulo> lista;
    
    public ListaArticulos(){
        lista = new ArrayList<>();
    }
    
    public void anadir(Articulo art) {
        lista.add(art);
    }
    
    public List<Articulo> getListaPersonas(){
        return lista;
    }
}
