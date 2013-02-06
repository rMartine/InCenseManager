package edu.incense.manager.java.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import edu.incense.manager.java.model.ComponentDataBase;
import edu.incense.manager.java.model.ComponentStructure;

public class ModifyComponentWizard extends JInternalFrame implements ActionListener {
	private JTextField textField; //Nombre del componente
	private JTextField textField_1; //Nombre de la salida
	private JTextField textField_2; //Tipo del componente
	private JTextField textField_3; //Tipo de la salida
	private JTextField textField_4; //Nombre de nueva variable
	private JTextField textField_5; //Preset del valor de la nueva variable
	
	private JTextArea textArea; //Espacio donde se escribe el código fuente
	private JList list; //Espacio donde se muestran las variables de configuración
	private JComboBox comboBox; //Tipo de nueva variable: Integer, String, etc.
	private JComboBox comboBox_1; //DImensión de la nueva variable: ninguna, vector o matríz.
	private JList list_1; //Listado de variables agregadas o removidas del componente
	private JComboBox comboBox_2; //Propósito de las variables: Operación, Literal o Output
	
	private JButton button; //Add new variable to component
	private JButton button_1; //Remove a variable from component
	private JButton btnCancel; //No guardar modificaciones al componente
	private JButton btnSave; //Guardar modificaciones al componente
	
	private ComponentExplorerFrame parent;
	private String[] validJavaTypes;
	private String[] validVarTypes;
	private ComponentStructure component;

