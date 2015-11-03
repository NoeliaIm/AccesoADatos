/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Array;
import java.util.Arrays;
import milibreria.Numeros.Numeros;
/**
 *
 * @author Fernando
 */
public class Array {
    
    public static void mostrarArray(int array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void mostrarArray(String array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void mostrarArray(double array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void mostrarArray(long array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void mostrarArray(char array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void rellenaArray(int array[]){
        
        for(int i=0;i<array.length;i++){
            array[i]=Numeros.validarNumero();
        }
    }
    
    public static void mostrarArray(byte array[]){
        
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
    
    public static void rellenaArrayAleatorios(int array[], int minimo, int maximo){
        
        for(int i=0;i<array.length;i++){
            array[i]=Numeros.generaNumeroAleatorio(minimo, maximo);
        }
    }
    
    public static boolean EsCapicua (int lista[]){
		//creamos otro array
		int listaprueba[];
		listaprueba=new int [lista.length];
		
		//asignamos los valores al nuevo array
		//lo hacemos añadiendo los ultimos valores del primer array, al principio del nuevo array
		for (int i=0, j=1;j<=lista.length;i++, j++){
			listaprueba[i]=lista[lista.length-j];
		}
		//mensajes de traza
		for (int i=0;i<lista.length;i++){
			System.out.println(">>" +listaprueba[i]);
		}
		//retorno de valores
		if (Arrays.equals(lista, listaprueba)){
			return true;
		}
		
		return false;
		
     }
    
    public int sumaArray(int array[]){
        
        int suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public long sumaArray(long array[]){
        
        long suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public double sumaArray(double array[]){
        
        double suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public double mediaArray(int array[]){
        
        int suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public double mediaArray(double array[]){
        
        double suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public double mediaArray(long array[]){
        
        long suma=0;
        for(int i=0;i<array.length;i++){
           suma+=array[i];
        }
        
        return suma;
    }
    
    public static int[] redimensionar(int array[], int nuevaLongitud){
        
        int temp[]=new int[nuevaLongitud];
        
        if(array.length>nuevaLongitud){
            
            for(int i=0;i<temp.length;i++){
                temp[i]=array[i];
            }
            
        }else{
            
            for(int i=0;i<array.length;i++){
                temp[i]=array[i];
            }
        
        }
        
        return temp;
       
    }
    
    public static String[] redimensionar(String array[], int nuevaLongitud){
        
        String temp[]=new String[nuevaLongitud];
        
        if(array.length>nuevaLongitud){
            
            for(int i=0;i<temp.length;i++){
                temp[i]=array[i];
            }
            
        }else{
            
            for(int i=0;i<array.length;i++){
                temp[i]=array[i];
            }
            
        }
        
        
        
        return temp;
        
    }
    
    public static double[] redimensionar(double array[], int nuevaLongitud){
        
        double temp[]=new double[nuevaLongitud];
        
        for(int i=0;i<temp.length;i++){
            temp[i]=array[i];
        }
        
        return temp;
        
    }
       
    public static int[] invertirArray(int array[]){
        
        int temp[]=new int[array.length];
        
        for(int i=temp.length-1, j=0;i>=0;i--, j++){
            temp[i]=array[j];
        }
        
        return temp;
    }
    
    public static void ordenacionBurbuja (int lista[]){
        int cuentaintercambios=0;
        for (boolean ordenado=false;!ordenado;){
                for (int i=0;i<lista.length-1;i++){
                    if (lista[i]>lista[i+1]){
                        int variableauxiliar=lista[i];
                        lista[i]=lista[i+1];
                        lista[i+1]=variableauxiliar;
                        cuentaintercambios++;
                    }
                }
                if (cuentaintercambios==0){
                    ordenado=true;
                }
                cuentaintercambios=0;
        }
    }
    
    public static void ordenacionQuicksort (int array[], int izq, int der){
        int i=izq;
        int j=der;
        int pivote=array[(i+j)/2];
        do {
            while (array[i]<pivote){
                i++;
            }
            while (array[j]>pivote){
                j--;
            }
            if (i<=j){
                int aux=array[i];
                array[i]=array[j];
                array[j]=aux;
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            ordenacionQuicksort(array, izq, j);
        }
        if (i<der){
            ordenacionQuicksort(array, i, der);
        }
    }
    
    public static void ordenacionQuicksort (String array[], int izq, int der){
        int i=izq;
        int j=der;
        int pivote=(i+j)/2;
        do {
            while (array[i].compareToIgnoreCase(array[pivote])<0){
                i++;
            }
            while (array[j].compareToIgnoreCase(array[pivote])>0){
                j--;
            }
            if (i<=j){
                String aux=array[i];
                array[i]=array[j];
                array[j]=aux;
                i++;
                j--;
            }
        }while(i<=j);
        if (izq<j){
            ordenacionQuicksort(array, izq, j);
        }
        if (i<der){
            ordenacionQuicksort(array, i, der);
        }
    }
    
    public static int buscarBin (int array[], int numerobuscado){
        int izq=0;
        int der=array.length;
        boolean encontrado=false;
        int pos=-1;

        for(int i=0;!encontrado && i<array.length;i++){
            int central=array[(izq+der)/2];
            if (central<=numerobuscado){
                if (central==numerobuscado){
                    encontrado=true;
                    pos=(izq+der)/2;
                }else{
                    izq=(izq+der)/2;
                }
            }else{
                der=(izq+der)/2;
            }
        }
        return pos;
    }
    
    public static int buscarBin (String array[], String cadenaBuscada){
        int izq=0;
        int der=array.length;
        boolean encontrado=false;
        int pos=-1;

        for(int i=0;!encontrado && i<array.length;i++){
            int central=(izq+der)/2;
            if (array[central].compareToIgnoreCase(cadenaBuscada)<=1){
                if (array[central].equals(cadenaBuscada)){
                    encontrado=true;
                    pos=(izq+der)/2;
                }else{
                    izq=(izq+der)/2;
                }
            }else{
                der=(izq+der)/2;
            }
        }
        return pos;
    }
    
    public static String[] tiposDatos(Object[] array){
        
        String tipos[]=new String[array.length];
        
        for(int i=0;i<array.length;i++){
            
            tipos[i]=devuelveTipoDato(array[i].getClass().getName());
            
        }
        
        return tipos;
        
    }
    
    private static String devuelveTipoDato(String tipoCompleto){
        
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
    
}
