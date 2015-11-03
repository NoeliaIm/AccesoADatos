/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionbiblioteca.auxiliar;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aitor
 */
public class Auxiliar {
    
    
    //limpia un jTable
    public static void limpiarTabla(DefaultTableModel jt){
        for (int i = jt.getRowCount() - 1; i >= 0; i--) {
            jt.removeRow(i);
        }
    }
    
    //comprueba que el DNI es correcto
    public boolean validarDNI(String DNI){
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
           //JOptionPane.showMessageDialog(null, "El DNI debe ser de 8 u 9 cifras", "", JOptionPane.ERROR_MESSAGE);
           return false;
       }
        
    }
    
    
    //Premitir sólo introducir enteros en un JTF
    public static void escribirSoloEnterosLimitado(KeyEvent evt,String texto,int maximo,JTextField txtTexto){
        if(txtTexto.getText().length() == maximo){
            evt.consume();           
        }
        if(!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)&& !(evt.getKeyChar()==KeyEvent.VK_ENTER)){
            evt.consume();
        }
    }
    
    //limita el números de caracteres introducidos en un JTF
    public static void limitarCaracteres(KeyEvent evt,String texto, int maximo,JTextField txtTexto){
        if(txtTexto.getText().length()== maximo){
            evt.consume();
        }
    }
    
    
     public static boolean estaVacio(JTextField jtf){
       if(jtf.getText().isEmpty()){
           return true;
       }else{
           return false;
       }
   }
}
