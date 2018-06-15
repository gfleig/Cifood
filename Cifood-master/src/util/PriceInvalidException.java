
package util;

public class PriceInvalidException extends ItemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4081633597571256549L;

	public PriceInvalidException() {
		super("Password Invalido");
	}

	public PriceInvalidException(String Message) {
		super(Message);
	}
}
