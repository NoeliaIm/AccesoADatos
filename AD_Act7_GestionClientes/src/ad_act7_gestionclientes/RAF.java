/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_act7_gestionclientes;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Fernando
 */
public class RAF {
    
    //private RandomAccessFile raf;
    private int tamanioRegistro; //Hay que calcularlo
    private File fichero;
    private String[][] informacion; //Contiene el tipo de dato(String, int,...) y su tamaño 
    
    public RAF(int pTamanioRegistro, File pFichero, String [][] pInformacion){
        
        fichero=pFichero;
        tamanioRegistro=pTamanioRegistro;
        informacion=pInformacion;
    }
    
    public long posicionRegistro(int id){
        
        return (id-1)*tamanioRegistro;
        
    }
    
    public long posicionFinal(){
        
        return fichero.length();
        
    }
    
    //distanciaDato es a cuantos bytes esta un dato concreto al que queramos acceder
    public long posicionDatoRegistro(int id, int distanciaDato){
        
        return ((id-1)*tamanioRegistro)+distanciaDato;
        
    }
    
    //Se pasan los datos que se quieran introducir
    public void anadir(int id,String datos[]){
        
        try(RandomAccessFile raf=new RandomAccessFile(fichero, "rw")){
            
            StringBuffer buffer;
            
            raf.seek(posicionRegistro(id));
            
            for(int i=0;i<datos.length;i++){
                
                String tipo=informacion[i][0];
                
                switch(tipo){
                    case "String":
                        buffer=new StringBuffer(datos[i]);
                        buffer.setLength(Integer.parseInt(informacion[i][1]));
                        raf.writeChars(buffer.toString());
                        break;
                    case "Integer":
                        raf.writeInt(Integer.parseInt(datos[i]));
                        break;
                    case "Float":
                        raf.writeFloat(Float.parseFloat(datos[i]));
                        break;
                    case "Short":
                        raf.writeShort(Short.parseShort(datos[i]));
                        break;
                    case "Double":
                        raf.writeDouble(Double.parseDouble(datos[i]));
                        break;
                    case "Boolean":
                        raf.writeBoolean(Boolean.parseBoolean(datos[i]));
                        break;
                    default:
                        System.out.println("error");
                }
            }
            
        }catch(IOException e){
            System.out.printf(e.getMessage());
        }
        
    }
    
    public String[] devolverDatos(int id){
        
        try(RandomAccessFile raf=new RandomAccessFile(fichero, "rw")){
            
            String[] datos=new String[informacion.length];
            
            raf.seek(posicionRegistro(id));
            
            for(int i=0;i<informacion.length;i++){
                
                String tipo=informacion[i][0];
                
                switch(tipo){
                    case "String":
                       
                        String dato="";
                        int limite=Integer.parseInt(informacion[i][1]);
                        for(int j=0;j<limite;j++){
                             dato+=raf.readChar();
                        }
                        
                        datos[i]=dato;
                        
                        break;
                    case "Integer":
                        datos[i]=String.valueOf(raf.readInt());
                        break;
                    case "Float":
                        datos[i]=String.valueOf(raf.readFloat());
                        break;
                    case "Short":
                        datos[i]=String.valueOf(raf.readShort());
                        break;
                    case "Double":
                        datos[i]=String.valueOf(raf.readDouble());
                        break;
                    case "Boolean":
                        datos[i]=String.valueOf(raf.readBoolean());
                        break;
                    default:
                        System.out.println("error");
                }
            }
            
            return datos;
            
        }catch(IOException e){
            return null;
        }
        
        
    }
    
    
    //suponiendo que para eliminar un registro, hay que cambiar un valor a true(normalmente es el ultimo)
    public void borrar(int id){
        
        try(RandomAccessFile raf=new RandomAccessFile(fichero, "rw")){
            
            long pos=(id-1)*tamanioRegistro;
            raf.seek(pos+(tamanioRegistro-1));
            
            raf.writeBoolean(true);
            
        }catch(IOException e){
            
        }
        
    }
    
    public boolean comprobarBorrado(int id){
        
        try(RandomAccessFile raf=new RandomAccessFile(fichero, "rw")){
            
            long pos=(id-1)*201;
            raf.seek(pos+200);
            
            return raf.readBoolean();
           
            
        }catch(IOException e){
            
        }
        return true;
    }
    
    //Solo si el el primer registro es un codigo numerico
    public boolean comprobarIDExistente(int id){
        
        boolean existe=false;
        
        try(RandomAccessFile raf=new RandomAccessFile(fichero, "rw")){
            
            long pos=(id-1)*201;
            raf.seek(pos);

            int idActual=raf.readInt();
            if(idActual==id){
                existe=true;
            }
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        return existe;
    }
    
}
