package control;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import model.*;

import view.FrameApp;

/**
 * This class is the controller which refer all the other controller of the application
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0  
 */
public class Controller {
	
	/**
	 * The frame of the application
	 */
	private FrameApp frame;
	
	/**
	 * The listener of the menu
	 */
	private ListenerMenu listenerMenu;
	
	/**
	 * The listener of the tree
	 */
	private ListenerTree listenerTree;
	
	/**
	 * The listener of the panel left
	 */
	private ListenerButtonPanelLeft listenerButtonPanelLeft;
	
	/**
	 * The listener of the panel right
	 */
	private ListenerButtonPanelRight listenerButtonPanelRight;
	
	/**
	 * The listener of the connection dialog
	 */
	private ConnectionDialogController connectionDialogController;
	
	/**
	 * The listener of the creation dialog 
	 */
	private CreationDialogController creationDialogController;
	
	/**
	 * The HashMap with a String key and a ConnectionDBMS values
	 */
	public HashMap<String,ConnectionDBMS> connections;
	
	/**
	 * Initialize a Controller object
	 * @param frame The frame of the application
	 */
	public Controller(FrameApp frame){
		
		this.frame = frame;
		
		this.connections = new HashMap<String, ConnectionDBMS>();
		
		this.listenerMenu = new ListenerMenu(this);
		this.listenerTree = new ListenerTree(this);
		this.listenerButtonPanelLeft = new ListenerButtonPanelLeft(this);
		this.listenerButtonPanelRight = new ListenerButtonPanelRight(this);
		this.connectionDialogController = new ConnectionDialogController(this);
		this.creationDialogController = new CreationDialogController(this);
	}
	
	/**
	 * Getter
	 * @return The listener of the tree
	 */
	public ListenerTree getListenerTree(){
		return this.listenerTree;
	}
	
	/**
	 * Getter
	 * @return The listener of the menu
	 */
	public ListenerMenu getListenerMenu(){
		return this.listenerMenu;
	}
	
	/**
	 * 
	 * @return The listener of the panel right
	 */
	public ListenerButtonPanelRight getListenerButtonRight(){
		return this.listenerButtonPanelRight;
	} 
	
	/**
	 * Getter
	 * @return The listener of the panel left
	 */
	public ListenerButtonPanelLeft getListenerButtonLeft(){
		return this.listenerButtonPanelLeft;
	}
	
	/**
	 * Getter
	 * @return The Connection Dialog
	 */
	public ConnectionDialogController getConnectionDialogController(){
		return this.connectionDialogController;
	}
	
	/**
	 * Getter
	 * @return The Creation Dialog
	 */
	public CreationDialogController getCreationDialogController(){
		return this.creationDialogController;
	}
	
	/**
	 * Getter of the frame application
	 * @return The frame of the application
	 */
	public FrameApp getFrame(){
		return this.frame;
	}
	
	/**
	 * Getter
	 * @param name the name of the connection
	 * @return The connection of the DBMS
	 */
	public ConnectionDBMS getConnection(String name){
		return this.connections.get(name);
	}
	
	/**
	 * This method return if the name of the connection is valid or not
	 * @param name The name of the connection
	 * @return true if is valid name connection
	 */
	public boolean isValidNameConnection(String name){
		return (!this.connections.containsKey(name));
	}
	
