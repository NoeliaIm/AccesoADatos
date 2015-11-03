package milibreria.listaEnlazada;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alumno
 */
public class ListaEnlazadaCartas
{
        
    private class Nodo 
    {
        //Atributos
        protected Carta dato;
        protected Nodo sig;

        //Métodos constructores
        public Nodo(int numero, String palo)
         {
            sig=null;
            dato = new Carta(numero, palo);
         }
        
         public Nodo(Carta p)
         {
            sig=null;
            dato = p;
         }
    }



    //*********************************************************
    //*********************************************************
    
    //Constantes
    private int[] numeros={1,2,3,4,5,6,7,10,11,12};
    private String[] palos={"Espadas", "Oros" ,"Bastos", "Copas"};
    
    //Atributos
    private Nodo primero;
    
    
    //---------------------------------------------
    public ListaEnlazadaCartas()
    {
        Lista_Vacia();
        rellenarBaraja();
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
    public void Insertar_Primero(Carta p)
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
    public void Insertar_Ultimo(Carta p)
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
    public Carta Ultimo()
    { 
     Carta elemen = null;
     Nodo aux = null;
     if (!Esta_Vacia())
        {
          aux = primero;
          while(aux.sig != null)  aux = aux.sig;
               elemen = (Carta) aux.dato;  
        }
        return elemen;
    }
      
    //---------------------------------------------
    public Carta Primero()
    { Carta elemen = null;
        if (!Esta_Vacia())
        {
         elemen = (Carta) primero.dato;   
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
    
    public Carta devYBorrarPrimero(){
        
        Carta p=Primero();
        
        Quitar_Primero();
        
        return p;
    }
    
    public void marcarCartaBaraja(int posicionCarta){
        
        Nodo aux=primero;
        
        if(posicionCarta==1){
            primero.dato.setMarcada(true);;
        }else{
            
            boolean encontrado=false;
            int contador=0;
            while(aux.sig!=null && !encontrado){

                if(contador==posicionCarta){
                    encontrado=true;
                }else{
                    aux=aux.sig;
                    contador++;
                }
            }

            aux.dato.setMarcada(true);
        }     
    }
    
    public Carta buscarCarta(int posicionCarta){
        
        Nodo aux=primero;
        
        if(posicionCarta==1){
            return primero.dato;
        }else{
            
            boolean encontrado=false;
            int contador=1;
            while(aux.sig!=null && !encontrado){

                if(contador==posicionCarta){
                    encontrado=true;
                }else{
                    aux=aux.sig;
                    contador++;
                }
            }
                
            if(aux==null){
                return null;
            }else{
                Borra_Posicion(contador);
                return aux.dato;
            }
            
        }
        
    }
    
    public void rellenarBaraja(){
        
        for(int i=0;i<palos.length;i++){
            for(int j=0;j<numeros.length;j++){
                Insertar_Ultimo(new Carta(numeros[j], palos[i]));
            }
        }
    }


}
