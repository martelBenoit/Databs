package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

/**
 * This class corresponds to the panel at the left of the main window
 * @author Alexandre and and Benoît MARTEL
 * @version 1.0
 */
public class PanelGauche extends JPanel {
	
	
	/**
	 * This assignment correspond to the button of the left table
	 */
	private JButton nouveau, supprimer, rafraichir;
	
	/**
	 * This assignment correspond to the tab of the panel
	 */
	private JTabbedPane jtp;
	
	/**
	 * This assignment correspond to the tree of the panel
	 */
	private JTree arbre;

	/**
	 * The DefaultMutableTreeNode
	 */
	private DefaultMutableTreeNode racine;

	/**
	 * The DefaultTreeModel
	 */
	private DefaultTreeModel model;
	
	
	/**
	 * This assignment correspond to the panel inside the JTabbedPane
	 */
	private JPanel jp;
	
	/**
	 * This assignment correspond to the panel of the buttons
	 */
	private JPanel panelBouton;
	
	/**
	 * Initialize a PanelGauche object
	 * @param panPrincipal the {@link PanelPrincipal} of this application 
	 */
	public PanelGauche(PanelPrincipal panPrincipal){
		
		nouveau = new JButton();
		nouveau.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonLeft());
		nouveau.setToolTipText("Nouvelle connexion");
		nouveau.setOpaque(false);
		nouveau.setContentAreaFilled(false);
		nouveau.setBorderPainted(false);

		
		
		rafraichir = new JButton();
		rafraichir.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonLeft());
		rafraichir.setToolTipText("Rafraichir");
		rafraichir.setOpaque(false);
		rafraichir.setContentAreaFilled(false);
		rafraichir.setBorderPainted(false);

		
		supprimer = new JButton();
		supprimer.addActionListener(panPrincipal.getFrame().getControl().getListenerButtonLeft());
		supprimer.setToolTipText("Se déconnecter");
		supprimer.setOpaque(false);
		supprimer.setContentAreaFilled(false);
		supprimer.setBorderPainted(false);


		try
   		{
   			nouveau.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_add.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			nouveau.setRolloverIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_add_rollover.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
			nouveau.setPressedIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_add_pressed.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			
   			rafraichir.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_refresh.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			rafraichir.setRolloverIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_refresh_rollover.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
			rafraichir.setPressedIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_refresh_pressed.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			
   			supprimer.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_delete.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			supprimer.setRolloverIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_delete_rollover.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
			supprimer.setPressedIcon(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/database_delete_pressed.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
   			


   		}
		catch(Exception err){}
		
		
		jtp = new JTabbedPane();
		
		racine = new DefaultMutableTreeNode("Connexions");
		
		this.model = new DefaultTreeModel(this.racine);
		arbre = new JTree(racine);
		arbre.addMouseListener(panPrincipal.getFrame().getControl().getListenerTree());
		
		panelBouton = new JPanel();
		panelBouton.setLayout(new FlowLayout());
		panelBouton.add(nouveau);
		panelBouton.add(rafraichir);
		panelBouton.add(supprimer);
		
		jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(panelBouton, BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(arbre,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(300, 300));
        jp.add(scroll, BorderLayout.CENTER);
        
		this.setLayout(new BorderLayout());
		jtp.add(jp, "Base de données");
		this.add(jtp, BorderLayout.CENTER);
		
		 //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(200, 50);
        this.setMinimumSize(minimumSize);
        ImageIcon leafIcon = null;
        ImageIcon openIcon = null;
        try
   		{
	        leafIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/leaf.png")));
	        openIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelGauche/databaseConnect.png")));
	    }
		catch(Exception err){}

        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = 
                new DefaultTreeCellRenderer();
            renderer.setLeafIcon(leafIcon);
            renderer.setOpenIcon(openIcon);
            arbre.setCellRenderer(renderer);
        }
   
	
	}
	
	/**
	 * Getter new button
	 * @return the new button
	 */
	public JButton getButtonNew(){
		return this.nouveau;
	}
	
	/**
	 * Getter delete button
	 * @return delete button
	 */
	public JButton getButtonSupprimer() {
		return supprimer;
	}
	
	/**
	 * Getter refresh button
	 * @return refresh button
	 */
	public JButton getButtonRafraichir() {
		return rafraichir;
	}
	
	/**
	 * Getter the DefaultMutableTreeNode
	 * @return DefaultMutableTreeNode
	 */
	public DefaultMutableTreeNode getMutableTreeNode(){
		return this.racine;
	}
	
	/**
	 * Getter of the DefaultTreeModel
	 * @return DefaultTreeModel
	 */
	public DefaultTreeModel getTreeModel(){
		return this.model;
	}
	
	/**
	 * Getter of the JTree
	 * @return the jTree
	 */
	public JTree getTree(){
		return this.arbre;
	}

}