	/**
	 * This method return if the connection is valid or not
	 * @param connection The connection of the DBMS
	 * @return True if valid or False is invalid
	 */
	public boolean isValidConnection(ConnectionDBMS connection){
		
		boolean ret = true;
		Iterator it = this.connections.entrySet().iterator();
	    while (it.hasNext() && ret == true) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if(pair.getValue().equals(connection)){
	        	ret = false;
	        }
	    }
		return ret;	
	}
	
	/**
	 * Getter 
	 * @return The HashMap Which associates a connection name with a connection
	 */
	public HashMap<String, ConnectionDBMS> getHashMapConnection(){
		return this.connections;
	}
	
	/**
	 * This method put in the HashMap a connection with his name and the connection DBMS
	 * @param name The name of the connection
	 * @param connection The connection of the DBMS
	 */
	public void addConnection(String name, ConnectionDBMS connection) {
		this.connections.put(name, connection);
	}
	
	/**
	 * This method removes the connection specified in parameter
	 * @param name The name of the connection
	 */
	public void deleteConnection(String name){
		this.connections.remove(name);
	}
	
	/**
	 * This method allow to update the tree
	 */
	public void updateTree(){
		
		for (Map.Entry<String,ConnectionDBMS> connection : this.connections.entrySet()){

		    String nameConnection = connection.getKey();
		    ConnectionDBMS connect = connection.getValue();
		    
		    this.getFrame().getPanelPrincipal().getPanelGauche().getMutableTreeNode().removeAllChildren();
		    
		    DefaultMutableTreeNode newConnection = new DefaultMutableTreeNode(nameConnection);
		    DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode("Tables");
		    DefaultMutableTreeNode viewNode = new DefaultMutableTreeNode("Vues");
		    DefaultMutableTreeNode triggerNode = new DefaultMutableTreeNode("Triggers");
			this.getFrame().getPanelPrincipal().getPanelGauche().getMutableTreeNode().add(newConnection);
			this.getFrame().getPanelPrincipal().getPanelGauche().getMutableTreeNode().getLastLeaf().add(tableNode);
			this.getFrame().getPanelPrincipal().getPanelGauche().getMutableTreeNode().getNextNode().add(viewNode);
			this.getFrame().getPanelPrincipal().getPanelGauche().getMutableTreeNode().getNextNode().add(triggerNode);
			
			ListData dataTable = new ListData(connect.getConnection(), Data.TABLE);
			for(String data : dataTable.getListTable()){
				DefaultMutableTreeNode table = new DefaultMutableTreeNode(data);
				tableNode.add(table);
			}
			
			ListData viewTable = new ListData(connect.getConnection(), Data.VIEW);
			for(String data : viewTable.getListTable()){
				DefaultMutableTreeNode view = new DefaultMutableTreeNode(data);
				viewNode.add(view);
			}
			
			ListData triggerTable = new ListData(connect.getConnection(), Data.TRIGGER);
			for(String data : triggerTable.getListTable()){
				DefaultMutableTreeNode trigger = new DefaultMutableTreeNode(data);
				triggerNode.add(trigger);
			}
		    
		}
		
		this.getFrame().getPanelPrincipal().getPanelGauche().getTreeModel().reload();
		this.getFrame().getPanelPrincipal().getPanelGauche().getTree().updateUI();
		int rowCount = this.getFrame().getPanelPrincipal().getPanelGauche().getTree().getRowCount();
		
		expandAllNodes(this.getFrame().getPanelPrincipal().getPanelGauche().getTree(), 0, rowCount);
	}
	
	/**
	 * This method allow to expand all the node when the connection is valid
	 * @param tree The tree
	 * @param startingIndex The number of the index
	 * @param rowCount Number of rows
	 */
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	
	/**
	 * This method allow to update the JTabbedPane when connection is valid
	 * @param nameTable The name of the table
	 * @param connection The connection DMBS 
	 */
	protected void updateJTabbedPan(String nameTable, ConnectionDBMS connection){

		SearchQuery table = new SearchQuery(connection.getConnection(), ("SELECT * FROM "+nameTable));
		int indexSelected = this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
		String tableName = this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(indexSelected);
		this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().remove(indexSelected);
		
		this.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(tableName, table, indexSelected);
		indexSelected = this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
		this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(indexSelected+1);
		
		this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().updateUI();
		this.getFrame().getPanelPrincipal().getPanelCentre().updateTabPan();
		
		DataTable dataTable = new DataTable(nameTable,connection.getConnection());
		this.getFrame().getPanelPrincipal().getPanelCentre().setTableResult(dataTable);
		this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanBot().setSelectedIndex(1);

	}
	
	/**
	 * Use this method to create the template for either creating a table or a column.
	 * @param creation the type of the creation
	 * @return the model
	 */
	public TableDescribe createTableDescribe(Creation creation){
		
		TableDescribe tableDescribe = null;
		
		if(creation == Creation.TABLE){
			tableDescribe = new TableDescribe(this.getFrame().getDialogTableCreation().getNameTable().getText());

			for(int i = 0; i < this.getFrame().getDialogTableCreation().getTable().getRowCount(); i++)
			{
				Boolean pk = (Boolean) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,0);
				String name = (String) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,1);
				String type = (String) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,2);
				String taille = (String) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,3);
				Boolean nul = (Boolean) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,4);
				Boolean uk = (Boolean) this.getFrame().getDialogTableCreation().getTable().getValueAt(i,5);

				tableDescribe.addAtributDescribe(new AttributDescribe(pk, name, type, taille, nul, uk));
			}
			
			
		}
		else if(creation == Creation.COLUMN){
			
			tableDescribe = new TableDescribe("");

			for(int i = 0; i < this.getFrame().getDialogColumnCreation().getTable().getRowCount(); i++)
			{
				Boolean pk = (Boolean) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,0);
				String name = (String) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,1);
				String type = (String) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,2);
				String taille = (String) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,3);
				Boolean nul = (Boolean) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,4);
				Boolean uk = (Boolean) this.getFrame().getDialogColumnCreation().getTable().getValueAt(i,5);

				tableDescribe.addAtributDescribe(new AttributDescribe(pk, name, type, taille, nul, uk));
			}
			
			
		}
		
		return tableDescribe;
		
	}
	
	/**
	 * This method prints the result of a query on the console
	 * @param result the result to print
	 */
	public void printResult(String result){
		if(result != null){
			this.getFrame().getPanelPrincipal().getPanelCentre().setTextError(result);
		}
		
	}
	
	/**
	 * This method is used to print the results 
	 * on the console according to the connection 
	 * and the query specified as a parameter.
	 * @param conn the connection used
	 * @param command The command to execute
	 * @throws SQLException the sqlException
	 */
	public void printResult(Connection conn, String command) throws SQLException{

			SearchQuery query = new SearchQuery(conn, command);
			
			if (query.getResult() != null) {

				this.getFrame().getPanelPrincipal().getPanelCentre().setTableResult(query);
				this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanBot().setSelectedIndex(1);
                
            } else {
            	this.printResult("Ligne(s) exécutée(s) correctement.\n");
            	this.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanBot().setSelectedIndex(0);


            }
		
	}
	
	 /**
     * This method allows you to count the number of nodes under the selected node
     * @param model The model on which to treat
     * @param node The node from which to count
     * @return the number of nodes 
     */
	protected int getNumberOfNodes(TreeModel model, Object node){  
	     int count = 1;
	     int nChildren = model.getChildCount(node);  
	     for (int i = 0; i < nChildren; i++)  
	     {  
	         count += getNumberOfNodes(model, model.getChild(node, i));  
	     }  
	     return count;  
	}
	
}
