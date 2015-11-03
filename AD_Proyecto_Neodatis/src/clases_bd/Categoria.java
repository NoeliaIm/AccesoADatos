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
public class Categoria {
    
    private int idCategoria;
    private String descripcion;
    
    public Categoria(){}
    
    public Categoria(int pIdCategoria, String pDescripcion){
        this.idCategoria=pIdCategoria;
        this.descripcion=pDescripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
