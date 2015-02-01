package by.epam.green.library.dao.exception;

public class DAOLogicException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOLogicException() {
		super();
	}

	public DAOLogicException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public DAOLogicException(String arg0) {
		super(arg0);

	}

	public DAOLogicException(Throwable arg0) {
		super(arg0);

	}
}
