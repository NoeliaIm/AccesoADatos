/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
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
    
    public int actualizar(String instruccion){
        
        Query q = sesion.createQuery(instruccion);
        
        int filas = q.executeUpdate();
        
        return filas;
        
    }
    
    //Para parametros por posicion
    //Abrir sesion desde fuera
    public int actualizar(String consulta, Object parametros[]){
        
        Query q = sesion.createQuery(consulta);
        
        String tipos[]=tiposDatos(parametros);     
  
        for(int i=0;i<tipos.length;i++){
           
            switch(tipos[i]){
                case "Byte":
                    q.setByte(i, (Byte)parametros[i]);
                    break;
                case "Short":
                     q.setShort(i, (Short)parametros[i]);
                    break;
                case "Integer":
                     q.setInteger(i, (Integer)parametros[i]);
                    break;
                case "Long":
                     q.setLong(i, (Long)parametros[i]);
                    break;
                case "Double":
                     q.setDouble(i, (Double)parametros[i]);
                    break;
                case "Float":
                     q.setFloat(i, (Float)parametros[i]);
                    break;
                case "String":
                     q.setString(i, (String)parametros[i]);
                    break;
                case "Character":
                     q.setCharacter(i, (Character)parametros[i]);
                    break;
                case "Boolean":
                     q.setBoolean(i, (Boolean)parametros[i]);
                    break;
            }
            
        }
        
        int filas = q.executeUpdate();
        
        
        
        return filas;
        
        
    }
    
    
    //Para parametros nombrados
    //Abrir Sesion desde fuera
    public int actualizar(String consulta, Object parametros[][]){
        
        Query q = sesion.createQuery(consulta);
        
        String tipos[]=tiposDatos(parametros, 1);     
  
        for(int i=0;i<tipos.length;i++){
           
            switch(tipos[i]){
                case "Byte":
                    q.setByte((String)parametros[i][0], (Byte)parametros[i][1]);
                    break;
                case "Short":
                     q.setShort((String)parametros[i][0], (Short)parametros[i][1]);
                    break;
                case "Integer":
                     q.setInteger((String)parametros[i][0], (Integer)parametros[i][1]);
                    break;
                case "Long":
                     q.setLong((String)parametros[i][0], (Long)parametros[i][1]);
                    break;
                case "Double":
                     q.setDouble((String)parametros[i][0], (Double)parametros[i][1]);
                    break;
                case "Float":
                     q.setFloat((String)parametros[i][0], (Float)parametros[i][1]);
                    break;
                case "String":
                     q.setString((String)parametros[i][0], (String)parametros[i][1]);
                    break;
                case "Character":
                     q.setCharacter((String)parametros[i][0], (Character)parametros[i][1]);
                    break;
                case "Boolean":
                     q.setBoolean((String)parametros[i][0], (Boolean)parametros[i][1]);
                    break;
            }
            
        }
        
        int filas = q.executeUpdate();
        
        return filas;
        
        
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
    
    public <T> void rellenarTabla(DefaultTableModel modelo, String consulta, String[] nombreMetodos){
      
        abrirSesion();
        
        limpiarTabla(modelo);
        
        Query q=sesion.createQuery(consulta);
        
        List<T> lista=q.list();
        
        T fila;
        
        Object[] values=new Object[nombreMetodos.length];
        
        for(int i=0;i<lista.size();i++){
            
            fila = lista.get(i);
            
            for(int j=0;j<values.length;j++){
                
                try {
                   
                    values[j]=fila.getClass().getMethod(nombreMetodos[j], null).invoke(fila, null);
            
                } catch (    IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            modelo.addRow(values);
        
        }
        
        cerrarSesion();
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
                Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
     
    //Abrir sesion desde fuera
    public int cuentaRegistrosInt(String clase, String campo, int valor){
        
        long numRegistros=(Long)sesion.createQuery("select count(*) from "+clase+" where "+campo+"="+valor).uniqueResult();
        
        return (int)numRegistros;
        
    }
    
    public int cuentaRegistros(String clase){
        
        long numRegistros=(Long)sesion.createQuery("select count(*) from "+clase).uniqueResult();
        
        return (int)numRegistros;
        
    }
     
    
    public <T> void rellenarCMB2Columnas(JComboBox cmb, 
                                            String consultaH, 
                                            String inicio, 
                                            String nombreMetodoNoVisible,
                                            String nombreMetodoVisible) {

        //Uso una variable local para evitar problemas con sesiones abiertas
        Session sesionAux=instancia.openSession();
        
        String datos[] = new String[2];

        cmb.removeAllItems(); //Borra todos los Items
        
        
        Query consulta = sesionAux.createQuery(consultaH);
        
        if (!inicio.equals("")){
            cmb.addItem(new String[]{"-1", inicio,}); //Los añade al Combo con dos columnas
        }
       
        Iterator iter = consulta.iterate();
        while (iter.hasNext()) {
            try {
                
                //En caso de fallo
                //Cambiar la expresion de datos[n] por objeto.Metodo
                
                T objeto = (T) iter.next();
                datos[0] = String.valueOf((objeto.getClass().getMethod(nombreMetodoNoVisible, null).invoke(objeto, null))); 
                datos[1] = (String) objeto.getClass().getMethod(nombreMetodoVisible, null).invoke(objeto, null);
                cmb.addItem(new String[]{datos[0], datos[1],});
            } catch (    IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
        
        cmb.setSelectedIndex(0);
        
        sesionAux.close();
        
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
            Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MiHibernate.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String[] tiposDatos(Object[] array){
        
        String tipos[]=new String[array.length];
        
        for(int i=0;i<array.length;i++){
            
            tipos[i]=devuelveTipoDato(array[i].getClass().getName());
            
        }
        
        return tipos;
        
    }
    
    
     private String[] tiposDatos(Object[][] array, int posDato){
        
        String tipos[]=new String[array.length];
        
        for(int i=0;i<array.length;i++){
            
            System.out.println(array[i][posDato].getClass().getName());
            
            tipos[i]=devuelveTipoDato(array[i][posDato].getClass().getName());
            
        }
        
        return tipos;
        
    }
     
     private String devuelveTipoDato(String tipoCompleto){
        
        String tipo;
        
        switch(tipoCompleto){
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer": 
            case "java.lang.Long":
            case "java.lang.Double":
            case "java.lang.Float":
            case "java.lang.String":
            case "java.lang.Character":
            case "java.lang.Boolean":
                tipo=tipoCompleto.substring(10);
                break;
            default:
                tipo="Desconocido";
        }
        
        return tipo;
        
        
    }
    
    
     
    private void limpiarTabla(DefaultTableModel dtm){
       
        while(!dtm.getDataVector().isEmpty()){
            dtm.removeRow(0);
        }
    }
}

