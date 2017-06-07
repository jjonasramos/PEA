package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.GerenciadorCenas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sistema_loja.classes.vendas.Venda;

public class ControladorVenda extends Controlador implements Initializable 
{
	private List<ItemCarrinho> produtosCarrinho;
	private double total;
	private static int contador = 0;
	
	@FXML private ComboBox<String> formaPagamento;
	@FXML private TextField campoNome;
	@FXML private TextField campoCpf;
	@FXML private TextField campoTelefone;
	@FXML private TextField campoEstado;
	@FXML private TextField campoEndereco;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		this.produtosCarrinho = Controlador.carrinho.getProdutos();
		total = ControladorCarrinho.carrinho.calcularValorTotal();
		ObservableList<String> items = FXCollections.observableArrayList();

		items.addAll("Boleto Bancário... R$" + Double.toString(total),
					"Cartão de Crédito... 1 x R$" + Double.toString(total),
					"Cartão de Crédito... 2 x R$" + Double.toString(Math.floor(total/2 * 100) / 100),
					"Paypal... R$" + Double.toString(total));
		formaPagamento.setItems(items);
	}
	
	@FXML
	private void concluir()
	{
		try
		{
			// Verificar campos
			// Adicionar a venda na lista
			// Resetar o carrinho e ir pra tela principal
			contador++;
			Venda v = new Venda(getProdutosCarrinho(),getTotal() , contador);
			v.setNomeCliente(campoNome.getText());
			v.setCpf(campoCpf.getText());
			v.setTelefone(campoTelefone.getText());
			v.setEstadoCidade(campoEstado.getText());
			v.setEndereco(campoEndereco.getText());
			vendas.add(v);
	
			carrinho.esvaziar();
			GerenciadorCenas.irPara(PRINCIPAL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void cancelar() throws IOException
	{
		GerenciadorCenas.irPara(PRINCIPAL);
	}

	public List<ItemCarrinho> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(List<ItemCarrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}	
}
