/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Clases;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Lista<T>{
        
    private ArrayList<T> lista;

    public Lista(){
        lista = new ArrayList<>();
    }

    public void anadir(T o) {
        lista.add(o);
    }

    public ArrayList<T> getLista(){
        return lista;
    }
}
