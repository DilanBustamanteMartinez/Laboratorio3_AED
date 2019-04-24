package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class controller {
	
	@FXML
	private Label logo;
	
	
	public controller() {
		
	}
	
	
	public void initialize() {
	Image icono = new Image("application/bvc1.png");
	ImageView img = new ImageView(icono);
	logo.setLayoutX(0);
	logo.setLayoutY(0);
	logo.setGraphic(img);
	
	}
	
	

}
