/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Numeros;

import java.util.Arrays;
import javax.swing.JOptionPane;
import milibreria.Array.Array;

/**
 *
 * @author Fernando
 */
public class Numeros {
    
    
    private static final char caracteresEspeciales[] = {'!', '%','&','(',')','*','/', '-','#','@'};
    
    /**
     * Genera un numero aleatorio entre dos numeros. NOTA: se incluye el minimo, pero no el maximo
     * @param minimo
     * @param maximo
     * @return numero entre minimo y maximo
     */
    public static int generaNumeroAleatorio(int minimo, int maximo){
        
        int num=(int)Math.floor(Math.random()*(minimo-maximo)+maximo);
        return num;
    }
    
    /**
     * Genera numeros aleatorios en un array, entre un minimo y un maximo
     * @param longitud
     * @param minimo
     * @param maximo
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
     * @param longitud
     * @param minimo
     * @param maximo
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
    
    //Es como anterior pero da mas libertad, no es necesaro que reparta los numeros entre el minimo y maximo
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
     * @param longitud
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
     * Cuenta el numero de cifras de un numero
     * @param num
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
     * Indica si un numero es primo
     * @param numero
     * @return booleano
     */
    public static boolean esPrimo(int numero){

            int contador=0;

            //bucle que cuenta los numeros divisibles
            for (int i=(int)Math.sqrt(numero);i>1;i--){
                    if (numero%i==0){
                            contador+=1;
                    }
            }
            if (contador>=1){
                    return false;
            }else{
                    return true;
            }
    }
    
    /**
     * Genera un numero primo entre el minimo y maximo
     * @param minimo
     * @param maximo
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
     * Cuenta el numero de primo que hay entre el minimo y el maximo
     * @param minimo
     * @param maximo
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
    
    /**
     * Genera numeros primos entre el maximo y el minimo, los devuelve en un array
     * @param minimo
     * @param maximo
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
     * Devuelve el factorial de un numero
     * @param num
     * @return factorial de num
     */
    public static int factorial(int num){
        
        int producto = num;
        for (int i = num - 1;i>0;i--){
            producto *= i;
        } 

        return producto;
    }
    
