package business.control;

import infra.InfraException;
import infra.EntityFileDaoImpl;
import util.NameInvalidException;
import util.PriceInvalidException;
import util.ItemValidador;

import java.util.Map;
//import java.util.logging.Level;

import javax.swing.JOptionPane;

//import business.model.Entity;
import business.model.*;

public class EntityManager {

	private static EntityManager instance = new EntityManager(); //Singleton
	
	private static Map<String, Entity> items;
	private static Map<String, Entity> users;
	private static Map<String, Entity> suppliers;
	
	static EntityFileDaoImpl entityFileDao;
	
	private EntityManager()
	{
		try 
		{
			entityFileDao = new EntityFileDaoImpl();
			items = entityFileDao.loadEntities("cardapio.bin");
			users = entityFileDao.loadEntities("clientes.bin");
			suppliers = entityFileDao.loadEntities("fornecedores.bin");
		} catch (InfraException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static EntityManager getInstance()
	{
		if(instance==null)
		{
			instance = new EntityManager();
		}
		return instance;
	}

	public void addEntity(Entity entity) throws NameInvalidException, PriceInvalidException {

		switch (entity.getType())
		{
			case "Cliente":
				
				users.put(((User) entity).getName(), entity);
				entityFileDao.saveEntity(users, "clientes.bin");
				break;
				
			case "Fornecedor":
				
				suppliers.put(((FoodSupplier) entity).getName(), entity);
				entityFileDao.saveEntity(suppliers, "fornecedores.bin");
				break;
				
			case "Item":
				ItemValidador.validateName(((Item) entity).getName());
				ItemValidador.validatePrice(((Item) entity).getPrice());
				
				items.put(((Item) entity).getName(), entity);
				entityFileDao.saveEntity(items, "cardapio.bin");
				break;
		}
		

	}
	
	public void deleteEntity(String args, String category) {
		
		switch (category)
		{
			case "Cliente":
				
				if(users.remove(args) != null) {
					entityFileDao.deleteEntity(users, "clientes.bin");
					JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Cliente não faz parte da lista!!!");
				}
				break;
				
			case "Fornecedor":
				
				if(suppliers.remove(args) != null) {
					entityFileDao.deleteEntity(suppliers, "fornecedores.bin");
					JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fornecedor não faz parte da lista!!!");
				}
				break;
				
			case "Item":
				if(items.remove(args) != null) {
					entityFileDao.deleteEntity(items, "cardapio.bin");
					JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Item não faz parte da lista!!!");
				}
				break;
		}
				
		
	}

	public Map<String, Entity> getAllEntities(String entity) throws InfraException {
		Map<String, Entity> mylist = null;
		switch (entity)
		{
			case "Cliente":
				try {
					mylist= entityFileDao.loadEntities("clientes.bin");

				} catch (NullPointerException ex) {
					EntityFileDaoImpl.logger.severe(ex.getMessage());
					throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

				}
				
				break;
				
			case "Fornecedor":
				try {
					mylist= entityFileDao.loadEntities("fornecedores.bin");

				} catch (NullPointerException ex) {
					EntityFileDaoImpl.logger.severe(ex.getMessage());
					throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

				}
				break;

			case "Item":
				try {
					mylist= entityFileDao.loadEntities("cardapio.bin");
					

				} catch (NullPointerException ex) {
					EntityFileDaoImpl.logger.severe(ex.getMessage());
					throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");

				}
				break;
		}
		return mylist;
		
	}

}
