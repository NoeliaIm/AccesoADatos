/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.ficheros;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import milibreria.Array.Array;
import milibreria.Clases.MiObjectOutputStream;

/**
 *
 * @author Fernando
 */
public class Ficheros {
    
    
    /**
     * Devuelve un String con el contenido del fichero
     * @param rutaFichero
     * @return 
     */
    public static String leeFicheroCaracteres(String rutaFichero){
        
        String contenido="";
        try(FileReader fr=new FileReader(rutaFichero)){
            
            int c;
            while((c=fr.read())!= -1){
                contenido+=(char)c;
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return contenido;
    }
    
    /**
     * Devuelve un String con el contenido del fichero
     * @param fichero
     * @return 
     */
    public static String leeFicheroCaracteres(File fichero){
        
        String contenido="";
        try(FileReader fr=new FileReader(fichero)){
            
            int c;
            while((c=fr.read())!= -1){
                contenido+=(char)c;
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return contenido;
    }
    
    /**
     * Devuelve un String con el contenido del Fichero usando Bufferes
     * @param rutaFichero
     * @return 
     */
    public static String leeFicheroCaracteresBuf(String rutaFichero){
        
        String contenido="";
        try(BufferedReader br=new BufferedReader(new FileReader(rutaFichero))){
            
            String c;
            while((c=br.readLine())!= null){
                contenido+=c+"\n";
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return contenido;
    }
    
    /**
     * Devuelve un String con el contenido del Fichero usando Bufferes
     * @param fichero
     * @return 
     */
    public static String leeFicheroCaracteresBuf(File fichero){
        
        String contenido="";
        try(BufferedReader br=new BufferedReader(new FileReader(fichero))){
            
            String c;
            while((c=br.readLine())!= null){
                contenido+=c+"\n";
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return contenido;
    }
    
    /**
     * Escribe el String pasado a un fichero
     * @param rutaFichero
     * @param contenido
     * @param anadir 
     */
    public static void escribirFicheroCaracteres(String rutaFichero, String contenido, boolean anadir){
        
        try(FileWriter fw=new FileWriter(rutaFichero, anadir)){
            
            fw.write(contenido);
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Escribe el String pasado a un fichero
     * @param rutaFichero
     * @param contenido
     * @param anadir 
     */
    public static void escribirFicheroCaracteres(File rutaFichero, String contenido, boolean anadir){
        
        try(FileWriter fw=new FileWriter(rutaFichero, anadir)){
            
            fw.write(contenido);
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Escribe el String pasado a un fichero con buferes
     * @param rutaFichero
     * @param contenido
     * @param anadir 
     */
    public static void escribirFicheroCaracteresBuf(String rutaFichero, String contenido, boolean anadir){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(rutaFichero, anadir))){
            
            bw.write(contenido);
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Escribe el String pasado a un fichero con buferes
     * @param rutaFichero
     * @param contenido
     * @param anadir 
     */
    public static void escribirFicheroCaracteresBuf(File fichero, String contenido, boolean anadir){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichero, anadir))){
            
            bw.write(contenido);
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Escribe el String pasado a un fichero, respetando los espacios
     * @param rutaFichero
     * @param contenido
     * @param anadir 
     */
    public static void escribirFicheroCaracteresEspacios(String rutaFichero, String contenido, boolean anadir){
        
        try(PrintWriter pw=new PrintWriter(new FileWriter(rutaFichero, anadir))){
            
            pw.write(contenido);
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public static void escribirFicheroCaracteresEspacios(File fichero, String contenido, boolean anadir){
        
        try(PrintWriter pw=new PrintWriter(new FileWriter(fichero, anadir))){
            
            for(int i=0;i<contenido.length();i++){
                
                char caracter=contenido.charAt(i);
                
                if(String.valueOf(caracter).equals("\n")){
                    pw.println();
                }else{
                    pw.print(caracter);
                }
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //escribo el contenido de una matriz de String en un fichero( respeto espacios para que se vea mejor)
    public static void escribirFicherosCaracteresMatrices(File fichero, String matriz[][]){
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                escribirFicheroCaracteresBuf(fichero, matriz[i][j]+" ", true);
            }
        }
        
    }
    
    //Sobrescribe el fichero, en caso de volverse a seleccionar
    //Respeta espacios, gracias a printWriter
    public static void escribirFicherosCaracteresMatricesConEspacios(File fichero, String matriz[][]){
        
        try(PrintWriter pw=new PrintWriter(fichero)){
             for(int i=0;i<matriz.length;i++){
                for(int j=0;j<matriz[0].length;j++){
                    
                    pw.print(matriz[i][j]+"     ");
                    
                }
                pw.println();
            }
        }catch(IOException e){
            
        }
        
    }
    
    public static void copiarFicherosCaracteres(String rutaFicheroOrigen, String rutaFicheroDestino){
        
        String contenido=leeFicheroCaracteresBuf(rutaFicheroOrigen);
        escribirFicheroCaracteresBuf(rutaFicheroDestino, contenido, false);
       
    }
    
    public static void copiarFicherosCaracteres(File ficheroOrigen, File ficheroDestino){
        
        String contenido=leeFicheroCaracteresBuf(ficheroOrigen);
        escribirFicheroCaracteresBuf(ficheroDestino, contenido, false);
       
    }
    
    
    //Devuelve el numero de filas que hay en un fichero de texto
    public static int numeroLineasFicheroCaracteres(String rutaFichero){
        
        int contador=0;
        try(BufferedReader br=new BufferedReader(new FileReader(rutaFichero))){
            
            
            while(br.readLine()!=null){
                contador++;    
            }
            
        }catch(EOFException e ){
            
        }catch(IOException e ){
            
        }
        return contador;
    }
    
    public static char[] deFicheroTextoACaracteres(String rutaFichero){
        
        String contenido=leeFicheroCaracteresBuf(rutaFichero);
        
        char caracteres[]=contenido.toCharArray();
        
        return caracteres;
    }
    
    public static char[] deFicheroTextoACaracteres(File fichero){
        
        String contenido=leeFicheroCaracteresBuf(fichero);
        
        char caracteres[]=contenido.toCharArray();
        
        return caracteres;
        
    }
    
    public static String[] deFicheroAPalabras(String rutaFichero){
        
        String contenido=leeFicheroCaracteresBuf(rutaFichero);
        
        StringTokenizer srText = new StringTokenizer(contenido);
        
        String palabras[]=new String[srText.countTokens()];
        
        for(int i=0;srText.hasMoreElements();i++){
            palabras[i]=srText.nextElement().toString();
        }
        
        return palabras;
        
    }
    
    public static void deArrayAFicheroCaracteres(String rutaFichero, String[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(rutaFichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
                
            }
            
        }catch(IOException e){
            
        }
        
    }
    
    public static void deArrayAFicheroCaracteres(File fichero, String[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
        
        
    }
    
    public static void deArrayAFicheroCaracteres(String rutaFichero, int[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(rutaFichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
        
    }
    
    public static void deArrayAFicheroCaracteres(File fichero, int[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
    }
    
    public static void deArrayAFicheroCaracteres(String rutaFichero, double[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(rutaFichero))){
            
            for(int i=0;i<array.length;i++){
                bw.write(array[i]+separador);
            }
            
        }catch(IOException e){
            
        }
    }
    
    public static void deArrayAFicheroCaracteres(File fichero, double[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]+separador);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
    }
    
    public static void deArrayAFicheroCaracteres(String rutaFichero, char[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(rutaFichero))){
            
            for(int i=0;i<array.length;i++){
                if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
    }
    
    public static void deArrayAFicheroCaracteres(File fichero, char[] array, String separador){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fichero))){
            
            for(int i=0;i<array.length;i++){
               if (separador.equals("\n")){
                    bw.write(array[i]);
                    bw.newLine();
                }else{
                    bw.write(array[i]+separador);
                }
            }
            
        }catch(IOException e){
            
        }
    }
    
    //Devuelve todo el contenido del fichero
    public static String[] deFicheroAPalabras(File fichero){
        
        String contenido=leeFicheroCaracteresBuf(fichero);
        
        StringTokenizer srText = new StringTokenizer(contenido);
        
        String palabras[]=new String[srText.countTokens()];
        
        for(int i=0;srText.hasMoreElements();i++){
            palabras[i]=srText.nextElement().toString();
        }
        
        return palabras;
        
    }
    
    //Copia el contenido de un textarea a un fichero
    public static void deTextAreaAFichero(JTextArea textArea, String rutaFichero){
        
        escribirFicheroCaracteresEspacios(rutaFichero, textArea.getText(), false);
        
    }
    
    //Copia el contenido de un textarea a un fichero
    public static void deTextAreaAFichero(JTextArea textArea, File fichero){
        
        escribirFicheroCaracteresEspacios(fichero, textArea.getText(), false);
        
    }
    
    //Copia el contenido de un fichero a un textarea
    public static void deFicheroATextArea(JTextArea textArea, String rutaFichero){
        
        String contenido=leeFicheroCaracteresBuf(rutaFichero);
        
        textArea.setText(contenido);
        
    }
    
    //Copia el contenido de un fichero a un textarea
    public static void deFicheroATextArea(JTextArea textArea, File fichero){
        
        String contenido=leeFicheroCaracteresBuf(fichero);
        
        textArea.setText(contenido);
        
        
    }
    
    public static String ficherosRecursivos(File f, String separador){
        File[] ficheros = f.listFiles();
        
        String contenido="";
                for (int x=0;x<ficheros.length;x++){                    
                        contenido+=separador + ficheros[x].getName();
                        
                        if (ficheros[x].isDirectory()){
                                String nuevo_separador;
                                nuevo_separador = separador + " ";
                                ficherosRecursivos(ficheros[x],nuevo_separador);
                        }
                }
        
        return contenido;
    }
    
    public static void ficherosRecursivosFicheroTexto(File f, String separador, File destino){
        
        String contenido=ficherosRecursivos(f,separador);
        
        Ficheros.escribirFicheroCaracteres(destino, contenido, false);
        
    }
    
    
    public static void escribirConDOS(String rutaFichero,Object[] datos){
        
        String tipos[]=Array.tiposDatos(datos);
        
        try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(rutaFichero, true))){
            
            for(int i=0;i<tipos.length;i++){
                
                switch(tipos[i]){
                    case "Byte":
                        dos.writeByte((Byte)datos[i]);
                        break;
                    case "Short":
                        dos.writeShort((Short)datos[i]);
                        break;
                    case "Integer":
                        dos.writeInt((int)datos[i]);
                        break;
                    case "Long":
                        dos.writeLong((Long)datos[i]);
                        break;
                    case "Character":
                        dos.writeChar((char)datos[i]);
                        break;
                    case "String":
                        dos.writeUTF((String)datos[i]);
                        break;
                    case "Double":
                        dos.writeDouble((Double)datos[i]);
                        break;
                    case "Float":
                        dos.writeFloat((Float)datos[i]);
                        break;
                    case "Boolean":
                        dos.writeBoolean((Boolean)datos[i]);
                        break;   
                }
                
            }
        }catch(IOException e){
            
        }
        
    }
    
    public static void escribirConDOS(File fichero,Object[] datos){
        
        String tipos[]=Array.tiposDatos(datos);
        
        try(DataOutputStream dos=new DataOutputStream(new FileOutputStream(fichero,true))){
            
            for(int i=0;i<tipos.length;i++){
                
                switch(tipos[i]){
                    case "Byte":
                        dos.writeByte((Byte)datos[i]);
                        break;
                    case "Short":
                        dos.writeShort((Short)datos[i]);
                        break;
                    case "Integer":
                        dos.writeInt((int)datos[i]);
                        break;
                    case "Long":
                        dos.writeLong((Long)datos[i]);
                        break;
                    case "Character":
                        dos.writeChar((char)datos[i]);
                        break;
                    case "String":
                        dos.writeUTF((String)datos[i]);
                        break;
                    case "Double":
                        dos.writeDouble((Double)datos[i]);
                        break;
                    case "Float":
                        dos.writeFloat((Float)datos[i]);
                        break;
                    case "Boolean":
                        dos.writeBoolean((Boolean)datos[i]);
                        break;   
                }
                
            }
        }catch(IOException e){
            
        }
        
    }
    
    
    public static String leerConDIS(String rutaFichero,Object[] datos){
        
        String tipos[]=Array.tiposDatos(datos);
        
        String contenido="";
        
        try(DataInputStream dis=new DataInputStream(new FileInputStream(rutaFichero))){
            
            for(int i=0;true;i++){
                
                if(tipos.length==i){
                    i=0;
                }
                
                switch(tipos[i]){
                    case "Byte":
                        contenido+=dis.readByte()+"\n";
                        break;
                    case "Short":
                        contenido+=dis.readShort()+"\n";
                        break;
                    case "Integer":
                        contenido+=dis.readInt()+"\n";
                        break;
                    case "Long":
                        contenido+=dis.readLong()+"\n";
                        break;
                    case "Character":
                        contenido+=dis.readChar()+"\n";
                        break;
                    case "String":
                        contenido+=dis.readUTF()+"\n";
                        break;
                    case "Double":
                        contenido+=dis.readDouble()+"\n";
                        break;
                    case "Float":
                        contenido+=dis.readFloat()+"\n";
                        break;
                    case "Boolean":
                        contenido+=dis.readBoolean()+"\n";
                        break;   
                }
                
                
            }
        }catch(EOFException e){
            
        }catch(IOException e){
            
        }
        
        return contenido;
    }
    
    public static String leerConDIS(File fichero,Object[] datos){
        
        String tipos[]=Array.tiposDatos(datos);
        
        String contenido="";
        
        try(DataInputStream dis=new DataInputStream(new FileInputStream(fichero))){
            
            for(int i=0;true;i++){
                
                switch(tipos[i]){
                    case "Byte":
                        contenido+=dis.readByte()+"\n";
                        break;
                    case "Short":
                        contenido+=dis.readShort()+"\n";
                        break;
                    case "Integer":
                        contenido+=dis.readInt()+"\n";
                        break;
                    case "Long":
                        contenido+=dis.readLong()+"\n";
                        break;
                    case "Character":
                        contenido+=dis.readChar()+"\n";
                        break;
                    case "String":
                        contenido+=dis.readUTF()+"\n";
                        break;
                    case "Double":
                        contenido+=dis.readDouble()+"\n";
                        break;
                    case "Float":
                        contenido+=dis.readFloat()+"\n";
                        break;
                    case "Boolean":
                        contenido+=dis.readBoolean()+"\n";
                        break;   
                }
                
            }
        }catch(EOFException e){
            
        }catch(IOException e){
            
        }
        
        return contenido;
    }
    
//    //Comprobar que funciona bien
//    public static byte[] leerFicheroBinario(String rutaFichero){
//        
//        byte contenido[]=null;
//        try(FileInputStream fis=new FileInputStream(rutaFichero)){
//            
//            contenido=new byte[fis.available()];
//            fis.read();
//            
//        }catch(FileNotFoundException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }catch(IOException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//        return contenido;
//    }
    
    
//     //Comprobar que funciona bien
//    public static byte[] leerFicheroBinario(File fichero){
//        
//        byte contenido[]=null;
//        try(FileInputStream fis=new FileInputStream(fichero)){
//            
//            contenido=new byte[4096];
//            fis.read(contenido);
//            
//            noOfBytes = fin.read(b);
//            
//            
//        }catch(FileNotFoundException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }catch(IOException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//        
////        try(FileInputStream fin = new FileInputStream(fichero)){
////                
////		byte[] b = new byte[4096];
////		int noOfBytes = 0;
////
////                //for(int i=0;i<b.length;i++){
////			fin.read(b);
////                //}
////         }catch(IOException e){
////             
////         }
//        
//        
//        
//        return contenido;
//        
//    }
//     //Comprobar que funciona bien
//    public static void escribeFicheroBinario(String rutaFichero, byte[] contenido, boolean anadir){
//        
//        try(FileOutputStream fos=new FileOutputStream(rutaFichero, anadir)){
//            
//            fos.write(contenido);
//            
//        }catch(FileNotFoundException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }catch(IOException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        
//    }
//    
//     //Comprobar que funciona bien
//    public static void escribeFicheroBinario(File fichero, byte[] contenido, boolean anadir){
//        
//        try(FileOutputStream fos=new FileOutputStream(fichero, anadir)){
//            
//            fos.write(contenido);
//            
//        }catch(FileNotFoundException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }catch(IOException e){
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        
//        
//    }
    
    public static void copiarFicherosBinarios(String rutaOrigen, String rutaDestino){
        
         try(FileInputStream fin = new FileInputStream(rutaOrigen);
                  FileOutputStream fout = new FileOutputStream(rutaDestino)){
                
		byte[] b = new byte[4096];
		int noOfBytes = 0;

                while( (noOfBytes = fin.read(b)) != -1 ){
			fout.write(b, 0, noOfBytes);
                }
         }catch(IOException e){
             
         }
        
    }
    
    public static void copiarFicherosBinarios(File rutaOrigen, File rutaDestino){
        
          try(FileInputStream fin = new FileInputStream(rutaOrigen);
                  FileOutputStream fout = new FileOutputStream(rutaDestino)){
                
		byte[] b = new byte[4096];
		int noOfBytes = 0;

                while( (noOfBytes = fin.read(b)) != -1 ){
			fout.write(b, 0, noOfBytes);
                }
         }catch(IOException e){
             
         }
        
    }
    
    public static void mezclarFicherosCaracteres(String rutaFichero1, String rutaFichero2, String rutaFicheroFinal){
        
        String contenido=leeFicheroCaracteresBuf(rutaFichero1)+leeFicheroCaracteresBuf(rutaFichero2);
        escribirFicheroCaracteresBuf(rutaFicheroFinal, contenido, false);
        
    }
    
    public static void  escribirFicheroSerializado(File fichero, Object objeto){
        
        try{  
            ObjectOutputStream oos;
            MiObjectOutputStream moos;
           
            if(fichero.exists()){
                moos=new MiObjectOutputStream(new FileOutputStream(fichero, fichero.exists()));
                moos.writeObject(objeto);
                moos.close();
            }else{
                oos=new ObjectOutputStream(new FileOutputStream(fichero, fichero.exists())); 
                oos.writeObject(objeto);
                oos.close();
            }

        }catch(IOException e){
            
        }
    }
    
    
    public static void  escribirFicheroSerializado(String rutaFichero, Object objeto){
        
        File fichero=new File(rutaFichero);
        
        try{  
            ObjectOutputStream oos;
            MiObjectOutputStream moos;
           
            if(fichero.exists()){
                moos=new MiObjectOutputStream(new FileOutputStream(fichero, fichero.exists()));
                moos.writeObject(objeto);
                moos.close();
            }else{
                oos=new ObjectOutputStream(new FileOutputStream(fichero, fichero.exists())); 
                oos.writeObject(objeto);
                oos.close();
            }

        }catch(IOException e){
            
        }
    }
    
    //Debes crear un metodo toString para personalizar los datos
    public static String leerFicheroBinarioSerializado(File fichero){
        
        String texto="";
        if(fichero.exists()){
            try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero))){
               
            while(true){
                Object objeto= ois.readObject();
                
                texto+=objeto.toString();
            }
           
            }catch(ClassNotFoundException e){
                
            }catch(EOFException e){
                  
            }catch(IOException e){
                  
            }
        }
        return texto;
    }
    
     //Debes crear un metodo toString para personalizar los datos
    public static String leerFicheroBinarioSerializado(String rutaFichero){
        
        File fichero=new File(rutaFichero);
        
        String texto="";
        if(fichero.exists()){
            try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero))){
               
            while(true){
                Object objeto= ois.readObject();
                
                texto+=objeto.toString();
            }
           
            }catch(ClassNotFoundException e){
                
            }catch(EOFException e){
                  
            }catch(IOException e){
                  
            }
        }
        return texto;
    }
    
    //Se necesita el metodo equals sobrescito
    //En caso de fallo, copiar metodo y cambiar Object por la clase
    public static boolean comprobarObjetoExistente(File fichero, Object objeto){
        
        boolean existe=false;
        
        if(fichero.exists()){
            try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fichero))){
               
            while(true){
                Object objetoActual= ois.readObject();
                
                if(objeto.equals(objetoActual)){
                    existe=true;
                }
            }
           
            }catch(ClassNotFoundException e){
                System.out.println("Clase no encontrada");
            }catch(EOFException e){
                  
            }catch(IOException e){
                  System.out.println(e.getMessage());
            }
        }
        
        return existe;
        
    }
    
    
    
    
    //Ficheros aleatorios
    
    /*
     * Usado para devolver la posicion del registro que queramos
     */
    public static long posicionRegistro(int id, int tamanioRegistro){
        
        return (id-1)*tamanioRegistro;
        
    }
    
}
