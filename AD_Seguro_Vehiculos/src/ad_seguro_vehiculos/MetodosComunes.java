/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernando
 */
public class MetodosComunes {
    
    //Oculta una columna de un jtable
    public static void ocultarColumnaJTable(JTable tabla, int columna){
        
        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(0);
        
    }
    
    //Solo permite escribir en un textbox letras y espacios
    public static void escribirSoloLetrasYEspacios(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo letras");
        }else{
            etiquetaError.setText("");
        }
    }
    
    //Solo permite escribir en un textbox numeros(no negativos)
    public static void escribirSoloNumeros(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo numeros");
       }else{
            etiquetaError.setText("");
       }
    }
    
    //permite escribir letras y numeros
    public static void escribirAlfanumerico(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isAlphabetic(evt.getKeyChar()) && !Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo numeros y letras");
       }else{
            etiquetaError.setText("");
       }
    }
    
    //Limita el numero de caracteres
    public static void noEscribirMasDe(JTextField campo, int longitudMaxima,KeyEvent evt, JLabel etiquetaError){
        if(campo.getText().length()>=longitudMaxima){
                evt.consume();
                etiquetaError.setText("No mas de "+longitudMaxima+" caracteres");
       }else{
            etiquetaError.setText("");
        }
    }
    
    public static void noEscribirMasDe(JTextArea campo, int longitudMaxima,KeyEvent evt, JLabel etiquetaError){
        if(campo.getText().length()>=longitudMaxima){
                evt.consume();
                etiquetaError.setText("No mas de "+longitudMaxima+" caracteres");
       }else{
            etiquetaError.setText("");
        }
    }
    
    
    
    //valida un email
    public static boolean emailCorrecto(String email){
        boolean existe=false;
        
        if(email.matches("[-\\w\\.]+@\\w+\\.\\w+")){
            existe=true;
        }
        
        return existe;
    }
    
    //Valida un DNI
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
                if(DNICalculado.equals(DNI)){
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
    
    //Convierte una fecha normal a fecha cientifica
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

    //convierte una fecha cientifica a fecha normal
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
    
    //1º dimension = textbox, 2º dimension = mensajes personalizados
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
    
    //Devuelve el id de un combobox de doble columna
    public static int devolverCodigoComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return Integer.parseInt(newSelection[0]);
        
    }
    
    //limpia la tabla
    public static void limpiarTabla(DefaultTableModel dtm){
       
        while(!dtm.getDataVector().isEmpty()){
            dtm.removeRow(0);
        }
    }
    
    //Centra la cabecera de una tabla
    public static void centraCabecera(JTable table){
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel)rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    
    //Centra los datos de una tabla
    public static void centrarDatos(JTable table){
		 
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        int numColumnas=table.getColumnCount();
        
        for(int i=0;i<numColumnas;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
        }
    }
    
    //Añade un borde con titulo a un jPanel
    public static void bordeConTitulo(JPanel panel, String titulo){
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
    }
    
    //Evita que el usuario pegue texto en los textbox
    public static void evitarPegar(JTextField txt){
        
        InputMap map2 = txt.getInputMap(txt.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        
    }
    
    //rellena un combobox de un array de string
     public static void rellenaComboBox(JComboBox cmb, String datos[]){
        
        for(int i=0;i<datos.length;i++){
            cmb.addItem(datos[i]);
        }
        
    }
     
     /**
     * Restringe el texto solo numeros decimales, con un limite de decimales. Escribe el aviso en el JLabel pasado.
     * 
     * @param texto
     * @param evt
     * @param etiquetaError
     * @param numeroDecimales 
     */
    public static void escribirSoloDoubles(JTextField texto, KeyEvent evt, JLabel etiquetaError, int numeroDecimales){
            
            String text=texto.getText();
            char caracter=evt.getKeyChar();
        
            //Controlo que haya un punto, si devuelve -1 no hay punto
            boolean punto;
            if(text.indexOf('.')==-1){
                punto=false;
            }else{
                punto=true;
            }

            if(text.isEmpty() && (!Character.isDigit(caracter) || caracter=='0') ){
                evt.consume();
                etiquetaError.setText("El primer digito no puede ser un punto o un 0");
            }else{
                    if(!Character.isDigit(caracter) && (text.contains(".") || caracter!='.')){
                        evt.consume();
                        etiquetaError.setText("Solo numeros y puntos");
                    }else{
                        //Si hay punto, cojo una subcadena y compruebo si supera el limite
                        if(punto){
                            if(text.substring(text.indexOf('.')).length()>numeroDecimales){
                                evt.consume();
                                etiquetaError.setText("No puedes escribir mas decimeales");
                            }
                        }else{
                            etiquetaError.setText("");                
                        }
                    }
            }
    }
    
    
    
}
