package formularios;

import clases.FondoSwing;
import clases.Idioma;
import clases.MetodosComunes;
import clases.MiNeodatisSingleton;
import clases_bd.Categoria;
import clases_bd.Idiomas;
import clases_bd.Traducciones;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
public class MDApp extends javax.swing.JFrame {

    /**
     * Creates new form MDApp
     */
    public MDApp() {
        
        MetodosComunes.disenoGUI();
        
        String[][] tabla=new String[0][6];
        modelo=new DefaultTableModel(tabla,new Object[6]){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        fondo();
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        this.setMinimumSize(new Dimension((int)(screenSize.width*0.7),(int)(screenSize.height*0.7)));
        
        this.setIconImage(new ImageIcon("images/idiomas.png").getImage());
        this.setExtendedState(MAXIMIZED_BOTH);
        
        initComponents();
       
        icono();
             
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(1);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(1);
        
        columnas=new String[6];
        
        conexion = MiNeodatisSingleton.getInstance("Diccionario.db", "USR_DICCIONARIO", "123456");
        
        codigoIdiomaActual=ESPANOL;
        DameIdioma("Español");
        
        crearIdiomas();
        conexion.rellenarIdiomas(cmbIdioma, idioma.getProperty("todo"), -1);
        
        actualizarCategorias();
        
        rellenaTable();
        
    }
    
    
    private void actualizarIdiomas(){
         
        
        IQuery consulta=conexion.getOdb().criteriaQuery(Idiomas.class);
        
        Objects<Idiomas> objetos=conexion.getOdb().getObjects(consulta);
        
        String[] idiomas=cargaTodosIdiomas();
        
        Idiomas idiomaActual;
        for(int i=0;objetos.hasNext();i++){
            
            idiomaActual=objetos.next();
            
            idiomaActual.setIdioma(idiomas[i]);
            
            conexion.guardarObjeto(idiomaActual);
            
        }
        
        conexion.commit();
        
    }
    
    private void crearIdiomas(){
        
        int ultimoID=conexion.ultimoID("idIdioma", "clases_bd.Idiomas");
        
        if (!conexion.existeInt(ultimoID, "idIdioma", "clases_bd.Idiomas")){

            Idiomas id1=new Idiomas(1, "Español");
            
            conexion.guardarObjeto(id1);
            
            Idiomas id2=new Idiomas(2, "Inglés");

            conexion.guardarObjeto(id2);
            
            Idiomas id3=new Idiomas(3, "Frances");

            conexion.guardarObjeto(id3);
            
        }else{
            
            actualizarIdiomas(); 
            
        }
        
    }
    
    private void fondo(){
        
         try {
            FondoSwing fondo=new FondoSwing(ImageIO.read(new File("images/fondo.png")));
            JPanel panel=(JPanel)this.getContentPane();
            panel.setBorder(fondo);
        } catch (IOException ex) {
            
        }
        
    }
    
    public String[] cargaTodosIdiomas(){
            String todosIdiomas[]={idioma.getProperty("espanol"), idioma.getProperty("ingles"), idioma.getProperty("frances")};
            return todosIdiomas;
    }
    

    
    public void DameIdioma(String id) {
        idioma=new Idioma(id);
		
        this.setTitle(idioma.getProperty("titulo"));
        
        traduccion.setText(idioma.getProperty("traduccion"));
        anadir.setText(idioma.getProperty("anadir"));
        editar.setText(idioma.getProperty("editar"));
        borrar.setText(idioma.getProperty("borrar"));
        categoria.setText(idioma.getProperty("categoria"));
        anadirCategoria.setText(idioma.getProperty("anadirCategoria"));
        editarCategoria.setText(idioma.getProperty("editarCategoria"));
        borrarCategoria.setText(idioma.getProperty("borrarCategoria"));
        idiomaEspanol.setText(idioma.getProperty("espanol"));
        idiomaIngles.setText(idioma.getProperty("ingles"));
        
        
        lenguajes.setText(idioma.getProperty("lenguajes"));
        
        columnas[1]=idioma.getProperty("anadirTraduccion_textoOrigen");
        columnas[2]=idioma.getProperty("anadirTraduccion_textoMeta");
        columnas[3]=idioma.getProperty("anadirTraduccion_categoria");
        columnas[4]=idioma.getProperty("anadirTraduccion_idioma");
        columnas[5]=idioma.getProperty("anadirTraduccion_descripcion");
        modelo.setColumnIdentifiers(columnas);
        
        etiquetaTextoOrigenOMeta.setText(idioma.getProperty("etiquetaTextoOrigenOMeta"));
        etiquetaCategoria.setText(idioma.getProperty("etiquetaCategoria"));
        etiquetaIdioma.setText(idioma.getProperty("etiquetaIdioma"));
        botonActualizarTabla.setText(idioma.getProperty("botonActualizarTabla"));
        
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(idioma.getProperty("tituloPanel")));
        MetodosComunes.bordeConTitulo(panelBusqueda, idioma.getProperty("tituloPanel"));
        MetodosComunes.bordeConTitulo(panelOperacion, idioma.getProperty("tituloOperaciones"));
        
        btnNuevaTraduccion.setText(idioma.getProperty("botonNuevaTraduccion"));
        btnEditarTraduccion.setText(idioma.getProperty("botonEditarTraduccion"));
        btnBorrarTraduccion.setText(idioma.getProperty("botonBorrarTraduccion"));
        
        MenuSalir.setText(idioma.getProperty("salir"));
        menuJSalir.setText(idioma.getProperty("salir"));
    }
    
