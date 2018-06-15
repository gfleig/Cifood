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
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Entity;
import business.model.Item;

public class EntityFileDaoImpl implements FileDao{

	public static Logger logger = Logger.getLogger(EntityFileDaoImpl.class.getName());

	public EntityFileDaoImpl() {

		try {

			Handler hdConsole = new ConsoleHandler();
			Handler hdArquivo = new FileHandler("relatorioLog.txt");

			hdConsole.setLevel(Level.ALL);
			hdArquivo.setLevel(Level.ALL);

			EntityFileDaoImpl.logger.addHandler(hdConsole);
			EntityFileDaoImpl.logger.addHandler(hdArquivo);

			EntityFileDaoImpl.logger.setUseParentHandlers(false);

		} catch (IOException ex) {
			logger.severe("ocorreu um erro no arquivo durante a execução do programa");
		}
	}

	public void saveEntity(Map<String, Entity> entities, String arq) {
		File file = new File(arq);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(entities);
			out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteEntity(Map<String, Entity> entities, String arq)
	{
		File file = new File(arq);				
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			//out.reset();
			out.writeObject(entities);
			out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Map<String, Entity> loadEntities(String arq) throws InfraException {
		Map<String, Entity> entities = new HashMap<String, Entity>();
		File file = new File(arq);
		ObjectInputStream objInput = null;
		InputStream in = null;
		if (!file.exists()) {
			saveEntity(entities, arq);
		}
		try {
			in = new FileInputStream(file);
			// Recupera a lista
			objInput = new ObjectInputStream(in);
			entities = (Map<String, Entity>) objInput.readObject();

			return entities;

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
