package edu.incense.manager.java.model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class ComponentDataBase {
	public final static int CONF_VARIABLES = 1;
	public final static int OP_VARIABLES = 2;
	public final static int OUTPUTS = 3;
	public final static int LITERALS = 4;
	
	private String serverUrl;
	private String user;
	private String paswd;
	private ArrayList<ComponentStructure> dbComponents;
	private ArrayList<String> validJavaTypes = new ArrayList<String>(); //Los tipos de datos válidos para cada uno de las variables de un filtro
	private ArrayList<String> validVarTypes = new ArrayList<String>(); //Para determinar si es una variable Configuración de filtro, Operación (Globales), Outputs o Literales
	
	public ComponentDataBase(String serverUrl, String user, String paswd) {
		super();
		this.serverUrl = serverUrl;
		this.user = user;
		this.paswd = paswd;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateVars();
	}
	
	public ComponentDataBase(String user, String paswd) {
		this("jdbc:mysql://localhost:3306/mysql", user, paswd);
	}
	
	public ComponentDataBase() {
		this("jdbc:mysql://localhost:3306/mysql", "root", "root");
	}
	
	private ResultSet executeSQL(String query) {
		try {
			Connection cnX =  DriverManager.getConnection(this.serverUrl, this.user, this.paswd);
			Statement stmnt = cnX.createStatement();
			return stmnt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void populateVars() {
		ResultSet rsX = executeSQL("Select * From incense.ValidJavaTypes Order by validJavaTypesId asc");
		try {
			while(rsX.next()) {
				this.validJavaTypes.add(rsX.getString(3));
			}
			rsX.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rsX = executeSQL("Select * From incense.ValidVarTypes Order by validVarTypesId asc");
		try {
			while(rsX.next()) {
				this.validVarTypes.add(rsX.getString(2));
			}
			rsX.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean updateComponent (ComponentStructure component) {
		boolean success = false;
		String query;
		if (component.isDbRegistered()) {
			query = "Update incense.UserComponents " + 
				    "Set outputName = '" + component.getOutputName() + "', " +
				   	   "outputType = '" +  component.getOutputType() + "', " +
				   	   "componentType = '" +  component.getComponentType() + "', " +
				   	   "processDataCode = '" + component.getProcessDataCode() + "' " +
			   	    "Where userComponentsId = " + component.getComponentId();
			updateSQL (query);
			//Actualizando Vars
			updateSQL("Delete From incense.Variables Where componentId = " + component.getComponentId());
			
			//Configuration
			/*for (int i = 0; i < component.getConfigurationVariables().length; i++) {
				query = "Insert Into incense.Variables (componentId, " +
														"javaType, " +
														"name, " +
														"value," +
														"vectorFlag, " +
														"matrixFlag, " +
														"kind) " +
						"Values (" + component.getComponentId() + ", " +
								component.getConfigurationVariables()[i][component.VAR_TYPE] + ", '" +
								component.getConfigurationVariables()[i][component.VAR_NAME] + "', '" +
								component.getConfigurationVariables()[i][component.VAR_VALUE] + "', " +
								component.getConfigurationVariables()[i][component.VAR_VECTOR] + ", " +
								component.getConfigurationVariables()[i][component.VAR_MATRIX] + ", " +
								component.getConfigurationVariables()[i][component.VAR_KIND] + ")";
				updateSQL(query);
			}*/
			
			//Literals
			for (int i = 0; i < component.getDataLiterals().length; i++) {
				query = "Insert Into incense.Variables (componentId, " +
						"javaType, " +
						"name, " +
						"value," +
						"vectorFlag, " +
						"matrixFlag, " +
						"kind) " +
				"Values (" + component.getComponentId() + ", " +
						component.getDataLiterals()[i][component.VAR_TYPE] + ", '" +
						component.getDataLiterals()[i][component.VAR_NAME] + "', '" +
						component.getDataLiterals()[i][component.VAR_VALUE] + "', " +
						component.getDataLiterals()[i][component.VAR_VECTOR] + ", " +
						component.getDataLiterals()[i][component.VAR_MATRIX] + ", " +
						component.getDataLiterals()[i][component.VAR_KIND] + ")";
				updateSQL(query);
			}
			
			//Operation
			for (int i = 0; i < component.getOperationVariables().length; i++) {
				query = "Insert Into incense.Variables (componentId, " +
						"javaType, " +
						"name, " +
						"value," +
						"vectorFlag, " +
						"matrixFlag, " +
						"kind) " +
				"Values (" + component.getComponentId() + ", " +
						component.getOperationVariables()[i][component.VAR_TYPE] + ", '" +
						component.getOperationVariables()[i][component.VAR_NAME] + "', '" +
						component.getOperationVariables()[i][component.VAR_VALUE] + "', " +
						component.getOperationVariables()[i][component.VAR_VECTOR] + ", " +
						component.getOperationVariables()[i][component.VAR_MATRIX] + ", " +
						component.getOperationVariables()[i][component.VAR_KIND] + ")";
				updateSQL(query);
			}
			
			//Outputs
			for (int i = 0; i < component.getOutputs().length; i++) {
				query = "Insert Into incense.Variables (componentId, " +
						"javaType, " +
						"name, " +
						"value," +
						"vectorFlag, " +
						"matrixFlag, " +
						"kind) " +
				"Values (" + component.getComponentId() + ", " +
						component.getOutputs()[i][component.VAR_TYPE] + ", '" +
						component.getOutputs()[i][component.VAR_NAME] + "', '" +
						component.getOutputs()[i][component.VAR_VALUE] + "', " +
						component.getOutputs()[i][component.VAR_VECTOR] + ", " +
						component.getOutputs()[i][component.VAR_MATRIX] + ", " +
						component.getOutputs()[i][component.VAR_KIND] + ")";
				updateSQL(query);
			}
			success = true;
		} else {
			query = "Insert into incense.UserComponents (componentName, " +
														"componentType, " +
														"outputName, " +
														"outputType, " +
														"processDataCode) " +
					"Values ('" + component.getComponentName() + "', " +
							"'" + component.getComponentType() + "', " +
							"'" + component.getOutputName() + "', " +
							"'" + component.getOutputType() + "', " +
							"'" + component.getProcessDataCode() + "')";
			updateSQL(query);
			updateSQL("Insert into incense.BaseDataTypes (name) Values ('" + component.getOutputType() + "')");
			updateSQL("Insert into incense.BaseTaskTypes (name) Values ('" + component.getComponentType() + "')");
			ResultSet maxId = executeSQL("Select Max(userComponentsId) From incense.UserComponents");
			try {
				if (maxId.next()) {
					//Configuration
					for (int i = 0; i < component.getConfigurationVariables().length; i++) {
						query = "Insert Into incense.Variables (componentId, " +
																"javaType, " +
																"name, " +
																"value," +
																"vectorFlag, " +
																"matrixFlag, " +
																"kind) " +
								"Values (" + maxId.getInt(1) + ", " +
										component.getConfigurationVariables()[i][component.VAR_TYPE] + ", '" +
										component.getConfigurationVariables()[i][component.VAR_NAME] + "', '" +
										component.getConfigurationVariables()[i][component.VAR_VALUE] + "', " +
										component.getConfigurationVariables()[i][component.VAR_VECTOR] + ", " +
										component.getConfigurationVariables()[i][component.VAR_MATRIX] + ", " +
										component.getConfigurationVariables()[i][component.VAR_KIND] + ")";
						updateSQL(query);
					}
					
					//Literals
					for (int i = 0; i < component.getDataLiterals().length; i++) {
						query = "Insert Into incense.Variables (componentId, " +
								"javaType, " +
								"name, " +
								"value," +
								"vectorFlag, " +
								"matrixFlag, " +
								"kind) " +
						"Values (" + maxId.getInt(1) + ", " +
								component.getDataLiterals()[i][component.VAR_TYPE] + ", '" +
								component.getDataLiterals()[i][component.VAR_NAME] + "', '" +
								component.getDataLiterals()[i][component.VAR_VALUE] + "', " +
								component.getDataLiterals()[i][component.VAR_VECTOR] + ", " +
								component.getDataLiterals()[i][component.VAR_MATRIX] + ", " +
								component.getDataLiterals()[i][component.VAR_KIND] + ")";
						updateSQL(query);
					}
					
					//Operation
					for (int i = 0; i < component.getOperationVariables().length; i++) {
						query = "Insert Into incense.Variables (componentId, " +
								"javaType, " +
								"name, " +
								"value," +
								"vectorFlag, " +
								"matrixFlag, " +
								"kind) " +
						"Values (" + maxId.getInt(1) + ", " +
								component.getOperationVariables()[i][component.VAR_TYPE] + ", '" +
								component.getOperationVariables()[i][component.VAR_NAME] + "', '" +
								component.getOperationVariables()[i][component.VAR_VALUE] + "', " +
								component.getOperationVariables()[i][component.VAR_VECTOR] + ", " +
								component.getOperationVariables()[i][component.VAR_MATRIX] + ", " +
								component.getOperationVariables()[i][component.VAR_KIND] + ")";
						updateSQL(query);
					}
					
					//Outputs
					for (int i = 0; i < component.getOutputs().length; i++) {
						query = "Insert Into incense.Variables (componentId, " +
								"javaType, " +
								"name, " +
								"value," +
								"vectorFlag, " +
								"matrixFlag, " +
								"kind) " +
						"Values (" + maxId.getInt(1) + ", " +
								component.getOutputs()[i][component.VAR_TYPE] + ", '" +
								component.getOutputs()[i][component.VAR_NAME] + "', '" +
								component.getOutputs()[i][component.VAR_VALUE] + "', " +
								component.getOutputs()[i][component.VAR_VECTOR] + ", " +
								component.getOutputs()[i][component.VAR_MATRIX] + ", " +
								component.getOutputs()[i][component.VAR_KIND] + ")";
						updateSQL(query);
					}
				}
				success = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}
	
	private int updateSQL(String query) {
		try {
			Connection cnX =  DriverManager.getConnection(this.serverUrl, this.user, this.paswd);
			Statement stmnt = cnX.createStatement();
			return stmnt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<ComponentStructure> getDbComponents() {
		dbComponents = new ArrayList<ComponentStructure>();
		ResultSet components;
		ResultSet variables;
		ComponentStructure component;
		
		ArrayList<String[]> conVar = new ArrayList<String[]>();
		ArrayList<String[]> opeVar = new ArrayList<String[]>();
		ArrayList<String[]> outVar = new ArrayList<String[]>();
		ArrayList<String[]> litVar = new ArrayList<String[]>();
		
		components = executeSQL ("Select userComponentsId, componentName, componentType, outputName, outputType, processDataCode From incense.userComponents order by userComponentsId Asc");
		try {
			while(components.next()) {
				String sqlQuery = "Select incense.variables.javaType, " +
								  		 "incense.variables.name, " +
								  		 "incense.variables.value, " +
								  		 "incense.variables.vectorFlag, " +
								  		 "incense.variables.matrixFlag, " + 
								  		 "incense.variables.kind " +
						  		  "From incense.variables " +
								  "Where incense.variables.componentId = " + components.getInt(1);
				variables = executeSQL (sqlQuery);
				component = new ComponentStructure();
				component.setComponentId(components.getInt(1));
				component.setComponentName(components.getString(2));
				component.setComponentType(components.getString(3));
				component.setOutputName(components.getString(4));
				component.setOutputType(components.getString(5));
				component.setProcessDataCode(components.getString(6));
				while (variables.next()) {
					switch (variables.getInt(6)) {
						case CONF_VARIABLES :
							conVar.add(new String[] {variables.getString(1), variables.getString(2), variables.getString(3), String.valueOf(variables.getBoolean(4)), String.valueOf(variables.getBoolean(5)), String.valueOf(variables.getInt(6))});
							break;
						case OP_VARIABLES :
							opeVar.add(new String[] {variables.getString(1), variables.getString(2), variables.getString(3), String.valueOf(variables.getBoolean(4)), String.valueOf(variables.getBoolean(5)), String.valueOf(variables.getInt(6))});
							break;
						case OUTPUTS :
							outVar.add(new String[] {variables.getString(1), variables.getString(2), variables.getString(3), String.valueOf(variables.getBoolean(4)), String.valueOf(variables.getBoolean(5)), String.valueOf(variables.getInt(6))});
							break;
						case LITERALS :
							litVar.add(new String[] {variables.getString(1), variables.getString(2), variables.getString(3), String.valueOf(variables.getBoolean(4)), String.valueOf(variables.getBoolean(5)), String.valueOf(variables.getInt(6))});
							break;
					}
				}
				component.setConfigurationVariables(conVar);
				component.setOperationVariables(opeVar);
				component.setOutputs(outVar);
				component.setDataLiterals(litVar);
				dbComponents.add(component);
			}
			components.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbComponents;
	}
	
	public String getTypeDeclaration (String type, boolean vec, boolean mat) {
		if ((!vec && !mat)){
			return type;
		} else if (vec && !mat) {
			return type + "[]";
		} else {
			return type + "[][]";
		}
	}
	
	public String[] getValidJavaTypes () {
		String[] types = new String [validJavaTypes.size()];
		int i;
		
		for (i = 0; i < validJavaTypes.size(); i++) {
			types[i] = validJavaTypes.get(i);
		}
		return types;
	}
	
	public String[] getValidVarTypes() {
		String[] types = new String [validVarTypes.size()];
		int i;
		
		for (i = 0; i < validVarTypes.size(); i++) {
			types[i] = validVarTypes.get(i);
		}
		return types;
	}
}
