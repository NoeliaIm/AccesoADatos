/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.discoduroderoer.numeros;

import javax.swing.JOptionPane;

/**
 *
 * @author DiscoDurodeRoer
 */
public class Calculo {
    
    
    /**
     * Devuelve el factorial de un número
     * @param num Número
     * @return Factorial del número
     */
    public static int factorial(int num){
        
        int producto = num;
        for (int i = num - 1;i>0;i--){
            producto *= i;
        } 

        return producto;
    }
    
    /**
     * Resuelve una ecuación de 2º grado
     * @param a Valor de A
     * @param b Valor de B
     * @param c Valor de C
     * @return Resultado de la ecuación
     */
    public static double[] ecuacion2Grado(int a, int b, int c){
        
        if ((Math.pow(b, 2) - (4 * a * c)) >= 0){

            double soluciones[]=new double[2];

            soluciones[0] = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);

            soluciones[1] = ((-b) - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);

            return soluciones;
        }else{
            JOptionPane.showMessageDialog(null, "La ecuacion no se puede resolver");
            return null;
        }
        
    }
    
     /**
      * Indica si un numero es potencia de otro
      * @param numero Número que queremos saber si es el resultado de una potencia 
      * @param potencia Potencia
      * @return True = el número es potencia del parámetro potencia
      */
    public static boolean esPotenciaDe(int numero, int potencia){
        
        if(potencia>1){
            
            int contador=-1;
            int aux=numero;

            for(int i=aux;i>0;i=(int)Math.floor(i)/potencia){
                contador++;
            }
            
            return numero==Math.pow(potencia,contador);
            
        }else{
            return potencia>=0;
            
        }
        
    }
    
    /**
    * Devuelve el exponente de un número, dado su base y potencia final
    * Si devuelve -1, el numero introducido es erroneo
    * @param numeroFinal Resultado de la potencia
    * @param base Número que ha sido elevado a una potencia
    * @return Exponente del parámetro numeroFinal
    */
    public static int exponenteDe(int numeroFinal, int base){
        
        if(base>1){
            
            int contador=-1;
            int aux=numeroFinal;

            for(int i=aux;i>0;i=(int)Math.floor(i)/base){
                contador++;
            }

            if(numeroFinal==Math.pow(base,contador)){
                return contador;
            }else{
                return -1;
            }
            
        }else{
            if(numeroFinal==1 || numeroFinal==0){
                return numeroFinal;
            }else{
                return -1;    
            }
        }
    }
    
     /**
     * Devuelve el menor divisor de un numero, sea negativo o positivo
     * Si es primo devuelve 1, segun el signo puede ser -1 o 1
     * @param num Número inicial
     * @return Número que es el menor divisor del parámetro num
     */
    public static int menorDivisor(int num){
        
        if(esPrimo(num)){
            
            if(num>=0){
                return 1;
            }else{
                return -1;
            }
            
        }else{
            
            int divisores[]=rellenaDivisores(num);
         
            return divisores[0];
            
        }
         
     }
    
    /**
     * Indica el mayor divisor de un numero, sin incluir el numero como tal.
     * Si es primo devuelve 1, segun el signo puede ser -1 o 1
     * @param num Número inicial
     * @return Número que es el mayor divisor del parámetro num
     */
    public static int mayorDivisor(int num){
        
        if(esPrimo(num)){
            
            if(num>=0){
                return 1;
            }else{
                return -1;
            }
            
        }else{
            
            int divisores[]=rellenaDivisores(num);
         
            return divisores[divisores.length-1];
            
        }
         
     }
    
    /**
     * Rellena un array con los divisores de un numero, sin incluir el 1 y el propio numero
     * @param num Número inicial
     * @return Array con todos los divisores
     */
     public static int[] rellenaDivisores(int num){
         
         int divisor[]=new int[cuentaDivisores(num)];
         
         if(num>=0){
            for(int i=2, j=0;i<num && j<divisor.length;i++){

                if(num%i==0){
                    divisor[j]=i;
                    j++;
                }

            }
         }else{
             for(int i=-2, j=0;i>num && j<divisor.length;i--){

                if(num%i==0){
                    divisor[j]=i;
                    j++;
                }

            }
         }
        
         return divisor;     
     }
     
     /**
      * Devuelve el numero de divisores de un numero, sea negativo o positivo
      * @param num Número inicial
      * @return Número de divisores
      */
     public static int cuentaDivisores(int num){
         
         int contador=0;
         if(num>=0){
            for(int i=2;i<num;i++){
                if(num%i==0){
                    contador++;
                }
            }
         }else{
            for(int i=-2;i>num;i--){
                if(num%i==0){
                    contador++;
                }
            }
         }
         
         return contador;
     }
     
     /**
     * Indica si un numero es primo
     * @param numero Número que queremos saber si es primo o no
     * @return True = es primo
     */
    public static boolean esPrimo(int numero){

            int contador=0;

            if(numero>=0){
                //bucle que cuenta los numeros divisibles
                for (int i=(int)Math.sqrt(numero);i>1;i--){
                    if (numero%i==0){
                        contador+=1;
                    }
                }
            }else{
                //bucle que cuenta los numeros divisibles
                for (int i=(int)Math.sqrt(Math.abs(numero))*(-1);i<-1;i++){
                    if (numero%i==0){
                        contador+=1;
                    }
                }
            }
            
        return contador < 1;
    }
    
}
