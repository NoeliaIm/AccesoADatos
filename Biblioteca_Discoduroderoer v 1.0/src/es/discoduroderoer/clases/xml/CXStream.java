/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.discoduroderoer.clases.xml;

import com.thoughtworks.xstream.XStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 * @author DiscoDurodeRoer
 * @param <T> Clase con la que rellenaremos el XML
 */
public class CXStream<T> {
    
    private Lista<T> lista; //Se necesita la clase Lista, tambien una clase que manejaremos (T) 
    private XStream xstream;
    private String nombreRaiz;
    private String nombreElemento;
    private String clase; 
    
    /**
     * Constructor
     * @param pNombreRaiz Nombre de la raíz
     * @param pNombreElemento Nombre del elemento
     * @param pClase Nombre de la clase usada
     */
    public CXStream(String pNombreRaiz, String pNombreElemento, String pClase){
        
        this.lista=new Lista<>();
        this.xstream = new XStream();
        
        this.nombreRaiz=pNombreRaiz;
        this.nombreElemento=pNombreElemento;
        
        this.clase=pClase;
    }
    
    /**
     * Copiamos la información necesaria al fichero
     * @param nombreFicheroOriginal Ruta del fichero XML
     */
    private void copiaInformacionFichero(String nombreFicheroOriginal){
        
        try (ObjectInputStream objetos=new ObjectInputStream(new FileInputStream(new File(nombreFicheroOriginal)))){
            while (true) {
                T objetoActual=(T) objetos.readObject();
                lista.anadir(objetoActual);
            }
        }catch (EOFException e) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    /**
     * Crea el fichero
     * @param nombreFicheroXML Ruta fichero XML donde estan los elementos a insertar 
     * @param nombreFicheroOriginal Ruta fichero XML Original 
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void creaFichero(String nombreFicheroXML, String nombreFicheroOriginal){
        
        copiaInformacionFichero(nombreFicheroOriginal);
       
        try {
            
            //cambiar de nombre a las etiquetas XML
            xstream.alias(nombreRaiz, Lista.class);
           
            xstream.alias(nombreElemento, Class.forName(clase));
            
            //quitar etiqueta lista
            xstream.addImplicitCollection(Lista.class, "lista");
            
            //Insertar los objetos en el XML
            xstream.toXML(this.lista, new FileOutputStream(nombreFicheroXML));
            
        }catch (ClassNotFoundException | FileNotFoundException e){
            e.printStackTrace();
        } 
    }
    
    /**
     * Muestra el contenido en un textarea
     * @param textArea TextArea usado
     * @param nombreXML Ruta fichero
     */
    public void mostrarFicheroTextArea(JTextArea textArea, String nombreXML){
        
        //cambiar de nombre a las etiquetas XML
        xstream.alias(nombreRaiz, lista.getClass()); 
        try {
            xstream.alias(nombreElemento, Class.forName(clase));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        xstream.addImplicitCollection(Lista.class, "lista");
        
        try {
            lista = (Lista) xstream.fromXML(new FileInputStream(nombreXML));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator iterador = lista.getLista().listIterator();
        
        textArea.setText("");
        
        //Recorro la lista con el iterador que acabo de crear
        while( iterador.hasNext() ) {
            //Obtengo el elemento contenido
            T objetoActual = (T) iterador.next();
            textArea.append(objetoActual.toString());
        }
    }
    
    /**
     * Muestra el contenido del XML en un fichero de texto
     * @param fichero Nombre del fichero
     * @param nombreXML Nombre del fichero XML 
     */
    public void mostrarFicheroTexto(File fichero, String nombreXML){
        
        //cambiar de nombre a las etiquetas XML
        xstream.alias(nombreRaiz, lista.getClass()); 
        
        try {
            xstream.alias(nombreElemento, Class.forName(clase));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        xstream.addImplicitCollection(Lista.class, "lista");
        
        try {
            lista = (Lista) xstream.fromXML(new FileInputStream(nombreXML));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CXStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator iterador = lista.getLista().listIterator();
        
        try(PrintWriter pw=new PrintWriter(fichero)){
            
            //Recorro la lista con el iterador que acabo de crear
            while( iterador.hasNext() ) {
                //Obtengo el elemento contenido
                T objetoActual = (T) iterador.next();
                pw.println(objetoActual.toString());
            }
        }catch(IOException e){
            
        }
    }
    
}
