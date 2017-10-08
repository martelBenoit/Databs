package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * This class corresponds to the panel at the right of the main window
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class PanelDroite extends JPanel {
	/**
	 * This assignment correspond to the button of the right panel
	 */
	private JButton creerTable, supprimerTable, creerLigne, supprimerLigne, creerColonne, supprimerColonne, lancerScript;
	
	/**
	 * This assignment correspond to the label of the right panel
	 */
	private JLabel rechercherLabel;
	
	/**
	 * This assignment correspond to the JTextField for the research
	 */
	private JTextField rechercher;
	
	/**
	 * This assignment correspond to the five panel of the right panel
	 */
	private JPanel panel1, panel2, panel3, panel4, panel5;	
	
	/**
	 * This assignment corresponds to the Image of the new button
	 */
	private ImageIcon imageNew = null;
	
	/**
	 * This assignment corresponds to the Image of the delete button
	 */
	private ImageIcon imageDelete = null;
	
	
	/**
	 * Initialize a PanelDroite object
	 * @param panPrincipal The PanelPrincipal to refer
	 */
	public PanelDroite(PanelPrincipal panPrincipal){


		
		rechercherLabel = new JLabel();
		rechercherLabel.setOpaque(false);
		rechercherLabel.setToolTipText("Rechercher");
		rechercher = new JTextField();
		rechercher.addKeyListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		rechercher.setPreferredSize(new Dimension(250,40));
		creerTable = new JButton("Créer une table");
		creerTable.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		supprimerTable = new JButton("Supprimer une table");
		supprimerTable.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		creerColonne = new JButton("Créer une colonne");
		creerColonne.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		supprimerColonne = new JButton("Supprimer une colonne");
		supprimerColonne.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		creerLigne = new JButton("Créer une ligne");
		creerLigne.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		supprimerLigne = new JButton("Supprimer une ligne");
		supprimerLigne.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		lancerScript = new JButton("Lancer le script");
		lancerScript.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonRight());
		lancerScript.setOpaque(false);
		lancerScript.setContentAreaFilled(false);
		lancerScript.setBorderPainted(false);


		try{
			imageNew = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/imageNew.png")));
			imageDelete = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/imageDelete.png")));
			rechercherLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/search.png"))));
			lancerScript.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/launch.png"))));
			lancerScript.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/launch_rollover.png"))));
			lancerScript.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelDroit/launch_pressed.png"))));
		}
		catch(Exception err){}
		
		//Panel1
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(rechercherLabel);
		panel1.add(rechercher);
		 
		//Panel2
		panel2 = new JPanel();
		GridLayout gl = new GridLayout(2,2);
		gl.setHgap(10); // 5 pixels d'espace entre les colonnes (H comme Horizontal)
        gl.setVgap(10);
		panel2.setLayout(gl);
		panel2.add(new JLabel(this.imageNew));
		panel2.add(creerTable);
		panel2.add(new JLabel(this.imageDelete));
		panel2.add(supprimerTable);
		//Titled borders for Panel2
		Border blackline1;
		TitledBorder title1;
		blackline1 = BorderFactory.createLineBorder(Color.black);
		title1 = BorderFactory.createTitledBorder(blackline1, "Table");
		title1.setTitleJustification(TitledBorder.LEFT);
		panel2.setBorder(title1);
		
		//Panel3
		panel3 = new JPanel();
		GridLayout gl2 = new GridLayout(2,2);
		gl2.setHgap(10); // 5 pixels d'espace entre les colonnes (H comme Horizontal)
        gl2.setVgap(10);
		panel3.setLayout(gl2);
		panel3.add(new JLabel(this.imageNew));
		panel3.add(creerColonne);
		panel3.add(new JLabel(this.imageDelete));
		panel3.add(supprimerColonne);
		//Titled borders for Panel3
		Border blackline2;
		TitledBorder title2;
		blackline2 = BorderFactory.createLineBorder(Color.black);
		title2 = BorderFactory.createTitledBorder(blackline2, "Colonne");
		title2.setTitleJustification(TitledBorder.LEFT);
		panel3.setBorder(title2);
		
		//Panel4
		panel4 = new JPanel();
		GridLayout gl3 = new GridLayout(2,2);
		gl3.setHgap(10); // 5 pixels d'espace entre les colonnes (H comme Horizontal)
        gl3.setVgap(10);
		panel4.setLayout(gl3);
		panel4.add(new JLabel(this.imageNew));
		panel4.add(creerLigne);
		panel4.add(new JLabel(this.imageDelete));
		panel4.add(supprimerLigne);
		//Titled borders for Panel4
		Border blackline4;
		TitledBorder title4;
		blackline4 = BorderFactory.createLineBorder(Color.black);
		title4 = BorderFactory.createTitledBorder(blackline4, "Ligne");
		title4.setTitleJustification(TitledBorder.LEFT);
		panel4.setBorder(title4);
		
		//Panel5
		panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		panel5.add(lancerScript, BorderLayout.CENTER);
		
		GridLayout gl4 = new GridLayout(5,1);
		this.setLayout(gl4);
		gl4.setHgap(10); // 5 pixels d'espace entre les colonnes (H comme Horizontal)
        gl4.setVgap(10);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
	}
	
	/**
	 * Getter
	 * @return The create table button
	 */
	public JButton getTableCreateButton(){
		return this.creerTable;
	}
	
	/**
	 * Getter
	 * @return The delete table button
	 */
	public JButton getTableDeleteButton(){
		return this.supprimerTable;
	}
	
	/**
	 * Getter
	 * @return The create line button
	 */
	public JButton getLineCreateButton(){
		return this.creerLigne;
	}
	
	/**
	 * Getter
	 * @return The delete line button
	 */
	public JButton getLineDeleteButton(){
		return this.supprimerLigne;
	}
	
	/**
	 * Getter
	 * @return The create column button
	 */
	public JButton getColumnCreateButton(){
		return this.creerColonne;
	}
	
	/**
	 * Getter
	 * @return The delete column button
	 */
	public JButton getColumnDeleteButton(){
		return this.supprimerColonne;
	}
	

	/**
	 * Getter
	 * @return The launcher script button
	 */
	public JButton getLaunchScriptButton(){
		return this.lancerScript;
	}
	
	/**
	 * Getter
	 * @return the textField to research
	 */
	public JTextField getRechercher() {
		return rechercher;
	}

}
