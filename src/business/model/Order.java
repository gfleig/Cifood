package business.model;

public class Order extends Entity{

	protected User cliente;
	protected FoodSupplier fornecedor;
	protected PagamentoCommand formaDePagamento;
	
	public Order(User cliente, FoodSupplier fornecedor, PagamentoCommand forma) {		
		super();
		this.type = "Pedido";
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.formaDePagamento = forma;
	}

	public User getCliente() {
		return cliente;
	}

	public FoodSupplier getFornecedor() {
		return fornecedor;
	}
	
	public String toString() {
		return cliente.getName() + " pediu no " + fornecedor.getName() + ", pagando com " + formaDePagamento.toString();		
	}
	
	public void fecharPedido() {
		formaDePagamento.processarPedido(this);
	}
	
}
