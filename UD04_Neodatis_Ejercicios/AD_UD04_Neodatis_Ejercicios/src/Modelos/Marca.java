/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Isabel
 */
public class Marca {
    private Integer IdMarca;
    private String  NombreMarca;
    
    public void setMarca(String m){
        NombreMarca=m;
    }
    public String getMarca(){
        return NombreMarca;
    }
    
    public void setId(Integer i){
        IdMarca=i;
    }
    public Integer getId(){
        return IdMarca;
    }
}
