package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.2
 */
public class DialogConnection extends JDialog{ 

	/**
	 * This assignment corresponds to the panel top and bot
	 */
	private JPanel haut, bas;
	
	/**
	 * This assignment corresponds to the JLabel
	 */
	private JLabel nameConnection, user, password, info;
	
	/**
	 * This assignment corresponds to JTextField
	 */
	private JTextField nameConnectionField, userField;
	
	/**
	 * This assignment corresponds to JPasswordField
	 */
	private JPasswordField passwordField;
	
	/**
	 * This assignment corresponds to JButton
	 */
	private JButton valid, annul;
	
	/**
	 * This assignment corresponds to GridBagConstraint of the layout
	 */
	private GridBagConstraints c;
	
	/**
	 * This assignment corresponds to the frame of the application
	 */
	private FrameApp frame;

	
	/**
	 * Initialize a DialodConnection object
	 * @param frame The frame of the application
	 */
	public DialogConnection(FrameApp frame){
		
		this.frame = frame;
		
		this.setTitle("Se connecter à une nouvelle base de données");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);	
		this.setLayout(new GridLayout(2,1));
		
		haut = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		this.add(haut);
		
		bas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    	this.add(bas);

		initialisation();
			
		this.pack();
		this.setVisible(false);	
	}
	
	/**
	 * Initialization of the JDialog
	 */
	private void initialisation(){
		
		nameConnection = new JLabel("Nom de la connexion :");
		c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.insets = new Insets(25, 10, 0, 0);
		haut.add(nameConnection, c);
		
		nameConnectionField = new JTextField();
    	nameConnectionField.setPreferredSize(new Dimension (200, 20));
    	c.gridx = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(25, 10, 0, 10);
    	haut.add(nameConnectionField, c);
		
		user = new JLabel("User :");
		c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.insets = new Insets(5, 10, 0, 0);
		haut.add(user, c);

		userField = new JTextField();
		userField.setPreferredSize(new Dimension (200, 20));
    	c.gridx = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 10, 0, 10);
    	haut.add(userField, c);
    	
    	password = new JLabel("Password :");
		c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.insets = new Insets(5, 10, 15, 0);
		haut.add(password, c);
		
		passwordField = new JPasswordField();
    	c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 15, 10);
    	haut.add(passwordField, c);
    	
    	valid = new JButton("Valider");
    	valid.addActionListener(this.frame.getControl().getConnectionDialogController());
    	valid.setDefaultCapable(true);
    	bas.add(valid);
    	
    	this.getRootPane().setDefaultButton(valid);
		valid.requestFocus();

    	annul = new JButton("Annuler");
    	annul.addActionListener(this.frame.getControl().getConnectionDialogController());
    	bas.add(annul);
    	
    	info = new JLabel("");
    	info.setForeground(new Color(255,0,0));
    	info.setVisible(false);
    	bas.add(info);
    		
	}
	
	/**
	 * Getter of JtextField for the name of the connection
	 * @return the JTextField for the name of the connection
	 */
	public JTextField getNameConnection(){
		return this.nameConnectionField;
	}
	
	/**
	 * Getter of JTextField for the name of the user
	 * @return the JTextField for the name of the user
	 */
	public JTextField getUserField(){
		return this.userField;
	}
	
	/**
	 * Getter of JPasswordField
	 * @return the password field
	 */
	public JPasswordField getPasswordField(){
		return this.passwordField;
	}
	
	/**
	 * Getter valid button
	 * @return valid button
	 */
	public JButton getButtonValid(){
		return this.valid;
	}
	
	/**
	 * Getter cancel button
	 * @return the cancel button
	 */
	public JButton getButtonAnnul(){
		return this.annul;
	}
	
	/**
	 * Getter Jlabel info
	 * @return the label info
	 */
	public JLabel getInfo(){
		return this.info;
	}
}
