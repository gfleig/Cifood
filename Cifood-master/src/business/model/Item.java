package business.model;

import java.io.Serializable;

public class Item extends Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3409171233621036055L;

	private String name, price, type;

	public Item(String name, String price) {
		super();
		type = "Item";
		this.name = name;
		this.price = price;
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

	public String getType() {
		return type;
	}
}
