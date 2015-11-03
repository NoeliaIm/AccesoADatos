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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Profesor
 */
public class MetodosComunes {
    
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
     * Restringe el texto solo numeros. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirSoloNumeros(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo numeros");
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
    
    public static void escribirAlfanumerico(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isAlphabetic(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo numeros y letras");
       }else{
            etiquetaError.setText("");
       }
    }
    
    
     //Asigna el item segun el codigo
    public static void asignarItemCmb2Columnas(JComboBox cmb,int codigo){
        
        boolean encontrado=false;
        
        for(int i=1;i<cmb.getItemCount() && !encontrado;i++){
            String[] seleccion=(String[])cmb.getItemAt(i);
            
            if(codigo==Integer.parseInt(seleccion[0])){
                cmb.setSelectedIndex(i);
                encontrado=true;
            }
        }
        
        if(!encontrado){
            cmb.setSelectedIndex(0);
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
    
    
    /**
     * Devuelve el codigo de un comboBox de 2 columnas (usado para BD)
     * @param cmb
     * @return 
     */
    public static int devolverCodigoComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return Integer.parseInt(newSelection[0]);
        
    }
    
     public static void ocultarColumnaJTable(JTable tabla, int columna){
        
        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(0);
        
    }
     
     public static void limpiarTabla(DefaultTableModel dtm){
       
        while(!dtm.getDataVector().isEmpty()){
            dtm.removeRow(0);
        }
    }
     
      /**
     * Restringe el texto solo numeros decimales, con un limite de decimales y enteros. Escribe el aviso en el JLabel pasado.
     * @param texto
     * @param evt
     * @param etiquetaError
     * @param numeroEnteros
     * @param numeroDecimales 
     */
    public static void escribirSoloDoubles(JTextField texto, KeyEvent evt, JLabel etiquetaError, int numeroEnteros, int numeroDecimales){
        
            String text=texto.getText();
            char caracter=evt.getKeyChar();
        
            //Controlo que haya un punto, si devuelve -1 no hay punto
            boolean punto;
            if(texto.getText().indexOf('.')==-1){
                punto=false;
            }else{
                punto=true;
            }
            
//            boolean ceroInicial;
//            if(text.charAt(0)=='0'){
//                
//                ceroInicial=true;
//            }else{
//                ceroInicial=false;
//            }

            
            if(text.isEmpty() && (!Character.isDigit(caracter)) ){
                evt.consume();
                etiquetaError.setText("El primer digito no puede ser un punto");
            }else{
                    if(!Character.isDigit(caracter) && (text.contains(".") || caracter!='.') && caracter!=KeyEvent.VK_BACK_SPACE){
                        evt.consume();
                        etiquetaError.setText("Solo numeros y puntos");
                    }else{
                        //Si supera el limite de enteros lo indica, si se escribe un punto obvia esta condicion
                        if(text.length()>=numeroEnteros && caracter!=46 && !punto){
                            evt.consume();
                            etiquetaError.setText("No puedes escribir mas enteros");
                        }else{
                            //Si hay punto, cojo una subcadena y compruebo si supera el limite
                            if(punto){
                                if(text.substring(text.indexOf('.')).length()>numeroDecimales){
                                    evt.consume();
                                    etiquetaError.setText("No puedes escribir mas decimales");
                                }
                            }else{
                                etiquetaError.setText("");                
                            }
                        }   
                    }   
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
    
    
}
