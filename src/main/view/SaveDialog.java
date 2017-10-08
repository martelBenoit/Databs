package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import control.*;

/**
 * Classe qui permet de sauvegarder les élèves crées dans un fichier csv
 * @author Benoît
 * @version 1.0
 */
public class SaveDialog extends JFrame{
	
	/**
	 * Fenêtre de naviguation dans les dossiers et fichiers de l'ordinateur
	 */
	private JFileChooser fileChooser;
	
	/**
	 * Controleur de l'application
	 */
	private Controller control;
	
	/**
	 * Constructeur de la classe SaveDialogTxt
	 * @param control Controleur de l'application
	 */
	public SaveDialog(Controller control){

		this.control = control;
		fileChooser = new JFileChooser();
		
		FileFilter filterSQL = new FileNameExtensionFilter("Fichier SQL", new String[] {"sql"});
		
		fileChooser.setFileFilter(filterSQL);
		fileChooser.addChoosableFileFilter(filterSQL);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		fileChooser.setDialogTitle("Enregistrer le script SQL");
		
	}
	
	/**
	 * Méthode qui permet de réaliser l'enregistrement du ficher sql
	 * @return true si l'enregistrement est un succès
	 */
	public boolean saving(){
		
		boolean success = false;
		int userSelection = fileChooser.showSaveDialog(this);
		int index = 0;
		String fileName = "";
		String areaText = "";

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			
			
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			
			if(!fileName.contains(".sql")){
				fileName = fileName.concat(".sql");
			}
			
			index =  this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();	
			JScrollPane scroll = (JScrollPane) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
			JViewport viewport = scroll.getViewport();
			RSyntaxTextArea area = (RSyntaxTextArea)viewport.getView();
			areaText = area.getText();
				
			this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().removeTabAt(index);
			this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(fileName, areaText, index);
			this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(index);

			try {
				FileWriter file = new FileWriter (fileName);
				BufferedWriter buf = new BufferedWriter(file);
				PrintWriter out = new PrintWriter(buf); 
				out.print(areaText);

				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			success = true;
			

		}

		
		return success;
	}
	
}