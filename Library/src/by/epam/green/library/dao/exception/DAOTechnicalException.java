package by.epam.green.library.dao.exception;

public class DAOTechnicalException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOTechnicalException() {
		super();
	}

	public DAOTechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public DAOTechnicalException(String arg0) {
		super(arg0);

	}

	public DAOTechnicalException(Throwable arg0) {
		super(arg0);

	}

}
