package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ConnectionDBMS;
import model.ListData;

/**
 * This class allows to manage the actions performed on the dialog box 
 * to create a new connection to a database.
 * @author Benoît and Alexandre
 * @version 2.0
 */
public class ConnectionDialogController implements ActionListener{
	
	/**
	 * The main controller of this application.
	 */
	private Controller control;
	
	/**
	 * The name of the user.
	 */
	private String user;
	
	/**
	 * The password of the user.
	 */
	private String password;
	
	/**
	 * Instance of the ListTable class.
	 */
	private ListData dataTable;
	
	/**
	 * Constructor of the class.
	 * @param control the main controller of this application.
	 */
	public ConnectionDialogController(Controller control){
		this.control = control;
	}
	
	/**
	 * This method starts with each click on a button belonging to the dialog box to connect.
	 */
	public void actionPerformed(ActionEvent e) {
		
		// BOUTON ANNULER POUR ANNULER LA CONNEXION A UNE NOUVELLE BASE DE DONNEE
		if(e.getSource() == this.control.getFrame().getDialogConnection().getButtonAnnul()){
			this.control.getFrame().getDialogConnection().setVisible(false);
		}
		
		// BOUTON VALIDER POUR SE CONNECTER A LA BASE DE DONNEE
		else if(e.getSource() == this.control.getFrame().getDialogConnection().getButtonValid()){
			
			this.user = this.control.getFrame().getDialogConnection().getUserField().getText();
			this.password = new String(this.control.getFrame().getDialogConnection().getPasswordField().getPassword());
			ConnectionDBMS connection = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", this.user, this.password);
			
			String nameConnection = this.control.getFrame().getDialogConnection().getNameConnection().getText();
					
			if(this.isCorrectParameter(nameConnection, connection)){
				this.control.addConnection(nameConnection, connection);
				this.control.getFrame().getDialogConnection().dispose();
				
				this.control.updateTree();
			} 

		}
			
	}
	
	/**
	 * This method is used to determine whether the parameters entered by the user are correct or not.
	 * @param nameConnection the name of the connection.
	 * @param connection the connection.
	 * @return true, if the parameters are correct.
	 */
	private boolean isCorrectParameter(String nameConnection, ConnectionDBMS connection){
		
		boolean ret = false;
		
		if(this.control.isValidConnection(connection)){
			if(this.control.isValidNameConnection(nameConnection)){
				if(connection.connectionDBMS() && connection.isValidConnection()){
					ret = true;
				}
				else{
					this.control.getFrame().getDialogConnection().getInfo().setText("Connexion à la base de donnée impossible");
					this.control.getFrame().getDialogConnection().getInfo().setVisible(true);
				}
			}
			else{
				this.control.getFrame().getDialogConnection().getInfo().setText("Une connexion de ce nom existe déjà");
				this.control.getFrame().getDialogConnection().getInfo().setVisible(true);
			}
		}
		else{
			this.control.getFrame().getDialogConnection().getInfo().setText("Une connexion avec cette base de donnée est déja établie");
			this.control.getFrame().getDialogConnection().getInfo().setVisible(true);
		}
		
		return ret;
		
		
	}


}
