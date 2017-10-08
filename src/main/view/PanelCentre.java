package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import model.TableModel;

/**
 * This class corresponds to the panel at the center of the main window
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class PanelCentre extends JPanel {
	
	/**
	 * This assignment correspond to the panelPrincipal
	 */
	private PanelPrincipal panPrincipal;
	
	/**
	 * This assignment correspond to the JTabbedPane
	 */
	private JTabbedPane jtp1, jtp2;
	
	/**
	 * This assignment correspond to the JTable
	 */
	private JTable jt;
	
	/**
	 *  This assignment correspond to the panelBas
	 */
	private JPanel panelBas;
	
	/**
	 * This assignment correspond to the JScrollPane
	 */
	private JScrollPane scrollHaut, scrollBas, scrollTop, scrollTable;

	
	/**
	 * This assignment correspond to the JSplitPane
	 */
	private JSplitPane jsp;
	
	/**
	 * This assignment corresponds to the JTable
	 */
	private JTable tableResult;
	
	/**
	 * This assignment correspond to the TextArea
	 */
	private JTextArea jtextArea;

	/**
	 * This assignment corresponds to the ClearBouton
	 */
	private JButton clearBoutton;
	
	/**
	 * Initialize a panelCentre Object
	 * @param panPrincipal the {@link PanelPrincipal} of this application
	 */
	public PanelCentre(PanelPrincipal panPrincipal) {
		
		this.panPrincipal = panPrincipal;
		
		//scrollHaut = new JScrollPane(); 	// SCROLL DU HAUT

		jtextArea = new JTextArea("");
		jtextArea.setEditable(false);
		jtextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		
		scrollBas = new JScrollPane(jtextArea); 	// SCROLL DU BAS

		jtp1 = new JTabbedPane();
		jtp2 = new JTabbedPane();
		jtp2.setTabPlacement(JTabbedPane.BOTTOM);	

		tableResult = new JTable(){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		JScrollPane scrollTable = new JScrollPane(tableResult);
		jtp2.add(scrollBas, "Message");
		jtp2.add(scrollTable, "Resultat");

		clearBoutton = new JButton();
		clearBoutton.setToolTipText("Nettoyer la console");
		clearBoutton.setOpaque(false);
		clearBoutton.setContentAreaFilled(false);
		clearBoutton.setBorderPainted(false);
		clearBoutton.addActionListener(new ListenerClearBouton());
		
		try{
			clearBoutton.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelCentre/clearButton.png"))));
		}
		catch(Exception err){}

		panelBas = new JPanel(new BorderLayout());

		JPanel pan2 = new JPanel(new BorderLayout());
		panelBas.add(clearBoutton, BorderLayout.EAST);

		pan2.add(panelBas, BorderLayout.NORTH);
		pan2.add(jtp2);

		addTabPan("Nouveau");

		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, jtp1, pan2);
		jsp.setOneTouchExpandable(true);
		jsp.setDividerLocation(600);


        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 500);
        jtp1.setMinimumSize(minimumSize);
		
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
	}
	
	/**
	 * This class is the listener of the Clear Button
	 * @author Alexandre BOUDET and Benoît MARTEL
	 * @version 1.0
	 */
	public class ListenerClearBouton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jtextArea.selectAll();
			jtextArea.replaceSelection("");
		}
		
	}
	
	/**
	 * This method deals to put some design on the button
	 * @param title Title of the tab
	 * @return pnlTable The table which receive the design
	 */
	private JPanel designTab(String title){
		
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(title);
		JButton btnClose = new JButton();
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setPreferredSize(new Dimension(30,20));
		btnClose.addActionListener(new MyCloseActionHandler(title));

		try{
			btnClose.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelCentre/delButton.png"))));
			btnClose.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/panelCentre/delButton_press.png"))));
		}
		catch(Exception err){}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		pnlTab.add(btnClose, gbc);
		return pnlTab;
	}
	
	/**
	 * Getter
	 * @return the jsp
	 */
	public JSplitPane getJsp() {
		return jsp;
	}

	/**
	 * @param jsp the jsp to set
	 */
	public void setJsp(JSplitPane jsp) {
		this.jsp = jsp;
	}
	
	/**
	 * This class is the listener of the tab on the JTabbedPane
	 * @author Alexandre BOUDET and Benoît MARTEL
	 * @version 1.0
	 */
	public class MyCloseActionHandler implements ActionListener {
		
		/**
		 * This assignment corresponds to the name of the tab
		 */
	    private String tabName;
	    
	    /**
	     * Initialize a MyCloseActionHandler object
	     * @param tabName the name of the tab
	     */
	    public MyCloseActionHandler(String tabName) {
	        this.tabName = tabName;
	    }
	    
	    /**
	     * Getter the name of the tab
	     * @return The tab's name
	     */
	    public String getTabName() {
	        return tabName;
	    }
	    
	    /*
	     * (non-Javadoc)
	     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	     */
	    @Override
	    public void actionPerformed(ActionEvent evt) {

	        int index = jtp1.indexOfTab(getTabName());
	        if (index >= 0) {

	            jtp1.removeTabAt(index);
	        }

	    }


	}
    
	/**
	 * Getter of the jTabbedPanTop
	 * @return The JTabbedPane at the top of the frame
	 */
	public JTabbedPane getJTabbedPanTop(){
		return this.jtp1;
	}
	
	/**
	 * Getter
	 * @return The JTabbedPane at the bot of the frame
	 */
	public JTabbedPane getJTabbedPanBot(){
		return this.jtp2;
	}
	
	/**
	 * Getter
	 * @return The text area
	 */
	public JTextArea getTextArea(){
		return this.jtextArea;
	}
	
	/**
	 * This method allow to set the result on the table
	 * @param query the query for which to display the result
	 */
	public void setTableResult(TableModel query){

		JTable newTable = new JTable(query);
		this.tableResult.setModel(newTable.getModel());
		DefaultTableModel tableModel = (DefaultTableModel) this.tableResult.getModel();
		tableModel.fireTableDataChanged();
		this.tableResult.repaint();
		this.tableResult.validate();
		this.tableResult.revalidate();
		this.repaint();
		this.validate();
		this.revalidate();
	}
	
	/**
	 * This method allow to add a Tab to the JTabbedPane (Override)
	 * @param nameTable The name of the Table
	 * @param dataTable The data of the table
	 */
	public void addTabPan(String nameTable, TableModel dataTable){
		 
		 JTable jtable = new JTable(dataTable);
		 JScrollPane scrollTable = new JScrollPane(jtable);
		 jtp1.addTab(nameTable, scrollTable);
		 jtp1.setTabComponentAt(jtp1.indexOfTab(nameTable), designTab(nameTable));
		 this.repaint();
		 this.validate();
		
	}
	
	/**
	 * This method allow to add a Tab to the JTabbedPane (Override)
	 * @param nameTable The name of the table
	 * @param dataTable the model table
	 * @param index the index to add tab
	 */
	public void addTabPan(String nameTable, TableModel dataTable, int index){
		 
		 JTable jtable = new JTable(dataTable);
		 JScrollPane scrollTable = new JScrollPane(jtable);
		 jtp1.insertTab(nameTable, null, scrollTable, null, index);
		 jtp1.setTabComponentAt(jtp1.indexOfTab(nameTable), designTab(nameTable));
		 this.repaint();
		 this.validate();
		
	}
	
	/**
	 * This method allow to add a Tab to the JTabbedPane (Override)
	 * @param nameTable The name of the table
	 */
	public void addTabPan(String nameTable){
		
		RSyntaxTextArea textArea = new RSyntaxTextArea(20,60);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
	    textArea.setCodeFoldingEnabled(true);
	    textArea.setFont(new Font("Arial", Font.PLAIN, 14));
	    RTextScrollPane sp = new RTextScrollPane(textArea);
        jtp1.addTab(nameTable, sp);
		jtp1.setTabComponentAt(jtp1.indexOfTab(nameTable), designTab(nameTable));
		this.repaint();
		this.validate();
	}
	
	/**
	 * This method allow to add a Tab to the JTabbedPane (Override)
	 * @param nameTable The name of the table
	 * @param contains The contains of the table
	 */
	public void addTabPan(String nameTable, String contains){
		
		RSyntaxTextArea textArea = new RSyntaxTextArea(20,60);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
	    textArea.setCodeFoldingEnabled(true);
	    textArea.setText(contains);
	    RTextScrollPane sp = new RTextScrollPane(textArea);
        jtp1.addTab(nameTable, sp);
		jtp1.setTabComponentAt(jtp1.indexOfTab(nameTable), designTab(nameTable));
		this.repaint();
		this.validate(); 
	}

	/**
	 * This method allow to add a Tab to the JTabbedPane (Override)
	 * @param nameTable The name of the table
	 * @param contains the contains of the area
	 * @param index the index to add tab
	 */
	public void addTabPan(String nameTable, String contains, int index){
		 
		RSyntaxTextArea textArea = new RSyntaxTextArea(20,60);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
	    textArea.setCodeFoldingEnabled(true);
	    textArea.setText(contains);
	    RTextScrollPane sp = new RTextScrollPane(textArea);
	    jtp1.insertTab(nameTable, null, sp, null, index);
		jtp1.setTabComponentAt(jtp1.indexOfTab(nameTable), designTab(nameTable));
		this.repaint();
		this.validate(); 
		
	}
	
	/**
	 * This method allow to update the JTabbedPane
	 */
	public void updateTabPan(){
		this.repaint();
		this.validate();
	}
	
	/**
	 * This method allows you to change the error on the display
	 * @param error the error to display at the user
	 */
	public void setTextError(String error){
		this.jtextArea.setText(this.jtextArea.getText()+"\n"+error);
	}
	
}
