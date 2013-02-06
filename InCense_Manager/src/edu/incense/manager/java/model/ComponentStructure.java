package edu.incense.manager.java.model;

import java.util.ArrayList;

public class ComponentStructure {
	public final int VAR_TYPE = 0;
	public final int VAR_NAME = 1;
	public final int VAR_VALUE = 2;
	public final int VAR_VECTOR = 3;
	public final int VAR_MATRIX = 4;
	public final int VAR_KIND = 5;
	
	public final int CONF_VARIABLES = 1;
	public final int OP_VARIABLES = 2;
	public final int OUTPUTS = 3;
	public final int LITERALS = 4;
	
	public final String[] DIMENSION = {"None", "Vector", "Matrix"};
	public final int VAR_DIMENSION_NONE = 0;
	public final int VAR_DIMENSION_VECTOR = 1;
	public final int VAR_DIMENSION_MATRIX = 2;
	
	private int componentId; //Id Necesario para recuperar las variables
	private boolean dbRegistered; //Saber si ya está registrado en la base de datos;
	private String componentName; //Nombre del filtro
	private String componentType; //Equivalente al TaskType
	private String processDataCode; /*Archivo que contiene el metodo "public processData" y los que sean necesarios para procesar los datos
		 							 *Será necesario cargarlo en tiempo de ejecución a esta variable
		 							 */
	private String outputName;
	private String outputType;

	private ArrayList <String[]> configurationVariables = new ArrayList <String[]>(); //Variables para configurar el filtro o personalizarlo matriz de 2 x N {tipo, variable}
	private ArrayList <String[]> operationVariables = new ArrayList <String[]>(); /* Variables que son utilizadas durante el procesamiento de datos pero 
											 									   * no son necesarias para caracterizar al usuario o configurar el filtro y sus respectivos valores
											 									   * de inicialización matriz de 3 x N {tipo, variable, valor}
											 									   */
	private ArrayList <String[]> outputs = new ArrayList <String[]>(); /*Las salidas del filtro después de procesar los datos*/
	private ArrayList <String[]> dataLiterals = new ArrayList <String[]>();
	
	public ComponentStructure(int componentId, String componentName,
			String componentType, String processDataCode, String outputName,
			String outputType, ArrayList<String[]> configurationVariables,
			ArrayList<String[]> operationVariables,
			ArrayList<String[]> outputs, ArrayList<String[]> dataLiterals) {
		super();
		this.componentId = componentId;
		this.componentName = componentName;
		this.componentType = componentType;
		this.processDataCode = processDataCode;
		this.outputName = outputName;
		this.outputType = outputType;
		this.configurationVariables = configurationVariables;
		this.operationVariables = operationVariables;
		this.outputs = outputs;
		this.dataLiterals = dataLiterals;
	}
	
	public ComponentStructure() {
		super();
	}

	/**
	 * @return the componentId
	 */
	public int getComponentId() {
		return componentId;
	}

	/**
	 * @param componentId the componentId to set
	 */
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * @return the componentType
	 */
	public String getComponentType() {
		return componentType;
	}

	/**
	 * @param componentType the componentType to set
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	/**
	 * @return the processDataCode
	 */
	public String getProcessDataCode() {
		return processDataCode;
	}

	/**
	 * @param processDataCode the processDataCode to set
	 */
	public void setProcessDataCode(String processDataCode) {
		this.processDataCode = processDataCode;
	}

	/**
	 * @return the outputName
	 */
	public String getOutputName() {
		return outputName;
	}

	/**
	 * @param outputName the outputName to set
	 */
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	/**
	 * @return the outputType
	 */
	public String getOutputType() {
		return outputType;
	}

	/**
	 * @param outputType the outputType to set
	 */
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	/**
	 * @return the configurationVariables
	 */
	public String[][] getConfigurationVariables() {
		String [][] vars = new String[configurationVariables.size()][6];
		int i;
		
		for (i=0; i < configurationVariables.size(); i++) {
			vars[i][VAR_TYPE] = configurationVariables.get(i)[VAR_TYPE];
			vars[i][VAR_NAME] = configurationVariables.get(i)[VAR_NAME];
			vars[i][VAR_VALUE] = configurationVariables.get(i)[VAR_VALUE];
			vars[i][VAR_VECTOR] = configurationVariables.get(i)[VAR_VECTOR];
			vars[i][VAR_MATRIX] = configurationVariables.get(i)[VAR_MATRIX];
			vars[i][VAR_KIND] = configurationVariables.get(i)[VAR_KIND];
		}
		return vars;
	}
	
