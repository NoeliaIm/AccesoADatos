package clases;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Properties;

public class Idioma extends Properties{
    
    private static final long serialVersionUID = 1L;
	        
    public Idioma(String idioma){
    	
        //Modificar si quieres añadir mas idiomas
    	switch(idioma){
	    	case "Español":
                    getProperties("espanol.properties");
                    break;
	    	case "Inglés":
                    getProperties("ingles.properties");
                    break;
                case "Frances":
                    getProperties("frances.properties");
                    break;
	    	default:
                    getProperties("espanol.properties");
	   	}

    }

    private void getProperties(String idioma) {
        try {
            this.load( getClass().getResourceAsStream(idioma) );
        } catch (IOException ex) {
        
        }
   }
}
