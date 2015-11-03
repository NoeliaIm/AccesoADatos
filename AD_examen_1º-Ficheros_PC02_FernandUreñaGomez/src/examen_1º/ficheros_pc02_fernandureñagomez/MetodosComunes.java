/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1º.ficheros_pc02_fernandureñagomez;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public class MetodosComunes {
    
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
    
    
    
    //Copia el contenido de un fichero a un textarea
    public static void deFicheroATextArea(JTextArea textArea, File rutaFichero){
        
        String contenido=leeFicheroCaracteresBuf(rutaFichero);
        
        textArea.setText(contenido);
        
    }
    
    
    public static int numeroCaracteres(String cadena, char caracter){
        
        int contador=0;
        
        for(int i=0;i<cadena.length();i++){
            
            char caracterActual=cadena.charAt(i);
            
            if(caracter==caracterActual){
                contador++;
            }
            
        }
        
        return contador;
        
    }
    
    
    public static void rellenaComboBox(JComboBox cmb, String datos[]){
        
        for(int i=0;i<datos.length;i++){
            cmb.addItem(datos[i]);
        }
        
    }
    
    public static void escribirSoloLetrasYEspacios(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE))){
            evt.consume();
            etiquetaError.setText("Escribe solo letras y espacios");
        }else{
            etiquetaError.setText("");
        }
    }
    
    public static void escribirSoloNumeros(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo numeros");
       }else{
            etiquetaError.setText("");
       }
    }
    
    public static void noEscribirMasDe(JTextField campo, int longitudMaxima,KeyEvent evt, JLabel etiquetaError){
        if(campo.getText().length()>=longitudMaxima){
                evt.consume();
                etiquetaError.setText("No mas de "+longitudMaxima+" caracteres");
       }else{
            etiquetaError.setText("");
        }
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
    
    public static String comprobarVacios(Object matriz[][]){
        
        String error="";
        
        for(int i=0;i<matriz[0].length;i++){
            JTextField c=(JTextField)matriz[0][i];
            
            if(c.getText().isEmpty()){
                error+="-"+(String)matriz[1][i]+"\n";
            }
        }
        
        return error;
        
    }
    
    public static void limpiaCampos(JTextField campos[]){
        
        for(int i=0;i<campos.length;i++){
            campos[i].setText("");
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
    
    
}
