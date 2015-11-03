/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ALUMNOM
 */
public class MetodosComunes {

    // Valida que se introduzca un número float
    public boolean txtEstaVacio(JTextField txt) {
        boolean resultado = false;
        if (txt.getText().equals("")) {
            resultado = true;
        }
        return resultado;
    }
    
    public void escribirSoloEnterosLimitado(KeyEvent evt,String texto,int maximo,JTextField txtError,JTextField txtTexto){
        if(txtTexto.getText().length() == maximo){
            evt.consume();
            txtError.setText(texto+" Longitud Máxima: "+maximo+" caracteres");
        }else{
            txtError.setText("");
        }
        if(!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)&& !(evt.getKeyChar()==KeyEvent.VK_ENTER)){
            evt.consume();
            txtError.setText(texto +" Escribe solo números");
        }
    }
    public void limitarCaracteres(KeyEvent evt,String texto, int maximo,JTextField txtError,JTextField txtTexto){
        if(txtTexto.getText().length()== maximo){
            evt.consume();
            txtError.setText(texto+" Longitud Máxima: "+maximo+" caracteres");
        }else{
            txtError.setText("");
        }
    }
    
    public boolean validarDNI(String DNI,JLabel lblError){
          char letrasNIF[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y', 
                                'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 
                                'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    
        int DIVISOR=23;
        if(DNI.length()>=8 && DNI.length()<=9){
           try{
                
                int dni=Integer.parseInt(DNI.substring(0, DNI.length()-1));
                int res=dni-(dni/DIVISOR*DIVISOR);
          
                String DNICalculado=String.valueOf(dni)+letrasNIF[res];
               
                return DNICalculado.replace('0', ' ').trim().equals(DNI.replace('0', ' ').trim());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La cadena pasada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
       }else{
           lblError.setText("El DNI debe ser de 8 u 9 cifras");
           return false;
       }
        
    }
    public void escribirSoloDoublesCajaTexto(JTextField campo, KeyEvent evt,String texto, JTextField txtError, int numeroEnteros, int numeroDecimales){
        String text = campo.getText();
        char caracter = evt.getKeyChar();

        //Controlo que haya un punto, si devuelve -1 no hay punto
        boolean punto;

        punto = campo.getText().indexOf('.') != -1;

        if (text.isEmpty() && (!Character.isDigit(caracter) || caracter == '0')) {
            evt.consume();
            txtError.setText(texto+ " El primer digito no puede ser un punto o un 0");
        } else {
            if (!Character.isDigit(caracter) && (text.contains(".") || caracter != '.') && caracter != KeyEvent.VK_BACK_SPACE) {
                evt.consume();
                txtError.setText(texto +" Solo numeros y puntos");
            } else {
                //Si supera el limite de enteros lo indica, si se escribe un punto obvia esta condicion
                if (text.length() >= numeroEnteros && caracter != 46 && !punto) {
                    evt.consume();
                    txtError.setText(texto+" No puedes escribir mas enteros");
                } else {
                    //Si hay punto, cojo una subcadena y compruebo si supera el limite
                    if (punto) {
                        if (text.substring(text.indexOf('.')).length() > numeroDecimales) {
                            evt.consume();
                            txtError.setText(texto+" No puedes escribir mas decimales");
                        }
                    } else {
                        txtError.setText("");
                    }
                }
            }
        }
    }

    public void escribirSoloDoubles(JTextField campo, KeyEvent evt, JLabel etiquetaError, int numeroEnteros, int numeroDecimales) {

        String text = campo.getText();
        char caracter = evt.getKeyChar();

        //Controlo que haya un punto, si devuelve -1 no hay punto
        boolean punto;

        punto = campo.getText().indexOf('.') != -1;

        if (text.isEmpty() && (!Character.isDigit(caracter) || caracter == '0')) {
            evt.consume();
            etiquetaError.setText("El primer digito no puede ser un punto o un 0");
        } else {
            if (!Character.isDigit(caracter) && (text.contains(".") || caracter != '.') && caracter != KeyEvent.VK_BACK_SPACE) {
                evt.consume();
                etiquetaError.setText("Solo numeros y puntos");
            } else {
                //Si supera el limite de enteros lo indica, si se escribe un punto obvia esta condicion
                if (text.length() >= numeroEnteros && caracter != 46 && !punto) {
                    evt.consume();
                    etiquetaError.setText("No puedes escribir mas enteros");
                } else {
                    //Si hay punto, cojo una subcadena y compruebo si supera el limite
                    if (punto) {
                        if (text.substring(text.indexOf('.')).length() > numeroDecimales) {
                            evt.consume();
                            etiquetaError.setText("No puedes escribir mas decimales");
                        }
                    } else {
                        etiquetaError.setText("");
                    }
                }
            }
        }
    }
    /**
     * Convierte un entero a formato fecha y lo establece en el DateChooser
     *
     * @param fechaint
     * @param chooser
     */
    public void IntegerToDate(int fechaint,JDateChooser chooser){
        
        String cadFecha = Integer.toString(fechaint);
        SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyyMMdd");
        try {
            Date fechaDate = formato.parse(cadFecha);
            chooser.setDate(fechaDate);
        } catch (ParseException ex) {
            Logger.getLogger(MetodosComunes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int DateToInteger(JDateChooser chooser){
        Date date = chooser.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(date);
        return Integer.parseInt(fecha);
        
    }
    public int getFechaActual(){
         Date date = new Date();
         DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(dateFormat.format(date));
    }
    
     public  boolean validarEmail(String email){
        
        //return email.matches("^([\\w-]+\\.)*?[\\w-]+@[\\w-]+\\.([\\w-]+\\.)*?[\\w]+$");
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
     public boolean validarDNI(String dni){
         return dni.matches("^(([A-Z])|\\d)?\\d{8}(\\d|[A-Z])?$");
     }

}
