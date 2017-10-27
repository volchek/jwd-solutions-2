package by.tc.task02.dao;

import by.tc.task02.dao.impl.XmlDAOImpl;


public final class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final XmlDAO applianceDAO = new XmlDAOImpl();
	
	private DAOFactory() {}

	
	public XmlDAO getApplianceDAO() {
		return applianceDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
