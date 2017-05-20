package application.controladores;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Classe de uma VBox customizada.
 * <p>
 * Dentro dela, temos um ImageView e
 * duas Labels. Ao criar uma inst�ncia
 * dessa classe em qualquer outro lugar,
 * � poss�vel coloc�-la dentro de um outro .fxml.
 * 
 * Mais informa��es sobre criar elementos customizados em:
 * http://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm
 * 
 * @author �nio Jos�
 */
public class Item extends VBox
{
	@FXML private ImageView capa;
	@FXML private Label titulo;
	@FXML private Label autor;
	
	public Item(Image imagem, String titulo, String autor)
	{
		// Preparar o FxmlLoader para carregar
		// o arquivo posteriormente.
		FXMLLoader fxmlLoader = new FXMLLoader(
	                getClass().getResource("../cenas/Item.fxml"));

		// Setar a raiz e controlador do .fxml a ser carregado
		// como essa inst�ncia.
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try 
	    {
	    	// Carregar o arquivo.
	    	fxmlLoader.load();
	    } 
	    catch (IOException exception) 
	    {
	    	throw new RuntimeException(exception);
	    }
		
		this.titulo.setText(titulo);
		this.autor.setText(autor);
		capa.setImage(imagem);
	}
	
	@FXML private void click() throws IOException
	{
		// Coloquei isso s� para testar mesmo, pode deletar dps.
		System.out.println("Item selecionado: " + titulo.getText());
		//GerenciadorCenas.irPara(2);
	}
}
