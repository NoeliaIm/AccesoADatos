/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases_bd;

/**
 *
 * @author Fernando
 */
public class Idiomas {
    
    private int idIdioma;
    private String idioma;
    
    public Idiomas(){}
    
    public Idiomas(int pIdIdioma, String pIdioma){
        this.idIdioma=pIdIdioma;
        this.idioma=pIdioma;
    }
    
    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
}
