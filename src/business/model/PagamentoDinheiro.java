package business.model;

import javax.swing.JOptionPane;

public class PagamentoDinheiro  implements PagamentoCommand{

	@Override
	public void processarPedido(Order order) {
		JOptionPane.showMessageDialog(null, "Pedido encaminhado.\nLembre-se do troco!");
	}
	
}
