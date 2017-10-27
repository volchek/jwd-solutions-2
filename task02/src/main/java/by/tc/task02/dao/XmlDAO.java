package by.tc.task02.dao;

import by.tc.task02.entity.Node;
import by.tc.task02.dao.exceptions.DaoException;


public interface XmlDAO {
	
	Node getData(String inputFile) throws DaoException;
	
	Node getData() throws DaoException;
	
}
