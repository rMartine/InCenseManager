package edu.incense.manager.java.model;

import java.util.ArrayList;


public class ComponentRepository {
	private ArrayList<ComponentStructure> dbRepository;
	private ArrayList<ComponentStructure> ontoRepository;
	private ArrayList<ComponentStructure> componentRepository = new ArrayList<ComponentStructure>();
	private ComponentDataBase componentDB;
	private ComponentOntology componentOnto;
	private String usr;
	private String pwd;
	private String[] validJavaTypes;
	private String[] validVarTypes;
	
	public ComponentRepository(String usr, String pwd) {
		super();
		this.usr = usr;
		this.pwd = pwd;
		
		this.componentDB = new ComponentDataBase(this.usr, this.pwd);
		this.dbRepository = componentDB.getDbComponents();
		
		this.validJavaTypes = componentDB.getValidJavaTypes();
		this.validVarTypes = componentDB.getValidVarTypes();
		
		this.componentOnto = new ComponentOntology(this.validJavaTypes);
		this.ontoRepository = componentOnto.getOntoComponents();
		populateComponentRepository();
	}
	
	public ComponentRepository() {
		this("root","root");
	}
	
	/**
	 * @return the dbRepository
	 */
	public ArrayList<ComponentStructure> getDbRepository() {
		return dbRepository;
	}
	
	public String[] getComponentNames() {
		String[] names = new String[componentRepository.size()];
		int i;
		
		for (i = 0; i < componentRepository.size(); i++) {
			names[i] = componentRepository.get(i).getComponentName();
		}
		return names;
	}
	
	/**
	 * @param dbRepository the dbRepository to set
	 */
	public void setDbRepository(ArrayList<ComponentStructure> dbRepository) {
		this.dbRepository = dbRepository;
	}
	
	/**
	 * @return the validJavaTypes
	 */
	public String[] getValidJavaTypes() {
		return validJavaTypes;
	}
	
	/**
	 * @param validJavaTypes the validJavaTypes to set
	 */
	public void setValidJavaTypes(String[] validJavaTypes) {
		this.validJavaTypes = validJavaTypes;
	}
	
	/**
	 * @return the validVarTypes
	 */
	public String[] getValidVarTypes() {
		return validVarTypes;
	}
	
	/**
	 * @param validVarTypes the validVarTypes to set
	 */
	public void setValidVarTypes(String[] validVarTypes) {
		this.validVarTypes = validVarTypes;
	}
	
	/* Compare the component list between the DB and the Ontology to know if every component in the
	 * Ontology is registered in the databease and enable or disable the flag*/
	private void populateComponentRepository () {
		ComponentStructure tempComponent;
		for (int i = 0; i < ontoRepository.size(); i++) {
			tempComponent = ontoRepository.get(i);
			tempComponent.setDbRegistered(false);
			componentRepository.add(tempComponent);
			for (int j = 0; j < dbRepository.size(); j++) {
				if (ontoRepository.get(i).getComponentName().compareTo(dbRepository.get(j).getComponentName()) == 0) {
					tempComponent = dbRepository.get(j);
					tempComponent.setDbRegistered(true);
					componentRepository.set(i, tempComponent);
				}
			}
		}
	}
	
	/**
	 * @return the componentRepository
	 */
	public ArrayList<ComponentStructure> getComponentRepository() {
		return componentRepository;
	}

	/**
	 * @param componentRepository the componentRepository to set
	 */
	public void setComponentRepository(ArrayList<ComponentStructure> componentRepository) {
		this.componentRepository = componentRepository;
	}
}
