/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import clases_bd.Categoria;
import clases_bd.Idiomas;
import clases_bd.Traducciones;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ComposedExpression;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author Fernando
 */
public class MiNeodatisSingleton {
    
    private final ODB odb;
    private static MiNeodatisSingleton instancia;

    private MiNeodatisSingleton(String pNombre){
        
        odb=ODBFactory.open(pNombre);
        
    }
    
    private MiNeodatisSingleton(String pNombre, String pUsuario, String pPassword){
        
        odb=ODBFactory.open(pNombre, pUsuario, pPassword);
        
    }
    
    public static MiNeodatisSingleton getInstance(String pNombre, String pUsuario, String pPassword) {
       
        createInstance(pNombre, pUsuario,pPassword);
        return instancia;
        
    }
    
    public static MiNeodatisSingleton getInstance(String pNombre) {
       
        createInstance(pNombre);
        return instancia;
        
    }
    
   public synchronized static void createInstance(String pNombre, String pUsuario, String pPassword) {
        
        synchronized(MiNeodatisSingleton.class) {
            if (instancia == null) { 
                
                instancia=new MiNeodatisSingleton(pNombre,pUsuario,pPassword);

            }
        }
        
    }
   
    public synchronized static void createInstance(String pNombre) {
        
        synchronized(MiNeodatisSingleton.class) {
            if (instancia == null) { 
                
                instancia=new MiNeodatisSingleton(pNombre);

            }
        }
        
    }
    
    
    public void guardarObjeto(Object objeto){
        
        odb.store(objeto);
        
    }
    
    public void borrarObjeto(Object objeto){
        
        odb.delete(objeto);
        
    }
    
    public void borrarObjetoCascada(Object objeto){
        
        odb.deleteCascade(objeto);
        
    }
    
    
    //Probar cpn Class.forName
    public OID obtenerOID(Object objeto){
        return odb.getObjectId(objeto);
    }
    
    public ODB getOdb() {
        return odb;
    }
    
    public void commit(){
        odb.commit();
    }
    
    
    
    
    
