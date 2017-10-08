package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * This class allow you to play a game to learn the SQL
 * @author Alexandre BOUDET and Benoit MARTEL
 * @version 1.0
 */
public class FrameJeuSQL extends JDialog {
	/**
	 * This assignment corresponds to the JPanels 
	 */
	private JPanel panDebut, pan1, pan2, pan3, pan4, pan5, pan6, panFin;
	
	/**
	 * This assignment corresponds to the JPanels
	 */
	private JPanel content, jp1;
	
	/**
	 * This assignment corresponds to the JPanels
	 */
	private JPanel buttonGroup1, buttonGroup2, buttonGroup3, buttonGroup4, buttonGroup5, buttonGroup6;
	
	/**
	 * This assignment corresponds to the CardLayout of the JeuSQL
	 */
	private CardLayout cl;
	
	/**
	 * This assignment corresponds to the name of the card 
	 */
	String[] listPan = {"panDebut", "pan1", "pan2", "pan3", "pan4", "pan5", "pan6", "panFin"};
	
	/**
	 * This assignment corresponds to the button (Validate, Begin) of the JeuSQL
	 */
	private JButton button;
	
	/**
	 * This assignment corresponds to the counter of points of the game SQL 
	 */
	private int counterPoint = 0;
	
	/**
	 * This assignment corresponds to all the answers
	 */
	private JRadioButton reponse1, reponse2, reponse1_2, reponse2_2, reponse1_3, reponse2_3, reponse1_4, reponse2_4, reponse1_5, reponse2_5, reponse1_6, reponse2_6;
	
    /**
      * This assignment corresponds to the result of quizz
      */
    private JLabel icon, jlFin;

