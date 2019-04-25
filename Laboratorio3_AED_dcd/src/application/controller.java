package application;

import java.io.IOException;
import java.security.Principal;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import Model.Main1;



public class controller {
	
	public Main1 link;
	public Main linkMainIG;
	
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
		link = new Main1(); 
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
	
	
	public void addFile () throws IOException {
	link.addFile();
		
	}
	
	
	public void selectFile() throws IOException{
		link.selectFile();
	}
	
	
	public void consultHigherPrice() throws IOException {
		link.consultHigherPrice();
		
	}
	
	public void consultLessPrice() {
		
	}
	
	public void watchGraphics() {
		
		linkMainIG = new Main();
		linkMainIG.start2();
		
	}
	
	public void watchStatistics() {
		
	}
	

}

	