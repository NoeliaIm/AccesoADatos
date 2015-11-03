package modelo;
// Generated 23-ene-2015 11:13:04 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Bmultas generated by hbm2java
 */
public class Bmultas  implements java.io.Serializable {


     private int idmulta;
     private String descmulta;
     private Short diasmin;
     private Short diasmax;
     private Integer cantidad;
     private Boolean eliminado;
     private Set bmultasprestamoses = new HashSet(0);

    public Bmultas() {
    }

	
    public Bmultas(int idmulta) {
        this.idmulta = idmulta;
    }
    public Bmultas(int idmulta, String descmulta, Short diasmin, Short diasmax, Integer cantidad, Boolean eliminado, Set bmultasprestamoses) {
       this.idmulta = idmulta;
       this.descmulta = descmulta;
       this.diasmin = diasmin;
       this.diasmax = diasmax;
       this.cantidad = cantidad;
       this.eliminado = eliminado;
       this.bmultasprestamoses = bmultasprestamoses;
    }
   
    public int getIdmulta() {
        return this.idmulta;
    }
    
    public void setIdmulta(int idmulta) {
        this.idmulta = idmulta;
    }
    public String getDescmulta() {
        return this.descmulta;
    }
    
    public void setDescmulta(String descmulta) {
        this.descmulta = descmulta;
    }
    public Short getDiasmin() {
        return this.diasmin;
    }
    
    public void setDiasmin(Short diasmin) {
        this.diasmin = diasmin;
    }
    public Short getDiasmax() {
        return this.diasmax;
    }
    
    public void setDiasmax(Short diasmax) {
        this.diasmax = diasmax;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Boolean getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
    public Set getBmultasprestamoses() {
        return this.bmultasprestamoses;
    }
    
    public void setBmultasprestamoses(Set bmultasprestamoses) {
        this.bmultasprestamoses = bmultasprestamoses;
    }




}

