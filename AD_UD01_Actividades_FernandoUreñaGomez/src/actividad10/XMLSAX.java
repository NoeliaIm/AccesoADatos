/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad10;

import java.io.IOException;
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
public class XMLSAX extends DefaultHandler{
    
    private JTextArea textArea;
    
    public XMLSAX(JTextArea textArea){
        super () ;
        this.textArea=textArea;
    }
    public void startDocument() {
        textArea.setText(textArea.getText()+"Comienzo del Documento XML\n");
    }
    public void endDocument() {
        //System.out.println("Final del Documento XML");
        textArea.setText(textArea.getText()+"Final del Documento XML"+"\n");
    }
    public void startElement(String uri, String nombre,String nombreC, Attributes atts) {
        //System.out.println("\tPrincipio Elemento: .. "+ nombre);
        textArea.setText(textArea.getText()+"\tPrincipio Elemento: .. "+ nombre+"\n");
    }
    public void endElement(String uri, String nombre, String nombreCl){
        //System.out.println ("\tFin Elemento: " + nombre);
        textArea.setText(textArea.getText()+"\tFin Elemento: " + nombre+"\n");
    }
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car=new String(ch,inicio,longitud);
        car = car.replaceAll("[\t\n]", ""); //quitar saltos de línea
        textArea.setText(textArea.getText()+"\t\tCaracteres: " + car+"\n");
        //System.out.println ("\t\tCaracteres: " + car);
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
