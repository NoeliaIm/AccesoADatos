/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.Clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Fernando
 */
public class CronometroNormal extends Thread{
    
    private JTextField campos[];
    private int milesimas;
    private int segundos;
    private int minutos;
    private int horas;
    private int limiteMinutos;
    private int limiteSegundos;
    
    public CronometroNormal(JTextField campos[]){
        this.campos=campos;
        milesimas=0;
        segundos=0;
        minutos=0;
        horas=0;
        limiteMinutos=-1;
        limiteSegundos=-1;
    }
    
    public CronometroNormal(JTextField campos[], int tiempoMinutosInicial, int limiteMinutos, int limiteSegundos){
        
        if(tiempoMinutosInicial>0 && tiempoMinutosInicial<60){
            minutos=tiempoMinutosInicial;
        }else{
            minutos=1;
        }
        
        this.campos=campos;
        if(tiempoMinutosInicial<10){
            campos[2].setText("0"+String.valueOf(tiempoMinutosInicial));
        }else{
            campos[2].setText(String.valueOf(tiempoMinutosInicial));
        }
        
        segundos=0;
        horas=0;
        
        this.limiteMinutos=limiteMinutos;
        this.limiteSegundos=limiteSegundos;
        
    }
    
    public CronometroNormal(JTextField campos[], int tiempoMinutosInicial, int tiempoSegundosInicial, int limiteMinutos, int limiteSegundos){
        
        if(tiempoMinutosInicial>0 && tiempoMinutosInicial<60){
            minutos=tiempoMinutosInicial;
        }else{
            minutos=1;
        }
        
        if(tiempoSegundosInicial>0 && tiempoSegundosInicial<60){
            segundos=tiempoSegundosInicial;
        }else{
            segundos=0;
        }
        
        this.campos=campos;
        if(tiempoMinutosInicial<10){
            campos[2].setText("0"+String.valueOf(tiempoMinutosInicial));
        }else{
            campos[2].setText(String.valueOf(tiempoMinutosInicial));
        }
        
        segundos=tiempoSegundosInicial;
        horas=0;
        
        this.limiteMinutos=limiteMinutos;
        this.limiteSegundos=limiteSegundos;
        
    }
    
    public void run(){
        
        while(true){
            
            if(milesimas==1000){
                segundos+=1;
                milesimas=0;
            }
            if(segundos==60){
                minutos+=1;
                segundos=0;
            }
            if(minutos==60){
                horas+=1;
                minutos=0;
            }
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                //Logger.getLogger(TiempoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            milesimas+=1;
            
            campos[0].setText(String.valueOf(milesimas));
            
            if(segundos<10){
                campos[1].setText("0"+String.valueOf(segundos));            
            }else{
                campos[1].setText(String.valueOf(segundos));
            }
            
            if(minutos<10){
                campos[2].setText("0"+String.valueOf(minutos));
            }else{
                campos[2].setText(String.valueOf(minutos));
            }
            
            if(horas<10){
                campos[3].setText("0"+String.valueOf(horas));
            }else{
                campos[3].setText(String.valueOf(horas));
            }
            
            if(limiteMinutos==minutos && limiteSegundos==segundos){
                campos[0].setText("00");
                stop();
            }
            
            
        }
        
        
    }
    
    
}

