package business.model;

public class Message {
	String destination;
	String content;
	
	public Message(String dest, String content)
	{
		this.destination = dest;
		this.content = content;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