    /**
     * Valida si la cadena introducida es un numero, hasta que no sea correcto no se deja de preguntar
     * @return numero ya validado
     */
    public static int validarNumero(){
        boolean interruptor=true;
        
        int num=0; 

        do{
            try{
                String texto=JOptionPane.showInputDialog("Introduce un número");
                num=Integer.parseInt(texto);
                interruptor = false;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor);
           

        return num;
    }
    
    /**
     * Valida si la cadena introducida es un numero, hasta que no sea correcto no se deja de preguntar
     * @return numero ya validado
     */
    public static int validarNumero(String textoPersonalizado){
        boolean interruptor=true;
        
        int num=0; 

        do{
            try{
                String texto=JOptionPane.showInputDialog(textoPersonalizado);
                num=Integer.parseInt(texto);
                interruptor = false;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor);
           

        return num;
    }
    
    /**
     * Valida si el numero esta entre el minimo y el maximo
     * @param minimo
     * @param maximo
     * @return numero entre el minimo y el maximo
     */
    public static int validarNumeroEntre(int minimo, int maximo){
        boolean interruptor=true;
        
        int num=0; 

        do{
            try{
                String texto=JOptionPane.showInputDialog("Introduce un número");
                num=Integer.parseInt(texto);
                if(num < minimo || num > maximo){
                    JOptionPane.showMessageDialog(null, "Debes escribir un numero entre "+minimo+" y " +maximo, "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    interruptor = false;
                }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor && (num < minimo || num > maximo));
           

        return num;
    }
    
    /**
     * Valida si el numero es un numero positivo
     * @return numero mayor o igual que 0
     */
    public static int validarNumeroPositivo(){
        boolean interruptor=true;
        
        int num=-1; 

        do{
            try{
                String texto=JOptionPane.showInputDialog("Introduce un número");
                num=Integer.parseInt(texto);
                if(num < 0){
                    JOptionPane.showMessageDialog(null, "Debes escribir un numero mayor o igual que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    interruptor = false;
                }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor && (num < 0));
           

        return num;
    }
    
    /**
     * Valida si el numero es un numero positivo
     * @return numero mayor o igual que 0
     */
    public static int validarNumeroPositivo(String textoPersonalizado){
        boolean interruptor=true;
        
        int num=-1; 

        do{
            try{
                String texto=JOptionPane.showInputDialog(textoPersonalizado);
                num=Integer.parseInt(texto);
                if(num < 0){
                    JOptionPane.showMessageDialog(null, "Debes escribir un numero mayor o igual que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    interruptor = false;
                }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor && (num < 0));
           

        return num;
    }
    
    
    
    
    /**
     * Valida si el numero es negativo
     * @return numero negativo
     */
    public static int validarNumeroNegativo(){
        boolean interruptor=true;
        
        int num=0; 

        do{
            try{
                String texto=JOptionPane.showInputDialog("Introduce un número");
                num=Integer.parseInt(texto);
                if(num >= 0){
                    JOptionPane.showMessageDialog(null, "Debes escribir un numero menor que 0", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    interruptor = false;
                }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No es un numero, prueba de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(interruptor && (num >= 0));
           

        return num;
    }
    
    /**
     * Indica el numero de la serie fibonacci
     * @param numeroserie
     * @return numero fibonacci
     */
    public static int numeroFibonacci (int numeroserie){
        int num1=0, num2=1, suma=1;

        for (int i=1;i<numeroserie;i++){
            //primero sumamos
            suma=num1+num2;
            //Despues, cambiamos la segunda variable por la primera
            num1=num2;
            //Por ultimo, cambiamos la suma por la segunda variable
            num2=suma;
        }
        return suma;
		
    }
    
    public static char letraNIF(int DNI){
        
        if(cuentaCifras(DNI)>=7 && cuentaCifras(DNI)<=8){
            final int divisor=23;

            int res=DNI-(DNI/divisor*divisor);

            char letrasNIF[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y', 
                              'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 
                              'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

            return letrasNIF[res];
        }else{
            JOptionPane.showMessageDialog(null, "El DNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
		
     }
    
    public static String letraNIF(String DNI){
        
       if(DNI.length()>=7 && DNI.length()<=8){
           try{
                final int divisor=23;
                int dni=Integer.parseInt(DNI);
                int res=dni-(dni/divisor*divisor);

                char letrasNIF[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y', 
                                  'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 
                                  'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

                return DNI+letrasNIF[res];
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
       }else{
           JOptionPane.showMessageDialog(null, "El DNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
           return null;
       }
       
    }
    
    public static boolean comprobarDNI(String DNI){
        
        if(DNI.length()>=8 && DNI.length()<=9){
           try{
               
                boolean correcto=false;
                final int divisor=23;
                int dni=Integer.parseInt(DNI.substring(0, DNI.length()-1));
                int res=dni-(dni/divisor*divisor);
                
                char letrasNIF[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y', 
                                  'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 
                                  'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

                String DNICalculado=String.valueOf(dni)+letrasNIF[res];
                if(DNICalculado.equals(DNI)){
                    correcto=true;
                }
                
                return correcto;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       }else{
           JOptionPane.showMessageDialog(null, "El DNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
           return false;
       }
        
    }
    
    
    public static int binarioADecimal (String numerobinario) {
        
        int res=0;
        try{
            int longitud=numerobinario.length();
            boolean numeroCorrecto=true;
            for (int cont=0, posicion=longitud-1;cont<=(longitud-1) && numeroCorrecto;cont++, posicion--){
                if (numerobinario.charAt(cont)=='0' || numerobinario.charAt(cont)=='1'){
                    String texto=numerobinario.substring(cont, cont+1);
                    int numero=Integer.parseInt(texto);
                    res=res+(numero*((int)Math.pow(2 , posicion)));
                }else{
                    numeroCorrecto=false;
                }
            }
            
            if(numeroCorrecto){
                return res;
            }else{
                JOptionPane.showMessageDialog(null, "No has introducido un numero binario, solo puedes escribir 0 y 1", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }    
    }
    
    public static int binarioADecimal (int numerobinario) {
        
        String auxliar=String.valueOf(numerobinario);
        int res=0;
            int longitud=auxliar.length();
            boolean numeroCorrecto=true;
            for (int cont=0, posicion=longitud-1;cont<=(longitud-1) && numeroCorrecto;cont++, posicion--){
                if (auxliar.charAt(cont)=='0' || auxliar.charAt(cont)=='1'){
                    String texto=auxliar.substring(cont, cont+1);
                    int numero=Integer.parseInt(texto);
                    res=res+(numero*((int)Math.pow(2 , posicion)));
                }else{
                    numeroCorrecto=false;
                }
            }
            
            if(numeroCorrecto){
                return res;
            }else{
                JOptionPane.showMessageDialog(null, "No has introducido un numero binario, solo puedes escribir 0 y 1", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            
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
    
    public double[] ecuacion2Grado(int a, int b, int c){
        
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
    
    public static boolean esPotenciaDe(int numero, int pot){
        
        if(pot>1){
            
            int contador=-1;
            int aux=numero;

            for(int i=aux;i>0;i=(int)Math.floor(i)/pot){
                contador++;
            }

            if(numero==Math.pow(pot,contador)){
                return true;
            }else{
                return false;
            }
            
        }else{
            
            if(pot>=0){
                return true;
            }else{
                return false;
            }
            
        }
        
    }
    
    //-1 = igual a error
    public static int exponenteDe(int numero, int pot){
        
        
        if(pot>1){
            
            int contador=-1;
            int aux=numero;

            for(int i=aux;i>0;i=(int)Math.floor(i)/pot){
                contador++;
            }

            if(numero==Math.pow(pot,contador)){
                return contador;
            }else{
                return -1;
            }
            
        }else{
            if(numero==1 || numero==0){
                return numero;
            }else{
                return -1;    
            }
            
        }
        
        
        
    }
}
