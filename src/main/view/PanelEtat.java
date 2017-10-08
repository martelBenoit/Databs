package view;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class corresponds to the panel at the bottom of the main window
 * @author Alexandre BOUDET and and Benoît MARTEL
 * @version 1.0
 */
public class PanelEtat extends JPanel {
	
	/**
	 * This assignment corresponds to a label
	 */
	private JLabel currentAction;
	
	/**
	 * This assignment corresponds to the date with hours
	 */
	private JLabel date;
	
	/**
	 * Initialize a PanelEtat object
	 */
	public PanelEtat() {
		currentAction = new JLabel();
		date = new JLabel();

		currentAction.setText("Bonjour ");
		
		Date aujourdhui = new Date();
		DateFormat mediumDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
		//date.setText();
		
		Thread clock = new Thread(){ 
			@Override
			public void run() {
				while (true) { //boucle éternelle
				 Calendar cal = new GregorianCalendar(); 
				 int h = cal.get(Calendar.HOUR_OF_DAY); 
				 int min = cal.get(Calendar.MINUTE);  
				 date.setText(mediumDateFormat.format(aujourdhui)+",  "+String.valueOf(h)+":"+String.valueOf(min)); 
					try {
						sleep(500); // dormir une demi seconde
					} catch (InterruptedException ie) {}
				}
			}
		};
		
		clock.start();

		setLayout(new BorderLayout());
		add(currentAction, BorderLayout.WEST);
		add(date, BorderLayout.EAST);
		
		
	}
	
	/**
	 * Getter
	 * @return The JLabel of the PanelEtat
	 */
	public JLabel getEtatLabel(){
		return this.currentAction;
	}
}
