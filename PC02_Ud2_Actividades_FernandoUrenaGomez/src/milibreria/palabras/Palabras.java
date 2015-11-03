/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.palabras;

import java.util.StringTokenizer;

/**
 *
 * @author Fernando
 */
public class Palabras {
    
    public static int numeroPalabras(String cadena){
        
        StringTokenizer texto = new StringTokenizer(cadena);
        
        return texto.countTokens();
       
    }
    
    public static int numeroCaracteres(String cadena, char caracter){
        
        int contador=0;
        
        for(int i=0;i<cadena.length();i++){
            
            char caracterActual=cadena.charAt(i);
            
            if(caracter==caracterActual){
                contador++;
            }
            
        }
        
        return contador;
        
    }
    
    
    
}
