package modelos;
// Generated 24-feb-2014 18:09:26 by Hibernate Tools 3.6.0


import java.math.BigDecimal;

/**
 * DetallepedidosId generated by hbm2java
 */
public class DetallepedidosId  implements java.io.Serializable {


     private BigDecimal codigopedido;
     private String codigoproducto;

    public DetallepedidosId() {
    }

    public DetallepedidosId(BigDecimal codigopedido, String codigoproducto) {
       this.codigopedido = codigopedido;
       this.codigoproducto = codigoproducto;
    }
   
    public BigDecimal getCodigopedido() {
        return this.codigopedido;
    }
    
    public void setCodigopedido(BigDecimal codigopedido) {
        this.codigopedido = codigopedido;
    }
    public String getCodigoproducto() {
        return this.codigoproducto;
    }
    
    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetallepedidosId) ) return false;
		 DetallepedidosId castOther = ( DetallepedidosId ) other; 
         
		 return ( (this.getCodigopedido()==castOther.getCodigopedido()) || ( this.getCodigopedido()!=null && castOther.getCodigopedido()!=null && this.getCodigopedido().equals(castOther.getCodigopedido()) ) )
 && ( (this.getCodigoproducto()==castOther.getCodigoproducto()) || ( this.getCodigoproducto()!=null && castOther.getCodigoproducto()!=null && this.getCodigoproducto().equals(castOther.getCodigoproducto()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCodigopedido() == null ? 0 : this.getCodigopedido().hashCode() );
         result = 37 * result + ( getCodigoproducto() == null ? 0 : this.getCodigoproducto().hashCode() );
         return result;
   }   


}

