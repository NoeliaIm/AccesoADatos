/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.discoduroderoer.numeros;

import es.discoduroderoer.arrays.Array;

/**
 * @author DiscoDurodeRoer
 */
public class Numeros {
    
    /**
     * Indica cual es el menor número de un array
     * @param numeros Array de int
     * @return Menor número
     */
    public static int menorNumero(int[] numeros){
        
        int menor=numeros[0];
        
        for(int i=1;i<numeros.length;i++){
            if(numeros[i]<menor){
                menor=numeros[i];
            }
        }
        
        return menor;
    }
    
    /**
     * Indica cual es el mayor número de un array
     * @param numeros Array de int
     * @return Mayor número
     */
    public static int mayorNumero(int[] numeros){
        
        int mayor=numeros[0];
        
        for(int i=1;i<numeros.length;i++){
            if(numeros[i]>mayor){
                mayor=numeros[i];
            }
        }
        
        return mayor;
    }
    
    
    /**
     * Cuenta el numero de cifras de un numero
     * @param num Número a contrar
     * @return numero de cifras
     */
    public static int cuentaCifras(int num){
        int contador=0;
        if(num==0){
            return 1;
        }else if(num>0){
             //bucle que cuenta las cifras
            while (num>0){
                num=(int)Math.floor(num/10);
                contador+=1;
            }
            return contador;
        }else{
            while (num<0){
                num=(int)Math.floor(num/10);
                contador+=1;
            }
            return contador;
        }
    }
    
    
    
    
    /**
     * Indica el numero de la serie fibonacci
     * @param posicion Posición de la serie Fibonacci
     * @return Número de la serie Fibonacci
     */
    public static int numeroFibonacci (int posicion){
        int num1=0, num2=1, suma=1;

        for (int i=1;i<posicion;i++){
            //primero sumamos
            suma=num1+num2;
            //Despues, cambiamos la segunda variable por la primera
            num1=num2;
            //Por ultimo, cambiamos la suma por la segunda variable
            num2=suma;
        }
        return suma;
		
    }
    
    public static int sumaDigitos (int numeroInicial){
        
        int numero=numeroInicial;
        int suma=0;
        int numero_solo;
        
        while(numeroInicial>0){
            numero/=10;
            numero_solo=numeroInicial-(numero*10);
            suma+=numero_solo;
            numeroInicial=numero;
        }
        return suma;
    }
    
    public static int[] devuelveDigitos (int numeroInicial){
        
        int numero=numeroInicial;
        
        int digitos[]=new int[cuentaCifras(numeroInicial)];
        int numero_solo;
        
        for(int i=0;numeroInicial>0;i++){
            numero/=10;
            numero_solo=numeroInicial-(numero*10);
            digitos[i]=numero_solo;
            numeroInicial=numero;
        }
        return Array.invertirArray(digitos);
        
    }

}
