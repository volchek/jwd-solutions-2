package by.tc.task02.entity;

import java.util.Map;


/*
 * Base class for one XML element, containing tag name and attributes list
 */

public class XmlElement implements Node, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final int PRIME = 31;

	
	private String name;
	private Map<String, String> attributes;
	
	
	/*
	 * Constructors
	 */
	
	public XmlElement() { }
	
	public XmlElement(String name, Map<String, String> attributes){
		this.name = name;
		this.attributes = attributes;
	}
	

	
	/*
	 * Get and set methods
	 */

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Map<String, String> getAttributes(){
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes){
		this.attributes = attributes;
	}
	
	public static int getPrime(){
		return PRIME;
	}

	
	/*
	 * Override methods of class Object
	 */
	
	
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
		
		XmlElement other = (XmlElement) obj;
		if (attributes == null) {
			if (other.attributes != null){
				return false;
			}
		} else if (!attributes.equals(other.attributes)){
			return false;
		}
		if (name == null) {
			if (other.name != null){
				return false;
			}
		} else if (!name.equals(other.name)){
			return false;
		}
		return true;
	}

	
	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
	@Override
	public String toString() {
		return "XmlElement : \n\tname=" + name + ", attributes=" + attributes;
	}
	
}
