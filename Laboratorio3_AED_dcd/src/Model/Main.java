package Model;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javafx.scene.chart.XYChart;
public class Main extends JFrame{
	
	private RedBlackTree exchanges;
	private TreeAVL capital;
	private File selected;
	

public Main() {
	
		this.exchanges = new RedBlackTree<String>();
		this.capital = new TreeAVL<String>();
		selected = null;
}


public void modifyMarket() {
		
	}
	
	
	public void deleteMarket() {
		
	}
	
	
	public void addFile(){
		
		
	}	
	
	public File selectFile() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    selected = selectedFile;
		}
		return selected;
		
	}
	
	public void consultHigherPrice() {
		
		int higher = 0;
		selectFile();
		
		
	}
	
	public void consultLessPrice() {
		
	}
	
	public void watchGraphics() {
		
	}
	
	public void watchStatistics() {
		
	}
	
	public void Graficas() {
		
	}
	
	public static void main(String[] args){
			Main main = new Main();
	}
	
}
