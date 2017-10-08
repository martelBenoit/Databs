package control;


import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.tree.*;

import model.DataTable;
import model.SearchQuery;

/**
 * This class corresponds to the listener of the tree, thanks to this tree will be transformed according to the tables, 
 * the views or the triggers added
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class ListenerTree extends MouseAdapter{
	
	/**
	 * This assignment correspond to a declaration of the controller
	 */
	private Controller control;
	
	/**
	 * This assignment corresponds to a boolean which indicates if the user do a double click
	 */
	private boolean doubleClick  = true;
	
	/**
	 * This assignment corresponds to the delay of the double click
	 */
	private int doubleClickDelay = 300;
	
	/**
	 * This assignment corresponds to a declaration of the Timer class
	 */
	private Timer timer; 
	
	/**
	 * Initialize a ListenerTree object
	 * @param control The controller to refer
	 */
	public ListenerTree(Controller control){
		
		this.control = control;
		
		ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {                
                timer.stop();
                if (doubleClick) {
                    try {
                        doubleClickHandler(e);
                    } 
                    catch (ParseException ex) {
                        Logger.getLogger(ex.getMessage());
                    }
                }
            }
        }; 
        timer = new javax.swing.Timer(doubleClickDelay, actionListener);
        timer.setRepeats(false);
	}
	
	public void mouseClicked(MouseEvent e) { 
        if (e.getClickCount() == 1) {
            doubleClick = false;
            timer.start();
        } 
        else {
            doubleClick = true;
        }
    }
    
    /**
     * This method opens a new tab when you click on a component in the tree.
     * @param e The event 
     * @throws ParseException The exception which can be throw
     */
    private void doubleClickHandler(ActionEvent e) throws ParseException { 
    	
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getLastSelectedPathComponent();
    	TreeModel model = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getModel();
    	TreeNode treeNode = null;
    	String nameSelected = "";
    	String typeData = "";
    	String connection = "";
    	
    	// Si le composant sélectionné dans l'arbre est une feuille
    	if(this.control.getNumberOfNodes(model, node) == 1){
    		try{
        		
    			treeNode = node.getParent();
    			typeData = treeNode.toString();
    			nameSelected = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getLastSelectedPathComponent().toString();
    		   	connection = treeNode.getParent().toString();

        	}
    		catch(Exception ex){
    			// UNE ERRREUR A ETE RENCONTRE DANS LA LECTURE 
    		}
    		
    		boolean alreadyOpening = isAlreadyOpening(nameSelected, connection);
    	
			if(!alreadyOpening){

						
			   	if(typeData.equals("Tables")){
				
			   		SearchQuery query = new SearchQuery(this.control.getConnection(connection).getConnection(), ("SELECT * FROM "+nameSelected));
			   	    this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(nameSelected+" ("+connection+")", query);
			   		int indexSelected = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
					this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(indexSelected+1);

			   		
					DataTable dataTable = new DataTable(nameSelected,this.control.getConnection(connection).getConnection());
					this.control.getFrame().getPanelPrincipal().getPanelCentre().setTableResult(dataTable);
					this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanBot().setSelectedIndex(1);
			   		
			   	}
			   	else if(typeData.equals("Vues")){
			   		
			
					String query = ("SELECT TEXT FROM USER_VIEWS WHERE VIEW_NAME = '"+nameSelected.toUpperCase()+"'");
					SearchQuery queryAction = new SearchQuery(this.control.getConnection(connection).getConnection(), query);
					
					String codeView = (queryAction.getValueAt(0, 0).toString());
					this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(nameSelected+" ("+connection+")", codeView);
					int indexSelected = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
					this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(indexSelected+1);
					
					
			   		DataTable dataTable = new DataTable(nameSelected,this.control.getConnection(connection).getConnection());
			   		this.control.getFrame().getPanelPrincipal().getPanelCentre().setTableResult(dataTable);
				    this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanBot().setSelectedIndex(1);
			
			   	}
			   	
			   	else if(typeData.equals("Triggers")){
			   		
			
					String queryStart = ("SELECT DESCRIPTION FROM USER_TRIGGERS WHERE TRIGGER_NAME = '"+nameSelected.toUpperCase()+"'");
					String queryEnd = ("SELECT TRIGGER_BODY FROM USER_TRIGGERS WHERE TRIGGER_NAME = '"+nameSelected.toUpperCase()+"'");
					SearchQuery queryActionStart = new SearchQuery(this.control.getConnection(connection).getConnection(), queryStart);
					SearchQuery queryActionEnd = new SearchQuery(this.control.getConnection(connection).getConnection(), queryEnd);
					
					String codeTrigger = (queryActionStart.getValueAt(0, 0)+"\n"+queryActionEnd.getValueAt(0, 0));
					this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(nameSelected+" ("+connection+")", codeTrigger);
					int indexSelected = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
					this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(indexSelected+1);
			
			   	}
			}
    	}
    }
       	
    /**
     * This method is used to check if the tab is not already open in the application
     * @param nameSelected the nameSelected on the tree
     * @param connection the name of the connection use
     * @return true if the tab is already opening
     */
    private boolean isAlreadyOpening(String nameSelected, String connection){
    	
    	int nbTab = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTabCount();
    	boolean alreadyOpening = false;
    	 int i = 0;
    	 while(i < nbTab && !alreadyOpening){
    		 try{
    			 if((this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(i).contains(nameSelected)) &&
        				( this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(i).contains(connection))){
        			 alreadyOpening = true;
    			 }
    		 }
    		 catch(NullPointerException nullPointer){};
        		
    	 
    		 i++;
    	 }
    	return alreadyOpening;
    }
}