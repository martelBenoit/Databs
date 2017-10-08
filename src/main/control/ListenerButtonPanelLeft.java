package control;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.tree.*;

/**
 * This class corresponds to the listener of the panel left (New connection, Refresh, Disconnection)
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class ListenerButtonPanelLeft implements ActionListener{
	
	/**
	 * This assignment corresponds to the controller of the application 
	 */
	private Controller control;
	
	/**
	 * Initialize a ListenerButtonPanelLeft object
	 * @param control A controller to refer
	 */
	public ListenerButtonPanelLeft(Controller control){
		this.control = control;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelGauche().getButtonNew()){
			
			this.control.getFrame().getDialogConnection().getNameConnection().setText("");
			this.control.getFrame().getDialogConnection().getUserField().setText("");
			this.control.getFrame().getDialogConnection().getPasswordField().setText("");
			this.control.getFrame().getDialogConnection().getInfo().setVisible(false);
			this.control.getFrame().getDialogConnection().setVisible(true);
			
		} 
		else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelGauche().getButtonRafraichir()){
			this.control.updateTree();
		}
		else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelGauche().getButtonSupprimer()){
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getLastSelectedPathComponent();
			
			TreeModel model = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getModel();
	    	TreeNode treeNode = null;
	    	TreeNode connection = null;
	    	
	    	TreePath treePath = null;
	    	Boolean validSelection = false;
	    	
	    	// Si le composant sélectionné dans l'arbre est une feuille
	    	if(this.control.getNumberOfNodes(model, node) == 1){
	    		treeNode = node.getParent();
    			connection = treeNode.getParent();
    			treePath = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getSelectionPath().getParentPath().getParentPath();
    			validSelection = true;
	    	}
	    	// Si le composant sélectionné est un noeud Tables, Vues ou Trigger
	    	else if(node.toString().equals("Tables") || node.toString().equals("Vues") || node.toString().equals("Triggers")){
	    		connection = node.getParent();
	    		treePath = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getSelectionPath().getParentPath();
	    		validSelection = true;
	    	}
	    	// Si le composant sélectionné est la connection
	    	else if(node.getParent() == model.getRoot()){
	    		connection = node;
	    		treePath = this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getSelectionPath();
	    		validSelection = true;
	    	}
	    	// Si le composant sélectionné est la racine
	    	else if(node == model.getRoot()){
	    		// MESSAGE VEUILLEZ SELECTIONNNE UNE CONNECTION VALIDE
	    	}
	    	
	    	JLabel msg = new JLabel("<HTML><center><b>Déconnection de "+connection.toString()+" ?</b></center>"
					+"</HTML>");
					Object[] options = {"Continuer",
	                "Annuler"};
					int n = JOptionPane.showOptionDialog(this.control.getFrame(),msg,
					"Confirmation déconnection",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
					
			if(n == 0){
				if(validSelection){
		    		if(deconnection(treePath, connection.toString())){
						System.out.println("DECO OK");
					}
					else{
						System.out.println("DECO NOT OK");
					}
		    	}
			}
	    	
		}		
	}
	
	/**
	 * This method allow to know if the deconnection is possible or not
	 * @param treePath The path of the tree
	 * @param nameConnection The name of the connection
	 * @return ret Return if the deconnection is valid
	 */
	private boolean deconnection(TreePath treePath, String nameConnection){
		
		boolean ret = false;
		
		this.control.getFrame().getControl().getConnection(nameConnection).disconnectDBMS();
		if(this.control.getFrame().getControl().getConnection(nameConnection).isValidDisconnection()){
			ret = true;
			this.removeNode(treePath);
			this.control.deleteConnection(nameConnection);
			this.removeTab(nameConnection);
		}

		return ret;
	}
	
	/**
	 * This method remove the nodes of the tree
	 * @param treePath The path of the tree
	 */
	private void removeNode(TreePath treePath){
		DefaultTreeModel model = (DefaultTreeModel) this.control.getFrame().getPanelPrincipal().getPanelGauche().getTree().getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        if (node.getParent() != null) {
            model.removeNodeFromParent(node);
        }
	}
	
	/**
	 * This method allow to remove the table of the JTabbedPane
	 * @param nameConnection The name of the connection
	 */
	private void removeTab(String nameConnection){
		int index = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTabCount();
		
		for(int i = 0; i < index; i++){
			if(this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(i).contains(nameConnection)){
				this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().remove(i);
				index--;
				i--;
			}
					
		}	
	}
}