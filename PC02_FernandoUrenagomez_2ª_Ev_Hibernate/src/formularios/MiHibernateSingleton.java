/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;
import org.hibernate.Session; //Para crear la sesión
import org.hibernate.SessionFactory; // Para crear SessionFactory
import org.hibernate.Transaction;
import conexion.HibernateUtil;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import org.hibernate.Query;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Fernando
 */
public class MiHibernateSingleton {
    
    private SessionFactory instancia;
    private Session sesion;
    private Transaction tx;
    
    private static MiHibernateSingleton INSTANCE;
    
    private MiHibernateSingleton(){
        
        instancia= HibernateUtil.getSessionFactory();
        
    }
    
    private synchronized static void createInstance() {
        
        synchronized(MiHibernateSingleton.class) {
            if (INSTANCE == null) { 
                
                INSTANCE=new MiHibernateSingleton();

            }
        }
        
    }
    
    public static MiHibernateSingleton getInstance() {
        createInstance();
        return INSTANCE;
    }
    
    
    
     public SessionFactory getInstancia() {
        return instancia;
    }
    
    public Session getSesion() {
        return sesion;
    }
    
    public void abrirSesion(){
        sesion = instancia.openSession() ;
    }
    
    public void cerrarSesion(){
        sesion.close();
    }
    
    public void abrirTransaccion(){
        tx =sesion.beginTransaction();
    }
    
    //Guardamos los datos
    public void cerrarTransaccion(){
        tx.commit();
    }
    
    public void abrirSesionYTransaccion(){
        sesion = instancia.openSession() ;
        tx =sesion.beginTransaction();
    }
    
    public void cerrarSesionYTransaccion(){
        tx.commit();
        sesion.close();
    }
    
    //Guarda el objeto en la bd(Como si hciera un insert)
    public void insertar(Object o){
        sesion.save(o);
    }
    
    //Borra un objeto
    public void borrar(Object o){
        sesion.delete(o);
    }
    
    public void update(Object o){
        sesion.update(o);
    }
    
   
    public int proximoIDDisponible(String campoID, String clase){
        
        Integer id=(Integer)sesion.createQuery("select max("+campoID+") from "+clase).uniqueResult();
        
        if(id==null){
            id=new Integer(0);
        }
        
        return id+1;
        
    }
    
    public BigDecimal proximoIDDisponibleBigDec(String campoID, String clase){
        
        BigDecimal id=(BigDecimal)sesion.createQuery("select max("+campoID+") from "+clase).uniqueResult();
        
        if(id==null){
            id=new BigDecimal(0);
        }
        
        return id.add(new BigDecimal(1));
    }
    
    public void rellenarTabla(DefaultTableModel modelo, String consulta, int columnas){

        abrirSesion();
        
        limpiarTabla(modelo);

        Query q=sesion.createQuery(consulta);

        q.setFetchSize(10);
        
        //Cambiar clase
        Iterator<Object[]> it= q.iterate();
        Object[] fila;

        //Pasar el numero de columnas que tenemos
        Object[] values=new Object[columnas];

        while(it.hasNext()){

            fila = it.next();

            
            for(int i=0;i<columnas;i++){
                values[i]=fila[i]; 
            }
            
            
            //añade al modelo(DefaultTableModel)
            modelo.addRow(values);

        }
    }
    
    public void rellenarTablaObject(String consulta, int columnas, DefaultTableModel dtm){
          
        abrirSesion();
          
        limpiarTabla(dtm);

        Query q=sesion.createQuery(consulta);

        q.setFetchSize(10);

        //Cambiar clase, si es cruzada poner Object[]
        Iterator<Object[]> it= q.iterate();
        Object[] fila;

        //Pasar el numero de columnas que tenemos
        Object[] values=new Object[columnas];

        while(it.hasNext()){

            fila = (Object[])it.next();

            //Añadir metodos del objeto y guardarlos en el array de objetos
            //values[0]=metodo;
            //...
            
            for(int i=0;i<values.length;i++){
                values[i]=fila[i];
            }
            
            //añade al modelo(DefaultTableModel)
            dtm.addRow(values);

        }
          
          
        cerrarSesion();
          
      }
    
