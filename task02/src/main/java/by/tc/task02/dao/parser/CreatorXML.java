package by.tc.task02.dao.parser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.tc.task02.entity.XmlElement;
import by.tc.task02.entity.ComplexXmlElement;
import by.tc.task02.entity.Node;
import by.tc.task02.entity.SimpleXmlElement;

public class CreatorXML {
	
	private List<String> rawData;
	private Set<String> uniqueTags;
	private Stack<Node> nodeStack = new Stack<Node>();
	private Stack<String> seenTags = new Stack<String>();
		
	
	public CreatorXML() { }
	
	public CreatorXML(XmlData data){
		rawData = data.getAllXmlElements();
		uniqueTags = data.getUniqueTags();
	}
	
	static class SimpleParser {
		
		static final int ATTR_NAME_INDEX = 1;
		static final int ATTR_VALUE_INDEX = 2;
		
		static final String INNER_DELIMITER = "=";
		static final String ATTR_REGEX = "\\s+(.+?)=['\"](.+?)['\"]";
		static final Pattern pattern;
		
		static {
			pattern = Pattern.compile(ATTR_REGEX);
		}
		
		
		static Map<String, String> parseAttribute(String complexTag){
			if (!complexTag.contains(INNER_DELIMITER)){
				return null;
			}
			
			Matcher matcher = pattern.matcher(complexTag); 
			Map<String, String> attributes = new HashMap<String, String>();
			
			while(matcher.find()){
				String nameAttr = matcher.group(ATTR_NAME_INDEX);
				String valueAttr = matcher.group(ATTR_VALUE_INDEX);
				attributes.put(nameAttr, valueAttr);
				System.out.println(nameAttr + " --- " + valueAttr);
			}
			return attributes;	
		}
	}

	
	
	public Node getXML(){

		for (String element : rawData){
			Map<String, String> attr = SimpleParser.parseAttribute(element);
			
			if (uniqueTags.contains(element) && element.contains(" ")){
				String clearElem = element.substring(0, element.indexOf(" "));
				element = clearElem;
				uniqueTags.add(element);
			}

			Node currentNode = new XmlElement(element, attr);
			
			if (nodeStack.isEmpty()){
				nodeStack.push(currentNode);
				seenTags.push(element);
				System.out.println("CREATE FIRST NODE " + element);
			}
			else if (!uniqueTags.contains(element)){
				nodeStack.push(currentNode);
				System.out.println("ADD TEXT " + element);
			}
			else {
				
				String previousTag = seenTags.peek();
				
				if (!element.equals(previousTag)){
					nodeStack.push(currentNode);
					seenTags.push(element);
					System.out.println("ADD SIMPLE TAG "  + element);
				}
				else {
					XmlElement previousNode = (XmlElement)nodeStack.peek();
					String previousNodeType = previousNode.getName();
					if (!uniqueTags.contains(previousNodeType) && !((previousNode instanceof SimpleXmlElement) || (previousNode instanceof ComplexXmlElement)) ){
						
						seenTags.pop();
						nodeStack.pop();
						Map<String, String> currentAttr = ((XmlElement)nodeStack.pop()).getAttributes();
						Node currentElement = new SimpleXmlElement(element, currentAttr, previousNodeType);	
						nodeStack.push(currentElement);
						System.out.println("ADD SIMPLE NODE "  + element + " " + previousNodeType);
					}
					else {
						System.out.println("ADD COMPLEX NODE "  + element);
						createComplexNode(element, attr);
					}
				}
			}
		}
		
		Node res = nodeStack.pop();
		
		return res;
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
		
	
}
