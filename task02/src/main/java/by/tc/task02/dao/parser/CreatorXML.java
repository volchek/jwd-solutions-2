package by.tc.task02.dao.parser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;


import by.tc.task02.entity.Node;
import by.tc.task02.entity.XmlElement;
import by.tc.task02.entity.ComplexXmlElement;
import by.tc.task02.entity.SimpleXmlElement;
import by.tc.task02.dao.parser.AttributeParser;


public class CreatorXML {
	
	static final int START_POSITION = 0;
	static final String SPACE_TAG_DELIMITER = " ";
	
	private List<String> rawData;
	private Set<String> uniqueTags;
	private Stack<Node> nodeStack;
	private Stack<String> seenTags;
		
	
	public CreatorXML() { 
		nodeStack = new Stack<Node>();
		seenTags = new Stack<String>();
	}
	
	public CreatorXML(XmlData data){
		this();
		rawData = data.getAllXmlElements();
		uniqueTags = data.getUniqueTags();
		nodeStack = new Stack<Node>();
	}
	
	
	/*
	 * Main method 
	 */
	public Node getXML(){

		for (String element : rawData){

			Map<String, String> attr = AttributeParser.parseAttribute(element);
			
			element = getClearTag(element);
			
			Node currentNode = new XmlElement(element, attr);
			
			if (nodeStack.isEmpty()){	/* add first XML element to Stack */
				nodeStack.push(currentNode);
				seenTags.push(element);
			}
			else if (!uniqueTags.contains(element)){
				nodeStack.push(currentNode);	/* add text content to Stack */
			}
			else {
				createNewNode(element, currentNode, attr);	/* add new XML element to Stack */
			}
		}
		
		Node fullXmlTree = nodeStack.pop();
		
		return fullXmlTree;
	}
	
	
	/*
	 * Auxiliary method. If an currentElement isn't equal to the previous tag,
	 * add it to the both stacks; else create a simple or complex XML element 
	 * using previous information in nodeStack
	 */
	private void createNewNode(String currentElement, Node currentNode, Map<String, String> attr){
		String previousTag = seenTags.peek();
		
		if (!currentElement.equals(previousTag)){
			nodeStack.push(currentNode);
			seenTags.push(currentElement);
		}
		else {
			XmlElement previousNode = (XmlElement)nodeStack.peek();
			String previousNodeType = previousNode.getName();
			if (!uniqueTags.contains(previousNodeType) && 
					!((previousNode instanceof SimpleXmlElement) 
					|| (previousNode instanceof ComplexXmlElement)) ){
				createSimpleNode(currentElement, previousNodeType);
			}
			else {
				createComplexNode(currentElement, attr);
			}
		}
	}
	
	
	private void createComplexNode(String currentElement, Map<String, String> attributes){
		List<XmlElement> childElements = new LinkedList<XmlElement>();
		
		while (true){
			XmlElement previousNode = (XmlElement)nodeStack.pop();
			String previousNodeType = previousNode.getName();
			
			if (currentElement.equals(previousNodeType)){
				Map<String, String> currentAttributes = previousNode.getAttributes();
				Collections.reverse(childElements);
				Node complexNode = new ComplexXmlElement(currentElement, currentAttributes, childElements);
				nodeStack.push(complexNode);
				seenTags.pop();
				break;
			}
			
			childElements.add(previousNode);
		}
	}
	
	
	private void createSimpleNode(String currentElement, String previousNodeType){
		
		seenTags.pop();
		nodeStack.pop();
		
		Map<String, String> currentAttr = ((XmlElement)nodeStack.pop()).getAttributes();
		Node newElement = new SimpleXmlElement(currentElement, currentAttr, previousNodeType);
		
		nodeStack.push(newElement);
	
	}

	
	private String getClearTag(String rawElement){
		if (uniqueTags.contains(rawElement) && rawElement.contains(SPACE_TAG_DELIMITER)){
			String clearElem = rawElement.substring(START_POSITION, rawElement.indexOf(SPACE_TAG_DELIMITER));
			rawElement = clearElem;
			uniqueTags.add(rawElement);
			return clearElem;
		}
		
		return rawElement;
	}
	
}
