package business.model;

import java.io.Serializable;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3409171233621036055L;

	private String name, price;

	public Item(String login, String senha) {
		super();
		this.name = login;
		this.price = senha;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String toString() {
		return name + ":\t\t\tR$" + price + "\n";
	}
}
