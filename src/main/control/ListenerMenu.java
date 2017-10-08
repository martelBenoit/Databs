package control;

import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextAreaEditorKit.*;

import view.*;

/**
 * This class corresponds to the different listener of the menu bar as well as the JToolBar
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class ListenerMenu implements ActionListener{
	
	/**
	 * This assignment corresponds to the controller
	 */
	private Controller control;
	
	/**
	 * This assignment corresponds to the index of the new page
	 */
	private int index = 1;
	
	/**
	 * This assignment corresponds to the RSyntaxtextArea
	 */
	private RSyntaxTextArea area;
	
	/**
	 * Initialize a ListenerMenu object
	 * @param control A controller to refer
	 */
	public ListenerMenu(Controller control){
		this.control = control;
	}
	
	/**
	 * This method handles all events that occur in the menu bar.
	 */
	public void actionPerformed(ActionEvent e) {
		
		// MENU NOUVEAU
		if(e.getSource() == this.control.getFrame().getPanelMenu().getNouveau() || e.getSource() == this.control.getFrame().getPanelMenu().getNouveauBouton()) {
			this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan("Nouveau "+this.index);
			this.index++;
			int index = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTabCount();
			this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(index-1);
		}
				
		// MENU CHARGER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getCharger() || e.getSource() == this.control.getFrame().getPanelMenu().getChargerBouton()){
			LoadDialog loadDialog = new LoadDialog();
			if(loadDialog.openning()){ 
				// OUVERTURE REUSSI
				String contains = loadDialog.getContainsFile();
				String nameFile = loadDialog.getNameFile();
				this.control.getFrame().getPanelPrincipal().getPanelCentre().addTabPan(nameFile, contains);
				int index = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTabCount();
				this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().setSelectedIndex(index-1);
				this.control.getFrame().getPanelEtat().getEtatLabel().setText("Ouverture du fichier réussi");
			}
			else{
				this.control.getFrame().getPanelEtat().getEtatLabel().setText("Echec de l'ouverture du fichier");
			}
		}

		// MENU ENREGISTRER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getEnregistrer() || e.getSource() == this.control.getFrame().getPanelMenu().getEnregistrerBouton()){

			boolean fileSQLCorrect = true;
			RSyntaxTextArea area = null;
			try{
				JScrollPane scroll = (JScrollPane) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
				JViewport viewport = scroll.getViewport();
				area = (RSyntaxTextArea)viewport.getView();

				if(area == null){
					fileSQLCorrect = false;
				}

			}

			catch(Exception exSQL){}

			
			if(fileSQLCorrect){
				index =  this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
				String name = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(index);
				if(name.contains("Nouveau")){
					SaveDialog saveDialog = new SaveDialog(this.control);
					if(saveDialog.saving()){ 
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Enregistrement du fichier réussi");
					}
					else{
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Echec de l'enregistrement");
					}
				}
				else{
					try {
						FileWriter file = new FileWriter (name);
						BufferedWriter buf = new BufferedWriter(file);
						PrintWriter out = new PrintWriter(buf); 
						out.print(area.getText());

						out.close();
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Enregistrement du fichier réussi");
					}
					catch (IOException ioEx)
					{
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Echec de l'enregistrement");
					}
				}
				

			}
			else{
				this.control.getFrame().getPanelEtat().getEtatLabel().setText("Positionnez vous sur le fichier SQL à enregistrer");
			}
		}

		// MENU ENREGISTRER SOUS
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getEnregistrerSous() || e.getSource() == this.control.getFrame().getPanelMenu().getEnregistrerSousBouton()){
			
			boolean fileSQLCorrect = true;
			try{
				JScrollPane scroll = (JScrollPane) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
				JViewport viewport = scroll.getViewport();
				RSyntaxTextArea area = (RSyntaxTextArea)viewport.getView();

				if(area == null){
					fileSQLCorrect = false;
				}

			}

			catch(Exception exSQL){}

			
			if(fileSQLCorrect){
				SaveDialog saveDialog = new SaveDialog(this.control);
				if(saveDialog.saving()){ 
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Enregistrement du fichier réussi");
				}
				else{
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Echec de l'enregistrement");
				}

			}
			else{
				this.control.getFrame().getPanelEtat().getEtatLabel().setText("Positionnez vous sur le fichier SQL à enregistrer");
			}

		}
		
		// MENU FERMER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getFermer()){
			this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().remove(this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent());
		}
		
		// MENU TOUT FERMER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getToutFermer()){
			this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().removeAll();
		}
		
		//MENU QUITTER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getQuitter()){
			System.exit(0);
		}
	
		//MENU IMPRIMER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getImprimer() || e.getSource() == this.control.getFrame().getPanelMenu().getImprimerBouton()){
			PrinterJob job = PrinterJob.getPrinterJob();
			/* On donne le contenu à imprimer au job */
			
			JScrollPane scroll = (JScrollPane)this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
			JViewport viewport = scroll.getViewport();
			area = (RSyntaxTextArea)viewport.getView();
			job.setPrintable(area);
			/* Affichage de la boite de dialogue d'impression */
			boolean doPrint = job.printDialog();
			if(doPrint) {
				try {
					/* Lancement de l'impression */
					job.print();
				}
				catch (PrinterException e1) {
					e1.printStackTrace();
				}
			} 
		}

		//MENU COUPER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getCouper() || e.getSource() == this.control.getFrame().getPanelMenu().getCouperBouton()) {
			
			if(getText() != null){
				 CutAction ca = new CutAction();
				ca.actionPerformedImpl(e, getText());
			}
		}

		//MENU COLLER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getColler() || e.getSource() == this.control.getFrame().getPanelMenu().getCollerBouton()) {
			
			if(getText() != null){
				PasteAction pa = new PasteAction();
				pa.actionPerformedImpl(e, getText());
			}
		}
		
		//MENU COPIER
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getCopier() || e.getSource() == this.control.getFrame().getPanelMenu().getCopierBouton()) {

			if(getText() != null){
				CopyAction ca = new CopyAction();
				ca.actionPerformedImpl(e, getText());
			}
		}

		// MENU RETOUR ARRIERE
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getRetourArriereBouton() || e.getSource() == this.control.getFrame().getPanelMenu().getRetourArriereItem()) {

			if(getText() != null){
				RedoAction ua = new RedoAction();
				ua.actionPerformedImpl(e, getText());
			}

		}

		// MENU RETOUR AVANT
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getRetourAvantBouton() || e.getSource() == this.control.getFrame().getPanelMenu().getRetourAvantItem()) {

			if(getText() != null){
				UndoAction ua = new UndoAction();
				ua.actionPerformedImpl(e, getText());
			}

		}

		// MENU JEU SQL
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getLancerJeu()){
			new FrameJeuSQL(this.control.getFrame());

		}

		//MENU A PROPOS
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getAProposDe()){
			new FrameAPropos(this.control.getFrame());
		}

		//MENU MANUEL
		else if(e.getSource() == this.control.getFrame().getPanelMenu().getManuel()){
			new FrameManuel(this.control.getFrame());
		}


	}

	/**
	  * This method allow you to getTextArea selected in the frame
	  * @return the textArea
	  */
	private RSyntaxTextArea getText(){

		JScrollPane scroll = (JScrollPane)this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
		JViewport viewport = scroll.getViewport();
		area = (RSyntaxTextArea)viewport.getView();

		return area;
	}
}
