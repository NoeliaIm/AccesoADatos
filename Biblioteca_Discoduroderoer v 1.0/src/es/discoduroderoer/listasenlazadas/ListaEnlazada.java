package es.discoduroderoer.listasenlazadas;

/**
 * @author DiscoDurodeRoer
 */
public class ListaEnlazada{
    /**
     * Clase interna Nodo
     */
    private class Nodo {
        protected Object dato;
        protected Nodo sig;

        /**
         * Constructor por defectis
         */
        public Nodo(){
            sig=null;
            dato = new Object();
         }
        
        /**
         * Le pasamos un objeto al nodo
         * @param p Objeto pasado
         */
         public Nodo(Object p){
            sig=null;
            dato = p;
         }
    }

    //*********************************************************
    //*********************************************************
    
    //Atributos
    private Nodo primero;
    
    /**
     * Constructor por defecto
     */
    public ListaEnlazada(){
        Lista_Vacia(); 
    }
       
    /**
     * Vacia la lista
     */
    private void Lista_Vacia(){
        primero = null;
    }
    
    /**
     * Indica si la lista esta vacia o no
     * @return True = esta vacia
     */
    public boolean Esta_Vacia(){
        return primero == null;
    }
    
    /**
     * Inserta un objeto al principio de la lista 
     * @param p Objeto insertado
     */
    public void Insertar_Primero(Object p){
        Nodo nuevo = new Nodo(p);
        
        if (primero==null) primero=nuevo;
        else {
            nuevo.sig = primero;
            primero = nuevo;
        }

    }
    
    /**
     * Inserta al final de la lista un objeto
     * @param p Objeto insertado 
     */
    public void Insertar_Ultimo(Object p){
    
        Nodo aux = new Nodo(p);
        Nodo rec_aux;
     
        if (primero==null) {
             aux.sig = primero;
             primero = aux;
        }else {
           rec_aux = primero;
           while(rec_aux.sig != null)  rec_aux = rec_aux.sig;
           rec_aux.sig = aux;
        }
    }
    
    /**
     * Quita el primer elemento de la lista
     */
    public void Quitar_Primero(){
     Nodo aux;
     if (!Esta_Vacia()) 
        {
          aux=primero;
          primero = primero.sig;
          aux=null; //Lo marcamos para el recolector de basura   
        }
    }
    
    /**
     * Quita el ultimo elemento de la lista
     */
    public void Quitar_Ultimo(){
        Nodo aux=primero;
        if(aux.sig==null) 
           Lista_Vacia();
        if(!Esta_Vacia()) {
        aux=primero;    
        while(aux.sig.sig != null)
            aux=aux.sig;
        aux.sig=null;
        }

    }        
    
    /**
     * Devuelve el último elemento de la lista
     * @return Último elemento
     */
    public Object Ultimo(){ 
        Object elemen = null;
        Nodo aux = null;
        if (!Esta_Vacia()){
            aux = primero;
            while(aux.sig != null)  aux = aux.sig;
                elemen = (Object) aux.dato;  
        }
        return elemen;
    }
      
    /**
     * Devuelve el primer elemento de la lista
     * @return Primer elemento
     */
    public Object Primero(){
        Object elemen = null;
        if (!Esta_Vacia()){
            elemen = (Object) primero.dato;   
        }
        return  elemen;
    }
    
    /**
     * Devuelve el número de elementos de la lista
     * @return Número de elementos
     */
    public int Cuantos_Elementos(){
        Nodo aux=null;
        int i=0;
        aux = primero;
        
        while(aux != null){
            aux = aux.sig;
            i++;
        }
        return i;
  
    }
    
    /**
     * Borra un elemento de la lista
     * @param pos Posición de la lista que queremos borrar 
     */
    public void Borra_Posicion(int pos){
        
        Nodo aux, ant;
        int cont=1;
 
        aux=primero;
        ant=null;
        while(aux!=null){
            if (pos == cont){
                if (ant==null){
                    primero = primero.sig;
                    aux=null;
                }else {
                    ant.sig = aux.sig;
                    aux=null;
                }
            }else{
                ant=aux;
                aux=aux.sig;
                cont++;
            }
        }
    }
    
    /**
     * Devuelve el primer el elemento y lo borra de la lista
     * @return Primer elemento
     */
    public Object devYBorrarPrimero(){
        
        Object p=Primero();
        
        Quitar_Primero();
        
        return p;
    }
    
    /**
     * Indica la posición de un objeto
     * @param t Objeto buscado
     * @return Posición del objeto buscado
     */
    public int indexOf (Object t){

       Nodo aux=primero;
       if (Esta_Vacia()){
               return 0;
       }else{
           int contador=0;
           boolean encontrado=false;
           while(aux!=null && !encontrado){
               if(t.equals(aux.dato)){
                   encontrado=true;
               }
               contador++;
               aux=aux.sig;
           }
           if(encontrado){
                return contador;
           }else{
                return -1;
           }
       }
    }
           
    /**
     * Indica si un objeto existe en la lista
     * @param t Objeto a comprobar
     * @return True = el objeto existe
     */
    public boolean objetoExistente(Object t){
		
        boolean existe=false;

        Nodo aux=primero;
        
        while(aux!=null && !existe){

            Object oActual=aux.dato;

            if(oActual.equals(t)){
                existe=true;
            }
            
            aux=aux.sig;
        }

        return existe;
    }
    
}