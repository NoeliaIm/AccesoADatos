/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package milibreria.swing;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import milibreria.Array.Array;
/**
 *
 * @author Fernando
 */
public class MiSwing {
    
    /**
     * Indica si los campos estan vacios
     * @param campos
     * @return  true = algun campo vacio
     *          false = no hay vacios
     */
    public static boolean comprobarVacios(JTextField campos[]){
        
        boolean vacio=false;
        
        for(int i=0;i<campos.length && !vacio;i++){
            if(campos[i].getText().isEmpty()){
                vacio=true;
            }
        }
        return vacio;
    }
    
    /**
     * Indica si un campo de texto esta vacio, con un mensaje personalizado
     * @param campo
     * @param texto
     * @return  true = vacio
     *          false= no vacio
     */
    public static boolean comprobarVacio(JTextField campo, String texto){
        boolean vacio=false;
        
        if(campo.getText().isEmpty()){
             vacio=true;
             JOptionPane.showMessageDialog(null, texto);
        }
        return vacio;
    }
    
    /**
     * Si esta vacio, añade el valor nulo pasado por parametro 
     * @param campo
     * @param valorNulo 
     */
    public static void comprobarVacio(JTextField campo, int valorNulo){
        
        if(campo.getText().isEmpty()){
             campo.setText(String.valueOf(valorNulo));
        }
    }
    
