/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.listaEnlazada;

/**
 *
 * @author Fernando
 */
public class Carta{
        private int numero;
        private String palo;
        private boolean marcada;

        public String toString(){
            String figura="";
            switch(numero){
                case 10:
                    figura="Sota";
                    break;
                case 11:
                    figura="Caballo";
                    break;
                case 12:
                    figura="Rey";
                    break;
            }
            if(numero>=10 && numero<=12){
                return figura+" de "+palo;
            }else{
                return numero+" de "+palo;
            }
            
        }
        
        public boolean isMarcada() {
            return marcada;
        }

        public void setMarcada(boolean marcada) {
            this.marcada = marcada;
        }

        public Carta(int numero, String palo){
            this.numero=numero;
            this.palo=palo;
            marcada=false;
        }
    }
