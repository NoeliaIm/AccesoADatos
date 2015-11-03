/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public class MetodosComunes {
    
    
    /**
     * Oculta una columna de una tabla
     * @param tabla
     * @param columna 
     */
    public static void ocultarColumnaJTable(JTable tabla, int columna){

        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(0);
        
    }
    
     /**
     * Restringe el texto solo a letras. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirSoloLetras(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo letras");
        }else{
            etiquetaError.setText("");
        }
    }
    
    public static int deFechaANumero(Date fecha){
        
        String f = String.valueOf(fecha.getYear()+1900);
        
        String formato="MM";
         SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        
        if (Integer.parseInt(dateFormat.format(fecha)) < 10) {
            f+="0"+Integer.parseInt(dateFormat.format(fecha));
        }else{
            f+=Integer.parseInt(dateFormat.format(fecha));
        }
        
        formato="dd";
        dateFormat = new SimpleDateFormat(formato);
        
        
        if (Integer.parseInt(dateFormat.format(fecha)) <10){
            f+="0"+Integer.parseInt(dateFormat.format(fecha));
        }else{
            f+=Integer.parseInt(dateFormat.format(fecha));
        }
        
        return Integer.parseInt(f);
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
    
    public static boolean comprobarDNI(String DNI){
        
        if(DNI.length()>=8 && DNI.length()<=9){
           try{
               
                boolean correcto=false;
                final int divisor=23;
                int dni=Integer.parseInt(DNI.substring(0, DNI.length()-1));
                int res=dni-(dni/divisor*divisor);
                
                char letrasNIF[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y', 
                                  'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 
                                  'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

                String DNICalculado=String.valueOf(dni)+letrasNIF[res];
                
               
                if(DNICalculado.replace('0', ' ').trim().equals(DNI.replace('0', ' ').trim())){
                    correcto=true;
                }
                
                return correcto;
        }catch(NumberFormatException e){
            //JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       }else{
           //JOptionPane.showMessageDialog(null, "El DNI debe ser de 7 u 8 cifras", "", JOptionPane.ERROR_MESSAGE);
           return false;
       }
        
    }
    
     public static Date deNumeroAFecha(int numero){
        
        try{
            
            int anio=(int)Math.floor(numero/10000);
            
            int mes = (int)Math.floor(((numero%10000) / 100));
            
            int dia=numero%100;
            
            Date fecha=new Date(anio-1900, mes-1, dia);
            
            return fecha;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, la fecha no es correcta");
            return null;
        }
    }
     
     /**
     * Devuelve el codigo de un comboBox de 2 columnas (usado para BD)
     * @param cmb
     * @return 
     */
    public static int devolverCodigoComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return Integer.parseInt(newSelection[0]);
        
    }
    
    /**
     * Restringe el texto solo a letras y espacios. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirSoloLetrasYEspacios(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo letras");
        }else{
            etiquetaError.setText("");
        }
    }
    
     /**
     * Solo se puede escribir una longitud determinada. Escribe el aviso en un JLabel
     * @param campo
     * @param longitudMaxima
     * @param evt
     * @param etiquetaError 
     */
    public static void noEscribirMasDe(JTextField campo, int longitudMaxima,KeyEvent evt, JLabel etiquetaError){
        if(campo.getText().length()>=longitudMaxima){
                evt.consume();
                etiquetaError.setText("No mas de "+longitudMaxima+" caracteres");
       }else{
            etiquetaError.setText("");
        }
    }
    
    /**
     * Restringe el texto solo a letras. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirAlfanumerico(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar())){
            evt.consume();
            etiquetaError.setText("Escribe solo letras y numeros");
        }else{
            etiquetaError.setText("");
        }
    }
    
    public static boolean fechaMayorqueHoy(Date fecha){
        
        int fechaHoy=MetodosComunes.deFechaANumero(new Date());
        int fechaActual=MetodosComunes.deFechaANumero(fecha);
            
        return fechaActual<=fechaHoy;
        
    }
    
}
