/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ad_seguro_vehiculos;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class frmPartes extends javax.swing.JInternalFrame {

    
     public static frmPartes getInstancia() {

        if (instancia == null || instancia.isClosed) {
            instancia = new frmPartes();
        }
        return instancia;
    }
    
    
    private frmPartes() {
        
        modeloPartes=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modeloSubPartes=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        
        
        conexion=frmPrincipal.devConexion();
        
        initComponents();
        
        //Grupos de botones
        ButtonGroup grupoPartes=new ButtonGroup();
        
        grupoPartes.add(rdbEliminadoPartes);
        grupoPartes.add(rdbNoEliminadoPartes);
        
        rdbNoEliminadoPartes.setSelected(true);
        
        ButtonGroup grupoSubPartes=new ButtonGroup();
        
        grupoSubPartes.add(rdbEliminadoSubPartes);
        grupoSubPartes.add(rdbNoEliminadoSubPartes);
        
        rdbNoEliminadoSubPartes.setSelected(true);
        
        //rellenamos los comboboxes
        conexion.rellenaComboBox2Columnas(cmbMatricula, CONSULTA_MATRICULA, "-Elige una matricula-", "idvehiculo", "matricula");
        MetodosComunes.rellenaComboBox(cmbFiltroTipo, frmPartes.TIPOS);
        rellenarComboBoxResponsable();
        
        //Titulos paneles
        MetodosComunes.bordeConTitulo(panelPartes, "Partes");
        MetodosComunes.bordeConTitulo(panelSubPartes, "SubPartes");
        MetodosComunes.bordeConTitulo(panelFiltroPartes, "Filtro Partes");
        MetodosComunes.bordeConTitulo(panelFiltroSubPartes, "Filtro SubPartes");
        
        //rellenamos la tabla partes
        rellenaPartes(CONSULTA_PARTE+NOELIMINADO+devolverFiltroPartes());
        
        //el evento de cambio de fila seleccionada no esta en netbeans, lo hago yo mismo
        eventoEspecial();
        
        btnRestaurarParte.setEnabled(false);
        btnRestaurarSubParte.setEnabled(false);
        
        MetodosComunes.evitarPegar(txtCoste);
        
        //Atributo usado para combobox de matricula
        terminado=true;
        
    }
    
    private void eventoEspecial(){
        
        ListSelectionModel rowSM = tablaPartes.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm =
                    (ListSelectionModel)e.getSource();

                if (lsm.isSelectionEmpty()) {
                    //no rows are selected
                    MetodosComunes.limpiarTabla(modeloSubPartes);
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    //selectedRow is selected

                    rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(selectedRow, 0)+devolverEstadoSubPartes());

                }
            }
        });
        
        
    }
    
    private void rellenaPartes(String consulta){
        
        MetodosComunes.limpiarTabla(modeloPartes);
        
        conexion.ejecutarConsulta(consulta);
        
        conexion.rellenaJTableBD(modeloPartes);
        
        modificarFecha();
        
        //ocultamos el id
        MetodosComunes.ocultarColumnaJTable(tablaPartes, 0);
        
        modificaResponsable();
        
        //ajustamos el tamaño
        tablaPartes.getColumnModel().getColumn(1).setPreferredWidth(1);
        tablaPartes.getColumnModel().getColumn(3).setPreferredWidth(1);
        tablaPartes.getColumnModel().getColumn(4).setPreferredWidth(1);
        tablaPartes.getColumnModel().getColumn(6).setPreferredWidth(1);
        
        MetodosComunes.centraCabecera(tablaPartes);
        
        MetodosComunes.centrarDatos(tablaPartes);
       
        conexion.cerrarResult();
        conexion.cerrarSentencia();
        
    }
    
    public void rellenaSubPartes(String consulta){
        
        MetodosComunes.limpiarTabla(modeloSubPartes);
        
        conexion.ejecutarConsulta(consulta);
        
        conexion.rellenaJTableBD(modeloSubPartes);
        
        MetodosComunes.ocultarColumnaJTable(tablaSubPartes, 0);
        
        modificaTipo();
        
        //ajustamos las columnas
        tablaSubPartes.getColumnModel().getColumn(1).setPreferredWidth(1);
        tablaSubPartes.getColumnModel().getColumn(3).setPreferredWidth(1);
        
        //centramos todo
        MetodosComunes.centraCabecera(tablaSubPartes);
        MetodosComunes.centrarDatos(tablaSubPartes);
        
        conexion.cerrarResult();
        conexion.cerrarSentencia();
        
    }
    
    private void rellenarComboBoxResponsable(){
        
        String datos[]=new String[2];
        
        datos[0]=Integer.toString(-1);
        datos[1]="-Responsabilidad-";
        cmbResponsable.addItem(new String[] {datos[0],datos[1],});
        
        datos[0]=Integer.toString(0);
        datos[1]="Responsable";
        cmbResponsable.addItem(new String[] {datos[0],datos[1],});
        
        datos[0]=Integer.toString(1);
        datos[1]="No Responsable";
        cmbResponsable.addItem(new String[] {datos[0],datos[1],});
        
        cmbResponsable.setRenderer (new DefaultListCellRenderer() 
            {
                @Override
                public java.awt.Component getListCellRendererComponent (
                JList l,Object o,int i,boolean s, boolean f)
                {
                    return new JLabel (((String[])o)[1]);  //Visualiza sólo la columna 1, que es el nombre
                }
            });
        
        
    }
    
    private void modificaTipo(){
        
        for(int i=0;i<tablaSubPartes.getRowCount();i++){
            
            if(tablaSubPartes.getValueAt(i, 1).equals("P")){
                tablaSubPartes.setValueAt("Personal", i, 1);
            }else{
                tablaSubPartes.setValueAt("Vehiculo", i, 1);
            }
            
        }
        
        
    }
    
    private void modificaResponsable(){
        
        for(int i=0;i<tablaPartes.getRowCount();i++){
            
            if(tablaPartes.getValueAt(i, 4).equals("S")){
                tablaPartes.setValueAt("Si", i, 4);
            }else{
                tablaPartes.setValueAt("No", i, 4);
            }
        }
        
    }
    
     //Cambio de fecha cientifica a real en todas las filas
    private void modificarFecha(){
        
        for(int i=0;i<modeloPartes.getRowCount();i++){
            
            BigDecimal fecha=(BigDecimal)modeloPartes.getValueAt(i, 3);
            
            Date fechaActual=MetodosComunes.deNumeroAFecha(fecha.intValue());
            
            String valorActual=fechaActual.getDate()+"/"+(fechaActual.getMonth()+1)+"/"+(fechaActual.getYear()+1900);
            
            modeloPartes.setValueAt(valorActual, i, 3);
            
        }
            
    }
    
    private String devolverEstadoPartes(){
        
        if (rdbEliminadoPartes.isSelected()){
            return ELIMINADO;
        }else{
            return NOELIMINADO;
        }
        
    }
    
    private String devolverEstadoSubPartes(){
        
        if (rdbEliminadoSubPartes.isSelected()){
            return ELIMINADO;
        }else{
            return NOELIMINADO;
        }
        
    }
    
    //Devuelvo los datos del filtro
    private String devolverFiltroPartes(){
        
        String condicion="";
        
        if(cmbMatricula.getSelectedIndex()!=0){
            condicion+=" and v.idvehiculo="+MetodosComunes.devolverCodigoComboBox(cmbMatricula);
        }
        
        if(MetodosComunes.devolverCodigoComboBox(cmbResponsable)!=-1){
            if(MetodosComunes.devolverCodigoComboBox(cmbResponsable)==0){
                condicion+=" and p.responsable='S'";
            }else{
                condicion+=" and p.responsable='N'";
            }
        }
        
        if(dcDe.getDate()!=null || dcHasta.getDate()!=null){
            
            if(MetodosComunes.deFechaANumero(dcDe.getDate())<MetodosComunes.deFechaANumero(dcHasta.getDate())){
                condicion+=" and p.fecha_parte between "+MetodosComunes.deFechaANumero(dcDe.getDate())+" and "+MetodosComunes.deFechaANumero(dcHasta.getDate())+"";    
            }else{
                JOptionPane.showMessageDialog(this, "La fecha DE debe ser menor que la fecha HASTA, no se ha tenido en cuenta en el filtro");
            }
            
        }
        
        return condicion;
        
    }
    
    
    //Devuelvo los datos del filtro
    private String devolverFiltroSubPartes(){
        
        String condicion="";
        
        if(cmbFiltroTipo.getSelectedIndex()!=0){
            condicion+=" and tipo='"+cmbFiltroTipo.getSelectedItem().toString().charAt(0)+"'";
        }
        
        if(!txtAreaDescripcion.getText().isEmpty()){
            condicion+=" and upper(descrip) like '%"+txtAreaDescripcion.getText().toUpperCase()+"%'";
        }
        
        if(!txtCoste.getText().isEmpty()){
            condicion+=" and coste"+cmbFiltroOperacion.getSelectedItem().toString()+txtCoste.getText();
        }
        
        return condicion;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPartes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPartes = new javax.swing.JTable();
        bntNuevoParte = new javax.swing.JButton();
        btnEditarParte = new javax.swing.JButton();
        btnEliminarParte = new javax.swing.JButton();
        rdbNoEliminadoPartes = new javax.swing.JRadioButton();
        rdbEliminadoPartes = new javax.swing.JRadioButton();
        btnRestaurarParte = new javax.swing.JButton();
        panelSubPartes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSubPartes = new javax.swing.JTable();
        btnEliminarSubParte = new javax.swing.JButton();
        btnEditarSubParte = new javax.swing.JButton();
        btnNuevoSubParte = new javax.swing.JButton();
        rdbNoEliminadoSubPartes = new javax.swing.JRadioButton();
        rdbEliminadoSubPartes = new javax.swing.JRadioButton();
        btnRestaurarSubParte = new javax.swing.JButton();
        panelFiltroPartes = new javax.swing.JPanel();
        cmbMatricula = new javax.swing.JComboBox();
        cmbResponsable = new javax.swing.JComboBox();
        dcDe = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dcHasta = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnFiltrarPartes = new javax.swing.JButton();
        panelFiltroSubPartes = new javax.swing.JPanel();
        cmbFiltroTipo = new javax.swing.JComboBox();
        txtCoste = new javax.swing.JTextField();
        cmbFiltroOperacion = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblControlNumeros = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaDescripcion = new javax.swing.JTextArea();
        btnFiltrarSubPartes = new javax.swing.JButton();
        lblControlCoste = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Partes y subpartes");

        tablaPartes.setModel(modeloPartes);
        tablaPartes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaPartes);

        bntNuevoParte.setText("Nuevo");
        bntNuevoParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNuevoParteActionPerformed(evt);
            }
        });

        btnEditarParte.setText("Editar");
        btnEditarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarParteActionPerformed(evt);
            }
        });

        btnEliminarParte.setText("Eliminar");
        btnEliminarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarParteActionPerformed(evt);
            }
        });

        rdbNoEliminadoPartes.setText("No eliminado");
        rdbNoEliminadoPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoEliminadoPartesActionPerformed(evt);
            }
        });

        rdbEliminadoPartes.setText("Eliminado");
        rdbEliminadoPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbEliminadoPartesActionPerformed(evt);
            }
        });

        btnRestaurarParte.setText("Restaurar");
        btnRestaurarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarParteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPartesLayout = new javax.swing.GroupLayout(panelPartes);
        panelPartes.setLayout(panelPartesLayout);
        panelPartesLayout.setHorizontalGroup(
            panelPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPartesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(panelPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbNoEliminadoPartes)
                    .addComponent(rdbEliminadoPartes)
                    .addGroup(panelPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bntNuevoParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestaurarParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        panelPartesLayout.setVerticalGroup(
            panelPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPartesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPartesLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(44, Short.MAX_VALUE))
                    .addGroup(panelPartesLayout.createSequentialGroup()
                        .addComponent(bntNuevoParte)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarParte)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarParte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestaurarParte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(rdbNoEliminadoPartes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbEliminadoPartes)
                        .addGap(30, 30, 30))))
        );

        tablaSubPartes.setModel(modeloSubPartes);
        tablaSubPartes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaSubPartes);

        btnEliminarSubParte.setText("Eliminar");
        btnEliminarSubParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSubParteActionPerformed(evt);
            }
        });

        btnEditarSubParte.setText("Editar");
        btnEditarSubParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSubParteActionPerformed(evt);
            }
        });

        btnNuevoSubParte.setText("Nuevo");
        btnNuevoSubParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoSubParteActionPerformed(evt);
            }
        });

        rdbNoEliminadoSubPartes.setText("No eliminado");
        rdbNoEliminadoSubPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoEliminadoSubPartesActionPerformed(evt);
            }
        });

        rdbEliminadoSubPartes.setText("Eliminado");
        rdbEliminadoSubPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbEliminadoSubPartesActionPerformed(evt);
            }
        });

        btnRestaurarSubParte.setText("Restaurar");
        btnRestaurarSubParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarSubParteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSubPartesLayout = new javax.swing.GroupLayout(panelSubPartes);
        panelSubPartes.setLayout(panelSubPartesLayout);
        panelSubPartesLayout.setHorizontalGroup(
            panelSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubPartesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(panelSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbNoEliminadoSubPartes)
                    .addComponent(rdbEliminadoSubPartes)
                    .addGroup(panelSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnNuevoSubParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarSubParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarSubParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRestaurarSubParte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(33, 33, 33))
        );
        panelSubPartesLayout.setVerticalGroup(
            panelSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubPartesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSubPartesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(panelSubPartesLayout.createSequentialGroup()
                        .addComponent(btnNuevoSubParte)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarSubParte)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarSubParte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestaurarSubParte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdbNoEliminadoSubPartes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbEliminadoSubPartes)
                        .addGap(26, 26, 26))))
        );

        cmbMatricula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMatriculaItemStateChanged(evt);
            }
        });

        cmbResponsable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbResponsableItemStateChanged(evt);
            }
        });

        jLabel1.setText("Matricula");

        jLabel2.setText("Responsable");

        jLabel3.setText("De");

        jLabel4.setText("Hasta");

        btnFiltrarPartes.setText("Filtrar");
        btnFiltrarPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPartesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltroPartesLayout = new javax.swing.GroupLayout(panelFiltroPartes);
        panelFiltroPartes.setLayout(panelFiltroPartesLayout);
        panelFiltroPartesLayout.setHorizontalGroup(
            panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroPartesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltroPartesLayout.createSequentialGroup()
                        .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFiltroPartesLayout.createSequentialGroup()
                        .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(51, 51, 51)
                        .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcDe, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(dcHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnFiltrarPartes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFiltroPartesLayout.setVerticalGroup(
            panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroPartesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcDe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelFiltroPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnFiltrarPartes)
                .addContainerGap())
        );

        cmbFiltroTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFiltroTipoItemStateChanged(evt);
            }
        });
        cmbFiltroTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroTipoActionPerformed(evt);
            }
        });

        txtCoste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCosteKeyTyped(evt);
            }
        });

        cmbFiltroOperacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<", ">", "=", "<=", ">=" }));

        jLabel5.setText("Coste");

        jLabel6.setText("Tipo");

        jLabel7.setText("Descripcion");

        txtAreaDescripcion.setColumns(20);
        txtAreaDescripcion.setRows(5);
        jScrollPane3.setViewportView(txtAreaDescripcion);

        btnFiltrarSubPartes.setText("Filtrar");
        btnFiltrarSubPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarSubPartesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltroSubPartesLayout = new javax.swing.GroupLayout(panelFiltroSubPartes);
        panelFiltroSubPartes.setLayout(panelFiltroSubPartesLayout);
        panelFiltroSubPartesLayout.setHorizontalGroup(
            panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(43, 43, 43)
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFiltroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                            .addComponent(cmbFiltroOperacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(31, 31, 31)
                            .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblControlCoste, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                                    .addComponent(txtCoste, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(113, 113, 113))))))
                .addGap(22, 22, 22))
            .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnFiltrarSubPartes, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblControlNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFiltroSubPartesLayout.setVerticalGroup(
            panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFiltroTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltroSubPartesLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltroSubPartesLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(44, 44, 44)))
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFiltroOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCoste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFiltroSubPartesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblControlNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrarSubPartes)
                    .addComponent(lblControlCoste, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelFiltroSubPartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelSubPartes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelFiltroPartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelPartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFiltroPartes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPartes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSubPartes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFiltroSubPartes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addGap(108, 108, 108))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarParteActionPerformed
        
        if (tablaPartes.getSelectedRow()!=-1){
            
            int eleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de borrar el parte? Nota, las subpartes no seran afectadas.", "Confirmacion", JOptionPane.YES_NO_OPTION);
            
            if(eleccion==JOptionPane.YES_OPTION){
                
                
                BigDecimal idParte=(BigDecimal)tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0);
                
                //Eliminamos la parte y las subpartes asociadas
                conexion.ejecutarInstruccion("update s_subpartes set eliminado=1 where idparte="+idParte.intValue());
                
                conexion.commit();
                
                rellenaPartes(CONSULTA_PARTE+devolverEstadoPartes()+devolverFiltroPartes());
                
                JOptionPane.showMessageDialog(this, "Parte y subpartes asociadas eliminadas");
                
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccionadoninguna fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnEliminarParteActionPerformed

    private void bntNuevoParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNuevoParteActionPerformed
        
        InformacionParte ventana= new InformacionParte();
        ventana.setVisible(true);
        
        rellenaPartes(CONSULTA_PARTE+devolverEstadoPartes()+devolverFiltroPartes());
        
    }//GEN-LAST:event_bntNuevoParteActionPerformed

    private void rdbNoEliminadoSubPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNoEliminadoSubPartesActionPerformed
        
        if (tablaPartes.getSelectedRow()!=-1){
            rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+NOELIMINADO+devolverFiltroSubPartes());
            
        }
        
        btnEliminarSubParte.setEnabled(true);
        btnRestaurarSubParte.setEnabled(false);
        
    }//GEN-LAST:event_rdbNoEliminadoSubPartesActionPerformed

    private void btnEditarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarParteActionPerformed
        
        if (tablaPartes.getSelectedRow()!=-1){
            
            BigDecimal idParte=(BigDecimal)tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0);
            InformacionParte ventana= new InformacionParte(idParte.intValue());
            ventana.setVisible(true);

            rellenaPartes(CONSULTA_PARTE+devolverEstadoPartes()+devolverFiltroPartes());
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccionado ninguna fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnEditarParteActionPerformed

    private void btnNuevoSubParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoSubParteActionPerformed
       
        if (tablaPartes.getSelectedRow()!=-1){
            
            
            BigDecimal idParte=(BigDecimal)tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0);
            
            InformacionSubParte ventana=new InformacionSubParte(idParte.intValue());
            ventana.setVisible(true);
            
            rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccinado ningun parte", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnNuevoSubParteActionPerformed

    private void btnEditarSubParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSubParteActionPerformed
       
         if (tablaSubPartes.getSelectedRow()!=-1){
            
            
            BigDecimal idParte=(BigDecimal)tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0);
            BigDecimal idSubParte=(BigDecimal)tablaSubPartes.getValueAt(tablaSubPartes.getSelectedRow(), 0);
        
            InformacionSubParte ventana=new InformacionSubParte(idParte.intValue(), idSubParte.intValue());
            ventana.setVisible(true);
            
            rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());

            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccinado ningun subparte", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnEditarSubParteActionPerformed

    private void btnEliminarSubParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSubParteActionPerformed
         
        if (tablaSubPartes.getSelectedRow()!=-1){
            
            int eleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres borrar la subparte?","Confirmacion",JOptionPane.YES_NO_OPTION);
            
            if(eleccion==JOptionPane.YES_OPTION){

                BigDecimal idSubParte=(BigDecimal)tablaSubPartes.getValueAt(tablaSubPartes.getSelectedRow(), 0);

                conexion.ejecutarInstruccion("update s_subpartes set eliminado=1 where idsubparte="+idSubParte.intValue());

                conexion.commit();

                rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());

                JOptionPane.showMessageDialog(this, "Subparte eliminada");

            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccinado ningun subparte", "Error", JOptionPane.ERROR_MESSAGE);
        }
         
    }//GEN-LAST:event_btnEliminarSubParteActionPerformed

    private void rdbNoEliminadoPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNoEliminadoPartesActionPerformed
       
        rellenaPartes(CONSULTA_PARTE+NOELIMINADO+devolverFiltroPartes());
        btnEliminarParte.setEnabled(true);
        btnRestaurarParte.setEnabled(false);
    }//GEN-LAST:event_rdbNoEliminadoPartesActionPerformed

    private void rdbEliminadoPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbEliminadoPartesActionPerformed
        rellenaPartes(CONSULTA_PARTE+ELIMINADO+devolverFiltroPartes());
        btnEliminarParte.setEnabled(false);
        btnRestaurarParte.setEnabled(true);
    }//GEN-LAST:event_rdbEliminadoPartesActionPerformed

    private void rdbEliminadoSubPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbEliminadoSubPartesActionPerformed

        
        if (tablaPartes.getSelectedRow()!=-1){
            rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+ELIMINADO+devolverFiltroSubPartes());
        }
        
        btnEliminarSubParte.setEnabled(false);
        btnRestaurarSubParte.setEnabled(true);
               
        
    }//GEN-LAST:event_rdbEliminadoSubPartesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnFiltrarPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPartesActionPerformed
        rellenaPartes(CONSULTA_PARTE+devolverEstadoPartes()+devolverFiltroPartes());
    }//GEN-LAST:event_btnFiltrarPartesActionPerformed

    private void btnFiltrarSubPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarSubPartesActionPerformed
        
        if (tablaPartes.getSelectedRow()!=-1){
            rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());
        }else{
            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna parte", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnFiltrarSubPartesActionPerformed

    private void cmbMatriculaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMatriculaItemStateChanged

        //Hasta que no termine el constructor, no rellena ninguna de las partes
        if(ItemEvent.SELECTED == evt.getStateChange() && terminado){
            
            rellenaPartes(CONSULTA_PARTE+devolverEstadoPartes()+devolverFiltroPartes());
            
        }
        
    }//GEN-LAST:event_cmbMatriculaItemStateChanged

    private void cmbResponsableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbResponsableItemStateChanged
        
    }//GEN-LAST:event_cmbResponsableItemStateChanged

    private void cmbFiltroTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFiltroTipoItemStateChanged
        
    }//GEN-LAST:event_cmbFiltroTipoItemStateChanged

    private void cmbFiltroTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroTipoActionPerformed
        
    }//GEN-LAST:event_cmbFiltroTipoActionPerformed

    private void btnRestaurarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarParteActionPerformed
       
        
        if (tablaPartes.getSelectedRow()!=-1){
            
            int eleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres restaurar la parte?","Confirmacion",JOptionPane.YES_NO_OPTION);
            
            if(eleccion==JOptionPane.YES_OPTION){

                BigDecimal idParte=(BigDecimal)tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0);

                conexion.ejecutarInstruccion("update s_partes set eliminado=0 where idparte="+idParte.intValue());

                conexion.commit();

                rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());

                JOptionPane.showMessageDialog(this, "Parte restaurada");

            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccinado ninguna parte", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRestaurarParteActionPerformed

    private void btnRestaurarSubParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarSubParteActionPerformed
       
        if (tablaSubPartes.getSelectedRow()!=-1){
            
            int eleccion=JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres restaurar la subparte?","Confirmacion",JOptionPane.YES_NO_OPTION);
            
            if(eleccion==JOptionPane.YES_OPTION){

                BigDecimal idSubParte=(BigDecimal)tablaSubPartes.getValueAt(tablaSubPartes.getSelectedRow(), 0);

                conexion.ejecutarInstruccion("update s_subpartes set eliminado=0 where idsubparte="+idSubParte.intValue());

                conexion.commit();

                rellenaSubPartes(CONSULTA_SUBPARTE+tablaPartes.getValueAt(tablaPartes.getSelectedRow(), 0)+devolverEstadoSubPartes()+devolverFiltroSubPartes());

                JOptionPane.showMessageDialog(this, "Subparte restaurada");

            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No has seleccinado ningun subparte", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnRestaurarSubParteActionPerformed

    private void txtCosteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCosteKeyTyped
        
        MetodosComunes.escribirSoloDoubles(txtCoste, evt, lblControlCoste, 2);
        
    }//GEN-LAST:event_txtCosteKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntNuevoParte;
    private javax.swing.JButton btnEditarParte;
    private javax.swing.JButton btnEditarSubParte;
    private javax.swing.JButton btnEliminarParte;
    private javax.swing.JButton btnEliminarSubParte;
    private javax.swing.JButton btnFiltrarPartes;
    private javax.swing.JButton btnFiltrarSubPartes;
    private javax.swing.JButton btnNuevoSubParte;
    private javax.swing.JButton btnRestaurarParte;
    private javax.swing.JButton btnRestaurarSubParte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbFiltroOperacion;
    private javax.swing.JComboBox cmbFiltroTipo;
    private javax.swing.JComboBox cmbMatricula;
    private javax.swing.JComboBox cmbResponsable;
    private com.toedter.calendar.JDateChooser dcDe;
    private com.toedter.calendar.JDateChooser dcHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblControlCoste;
    private javax.swing.JLabel lblControlNumeros;
    private javax.swing.JPanel panelFiltroPartes;
    private javax.swing.JPanel panelFiltroSubPartes;
    private javax.swing.JPanel panelPartes;
    private javax.swing.JPanel panelSubPartes;
    private javax.swing.JRadioButton rdbEliminadoPartes;
    private javax.swing.JRadioButton rdbEliminadoSubPartes;
    private javax.swing.JRadioButton rdbNoEliminadoPartes;
    private javax.swing.JRadioButton rdbNoEliminadoSubPartes;
    private javax.swing.JTable tablaPartes;
    private javax.swing.JTable tablaSubPartes;
    private javax.swing.JTextArea txtAreaDescripcion;
    private javax.swing.JTextField txtCoste;
    // End of variables declaration//GEN-END:variables
    private static frmPartes instancia;
    private ConexionDB conexion;
    private DefaultTableModel modeloPartes;
    private DefaultTableModel modeloSubPartes;
    private boolean terminado;
    //Constantes
    
    private final String CONSULTA_PARTE= "Select p.idparte, p.nro_parte, p.descripcion, p.fecha_parte, p.responsable, p.direccion, v.matricula from s_partes p, s_vehiculos v where v.eliminado=0 and v.idvehiculo=p.idvehiculo ";
    private final String CONSULTA_SUBPARTE="select idsubparte, tipo, descrip as descripcion, coste from s_subpartes p where idparte=";
    private final String ELIMINADO=" and p.eliminado=1";
    private final String NOELIMINADO=" and p.eliminado=0";
    
    public static final String[] TIPOS={"-Elige un tipo-","Personal", "Vehiculo"};
    public static final String CONSULTA_MATRICULA="select idvehiculo, matricula from s_vehiculos where eliminado=0";
    public static final String CONSULTA_COMUNIDADES="select idcomunidad, nombre_com from s_comunidad";
}
