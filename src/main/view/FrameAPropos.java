package view;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.imageio.*;

import control.Controller;
import view.PanelEtat;
import view.PanelMenu;
import view.PanelPrincipal;

/**
 * 
 * 
 * @author Alexandre BOUDET et Benoît MARTEL
 * @version 1.0
 */
public class FrameAPropos extends JDialog {

	private JLabel title;

	private JLabel icon;

	private JLabel version, author, date;
	
	/**
	 * The constructor of FrameAPropos
	 * @param frame the frame of this application
	 */
	public FrameAPropos(FrameApp frame) {

		super(frame);
		this.setTitle("A Propos de Databs");
		this.setResizable(false);
		this.setModal(true);
		this.setPreferredSize(new Dimension(380,300));
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		System.out.println(screen.width+" "+screen.height);
		initialize();

		this.pack();
		this.setVisible(true);	
	}

	/**
	  * This method allow you to initialize the frame
	  */
	private void initialize(){
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

		title = new JLabel("<html> <h1 style=\"text-align: center;\">Databs</h1> <br> <p style=\"text-align: center;\">Editeur de base de donn&eacute;es pour d&eacute;butant et utilisateurs avanc&eacute;s</p> </html>");

		
		try{
			icon = new JLabel(new ImageIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/icon.png"))).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)), JLabel.CENTER);
		}
		catch(Exception e){
			icon = new JLabel();
		}

		version = new JLabel("<html>Version 1.0</html>", JLabel.CENTER);
		author = new JLabel("<html>&copy; Beno&icirc;t MARTEL &amp; Alexandre BOUDET</html>", JLabel.CENTER);
		date = new JLabel("<html> 2017 </html>", JLabel.CENTER);


		title.setAlignmentX(CENTER_ALIGNMENT);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		version.setAlignmentX(CENTER_ALIGNMENT);
		author.setAlignmentX(CENTER_ALIGNMENT);
		date.setAlignmentX(CENTER_ALIGNMENT);


		
		listPane.add(title);
		listPane.add(icon);
		listPane.add(version);
		listPane.add(author);
		listPane.add(date);


		this.add(listPane);

	}
}