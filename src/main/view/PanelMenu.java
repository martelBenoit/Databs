package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;


/**
 * This class is one of the panels of the frame, it contains the menu of the application
 * @author Alexandre BOUDET and and Benoît MARTEL
 * @version 1.0
 */
public class PanelMenu extends JPanel {
	
	/**
	 * The frame of the application
	 */
	private FrameApp frame;
	
	/**
	 * This assignment corresponds to the menu bar
	 */
	private JMenuBar menuBar;
	
	/**
	 * This assignment corresponds to the menu
	 */
	private JMenu fichier, edition, jeu, aide;
	
	/**
	 * This assignment corresponds to the item menu
	 */
	private JMenuItem charger, enregistrer, enregistrerSous, fermer, toutFermer, imprimer, 
					  nouveau, quitter, retourArriere, retourAvant, copier, coller, couper, lancerJeu, aProposDe,
					  manuel;
	
	/**
	 * This assignment corresponds to the JToolBar
	 */
	private JToolBar jtb;
	
	/**
	 * This assignment corresponds to the button of the ToolBar
	 */
	private JButton nouveauBouton, chargerBouton, enregistrerBouton, enregistrerSousBouton, couperBouton, 
					copierBouton, collerBouton, retourArriereBouton, retourAvantBouton, imprimerBouton;
	
