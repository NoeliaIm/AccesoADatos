package modelos;
// Generated 25-feb-2014 9:08:21 by Hibernate Tools 3.2.1.GA



/**
 * Hanalrealiz generated by hbm2java
 */
public class Hanalrealiz  implements java.io.Serializable {


     private int idanalrealiz;
     private Integer idpaciente;
     private Integer idanalisis;
     private Integer idmedico;
     private int fechaanalisis;
     private char resultado;
     private boolean eliminado;

    public Hanalrealiz() {
    }

	
    public Hanalrealiz(int idanalrealiz, int fechaanalisis, char resultado, boolean eliminado) {
        this.idanalrealiz = idanalrealiz;
        this.fechaanalisis = fechaanalisis;
        this.resultado = resultado;
        this.eliminado = eliminado;
    }
    public Hanalrealiz(int idanalrealiz, Integer idpaciente, Integer idanalisis, Integer idmedico, int fechaanalisis, char resultado, boolean eliminado) {
       this.idanalrealiz = idanalrealiz;
       this.idpaciente = idpaciente;
       this.idanalisis = idanalisis;
       this.idmedico = idmedico;
       this.fechaanalisis = fechaanalisis;
       this.resultado = resultado;
       this.eliminado = eliminado;
    }
   
    public int getIdanalrealiz() {
        return this.idanalrealiz;
    }
    
    public void setIdanalrealiz(int idanalrealiz) {
        this.idanalrealiz = idanalrealiz;
    }
    public Integer getIdpaciente() {
        return this.idpaciente;
    }
    
    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }
    public Integer getIdanalisis() {
        return this.idanalisis;
    }
    
    public void setIdanalisis(Integer idanalisis) {
        this.idanalisis = idanalisis;
    }
    public Integer getIdmedico() {
        return this.idmedico;
    }
    
    public void setIdmedico(Integer idmedico) {
        this.idmedico = idmedico;
    }
    public int getFechaanalisis() {
        return this.fechaanalisis;
    }
    
    public void setFechaanalisis(int fechaanalisis) {
        this.fechaanalisis = fechaanalisis;
    }
    public char getResultado() {
        return this.resultado;
    }
    
    public void setResultado(char resultado) {
        this.resultado = resultado;
    }
    public boolean isEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }




}


