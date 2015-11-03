/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Clases;

import java.io.*;
public class MiObjectOutputStream extends ObjectOutputStream{
    
    /** Constructor que recibe OutputStream */
    public MiObjectOutputStream(OutputStream out) throws IOException{
        super(out); //Llama al constructor de la superclase(OutputStream)
    }
    /** Constructor sin parámetros */
    protected MiObjectOutputStream() throws IOException, SecurityException{
        super(); //Llama al constructor de la superclase(OutputStream)
    }
    
    @Override
    protected void writeStreamHeader() throws IOException{
        //No hacemos nada
    }
}
