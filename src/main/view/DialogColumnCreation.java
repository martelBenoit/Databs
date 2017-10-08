package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import control.Creation;
import control.ListenerTableDataColumn;

import model.ConnectionDBMS;

/**
 * 
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.5
 */
public class DialogColumnCreation extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel panTop, panDown;
	private JLabel nameTable, nameConnection;
	private JComboBox comboBox;
	private JButton validButton, cancelButton, boutonAdd, boutonDelete;
	private GridBagConstraints c, cDialog;
	private JTable table;
	private FrameApp frame;
	
	private TableDataColumn model;
	
	private ArrayList<String> querys;

	public DialogColumnCreation(FrameApp frame){
		
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.frame = frame;
		
		this.setTitle("Créer colonne(s)");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);	
		this.setLayout(new GridBagLayout());
		cDialog = new GridBagConstraints();
		
		initialisation();
		
		this.querys = new ArrayList<String>();
		
		this.pack();
		this.setVisible(false);	
	}
	
	private void initialisation(){

		panTop = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		nameTable = new JLabel("Table ");
		c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(nameTable, c);
		
		
    	nameConnection = new JLabel("Connexion : ");
		c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 0, 0);
		panTop.add(nameConnection, c);
		
		comboBox = new JComboBox();
		if(this.frame.getControl().connections.size() > 0){
			for (Map.Entry<String,ConnectionDBMS> connection : this.frame.getControl().connections.entrySet()){
			    comboBox.addItem(connection.getKey());
			} 
		}

		c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 0, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
		panTop.add(comboBox, c);
    	
    	JPanel panEdit = new JPanel(new FlowLayout());
    	boutonAdd = new JButton("Ajouter");
    	boutonAdd.addActionListener(this.frame.getControl().getCreationDialogController());
    	panEdit.add(boutonAdd);
    	
    	boutonDelete = new JButton("Supprimer");
    	boutonDelete.addActionListener(this.frame.getControl().getCreationDialogController());
    	panEdit.add(boutonDelete);
    	
    	c.gridx = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
    	panTop.add(panEdit, c);
    	
    	model = new TableDataColumn(Creation.COLUMN);

    	model.addTableModelListener( new ListenerTableDataColumn(model));

    	table = new JTable(model);
    	
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Set up column sizes.
        initColumnSizes(table);
 
        setTypeColumn(table, table.getColumnModel().getColumn(2));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
		c.gridy = 2;
    	panTop.add(scrollPane, c);
    	
    	cDialog.gridx = 0;
        cDialog.gridy = 1;
        cDialog.fill = GridBagConstraints.HORIZONTAL;
        cDialog.anchor = GridBagConstraints.LINE_START;
        cDialog.weightx = GridBagConstraints.REMAINDER;
    	this.add(panTop, cDialog);
    	
    	panDown = new JPanel(new FlowLayout());
    	
    	validButton = new JButton("Valider");
    	validButton.addActionListener(this.frame.getControl().getCreationDialogController());
    	panDown.add(validButton);
    	cancelButton = new JButton("Annuler");
    	cancelButton.addActionListener(this.frame.getControl().getCreationDialogController());
    	panDown.add(cancelButton);
    	
    	cDialog.gridx = 0;
        cDialog.gridy = 2;
        cDialog.weightx = GridBagConstraints.REMAINDER;
    	this.add(panDown, cDialog);
    	
	}
 
    
    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();
 
        for (int i = 0; i < 6; i++) {
            column = table.getColumnModel().getColumn(i);
 
            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            
            headerWidth = comp.getPreferredSize().width;
            if(i == 1 || i == 2){
            	headerWidth += 40;
            }
            column.setPreferredWidth(Math.max(headerWidth+10, cellWidth+10));
        }
    }
     
    public void setTypeColumn(JTable table,
                                 TableColumn typeColumn) {
        //Set up the editor for the types cells
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("VARCHAR2");
        comboBox.addItem("NUMBER");
        comboBox.addItem("CHAR");
        comboBox.addItem("DATE");
        typeColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Clique pour choisir le type");
        typeColumn.setCellRenderer(renderer);
    }
 
    
    
    public JLabel getNameTable(){
    	return this.nameTable;
    }
    
    public JButton getValidButton(){
    	return this.validButton;
    }
    
    public JButton getCancelButton(){
    	return this.cancelButton;
    }
    
    public JButton getAddBouton(){
    	return this.boutonAdd;
    }
    
    public JButton getDeleteButton(){
    	return this.boutonDelete;
    }
    
    public TableDataColumn getModelTable(){
    	return  this.model;
    }
    
    public JTable getTable(){
    	return this.table;
    }
    
    public void setTextQuery(){
    	
    	String line = "";


    	int sizeAttribut = this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().size();

    	for(int i = 0; i < sizeAttribut; i++){
    		line = "";
    		line = line.concat(this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getName()
    				+" "+this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getType());
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getType().equals("DATE"))
    			line = line.concat("("+this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getTaille()+")");
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getNul())
    			line = line.concat(" NOT NULL");	
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getPk())
    			if(this.frame.getControl().createTableDescribe(Creation.COLUMN).getAttributs().get(i).getUk())
    				line = line.concat(" UNIQUE");
    			
    		this.querys.add("ALTER TABLE "+this.getNameTable().getText()+" ADD ("+line+")");
    	}
    
    }
    

    
    public ArrayList<String> getQuerys(){
    	setTextQuery();
    	return this.querys;
    }
    
    public JComboBox getConnection(){
    	return this.comboBox;
    }
    
    public void setNameTable(String name){
    	this.nameTable.setText(name);
    }
}
