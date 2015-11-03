/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1º.ficheros_pc02_fernandureñagomez;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Peliculas implements Serializable{
 
    private int idPelicula;
    private String titulo;
    private int anioProduccion;
    private String director;
    private String nacionalidad;
    private int presupuesto;
    private char tipoPelicula;

    public Peliculas(){
        
    }
    
    public Peliculas(int pIdPelicula, String pTitulo, int pAnioProduccion, String pDirector, String pNacionalidad, int pPresupuesto, char pTipoPelicula){
        
        this.setIdPelicula(pIdPelicula);
        this.setTitulo(pTitulo);
        this.setAnioProduccion(pAnioProduccion);
        this.setDirector(pDirector);
        this.setNacionalidad(pNacionalidad);
        this.setTipoPelicula(pTipoPelicula);
        
    }
    
    
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioProduccion() {
        return anioProduccion;
    }

    public void setAnioProduccion(int anioProduccion) {
        this.anioProduccion = anioProduccion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public char getTipoPelicula() {
        return tipoPelicula;
    }

    public void setTipoPelicula(char tipoPelicula) {
        this.tipoPelicula = tipoPelicula;
    }
    
    
    
    
    
}
