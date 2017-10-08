package view;

import java.awt.*;
import java.util.ArrayList; 
import java.util.Map;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import control.Creation;
import model.AttributDescribe;
import model.ConnectionDBMS;

/**
 * This class corresponds the JDialog of the creation's class
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class DialogTableCreation extends JDialog{

	/**
	 * This assgnment corresponds to the  JPanel of the JDialog
	 */
	private JPanel panTop, panDown;
	
	/**
	 * This assignment corresponds to the JLabel of the table's name
	 */
	private JLabel nameTable, nameConnection;
	
	/**
	 * This assignment corresponds to the JTextField, where we can put write the name of the table
	 */
	private JTextField nameTableField;
	
	private JComboBox comboBox;
	
	/**
	 * This assignment corresponds to the JButton of the JDialog
	 */
	private JButton validButton, cancelButton, boutonAdd, boutonDelete;
	
	/**
	 * This assignment corresponds to the GridBagConstraints of the GrisBagLayout
	 */
	private GridBagConstraints c, cDialog;
	
	/**
	 * This assignment corresponds to the JTable where we can enter some information about the table
	 */
	private JTable table;
	
	/**
	 * This assignment corresponds to the application's frame
	 */
	private FrameApp frame;
	
	/**
	 * This assignment corresponds to the ArrayList of attribute which describe a table 
	 */
	private ArrayList<AttributDescribe> attributs = new ArrayList<AttributDescribe>();
	
	/**
	 * This assignment corresponds to the boolean which prevents the repetition of a loop
	 */
	private boolean repetition = false;
	
	/**
	 * This assignment corresponds to the JTabbedPane of the JDialog
	 */
	private JTabbedPane jtp;
	
	/**
	 * This assignment correspond to the model of the JTable
	 */
	private MyTableModel model;
	
	private String textArea;
	private String query;
	
	/**
	 * Initialize a DIalogTableCreation object
	 * @param frame the application frame
	 */
	public DialogTableCreation(FrameApp frame){
		
		this.frame = frame;
		
		this.setTitle("Créer table");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);	
		this.setLayout(new GridBagLayout());
		cDialog = new GridBagConstraints();
		
		initialisation();
			
		this.pack();
		this.setVisible(false);	
	}
	
	/**
	 * This method initialize the JDialog
	 */
	private void initialisation(){
		//JTabbedPane pour création table
		jtp = new JTabbedPane();
		JScrollPane scrollTabbed = new JScrollPane(jtp);
		
		JPanel panel1 = new JPanel(new GridBagLayout());
		jtp.addTab("Création Table", null, panel1,"Does nothing");
		
		//JTabbedPane pour génération de code SQL
		JPanel panelSQL = new JPanel(new BorderLayout());
		JLabel message = new JLabel("<HTML><br>Code SQL de création de table : <HTML>");
		RSyntaxTextArea jta = new RSyntaxTextArea(20,60);
		jta.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
	    jta.setCodeFoldingEnabled(true);
	    jta.setEditable(false);
		panelSQL.add(message, BorderLayout.NORTH);
		panelSQL.add(jta, BorderLayout.CENTER);
		jtp.addTab("Code SQL", null, panelSQL,"Does nothing");
		
		panTop = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		nameTable = new JLabel("Nom : ");
		c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(nameTable, c);
		
		nameTableField = new JTextField("NAME_TABLE");
    	nameTableField.setPreferredSize(new Dimension (150, 20));
    	c.gridx = 1;
        c.insets = new Insets(5, 10, 0, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.anchor = GridBagConstraints.NONE;
    	panTop.add(nameTableField, c);
    	
    	nameConnection = new JLabel("Connexion : ");
		c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
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
    	
    	model = new MyTableModel();

    	model.addTableModelListener(new TableModelListener() {

    		  public void tableChanged(TableModelEvent e) {
    			  
    			  jta.setText(getNewTextArea());
    			  
    			  if(getModelTable().countTrueCheckBox(attributs) > 1 && getModelTable().countTrueCheckBox(attributs) < 3 && !repetition) {
  					JOptionPane.showMessageDialog(frame.getDialogTableCreation(),"Attention une clé primaire multi-attributs a été créé !","Information",JOptionPane.WARNING_MESSAGE);
  					repetition = true;
    			  }
    			  if(getModelTable().countTrueCheckBox(attributs) < 2) {
    				  repetition = false;
    			  }
    			 
    			  int row = e.getFirstRow();
    			  int column = e.getColumn();
    			  TableModel model = (TableModel) e.getSource();
    			  if (column == 0) {
    	                Boolean checked = (Boolean) model.getValueAt(row, column);
    	                if (checked) {
    	                	
    	                	for(AttributDescribe item : attributs) {
	    	    				if(item.getPk() == true) {
	    	    					item.setUk(true);
	    	    					item.setNul(false);
	    	    				}
	    	    			}
    	                	getModelTable().updatedTable();
    	                } 
    	                
    	   
    	          }
    			  else if (column == 4) {
  	                Boolean checked = (Boolean) model.getValueAt(row, column);
  	                if (checked) {
  	                	
  	                	for(AttributDescribe item : attributs) {
	    	    				if(item.getNul() == true) {
	    	    					item.setUk(false);

	    	    				}
	    	    			}
  	                	getModelTable().updatedTable();
  	                } 
    			  }
  	              else if (column == 5) {
    	                Boolean checked = (Boolean) model.getValueAt(row, column);
    	                if (checked) {
    	                	
    	                	for(AttributDescribe item : attributs) {
  	    	    				if(item.getUk() == true) {
  	    	    					item.setNul(false);

  	    	    				}
  	    	    			}
    	                	getModelTable().updatedTable();
    	                } 
  	              }
    		  }
    	});

    	table = new JTable(model);
    	
        //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
       // table.setFillsViewportHeight(true);
 
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
    	panel1.add(panTop, cDialog);
    	
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
    	panel1.add(panDown, cDialog);
    	
    	this.add(jtp);
	}
 
    
	 /**
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     * @param table A JTable to refer
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
    
    /**
     * This methods allow to set type of column of the JTable
     * @param table A Jtable to refer
     * @param typeColumn Represent all the attributes of a column of a JTable
     */
    public void setTypeColumn(JTable table,
                                 TableColumn typeColumn) {
        //Set up the editor for the types cells
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("VARCHAR2");
        comboBox.addItem("NUMBER");
        comboBox.addItem("CHAR");
        comboBox.addItem("DATE");
        typeColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
        //Set up tool tips for the types cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Clique pour choisir le type");
        typeColumn.setCellRenderer(renderer);
    }
    
    /**
     * This class correspond to the model of the JTable
     * @author Alexandre BOUDET and Benoît MARTEL
     * @version 1.0
     */
    public class MyTableModel extends AbstractTableModel {
    	
    	/**
    	 * This assignment corresponds to the name of the column of the JTable
    	 */
        private final String[] columnNames = {"PK",
                                        "Nom",
                                        "Type",
                                        "Taille",
                                        "Nul",
                                        "Unique"};
        
        /**
         * Initialize the model of the JTable
         */
    	public MyTableModel(){
    		super();
    		attributs.add(new AttributDescribe(false, ("NAME_COLUMN"+(getRowCount()+1)), "VARCHAR2", "20", false, false));
    	}
    	
    	/**
    	 * This methods count how many check box are true
    	 * @param arrayList ArrayList of attributes
    	 * @return ret the number of true checkBox
    	 */
    	private int countTrueCheckBox(ArrayList<AttributDescribe> arrayList) {
    		int ret = 0;
    		if(arrayList != null) {
    			for(AttributDescribe item : arrayList) {
    				if(item.getPk() == true) {
    					ret++;
    				}
    			}
    		}
			return ret;
    	}
    	
    	/**
    	 * This method prevents the table model from indicating
    	 * that it has been modified. So he updates it.
    	 */
    	private void updatedTable() {
    		fireTableDataChanged();
    	}
 
    	/**
    	 * Getter column count
    	 * @return the column count
    	 */
        public int getColumnCount() {
            return columnNames.length;
        }
        
        /**
         * Getter row count
         * @return the row count
         */
        public int getRowCount() {
            return attributs.size();
        }
        
        /**
         * This methods allow you to get the column name corresponding at the index
         * @return the name of the column correponding at the index
         */
        public String getColumnName(int col) {
            return columnNames[col];
        }
        
        /**
         * 
         * @param row The row
         * @param col The column
         * @return Attributes
         */
        public Object getValueAt(int row, int col) {
            switch(col){
	            case 0:
	            	return attributs.get(row).getPk();
	            case 1:
	            	return attributs.get(row).getName();
	            case 2:
	            	return attributs.get(row).getType();
	            case 3:
	            	return attributs.get(row).getTaille();
	            case 4:
	            	return attributs.get(row).getNul();
	            case 5:
	            	return attributs.get(row).getUk();
	            default:
	            	return null; //Ne devrait jamais arriver
            }
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            return true;
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
        	switch(col){
	            case 0:
	            	attributs.get(row).setPk((Boolean)value);
	            	break;
	            case 1:
	            	System.out.println(value);
	            	attributs.get(row).setName((String)value);
	            	break;
	            case 2:
	            	attributs.get(row).setType((String)value);
	            	break;
	            case 3:
	            	attributs.get(row).setTaille((String)value);
	            	break;
	            case 4:
	            	attributs.get(row).setNul((Boolean)value);
	            	break;
	            case 5:
	            	attributs.get(row).setUk((Boolean)value);
	            	break;
	            	
        	}
            fireTableCellUpdated(row, col);
        } 
        
        /**
         * This method allow to add an attribute into the table
         * @param attribut Attribute to add
         */
        public void addAttribut(AttributDescribe attribut){
        	attributs.add(attribut);
        	fireTableRowsInserted(attributs.size() -1, attributs.size() -1);
        }
        
        /**
         * This method allow to remove an attribute into the Table
         * @param rowIndex index of the row
         */
        public void removeAttribut(int rowIndex){
        	try{
        		attributs.remove(rowIndex);
            	fireTableRowsInserted(rowIndex, rowIndex);
        	}
        	catch(IndexOutOfBoundsException e){}
        	
        }
    }
    
    /**
     * Getter 
     * @return the nameTable textField
     */
    public JTextField getNameTable(){
    	return this.nameTableField;
    }
    
    /**
     * Getter 
     * @return the valid button
     */
    public JButton getValidButton(){
    	return this.validButton;
    }
    
    /**
     * Getter 
     * @return the cancel button
     */
    public JButton getCancelButton(){
    	return this.cancelButton;
    }
    
    /**
     * Getter 
     * @return the button add
     */
    public JButton getAddBouton(){
    	return this.boutonAdd;
    }
    
    /**
     * Getter
     * @return the delete button
     */
    public JButton getDeleteButton(){
    	return this.boutonDelete;
    }
    
    /**
     * Getter
     * @return the model of the table
     */
    public MyTableModel getModelTable(){
    	return  this.model;
    }
    
    /**
     * Getter
     * @return the table
     */
    public JTable getTable(){
    	return this.table;
    }
    
    /**
     * This method allows you to write the SQL code 
     * corresponding to the table that the user has entered.
     */
    public void setTextQuery(){
    	String nameTable = this.frame.getControl().createTableDescribe(Creation.TABLE).getNameTable();
    	String line = "";
    	String lines = "";
    	String lineArea = "";
    	String pk = "";
    	String pkArea = "";
    	String query = "";
    	String queryArea = "";
    	int countPK = 0;
    	int sizeAttribut = this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().size();
    	for(int i = 0; i < sizeAttribut; i++){
    		line = "";
    		line = line.concat(this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getName()
    				+" "+this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getType());
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getType().equals("DATE"))
    			line = line.concat("("+this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getTaille()+")");
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getNul())
    			line = line.concat(" NOT NULL");	
    		
    		if(!this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getPk())
    			if(this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getUk())
    				line = line.concat(" UNIQUE");
    			
    		if(i < sizeAttribut-1) 
    			line = line.concat(", ");
    		
    		lines = lines.concat(line);
    		lineArea = lineArea.concat("\n\t"+line);	
    	}

    	
    	// COMPTER NOMBRE DE CLE PRIMAIRE
    	for(int i = 0; i < sizeAttribut; i++){
    		if(this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getPk()){
    			countPK++;
    		}
    	
    	}
    	int j = 0;
    	for(int i = 0; i < sizeAttribut; i++){
    		String pkSingle = "";
    		if(this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getPk()){
    			pkSingle = pkSingle.concat(this.frame.getControl().createTableDescribe(Creation.TABLE).getAttributs().get(i).getName());
    			
    			if(j < countPK-1){
    				pkSingle = pkSingle.concat(", ");
    				j++;
    			}
    		}
    		
    		pk = pk.concat(pkSingle);
    		if(!pkSingle.equals(""))
    			pkArea = pkArea.concat("\n\t\t"+pkSingle);	
    	}
	    	if(pk.equals("")){
	    		query = ("CREATE TABLE "+nameTable+"("+lines+")");
	    		queryArea = ("CREATE TABLE "+nameTable+"\n("+lineArea+"\n)");
	    		
	    	}
	    	else{
	    		query = ("CREATE TABLE "+nameTable+"("+lines+", CONSTRAINT "+nameTable+"_PK PRIMARY KEY ( "+pk+" ) ENABLE )");
	    		queryArea = ("CREATE TABLE "+nameTable+"\n("+lineArea+",\n\tCONSTRAINT "+nameTable+"_PK PRIMARY KEY\n\t("+pkArea+"\n\t)\n\tENABLE\n);");
	    	}
	    	
	    	this.textArea = queryArea;
	    	this.query = query;
    }
    
    /**
     * This method allows you to recover the SQL code corresponding to the table in the text box for the textArea. <br>
     * This method uses {@link #setTextQuery()}
     * @return the new text
     */
    public String getNewTextArea(){
    	setTextQuery();
    	return this.textArea;
    }
    
    /**
     * This method allows you to recover the SQL code corresponding to the table in the text box for the query to execute. <br>
     * This method uses {@link #setTextQuery()}
     * @return String 
     */
    public String getTextQuery(){
    	setTextQuery();
    	return this.query;
    }
    
    /**
     * This method allows you to retrieve the combobox to choose 
     * the connection in which you want to create a new class
     * @return the comboBox
     */
    public JComboBox getConnection(){
    	return this.comboBox;
    }
}
