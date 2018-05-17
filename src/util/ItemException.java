package util;

public class ItemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9101266191518161188L;

	public ItemException() {
		super("Preço Invalido");
	}

	public ItemException(String message) {
		super(message);
	}

}
