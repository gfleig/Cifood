package util;

public class ItemValidador {
	public static void validateName(String name) throws NameInvalidException {

		if (name.length() > 40)
			throw new NameInvalidException("O nome do item não pode conter mais de 40 caracteres!\n");
		else if (name.length() < 4)
			throw new NameInvalidException("O nome do item precisa ter, no mínimo, 4 caracteres!\n");
		else if (name.length() == 0)
			throw new NameInvalidException("O nome do item não pode ser vazio!\n");		
	}

	public static void validatePrice(String price) throws PriceInvalidException {

		if (price.length() > 12)
			throw new PriceInvalidException("O preço não pode possuir mais de 12 caracteres!\n");		
		else if (!price.matches("\\d{1,}[\\.\\,]\\d{2}"))
			throw new PriceInvalidException("O preço do item deve ser declarado no formato mínimo X...X[. ou ,]XX!");
	}
}
