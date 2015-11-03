package clases;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import clases.Idioma;
import java.io.Serializable;


public class Traduccion implements Serializable, Comparable<Traduccion>{

	//////////////
	//Constantes//
	//////////////
	
	public final static int MAYOR=1;

	public final static int IGUAL=0;
	
	public final static int MENOR=-1;
        
	/////////////
	//Atributos//
	/////////////
	
	private static final long serialVersionUID = 111L;
	private String palabraOriginal;
	private String palabraTraducida;
	private String categoria;
	private String idioma;
	private String descripcion;
	
	/**
	 * Constructor
	 * @param palabraOriginal
	 * @param palabraTraducida
	 * @param categoria
	 * @param descripcion
	 */
	public Traduccion(String palabraOriginal,
					String palabraTraducida,
					String categoria,
					String idioma,
					String descripcion){
		
		this.palabraOriginal=palabraOriginal;
		this.palabraTraducida=palabraTraducida;		
		this.categoria=categoria;
		this.idioma=idioma;
		this.descripcion=descripcion;
		
	}

	@Override
	public int compareTo(Traduccion t) {
		int estado=palabraOriginal.toLowerCase().compareTo(t.getPalabraOriginal().toLowerCase());
		if(estado>=1){
			if(estado==0){
				estado=IGUAL;
			}else{
				estado=MAYOR;
			}
		}else{
			estado=MENOR;
		}
		return estado;
	}
	
	public boolean equals(Traduccion t) {
		
		boolean igual=false;
		//Dos traducciones son iguales cuando su palabra original, palabra traducida,categoria y idioma son iguales
		if(palabraOriginal.equals(t.getPalabraOriginal()) && palabraTraducida.equals(t.getPalabraTraducida()) && categoria.equals(t.getCategoria()) && idioma.equals(t.getIdioma())){
			igual=true;
		}
		
		return igual;
	}

	public String getPalabraOriginal() {
		return palabraOriginal;
	}

	public void setPalabraOriginal(String palabraOriginal) {
		this.palabraOriginal = palabraOriginal;
	}

	public String getPalabraTraducida() {
		return palabraTraducida;
	}

	public void setPalabraTraducida(String palabraTraducida) {
		this.palabraTraducida = palabraTraducida;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
        @Override
	public String toString(){
		
            return palabraOriginal+"-"+palabraTraducida+" ("+categoria+") "+idioma;
		
	}

	public static String[] cargaIdiomas(int codigoIdioma, Idioma idioma){
		
		String[] idiomas=new String[6];
		
		String todosIdiomas[]=cargaTodosIdiomas(idioma);
		
		for(int i=0;i<todosIdiomas.length;i++){
			if(codigoIdioma<i){
				idiomas[i-1]=todosIdiomas[i];
			}else{
				idiomas[i]=todosIdiomas[i];
			}
			
		}
		
		return idiomas;
	}
	
	public static String[] cargaTodosIdiomas(Idioma idioma){
		String todosIdiomas[]={idioma.getProperty("espanol"), idioma.getProperty("ingles"), idioma.getProperty("frances"), idioma.getProperty("portugues"), idioma.getProperty("italiano"), idioma.getProperty("aleman"), idioma.getProperty("arabe")};
		return todosIdiomas;
	}
	
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
