package actividad4;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class MiObjectOutputStream extends ObjectOutputStream
{
/** Constructor que recibe OutputStream */
public MiObjectOutputStream(OutputStream out) throws IOException
{
super(out); //Llama al constructor de la superclase(OutputStream)
}
/** Constructor sin parámetros */
protected MiObjectOutputStream() throws IOException, SecurityException
{
super(); //Llama al constructor de la superclase(OutputStream)
}
/** Redefinición del método de escribir
IES Maestre Cva.
sin escribir nada en la cabecera */
protected void writeStreamHeader() throws IOException
{}
}