    public static int getCodigoIdioma() {
        return codigoIdiomaActual;
    }
    

    public void rellenaTable(){
		
            //Limpiamos la tabla antes
            MetodosComunes.limpiarTabla(modelo);

            IQuery consulta;
            
            ICriterion criterio1;
            ICriterion criterio2;
            ICriterion criterio3;
            ICriterion criterio4;
            
            ICriterion criterioPrincipal;
            
            criterio1=Where.like("palabraOrigen", "%"+busqueda.getText()+"%");
            criterio2=Where.like("palabraMeta", "%"+busqueda.getText()+"%");
  
            
            if(cmbIdioma.getSelectedIndex()!=0){
                
                Idiomas idioma=(Idiomas)conexion.devolverObjeto(cmbIdioma.getSelectedIndex(), "idIdioma", "clases_bd.Idiomas");
                criterio3=Where.equal("idiomas.idIdioma", idioma.getIdIdioma());
                
            }else{
                
                criterio3=Where.not(Where.equal("idiomas.idIdioma", 0));
                
            }
            
            
            if(cmbCategorias.getSelectedIndex()!=0){
               
                Categoria categoria=(Categoria)conexion.devolverObjeto(cmbCategorias.getSelectedItem().toString(), "descripcion", "clases_bd.Categoria");
                criterio4=Where.equal("categoria.descripcion", categoria.getDescripcion());
                
                criterioPrincipal=Where.and().add(criterio3).add(criterio4).add(Where.or().add(criterio1).add(criterio2));
            
            }else{
                criterioPrincipal=Where.and().add(criterio3).add(Where.or().add(criterio1).add(criterio2));
            
            }
                
                
            consulta=new CriteriaQuery(Traducciones.class, criterioPrincipal).orderByDesc("palabraMeta").orderByAsc("palabraOrigen");
            
            conexion.rellenarTabla(modelo, consulta);

            MetodosComunes.centrarDatos(jTable1);
            MetodosComunes.centraCabecera(jTable1);
            MetodosComunes.ocultarColumnaJTable(jTable1, 0);
    }
    
    public void actualizarCategorias(){
        
        conexion.rellenarComboBoxCategorias(cmbCategorias, idioma.getProperty("anadirTraduccion_eleccionCategoria"), conexion);
        
    }
    
