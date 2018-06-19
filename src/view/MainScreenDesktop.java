package view;

import infra.InfraException;
import util.NameInvalidException;
import util.PriceInvalidException;

import java.util.Iterator;

import javax.swing.JOptionPane;

import business.control.FacadeManager;
import business.model.Entity;
import business.model.FoodSupplier;
import business.model.Item;
import business.model.Message;
import business.model.User;

public class MainScreenDesktop {
	
	FacadeManager manager = FacadeManager.getInstance();

	public static void main(String[] args) {
		showMenu();
	}

	public static void showMenu() {
		String option = JOptionPane.showInputDialog(
				null,
				"Bem-vindo ao sistema do Cifood!\nEscolha a opção desejada:" +
				"\n1-Inserir novo item\n2-Listar itens\n3-Excluir item\n33-Desfazer inclusão/exclusão de item" +
				"\n4-Inserir cliente\n5-Listar clientes\n6-Excluir cliente\n66-Desfazer inclusão/exclusão de cliente" +
				"\n7-Inserir fornecedor\n8-Listar fornecedores\n9-Excluir fornecedor\n99-Desfazer inclusão/exclusão de fornecedor" +
				"\n10-Enviar mensagem direcionada\n11-Gerar relatórios administrativos\n12-Criar e processar Pedido" + 
				"\n13-Sair",
				//"Digite sua opção",
				"Cifood",
				JOptionPane.QUESTION_MESSAGE);

		MainScreenDesktop main = new MainScreenDesktop();

		main.readUserInput(option);
	}

