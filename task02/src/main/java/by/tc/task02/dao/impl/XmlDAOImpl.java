package by.tc.task02.dao.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import by.tc.task02.dao.XmlDAO;
import by.tc.task02.dao.exceptions.DaoException;
import by.tc.task02.entity.Node;
import by.tc.task02.dao.parser.Parser;
import by.tc.task02.dao.parser.XmlData;
import by.tc.task02.dao.parser.CreatorXML;


public class XmlDAOImpl implements XmlDAO {
	
	private static final String DEFAULT_FILE_NAME = "task02.xml";
	
	public Node getData(String inputFile) throws DaoException {
		
		String rawData = null;
		try {
			rawData = readFile(inputFile);
		}
		catch(IOException e){
			throw new DaoException("File not found");
		}
			
		if (rawData.isEmpty()){
			throw new DaoException("Issues with input data reading ");
		}
		
		Parser parserXML = new Parser();
		XmlData result = parserXML.splitXml(rawData);
		
		CreatorXML creatorXML = new CreatorXML(result);
		Node xmlTree = creatorXML.getXML();
	
		return xmlTree;

	}

	public Node getData() throws DaoException {
		return getData(getDefaultPath(DEFAULT_FILE_NAME));
	}
	
	
	private String getDefaultPath(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource(fileName).getFile();
	}
	
	
	private String readFile(String inputFile) throws IOException {
		final String START_FILE = "file:";
		final String START_JAR = "jar:";
		final String DELIMITER = "!";
		final String START_SLASH = "/";
		
		Path path = null;
		if (inputFile.startsWith(START_FILE)) {
			inputFile = START_JAR + inputFile;
			String[] array = inputFile.split(DELIMITER);
			FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), new HashMap<String, Object>());
			path = fs.getPath(array[1]);
		}
		else {
			if (inputFile.startsWith(START_SLASH)) { inputFile = inputFile.substring(1); }
			path = Paths.get(inputFile);
		}
		String contents = new String(Files.readAllBytes(path));
		
		return contents;
	}
	
}