    /* no funciona aun proximo id **/
    
    
    
    
    public int proximoId(String atributo, String clase){
       
        BigDecimal ultimoid=new BigDecimal(1);
        try {
            Values values = odb.getValues(new ValuesCriteriaQuery(Class.forName(clase)).max(atributo));
            
            ultimoid=(BigDecimal)values.getFirst().getByAlias(atributo);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            if (odb != null) {
//                // Close the database
//                odb.close();
//            }
//        }
        
        return ultimoid.intValue()+1;
        
    }
    
    public boolean existeValorString(String valor, String atributo, String clase){
        
        boolean existe=false;
        try {
            IQuery consulta= new CriteriaQuery(Class.forName(clase),Where.equal(atributo, valor));
            
            Objects objetos = odb.getObjects(consulta);
            
            if (objetos.size()!=0){
                existe=true;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            if (odb != null) {
//                // Close the database
//                odb.close();
//            }
//        }
        
        return existe;
        
    }
    
    public boolean existeInt(int valor, String atributo, String clase){
        
        boolean existe=false;
        try {
            
            ICriterion criterioPrincipal=Where.equal(atributo, valor);
            
            IQuery consulta= new CriteriaQuery(Class.forName(clase),criterioPrincipal);
            
            Objects resultado=odb.getObjects(consulta);
            
            if(resultado.size()!=0){
                existe=true;
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return existe;
        
    }
    
    public int ultimoID(String atributo, String clase){
        
        BigDecimal ultimoid=new BigDecimal(1);
        try {
            Values values = odb.getValues(new ValuesCriteriaQuery(Class.forName(clase)).max(atributo));
            
            ultimoid=(BigDecimal)values.getFirst().getByAlias(atributo);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            if (odb != null) {
//                // Close the database
//                odb.close();
//            }
//        }
        
        return ultimoid.intValue();
        
    }
    
    public Object devolverObjeto(int id, String atributo, String clase){
        
        Object objeto=null;
        
        try {
            IQuery consulta= new CriteriaQuery(Class.forName(clase),Where.equal(atributo, id));
            
            objeto=odb.getObjects(consulta).getFirst();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return objeto;
        
    }
    
    public Object devolverObjeto(String valor, String atributo, String clase){
        
        Object objeto=null;
        
        try {
            IQuery consulta= new CriteriaQuery(Class.forName(clase),Where.equal(atributo, valor));
            
            objeto=odb.getObjects(consulta).getFirst();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return objeto;
        
    }
    
    public Object devolverObjeto(String[] valores, String[] atributos, String clase){
        
        Object objeto=null;
        
        try {
            ComposedExpression criterioPrincipal=Where.and();
            ICriterion criterio=null;
            
            for(int i=0;i<atributos.length;i++){
                criterio= Where.equal(atributos[i], valores[i]);
                criterioPrincipal.add(criterio);
            }
            
            IQuery consulta= new CriteriaQuery(Class.forName(clase),criterioPrincipal);
            
            objeto=odb.getObjects(consulta).getFirst();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiNeodatisSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return objeto;
        
    }
    
    
    public void rellenarIdiomas(JComboBox cmb, String inicio, int codigoIdioma){
    
        String datos[] = new String[2];
        cmb.removeAllItems();
        IQuery consulta= new CriteriaQuery(Idiomas.class);
        if (!inicio.equals("")){
            cmb.addItem(new String[]{"-1", inicio,}); //Los añade al Combo con dos columnas
        }
        Objects<Idiomas> objetos=odb.getObjects(consulta);
        Idiomas objeto;
        
        int contador=0;
        
        while (objetos.hasNext()) {
            
            objeto = (Idiomas) objetos.next();
                
            if(contador!=codigoIdioma){

                //Cambiar metodo por el que corresponda
                datos[0] = String.valueOf(objeto.getIdIdioma());
                datos[1] = objeto.getIdioma();

                cmb.addItem(new String[]{datos[0], datos[1],});

            }

            contador++;
                
        }
        cmb.setRenderer(new DefaultListCellRenderer() //Renderiza el Combo
        {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList l, Object o, int i, boolean s, boolean f) {
                return new JLabel(((String[]) o)[1]);  //Visualiza sólo la columna 1, que es el nombre
            }
        });
        
    }    
    
    public boolean existeTraduccion(Traducciones tradu){
        
        ICriterion criterio1=Where.equal("palabraOrigen", tradu.getPalabraOrigen());
        ICriterion criterio2=Where.equal("palabraMeta", tradu.getPalabraMeta());
        ICriterion criterio3=Where.equal("idiomas.idioma", tradu.getIdiomas().getIdioma());
        ICriterion criterio4;
        if(tradu.getCategoria()!=null){
            criterio4=Where.equal("categoria.descripcion", tradu.getCategoria().getDescripcion());
        }else{
            criterio4=Where.isNull("categoria.descripcion");
        }
        
        ICriterion criterioPrincipal=Where.and().add(criterio1).add(criterio2).add(criterio3).add(criterio4);
        
        IQuery consulta= new CriteriaQuery(Traducciones.class, criterioPrincipal);
            
        Objects objetos=odb.getObjects(consulta);
        
        return objetos.size()!=0;
        
    }
    
    public void rellenarTabla(DefaultTableModel modelo, IQuery consulta){
        
        Objects objetos=odb.getObjects(consulta);
        Traducciones tradu;
        Object[] valores=new Object[6];
        
        while (objetos.hasNext()) {
            
            //Borra todos los Items
            tradu = (Traducciones) objetos.next();
            valores[0]=tradu.getIdTraduccion();
            valores[1]=tradu.getPalabraOrigen();
            valores[2]=tradu.getPalabraMeta();
            if(tradu.getCategoria()!=null){
                valores[3]=tradu.getCategoria().getDescripcion();
            }else{
                valores[3]="";
            }
            valores[4]=tradu.getIdiomas().getIdioma();
            valores[5]=tradu.getDescripcion();
            
            modelo.addRow(valores);
            
        }
            
        
        
    }
    
    public void actulizarCategoriasTraducciones(Categoria categoria){
        
        IQuery consulta= new CriteriaQuery(Traducciones.class);
        
        Objects<Traducciones> objetos=odb.getObjects(consulta);
        Traducciones objeto;
        
        while(objetos.hasNext()){
        
            objeto = objetos.next();
            
            if(objeto.getCategoria()==categoria){
                
                objeto.setCategoria(null);
                
                guardarObjeto(objeto);
            }
            
        }
        
    }
    
    public void rellenarComboBoxCategorias(JComboBox cmb,String inicio, MiNeodatisSingleton conexion){
        
        cmb.removeAllItems();
        
        if(!inicio.equals("")){
            cmb.addItem(inicio);
        }
        
        IQuery consulta=new CriteriaQuery(Categoria.class).orderByAsc("descripcion");
        
        Objects<Categoria> categorias= conexion.getOdb().getObjects(consulta);
        
        while(categorias.hasNext()){
            
            Categoria categoria=categorias.next();
            
            cmb.addItem(categoria.getDescripcion());
            
        }
        
    }

    
    public void cerrarODB(){
        
        odb.close();
        
    }
    
}