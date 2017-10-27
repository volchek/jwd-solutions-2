package by.tc.task02.main;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.tc.task02.entity.Node;
import by.tc.task02.entity.ComplexXmlElement;
import by.tc.task02.entity.SimpleXmlElement;
import by.tc.task02.entity.XmlElement;


public class PrintXMLInfo {
	
	static final String DELIMITER = "\n";
	static StringBuilder prefix = new StringBuilder("");
	
	public static void printXml(Node xmlTree) {
		System.out.println("* * * * * DATA * * * * *");
		StringBuilder result = new StringBuilder();
		result = recursivePrint(xmlTree, result, prefix);
		String finishString = clearString(result);
		System.out.println(finishString);
	}
	
	
	
	private static StringBuilder recursivePrint(Node node, StringBuilder result, StringBuilder prefix){
		
		if (node == null){
			return result;
		}
		
		XmlElement elem = (XmlElement)node;
		
		if (elem instanceof SimpleXmlElement){
			simpleElementPrint((SimpleXmlElement)elem, result, prefix);
			return result;
		}
		else if (elem instanceof ComplexXmlElement){
			complexElementPrint(elem, result, prefix);
		}
		
		return result;
	}
	
	
	
	private static void simpleElementPrint(Node element, StringBuilder result, StringBuilder prefix){
		
		SimpleXmlElement simpleElement = (SimpleXmlElement)element;
		
		if ((simpleElement != null) && (simpleElement.getAttributes() != null)){
			for (Map.Entry<String, String> el : simpleElement.getAttributes().entrySet()){
				result.append(prefix);
				result.append(el.getValue());
			}
		}
		
		result.append(prefix + simpleElement.getContent());
	}
	

	
	private static void complexElementPrint(Node element, StringBuilder result, StringBuilder prefix){
		
		prefix.setLength(0);
		result.append(DELIMITER);
		
		ComplexXmlElement el = (ComplexXmlElement)element;
		
		if ((el != null) && (el.getAttributes() != null) && (!el.getAttributes().isEmpty())){
			result.append(DELIMITER);
			result.append(prefix);
			for (Map.Entry<String, String> ell : el.getAttributes().entrySet()){
				result.append(ell.getValue() + "  ");
			}
			result.append(DELIMITER);
		}
		
		for (Node childElement : el.getChildElements()){
			result = recursivePrint(childElement, result, prefix.append("  "));
		}
	}
	
	
	private static String clearString(StringBuilder str){
		Pattern p = Pattern.compile("(\n)+");
		Matcher m = p.matcher(str);
		String clearStr = m.replaceAll("\n");
		return clearStr;
	}
	
}
