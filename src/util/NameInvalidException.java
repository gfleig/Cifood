
package util;

public class NameInvalidException extends ItemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3797234359811694207L;

	public NameInvalidException() {
		super("Login invalido");
	}

	public NameInvalidException(String Message) {
		super(Message);
	}
}
