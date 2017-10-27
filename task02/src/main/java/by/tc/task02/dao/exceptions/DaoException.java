package by.tc.task02.dao.exceptions;


public class DaoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public DaoException() { }

	public DaoException(String message) {
		super(message);
	}
	
}
