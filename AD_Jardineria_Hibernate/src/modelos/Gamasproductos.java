package modelos;
// Generated 24-feb-2014 18:09:26 by Hibernate Tools 3.6.0


import java.sql.Blob;
import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Gamasproductos generated by hbm2java
 */
public class Gamasproductos  implements java.io.Serializable {


     private String gama;
     private Clob descripciontexto;
     private Clob descripcionhtml;
     private Blob imagen;
     private Set productoses = new HashSet(0);

    public Gamasproductos() {
    }

	
    public Gamasproductos(String gama) {
        this.gama = gama;
    }
    public Gamasproductos(String gama, Clob descripciontexto, Clob descripcionhtml, Blob imagen, Set productoses) {
       this.gama = gama;
       this.descripciontexto = descripciontexto;
       this.descripcionhtml = descripcionhtml;
       this.imagen = imagen;
       this.productoses = productoses;
    }
   
    public String getGama() {
        return this.gama;
    }
    
    public void setGama(String gama) {
        this.gama = gama;
    }
    public Clob getDescripciontexto() {
        return this.descripciontexto;
    }
    
    public void setDescripciontexto(Clob descripciontexto) {
        this.descripciontexto = descripciontexto;
    }
    public Clob getDescripcionhtml() {
        return this.descripcionhtml;
    }
    
    public void setDescripcionhtml(Clob descripcionhtml) {
        this.descripcionhtml = descripcionhtml;
    }
    public Blob getImagen() {
        return this.imagen;
    }
    
    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }
    public Set getProductoses() {
        return this.productoses;
    }
    
    public void setProductoses(Set productoses) {
        this.productoses = productoses;
    }




}

