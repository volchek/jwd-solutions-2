package by.tc.task02.dao.parser;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	
	final static char OPEN_TAG = '<';
	final static char CLOSE_TAG = '>';
	final static char SLASH_TAG = '/';
	final static char SINGLE_QUOTE = '\'';
	final static char DOUBLE_QUOTE = '\"';
	
	final static String EMPTY_LINE = "";
	static final String START_XML = "\\s*<\\?.+\\?>";
	static final String DOCTYPE_XML = "\\s*<!DOCTYPE.+>";
	static final String COMMENT_XML = "\\s*<!--.+-->";
	static final String SPLIT_REGEX = "(\\s*<(.+?)>(.*?)</(.+?)\\s*>)|(</?(.+?)>)";	

	final static int INNER_OPEN_TAG = 2;
	final static int INNER_CONTENT_TAG = 3;
	final static int INNER_CLOSE_TAG = 4;
	final static int OUTER_TAGS = 6;
	
	final static String clearXml;
	
	static Pattern pattern;
	
	static {
		clearXml = START_XML + "|" + DOCTYPE_XML + "|" + COMMENT_XML;
		pattern = Pattern.compile(SPLIT_REGEX);
	}
	
	
	
	public XmlData splitXml(String inputData){
		
		String clearData = eraseData(inputData);
		XmlData result = getElements(clearData);

		return result;
	}

	
	private String eraseData(String inputData){
		
		String clearData = inputData.replaceAll(clearXml, EMPTY_LINE);
		return clearData;
	}

	
	private XmlData getElements(String inputData){
		
		List<String> tokens = new LinkedList<String>();
		Set<String> uniqueTags = new HashSet<String>();

		Matcher matcher = pattern.matcher(inputData);
		
		while(matcher.find()){
		  addElement(matcher, tokens, uniqueTags, INNER_OPEN_TAG);
		  addElement(matcher, tokens, uniqueTags, INNER_CONTENT_TAG);
		  addElement(matcher, tokens, uniqueTags, INNER_CLOSE_TAG);
		  addElement(matcher, tokens, uniqueTags, OUTER_TAGS);
		}
		
		return new XmlData(tokens, uniqueTags);
	}
	
	
	private void addElement(Matcher matcher, List<String> tags, Set<String> uniqueTags, int idElement){
		
		String token = matcher.group(idElement);
		
		if (token != null){
			tags.add(token);
			
			if (idElement == INNER_OPEN_TAG || idElement == OUTER_TAGS){
				uniqueTags.add(token);
			}
		}
	}	
	
}
