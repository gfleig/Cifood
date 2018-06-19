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
	
	private Entity clienteMemento = null;
	private Entity fornecedorMemento = null;
	private Entity itemMemento = null;
	private String[] lastOperation = {null, null, null};				//3 strings, uma para cada tipo de entidade, que salva qual foi a íltima operação realizada.
	//{cliente, fornecedor, item}	
	
	private EntityManager()	{
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
	
	public static EntityManager getInstance() {
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
				
				clienteMemento = entity;
				lastOperation[0] = "Add";
				users.put(((User) entity).getName(), entity);
				entityFileDao.saveEntity(users, "clientes.bin");
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.");
				break;
				
			case "Fornecedor":
				
				fornecedorMemento = entity;
				lastOperation[1] = "Add";
				suppliers.put(((FoodSupplier) entity).getName(), entity);
				entityFileDao.saveEntity(suppliers, "fornecedores.bin");
				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso.");
				break;
				
			case "Item":
				
				ItemValidador.validateName(((Item) entity).getName());			
				ItemValidador.validatePrice(((Item) entity).getPrice());
				
				itemMemento = entity;
				lastOperation[2] = "Add";
				items.put(((Item) entity).getName(), entity);
				entityFileDao.saveEntity(items, "cardapio.bin");
				JOptionPane.showMessageDialog(null, "Item adicionado com sucesso.");
				break;
		}
		

	}
	
	public void deleteEntity(String args, String category) {
		
		switch (category)
		{
			case "Cliente":
				
				if(users.get(args) != null){					
					clienteMemento = (User)users.remove(args);
					entityFileDao.deleteEntity(users, "clientes.bin");
					lastOperation[0] = "Remove";
					JOptionPane.showMessageDialog(null, "Cliente " + ((User) clienteMemento).getName() + " removido com sucesso.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Cliente não faz parte da lista.");
				}
				break;
				
			case "Fornecedor":
				
				if(suppliers.get(args) != null) {					
					fornecedorMemento = (FoodSupplier)suppliers.remove(args);
					entityFileDao.deleteEntity(suppliers, "fornecedores.bin");
					lastOperation[1] = "Remove";
					JOptionPane.showMessageDialog(null, "Fornecedor " + ((FoodSupplier) fornecedorMemento).getName() + " removido com sucesso.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Fornecedor não faz parte da lista.");
				}
				break;
				
			case "Item":
				if(items.get(args) != null) {
					
					itemMemento = (Item)items.remove(args);
					entityFileDao.deleteEntity(items, "cardapio.bin");
					lastOperation[2] = "Remove";
					JOptionPane.showMessageDialog(null, "Item " + ((Item) itemMemento).getName() + " removido com sucesso.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Item não faz parte da lista.");
				}
				break;
		}		
	}
	
	public void undo(String entityType) throws NameInvalidException, PriceInvalidException{
		switch (entityType){
			case "Cliente":
				if(lastOperation[0] == "Add"){
					deleteEntity(((User) clienteMemento).getName(), entityType);
					lastOperation[0] = null;
					
				}
				else if (lastOperation[0] == "Remove"){
					addEntity((User)clienteMemento);
					lastOperation[0] = null;
				}
				else if (lastOperation[0] == null){
					JOptionPane.showMessageDialog(null, "Não é opssível desfazer mais do que 1 operação consecutiva por tipo de entidade.");
				}
			break;
			case "Fornecedor":
				if(lastOperation[1] == "Add"){
					deleteEntity(((FoodSupplier) fornecedorMemento).getName(), entityType);
					lastOperation[1] = null;	
				}
				else if (lastOperation[1] == "Remove"){
					addEntity((FoodSupplier)fornecedorMemento);
					lastOperation[1] = null;
				}
				else if (lastOperation[1] == null){
					JOptionPane.showMessageDialog(null, "Não é opssível desfazer mais do que 1 operação consecutiva por tipo de entidade.");
				}
			break;
			case "Item":
				if(lastOperation[2] == "Add"){
					deleteEntity(((Item) itemMemento).getName(), entityType);
					lastOperation[2] = null;	
				}
				else if (lastOperation[2] == "Remove"){
					addEntity((Item)itemMemento);
					lastOperation[2] = null;
				}
				else if (lastOperation[2] == null){
					JOptionPane.showMessageDialog(null, "Não é opssível desfazer mais do que 1 operação consecutiva por tipo de entidade.");
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
