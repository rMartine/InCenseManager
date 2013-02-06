package edu.incense.manager.java;

import javax.swing.JFrame;
import edu.incense.manager.java.ui.InCenseMainFrame;

public class InCenseManager {
	static String appName = "InCense Manager";
	public static InCenseMainFrame mainFrame = new InCenseMainFrame();
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Make sure we have nice window decorations.
                JFrame.setDefaultLookAndFeelDecorated(true);
                
                //Iniciado
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Display the window.
                mainFrame.setVisible(true);
            }
        });
	}

}
