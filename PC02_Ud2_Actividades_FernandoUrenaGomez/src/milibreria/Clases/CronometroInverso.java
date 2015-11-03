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
public class CronometroInverso extends Thread{
    
    private JTextField campos[];
    private int milesimas;
    private int segundos;
    private int minutos;
    private int horas;
    private int limiteMinutos;
    private int limiteSegundos;
    
    public CronometroInverso(JTextField campos[], int tiempoMinutosInicial){
        
        if(tiempoMinutosInicial>0 && tiempoMinutosInicial<60){
            minutos=tiempoMinutosInicial;
        }else{
            minutos=1;
        }
        
        this.campos=campos;
        milesimas=0;
        segundos=0;
        
        horas=0;
        limiteMinutos=0;
        limiteSegundos=0;
    }
    
    public CronometroInverso(JTextField campos[], int tiempoMinutosInicial, int limiteMinutos, int limiteSegundos){
        
        if(tiempoMinutosInicial>limiteMinutos){
            this.limiteMinutos=limiteMinutos;
            this.limiteSegundos=limiteSegundos;
        }else{
            this.limiteMinutos=0;
            this.limiteSegundos=0;
        }
        
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
        
    }
    
    public CronometroInverso(JTextField campos[], int tiempoMinutosInicial, int tiempoSegundosInicial, int limiteMinutos, int limiteSegundos){
        
        if(tiempoMinutosInicial>limiteMinutos){
            this.limiteMinutos=limiteMinutos;
            this.limiteSegundos=limiteSegundos;
        }else{
            this.limiteMinutos=0;
            this.limiteSegundos=0;
        }
        
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
        
        horas=0;
        
    }
    
    public void run(){
        
        while(true){

            if(milesimas==0){
                segundos-=1;
                milesimas=1000;
            }
            if(segundos==-1){
                if(minutos!=0){
                    minutos-=1;
                    segundos=59;
                }
            }
            if(minutos==-1){
                if(horas!=0){
                    horas-=1;
                    minutos=59;
                }

            }
            milesimas-=1;

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                //Logger.getLogger(TiempoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
