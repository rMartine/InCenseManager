package edu.incense.manager.java.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Insets;

import javax.swing.JDesktopPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JPanel;

import edu.incense.manager.java.emitters.DataEmitter;
import edu.incense.manager.java.emitters.DataComponentEmitter;
import edu.incense.manager.java.emitters.DataTypeEmitter;
import edu.incense.manager.java.emitters.TaskFactoryEmitter;
import edu.incense.manager.java.emitters.TaskTypeEmitter;
import edu.incense.manager.java.model.ComponentDataBase;
import edu.incense.manager.java.model.ComponentRepository;
import edu.incense.manager.java.model.ComponentStructure;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class ComponentExplorerFrame extends JInternalFrame implements ActionListener{
	private JButton btnShowComponentClass;
	private JButton btnShowDataClass;
	private JButton btnShowTaskfactoryClass;
	private JButton btnShowTasktypeClass;
	private JButton btnShowDatatypeClass;
	private JButton btnModify;
	private JDesktopPane desktop;
	private JTextArea textArea;
	private JComboBox comboBox;
	private JCheckBox chckbxRegistered;
	private ModifyComponentWizard frameModif;
	private DataComponentEmitter dataFE = new DataComponentEmitter();
	private TaskFactoryEmitter taskFE = new TaskFactoryEmitter();
	private DataEmitter dataE = new DataEmitter();
	private TaskTypeEmitter taskTE = new TaskTypeEmitter();
	private DataTypeEmitter dataTE = new DataTypeEmitter();
	private ComponentRepository componentRepository;
	
	/**
	 * Create the frame.
	 */
	public ComponentExplorerFrame (JDesktopPane desktop) {
		setFrameIcon(new ImageIcon(ComponentExplorerFrame.class.getResource("/edu/incense/manager/java/ui/images/database.gif")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.desktop = desktop;
		
		setClosable(true);
		setTitle("Explore User Added Components");
		setBounds(100, 100, 660, 570);
		getContentPane().setLayout(null);
		
		JLabel lblExistingComponents = new JLabel("Repository");
		lblExistingComponents.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExistingComponents.setBounds(10, 11, 119, 14);
		getContentPane().add(lblExistingComponents);
		
		btnShowComponentClass = new JButton("Component");
		btnShowComponentClass.setBounds(10, 88, 119, 23);
		btnShowComponentClass.setToolTipText("Show Component Class");
		btnShowComponentClass.addActionListener(this);
		getContentPane().add(btnShowComponentClass);
		
		btnShowDataClass = new JButton("Data");
		btnShowDataClass.setBounds(10, 122, 119, 23);
		btnShowDataClass.setToolTipText("Show Data Class");
		btnShowDataClass.addActionListener(this);
		getContentPane().add(btnShowDataClass);
		
		btnShowTaskfactoryClass = new JButton("TaskFactory");
		btnShowTaskfactoryClass.setBounds(10, 156, 119, 23);
		btnShowTaskfactoryClass.setToolTipText("Show TaskFactory Class");
		btnShowTaskfactoryClass.addActionListener(this);
		getContentPane().add(btnShowTaskfactoryClass);
		
		btnShowTasktypeClass = new JButton("TaskType");
		btnShowTasktypeClass.setBounds(10, 190, 119, 23);
		btnShowTasktypeClass.setToolTipText("Show DataType Class");
		btnShowTasktypeClass.addActionListener(this);
		getContentPane().add(btnShowTasktypeClass);
		
		btnShowDatatypeClass = new JButton("DataType");
		btnShowDatatypeClass.setBounds(10, 224, 119, 23);
		btnShowDatatypeClass.setToolTipText("Show DataType Class");
		btnShowDatatypeClass.addActionListener(this);
		getContentPane().add(btnShowDatatypeClass);
		
		btnModify = new JButton("Implement");
		btnModify.setBounds(10, 306, 97, 23);
		btnModify.addActionListener(this);
		btnModify.setToolTipText("Modify components defined with ontology");
		getContentPane().add(btnModify);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 11, 495, 519);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnExport = new JButton("Export");
		btnExport.setToolTipText("Modify components defined with ontology");
		btnExport.setBounds(10, 374, 85, 23);
		getContentPane().add(btnExport);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 119, 23);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
		
		chckbxRegistered = new JCheckBox("Registered");
		chckbxRegistered.setEnabled(false);
		chckbxRegistered.setBounds(10, 58, 97, 23);
		getContentPane().add(chckbxRegistered);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ComponentExplorerFrame.class.getResource("/edu/incense/manager/java/ui/images/ciceseIcono.gif")));
		lblNewLabel.setBounds(-50, 374, 574, 204);
		getContentPane().add(lblNewLabel);
		
		/*Populating form*/
		populateComboBx();
	}
	
	/*Populate form with the names of the compoents*/
	public void populateComboBx() {
		componentRepository = new ComponentRepository();
		String[] laLista = new String[componentRepository.getComponentRepository().size()] ;
		for (int i = 0; i < componentRepository.getComponentRepository().size(); i++) {
			laLista[i] = componentRepository.getComponentRepository().get(i).getComponentName();
		}
		comboBox.setModel(new DefaultComboBoxModel(laLista));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnModify) {
			frameModif = new ModifyComponentWizard(this, componentRepository.getComponentRepository().get(comboBox.getSelectedIndex()), componentRepository.getValidJavaTypes(), componentRepository.getValidVarTypes());
			desktop.add(frameModif);
			frameModif.setVisible(true);
	        try {
	        	this.setVisible(false);
	        	frameModif.setSelected(true);
	        } catch (java.beans.PropertyVetoException e) {}
		}
		
		if (arg0.getSource() == comboBox) {
			chckbxRegistered.setSelected(componentRepository.getComponentRepository().get(comboBox.getSelectedIndex()).isDbRegistered());
		}
		
		if (arg0.getSource() == btnShowComponentClass) {
			ComponentStructure f = componentRepository.getComponentRepository().get(comboBox.getSelectedIndex());
			textArea.setText(dataFE.generate(f));
		}
		
		if (arg0.getSource() == btnShowDataClass) {
			ComponentStructure f = componentRepository.getComponentRepository().get(comboBox.getSelectedIndex());
			textArea.setText(dataE.generate(f));
		}
		
		if (arg0.getSource() == btnShowTaskfactoryClass) {
			ComponentStructure f = componentRepository.getComponentRepository().get(comboBox.getSelectedIndex());
			textArea.setText(taskFE.generate(f));
		}
		
		if (arg0.getSource() == btnShowTasktypeClass) {
			ComponentStructure f = componentRepository.getComponentRepository().get(comboBox.getSelectedIndex());
			textArea.setText(taskTE.generate(f));
		}
		
		if (arg0.getSource() == btnShowDatatypeClass) {
			ComponentStructure f = componentRepository.getComponentRepository().get(comboBox.getSelectedIndex());
			textArea.setText(dataTE.generate(f));
		}
	}
}
