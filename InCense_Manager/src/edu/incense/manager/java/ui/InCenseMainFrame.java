package edu.incense.manager.java.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Canvas;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class InCenseMainFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuItem mntmComponentExplorer;
	private JMenuItem mntmProjectExporter;
	private JMenuItem mntmExit;
	private JDesktopPane desktopPane;
	private OntoDeployFrame exportFrame;
	private ComponentExplorerFrame exploreFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InCenseMainFrame frame = new InCenseMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InCenseMainFrame() {
		setTitle("InCense Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InCenseMainFrame.class.getResource("/edu/incense/manager/java/ui/images/ciceseIcono.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmComponentExplorer = new JMenuItem("Project Exporter");
		mntmComponentExplorer.addActionListener(this);
		mnFile.add(mntmComponentExplorer);
		
		mntmProjectExporter = new JMenuItem("Component Explorer");
		mntmProjectExporter.addActionListener(this);
		mnFile.add(mntmProjectExporter);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InCenseMainFrame.class.getResource("/edu/incense/manager/java/ui/images/logoCicese.png")));
		lblNewLabel.setBounds(128, 171, 737, 365);
		desktopPane.add(lblNewLabel);
		
		exportFrame = new OntoDeployFrame();
		exploreFrame = new ComponentExplorerFrame(desktopPane);
		desktopPane.add(exportFrame);
		desktopPane.add(exploreFrame);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == mntmComponentExplorer) { //new
        	createExporterFrame();
        } else if(arg0.getSource() == mntmProjectExporter){
        	createExploreComponentsFrame();
        } else {
            quit();
        }
	}
	
	//Create Exporter Frame.
    protected void createExporterFrame() {
    	exportFrame.setVisible(true); //necessary as of 1.3
        try {
        	exportFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
  //Create a new Component Frame.
    protected void createExploreComponentsFrame() {
    	exploreFrame.setVisible(true); //necessary as of 1.3
        try {
        	exploreFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }
}
