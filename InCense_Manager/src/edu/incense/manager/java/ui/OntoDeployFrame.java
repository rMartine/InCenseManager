package edu.incense.manager.java.ui;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import edu.incense.manager.java.model.DynamicTree;
import edu.incense.manager.java.model.ProjectTree;
import edu.incense.manager.java.ui.utils.FileChooserFilter;
import edu.incense.manager.java.ui.utils.XMLFileView;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class OntoDeployFrame extends JInternalFrame implements ActionListener{
	private DynamicTree panel;
	private JButton btnCancel;
	private JButton btnExport;

	/**
	 * Create the frame.
	 */
	public OntoDeployFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setFrameIcon(new ImageIcon(OntoDeployFrame.class.getResource("/edu/incense/manager/java/ui/images/export.gif")));
		setClosable(true);
		setTitle("Sensing Campaign Deployment");
		setBounds(100, 100, 495, 475);
		getContentPane().setLayout(null);
		
		panel = new DynamicTree();
		panel.setBounds(10, 44, 459, 312);
		getContentPane().add(panel);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 396, 127, 39);
		btnCancel.setIcon(new ImageIcon(OntoDeployFrame.class.getResource("/edu/incense/manager/java/ui/images/cancel.gif")));
		btnCancel.addActionListener(this);
		getContentPane().add(btnCancel);
		
		btnExport = new JButton("Deploy");
		btnExport.setBounds(360, 396, 109, 39);
		btnExport.setIcon(new ImageIcon(OntoDeployFrame.class.getResource("/edu/incense/manager/java/ui/images/json.gif")));
		btnExport.addActionListener(this);
		getContentPane().add(btnExport);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MonitoringActivityCounts", "ProblematicBehaviors"}));
		comboBox.setBounds(10, 13, 166, 20);
		getContentPane().add(comboBox);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(OntoDeployFrame.class.getResource("/edu/incense/manager/java/ui/images/ciceseIcono.gif")));
		label.setBounds(-39, 231, 574, 204);
		getContentPane().add(label);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnCancel) {
			this.setVisible(false);
		}
	}
}
