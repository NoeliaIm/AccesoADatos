/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.fechas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class Fechas {
     
    public static boolean esBisiesto(int anio){
        
        GregorianCalendar calendar=new GregorianCalendar();
        boolean esBisiesto=false;
        if(calendar.isLeapYear(anio)){
            esBisiesto=true;
        }
        return esBisiesto;
        
    }
    
    public static String[] mesesAnio(){
        
        String mesesAnio[]={"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        return mesesAnio;
    }
    
    public static boolean mesCorrecto(String mes){
        
        boolean correcto=false;
        
        String meses[]=mesesAnio();
        
        for(int i=0;i<meses.length;i++){
            if(mes.toLowerCase().trim().equals(meses[i])){
                correcto=true;
            }
        }
        
        return correcto;
        
    }
    
    
    public static int numeroDeDiasMes(String mes){
        
        int numeroDias=-1;
        if(mesCorrecto(mes)){
            
            
            switch(mes.toLowerCase().trim()){
                case "enero":
                case "marzo":
                case "mayo":
                case "julio":
                case "agosto":
                case "octubre":
                case "diciembre":
                    numeroDias=31;
                    break;
                case "abril":
                case "junio":
                case "septiembre":
                case "noviembre":
                    numeroDias=30;
                    break;
                case "febrero":
                    
                    Date anioActual=new Date();
                    if(esBisiesto(anioActual.getYear())){
                        numeroDias=29;
                    }else{
                        numeroDias=28;
                    }
                    break;
                                        
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "El mes introducido no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return numeroDias;
    }
    
    public static int numeroDeDiasMes(int mes){
        
        int numeroDias=-1;
        //if(mesCorrecto(mes)){
            
            
            switch(mes){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    numeroDias=31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    numeroDias=30;
                    break;
                case 2:
                    
                    Date anioActual=new Date();
                    if(esBisiesto(anioActual.getYear())){
                        numeroDias=29;
                    }else{
                        numeroDias=28;
                    }
                    break;
                                        
            }
            
       /* }else{
            JOptionPane.showMessageDialog(null, "El mes introducido no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
        }*/
        
        return numeroDias;
    }
    
    
    public static int numeroDeDiasMes(String mes, int anio){
        
        int numeroDias=-1;
        if(mesCorrecto(mes)){
            
            
            switch(mes.toLowerCase().trim()){
                case "enero":
                case "marzo":
                case "mayo":
                case "julio":
                case "agosto":
                case "octubre":
                case "diciembre":
                    numeroDias=31;
                    break;
                case "abril":
                case "junio":
                case "septiembre":
                case "noviembre":
                    numeroDias=32;
                    break;
                case "febrero":
                  
                    if(esBisiesto(anio)){
                        numeroDias=29;
                    }else{
                        numeroDias=28;
                    }
                    break;
                                        
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "El mes introducido no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return numeroDias;
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
    
    //Probar
    public static int deHoraANumero(Date fecha){
        
        SimpleDateFormat formato=new SimpleDateFormat("HH:mm:ss");
        
        StringBuilder constructor = new StringBuilder( formato.format( fecha ) );
        String f=constructor.toString();
        
        if (fecha.getHours() <10){
            f="0"+fecha.getHours();
        }else{
            f=String.valueOf(fecha.getHours());
        }
        
        if(fecha.getMinutes() < 10){
            f+="0"+fecha.getMinutes();
        }else{
            f+=fecha.getMinutes();
        }
        
        if(fecha.getSeconds()<10){
            f+="0"+fecha.getSeconds();
        }else{
            f+=fecha.getSeconds();
        }
        
        return Integer.parseInt(f);
    }
    
    public static Date deNumeroAHora(int numero){
        
        int hora=(int)Math.floor(numero/10000);
        
        int minuto= (int)Math.floor((numero%10000)/100);
        
        int segundo=numero%100;
        
        Date fecha=new Date(1,1,1,hora,minuto,segundo);
        
        return fecha;        
    }
    
    public static boolean validarFecha(int anio, int mes, int dia){
        
        boolean valido=false;
        
        if (anio>=0 && (mes>=1 && mes<=12)){
            if(numeroDeDiasMes(mes) >= dia){
                valido=true;
            }
        }
        
        return valido;
        
    } 

    public static boolean validaHora(int hora, int minuto, int segundo){
        
        boolean valido=false;
        
        if ((hora >= 0 && hora <= 23) && (minuto >= 0 && minuto <= 59) && (segundo >= 0 && segundo <= 59)){
            valido = true;
        }

        
        return valido;
    }

}
