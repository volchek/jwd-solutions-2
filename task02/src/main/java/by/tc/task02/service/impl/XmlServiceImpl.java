package by.tc.task02.service.impl;

import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.XmlDAO;
import by.tc.task02.dao.exceptions.DaoException;
import by.tc.task02.entity.Node;
import by.tc.task02.service.XmlService;
import by.tc.task02.service.exceptions.ServiceException;

public class XmlServiceImpl implements XmlService {
	
	@Override
	public Node getData(String inputFile) throws ServiceException {

		DAOFactory factory = DAOFactory.getInstance();
		XmlDAO xmlDAO = factory.getApplianceDAO();

		try {
			Node xmlTree = null;
			if (inputFile.isEmpty()) {
				xmlTree = xmlDAO.getData();
			}
			else {
				xmlTree = xmlDAO.getData(inputFile);
			}
			return xmlTree;
		}
		catch(DaoException ex){
			throw new ServiceException();
		}
	}
	
	
	@Override
	public Node getData() throws ServiceException {
		
		return getData("");
	}
	
	
}
