/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidor;

/**
 *
 * @author Isabel
 */
public class Paises implements java.io.Serializable{
    private int id;
    private String nombrepais;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Paises() {
    }
    public Paises(String nombrepais, int id) {
        this.nombrepais = nombrepais;
        this.id = id;
    }
    public String getNombrepais() {
        return this.nombrepais;
    }
    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }
    public String toString() {
        return nombrepais;
    }
}
