/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import clases_bd.Categoria;
import clases_bd.Traducciones;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author Fernando
 */
public class MetodosComunes {
    
    /**
     * Añade un borde con titulo al panel pasado por parametro
     * @param panel
     * @param titulo 
     */
    public static void bordeConTitulo(JPanel panel, String titulo){
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
    }
    
    /**
     * Cambia el diseño del JFrame, segun el SO estemos
     */
    public static void disenoGUI(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.out.println("Error setting Java LAF: "+e);
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
     */
    public static void centrarDatos(JTable table){
		 
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        int numColumnas=table.getColumnCount();
        
        for(int i=0;i<numColumnas;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
        }
    }
    
    public static int devolverCodigoComboBox(JComboBox cmb){
        
        String[] newSelection = (String[])cmb.getSelectedItem();
        
        return Integer.parseInt(newSelection[0]);
        
    }
    
    public static void ocultarColumnaJTable(JTable tabla, int columna){

        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        
        tabla.getColumnModel().getColumn(columna).setPreferredWidth(0);
        
    }
   
    
    
    
    
}
