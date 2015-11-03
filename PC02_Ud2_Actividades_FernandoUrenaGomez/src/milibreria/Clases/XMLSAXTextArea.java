/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Alumno
 */
public class XMLSAXTextArea extends DefaultHandler{
    
    private JTextArea textArea;
    
    public XMLSAXTextArea(JTextArea textArea){
        super () ;
        this.textArea=textArea;
    }
    public void startDocument() {
        textArea.append("Comienzo del Documento XML\n");
    }
    public void endDocument() {
        textArea.append("Final del Documento XML"+"\n");
    }
    public void startElement(String uri, String nombre,String nombreC, Attributes atts) {
        textArea.append("\tPrincipio Elemento: .. "+ nombre+"\n");
    }
    public void endElement(String uri, String nombre, String nombreCl){
        textArea.append("\tFin Elemento: " + nombre+"\n");
    }
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car=new String(ch,inicio,longitud);
        car = car.replaceAll("[\t\n]", ""); //quitar saltos de línea
        textArea.append("\t\tCaracteres: " + car+"\n");
    }
    
    public void leerFichero(String fichero){
        
        XMLReader procesadorXML;
            try {
                procesadorXML = XMLReaderFactory.createXMLReader();
                
                procesadorXML.setContentHandler(this);
                InputSource fileXML = new InputSource (fichero);
                procesadorXML.parse(fileXML);
            } catch (    SAXException | IOException ex) {
               
            }
        
    }
    
}
