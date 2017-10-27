package by.tc.task02.main;

import java.util.Map;

import by.tc.task02.entity.Node;
import by.tc.task02.entity.ComplexXmlElement;
import by.tc.task02.entity.SimpleXmlElement;
import by.tc.task02.entity.XmlElement;


public class PrintXMLInfo {
	
	public static void printXml(Node xmlTree) {
		System.out.println("* * * * * DATA * * * * *");
		recursivePrint(xmlTree, "");
	}
	
	private static void recursivePrint(Node node, String start){
		if (node == null){
			return;
		}
		
		XmlElement elem = (XmlElement)node;
		if (elem instanceof SimpleXmlElement){
			simpleElementPrint((SimpleXmlElement)elem);
			return;
		}
		else if (elem instanceof ComplexXmlElement){
			ComplexXmlElement el = (ComplexXmlElement)elem;
			if ((el != null) && (el.getAttributes() != null) && (!el.getAttributes().isEmpty())){
				System.out.println("\n");
				for (Map.Entry<String, String> ell : elem.getAttributes().entrySet()){
					System.out.print(ell.getValue() + "  ");
				}
				System.out.println("\n");
			}
			for (Node childElement : el.getChildElements()){
				recursivePrint(childElement, "  ");
			}
		}
	}
	
	
	private static void simpleElementPrint(Node element){
		SimpleXmlElement simpleElement = (SimpleXmlElement)element;
		if ((simpleElement != null) && (simpleElement.getAttributes() != null)){
			for (Map.Entry<String, String> el : simpleElement.getAttributes().entrySet()){
				System.out.print("   " + el.getValue() + "  ");
			}
		}
		System.out.print("   " + simpleElement.getContent());
	}

}
