package infra;

import javax.swing.JOptionPane;

import business.model.Message;

public class MailMessage implements SentMessage{
	MessageAdapter msgAdapter;
	
	public void sent(Message msg) {
		if(msg.getDestination().contains("@"))
		{
			//E-mail enviado com sucesso!
			JOptionPane.showMessageDialog(null, "Enviando E-mail....");
		}
		else if(msg.getDestination().matches(".*\\d.*"))
		{
			msgAdapter = new MessageAdapter();
			msgAdapter.sent(msg);
		}
		else
		{
			//Inválido
		}
	}

	
}