	/**
	 * Initialize a FrameJeuSQL object
     * @param frame the frame of this application
	 */
	public FrameJeuSQL(FrameApp frame) {
        super(frame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Jeu SQL - Niveau BG");
		this.setLayout(new BorderLayout());
		initializeFrame();
		this.setPreferredSize(new Dimension(650, 750));
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * This methods allow to initialize the JeuSQL frame
	 */
	private void initializeFrame() {
	
    	GridBagLayout gbl = new GridBagLayout();
		jp1 = new JPanel(gbl);
		button = new JButton("Commencer");
		button.addActionListener(new ListenerBouton());
		Font f = new Font("Dialog", Font.PLAIN, 36);
		button.setPreferredSize(new Dimension(200,50));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 80, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        jp1.setBackground(new Color(135,206,250));
        jp1.add(button, c);
		
        GridBagConstraints cCard = new GridBagConstraints();
        panDebut = new JPanel(gbl);
        panDebut.setBackground(new Color(135,206,250));

/*
        try{
            icon = new JLabel(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/icon.png"))).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)), JLabel.CENTER);
        }
        catch(Exception e){
            icon = new JLabel();
        }
        cCard.gridx = 0;
        cCard.gridy = 0;
        cCard.insets = new Insets(5, 10, 80, 0);
        cCard.anchor = GridBagConstraints.FIRST_LINE_START;
        panDebut.add(icon, cCard);
*/

        //PanDebut configuration
        JLabel text = new JLabel("Jeu SQL");
        text.setFont(f);
        
        
        cCard.gridx = 1;
        cCard.gridy = 1;
        cCard.insets = new Insets(5, 10, 80, 0);
        cCard.anchor = GridBagConstraints.FIRST_LINE_START;
		panDebut.add(text, cCard);
		
		//pan1
		JLabel jl1_1 = new JLabel("Question 1");
        //jl1_1.setFont(f);
        pan1 = new JPanel(gbl);
        pan1.setBackground(new Color(135,206,250));
        pan1.add(jl1_1, cCard);
        cCard.gridy = 2;
        JLabel jl1_2  = new JLabel("Que signifie SQL ?");
        //jl1_2.setFont(f);
        pan1.add(jl1_2, cCard);
        cCard.gridy = 3;
        reponse1 = new JRadioButton("Smart Query Language");
        reponse1.setBackground(new Color(135,206,250));
        reponse2 = new JRadioButton("Structured Query Language");
        reponse2.setBackground(new Color(135,206,250));
        ButtonGroup group = new ButtonGroup();
        group.add(reponse1);
        group.add(reponse2);
        buttonGroup1 = new JPanel();
        buttonGroup1.setBackground(new Color(135,206,250));
        buttonGroup1.setLayout(new BoxLayout(buttonGroup1, BoxLayout.PAGE_AXIS));
        buttonGroup1.add(reponse1);
        buttonGroup1.add(reponse2);
        pan1.add(buttonGroup1, cCard);
        
        //Pan2
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jl2_1 = new JLabel("Question 2");
        //jl1_1.setFont(f);
        pan2 = new JPanel(gbl);
        pan2.setBackground(new Color(135,206,250));
        pan2.add(jl2_1, cCard);
        cCard.gridy = 2;
        JLabel jl2_2  = new JLabel("Quelle est la synthaxe minimum pour l'écriture d'une requête d'une requête SQL ? ?");
        //jl1_2.setFont(f);
        pan2.add(jl2_2, cCard);
        cCard.gridy = 3;
        reponse1_2 = new JRadioButton("SELECT ... FROM ... ;");
        reponse1_2.setBackground(new Color(135,206,250));
        reponse2_2 = new JRadioButton("SELECT ... FROM ... WHERE ... ;");
        reponse2_2.setBackground(new Color(135,206,250));
        ButtonGroup group_2 = new ButtonGroup();
        group_2.add(reponse1_2);
        group_2.add(reponse2_2);
        buttonGroup2 = new JPanel();
        buttonGroup2.setBackground(new Color(135,206,250));
        buttonGroup2.setLayout(new BoxLayout(buttonGroup2, BoxLayout.PAGE_AXIS));
        buttonGroup2.add(reponse1_2);
        buttonGroup2.add(reponse2_2);
        pan2.add(buttonGroup2, cCard);
        
        //Pan3
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jl3_1 = new JLabel("Question 3");
        //jl1_1.setFont(f);
        pan3 = new JPanel(gbl);
        pan3.setBackground(new Color(135,206,250));
        pan3.add(jl3_1, cCard);
        cCard.gridy = 2;
        JLabel jl3_2  = new JLabel("Quelle instruction SQL est utilisée pour mettre à jour les données dans une base de données ?");
        //jl1_2.setFont(f);
        pan3.add(jl3_2, cCard);
        cCard.gridy = 3;
        reponse1_3 = new JRadioButton("UPDATE");
        reponse1_3.setBackground(new Color(135,206,250));
        reponse2_3 = new JRadioButton("SAVE");
        reponse2_3.setBackground(new Color(135,206,250));
        ButtonGroup group_3 = new ButtonGroup();
        group_3.add(reponse1_3);
        group_3.add(reponse2_3);
        buttonGroup3 = new JPanel();
        buttonGroup3.setBackground(new Color(135,206,250));
        buttonGroup3.setLayout(new BoxLayout(buttonGroup3, BoxLayout.PAGE_AXIS));
        buttonGroup3.add(reponse1_3);
        buttonGroup3.add(reponse2_3);
        pan3.add(buttonGroup3, cCard);
        
        //Pan4
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jl4_1 = new JLabel("Question 4");
        //jl1_1.setFont(f);
        pan4 = new JPanel(gbl);
        pan4.setBackground(new Color(135,206,250));
        pan4.add(jl4_1, cCard);
        cCard.gridy = 2;
        JLabel jl4_2  = new JLabel("Quelle instruction SQL est utilisée pour supprimer des données d'une base de données ?");
        //jl1_2.setFont(f);
        pan4.add(jl4_2, cCard);
        cCard.gridy = 3;
        reponse1_4 = new JRadioButton("Delete");
        reponse1_4.setBackground(new Color(135,206,250));
        reponse2_4 = new JRadioButton("Remove");
        reponse2_4.setBackground(new Color(135,206,250));
        ButtonGroup group_4 = new ButtonGroup();
        group_4.add(reponse1_4);
        group_4.add(reponse2_4);
        buttonGroup4 = new JPanel();
        buttonGroup4.setBackground(new Color(135,206,250));
        buttonGroup4.setLayout(new BoxLayout(buttonGroup4, BoxLayout.PAGE_AXIS));
        buttonGroup4.add(reponse1_4);
        buttonGroup4.add(reponse2_4);
        pan4.add(buttonGroup4, cCard);
        
        //Pan5
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jl5_1 = new JLabel("Question 5");
        //jl1_1.setFont(f);
        pan5 = new JPanel(gbl);
        pan5.setBackground(new Color(135,206,250));
        pan5.add(jl5_1, cCard);
        cCard.gridy = 2;
        JLabel jl5_2  = new JLabel("En SQL, comment sélectionnez-vous toutes les colonnes d'une table nommée \"Persons\" ?");
        //jl1_2.setFont(f);
        pan5.add(jl5_2, cCard);
        cCard.gridy = 3;
        reponse1_5 = new JRadioButton("SELECT * FROM Persons");
        reponse1_5.setBackground(new Color(135,206,250));
        reponse2_5 = new JRadioButton("SELECT [all] FROM Persons");
        reponse2_5.setBackground(new Color(135,206,250));
        ButtonGroup group_5 = new ButtonGroup();
        group_5.add(reponse1_5);
        group_5.add(reponse2_5);
        buttonGroup5 = new JPanel();
        buttonGroup5.setBackground(new Color(135,206,250));
        buttonGroup5.setLayout(new BoxLayout(buttonGroup5, BoxLayout.PAGE_AXIS));
        buttonGroup5.add(reponse1_5);
        buttonGroup5.add(reponse2_5);
        pan5.add(buttonGroup5, cCard);
        
        //Pan6
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jl6_1 = new JLabel("Question 6");
        //jl1_1.setFont(f);
        pan6 = new JPanel(gbl);
        pan6.setBackground(new Color(135,206,250));
        pan6.add(jl6_1, cCard);
        cCard.gridy = 2;
        JTextArea jl6_2  = new JTextArea("En SQL, comment sélectionnez-vous tous les enregistrements d'une table nommée\n\"Persons\" où \"FirstName\" est \"Peter\" et \"LastName\" est \"Jackson\" ?");
        jl6_2.setEditable(false);
        jl6_2.setOpaque(false);
        //jl1_2.setFont(f);
        pan6.add(jl6_2, cCard);
        cCard.gridy = 3;
        reponse1_6 = new JRadioButton("SELECT FirstName='Peter', LastName='Jackson' FROM Persons");
        reponse1_6.setBackground(new Color(135,206,250));
        reponse2_6 = new JRadioButton("SELECT * FROM Persons WHERE FirstName='Peter' AND LastName='Jackson'");
        reponse2_6.setBackground(new Color(135,206,250));
        ButtonGroup group_6 = new ButtonGroup();
        group_6.add(reponse1_6);
        group_6.add(reponse2_6);
        buttonGroup6 = new JPanel();
        buttonGroup6.setBackground(new Color(135,206,250));
        buttonGroup6.setLayout(new BoxLayout(buttonGroup6, BoxLayout.PAGE_AXIS));
        buttonGroup6.add(reponse1_6);
        buttonGroup6.add(reponse2_6);
        pan6.add(buttonGroup6, cCard);
        
        //PanFin
        cCard.gridx = 1;
        cCard.gridy = 1;
        JLabel jlresultat = new JLabel("Résultat du quizz :");
        panFin = new JPanel(gbl);
        panFin.setBackground(new Color(135,206,250));
        panFin.add(jlresultat, cCard);
        cCard.gridy = 2;
        jlFin  = new JLabel();
        panFin.add(jlFin, cCard);
		
		//Content which regroup all the cards
		cl = new CardLayout();
		content = new JPanel();
		content.setLayout(cl);
		content.add(panDebut, listPan[0]);
		content.add(pan1, listPan[1]);
		content.add(pan2, listPan[2]);
		content.add(pan3, listPan[3]);
		content.add(pan4, listPan[4]);
		content.add(pan5, listPan[5]);
		content.add(pan6, listPan[6]);
		content.add(panFin, listPan[7]);
		
		//Placement des composants sur la fenêtre
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(jp1, BorderLayout.SOUTH);
        
	}
	
	/**
	 * This internal class is the listener of the unique button of the game SQL
	 * @author Alexandre BOUDET and Benoit MARTEL
	 * @version 1.0
	 */
	public class ListenerBouton implements ActionListener {
		
		/**
		 * This assignment correspond to the index of the listPan
		 */
		private int i = 0;
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
            
			if(FrameJeuSQL.this.reponse2.isSelected() == true && listPan[i].equals("pan1")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 1");
        	}
        	if(FrameJeuSQL.this.reponse1_2.isSelected() == true && listPan[i].equals("pan2")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 2");
        	}
        	if(FrameJeuSQL.this.reponse1_3.isSelected() == true && listPan[i].equals("pan3")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 3");
        	}
        	if(FrameJeuSQL.this.reponse1_4.isSelected() == true && listPan[i].equals("pan4")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 4");
        	}
        	if(FrameJeuSQL.this.reponse1_5.isSelected() == true && listPan[i].equals("pan5")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 5");
        	}
        	if(FrameJeuSQL.this.reponse2_6.isSelected() == true && listPan[i].equals("pan6")) {
        		FrameJeuSQL.this.counterPoint++;
        		System.out.println("Question 6");
       	 	}
        	if(FrameJeuSQL.this.button.getText().equals("Terminer")) {
        		FrameJeuSQL.this.dispose();
        	}
            

            i++;  
            if(i < listPan.length) {

                 cl.show(content, listPan[i]);

                 if(i == listPan.length - 1) {
                     FrameJeuSQL.this.jlFin.setText(FrameJeuSQL.this.counterPoint +"/6 !");
                     FrameJeuSQL.this.button.setText("Terminer");
                 }
                 else {
                     FrameJeuSQL.this.button.setText("Suivant");
                 }
            }
		}
	}
}
