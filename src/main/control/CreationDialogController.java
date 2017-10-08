package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AttributDescribe;
import model.ConnectionDBMS;
import model.UpdateBase;

/**
 * This class corresponds to the controller of the creationDialog
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class CreationDialogController implements ActionListener{
	
	/**
	 * This assignment correspond of the controller of the application
	 */
	private Controller control;
	
	/**
	 * Initialize a CreationDialogController object
	 * @param control A controller to refer
	 */
	public CreationDialogController(Controller control){
		this.control = control;
	}
	
	/**
	 * This method manages all the events that take place in the creation dialog
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// ACTION LIEE A LA CREATION D'UNE NOUVELLE TABLE
		if(e.getSource() == this.control.getFrame().getDialogTableCreation().getCancelButton()){
			this.control.getFrame().getDialogTableCreation().dispose();
		}
		else if(e.getSource() == this.control.getFrame().getDialogTableCreation().getAddBouton()){
			int nbRow = this.control.getFrame().getDialogTableCreation().getTable().getRowCount();
			this.control.getFrame().getDialogTableCreation().getModelTable().addAttribut(new AttributDescribe(false, ("NAME_COLUMN"+(nbRow+1)), "VARCHAR2", "20", false, false));
		}
		else if(e.getSource() == this.control.getFrame().getDialogTableCreation().getDeleteButton()){
			
			int indexSelected = this.control.getFrame().getDialogTableCreation().getTable().getSelectedRow();
			if(indexSelected >= 0)
				this.control.getFrame().getDialogTableCreation().getModelTable().removeAttribut(indexSelected);
		}
		else if(e.getSource() == this.control.getFrame().getDialogTableCreation().getValidButton()){
		
			String nameConnection = this.control.getFrame().getDialogTableCreation().getConnection().getSelectedItem().toString();
			ConnectionDBMS connection = this.control.getConnection(nameConnection);
			
			UpdateBase queryTable = new UpdateBase(connection.getConnection(), this.control.getFrame().getDialogTableCreation().getTextQuery());
			if(!queryTable.isSucceed()){
				this.control.getFrame().getPanelPrincipal().getPanelCentre().getTextArea().setText(queryTable.getException());
			}
			else{
				this.control.getFrame().getPanelPrincipal().getPanelCentre().setTextError("Table crée !");
			}
			this.control.getFrame().getDialogTableCreation().dispose();

		}
		
		// ACTION LIEE A LA CREATION D'UNE NOUVELLE COLONNE
		if(e.getSource() == this.control.getFrame().getDialogColumnCreation().getCancelButton()){
			this.control.getFrame().getDialogColumnCreation().dispose();
		}
		else if(e.getSource() == this.control.getFrame().getDialogColumnCreation().getAddBouton()){
			int nbRow = this.control.getFrame().getDialogColumnCreation().getTable().getRowCount();
			this.control.getFrame().getDialogColumnCreation().getModelTable().addAttribut(new AttributDescribe(false, ("NAME_COLUMN"+(nbRow+1)), "VARCHAR2", "20", false, false));
		}
		else if(e.getSource() == this.control.getFrame().getDialogColumnCreation().getDeleteButton()){
			
			int indexSelected = this.control.getFrame().getDialogColumnCreation().getTable().getSelectedRow();
			if(indexSelected >= 0)
				this.control.getFrame().getDialogColumnCreation().getModelTable().removeAttribut(indexSelected);
		}
		else if(e.getSource() == this.control.getFrame().getDialogColumnCreation().getValidButton()){
		

			String nameConnection = this.control.getFrame().getDialogColumnCreation().getConnection().getSelectedItem().toString();
			ConnectionDBMS connection = this.control.getConnection(nameConnection);
			
			
			for(String query : this.control.getFrame().getDialogColumnCreation().getQuerys()){
				UpdateBase queryTable = new UpdateBase(connection.getConnection(), query);
				if(!queryTable.isSucceed()){
							this.control.getFrame().getPanelPrincipal().getPanelCentre().getTextArea().setText(queryTable.getException());
						}
						else{
							this.control.getFrame().getPanelPrincipal().getPanelCentre().setTextError("1 colonne ajouté");
							this.control.updateJTabbedPan(this.control.getFrame().getDialogColumnCreation().getNameTable().getText(), connection);
						}
			}
			
			this.control.getFrame().getDialogColumnCreation().dispose();

		}
		
		// ACTION LIEE A LA CREATION DE LIGNE(S) SUR UNE TABLE SPECIFIEE
			
		//BOUTON ANNULER
		if(e.getSource() == this.control.getFrame().getDialogLineCreation().getCancelButton()){
			this.control.getFrame().getDialogLineCreation().dispose();
		}
			//BOUTON AJOUTER LIGNE
		else if(e.getSource() == this.control.getFrame().getDialogLineCreation().getAddBouton()){
			 this.control.getFrame().getDialogLineCreation().getModelTable().addRow(new Object[]{});
		} 
			//BOUTON SUPPRIMER LIGNE
		else if(e.getSource() == this.control.getFrame().getDialogLineCreation().getDeleteButton()){
			
			int indexSelected = this.control.getFrame().getDialogLineCreation().getTable().getSelectedRow();
			if(indexSelected >= 0)
				this.control.getFrame().getDialogLineCreation().getModelTable().removeRow(indexSelected);
		}
			//BOUTON VALIDER CREATION DE LIGNE
		else if(e.getSource() == this.control.getFrame().getDialogLineCreation().getValidButton()){
			
			String nameConnection = this.control.getFrame().getDialogLineCreation().getNameConnection().getText();
			String nameTable = this.control.getFrame().getDialogLineCreation().getNameTable().getText();
			
			ConnectionDBMS connection = this.control.getConnection(nameConnection);
			String line = "";
			String name = "";
			
			int rowCount = this.control.getFrame().getDialogLineCreation().getTable().getRowCount();
			int columnCount = this.control.getFrame().getDialogLineCreation().getTable().getColumnCount();
		
			for(int i=0; i < rowCount; i++)
			{
				for(int j=0; j < columnCount; j++){
					name = ("'"+(String) this.control.getFrame().getDialogLineCreation().getTable().getValueAt(i,j)+"'");
					
					if(j == columnCount-1)
						line = line.concat(name);
					else
						line = line.concat(name+",");	
				}
				
				String query = ("INSERT INTO "+nameTable+" VALUES ("+line+")");
				UpdateBase queryTable = new UpdateBase(connection.getConnection(), query);
				if(!queryTable.isSucceed()){
					this.control.getFrame().getPanelPrincipal().getPanelCentre().getTextArea().setText(queryTable.getException());
				}
				line = "";
				
				// MODIFIER LA VUE APRES L'INSERTION DES VUES
				this.control.updateJTabbedPan(nameTable, connection);
				this.control.getFrame().getDialogLineCreation().dispose();
			}
			
			
		}
		
	}
}