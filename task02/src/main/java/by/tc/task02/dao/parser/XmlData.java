package by.tc.task02.dao.parser;

import java.util.List;
import java.util.Set;

public class XmlData {

	private List<String> allXmlElements;
	private Set<String> uniqueTags;
	
	public XmlData(){}
	
	public XmlData(List<String> allXmlElements, Set<String> uniqueTags){
		this.allXmlElements = allXmlElements;
		this.uniqueTags = uniqueTags;
	}
	
	public List<String> getAllXmlElements(){
		return allXmlElements;
	}
	
	public Set<String> getUniqueTags(){
		return uniqueTags;
	}
	
}
