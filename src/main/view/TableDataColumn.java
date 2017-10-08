package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import control.Creation;
import model.AttributDescribe;

/**
 * This class extends the AbstractTableModel class. 
 * <br>It allows to create the model for the table of creation of a new table.
 * @author Alexandre BOUDET and and Benoît MARTEL
 * @version 1.0 
 */
public class TableDataColumn extends AbstractTableModel {
	
	/**
	 * The name of columns
	 */
    private final String[] columnNames = {"PK",
                                    "Nom",
                                    "Type",
                                    "Taille",
                                    "Nul",
                                    "Unique"};
    
    /**
     * The list that contains all properties of the attributes of the table
     */
    private ArrayList<AttributDescribe> attributs = new ArrayList<AttributDescribe>();
    
    /**
     * The type of the creation
     */
    private Creation creation;

    /**
     * Constructor of the class TableDataColumn
     * @param creation the type of the creation to perform
     */
	public TableDataColumn(Creation creation){
		super();
		this.creation = creation;
		attributs.add(new AttributDescribe(false, ("NAME_COLUMN"+(getRowCount()+1)), "VARCHAR2", "20", false, false));
	}
	
	/**
	 * This methods allow you to count the number of the check box that have been checked
	 * @return the number of the check box that have been checked
	 */
	public int countTrueCheckBox() {
		int ret = 0;
		if(attributs != null) {
			for(AttributDescribe item : attributs) {
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
	public void updatedTable() {
		fireTableDataChanged();
	}

	/**
	 * This method allow you to count the column in the table
	 * @return the column count
	 */
    public int getColumnCount() {
        return columnNames.length;
    }
    
    /**
     * This method allow you to count the row in the table
     * @return the row count
     */
    public int getRowCount() {
        return attributs.size();
    }
    
    /**
     * This methods allow you to determinate the name of the column with the index
     * @param col the index of the column 
     * @return the name of the column at the specify index
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
    	if(this.creation == Creation.COLUMN){
    		if(col == 0){
    			return false;
    		}
    		else
    			return true;
    	}
    	else{
    		return true;
    	}
        
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
     * This method allow you to add attribut in the new table
     * @param attribut the new attribut to add
     */
    public void addAttribut(AttributDescribe attribut){
    	attributs.add(attribut);
    	fireTableRowsInserted(attributs.size() -1, attributs.size() -1);
    }
    
    /**
     * This method allow you to remove attribut in the new table
     * @param rowIndex the indew to remove
     */
    public void removeAttribut(int rowIndex){
    	try{
    		attributs.remove(rowIndex);
        	fireTableRowsInserted(rowIndex, rowIndex);
    	}
    	catch(IndexOutOfBoundsException e){}
    	
    }
    
    /**
     * This method return the list of the attributs
     * @return the list of the attributs of the new table
     */
    public ArrayList<AttributDescribe> getAttributs(){
    	return this.attributs;
    }
    
}
