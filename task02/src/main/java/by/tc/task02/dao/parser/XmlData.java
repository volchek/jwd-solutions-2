package by.tc.task02.dao.parser;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class XmlData implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int PRIME = 31;
	
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
	
	public void setAllXmlElements(List<String> allXmlElements){
		this.allXmlElements = allXmlElements;
	}

	
	public Set<String> getUniqueTags(){
		return uniqueTags;
	}
	
	public void setUniqueTags(Set<String> uniqueTags){
		this.uniqueTags = uniqueTags;
	}

	
	@Override
	public String toString() {
		return "XmlData : \n\t"+ 
					"allXmlElements = " + allXmlElements + 
					", uniqueTags=" + uniqueTags;
	}

	
	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result + ((allXmlElements == null) ? 0 : allXmlElements.hashCode());
		result = PRIME * result + ((uniqueTags == null) ? 0 : uniqueTags.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		
		XmlData other = (XmlData) obj;
		if (allXmlElements == null) {
			if (other.allXmlElements != null){
				return false;
			}
		} else if (!allXmlElements.equals(other.allXmlElements)){
			return false;
		}
		if (uniqueTags == null) {
			if (other.uniqueTags != null)
				return false;
		} else if (!uniqueTags.equals(other.uniqueTags)){
			return false;
		}
		return true;
	}
	
}
