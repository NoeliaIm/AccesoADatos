package modelos;
// Generated 21-feb-2014 23:39:39 by Hibernate Tools 3.6.0



/**
 * ExVentas generated by hbm2java
 */
public class ExVentas  implements java.io.Serializable {


     private int idventa;
     private ExArticulos exArticulos;
     private ExZonas exZonas;
     private ExVendedores exVendedores;
     private Integer fechaVenta;
     private Short unidades;
     private Boolean eliminado;

    public ExVentas() {
    }

	
    public ExVentas(int idventa) {
        this.idventa = idventa;
    }
    public ExVentas(int idventa, ExArticulos exArticulos, ExZonas exZonas, ExVendedores exVendedores, Integer fechaVenta, Short unidades, Boolean eliminado) {
       this.idventa = idventa;
       this.exArticulos = exArticulos;
       this.exZonas = exZonas;
       this.exVendedores = exVendedores;
       this.fechaVenta = fechaVenta;
       this.unidades = unidades;
       this.eliminado = eliminado;
    }
   
    public int getIdventa() {
        return this.idventa;
    }
    
    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }
    public ExArticulos getExArticulos() {
        return this.exArticulos;
    }
    
    public void setExArticulos(ExArticulos exArticulos) {
        this.exArticulos = exArticulos;
    }
    public ExZonas getExZonas() {
        return this.exZonas;
    }
    
    public void setExZonas(ExZonas exZonas) {
        this.exZonas = exZonas;
    }
    public ExVendedores getExVendedores() {
        return this.exVendedores;
    }
    
    public void setExVendedores(ExVendedores exVendedores) {
        this.exVendedores = exVendedores;
    }
    public Integer getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(Integer fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    public Short getUnidades() {
        return this.unidades;
    }
    
    public void setUnidades(Short unidades) {
        this.unidades = unidades;
    }
    public Boolean getEliminado() {
        return this.eliminado;
    }
    
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }




}