	public void readUserInput(String option) {		
		int choice = 0;
		String category = "";
		
		try {
			choice = Integer.parseInt(option);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível resolver (parse) a escolha", "Erro", JOptionPane.WARNING_MESSAGE);
		}
		boolean checkedName = false;
		boolean checkedPrice = false;
		
		switch (choice) {
		case 1:
			String name = "";
			String price = "";
			while (true) {
				if (!checkedName) {
					name = JOptionPane.showInputDialog("Nome do Item:");
				}
				if (!checkedPrice) {
					price = JOptionPane.showInputDialog("Preço do Item:");
				}

				try {
					Item item = new Item(name, price);
					this.manager.addEntity(item);
					JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
					break;
				} catch (NameInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedName = false;
					checkedPrice = true;
				} catch (PriceInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedName = true;
					checkedPrice = false;
				}

			}
			
			showMenu();
			break;
		case 2:
			String itemsString = "";
			Iterator<Entity> items;
			try {
				items = this.manager.getAllEntities("Item").values().iterator();
				while (items.hasNext()) {
					Item item = (Item) items.next();
					itemsString += item.toString();
				}
				JOptionPane.showMessageDialog(null, itemsString);
			} catch (InfraException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			showMenu();
			break;
		case 3:
			//remover item
			String itemRemove = "";
			category = "Item";
			
			itemRemove = JOptionPane.showInputDialog("Nome do item: ");
			this.manager.deleteEntity(itemRemove, category);
			
			showMenu();
			break;
		case 4:
			//Inserir UsuÃ¡rio
			String nameUser = "";
			String passwordUser = "";
			String mailUser = "";
		
			nameUser = JOptionPane.showInputDialog("Nome: ");
			passwordUser = JOptionPane.showInputDialog("Senha:");
			mailUser = JOptionPane.showInputDialog("Email: ");
			
			
			User cliente = new User(nameUser, mailUser, passwordUser);
			try {
				this.manager.addEntity(cliente);
			} catch (NameInvalidException | PriceInvalidException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
			
			showMenu();
			break;
		case 5:
			String usersString = "";
			Iterator<Entity> users;
			try {
				users = this.manager.getAllEntities("Cliente").values().iterator();
				while (users.hasNext()) {
					User user = (User) users.next();
					usersString += user.toString();
				}
				JOptionPane.showMessageDialog(null, usersString);
			} catch (InfraException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			showMenu();
			break;
		case 6:
			//remover cliente
			String clientRemove = "";
			category = "Cliente";
			
			clientRemove = JOptionPane.showInputDialog("Nome do Cliente: ");
			this.manager.deleteEntity(clientRemove, category);
			
			showMenu();
			break;
		case 7:
			//Inserir Fornecedor
			String nameSupplier = "";
			String passwordSupplier = "";
			String mailSupplier = "";
			String restaurantName = "";
			String phone = "";
			String cnpj_cpf = "";
			String address = "";
		
			nameSupplier = JOptionPane.showInputDialog("Nome: ");
			passwordSupplier = JOptionPane.showInputDialog("Senha:");
			mailSupplier = JOptionPane.showInputDialog("Email: ");
			restaurantName = JOptionPane.showInputDialog("Nome do Restaurante: ");
			phone = JOptionPane.showInputDialog("Telefone de contato: ");
			cnpj_cpf = JOptionPane.showInputDialog("CPF/CNPJ: ");
			address = JOptionPane.showInputDialog("EndereÃ§o do estabelecimento: ");
			
			FoodSupplier supplier = new FoodSupplier(nameSupplier, mailSupplier, passwordSupplier, restaurantName, phone, cnpj_cpf, address);
			try {
				this.manager.addEntity(supplier);
			} catch (NameInvalidException | PriceInvalidException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso!");
			
			showMenu();
			break;
		case 8:
			//Listando Fornecedores
			String suppliersString = "";
			Iterator<Entity> suppliers;
			try {
				suppliers = this.manager.getAllEntities("Fornecedor").values().iterator();
				while (suppliers.hasNext()) {
					FoodSupplier foodSupplier = (FoodSupplier) suppliers.next();
					suppliersString += foodSupplier.toString();
				}
				JOptionPane.showMessageDialog(null, suppliersString);
			} catch (InfraException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			showMenu();
			break;
		case 9:
			//remover fornecedor
			String supplierRemove = "";
			category = "Fornecedor";
			
			supplierRemove = JOptionPane.showInputDialog("Nome do Fornecedor: ");
			this.manager.deleteEntity(supplierRemove, category);
			
			showMenu();
			break;
		case 10:
			//Enviar mensagem
			String destination = "";
			String content = "";
			
			destination = JOptionPane.showInputDialog("DestinatÃ¡rio: ");
			content = JOptionPane.showInputDialog("ConteÃºdo da mensagem: ");

			Message msg = new Message(destination, content);
			
			this.manager.sent(msg);
		
			JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!");
						
			showMenu();
			break;
		case 11:
			String typeReport = "";
			
			typeReport = JOptionPane.showInputDialog("Salvar o relatório em qual formato? (PDF / XML) ");
			
			this.manager.generateReport(typeReport);
			
			showMenu();
			break;
		case 12:
			
			String clientePedido = "";
			String fornecedorPedido = "";
			int formaDePagamentoOption;
			//String formaDePagamento = "";
			
			String[] options = {"Dinheiro", "Cartão"};
			
			clientePedido = JOptionPane.showInputDialog("Cliente que faz o pedido: ");
			fornecedorPedido = JOptionPane.showInputDialog("Fornecedor que recebe o pedido:  ");
			formaDePagamentoOption = JOptionPane.showOptionDialog(null,
				    		"Escolha a forma de pagamento: ",
				    	    "Forma de Pagamento",
				    	    JOptionPane.YES_NO_OPTION,
				    	    JOptionPane.QUESTION_MESSAGE,
				    	    null,
				    	    options,
				    	    options[0]);

			if(formaDePagamentoOption == 1) {
				manager.addOrder(clientePedido, fornecedorPedido, "dinheiro");
			}
			else {
				manager.addOrder(clientePedido, fornecedorPedido, "cartao");
			}				
			
			showMenu();
			break;
		case 13:			
			break;
		case 33:
			try {
				manager.undoEntity("Item");
			} catch (NameInvalidException e) {
				e.printStackTrace();
			} catch (PriceInvalidException e) {
				e.printStackTrace();
			}
			showMenu();
			break;
		case 66:
			try {
				manager.undoEntity("Cliente");
			} catch (NameInvalidException e) {
				e.printStackTrace();
			} catch (PriceInvalidException e) {
				e.printStackTrace();
			}
			showMenu();
			break;
		case 99:
			try {
				manager.undoEntity("Fornecedor");
			} catch (NameInvalidException e) {
				e.printStackTrace();
			} catch (PriceInvalidException e) {
				e.printStackTrace();
			}
			showMenu();
			break;
		default:
			showMenu();
			break;

		}
	}
}