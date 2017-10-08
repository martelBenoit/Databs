import javax.swing.*;
import javax.imageio.ImageIO;
import view.*;

import java.awt.*;

/**
 * The launcher of this application
 * @author Benoît AND Alexandre
 * @version 1.0
 */
public class Launcher {
	
	/**
	 * Main of this application
	 * @param args No arguments provided
	*/
	public static void main(String[] args){

		 SwingUtilities.invokeLater(new Runnable(){
			 public void run(){
				 FrameApp frame = new FrameApp();
			 }
		 });
		 
	}



	
}