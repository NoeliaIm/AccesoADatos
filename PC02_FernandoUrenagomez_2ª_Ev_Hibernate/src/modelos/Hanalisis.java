package modelos;
// Generated 25-feb-2014 9:08:21 by Hibernate Tools 3.2.1.GA



/**
 * Hanalisis generated by hbm2java
 */
public class Hanalisis  implements java.io.Serializable {


     private int idanalisis;
     private String descripcion;
     private boolean eliminado;

    public Hanalisis() {
    }

    public Hanalisis(int idanalisis, String descripcion, boolean eliminado) {
       this.idanalisis = idanalisis;
       this.descripcion = descripcion;
       this.eliminado = eliminado;
    }
   
    public int getIdanalisis() {
        return this.idanalisis;
    }
    
    public void setIdanalisis(int idanalisis) {
        this.idanalisis = idanalisis;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }




}