	public ArrayList<String[]> getConfigurationVariablesAsList() {
		return configurationVariables;
	}

	/**
	 * @param configurationVariables the configurationVariables to set
	 */
	public void setConfigurationVariables(ArrayList<String[]> configurationVariables) {
		this.configurationVariables = configurationVariables;
	}

	/**
	 * @return the operationVariables
	 */
	public String[][] getOperationVariables() {
		String [][] vars = new String[operationVariables.size()][6];
		int i;
		
		for (i=0; i < operationVariables.size(); i++) {
			vars[i][VAR_TYPE] = operationVariables.get(i)[VAR_TYPE];
			vars[i][VAR_NAME] = operationVariables.get(i)[VAR_NAME];
			vars[i][VAR_VALUE] = operationVariables.get(i)[VAR_VALUE];
			vars[i][VAR_VECTOR] = operationVariables.get(i)[VAR_VECTOR];
			vars[i][VAR_MATRIX] = operationVariables.get(i)[VAR_MATRIX];
			vars[i][VAR_KIND] = operationVariables.get(i)[VAR_KIND];
		}
		return vars;
	}
	
	public ArrayList<String[]> getOperationVariablesAsList() {
		return operationVariables;
	}

	/**
	 * @param operationVariables the operationVariables to set
	 */
	public void setOperationVariables(ArrayList<String[]> operationVariables) {
		this.operationVariables = operationVariables;
	}

	/**
	 * @return the outputs
	 */
	public String[][] getOutputs() {
		String [][] vars = new String[outputs.size()][6];
		int i;
		
		for (i=0; i < outputs.size(); i++) {
			vars[i][VAR_TYPE] = outputs.get(i)[VAR_TYPE];
			vars[i][VAR_NAME] = outputs.get(i)[VAR_NAME];
			vars[i][VAR_VALUE] = outputs.get(i)[VAR_VALUE];
			vars[i][VAR_VECTOR] = outputs.get(i)[VAR_VECTOR];
			vars[i][VAR_MATRIX] = outputs.get(i)[VAR_MATRIX];
			vars[i][this.VAR_KIND] = outputs.get(i)[VAR_KIND];
		}
		return vars;
	}
	
	public ArrayList<String[]> getOutputsAsList() {
		return outputs;
	}

	/**
	 * @param outputs the outputs to set
	 */
	public void setOutputs(ArrayList<String[]> outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the dataLiterals
	 */
	public String[][] getDataLiterals() {
		String [][] vars = new String[dataLiterals.size()][6];
		int i;
		
		for (i=0; i < dataLiterals.size(); i++) {
			vars[i][VAR_TYPE] = dataLiterals.get(i)[VAR_TYPE];
			vars[i][VAR_NAME] = dataLiterals.get(i)[VAR_NAME];
			vars[i][VAR_VALUE] = dataLiterals.get(i)[VAR_VALUE];
			vars[i][VAR_VECTOR] = dataLiterals.get(i)[VAR_VECTOR];
			vars[i][VAR_MATRIX] = dataLiterals.get(i)[VAR_MATRIX];
			vars[i][VAR_KIND] = dataLiterals.get(i)[VAR_KIND];
		}
		return vars;
	}
	
	public ArrayList<String[]> getDataLiteralsAsList() {
		return dataLiterals;
	}

	/**
	 * @param dataLiterals the dataLiterals to set
	 */
	public void setDataLiterals(ArrayList<String[]> dataLiterals) {
		this.dataLiterals = dataLiterals;
	}
	
	/**
	 * @return the dbRegistered
	 */
	public boolean isDbRegistered() {
		return dbRegistered;
	}

	/**
	 * @param dbRegistered the dbRegistered to set
	 */
	public void setDbRegistered(boolean dbRegistered) {
		this.dbRegistered = dbRegistered;
	}
}
