package edu.incense.manager.java.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Project;
import edu.stanford.smi.protege.model.Slot;


public class ComponentOntology {
	public final static int CONF_VARIABLES = 1;
	public final static int OP_VARIABLES = 2;
	public final static int OUTPUTS = 3;
	public final static int LITERALS = 4;
	
	private String ontologyFile;
	private ArrayList<ComponentStructure> ontoComponents;
	private String[] validJavaTypes;
	
	public ComponentOntology(String ontologyFile, String[] validJavaTypes) {
		super();
		this.ontologyFile = ontologyFile;
		this.validJavaTypes = validJavaTypes;
		populateOntStructure();
	}

	public ComponentOntology(String[] validJavaTypes) {
		this ("C:/Users/Roberto/Desktop/InCense Manager Designing/Onto Incense/OntoIncenseVer4.pprj", validJavaTypes);
	}

	/**
	 * @return the ontoComponents
	 */
	public ArrayList<ComponentStructure> getOntoComponents() {
		return ontoComponents;
	}
	
	/**
	 * @param ontoComponents the ontoComponents to set
	 */
	public void setOntoComponents(ArrayList<ComponentStructure> ontoComponents) {
		this.ontoComponents = ontoComponents;
	}
	
	private void populateOntStructure() {
		ontoComponents  = new ArrayList<ComponentStructure>();
		Collection errors = new ArrayList();
        Project project = new Project(ontologyFile, errors);
        if (errors.size() == 0) {
        	KnowledgeBase kb = project.getKnowledgeBase();
        	Cls components = kb.getCls("Component");
        	Iterator i = components.getSubclasses().iterator();
            while (i.hasNext()) {
            	Cls cls = (Cls) i.next();
            	ComponentStructure component = new ComponentStructure ();
            	ArrayList<String[]> confVars = new ArrayList<String[]>();
                component.setComponentName(cls.getName());
                Iterator j = cls.getTemplateSlots().iterator();
                while (j.hasNext()) {
                	Slot slot = (Slot) j.next();
                	if (slot.getName().compareTo("configuration_var") == 0) {
                		Iterator k =  cls.getTemplateSlotAllowedClses(slot).iterator();
                		while (k.hasNext()) {
                			Cls varConf = (Cls) k.next();
                			String[] variable = new String[6];
                			variable[component.VAR_NAME] = varConf.getName();
                			Iterator l = varConf.getTemplateSlots().iterator();
                			while (l.hasNext()) {
                				Slot confSlot = (Slot) l.next();
                				if (confSlot.getName().compareTo("type") == 0)
                					variable[component.VAR_TYPE] = String.valueOf(getJavaTypeId(varConf.getTemplateSlotDefaultValues(confSlot).toString().replace("[", "").replace("]", "")));
                				if (confSlot.getName().compareTo("value") == 0)
                					variable[component.VAR_VALUE] = varConf.getTemplateSlotDefaultValues(confSlot).toString().replace("[", "").replace("]", "");
                				if (confSlot.getName().compareTo("vector") == 0)
                					variable[component.VAR_VECTOR] = varConf.getTemplateSlotDefaultValues(confSlot).toString().replace("[", "").replace("]", "");
                				if (confSlot.getName().compareTo("matriz") == 0)
                					variable[component.VAR_MATRIX] = varConf.getTemplateSlotDefaultValues(confSlot).toString().replace("[", "").replace("]", "");
                			}
                			variable[component.VAR_KIND] = String.valueOf(component.CONF_VARIABLES);
                			confVars.add(variable);
                		}
                	}
                }
                component.setConfigurationVariables(confVars);
                ontoComponents.add(component);
            }
        } else {
        	Iterator i = errors.iterator();
            while (i.hasNext()) {
            	System.out.println("Error: " + i.next());
            }
        }
	}
	
	private int getJavaTypeId(String elTipo) {
		for (int i = 0; i < validJavaTypes.length; i++) {
			if (validJavaTypes[i].compareTo(elTipo) == 0) {
				return (i+1);
			}
		}
		return 1;
	}
}
