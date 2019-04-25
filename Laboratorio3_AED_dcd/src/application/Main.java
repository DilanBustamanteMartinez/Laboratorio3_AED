package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
			Scene scene = new Scene(root,730,550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start2() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("graphic.fxml"));
		Pane pan = null;
		try {
			pan = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage clientVipDiaStage = new Stage();
		clientVipDiaStage.setTitle("Créditos JP");
		clientVipDiaStage.initModality(Modality.WINDOW_MODAL);
		clientVipDiaStage.initOwner(primaryStage);
		Scene scene = new Scene(pan);
		clientVipDiaStage.setScene(scene);
		clientVipDiaStage.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
