package infra;

import javax.swing.JOptionPane;

import business.model.Message;

public class MessageAdapter implements SentMessage{

	public void sent(Message msg) {
		//Envia SMS
		JOptionPane.showMessageDialog(null, "Enviando SMS...");
	}

}
