package edu.incense.manager.java.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;


public class ProjectTree {
    private String ontoFile;
    private DynamicTree treePanel;
    private Node docRootNode;
    private DefaultMutableTreeNode treeRoot;

    public ProjectTree(String ontoFile, DynamicTree treePanel){
    	this.ontoFile = ontoFile;
    	this.treePanel = treePanel;
    	this.clearTree();
    }
    
    public boolean parseProject () {
    	try {    
    		this.clearTree();
    		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    		Document doc = dBuilder.parse(this.ontoFile);
    		doc.getDocumentElement().normalize();
    		docRootNode = doc.getDocumentElement();
    		treeRoot = treePanel.addObject(null, docRootNode.getNodeName());
    		populateTree (docRootNode, treeRoot);
    		return true;
		}catch(Exception ex){
			this.treePanel.addObject(null, ex.getMessage());
			return false;
		}
    }
    
    public void populateTree (Node xmlParent, DefaultMutableTreeNode treeParent) {
    	int i;
    	
    	DefaultMutableTreeNode child;
    	
    	NodeList nodeList = xmlParent.getChildNodes();
    	for (i=0; i < nodeList.getLength(); i++) {
    		if (nodeList.item(i).getNodeType() == Node.TEXT_NODE) {
    			if (!(nodeList.item(i).getNodeValue().contains("\n") || nodeList.item(i).getNodeValue().contains("\t"))){
    				child = treePanel.addObject(treeParent, nodeList.item(i).getNodeValue());
    				populateTree (nodeList.item(i), child);
    			}
    		} else if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
    			child = treePanel.addObject(treeParent, nodeList.item(i).getNodeName());
	    		populateTree (nodeList.item(i), child);
    		}
    		
    	}
    }
    
    public void clearTree() {
    	treePanel.clear();
    }
    
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}