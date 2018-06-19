package business.model;

import javax.swing.JOptionPane;

public class PagamentoCartao implements PagamentoCommand {

	@Override
	public void processarPedido(Order order) {
		JOptionPane.showMessageDialog(null, "Pedido encaminhado.\n");
		
	}

}
