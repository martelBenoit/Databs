package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.imageio.*;

import control.Controller;
import view.PanelEtat;
import view.PanelMenu;
import view.PanelPrincipal;

/**
 * This class lets you group the different boards into the other classes of the view model in order to create a single frame, 
 * which will be that of the application
 * @author Alexandre BOUDET et Benoît MARTEL
 * @version 1.0
 */
public class FrameApp extends JFrame {
	
	/**
	 * This assignment corresponds to the controller of the application
	 */
	private Controller control;
	
	/**
	 * A assignment for PanelMenu
	 */
	private PanelMenu pm;
	
	/**
	 * A assignment for PanelEtat
	 */
	private PanelEtat pe;
	
	/**
	 * A assignment for PanelPrincipal
	 */
	private PanelPrincipal pp;
	
	/**
	 * This assignment corresponds to the connection dialog
	 */
	private DialogConnection dialogConnection;
	
	/**
	 * This assignment corresponds to the creation dialog 
	 */
	private DialogTableCreation dialogCreationTable;
	
	/**
	 * This assignment corresponds to the creation column dialog
	 */
	private DialogColumnCreation dialogColumnCreation;
	
	/**
	 * This assignment corresponds to the creation line dialog
	 */
	private DialogLineCreation dialogLineCreation;
	

	
	/**
	 * The constructor of FrameApp
	 */
	public FrameApp() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Editeur de Base De Données (Databs)");
		try
   		{
   			this. setIconImage(ImageIO.read(getClass().getResourceAsStream("/images/icon.png")));
   		}
		catch(Exception err){}

		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		
		this.control = new Controller(this);
		this.dialogConnection = new DialogConnection(this);
		
		initializeFrame();
		initializeDialog();
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * This method is used to initialize the frame by calling the other panels
	 * as well as entering the various parameters on the frame
	 */
	private void initializeFrame() {
		pm = new PanelMenu(this);
		pp = new PanelPrincipal(this);
		pe = new PanelEtat();
		pe.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
		
		this.setJMenuBar(pm.getMenuBarFrame());
		
		add(pe, BorderLayout.SOUTH);
		add(pp, BorderLayout.CENTER);
		add(pm.getJtb(), BorderLayout.NORTH);	  
	}
	
	/**
	 * This method allow to initialize the JDialog 
	 */
	private void initializeDialog(){
		this.dialogCreationTable = new DialogTableCreation(this);
		this.dialogColumnCreation = new DialogColumnCreation(this);
		this.dialogLineCreation = new DialogLineCreation(this, new String[]{""});
	}
	
	/**
	 * Getter for the controller
	 * @return The controller
	 */
	public Controller getControl(){
		return this.control;
	}
	
	/**
	 * Getter for the panel menu
	 * @return The panel menu
	 */
	public PanelMenu getPanelMenu(){
		return this.pm;
	}
	
	/**
	 * Getter for the panel etat
	 * @return The panel etat
	 */
	public PanelEtat getPanelEtat(){
		return this.pe;
	}
	
	/**
	 * Getter the principal panel
	 * @return The panel principal
	 */
	public PanelPrincipal getPanelPrincipal(){
		return this.pp;
	}
	
	/**
	 * Getter for the connection dialog
	 * @return The dialog connection
	 */
	public DialogConnection getDialogConnection(){
		return this.dialogConnection;
	}
	
	/**
	 * Getter for the creation table dialog
	 * @return The dialog creation table
	 */
	public DialogTableCreation getDialogTableCreation(){
		return this.dialogCreationTable;
	}
	
	/**
	 * Getter for the column creation dialog
	 * @return The dialog column creation
	 */
	public DialogColumnCreation getDialogColumnCreation(){
		return this.dialogColumnCreation;
	}
	
	
	/**
	 * Getter for the line creation dialog
	 * @return The dialog line creation
	 */
	public DialogLineCreation getDialogLineCreation(){
		return this.dialogLineCreation;
	}
	
	/**
	 *  This method allow to open the dialog for the creation table
	 */
	public void openingDialogCreationTable(){
		this.dialogCreationTable = new DialogTableCreation(this);
	}
	
	/**
	 * This method allow to open the dialog for the creation line
	 * @param columnNames The name of the column
	 */
	public void openingDialogCreationLine(String[] columnNames){
		this.dialogLineCreation = new DialogLineCreation(this, columnNames);
	}
	
	/**
	 * This method allow to open the dialog for the creation column
	 */
	public void openingDialogCreationColumn(){
		this.dialogColumnCreation = new DialogColumnCreation(this);
	}
	
}
