package modelos;
// Generated 21-feb-2014 23:39:39 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * ExZonas generated by hbm2java
 */
public class ExZonas  implements java.io.Serializable {


     private int idzona;
     private String nombreZona;
     private Boolean eliminado;
     private Set exVentases = new HashSet(0);
     private Set exVendedoreses = new HashSet(0);

    public ExZonas() {
    }

	
    public ExZonas(int idzona) {
        this.idzona = idzona;
    }
    public ExZonas(int idzona, String nombreZona, Boolean eliminado, Set exVentases, Set exVendedoreses) {
       this.idzona = idzona;
       this.nombreZona = nombreZona;
       this.eliminado = eliminado;
       this.exVentases = exVentases;
       this.exVendedoreses = exVendedoreses;
    }
   
    public int getIdzona() {
        return this.idzona;
    }
    
    public void setIdzona(int idzona) {
        this.idzona = idzona;
    }
    public String getNombreZona() {
        return this.nombreZona;
    }
    
    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }
    public Boolean getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
    public Set getExVentases() {
        return this.exVentases;
    }
    
    public void setExVentases(Set exVentases) {
        this.exVentases = exVentases;
    }
    public Set getExVendedoreses() {
        return this.exVendedoreses;
    }
    
    public void setExVendedoreses(Set exVendedoreses) {
        this.exVendedoreses = exVendedoreses;
    }




}


