package business.control;

import java.util.Map;

import javax.swing.JOptionPane;

import business.model.Entity;
import business.model.Item;
import business.model.Message;
import business.model.Order;
import infra.InfraException;
import util.NameInvalidException;
import util.PriceInvalidException;

public class FacadeManager 
{
	private static FacadeManager instance = new FacadeManager();
	
	EntityManager entityManager;
	MessageManager msgManager;
	ReportManager rptManager;
	
	private FacadeManager() 
	{ 
		entityManager = EntityManager.getInstance();
		msgManager = MessageManager.getInstance();
		rptManager = ReportManager.getInstance();
	}
	
	public static FacadeManager getInstance()
	{
		if(instance==null)
		{
			instance = new FacadeManager();
		}
		return instance;
	}
	
	public void addEntity(Entity entity) throws NameInvalidException, PriceInvalidException 
	{
		entityManager.addEntity(entity);
	}
	
	public void deleteEntity(String args, String category)
	{
		entityManager.deleteEntity(args, category);
	}
	
	public void undoEntity(String entityType) throws NameInvalidException, PriceInvalidException{
		entityManager.undo(entityType);
	}
	
	public Map<String, Entity> getAllEntities(String entity) throws InfraException
	{		
		return entityManager.getAllEntities(entity);
	}
	
	public void sent(Message msg)
	{
		msgManager.sent(msg);
	}
	
	public void generateReport(String typeReport)
	{
		rptManager.generateReport(typeReport);
	}
	
	public void addOrder(String cliente, String fornecedor, String pagamento) {
		entityManager.addOrder(cliente, fornecedor, pagamento);
	}
}
