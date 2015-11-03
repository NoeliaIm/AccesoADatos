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
public class Traducciones {
    
    private int idTraduccion;
    private String palabraOrigen;
    private String palabraMeta;
    private Idiomas idiomas;
    private String descripcion;
    private Categoria categoria; 

    public Traducciones(){}
    
    public Traducciones(int pIdTraduccion, String pPalabraOrigen, String pPalabraMeta,Idiomas pIdioma, String pDescripcion, Categoria pCategoria){
        
        this.idTraduccion=pIdTraduccion;
        this.palabraOrigen=pPalabraOrigen;
        this.palabraMeta=pPalabraMeta;
        this.idiomas=pIdioma;
        this.descripcion=pDescripcion;
        this.categoria=pCategoria;
        
    }
    
    //Sin categoria
    public Traducciones(int pIdTraduccion, String pPalabraOrigen, String pPalabraMeta,Idiomas pIdioma, String pDescripcion){
        
        this.idTraduccion=pIdTraduccion;
        this.palabraOrigen=pPalabraOrigen;
        this.palabraMeta=pPalabraMeta;
        this.idiomas=pIdioma;
        this.descripcion=pDescripcion;
        
    }
    
    public Traducciones(String pPalabraOrigen, String pPalabraMeta,Idiomas pIdioma, String pDescripcion){
        
        this.idTraduccion=-1; //Cuidado
        this.palabraOrigen=pPalabraOrigen;
        this.palabraMeta=pPalabraMeta;
        this.idiomas=pIdioma;
        this.descripcion=pDescripcion;
    }
    
    public Traducciones(String pPalabraOrigen, String pPalabraMeta,Idiomas pIdioma, String pDescripcion, Categoria pCategoria){
        
        this.idTraduccion=-1; //Cuidado
        this.palabraOrigen=pPalabraOrigen;
        this.palabraMeta=pPalabraMeta;
        this.idiomas=pIdioma;
        this.descripcion=pDescripcion;
        this.categoria=pCategoria;
    }   

    public int getIdTraduccion() {
        return idTraduccion;
    }

    public void setIdTraduccion(int idTraduccion) {
        this.idTraduccion = idTraduccion;
    }
    
    public String getPalabraOrigen() {
        return palabraOrigen;
    }

    public void setPalabraOrigen(String palabraOrigen) {
        this.palabraOrigen = palabraOrigen;
    }

    public String getPalabraMeta() {
        return palabraMeta;
    }

    public void setPalabraMeta(String palabraMeta) {
        this.palabraMeta = palabraMeta;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas (Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean equals(Traducciones t){
        
        boolean igual=false;
        //Dos traducciones son iguales cuando su palabra original, palabra traducida,categoria y idioma son iguales
        if(palabraOrigen.equals(t.getPalabraOrigen()) 
                && palabraMeta.equals(t.getPalabraMeta())  
                && idiomas.getIdioma().equals(t.getIdiomas().getIdioma())){
                igual=true;
        }

        return igual;
    }
    
    @Override
    public String toString(){
      
        if(categoria!=null){
            return palabraOrigen+"-"+palabraMeta+" "+"("+categoria.getDescripcion()+")"+" "+idiomas.getIdioma();
        }
        
        return palabraOrigen+"-"+palabraMeta+" "+"()"+" "+idiomas.getIdioma();
        
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
