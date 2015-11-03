/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.discoduroderoer.expresiones_regulares;

/**
 *
 * @author DicoDurodeRoer
 */
public class ExpresionesRegulares {
    
    
    /**
     * Valida si una cadena es un numero entero
     * @param texto String que contiene el valor a validar
     * @return True = es un numero entero
     */
    public static boolean validaNumeroEntero_Exp(String texto){
        
        return texto.matches("^[0-9]+$");
    }
    
    /**
     * Valida si una cadena es un numero real negativo
     * @param texto String que contiene el valor a validar
     * @return True = es un numero real negativo
     */
    public static boolean validaNumeroRealNegativo_Exp(String texto){
        
        return texto.matches("^-[0-9]+([\\.,][0-9]{1,2})$");
    }
    
    /**
     * Valida si una cadena es un numero real (positivo o negativo)
     * @param texto String que contiene el valor a validar
     * @return True = es un numero real
     */
    public static boolean validaNumeroReal_Exp(String texto){
        
        return texto.matches("^[-]?[0-9]+([\\.,][0-9]{1,2})?$");
    }

    /**
     * Valida si una cadena es un numero real positivo
     * @param texto String que contiene el valor a validar
     * @return True = es un numero real positivo
     */
    public static boolean validaNumeroRealPositivo_Exp(String texto){
        
        return texto.matches("^[0-9]+([\\.,][0-9]{1,2})?$");
    }

    /**
     * Valida si una cadena tiene el formato de fecha dd/mm/aaaa
     * @param texto String que contiene el valor a validar
     * @return True = es una fecha válida
     */
    public static boolean validaNumeroFecha_Exp(String texto){
        
        return texto.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[/\\/](19|20)\\d{2}$");
    }
    
    /**
     * Valida si una cadena es un email
     * @param email String que contiene el valor a validar
     * @return True = es un email válido
     */
    public static boolean validar_Mail_Exp(String email){
        
        return email.matches("^([\\w-]+\\.)*?[\\w-]+@[\\w-]+\\.([\\w-]+\\.)*?[\\w]+$");
        
    }
    
    /**
     * Valida si una cadena es un email complejo (subdominios)
     * @param email String que contiene el valor a validar
     * @return True = es un email complejo válido
     */
    public static boolean validar_Mail_Complejo_Exp(String email){
        
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
    }

    /**
     * Valida si una cadena es una IP válida
     * @param ip String que contiene el valor a validar
     * @return True = es una IP válida
     */
    public static boolean validaIP_Exp(String ip){
        
        return ip.matches("^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]).){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
    }

    /**
     * Valida si una cadena es una url
     * @param url String que contiene el valor a validar
     * @return True = es una url válida
     */
    public static boolean validaURL_Exp(String url){
        
        return url.matches("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\?=.-]*)*\\/?$");
    }
    
    /**
     * Valida si una cadena es un número binario
     * @param binario String que contiene el valor a validar
     * @return True = es un número binario válido
     */
    public static boolean validaBinario_Exp(String binario){
        
        return binario.matches("^[0-1]+$");
        
    }
    
    /**
     * Valida si una cadena es un número octal
     * @param octal String que contiene el valor a validar
     * @return True = es un número octal válido
     */
    public static boolean validaOctal_Exp(String octal){
        
        return octal.matches("^[0-7]+$");
        
    }
    
    /**
     * Valida si una cadena es un número hexadecimal
     * @param hexadecimal String que contiene el valor a validar
     * @return True = es un número hexadecimal válido
     */
    public static boolean validaHexadecimal_Exp(String hexadecimal){
        
        return hexadecimal.matches("^[0-9A-F]+$");
        
    }
 
}