	/**
	 * Constructor of the panel menu
	 * @param frame the frame of the application
	 */
	public PanelMenu(FrameApp frame) {
		
		this.frame = frame;
		
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		fichier = new JMenu("Fichier");
		fichier.setMnemonic(KeyEvent.VK_A);
		fichier.getAccessibleContext().setAccessibleDescription("Menu Fichier");
		menuBar.add(fichier);
		
		//Build the second menu.
		edition = new JMenu("Edition");
		edition.setMnemonic(KeyEvent.VK_A);
		edition.getAccessibleContext().setAccessibleDescription("Menu Edition");
		menuBar.add(edition);
		
		//Build the fourth menu.
		jeu = new JMenu("JeuSQL");
		jeu.setMnemonic(KeyEvent.VK_A);
		jeu.getAccessibleContext().setAccessibleDescription("Menu Jeu");
		menuBar.add(jeu);
		
		//Build the fifth menu.
		aide = new JMenu("Aide");
		aide.setMnemonic(KeyEvent.VK_A);
		aide.getAccessibleContext().setAccessibleDescription("Menu Aide");
		menuBar.add(aide);
		
		//Group of JMenuItems for the menu file
		nouveau = new JMenuItem("Nouveau", KeyEvent.VK_T);
		nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		nouveau.getAccessibleContext().setAccessibleDescription("Nouveau");
		nouveau.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(nouveau);
		
		//Group of JMenuItems for the menu file
		charger = new JMenuItem("Charger", KeyEvent.VK_T);
		charger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		charger.getAccessibleContext().setAccessibleDescription("Charger");
		charger.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(charger);
		
		//Séparateur de menu
		fichier.addSeparator();
		
		//Group of JMenuItems for the menu file
		fermer = new JMenuItem("Fermer",KeyEvent.VK_T);
		fermer.getAccessibleContext().setAccessibleDescription("Fermer");
		fermer.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(fermer);
		
		//Group of JMenuItems for the menu file
		toutFermer = new JMenuItem("Tout fermer",KeyEvent.VK_T);
		toutFermer.getAccessibleContext().setAccessibleDescription("Tout fermer");
		toutFermer.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(toutFermer);
		
		//Séparateur de menu
		fichier.addSeparator();
		
		//Group of JMenuItems for the menu file
		enregistrer = new JMenuItem("Enregistrer",KeyEvent.VK_T);
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		enregistrer.getAccessibleContext().setAccessibleDescription("Enregistrer");
		enregistrer.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(enregistrer);
		
		//Group of JMenuItems for the menu file
		enregistrerSous = new JMenuItem("Enregistrer Sous",KeyEvent.VK_5);
		enregistrerSous.getAccessibleContext().setAccessibleDescription("Enregistrer Sous");
		enregistrerSous.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(enregistrerSous);
		
		//Séparateur de menu
		fichier.addSeparator();
		
		//Group of JMenuItems for the menu file
		imprimer = new JMenuItem("Imprimer",KeyEvent.VK_T);
		imprimer.getAccessibleContext().setAccessibleDescription("Imprimer");
		imprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		imprimer.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(imprimer);
		
		//Séparateur de menu
		fichier.addSeparator();
		
		//Group of JMenuItems for the menu file
		quitter = new JMenuItem("Quitter",KeyEvent.VK_T);
		quitter.getAccessibleContext().setAccessibleDescription("Quitter");
		quitter.addActionListener(this.frame.getControl().getListenerMenu());
		fichier.add(quitter);
		
		//Group of JMenuItems for the menu Edition
		retourArriere = new JMenuItem("Retour arriere",KeyEvent.VK_T);
		retourArriere.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		retourArriere.getAccessibleContext().setAccessibleDescription("Retour arriere");
		edition.add(retourArriere);
		
		//Group of JMenuItems for the menu Edition
		retourAvant = new JMenuItem("Retour avant",KeyEvent.VK_T);
		retourAvant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		retourAvant.getAccessibleContext().setAccessibleDescription("Retour avant");
		edition.add(retourAvant);
		
		//Séparateur de menu
		edition.addSeparator();
		
		//Group of JMenuItems for the menu Edition
		copier = new JMenuItem("Copier",KeyEvent.VK_T);
		copier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copier.getAccessibleContext().setAccessibleDescription("Copier");
		copier.addActionListener(this.frame.getControl().getListenerMenu());
		edition.add(copier);
		
		//Group of JMenuItems for the menu Edition
		coller = new JMenuItem("Coller",KeyEvent.VK_T);
		coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		coller.getAccessibleContext().setAccessibleDescription("Coller");
		coller.addActionListener(this.frame.getControl().getListenerMenu());
		edition.add(coller);
		
		//Group of JMenuItems for the menu Edition
		couper = new JMenuItem("Couper",KeyEvent.VK_T);
		couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		couper.getAccessibleContext().setAccessibleDescription("Couper");
		couper.addActionListener(this.frame.getControl().getListenerMenu());
		edition.add(couper);
		
		//Group of JMenuItems for the menu Jeu
		lancerJeu = new JMenuItem("Lancer JeuSQL",KeyEvent.VK_T);
		lancerJeu.getAccessibleContext().setAccessibleDescription("LancerJeu");
		lancerJeu.addActionListener(this.frame.getControl().getListenerMenu());
		jeu.add(lancerJeu);
		
		//Group of JMenuItems for the menu Help
		aProposDe = new JMenuItem("A propos de...",KeyEvent.VK_T);
		aProposDe.getAccessibleContext().setAccessibleDescription("A propos de...");
		aProposDe.addActionListener(this.frame.getControl().getListenerMenu());
		aide.add(aProposDe);
		
		//Group of JMenuItems for the menu Help
		manuel = new JMenuItem("Manuel d'utilisation",KeyEvent.VK_T);
		manuel.getAccessibleContext().setAccessibleDescription("Manuel");
		manuel.addActionListener(this.frame.getControl().getListenerMenu());
		aide.add(manuel);
		

		//Create the JToolBar
		jtb = new JToolBar();
		
		//Create button of ToolBar
		nouveauBouton = new JButton();
		nouveauBouton.setToolTipText("Nouveau");
		nouveauBouton.addActionListener(this.frame.getControl().getListenerMenu());
		chargerBouton = new JButton();
		chargerBouton.setToolTipText("Charger");
		chargerBouton.addActionListener(this.frame.getControl().getListenerMenu());
		enregistrerBouton = new JButton();
		enregistrerBouton.setToolTipText("Enregistrer");
		enregistrerBouton.addActionListener(this.frame.getControl().getListenerMenu());
		enregistrerSousBouton = new JButton();
		enregistrerSousBouton.setToolTipText("Enregistrer sous");
		enregistrerSousBouton.addActionListener(this.frame.getControl().getListenerMenu());
		imprimerBouton = new JButton();
		imprimerBouton.setToolTipText("Imprimer");
		imprimerBouton.addActionListener(this.frame.getControl().getListenerMenu());
		couperBouton = new JButton();
		couperBouton.addActionListener(this.frame.getControl().getListenerMenu());
		couperBouton.setToolTipText("Couper");
		copierBouton = new JButton();
		copierBouton.addActionListener(this.frame.getControl().getListenerMenu());
		copierBouton.setToolTipText("Copier");
		collerBouton = new JButton();
		collerBouton.addActionListener(this.frame.getControl().getListenerMenu());
		collerBouton.setToolTipText("Coller");
		retourArriereBouton = new JButton();
		retourArriereBouton.addActionListener(this.frame.getControl().getListenerMenu());
		retourArriereBouton.setToolTipText("Retour arrière");
		retourAvantBouton = new JButton();
		retourAvantBouton.addActionListener(this.frame.getControl().getListenerMenu());
		retourAvantBouton.setToolTipText("Retour avant");

		try{
			nouveauBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/Nouveau.png"))));
			chargerBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/charger.png"))));
			enregistrerBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/Enregistrer.png"))));
			enregistrerSousBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/EnregistrerSous.png"))));
			imprimerBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/Imprimer.png"))));
			couperBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/couper.png"))));
			copierBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/copier.png"))));
			collerBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/coller.png"))));
			retourArriereBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/RetourArriere.png"))));
			retourAvantBouton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barreMenu/RetourAvant.png"))));

		}
		catch(Exception err){}
		
		//Add to the JToolBar
		jtb.add(nouveauBouton);
		jtb.add(chargerBouton);
		jtb.add(enregistrerBouton);
		jtb.add(enregistrerSousBouton);
		jtb.addSeparator();
		jtb.add(imprimerBouton);
		jtb.addSeparator();
		jtb.add(couperBouton);
		jtb.add(copierBouton);
		jtb.add(collerBouton);
		jtb.addSeparator();
		jtb.add(retourArriereBouton);
		jtb.add(retourAvantBouton);
	}

