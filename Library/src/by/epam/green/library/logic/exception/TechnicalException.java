package by.epam.green.library.logic.exception;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public TechnicalException() {
		super();
	}

	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public TechnicalException(String arg0) {
		super(arg0);

	}

	public TechnicalException(Throwable arg0) {
		super(arg0);

	}
}
