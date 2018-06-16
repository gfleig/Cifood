package business.model;

import java.io.Serializable;

public class FoodSupplier extends Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String type;
	private String name;
	private String email;
	private String senha;
	
	private String restaurant_name;
	private String phone;
	private String CNPJ_CPF;
	private String address;
	
	public FoodSupplier(String name, String email, String senha, String restaurant_name, String phone,
			String cNPJ_CPF, String address) {
		super();
		this.type = "Fornecedor";
		this.name = name;
		this.email = email;
		this.senha = senha;
		this.restaurant_name = restaurant_name;
		this.phone = phone;
		CNPJ_CPF = cNPJ_CPF;
		this.address = address;
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
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCNPJ_CPF() {
		return CNPJ_CPF;
	}
	public void setCNPJ_CPF(String cNPJ_CPF) {
		CNPJ_CPF = cNPJ_CPF;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Nome:" + name + "\nEmail:" + email + "\nNome do Restaurante:"
				+ restaurant_name + "\nPhone:" + phone + "\nCNPJ_CPF:" + CNPJ_CPF + "\nAddress:" + address + "\n\n";
	}

	public String getType() {
		return type;
	}
}