     public void rellenarCMBObject(JComboBox cmb, String consultaH, String inicio){
        
        Session auxSesion=instancia.openSession();
        
        String datos[] = new String[2];

        cmb.removeAllItems(); //Borra todos los Items
        
        
        Query consulta = auxSesion.createQuery(consultaH);
        
        if (!inicio.equals("")){
            cmb.addItem(new String[]{"-1", inicio,}); //Los añade al Combo con dos columnas
        }
       
        Iterator<Object[]> iter = consulta.iterate();
        Object[] objeto;
        
        BigDecimal aux;
        while (iter.hasNext()) {
            try {
                
                //Cambiar clase por la que nosotros queramos
                 objeto = (Object[]) iter.next();
                
                 aux=(BigDecimal)objeto[0];
                 
                //Cambiar metodo por el que corresponda
                datos[0] = String.valueOf( aux.intValue()); 
                datos[1] = (String)objeto[1];
                cmb.addItem(new String[]{datos[0], datos[1],});
            } catch ( Exception ex) {
                Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cmb.setRenderer(new DefaultListCellRenderer() //Renderiza el Combo 
        {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList l, Object o, int i, boolean s, boolean f) {
                return new JLabel(((String[]) o)[1]);  //Visualiza sólo la columna 1, que es el nombre
            }
        });
    
        auxSesion.close();
        
    }
     
    //Igual que el anterior pero siendo el id un int
     public void rellenarCMBObjectInt(JComboBox cmb, String consultaH, String inicio){
        
        Session auxSesion=instancia.openSession();
        
        String datos[] = new String[2];

        cmb.removeAllItems(); //Borra todos los Items
        
        
        Query consulta = auxSesion.createQuery(consultaH);
        
        if (!inicio.equals("")){
            cmb.addItem(new String[]{"-1", inicio,}); //Los añade al Combo con dos columnas
        }
       
        Iterator<Object[]> iter = consulta.iterate();
        Object[] objeto;
        
        Integer aux;
        while (iter.hasNext()) {
            try {
                
                //Cambiar clase por la que nosotros queramos
                 objeto = (Object[]) iter.next();
                
                 aux=(Integer)objeto[0];
                 
                //Cambiar metodo por el que corresponda
                datos[0] = String.valueOf( aux.intValue()); 
                datos[1] = (String)objeto[1];
                cmb.addItem(new String[]{datos[0], datos[1],});
            } catch ( Exception ex) {
                Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cmb.setRenderer(new DefaultListCellRenderer() //Renderiza el Combo 
        {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList l, Object o, int i, boolean s, boolean f) {
                return new JLabel(((String[]) o)[1]);  //Visualiza sólo la columna 1, que es el nombre
            }
        });
    
        auxSesion.close();
        
    }
     
    
    
    public <T> boolean ExisteValorPrimario(short valor, String nombreClase) {
        abrirSesion();
        boolean existe;
        
        //Integer emp_no_ant = Integer.parseInt(txtEmp_no.getText());
        T obj = null;
        try {
            obj = (T) sesion.get(Class.forName(nombreClase), valor);
            //Empleados emple = (Empleados) sesion.createQuery("from Empleados as e where e.empNo = ? ")
            //              .setInteger(0, Emp_no).uniqueResult();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj == null) {
            existe = false;
        } else {
            existe = true;
        }
        cerrarSesion();
        return existe;
    }
    
    public <T> boolean ExisteValorPrimario(String valor, String nombreClase) {
        
        abrirSesion();
        boolean existe;
        
        //Integer emp_no_ant = Integer.parseInt(txtEmp_no.getText());
        T obj = null;
        try {
            obj = (T) sesion.get(Class.forName(nombreClase), valor);
            //Empleados emple = (Empleados) sesion.createQuery("from Empleados as e where e.empNo = ? ")
            //              .setInteger(0, Emp_no).uniqueResult();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj == null) {
            existe = false;
        } else {
            
            existe = true;
        }
        cerrarSesion();
        return existe;
    }
    
    public <T> boolean ExisteValorPrimario(BigDecimal valor, String nombreClase) {
        
        abrirSesion();
        boolean existe;
        
        //Integer emp_no_ant = Integer.parseInt(txtEmp_no.getText());
        T obj = null;
        try {
            obj = (T) sesion.get(Class.forName(nombreClase), valor);
            //Empleados emple = (Empleados) sesion.createQuery("from Empleados as e where e.empNo = ? ")
            //              .setInteger(0, Emp_no).uniqueResult();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj == null) {
            existe = false;
        } else {
            existe = true;
        }
        cerrarSesion();
        
        return existe;
    }
    
    public <T> boolean ExisteValorPrimario(Integer valor, String nombreClase) {
        abrirSesion();
        boolean existe;
        
        //Integer emp_no_ant = Integer.parseInt(txtEmp_no.getText());
        T obj = null;
        try {
            obj = (T) sesion.get(Class.forName(nombreClase), valor);
            //Empleados emple = (Empleados) sesion.createQuery("from Empleados as e where e.empNo = ? ")
            //              .setInteger(0, Emp_no).uniqueResult();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj == null) {
            existe = false;
        } else {
            existe = true;
        }
        
        cerrarSesion();
        return existe;
    }
    
    public <T> boolean ExisteValorPrimario(byte valor, String nombreClase) {
        
        abrirSesion();
        boolean existe;
        
        //Integer emp_no_ant = Integer.parseInt(txtEmp_no.getText());
        T obj = null;
        try {
            obj = (T) sesion.get(Class.forName(nombreClase), valor);
            //Empleados emple = (Empleados) sesion.createQuery("from Empleados as e where e.empNo = ? ")
            //              .setInteger(0, Emp_no).uniqueResult();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiHibernateSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (obj == null) {
            existe = false;
        } else {
            existe = true;
        }
        
        cerrarSesion();
        
        return existe;
    }
    
    //Solo para un valor
    public <T> boolean ExisteValor(String valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            T obj=null;
            
            obj=(T)sesion.createQuery("from "+nombreClase+" where upper("+columna+") = '"+valor+"'").uniqueResult();
            
            existe = obj != null;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    //solo para un valor
    public <T> boolean ExisteValorInt(int valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            T obj=null;
            
            obj=(T)sesion.createQuery("from "+nombreClase+" where "+columna+" = "+valor).uniqueResult();
            
            existe = obj != null;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    //Solo para un valor
    public <T> boolean ExisteValorBigDec(BigDecimal valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            T obj=null;
            
            obj=(T)sesion.createQuery("from "+nombreClase+" where "+columna+" = "+valor.intValue()).uniqueResult();
            
            existe = obj != null;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    public <T> boolean ExistenValores(String valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            Query q=sesion.createQuery("from "+nombreClase+" where upper('"+columna+"') = '"+valor+"'");
            
            existe = q.list().size() > 0;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    public <T> boolean ExistenValores(int valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            Query q=sesion.createQuery("from "+nombreClase+" where "+columna+" = "+valor);
            
            existe = q.list().size() > 0;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    public <T> boolean ExistenValores(BigDecimal valor, String columna, String nombreClase){
        
            abrirSesion();
            
            boolean existe;
            
            Query q=sesion.createQuery("from "+nombreClase+" where "+columna+" = "+valor.intValue());
            
            existe = q.list().size() > 0;
            
            cerrarSesion();
            
            return existe;
            
    }
    
    //Abrir sesion desde fuera
    //Debe devolver un valor
    //debe ser un campo varchar2
    public String devolverValorString(String campo, String clase, String campoID, int id){
        
        String valor=(String)sesion.createQuery("select "+campo+" from "+clase+" where "+campoID+"="+id).uniqueResult();
        
        return valor;
    }
    
    //Abrir sesion desde fuera
    //Debe devolver un valor
    //debe ser un campo Int
    public int devolverValorInt(String campo, String clase, String campoID, int id){
        
        //cambiar clase y columna
        Integer valor=(Integer)sesion.createQuery("select "+campo+" from "+clase+" where "+campoID+"="+id).uniqueResult();
        
        //Cambiar el metodo a llamar
        return valor;
    }
    
    
    //Abrir sesion desde fuera
    //Debe devolver un valor
    //debe ser un campo Big Decimal
    public BigDecimal devolverValorBigDec(String campo, String clase, String campoID, int id){
        
        BigDecimal valor=(BigDecimal)sesion.createQuery("select "+campo+" from "+clase+" where "+campoID+"="+id).uniqueResult();
        
        return valor;
    }
    
    private void limpiarTabla(DefaultTableModel dtm){
       
        while(!dtm.getDataVector().isEmpty()){
            dtm.removeRow(0);
        }
    }
}