	/**
	 * Getter
	 * @return the menuBar
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Getter
	 * @return the jtb
	 */
	public JToolBar getJtb() {
		return jtb;
	}

	/**
	 * Getter
	 * @return the nouveauBouton
	 */
	public JButton getNouveauBouton() {
		return nouveauBouton;
	}

	/**
	 * Getter
	 * @return the chargerBouton
	 */
	public JButton getChargerBouton() {
		return chargerBouton;
	}

	/**
	 * Getter
	 * @return the enregistrerBouton
	 */
	public JButton getEnregistrerBouton() {
		return enregistrerBouton;
	}

	/**
	 * Getter
	 * @return the enregistrerSousBouton
	 */
	public JButton getEnregistrerSousBouton() {
		return enregistrerSousBouton;
	}
	
	/**
	 * Getter
	 * @return the imprimerBouton
	 */
	public JButton getImprimerBouton() {
		return imprimerBouton;
	}

	/**
	 * Getter
	 * @return the couperBouton
	 */
	public JButton getCouperBouton() {
		return couperBouton;
	}

	/**
	 * Getter
	 * @return the copierBouton
	 */
	public JButton getCopierBouton() {
		return copierBouton;
	}

	/**
	 * Getter
	 * @return the collerBouton
	 */
	public JButton getCollerBouton() {
		return collerBouton;
	}

	/**
	 * Getter
	 * @return the avant bouton
	 */
	public JButton getRetourAvantBouton() {
		return retourArriereBouton;
	}

	/**
	 * Getter
	 * @return the arriere bouton
	 */
	public JButton getRetourArriereBouton() {
		return retourAvantBouton;
	}

	/**
	 * Getter
	 * @return the menuBar
	 */
	public JMenuBar getMenuBarFrame() {
		return menuBar;
	}

	/**
	 * Getter
	 * @return the fichier
	 */
	public JMenu getFichier() {
		return fichier;
	}

	/**
	 * Getter
	 * @return the edition
	 */
	public JMenu getEdition() {
		return edition;
	}

	/**
	 *  Getter
	 * @return the jeu
	 */
	public JMenu getJeu() {
		return jeu;
	}

	/**
	 * Getter
	 * @return the aide
	 */
	public JMenu getAide() {
		return aide;
	}

	/**
	 * Getter
	 * @return the charger
	 */
	public JMenuItem getCharger() {
		return charger;
	}

	/**
	 * Getter
	 * @return the enregistrer
	 */
	public JMenuItem getEnregistrer() {
		return enregistrer;
	}

	/**
	 * Getter
	 * @return the enregistrerSous
	 */
	public JMenuItem getEnregistrerSous() {
		return enregistrerSous;
	}

	/**
	 * Getter
	 * @return the fermer
	 */
	public JMenuItem getFermer() {
		return fermer;
	}

	/**
	 * Getter
	 * @return the toutFermer
	 */
	public JMenuItem getToutFermer() {
		return toutFermer;
	}

	/**
	 * Getter
	 * @return the imprimer
	 */
	public JMenuItem getImprimer() {
		return imprimer;
	}

	/**
	 * Getter
	 * @return the nouveau
	 */
	public JMenuItem getNouveau() {
		return nouveau;
	}

	/**
	 * Getter
	 * @return the quitter
	 */
	public JMenuItem getQuitter() {
		return quitter;
	}


	/**
	 * @return the copier
	 */
	public JMenuItem getCopier() {
		return copier;
	}

	/**
	 * Getter
	 * @return the coller
	 */
	public JMenuItem getColler() {
		return coller;
	}

	/**
	 * Getter
	 * @return the couper
	 */
	public JMenuItem getCouper() {
		return couper;
	}

	/**
	 * Getter
	 * @return the retour arriere item
	 */
	public JMenuItem getRetourArriereItem() {
		return retourArriere;
	}

	/**
	 * Getter
	 * @return the retour avant item
	 */
	public JMenuItem getRetourAvantItem() {
		return retourAvant;
	}

	/**
	 * Getter
	 * @return the lancerJeu
	 */
	public JMenuItem getLancerJeu() {
		return lancerJeu;
	}

	/**
	 * Getter
	 * @return the aProposDe
	 */
	public JMenuItem getAProposDe() {
		return aProposDe; 
	}

	/**
	 * Getter
	 * @return the manuel
	 */
	public JMenuItem getManuel() {
		return manuel;
	}
}