    /**
     * Si esta vacio, añade el valor nulo pasado por parametro 
     * @param campo
     * @param valorNulo 
     */
    public static void comprobarVacio(JTextField campo, String texto, String valorNulo){
        
        if(campo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, texto);
             campo.setText(valorNulo);
        }
    }
    
    /**
     * Si esta vacio, añade el valor nulo pasado por parametro 
     * @param campo
     * @param valorNulo 
     */
    public static void comprobarVacio(JTextField campo, double valorNulo){
        
        if(campo.getText().isEmpty()){
             campo.setText(String.valueOf(valorNulo));
        }
    }
    
    /**
     * Si esta vacio, añade el valor nulo pasado por parametro 
     * @param campo
     * @param valorNulo 
     */
    public static void comprobarVacio(JTextField campo, char valorNulo){
        
        if(campo.getText().isEmpty()){
             campo.setText(String.valueOf(valorNulo));
        }
    }
    
    /**
     * Vaciamos el texto de todas las cajas de texto pasadas.
     * @param campos 
     */
    public static void limpiaCampos(JTextField campos[]){
        
        for(int i=0;i<campos.length;i++){
            campos[i].setText("");
        }
        
    }
    
    
    public static void limpiaCampoNulo(JTextField campo, String valor){
        
        if(campo.getText().isEmpty()){
            campo.setText(valor);
        }
        
    }
    
    /**
     * Vaciamos el texto de todas las cajas de texto pasadas. Los arrays de campos y objetos deben ser iguales.
     * @param campos
     * @param datos datos pasados para saber el tipo de dato 
     */
    public static void limpiaCamposValorNulo(JTextField campos[], Object[] datos){
        
        if(campos.length==datos.length){

            String tipo[]=Array.tiposDatos(datos);
            
            for(int i=0;i<campos.length;i++){
                
                String tipoActual=tipo[i];
                
                switch(tipoActual){
                    case "java.lang.Byte":
                    case "java.lang.Short":
                    case "java.lang.Integer": 
                    case "java.lang.Long":
                    case "java.lang.Double":
                    case "java.lang.Float":
                        campos[i].setText(String.valueOf(0));
                    case "java.lang.String":
                    case "java.lang.Character":
                        campos[i].setText("");
                    case "java.lang.Boolean":
                        campos[i].setText(String.valueOf(false));
                        break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error, el array de objetos y de campos debe ser igual");
        }
        
    }
    
    /**
     * Restringe el texto solo a letras.
     * @param evt 
     */
    public static void escribirSoloLetras(KeyEvent evt){
        if(!Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }
    }
    
    /**
     * Restringe el texto solo a letras y espacios
     * @param evt 
     */
    public static void escribirSoloLetrasYEspacios(KeyEvent evt){
        if(!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar()!=KeyEvent.VK_SPACE){
            evt.consume();
        }
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
    
    /**
     * Restringe el texto solo a letras y espacios. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirSoloLetrasYEspacios(KeyEvent evt, JLabel etiquetaError){
        if(!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar()==KeyEvent.VK_SPACE)){
            evt.consume();
            etiquetaError.setText("Escribe solo letras");
        }else{
            etiquetaError.setText("");
        }
    }
    
    
    /**
     * Restringe el texto solo numeros.
     * @param evt 
     */
    public static void escribirSoloNumeros(KeyEvent evt){
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
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
     * Restringe el texto solo numeros decimales. Escribe el aviso en el JLabel pasado.
     * @param evt
     * @param etiquetaError 
     */
    public static void escribirSoloDoubles(JTextField texto, KeyEvent evt, JLabel etiquetaError){
        
        if(texto.getText().isEmpty() && (!Character.isDigit(evt.getKeyChar()) || evt.getKeyChar()=='0') ){
            evt.consume();
            etiquetaError.setText("El primer digito no puede ser un punto o un 0");
        }else{
            
            if(!Character.isDigit(evt.getKeyChar()) && (texto.getText().contains(".") || evt.getKeyChar()!='.')){
                evt.consume();
                etiquetaError.setText("Solo numeros y puntos");
            }else{
                etiquetaError.setText("");                
            }
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

            if(text.isEmpty() && (!Character.isDigit(caracter) || caracter=='0') ){
                evt.consume();
                etiquetaError.setText("El primer digito no puede ser un punto o un 0");
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
    
    /**
     * Solo se puede escribir una longitud determinada.
     * @param campo
     * @param longitudMaxima
     * @param evt 
     */
    public static void noEscribirMasDe(JTextField campo, int longitudMaxima,KeyEvent evt){
        if(campo.getText().length()>=longitudMaxima){
                evt.consume();
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
     * Borra todas las filas de un DefaultTableModel
     * @param dtm 
     */
    public static void limpiarTabla(DefaultTableModel dtm){
       
        while(!dtm.getDataVector().isEmpty()){
            dtm.removeRow(0);
        }
    }
    
    /**
     * Borra todas los datos de un DefaultTableModel
     * @param dtm 
     */
    public static void limpiarDatosTabla(DefaultTableModel dtm){
       
        for(int i=0;i<dtm.getColumnCount();i++){
            for(int j=0;j<dtm.getRowCount();j++){
                dtm.setValueAt("", i, j);
            }
        }
    }
    
    /**
     * Centra el texto de la cabecera
     * @param table 
     */
    public static void centraCabecera(JTable table){
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel)rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    
    /**
     * Centra el texto de las celdas de la tabla
     * @param table
     * @param numColumnas 
     */
    public static void centrarDatos(JTable table, int numColumnas){
		 
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i=0;i<numColumnas;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
        }
    }
    
    /**
     * Establece el icono del JFrame
     * @param frame
     * @param rutaIcono 
     */
    public static void iconoJFrame(JFrame frame, String rutaIcono){
        frame.setIconImage(new ImageIcon(rutaIcono).getImage());
    }
    
    /**
     * Cambia el diseño del JFrame
     */
    public static void disenoGUI(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.out.println("Error setting Java LAF: "+e);
        }
    }
    
    /**
     * Añade un borde con titulo al panel pasado por parametro
     * @param panel
     * @param titulo 
     */
    public static void bordeConTitulo(JPanel panel, String titulo){
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
    }
    
    /**
     * Abre un fichero pasado por parametro
     * @param ruta 
     */
    public static void abrirFichero(String ruta){
        Desktop ficheroAEjecutar=Desktop.getDesktop();
        try {
                ficheroAEjecutar.open(new File(ruta));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    /**
     * Abre un fichero pasado por parametro
     * @param ruta 
     */
    public static void abrirFichero(File fichero){
        Desktop ficheroAEjecutar=Desktop.getDesktop();
        try {
                ficheroAEjecutar.open(fichero);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    /**
     * Abre el enlace pasado por parametro
     * @param enlaceAAceder 
     */
    public void enlace (String enlaceAAceder){
        Desktop enlace=Desktop.getDesktop();
        try {
                enlace.browse(new URI(enlaceAAceder));
        } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
        }
    }
    
    /**
     * Indica si el email es correcto
     * @param email
     * @return 
     */
    public static boolean emailCorrecto(String email){
        boolean existe=false;
        
        if(email.matches("[-\\w\\.]+@\\w+\\.\\w+")){
            existe=true;
        }
        
        return existe;
    }
    
    /**
     * Permite insertar un email y lo valida. Hasta que no se inserte un email valido, no deja de preguntar.
     * @return 
     */
    public static String validarEmail(){
        
        boolean interruptor=false;
        String email;
        do{
            email=JOptionPane.showInputDialog(null, "Introduce un email");
            if(emailCorrecto(email)){
                interruptor=true;
            }else{
                JOptionPane.showMessageDialog(null, "Error, el email no es valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }while(!interruptor);
        
        return email;
    }
    
    public static void rellenarDTMDesdeMatriz(DefaultTableModel modelo, Object matriz[][]){
        
        //String contenido[][]=tablero.contenidoTablero();
         String filas[]=new String[matriz.length];
         
         for(int i=0;i<matriz.length;i++){
             for(int j=0;j<matriz[0].length;j++){
                 filas[j]=matriz[i][j].toString();
             }
             modelo.addRow(filas);
         }
         
    }
    
    /**
     * Rellena un combobox con los datos pasados
     * @param cmb
     * @param datos 
     */
    public static void rellenaComboBox(JComboBox cmb, String datos[]){
        
        for(int i=0;i<datos.length;i++){
            cmb.addItem(datos[i]);
        }
        
    }
    
    /**
     * Rellena un combobox con datos numericos de una BD
     * @param cmb
     * @param rs
     * @param columna 
     */
    public static void rellenaComboBoxBDInt(JComboBox cmb, ResultSet rs, String columna){
        try {
            while(rs.next()){
                cmb.addItem(rs.getInt(columna));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MiSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Rellena un combobox con datos de cadena de una BD
     * @param cmb
     * @param rs
     * @param columna 
     */
    public static void rellenaComboBoxBDString(JComboBox cmb, ResultSet rs, String columna){
        try {
            while(rs.next()){
                cmb.addItem(rs.getString(columna));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MiSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Rellena un combobox con datos decimales de una BD
     * @param cmb
     * @param rs
     * @param columna 
     */
    public static void rellenaComboBoxBDDouble(JComboBox cmb, ResultSet rs, String columna){
        try {
            while(rs.next()){
                cmb.addItem(rs.getDouble(columna));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MiSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Rellena una tabla desde BD
     * @param resultSet
     * @param tabla 
     */
    public static void rellenaTablaBD(ResultSet resultSet, DefaultTableModel tabla){
        try {
            ResultSetMetaData metadatos = resultSet.getMetaData();
            
            tabla.setColumnCount(metadatos.getColumnCount());
            
            int numeroColumnas=tabla.getColumnCount();
            
            Object[] etiquetas = new Object[numeroColumnas];

            // Se obtiene cada una de las etiquetas para cada columna
            for (int i = 0; i < numeroColumnas; i++)
            {
               // Nuevamente, para ResultSetMetaData la primera columna es la 1. 
               etiquetas[i] = metadatos.getColumnLabel(i + 1); 
            }
            
             tabla.setColumnIdentifiers(etiquetas);
            
            int numeroFila = 0;
            
                // Para cada registro de resultado en la consulta 
                while (resultSet.next())
                {
                    // Se crea y rellena la fila para el modelo de la tabla.
                    Object[] datosFila = new Object[tabla.getColumnCount()];
                    for (int i = 0; i < tabla.getColumnCount(); i++)
                        datosFila[i] = resultSet.getObject(i + 1);
                    tabla.addRow(datosFila);
                    numeroFila++;
                }
                resultSet.close();
            } catch (SQLException ex) {
            Logger.getLogger(MiSwing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Activa o desactiva los controles
     * @param controles
     * @param activo 
     */
    public static void activa_DesactivaControles(Component[] controles, boolean activo){
        
        for(int i=0;i<controles.length;i++){
            controles[i].setEnabled(activo);
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
     * Si esta vacio devuelve null (usado para BD)
     * @param txt
     * @return 
     */
    public static String devolverEstado(JTextField txt){
        
        if(txt.getText().isEmpty()){
            return null;
        }else{
            return txt.getText();
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
    
    public static String[] obtenerValoresDe(JTextField[] campos){
        
        String datos[]=new String[campos.length];
        
        for(int i=0;i<campos.length;i++){
            
            datos[i]=campos[i].getText();
            
        }
        
        return datos;
        
    }
    
    public static void introducirValoresDe(JTextField[] campos, String[] datos){
        
        for(int i=0;i<campos.length;i++){
            
            campos[i].setText(datos[i]);
            
        }
        
    }
    
//    public static String comprobarVacios(JTextField[] campos){
//        
//        String error="";
//        
//        for(int i=0;i<campos.length;i++){
//            
//            if(campos[i].getText().isEmpty()){
//                error+="El campo de texto "+i+" no puede estar vacio"+"\n";
//            }
//        }
//        
//        return error;
//        
//    }
    
}
