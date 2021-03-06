package modelo;
// Generated 23-ene-2015 11:13:04 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Bprestamos generated by hbm2java
 */
public class Bprestamos  implements java.io.Serializable {


     private int idprestamo;
     private Blibros blibros;
     private Bsocios bsocios;
     private Integer fechasalida;
     private Integer fechamaxima;
     private Integer fechaentrega;
     private Character devuelto;
     private Boolean eliminado;
     private Set bmultasprestamoses = new HashSet(0);

    public Bprestamos() {
    }

	
    public Bprestamos(int idprestamo) {
        this.idprestamo = idprestamo;
    }
    public Bprestamos(int idprestamo, Blibros blibros, Bsocios bsocios, Integer fechasalida, Integer fechamaxima, Integer fechaentrega, Character devuelto, Boolean eliminado, Set bmultasprestamoses) {
       this.idprestamo = idprestamo;
       this.blibros = blibros;
       this.bsocios = bsocios;
       this.fechasalida = fechasalida;
       this.fechamaxima = fechamaxima;
       this.fechaentrega = fechaentrega;
       this.devuelto = devuelto;
       this.eliminado = eliminado;
       this.bmultasprestamoses = bmultasprestamoses;
    }
   
    public int getIdprestamo() {
        return this.idprestamo;
    }
    
    public void setIdprestamo(int idprestamo) {
        this.idprestamo = idprestamo;
    }
    public Blibros getBlibros() {
        return this.blibros;
    }
    
    public void setBlibros(Blibros blibros) {
        this.blibros = blibros;
    }
    public Bsocios getBsocios() {
        return this.bsocios;
    }
    
    public void setBsocios(Bsocios bsocios) {
        this.bsocios = bsocios;
    }
    public Integer getFechasalida() {
        return this.fechasalida;
    }
    
    public void setFechasalida(Integer fechasalida) {
        this.fechasalida = fechasalida;
    }
    public Integer getFechamaxima() {
        return this.fechamaxima;
    }
    
    public void setFechamaxima(Integer fechamaxima) {
        this.fechamaxima = fechamaxima;
    }
    public Integer getFechaentrega() {
        return this.fechaentrega;
    }
    
    public void setFechaentrega(Integer fechaentrega) {
        this.fechaentrega = fechaentrega;
    }
    public Character getDevuelto() {
        return this.devuelto;
    }
    
    public void setDevuelto(Character devuelto) {
        this.devuelto = devuelto;
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


