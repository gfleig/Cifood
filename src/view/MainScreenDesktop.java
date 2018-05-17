package view;

import infra.InfraException;
import util.NameInvalidException;
import util.PriceInvalidException;

import java.util.Iterator;

import javax.swing.JOptionPane;

import business.control.ItemManager;
import business.model.Item;

public class MainScreenDesktop {

	ItemManager userManager;

	public static void main(String[] args) {
		showMenu();
	}

	public static void showMenu() {
		String option = JOptionPane.showInputDialog(
				null,
				"Bem-vindo ao sistema do Cifood!\nEscolha a opção desejada:\n1-Inserir novo item\n2-Listar itens\n3-Excluir item(TODO)\n4-Sair",
				//"Digite sua opção",
				"Cifood",
				JOptionPane.QUESTION_MESSAGE);

		MainScreenDesktop main = new MainScreenDesktop();

		main.readUserInput(option);
	}

	public void readUserInput(String option) {
		try {
			userManager = new ItemManager();
		} catch (InfraException e) {
			//String option2 = JOptionPane.showInputDialog(e.getMessage());    //variável sem uso
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		int choice = 0;
		
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
					String[] args = { name, price };
					this.userManager.addItem(args);
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
			Iterator<Item> items;
			try {
				items = this.userManager.getAllItems().values().iterator();
				while (items.hasNext()) {
					Item user = items.next();
					//itemsString = itemsString + "[ Login: " + user.getName() + " || Senha: " + user.getPrice() + " ]" + "\n";
					itemsString += user.toString();
				}
				JOptionPane.showMessageDialog(null, itemsString);
			} catch (InfraException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			showMenu();
			break;
		case 3:
			//remover item, TODO
			showMenu();
			break;
		case 4:
			break;
		default:
			showMenu();
			break;

		}
	}
}