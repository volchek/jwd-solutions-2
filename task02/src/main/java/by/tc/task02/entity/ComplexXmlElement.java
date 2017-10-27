package by.tc.task02.entity;

import java.util.List;
import java.util.Map;

/*
 * Class for complex XML elements, containing child XML elements
 */


public class ComplexXmlElement extends XmlElement {

	private static final long serialVersionUID = 1L;

	private List<XmlElement> childElements;

	
	/*
	 * Constructors
	 */
	
	public ComplexXmlElement(){}
	
	public ComplexXmlElement(String name, Map<String, String> attributes, List<XmlElement> childElements){
		super(name, attributes);
		this.childElements = childElements;
	}

	
	/*
	 * Get and set methods
	 */

	public List<XmlElement> getChildElements(){
		return childElements;
	}
	
	public XmlElement getChildElements(int index){
		return childElements.get(index);
	}
	
	public void setElements(List<XmlElement> childElements){
		this.childElements = childElements;
	}

	
	/*
	 * Override methods of class Object
	 */
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (!super.equals(obj)){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		
		ComplexXmlElement other = (ComplexXmlElement) obj;
		if (childElements == null) {
			if (other.childElements != null){
				return false;
			}
		}
		else if (!childElements.equals(other.childElements)){
			return false;
		}
		return true;
	}
	
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = getPrime() * result + ((childElements == null) ? 0 : childElements.hashCode());
		return result;
	}	
	
}
