package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * This class corresponds to the JFileChooser Dialog
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class LoadDialog {
	
	/**
	 * This assignment corresponds to the JFileChooser
	 */
	private JFileChooser fileChooser;
	
	/**
	 * This assignment corresponds to the Scanner
	 */
	private Scanner in;
	
	/**
	 * This assignment corresponds to the text area
	 */
	private String textAreaString;
	
	/**
	 * This assignment corresponds to the file name
	 */
	private String fileName;
	
	/**
	 * Initialize a LoadDialog object
	 */
	public LoadDialog(){ // passer en parametre la fenetre de dialog
		
		fileChooser = new JFileChooser();
		FileFilter filterSQL = new FileNameExtensionFilter("Fichier SQL", new String[] {"sql"});
	
		fileChooser.setFileFilter(filterSQL);
		fileChooser.addChoosableFileFilter(filterSQL);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Ouverture de fichier SQL");
		
	}
	
	/**
	 * This method opens a dialog box to open a SQL file to open.
	 * <br>Once the file is selected, the method returns true if the opening has gone well.
	 * @return success True if the opening success
	 */
	public boolean openning(){
		
		boolean sucess = false;
		int userSelection = fileChooser.showOpenDialog(null); // ajouter la fenetre principal
		
		if(userSelection == JFileChooser.APPROVE_OPTION){
			
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			
			if(fileName.contains(".sql")){
				try {
					textAreaString = readFile(fileName);
					sucess = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sucess;
	}
	
	/**
	 * This method allow to read file 
	 * @param fileName The name of the file
	 * @return the stringBuilder 
	 * @throws IOException Exception thrown when file access error
	 */
	private String readFile(String fileName) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (fileName));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close(); 
	    }
	}
	
	/**
	 * Getter for the textAreaString
	 * @return string of the textArea
	 */
	public String getContainsFile(){
		return this.textAreaString;
	}
	
	/**
	 * Getter for the name file
	 * @return the name file
	 */
	public String getNameFile(){
		return this.fileName;	
	}
}