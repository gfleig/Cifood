package business.model;

import java.io.Serializable;

public class User extends Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	private String email;
	private String senha;
	
	public User(String name, String email, String senha) {
		super();
		this.type = "Cliente";
		this.name = name;
		this.email = email;
		this.senha = senha;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Nome:" + name + "\nEmail:" + email + "\n\n";
	}

	
	public String getType() {
		return type;
	}
}
