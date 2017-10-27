package by.tc.task02.main;

import by.tc.task02.entity.Node;
import by.tc.task02.service.XmlService;
import by.tc.task02.service.exceptions.ServiceException;
import by.tc.task02.service.ServiceFactory;
import by.tc.task02.main.PrintXMLInfo;


public class Main {

	public static void main(String[] args) {

		ServiceFactory factory = ServiceFactory.getInstance();
		XmlService service = factory.getApplianceService();
		
		try {
			// You may specify the full path to your XML file as a parameter of getData call
			Node res = service.getData();
			PrintXMLInfo.printXml(res);
		}
		catch(ServiceException err){
			err.printStackTrace();
		}
	}

}
