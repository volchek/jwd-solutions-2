package by.tc.task02.entity;

import java.util.Map;


/*
 * Class for simple XML elements, without other XML elements inside itself
 */


public class SimpleXmlElement extends XmlElement {

	private static final long serialVersionUID = 1L;

	private String content;

	
	/*
	 * Constructors
	 */
	
	public SimpleXmlElement(){}
	
	public SimpleXmlElement(String name, Map<String, String> attributes, String content){
		super(name, attributes);
		this.content = content;
	}
	
	
	/*
	 * Get and set methods
	 */

	public String getContent(){
		return content;
	}
	
	
	public void setContent(String content){
		this.content = content;
	}

	
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
		
		SimpleXmlElement other = (SimpleXmlElement) obj;
		if (content == null) {
			if (other.content != null){
				return false;
			}
		}
		else if (!content.equals(other.content)){
			return false;
		}
		return true;
	}
	

	
	/*
	 * Override methods of class Object
	 */
	
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = getPrime() * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	
	@Override
	public String toString() {
		return "SimpleXmlElement : \n\t" + 
				"Base class: " + super.toString() + 
				" content = " + content;
	}
	
}
