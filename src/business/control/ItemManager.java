package business.control;

import infra.InfraException;
import infra.ItemFile;
import util.NameInvalidException;
import util.PriceInvalidException;
import util.ItemValidador;

import java.util.Map;
//import java.util.logging.Level;

import business.model.Item;

public class ItemManager {

	private Map<String, Item> items;
	ItemFile itemFile;

	public ItemManager() throws InfraException {

		itemFile = new ItemFile();
		items = itemFile.loadItems();

	}

	public void addItem(String[] args) throws NameInvalidException, PriceInvalidException {

		ItemValidador.validateName(args[0]);
		ItemValidador.validatePrice(args[1]);

		items.put(args[0], new Item(args[0], args[1]));
		itemFile.saveItems(items);

	}

	public Map<String, Item> getAllItems() throws InfraException {
		try {
			Map<String, Item> mylist = itemFile.loadItems();
			return mylist;

		} catch (NullPointerException ex) {
			ItemFile.logger.severe(ex.getMessage());
			throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

		}
	}

}
