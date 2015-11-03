/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.discoduroderoer.numeros;

import static es.discoduroderoer.numeros.Calculo.esPrimo;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author DiscoDurodeRoer
 */
public class Aleatorios {
    
     private static final char caracteresEspeciales[] = {'!', '%','&','(',')','*','/', '-','#','@'};
    
    /**
     * Genera un numero aleatorio entre dos numeros.
     * Entre el minimo y el maximo incluidos
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return Número entre minimo y maximo
     */
    public static int generaNumeroAleatorio(int minimo, int maximo){
        
        int num=(int)Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }
    
    /**
     * Genera un numero real aleatorio entre dos numeros.
     * Entre el minimo y maximo
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return numero entre minimo y maximo
     */
    public static double generaNumeroRealAleatorio(int minimo, int maximo){
        
        double num=Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }
    
    /**
     * Genera numeros aleatorios en un array, entre un minimo y un maximo
     * @param longitud Longitud del array
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return array con numeros entre el minimo y el maximo
     */
    public static int[] generaNumeroAleatorios(int longitud, int minimo, int maximo){
        
        int numeros[]= new int[longitud];
        
        for(int i=0;i<numeros.length;i++){
            numeros[i]=generaNumeroAleatorio(minimo, maximo);
        }
        
        return numeros;
    }
    
    /**
     * Genera numeros aleatorios en un array no repetidos, entre el minimo y el maximo.
     * @param longitud Longitud del array
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return array con numeros no repetidos entre el minimo y el maximo
     */
    public static int[] generaNumeroAleatoriosNoRepetidos(int longitud, int minimo, int maximo){
        
        if(longitud-(maximo-minimo)<=0){
            
            int numeros[]= new int[longitud];
            Arrays.fill(numeros, minimo-1);
            int i=0;
            int num=0;
            while(i<numeros.length){

                 boolean repetido;
                 do{
                     repetido=false;
                     num=generaNumeroAleatorio(minimo, maximo);

                     for(int j=0;j<numeros.length && !repetido;j++){
                        if(numeros[j]==num){
                            repetido=true;
                        }
                     }
                 }while(repetido);

                 numeros[i]=num;
                 i++;
            }
        
        return numeros;
        }else{
            JOptionPane.showMessageDialog(null, "Error, la diferencia entre el maximo y el minimo debe ser mayor que la longitud", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    /**
     * Como anterior pero da mas libertad, no es necesaro que reparta los numeros entre el minimo y maximo
     * @param longitud Longitud del array
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return Array con números aleatorios
     */
    public static int[] generaNumeroAleatoriosNoRepetidosV2(int longitud, int minimo, int maximo){
        
        int numeros[]= new int[longitud];
        Arrays.fill(numeros, minimo-1);
        int i=0;
        int num=0;
        while(i<numeros.length){

             boolean repetido;
             do{
                 repetido=false;
                 num=generaNumeroAleatorio(minimo, maximo);

                 for(int j=0;j<numeros.length && !repetido;j++){
                    if(numeros[j]==num){
                        repetido=true;
                    }
                 }
             }while(repetido);

             numeros[i]=num;
             i++;
        }

        return numeros;

    }
    
    
    /**
     * Genera una letra mayuscula
     * @return letra mayuscula
     */
    public static char generaLetraMayus(){
        int letra=generaNumeroAleatorio(65,91);
        return (char)letra;
    }
    
    /**
     * Genera una letra minuscula
     * @return letra minuscula
     */
    public static char generaLetraMinus(){
        int letra=generaNumeroAleatorio(97,123);
        return (char)letra;
    }
    
    /**
     * Genera un caracter especial
     * @return caracter especial
     */
    public static char generaLetraCaracteresEspeciales(){
        int letra=(int)Math.floor(Math.random()*caracteresEspeciales.length);
        return (char)caracteresEspeciales[letra];
    }
    
    /**
     * Genera un password, con una longitud. Mezcla numeros, minusculas, mayusculas y caracteres especiales.
     * @param longitud Longitud de la password
     * @return password
     */
    public static String generaPassWord(int longitud){
        String password="";
        for(int i=0;i<longitud;i++){          
            int eleccion=(int)Math.floor(Math.random()*4);
            switch(eleccion){
                case 0:
                    password+=Integer.toString(generaNumeroAleatorio(0,10));
                    break;
                case 1:
                    password+=generaLetraMayus();
                    break;
                case 2:
                    password+=generaLetraMinus();
                    break;
                case 3:
                    password+=generaLetraCaracteresEspeciales();
                    break;
            }
        }
        return password;
    }
    
    /**
     * Genera un numero primo entre el minimo y maximo incluidos
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return primo entre el minimo y maximo
     */
    public static int generarNumeroPrimoAzar(int minimo, int maximo){
        
        int numPrimo=-1;
        if(cuentaPrimosEntre(minimo,maximo)==0){
            JOptionPane.showMessageDialog(null, "No hay primos entre " +minimo+" y "+maximo, "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            do{
                numPrimo=generaNumeroAleatorio(minimo, maximo);
            }while(!esPrimo(numPrimo));
        }
        
        return numPrimo;
        
    }
    
    /**
     * Genera numeros primos entre el maximo y el minimo incluidos, los devuelve en un array
      * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return array de numeros primos
     */
    public static int[] generarNumerosPrimosAzarEntre(int minimo, int maximo){
        
        int numerosPrimos[]=new int [cuentaPrimosEntre(minimo, maximo)];
        
        if(numerosPrimos.length==0){
            JOptionPane.showMessageDialog(null, "No hay primos entre " +minimo+" y "+maximo, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }else{
            
            for (int i = minimo,indice=0;i<maximo;i++){
                if (esPrimo(i)){
                    numerosPrimos[indice]=i;
                    indice++;
                }
            }
        }
        
        return numerosPrimos;
    }
    
    
    /**
     * Cuenta el numero de primo que hay entre el minimo y el maximo incluidos
     * @param minimo Número mínimo
     * @param maximo Número máximo
     * @return numero de primos entre el minimo y el maximo
     */
    private static int cuentaPrimosEntre(int numero1, int numero2){
        
        if (numero2 > numero1){

            int aux= numero1;
            numero1 = numero2;
            numero2 = aux;

        }
        
        int contador=0;
        
        for(int i=numero1;i<=numero2;i++){
            if (esPrimo(i)){
                contador++;
            }
        }
        
        return contador;
    }
    
    
}
