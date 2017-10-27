package by.tc.task02.service;

import by.tc.task02.entity.Node;
import by.tc.task02.service.exceptions.ServiceException;


public interface XmlService {	
	
	Node getData(String inputFile) throws ServiceException ;
	
	Node getData() throws ServiceException ;
	
}
