/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.matrices;
import milibreria.Array.Array;
import milibreria.Numeros.Numeros;
/**
 *
 * @author Fernando
 */
public class Matrices {
    
    public static void mostrarMatriz(int matriz[][]){
        
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static void mostrarMatriz(long matriz[][]){
        
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static void mostrarMatriz(String matriz[][]){
        
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static void mostrarMatriz(double matriz[][]){
        
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static void rellenarMatrizAleatorios(int matriz[][], int minimo, int maximo){
        
        for (int i=0;i<matriz.length;i++){
            for (int j=0;j<matriz[0].length;j++){
                matriz[i][j]=Numeros.generaNumeroAleatorio(minimo, maximo);
            }
        }
    }
    
    
    
    public static int[][] sumaMatrices (int tabla1[][], int tabla2[][]){
	
        int sumaMatrices[][]=new int[tabla1.length][tabla1[0].length];
        
        for (int i=0;i<sumaMatrices.length;i++){
            for (int j=0;j<sumaMatrices[0].length;j++){
                sumaMatrices[i][j]=tabla1[i][j]+tabla2[i][j];
            }
        }
        return sumaMatrices;
        
    }
    
    public static int[] adyacentesA(int matriz[][], int i, int j){
        
        int tamanio=matriz.length;
        
        int dirs[] = {1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0, -1, -1, 0, -1};
        
        int posicionesValidas[]=new int[17];

        int indice2=0;
        for(int indice=0;indice<dirs.length;indice+=2){
           if(i + dirs[indice] >= 0 && i + dirs[indice] < tamanio && j + dirs[indice + 1] >= 0 && j + dirs[indice + 1] < tamanio){
              posicionesValidas[indice2] = i + dirs[indice];
              posicionesValidas[indice2+1] = j + dirs[indice + 1];
              indice2 += 2;
           } 
        }
        
        return Array.redimensionar(posicionesValidas, indice2);
    }
    
    public static int[] adyacentesA(Object matriz[][], int i, int j){
        
        int tamanio=matriz.length;
        
        int dirs[] = {1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0, -1, -1, 0, -1};
        
        int posicionesValidas[]=new int[17];

        int indice2=0;
        for(int indice=0;indice<dirs.length;indice+=2){
           if(i + dirs[indice] >= 0 && i + dirs[indice] < tamanio && j + dirs[indice + 1] >= 0 && j + dirs[indice + 1] < tamanio){
              posicionesValidas[indice2] = i + dirs[indice];
              posicionesValidas[indice2+1] = j + dirs[indice + 1];
              indice2 += 2;
           } 
        }
        
        return Array.redimensionar(posicionesValidas, indice2);
    }
    
    //posicionar un elemento en una matriz pasandoles la posicion
    public static void posicionarElementoMatriz(Object matriz[][], int posicion, Object objeto){

        int tamanio = matriz.length;

        matriz[(int)Math.floor(posicion / tamanio)][posicion % tamanio] = objeto;

}

    //posiciona y devuelve donde lo haya posicionado
    public static int[] devolverPosicionMatriz(Object matriz[][], int posicion, Object objeto){

        int posiciones[]=new int[2];

        int tamanio = matriz.length;

        matriz[(int)Math.floor(posicion / tamanio)][posicion % tamanio] = objeto;

        posiciones[0] = (int)Math.floor(posicion / tamanio);
        posiciones[1] = posicion % tamanio;

        return posiciones;

    }

    //posicionar un elemento en una matriz aleatoriamente
    public static void posicionarElementoMatriz(Object matriz[][], Object objeto){

        int tamanio = matriz.length;
        int posicion = Numeros.generaNumeroAleatorio(0, (tamanio * tamanio) - 1);

        matriz[(int)Math.floor(posicion / tamanio)][posicion % tamanio] = objeto;

    }
}
