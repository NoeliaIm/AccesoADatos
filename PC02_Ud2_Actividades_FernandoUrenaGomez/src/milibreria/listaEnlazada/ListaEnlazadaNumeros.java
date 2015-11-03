/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package milibreria.listaEnlazada;

/**
 *
 * @author Alumno
 */
public class ListaEnlazadaNumeros
{
        
    private class Nodo 
    {
        //Atributos
        protected Object dato;
        protected Nodo sig;

        //Métodos constructores
        public Nodo()
         {
            sig=null;
            dato = (Integer) new Integer(0);
         }
        
         public Nodo(Integer p)
         {
            sig=null;
            dato = p;
         }
    }



    //*********************************************************
    //*********************************************************
    private Nodo primero;

    
    //---------------------------------------------
    public ListaEnlazadaNumeros()
    {
        Lista_Vacia();
        
    }
       
    
    //---------------------------------------------
    public void Lista_Vacia()
    {
        primero = null;
    }
    
    
    //---------------------------------------------
    public boolean Esta_Vacia()
    {
        return primero == null;
    }
    
    
    
    //---------------------------------------------
    public void Insertar_Primero(int p)
    {
        Nodo nuevo = new Nodo(p);
        
        //Caso a) --> Lista vacÃ­a
        if (primero==null) primero=nuevo;
        else {
            nuevo.sig = primero;
            primero = nuevo;
        }

    }
    
    
    //---------------------------------------------
    public void Insertar_Ultimo(int p)
    {Nodo aux = new Nodo(p);
     Nodo rec_aux;
     
        if (primero==null) 
            {
             aux.sig = primero;
             primero = aux;
            }
        else {
               rec_aux = primero;
               while(rec_aux.sig != null)  rec_aux = rec_aux.sig;
               rec_aux.sig = aux;
             }
    }
    
    
    //---------------------------------------------
    public void Quitar_Primero()
    {
     Nodo aux;
     if (!Esta_Vacia()) 
        {
          aux=primero;
          primero = primero.sig;
          aux=null; //Lo marcamos para el recolector de basura   
        }
    }
    
    //---------------------------------------------
    public void Quitar_Ultimo()
    {
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
    
    //---------------------------------------------
    public Object Ultimo()
    { 
     Object elemen = null;
     Nodo aux = null;
     if (!Esta_Vacia())
        {
          aux = primero;
          while(aux.sig != null)  aux = aux.sig;
               elemen = (Object) aux.dato;  
        }
        return elemen;
    }
      
    //---------------------------------------------
    public Object Primero()
    { Object elemen = null;
        if (!Esta_Vacia())
        {
         elemen = (Object) primero.dato;   
        }
        return  elemen;
    }
    
   
    //---------------------------------------------
    public int Cuantos_Elementos()
    {
      Nodo aux=null;
        int i=0;
        aux = primero;
        
        while(aux != null){
      
            aux = aux.sig;
            i++;
        }
        return i;
  
    }
    

    
    //------------------------------------------
    public void Borra_Posicion(int pos)
    //Las posiciones empiezan desde 1.
    {Nodo aux, ant;
     int cont=1;
 
     aux=primero;
     ant=null;
     while(aux!=null)
     {
         if (pos == cont)
         {
             if (ant==null) //Primero
                {
                   primero = primero.sig;
                   aux=null; //Para que borre efectivamente el nodo.
                }
             else {
                    ant.sig = aux.sig;
                    aux=null; //Para que borre efectivamente el nodo.
                  }
         }
         else
         {
             ant=aux;
             aux=aux.sig;
             cont++;
         }//Fin del 'else
     }//Fin del 'while'
    }
    
    public Object devYBorrarPrimero(){
        
        Object p=Primero();
        
        Quitar_Primero();
        
        return p;
    }
    
    public boolean existeNumero(int numeroBuscado){
        
        boolean existe=false;
        
        Nodo aux=primero;
        
        while(aux!=null && !existe){
            
            if(aux.dato==numeroBuscado){
                existe=true;
            }
            aux=aux.sig;
            
        }
        return existe;
    }
    
    
    
    /*public void rellenarLista(){
        
        for(int i=0;i<200;i++){
            Insertar_Ultimo(new Object());
        }
    }*/


}
