package by.tc.task02.service;

import by.tc.task02.service.impl.XmlServiceImpl;

public final class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();

	private final XmlService applianceService = new XmlServiceImpl();
	
	private ServiceFactory() {}

	public XmlService getApplianceService() {
		return applianceService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
