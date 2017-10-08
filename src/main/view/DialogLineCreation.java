package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;


/**
 * 
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class DialogLineCreation extends JDialog{
	
	/**
	 * This assignment corresponds to the frame of the application
	 */
	private FrameApp frame;
	
	/**
	 * This assignment corresponds to JPanel top and bot
	 */
	private JPanel panTop, panDown;
	
	/**
	 * This assignment corresponds to the name of the table
	 */
	private JLabel nameTable;
	
	/**
	 * This assignment corresponds to JLabel
	 */
	private JLabel name, connection, nameConnection;
	
	/**
	 * This assignment corresponds to JButton
	 */
	private JButton validButton, cancelButton, boutonAdd, boutonDelete;
	
	/**
	 * This assignment corresponds to the GridBagConstraint of the layout
	 */
	private GridBagConstraints c, cDialog;
	
	/**
	 * This assignment corresponds to the JTable
	 */
	private JTable table;
	
	/**
	 * This assignment corresponds to the model of the JTable
	 */
	private DefaultTableModel model;
	
	/**
	 * This assignment corresponds to the columns names
	 */
	private String[] columnNames;
	
	/**
	 * This assignment corresponds to the column
	 */
	private Vector column;
	
	/**
	 * Iniatize a DialogLineCreation object
	 * @param frame The frame of the application
	 * @param columnNames The names of the columns
	 */
	public DialogLineCreation(FrameApp frame, String[] columnNames){
		
		this.frame = frame;
		this.columnNames = columnNames;
		
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		
		initColumn();
		
		this.setTitle("Créer ligne(s)");
		this.setResizable(false);
		this.setModal(true);	
		this.setLayout(new GridBagLayout());
		cDialog = new GridBagConstraints();
		
		initialization();
			
		this.pack();
		this.setVisible(false);	
	}
	
	/**
	 * Initialize the column
	 */
	private void initColumn(){
		
		column = new Vector();
	   	for(int i = 0; i < this.columnNames.length; i++){
	   		column.add(columnNames[i]);
	   	}
	}
	
	/**
	 * Initialize the JDialog
	 */
	private void initialization(){ 
		panTop = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		name = new JLabel("Modification de la table : ");
		c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(name, c);
		
		nameTable = new JLabel("");
		c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(nameTable, c);
		
		connection = new JLabel("Connection :");
		c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(connection, c);
		
		nameConnection = new JLabel("");
		c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		panTop.add(nameConnection, c);
    	
    	JPanel panEdit = new JPanel(new FlowLayout());
    	boutonAdd = new JButton("Ajouter");
    	boutonAdd.addActionListener(this.frame.getControl().getCreationDialogController());
    	panEdit.add(boutonAdd);
    	
    	boutonDelete = new JButton("Supprimer");
    	boutonDelete.addActionListener(this.frame.getControl().getCreationDialogController());
    	panEdit.add(boutonDelete);
    	
    	c.gridx = 3;
    	c.insets = new Insets(0, 10, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
    	panTop.add(panEdit, c);
		
		model = new DefaultTableModel(columnNames, 1);
    	table = new JTable(model);
        //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        //table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        c.gridx = 0;
		c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.anchor = GridBagConstraints.LINE_START;
    	panTop.add(scrollPane, c);
    	

    	cDialog.gridx = 0;
        cDialog.gridy = 1;
        cDialog.fill = GridBagConstraints.HORIZONTAL;
        //cDialog.anchor = GridBagConstraints.LINE_START;
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
	
	/**
	 * Getter name of the table
	 * @return the name of the table
	 */
	public JLabel getNameTable(){
		return this.nameTable;
	}
	
	/**
	 * Getter name of the connection
	 * @return the name of the connection
	 */
	public JLabel getNameConnection(){
		return this.nameConnection;
	}
    
	/**
	 * Getter valid button
	 * @return valid button
	 */
    public JButton getValidButton(){
    	return this.validButton;
    }
    
    /**
	 * Getter cancel button
	 * @return cancel button
	 */
    public JButton getCancelButton(){
    	return this.cancelButton;
    }
    
    /**
	 * Getter add button
	 * @return add button
	 */
    public JButton getAddBouton(){
    	return this.boutonAdd;
    }
    
    /**
	 * Getter delete button
	 * @return the delete button
	 */
    public JButton getDeleteButton(){
    	return this.boutonDelete;
    }
    
    /**
	 * Getter of the table model
	 * @return the model of the table
	 */
    public DefaultTableModel getModelTable(){
    	return  this.model;
    }
    
    /**
	 * Getter of the table
	 * @return the table
	 */
    public JTable getTable(){
    	return this.table;
    }
    
}
