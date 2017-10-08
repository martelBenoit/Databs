package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import model.ConnectionDBMS;
import model.ScriptRunner;
import model.SearchQuery;
import model.UpdateBase;

/**
 * This class corresponds to the different listener of the right panel (Create table, column, row...)
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class ListenerButtonPanelRight implements ActionListener, KeyListener{
	
	/**
	 * This assignment corresponds to the controller
	 */
	private Controller control;
	
	/**
	 * This assignment corresponds to the JTable
	 */
	private JTable tableSelected;
	
	/**
	 * This assignment corresponds to the JTextArea
	 */
	private JTextArea area;
	
	/**
	 * This assignment corresponds to the color of the Highlighting
	 */
	private final Highlighter.HighlightPainter hPainter  = new HPainter(Color.PINK);
	
	/**
	 * Initialize a ListenerButtonPanelRight object
	 * @param control A controller to refer
	 */
	public ListenerButtonPanelRight(Controller control){
		
		this.control = control;
		this.tableSelected = null;
	}

	public void actionPerformed(ActionEvent e) {
		
			// BOUTON POUR CREER UNE NOUVELLE TABLE
			if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getTableCreateButton()){
				
				if(!this.control.getHashMapConnection().isEmpty()){
					this.control.getFrame().openingDialogCreationTable();
					this.control.getFrame().getDialogTableCreation().setVisible(true);
				}
				else{
					this.control.getFrame().getPanelPrincipal().getPanelCentre().setTextError("Aucunne connexion établie!");
				}
				
			}
	
			// BOUTON POUR SUPPRIMER LA TABLE SELECTIONNE
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getTableDeleteButton()){
				
				if(isValidSelection()){
					
					String[] references = getReferencesTab();
					String nameTable = references[0];
					String nameConnection = references[1];
					
					JLabel msg = new JLabel("<HTML><center><b>Vous êtes sur le point de supprimer une table</b></center>"
							+"<br><b>Connexions : </b>"+nameConnection+"<br><b>Table : </b>"+nameTable
							+"</HTML>");
							Object[] options = {"Confirmer",
			                "Annuler"};
							int n = JOptionPane.showOptionDialog(this.control.getFrame(),msg,
							"Confirmation suppression",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,     //do not use a custom Icon
							options,  //the titles of buttons
							options[0]); //default button title
							
					if(n == 0){
						ConnectionDBMS connection = this.control.getConnection(nameConnection);
	
						String query = ("DROP TABLE "+nameTable);
						UpdateBase queryTable = new UpdateBase(connection.getConnection(), query);
						this.control.updateTree();
						
						if(queryTable.isSucceed()){
							JOptionPane.showMessageDialog(this.control.getFrame(),
								    "La table a été supprimé");
							this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().remove(this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex());
						}
						else{
							JOptionPane.showMessageDialog(this.control.getFrame(),
								    "La table n'a pas été supprimé");
						}
	
					}
					
				}
				else{
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Vous devez sélectionner une table a supprimer");
				}
			}
			
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getColumnCreateButton()){
				
				String[] references = getReferencesTab();
				String nameTable = references[0];
				
				if(isValidSelection()){

						
				this.control.getFrame().openingDialogCreationColumn();
				this.control.getFrame().getDialogColumnCreation().setNameTable(nameTable);
				this.control.getFrame().getDialogColumnCreation().setVisible(true);
	
			
				}
				else{
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Vous devez sélectionner une table dans laquelle il faut ajouter une colonne");
					
				}
			}
			
			
			// BOUTON POUR SUPPRIMER UNE COLONNE DE LA TABLE SELECTIONNE
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getColumnDeleteButton()){
				if(isValidSelection()){
					String[] references = getReferencesTab();
					String nameTable = references[0];
					String nameConnection = references[1];
					
					int indexTab = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
					researchTable((JComponent) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getComponentAt(indexTab));
					TableModel tableModel = this.tableSelected.getModel();
					int indexColumn = this.tableSelected.getColumnCount();
					
					String[] nameColumns = new String[indexColumn];
					String[] data = new String[indexColumn];
					for(int i = 0; i < data.length; i++){
	
						nameColumns[i] = tableModel.getColumnName(i);
					}
	
				    String input = (String) JOptionPane.showInputDialog(this.control.getFrame(), "Sélectionner la colonne à supprimer",
				        "Attention", JOptionPane.WARNING_MESSAGE, null,                                                         
				        nameColumns, // Array of choices
				        nameColumns[0]); // Initial choice
				    
				    if(input != null){
				    	ConnectionDBMS connection = this.control.getConnection(nameConnection);
				    	String query = ("ALTER TABLE "+ nameTable +" DROP COLUMN "+input);
						UpdateBase queryTable = new UpdateBase(connection.getConnection(), query);
						
						if(queryTable.isSucceed()){
							JOptionPane.showMessageDialog(this.control.getFrame(),
								    "La colonne a été supprimé");
							this.control.updateJTabbedPan(nameTable, connection);
						}
						else{
							JOptionPane.showMessageDialog(this.control.getFrame(),
								    "La colonne n'a pas été supprimé");
						}
				    }
				    
				    
					
					
				}
				else{
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Vous devez sélectionner une table dans laquelle il faut supprimer une colonne");
				}
				
			}
			
			// BOUTON POUR CREER UNE NOUVELLE LIGNE DANS LA TABLE SELECTIONNE
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getLineCreateButton()){
				
				if(isValidSelection()){
					
					String[] references = getReferencesTab();
					String nameTable = references[0];
					String nameConnection = references[1];
					
					ConnectionDBMS connection = this.control.getConnection(nameConnection);
					SearchQuery table = new SearchQuery(connection.getConnection(), ("SELECT * FROM "+nameTable));
					int nbColumn = table.getColumnCount();
					String[] columns = new String[nbColumn];
					
					for(int i = 0; i < nbColumn; i++){
						columns[i] = table.getColumnName(i);
					}
					
					this.control.getFrame().openingDialogCreationLine(columns);
					this.control.getFrame().getDialogLineCreation().getNameTable().setText(nameTable);
					this.control.getFrame().getDialogLineCreation().getNameConnection().setText(nameConnection);
					this.control.getFrame().getDialogLineCreation().setVisible(true);
				}
				else{
					this.control.getFrame().getPanelEtat().getEtatLabel().setText("Vous devez sélectionner une table dans laquelle il faut ajouter une ligne");
					
				}
				
			}
			
			
			// BOUTON POUR SUPPRIMER UNE LIGNE DANS UNE TABLE SELECTIONNE
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getLineDeleteButton()){
				
				 if(isValidSelection()){
					
					String[] references = getReferencesTab();
					String nameTable = references[0];
					String nameConnection = references[1];
					
					int indexTab = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
					researchTable((JComponent) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getComponentAt(indexTab));
					
					
					int indexRow = this.tableSelected.getSelectedRow();
					TableModel tableModel = this.tableSelected.getModel();
					int indexColumn = this.tableSelected.getColumnCount();
					
					String[] nameColumns = new String[indexColumn];
					String[] data = new String[indexColumn];
					try{
						for(int i = 0; i < data.length; i++){
							data[i] = (String) tableModel.getValueAt(indexRow, i);
							nameColumns[i] = tableModel.getColumnName(i);
						}
						
						JLabel msg = new JLabel("<HTML><center><b>Vous êtes sur le point de supprimer un tuple</b></center>"
								+"<br><b>Connexions : </b>"+nameConnection+"<br><b>Table : </b>"+nameTable+"<br><b>Clé primaire : </b>"+data[0]
								+"</HTML>");
								Object[] options = {"Confirmer",
				                "Annuler"};
								int n = JOptionPane.showOptionDialog(this.control.getFrame(),msg,
								"Confirmation suppression",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[0]); //default button title
								
								
						if(n == 0){
							ConnectionDBMS connection = this.control.getConnection(nameConnection);
							String line = "";
							
							for(int i = 0; i < indexColumn; i++){
								line = line.concat(nameColumns[i]+" = '"+data[i]+"'");
								if(i != indexColumn-1){
									line = line.concat(" AND ");
								}
							}
		
							String query = ("DELETE FROM "+nameTable+"WHERE "+line);
							UpdateBase queryTable = new UpdateBase(connection.getConnection(), query);
							System.out.println(query);
							
							if(queryTable.isSucceed()){
								JOptionPane.showMessageDialog(this.control.getFrame(),
									    "La ligne a été supprimé");
								this.control.updateJTabbedPan(nameTable, connection);
							}
							else{
								JOptionPane.showMessageDialog(this.control.getFrame(),
									    "La ligne n'a pas été supprimé");
							}
		
						}
						
						
						
					}
					catch(ArrayIndexOutOfBoundsException indexOut){
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Aucunne ligne sélectionnée");
					}
					
					
					
				 }
				 else{
						this.control.getFrame().getPanelEtat().getEtatLabel().setText("Vous devez sélectionner une table dans laquelle il faut supprimer une ligne");
						
				 }
			}
			
			// BOUTON POUR LANCER LE SCRIPT
			else if(e.getSource() == this.control.getFrame().getPanelPrincipal().getPanelDroite().getLaunchScriptButton()){
				
				if(this.control.connections.size() > 0){
					
					try{
						int indexTab = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
						
						JScrollPane scroll = (JScrollPane) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
						JViewport viewport = scroll.getViewport();
						RSyntaxTextArea area = (RSyntaxTextArea)viewport.getView();
						
						JComboBox comboBox = new JComboBox();
						for (Map.Entry<String,ConnectionDBMS> connection : this.control.connections.entrySet()){
						    comboBox.addItem(connection.getKey());
						} 
								
				        
						JOptionPane.showMessageDialog(this.control.getFrame(), comboBox, "Sélectionnez la connection", JOptionPane.QUESTION_MESSAGE);
						
						String nameConnection = (String) comboBox.getSelectedItem();
						ConnectionDBMS co = this.control.getConnection(nameConnection);
						co.connectionDBMS();
						
						ScriptRunner runner = new ScriptRunner(this.control, co.getConnection(), false, true);;
			
						File temp = null;
						try {
							try { 
								temp = File.createTempFile("ScriptRunner", ".databs");
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							FileWriter file = null;
							try {
								file = new FileWriter(temp);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							BufferedWriter buf = new BufferedWriter(file);
							PrintWriter out = new PrintWriter(buf); 
							
							out.print(area.getText());
							
							out.flush();
							out.close();
							
							try {
								runner.runScript(new BufferedReader(new FileReader(temp)));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						 
						} finally {
						    temp.delete();
						}
					}catch(Exception ex){
						
					}
				
						
				}
				
			}
		
		
	}
	
	/**
	 * Methods which allows to highlight a word that we want research 
	 * @param tcomp JTextComponent where we want add Highlight
	 * @param word The word to HighLight
	 */
	public void addHighlight(JTextComponent tcomp, final String word) {
		// Supprime les anciens
		removeHighlights(tcomp);

		try {
		  final Highlighter h = tcomp.getHighlighter();
		  final Document doc = tcomp.getDocument();
		  final String fullText = doc.getText(0, doc.getLength());
		  int pos = 0;

		  // Recherche du "word"
		  while ((pos = fullText.indexOf(word, pos)) >= 0) {
			// Ajout du nouveau painter
			h.addHighlight(pos, pos + word.length(), hPainter);
			// On avance pour la suite
			pos += word.length();
		  }
		} catch (final BadLocationException e) {
		  e.printStackTrace();
		}

	  }
  

	/**
	  * Method which allow to remove the Highlighted Text
	  * @param textComp The JTextComponent where take place the Highlighting
	  */
	 public void removeHighlights(final JTextComponent textComp) {
		final Highlighter her = textComp.getHighlighter();
		final Highlighter.Highlight[] h = her.getHighlights();
		for (int i = 0; i < h.length; ++i) {
		  // Si c'est le notre on delete
		  if (HPainter.class.isInstance(h[i].getPainter()))
			her.removeHighlight(h[i]);
		}
	  }
	 

	  // Le passage par une classe privée permet d'isoler nos HighlightPainter au
	  // moment du remove
	  
	 /**
	  * This class corresponds to the initialization of the Highlighting color
	  * @author Alexandre BOUDET and Benoît MARTEL
	  * @version 1.0
	  */
	  class HPainter extends DefaultHighlighter.DefaultHighlightPainter {
		public HPainter(final Color color) {
		  super(color);
		}
	  }
	
	  /**
		 * This method return a boolean if the selection is valid
		 * @return isValid The boolean (true = valid, false = invalid)
		 */  
	  private boolean isValidSelection(){
		boolean isValid = false;
		
		String nameTable = getNameTabSelected();
		
		JScrollPane scroll = (JScrollPane) this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
		JViewport viewport = scroll.getViewport();
		
		if(viewport.getView() instanceof RSyntaxTextArea){
			isValid = false;
		}
		else{
			if(nameTable.contains("(") && nameTable.contains(")"))
				isValid = true;
		}
		
		
		return isValid;
	}
	
	  /**
		 * This method return the name of the tab selected 
		 * @return nameTable The name of the tab
		 */
	  private String getNameTabSelected(){
		
		String nameTable = "";
		
		try{
			int indexSelected = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedIndex();
			nameTable = this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getTitleAt(indexSelected);
			
		}
		catch(Exception e){}
		
		return nameTable;
	  }
	
	  /**
		 * This method give the tab referenced
		 * @return references 
		 */
	  private String[] getReferencesTab(){
		
		String[] references = new String[2];
		
		String nameTabSelected = getNameTabSelected();
		String[] splitArray = null;
		splitArray = nameTabSelected.split("\\(");
		references[0] = splitArray[0];
		
		String nameConnection = splitArray[1];
		splitArray = nameConnection.split("\\)");
		references[1] = splitArray[0];
		
		return references;
	  }
	
	  /**
		 * This method allow to search table by referring a component
		 * @param component The component in which to look for a table
		 */
	  private void researchTable(JComponent component){
    	
        for (int i=0;i<component.getComponentCount();i++) {
            if (component.getComponent(i) instanceof JTable) {
            	
                setTableSelected((JTable)component.getComponent(i));
            }
            if (component.getComponent(i) instanceof JPanel ||
                component.getComponent(i) instanceof JTabbedPane ||
                component.getComponent(i) instanceof Box ||
                component.getComponent(i) instanceof JViewport ||
                component.getComponent(i) instanceof JScrollPane) {
               
            	researchTable((JComponent)component.getComponent(i));
            }
        }
	  }
    
	  /**
	     * This method is used to define which table is selected
	     * @param table The table that is selected
	     */
	  private void setTableSelected(JTable table){
    	this.tableSelected = table;
	  }
    

	  public void keyPressed(KeyEvent e) {}

	  /**
	   * This method allows you to highlight the words that are contained in the search bar
	   */
	  public void keyReleased(KeyEvent e) {
		
		try{
			JScrollPane scroll = (JScrollPane)this.control.getFrame().getPanelPrincipal().getPanelCentre().getJTabbedPanTop().getSelectedComponent();
			JViewport viewport = scroll.getViewport();
			area = (JTextArea)viewport.getView();
			

			if(!this.control.getFrame().getPanelPrincipal().getPanelDroite().getRechercher().getText().equals("")){
				addHighlight(this.area, this.control.getFrame().getPanelPrincipal().getPanelDroite().getRechercher().getText());	
			}
			else{
				removeHighlights(area);
			}
		}
		catch(Exception ex){
			this.control.getFrame().getPanelEtat().getEtatLabel().setText("Aucun mot à rechercher");
		}
	  }


	  public void keyTyped(KeyEvent e) {}
}