	/**
	 * Create the frame.
	 */
	public ModifyComponentWizard(ComponentExplorerFrame parent, ComponentStructure component, String[] validJavaTypes, String[] validVarTypes) {
		setTitle("Modify Component Wizard");
		setFrameIcon(new ImageIcon(ModifyComponentWizard.class.getResource("/edu/incense/manager/java/ui/images/componentIcon.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 564);
		getContentPane().setLayout(null);
		
		JLabel lblComponentFrame = new JLabel("Component Name");
		lblComponentFrame.setBounds(10, 11, 105, 14);
		getContentPane().add(lblComponentFrame);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(10, 36, 138, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblOutputName = new JLabel("Output Name");
		lblOutputName.setBounds(158, 11, 80, 14);
		getContentPane().add(lblOutputName);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(158, 36, 138, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblComponentType = new JLabel("Component Type");
		lblComponentType.setBounds(10, 67, 97, 14);
		getContentPane().add(lblComponentType);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(10, 92, 138, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOutputType = new JLabel("Output Type");
		lblOutputType.setBounds(158, 67, 75, 14);
		getContentPane().add(lblOutputType);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(158, 92, 138, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblComponentCode = new JLabel("Component Code");
		lblComponentCode.setBounds(10, 123, 97, 14);
		getContentPane().add(lblComponentCode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 545, 376);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setToolTipText("You may edit your code in here");
		scrollPane.setViewportView(textArea);
		
		JLabel lblConfigurationVariables = new JLabel("Configuration Variables");
		lblConfigurationVariables.setBounds(306, 11, 140, 14);
		getContentPane().add(lblConfigurationVariables);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(306, 36, 249, 101);
		getContentPane().add(scrollPane_1);
		
		list = new JList();
		list.setEnabled(false);
		scrollPane_1.setViewportView(list);
		
		JLabel lblNewVariable = new JLabel("+ New Variable");
		lblNewVariable.setBounds(610, 11, 90, 14);
		getContentPane().add(lblNewVariable);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(565, 36, 46, 14);
		getContentPane().add(lblType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(565, 54, 97, 20);
		getContentPane().add(comboBox);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(672, 36, 46, 14);
		getContentPane().add(lblName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(672, 54, 107, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setBounds(565, 85, 60, 14);
		getContentPane().add(lblDimension);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(component.DIMENSION));
		comboBox_1.setBounds(565, 103, 75, 20);
		getContentPane().add(comboBox_1);
		
		JLabel lblPresetValue = new JLabel("Preset Value");
		lblPresetValue.setBounds(672, 85, 75, 14);
		getContentPane().add(lblPresetValue);
		
		textField_5 = new JTextField();
		textField_5.setBounds(672, 103, 107, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(565, 191, 182, 227);
		getContentPane().add(scrollPane_2);
		
		list_1 = new JList();
		scrollPane_2.setViewportView(list_1);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(ModifyComponentWizard.class.getResource("/edu/incense/manager/java/ui/images/add-icon.png")));
		button.setBounds(756, 191, 23, 23);
		getContentPane().add(button);
		button.addActionListener(this);
		
		button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(ModifyComponentWizard.class.getResource("/edu/incense/manager/java/ui/images/minussymbol16.png")));
		button_1.setBounds(756, 302, 23, 23);
		getContentPane().add(button_1);
		button_1.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Without saving...");
		btnCancel.setBounds(565, 481, 89, 43);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(this);
		
		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save & exit...");
		btnSave.setBounds(690, 481, 89, 43);
		getContentPane().add(btnSave);
		btnSave.addActionListener(this);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setBounds(565, 137, 60, 14);
		getContentPane().add(lblPurpose);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(565, 155, 160, 20);
		getContentPane().add(comboBox_2);
		
		this.parent = parent;
		this.component = component;
		this.validJavaTypes = validJavaTypes;
		this.validVarTypes = validVarTypes;
		populateForm();
	}
	
	/*Populate the form with the given ComponentStructure*/
	private void populateForm() {
		textField.setText(component.getComponentName());
		if (component.isDbRegistered()) {
			textField_1.setText(component.getOutputName()); //Nombre de la salida
			textField_2.setText(component.getComponentType()); //Tipo del componente
			textField_3.setText(component.getOutputType()); //Tipo de la salida
		} else {
			textField_1.setEnabled(true);
			textField_3.setEnabled(true);
			textField_1.setText(component.getOutputName()); //Nombre de la salida
			textField_2.setText(component.getComponentName()); //Tipo del componente
			textField_3.setText(component.getOutputType()); //Tipo de la salida
		}
		textArea.setText(component.getProcessDataCode());
		comboBox.setModel(new DefaultComboBoxModel(validJavaTypes));
		comboBox_2.setModel(new DefaultComboBoxModel(validVarTypes));
		DefaultListModel lasVars = new DefaultListModel();
		boolean auxV, auxM;
		int auxI = 0;
		for (int i = 0; i < component.getConfigurationVariables().length; i++) {
			auxV = Boolean.parseBoolean(component.getConfigurationVariables()[i][component.VAR_VECTOR]);
			auxM = Boolean.parseBoolean(component.getConfigurationVariables()[i][component.VAR_MATRIX]);
			if (!auxV && !auxM) {
				auxI = component.VAR_DIMENSION_NONE;
			} else if (auxV && !auxM) {
				auxI = component.VAR_DIMENSION_VECTOR;
			} else {
				auxI = component.VAR_DIMENSION_MATRIX;
			}
			lasVars.addElement(validJavaTypes[Integer.parseInt(component.getConfigurationVariables()[i][component.VAR_TYPE])-1] + ":" + component.DIMENSION[auxI] + ":" + component.getConfigurationVariables()[i][component.VAR_NAME] + ":" + component.getConfigurationVariables()[i][component.VAR_VALUE] + ":" + validVarTypes[Integer.parseInt(component.getConfigurationVariables()[i][component.VAR_KIND])-1]);
		}
		list.setModel(lasVars);
		
		DefaultListModel lasVars2 = new DefaultListModel();
		//Literals
		for (int i = 0; i < component.getDataLiterals().length; i++) {
			auxV = Boolean.parseBoolean(component.getDataLiterals()[i][component.VAR_VECTOR]);
			auxM = Boolean.parseBoolean(component.getDataLiterals()[i][component.VAR_MATRIX]);
			if (!auxV && !auxM) {
				auxI = component.VAR_DIMENSION_NONE;
			} else if (auxV && !auxM) {
				auxI = component.VAR_DIMENSION_VECTOR;
			} else {
				auxI = component.VAR_DIMENSION_MATRIX;
			}
			lasVars2.addElement(validJavaTypes[Integer.parseInt(component.getDataLiterals()[i][component.VAR_TYPE])-1] + ":" + component.DIMENSION[auxI] + ":" + component.getDataLiterals()[i][component.VAR_NAME] + ":" + component.getDataLiterals()[i][component.VAR_VALUE] + ":" + validVarTypes[Integer.parseInt(component.getDataLiterals()[i][component.VAR_KIND])-1]);
		}
		
		//Operation
		for (int i = 0; i < component.getOperationVariables().length; i++) {
			auxV = Boolean.parseBoolean(component.getOperationVariables()[i][component.VAR_VECTOR]);
			auxM = Boolean.parseBoolean(component.getOperationVariables()[i][component.VAR_MATRIX]);
			if (!auxV && !auxM) {
				auxI = component.VAR_DIMENSION_NONE;
			} else if (auxV && !auxM) {
				auxI = component.VAR_DIMENSION_VECTOR;
			} else {
				auxI = component.VAR_DIMENSION_MATRIX;
			}
			lasVars2.addElement(validJavaTypes[Integer.parseInt(component.getOperationVariables()[i][component.VAR_TYPE])-1] + ":" + component.DIMENSION[auxI] + ":" + component.getOperationVariables()[i][component.VAR_NAME] + ":" + component.getOperationVariables()[i][component.VAR_VALUE] + ":" + validVarTypes[Integer.parseInt(component.getOperationVariables()[i][component.VAR_KIND])-1]);
		}
		
		//Outputs
		for (int i = 0; i < component.getOutputs().length; i++) {
			auxV = Boolean.parseBoolean(component.getOutputs()[i][component.VAR_VECTOR]);
			auxM = Boolean.parseBoolean(component.getOutputs()[i][component.VAR_MATRIX]);
			if (!auxV && !auxM) {
				auxI = component.VAR_DIMENSION_NONE;
			} else if (auxV && !auxM) {
				auxI = component.VAR_DIMENSION_VECTOR;
			} else {
				auxI = component.VAR_DIMENSION_MATRIX;
			}
			lasVars2.addElement(validJavaTypes[Integer.parseInt(component.getOutputs()[i][component.VAR_TYPE])-1] + ":" + component.DIMENSION[auxI] + ":" + component.getOutputs()[i][component.VAR_NAME] + ":" + component.getOutputs()[i][component.VAR_VALUE] + ":" + validVarTypes[Integer.parseInt(component.getOutputs()[i][component.VAR_KIND])-1]);
		}
		list_1.setModel(lasVars2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object[] options = {"Yes", "No"};
		int n;
		if (arg0.getSource() == btnCancel) {
			n = JOptionPane.showOptionDialog(this, "Do you want to exit modify without saving?", "Cancel modifications", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);	
			if (n == 0) {
				try {
					this.setClosed(true);
					parent.populateComboBx();
					parent.setVisible(true);
					parent.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (arg0.getSource() == btnSave) {
			n = JOptionPane.showOptionDialog(this, "Do you want to save modifications and exit this module?", "Save and exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (n == 0) {
				component.setComponentType(textField_2.getText());
				component.setOutputName(textField_1.getText());
				component.setOutputType(textField_3.getText());
				component.setProcessDataCode(textArea.getText());
				if (new ComponentDataBase().updateComponent(component)) { //actualizar nuevo componente
					try {
						this.setClosed(true);
						parent.populateComboBx();
						parent.setVisible(true);
						parent.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showOptionDialog(this, "A problem ocurred while trying to save modifications\n try restarting this application", "Unable to save modifications", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
				}
			}
		} else if (arg0.getSource() == button) {
			n = JOptionPane.showOptionDialog(this, "Do you want to add a new variable?", "New variable", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (n == 0) {
				addVariable(new String[] {String.valueOf(comboBox.getSelectedIndex()+1), textField_4.getText(), textField_5.getText(), comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString()});
			}
		} else if(arg0.getSource() == button_1) {
			n = JOptionPane.showOptionDialog(this, "You are abou to remove a variable from the list. Do you agree?", "Remove variable", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (n == 0) {
				removeVariable(list_1.getSelectedValue().toString());
			}
		}
	}
	
	private void removeVariable(String var) {
		String[] variable = var.split(":"); //Nombre 2 y kind 4
		ArrayList<String[]> listaVariables;
		if (variable[4].compareTo(validVarTypes[component.LITERALS-1]) == 0) {
			listaVariables = component.getDataLiteralsAsList();
			for (int i = 0; i < listaVariables.size(); i++) {
				if (listaVariables.get(i)[component.VAR_NAME].compareTo(variable[2]) == 0) {
					listaVariables.remove(i);
				}
			}
			component.setDataLiterals(listaVariables);
		} else if (variable[4].compareTo(validVarTypes[component.OP_VARIABLES-1]) == 0) {
			listaVariables = component.getOperationVariablesAsList();
			for (int i = 0; i < listaVariables.size(); i++) {
				if (listaVariables.get(i)[component.VAR_NAME].compareTo(variable[2]) == 0) {
					listaVariables.remove(i);
				}
			}
			component.setOperationVariables(listaVariables);
		} else if (variable[4].compareTo(validVarTypes[component.OUTPUTS-1]) == 0) {
			listaVariables = component.getOutputsAsList();
			for (int i = 0; i < listaVariables.size(); i++) {
				if (listaVariables.get(i)[component.VAR_NAME].compareTo(variable[2]) == 0) {
					listaVariables.remove(i);
				}
			}
			component.setOutputs(listaVariables);
		} else {
			listaVariables = component.getConfigurationVariablesAsList();
			for (int i = 0; i < listaVariables.size(); i++) {
				if (listaVariables.get(i)[component.VAR_NAME].compareTo(variable[2]) == 0) {
					listaVariables.remove(i);
				}
			}
			component.setConfigurationVariables(listaVariables);
		}
		populateForm();
	}
	
	private void addVariable(String[] variable) {
		ArrayList<String[]> listaVariables;
		if (variable[4].compareTo(validVarTypes[component.LITERALS-1]) == 0) {
			listaVariables = component.getDataLiteralsAsList();
			if (variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_NONE]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "false", String.valueOf(component.LITERALS)});
			} else if(variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_VECTOR]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "true", "false", String.valueOf(component.LITERALS)});
			} else {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "true", String.valueOf(component.LITERALS)});
			}
			component.setDataLiterals(listaVariables);
		} else if (variable[4].compareTo(validVarTypes[component.OP_VARIABLES-1]) == 0) {
			listaVariables = component.getOperationVariablesAsList();
			if (variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_NONE]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "false", String.valueOf(component.OP_VARIABLES)});
			} else if(variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_VECTOR]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "true", "false", String.valueOf(component.OP_VARIABLES)});
			} else {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "true", String.valueOf(component.OP_VARIABLES)});
			}
			component.setOperationVariables(listaVariables);
		} else if (variable[4].compareTo(validVarTypes[component.OUTPUTS-1]) == 0) {
			listaVariables = component.getOutputsAsList();
			if (variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_NONE]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "false", String.valueOf(component.OUTPUTS)});
			} else if(variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_VECTOR]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "true", "false", String.valueOf(component.OUTPUTS)});
			} else {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "true", String.valueOf(component.OUTPUTS)});
			}
			component.setOutputs(listaVariables);
		} /*else {
			listaVariables = component.getConfigurationVariablesAsList();
			if (variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_NONE]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "false", String.valueOf(component.CONF_VARIABLES)});
			} else if(variable[3].compareTo(component.DIMENSION[component.VAR_DIMENSION_VECTOR]) == 0) {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "true", "false", String.valueOf(component.CONF_VARIABLES)});
			} else {
				listaVariables.add(new String[] {variable[0], variable[1], variable[2], "false", "true", String.valueOf(component.CONF_VARIABLES)});
			}
			component.setConfigurationVariables(listaVariables);
		}*/
		populateForm();
	}
}
