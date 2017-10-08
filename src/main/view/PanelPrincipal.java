/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * This class corresponds to the main panel which is at the center of the main window
 * @author Alexandre BOUDET and and Benoît MARTEL
 * @version 1.0
 */
public class PanelPrincipal extends JPanel {
	
	/**
	 * This assignment corresponds to the frame of the application
	 */
	private FrameApp frame; 
	
	/**
	  * This assignment correspond to the left panel
	  */
	private PanelGauche pg;
	
	/**
	 * This assignment correspond to the right panel
	 */
	private PanelDroite pd;
	
	/**
	 * This assignment correspond to the center panel
	 */
	private PanelCentre pc;
	
	/**
	 * This assignment correspond to the JSplitPane
	 */
	private JSplitPane jsp;
	
	/**
	 * Initialize a PanelPrincipal object
	 * @param frame  The frame of the application
	 */
	public PanelPrincipal(FrameApp frame) {
		this.frame = frame;
		pg = new PanelGauche(this);
		pd = new PanelDroite(this);
		pc = new PanelCentre(this);
		
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,  pg, pc.getJsp());
		jsp.setOneTouchExpandable(true);
		jsp.setDividerLocation(250);
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
		this.add(pd, BorderLayout.EAST);
	}
	
	/**
	 * Getter the panel gauche
	 * @return the panel gauche
	 */
	public PanelGauche getPanelGauche(){
		return this.pg;
	}
	
	
	/**
	 * Getter the panel centre
	 * @return the panel centre
	 */
	public PanelCentre getPanelCentre(){
		return this.pc;
	}
	
	/**
	 * Getter the panel droite
	 * @return the panel droite
	 */
	public PanelDroite getPanelDroite(){
		return this.pd;
	}
	
	/**
	 * Getter the frame of this application
	 * @return the frame of this application
	 */
	public FrameApp getFrame(){
		return this.frame;
	}
}
