package infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Item;

public class ItemFile {

	public static Logger logger = Logger.getLogger(ItemFile.class.getName());

	public ItemFile() {

		try {

			Handler hdConsole = new ConsoleHandler();
			Handler hdArquivo = new FileHandler("relatorioLog.txt");

			hdConsole.setLevel(Level.ALL);
			hdArquivo.setLevel(Level.ALL);

			ItemFile.logger.addHandler(hdConsole);
			ItemFile.logger.addHandler(hdArquivo);

			ItemFile.logger.setUseParentHandlers(false);

		} catch (IOException ex) {
			logger.severe("ocorreu um erro no arquivo durante a execução do programa");
		}
	}

	public void saveItems(Map<String, Item> items) {
		File file = new File("cardapio.bin");				//um novo arquivo SEMPRE é criado?
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(items);
			out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Map<String, Item> loadItems() throws InfraException {
		Map<String, Item> items = new HashMap<String, Item>();
		File file = new File("cardapio.bin");
		ObjectInputStream objInput = null;
		InputStream in = null;
		if (!file.exists()) {
			saveItems(items);
		}
		try {
			in = new FileInputStream(file);
			// Recupera a lista
			objInput = new ObjectInputStream(in);
			items = (Map<String, Item>) objInput.readObject();

			return items;

		} catch (NullPointerException ex) {
			logger.config(ex.getMessage());
			throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

		} catch (IOException ex) {
			logger.config(ex.getMessage());
			throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
		} catch (ClassNotFoundException ex) {
			logger.config(ex.getMessage());
			throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
		} finally {
			try {
				objInput.close();
				in.close();

			} catch (IOException e) {
				logger.severe(e.getMessage());
				throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

			} catch (Exception e) {
				logger.severe(e.getMessage());
				throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

			}

		}
	}
}
