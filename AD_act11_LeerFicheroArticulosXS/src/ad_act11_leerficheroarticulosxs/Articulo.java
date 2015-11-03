/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_act11_leerficheroarticulosxs;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Articulo implements Serializable{
    
    private int id;
    private String nombre;
    private double precio;
    private int cantAlmacen;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantAlmacen() {
        return cantAlmacen;
    }

    public void setCantAlmacen(int cantAlmacen) {
        this.cantAlmacen = cantAlmacen;
    }
    
    @Override
    public String toString(){
        return "ID: "+getId()+" \n"+
               "Nombre: "+getNombre()+" \n"+
               "Precio: "+getPrecio()+" \n"+
               "Cantidad Almacen: "+getCantAlmacen()+" \n";
    }
    
    public Articulo(int id, String nombre, double precio, int cantAlmacen){
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setCantAlmacen(cantAlmacen);
    }
    
    public Articulo(){
        
    }
    
}
