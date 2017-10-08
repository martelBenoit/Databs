package control;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.AttributDescribe;
import view.TableDataColumn;

/**
 * Listener of the class TableDataColumn
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class ListenerTableDataColumn implements TableModelListener{
	
	/**
	 * The model of the table 
	 */
	private TableDataColumn modelDataColumn;
	
	/**
	 * The constructor of the class ListenerTableDataColumn
	 * @param modelDataColumn the model of the dataColumn
	 */
	public ListenerTableDataColumn(TableDataColumn modelDataColumn){
		this.modelDataColumn = modelDataColumn;
	}
	
	/**
	 * This method allows you to modify the table creation template whenever the table is modified by the user.
	 */
	public void tableChanged(TableModelEvent e) {
		  
		 
		  int row = e.getFirstRow();
		  int column = e.getColumn();
		  TableModel model = (TableModel) e.getSource();
		  
		  if (column == 0) {
               Boolean checked = (Boolean) model.getValueAt(row, column);
               if (checked) {
               	
               	for(AttributDescribe item : this.modelDataColumn.getAttributs()) {
	    				if(item.getPk() == true) {
	    					item.setUk(true);
	    					item.setNul(false);
	    				}
	    			}
               	this.modelDataColumn.updatedTable();
               } 
               
  
         }
		  else if (column == 4) {
             Boolean checked = (Boolean) model.getValueAt(row, column);
             if (checked) {
             	
             	for(AttributDescribe item : this.modelDataColumn.getAttributs()) {
	    				if(item.getNul() == true) {
	    					item.setUk(false);

	    				}
	    			}
             	this.modelDataColumn.updatedTable();
             } 
		  }
           else if (column == 5) {
               Boolean checked = (Boolean) model.getValueAt(row, column);
               if (checked) {
               	
               	for(AttributDescribe item : this.modelDataColumn.getAttributs()) {
 	    				if(item.getUk() == true) {
 	    					item.setNul(false);

 	    				}
 	    			}
               	this.modelDataColumn.updatedTable();
               } 
           }
	  }
}
