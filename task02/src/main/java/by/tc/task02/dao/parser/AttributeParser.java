package by.tc.task02.dao.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AttributeParser {
		
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
		}
		return attributes;	
	}
}
