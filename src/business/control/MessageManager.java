package business.control;

import javax.swing.JOptionPane;

import business.model.Message;
import infra.InfraException;
import infra.MailMessage;

public class MessageManager {
	private static MessageManager instance = new MessageManager(); //Singleton
	
	MailMessage mailMessage;
	
	private MessageManager() { }
	
	public static MessageManager getInstance()
	{
		if(instance==null)
		{
			instance = new MessageManager();
		}
		return instance;
	}
	
	public void sent(Message msg) {
		
		mailMessage = new MailMessage();
		mailMessage.sent(msg);
		
	}
	
}
