package modelos;
// Generated 24-feb-2014 18:09:26 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Oficinas generated by hbm2java
 */
public class Oficinas  implements java.io.Serializable {


     private String codigooficina;
     private String ciudad;
     private String pais;
     private String region;
     private String codigopostal;
     private String telefono;
     private String lineadireccion1;
     private String lineadireccion2;
     private Set empleadoses = new HashSet(0);

    public Oficinas() {
    }

	
    public Oficinas(String codigooficina, String ciudad, String pais, String codigopostal, String telefono, String lineadireccion1) {
        this.codigooficina = codigooficina;
        this.ciudad = ciudad;
        this.pais = pais;
        this.codigopostal = codigopostal;
        this.telefono = telefono;
        this.lineadireccion1 = lineadireccion1;
    }
    public Oficinas(String codigooficina, String ciudad, String pais, String region, String codigopostal, String telefono, String lineadireccion1, String lineadireccion2, Set empleadoses) {
       this.codigooficina = codigooficina;
       this.ciudad = ciudad;
       this.pais = pais;
       this.region = region;
       this.codigopostal = codigopostal;
       this.telefono = telefono;
       this.lineadireccion1 = lineadireccion1;
       this.lineadireccion2 = lineadireccion2;
       this.empleadoses = empleadoses;
    }
   
    public String getCodigooficina() {
        return this.codigooficina;
    }
    
    public void setCodigooficina(String codigooficina) {
        this.codigooficina = codigooficina;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getRegion() {
        return this.region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCodigopostal() {
        return this.codigopostal;
    }
    
    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getLineadireccion1() {
        return this.lineadireccion1;
    }
    
    public void setLineadireccion1(String lineadireccion1) {
        this.lineadireccion1 = lineadireccion1;
    }
    public String getLineadireccion2() {
        return this.lineadireccion2;
    }
    
    public void setLineadireccion2(String lineadireccion2) {
        this.lineadireccion2 = lineadireccion2;
    }
    public Set getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set empleadoses) {
        this.empleadoses = empleadoses;
    }




}


