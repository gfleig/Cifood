package infra;

import business.model.Message;

public interface SentMessage {
	
	public void sent(Message msg);

}
