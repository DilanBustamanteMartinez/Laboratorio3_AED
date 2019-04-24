package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class controller {
	
	@FXML
	private Label logo;
	
	@FXML
	private Button modifymarket;
	@FXML
	private Button deleteMarket;
	@FXML
	private Button addFile;
	@FXML
	private Button selectFile;
	@FXML
	private Button consultHigherPrice;
	@FXML
	private Button consultLessPrice;
	@FXML
	private Button watchGraphics;
	@FXML
	private Button watchStatistics;
	
	
	public controller() {
		
	}
	
	public void initialize() {
	Image icono = new Image("application/bvc1.png");
	ImageView img = new ImageView(icono);
	img.setScaleX(1.3);
	logo.setLayoutX(80);
	logo.setLayoutY(0);
	logo.setGraphic(img);
	
	}
	
	public void modifyMarket() {
		
	}
	
	
	public void deleteMarket() {
		
	}
	
	
	public void addFile () {
		
	}
	
	
	public void selectFile() {
		
	}
	
	
	public void consultHigherPrice() {
		
	}
	
	public void consultLessPrice() {
		
	}
	
	public void watchGraphics() {
		
	}
	
	public void watchStatistics() {
		
	}
	

}

	