    private void icono(){
        this.setIconImage(new ImageIcon("images"+File.separator+"idiomas.png").getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelBusqueda = new javax.swing.JPanel();
        etiquetaTextoOrigenOMeta = new javax.swing.JLabel();
        etiquetaCategoria = new javax.swing.JLabel();
        etiquetaIdioma = new javax.swing.JLabel();
        botonActualizarTabla = new javax.swing.JButton();
        cmbCategorias = new javax.swing.JComboBox();
        cmbIdioma = new javax.swing.JComboBox();
        busqueda = new javax.swing.JTextField();
        panelOperacion = new javax.swing.JPanel();
        btnNuevaTraduccion = new javax.swing.JButton();
        btnEditarTraduccion = new javax.swing.JButton();
        btnBorrarTraduccion = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        traduccion = new javax.swing.JMenu();
        anadir = new javax.swing.JMenuItem();
        editar = new javax.swing.JMenuItem();
        borrar = new javax.swing.JMenuItem();
        categoria = new javax.swing.JMenu();
        anadirCategoria = new javax.swing.JMenuItem();
        editarCategoria = new javax.swing.JMenuItem();
        borrarCategoria = new javax.swing.JMenuItem();
        lenguajes = new javax.swing.JMenu();
        idiomaEspanol = new javax.swing.JMenuItem();
        idiomaIngles = new javax.swing.JMenuItem();
        frances = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenu();
        menuJSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setIconImages(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(modelo);
        jTable1.setOpaque(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 717;
        gridBagConstraints.ipady = 723;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 80, 20, 21);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        panelBusqueda.setOpaque(false);
        panelBusqueda.setLayout(new java.awt.GridBagLayout());

        etiquetaTextoOrigenOMeta.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(etiquetaTextoOrigenOMeta, gridBagConstraints);

        etiquetaCategoria.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(etiquetaCategoria, gridBagConstraints);

        etiquetaIdioma.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(etiquetaIdioma, gridBagConstraints);

        botonActualizarTabla.setText("Actualizar tabla");
        botonActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarTablaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelBusqueda.add(botonActualizarTabla, gridBagConstraints);

        cmbCategorias.setMinimumSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(cmbCategorias, gridBagConstraints);

        cmbIdioma.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(cmbIdioma, gridBagConstraints);

        busqueda.setPreferredSize(new java.awt.Dimension(120, 20));
        busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelBusqueda.add(busqueda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(panelBusqueda, gridBagConstraints);

        panelOperacion.setOpaque(false);
        panelOperacion.setLayout(new java.awt.GridBagLayout());

        btnNuevaTraduccion.setText("Nuevo");
        btnNuevaTraduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaTraduccionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 30, 10);
        panelOperacion.add(btnNuevaTraduccion, gridBagConstraints);

        btnEditarTraduccion.setText("Editar");
        btnEditarTraduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTraduccionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 30, 10);
        panelOperacion.add(btnEditarTraduccion, gridBagConstraints);

        btnBorrarTraduccion.setText("Eliminar");
        btnBorrarTraduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarTraduccionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 30, 10);
        panelOperacion.add(btnBorrarTraduccion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 130, 10, 0);
        getContentPane().add(panelOperacion, gridBagConstraints);

        traduccion.setText("Traduccion");

        anadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        anadir.setText("Añadir...");
        anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirActionPerformed(evt);
            }
        });
        traduccion.add(anadir);

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        editar.setText("Editar...");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        traduccion.add(editar);

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        borrar.setText("Borrar...");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        traduccion.add(borrar);

        jMenuBar1.add(traduccion);

        categoria.setText("Categoria");

        anadirCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        anadirCategoria.setText("Añadir....");
        anadirCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirCategoriaActionPerformed(evt);
            }
        });
        categoria.add(anadirCategoria);

        editarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        editarCategoria.setText("Editar...");
        editarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCategoriaActionPerformed(evt);
            }
        });
        categoria.add(editarCategoria);

        borrarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        borrarCategoria.setText("Borrar...");
        borrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarCategoriaActionPerformed(evt);
            }
        });
        categoria.add(borrarCategoria);

        jMenuBar1.add(categoria);

        lenguajes.setText("Idioma");

        idiomaEspanol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/espana.png"))); // NOI18N
        idiomaEspanol.setText("Español");
        idiomaEspanol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idiomaEspanolActionPerformed(evt);
            }
        });
        lenguajes.add(idiomaEspanol);

        idiomaIngles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/gran_bretana.png"))); // NOI18N
        idiomaIngles.setText("Ingles");
        idiomaIngles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idiomaInglesActionPerformed(evt);
            }
        });
        lenguajes.add(idiomaIngles);

        frances.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/frances.png"))); // NOI18N
        frances.setText("Frances");
        frances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                francesActionPerformed(evt);
            }
        });
        lenguajes.add(frances);

        jMenuBar1.add(lenguajes);

        MenuSalir.setText("Salir");

        menuJSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        menuJSalir.setText("Salir");
        menuJSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuJSalirActionPerformed(evt);
            }
        });
        MenuSalir.add(menuJSalir);

        jMenuBar1.add(MenuSalir);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarTablaActionPerformed
        rellenaTable();
    }//GEN-LAST:event_botonActualizarTablaActionPerformed

    private void anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirActionPerformed
					
        AnadirTraduccion dialogo=new AnadirTraduccion(this, idioma);
        dialogo.setVisible(true);

        rellenaTable();
        
    }//GEN-LAST:event_anadirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        conexion.cerrarODB();
        
    }//GEN-LAST:event_formWindowClosed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        EditarTraduccion dialogo=new EditarTraduccion(this,idioma);
        dialogo.setVisible(true);
        
        rellenaTable();
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed

        BorrarTraduccion dialogo=new BorrarTraduccion(this, idioma);
        dialogo.setVisible(true);
        
        rellenaTable();
        
    }//GEN-LAST:event_borrarActionPerformed

    private void anadirCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirCategoriaActionPerformed
      
        AnadirCategoria dialogo=new AnadirCategoria(this, idioma);
        dialogo.setVisible(true);
        
        actualizarCategorias();
        
        rellenaTable();
    }//GEN-LAST:event_anadirCategoriaActionPerformed

    private void editarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCategoriaActionPerformed
        EditarCategoria dialogo=new EditarCategoria(this, idioma);
        dialogo.setVisible(true);
        
        actualizarCategorias();
        
        rellenaTable();
        
    }//GEN-LAST:event_editarCategoriaActionPerformed

    private void borrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarCategoriaActionPerformed
        BorrarCategoria dialogo=new BorrarCategoria(this, idioma);
        dialogo.setVisible(true);
        
        actualizarCategorias();
        
        rellenaTable();
        
    }//GEN-LAST:event_borrarCategoriaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
    }//GEN-LAST:event_formWindowClosing

    private void busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaActionPerformed
        
    }//GEN-LAST:event_busquedaActionPerformed

    private void btnNuevaTraduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaTraduccionActionPerformed
       
        AnadirTraduccion dialogo=new AnadirTraduccion(this, idioma);
        dialogo.setVisible(true);

        rellenaTable();
        
    }//GEN-LAST:event_btnNuevaTraduccionActionPerformed

    private void btnEditarTraduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTraduccionActionPerformed
        
        if(jTable1.getSelectedRow()!=-1){
              
            Traducciones traduActual = (Traducciones)conexion.devolverObjeto((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0), "idTraduccion", "clases_bd.Traducciones");
            
            EditarTraduccion ventana=new EditarTraduccion(this,  idioma, traduActual);
            ventana.setVisible(true);
            
            rellenaTable();
           
        }else{
            JOptionPane.showMessageDialog(this, 
                                        idioma.getProperty("filaTablaNoSeleccionada"), 
                                        idioma.getProperty("anadirTraduccion_tituloMensaje1"), 
                                        JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnEditarTraduccionActionPerformed

    private void btnBorrarTraduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTraduccionActionPerformed
        
        
        if(jTable1.getSelectedRow()!=-1){
  
            int eleccion=JOptionPane.showConfirmDialog(this, 
                                                        idioma.getProperty("borrarTraduccion_mensajeConfirmar"), 
                                                        idioma.getProperty("borrarTraduccion_mensajeConfirmarTitulo"), 
                                                        JOptionPane.YES_NO_OPTION);
            
            if(eleccion==JOptionPane.YES_OPTION){
    
                Traducciones traduActual = (Traducciones)conexion.devolverObjeto((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0), "idTraduccion", "clases_bd.Traducciones");
            
                conexion.borrarObjeto(traduActual);

                conexion.commit();
                
                JOptionPane.showMessageDialog(this, 
                        idioma.getProperty("borrarTraduccion_borradoExito"), 
                        idioma.getProperty("borrarTraduccion_borradoExitoTitulo"), 
                        JOptionPane.INFORMATION_MESSAGE);
                
                
                rellenaTable();
        
            }
            
        }else{
            JOptionPane.showMessageDialog(this, 
                                        idioma.getProperty("filaTablaNoSeleccionada"), 
                                        idioma.getProperty("anadirTraduccion_tituloMensaje1"), 
                                        JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnBorrarTraduccionActionPerformed

    private void idiomaInglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idiomaInglesActionPerformed
        
        DameIdioma("Inglés");
        
        codigoIdiomaActual=INGLES;
        
        actualizarIdiomas();
        
        actualizarCategorias();
        
        conexion.rellenarIdiomas(cmbIdioma, idioma.getProperty("todo"), -1);
        
        rellenaTable();
        
    }//GEN-LAST:event_idiomaInglesActionPerformed

    private void idiomaEspanolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idiomaEspanolActionPerformed
        
        DameIdioma("Espanol");
        
        codigoIdiomaActual=ESPANOL;
        actualizarIdiomas();
        
        conexion.rellenarIdiomas(cmbIdioma, idioma.getProperty("todo"), -1);
        
        actualizarCategorias();
        
        rellenaTable();
        
    }//GEN-LAST:event_idiomaEspanolActionPerformed

    private void francesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_francesActionPerformed
        
        DameIdioma("Frances");
        
        codigoIdiomaActual=FRANCES;
        actualizarIdiomas();
        
        conexion.rellenarIdiomas(cmbIdioma, idioma.getProperty("todo"), -1);
        
        actualizarCategorias();
        
        rellenaTable();
        
    }//GEN-LAST:event_francesActionPerformed

    private void menuJSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuJSalirActionPerformed
        
        conexion.cerrarODB();
        
        System.exit(0);
        
    }//GEN-LAST:event_menuJSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MDApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuSalir;
    private javax.swing.JMenuItem anadir;
    private javax.swing.JMenuItem anadirCategoria;
    private javax.swing.JMenuItem borrar;
    private javax.swing.JMenuItem borrarCategoria;
    private javax.swing.JButton botonActualizarTabla;
    private javax.swing.JButton btnBorrarTraduccion;
    private javax.swing.JButton btnEditarTraduccion;
    private javax.swing.JButton btnNuevaTraduccion;
    private javax.swing.JTextField busqueda;
    private javax.swing.JMenu categoria;
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbIdioma;
    private javax.swing.JMenuItem editar;
    private javax.swing.JMenuItem editarCategoria;
    private javax.swing.JLabel etiquetaCategoria;
    private javax.swing.JLabel etiquetaIdioma;
    private javax.swing.JLabel etiquetaTextoOrigenOMeta;
    private javax.swing.JMenuItem frances;
    private javax.swing.JMenuItem idiomaEspanol;
    private javax.swing.JMenuItem idiomaIngles;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu lenguajes;
    private javax.swing.JMenuItem menuJSalir;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelOperacion;
    private javax.swing.JMenu traduccion;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modelo;
    private Idioma idioma;
    private String[] columnas;
    private MiNeodatisSingleton conexion;
    
    //////////////
    //Constantes//
    //////////////

    public static int codigoIdiomaActual;
    
    public static final int ESPANOL=0;
    public static final int INGLES=1;
    public static final int FRANCES=2;
    public static final int PORTUGUES=3;
    public static final int ITALIANO=4;
    public static final int ALEMAN=5;
    public static final int ARABE=6;

   
}
