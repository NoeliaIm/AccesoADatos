package modelos;
// Generated 25-feb-2014 9:08:21 by Hibernate Tools 3.2.1.GA



/**
 * Hmedicos generated by hbm2java
 */
public class Hmedicos  implements java.io.Serializable {


     private int idmedico;
     private String dni;
     private String nombrem;
     private String espec;
     private Boolean eliminado;

    public Hmedicos() {
    }

	
    public Hmedicos(int idmedico) {
        this.idmedico = idmedico;
    }
    public Hmedicos(int idmedico, String dni, String nombrem, String espec, Boolean eliminado) {
       this.idmedico = idmedico;
       this.dni = dni;
       this.nombrem = nombrem;
       this.espec = espec;
       this.eliminado = eliminado;
    }
   
    public int getIdmedico() {
        return this.idmedico;
    }
    
    public void setIdmedico(int idmedico) {
        this.idmedico = idmedico;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombrem() {
        return this.nombrem;
    }
    
    public void setNombrem(String nombrem) {
        this.nombrem = nombrem;
    }
    public String getEspec() {
        return this.espec;
    }
    
    public void setEspec(String espec) {
        this.espec = espec;
    }
    public Boolean getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }




}


