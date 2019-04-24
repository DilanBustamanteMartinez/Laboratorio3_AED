package Model;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
 

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
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
	}	
	
	public File selectFile() {
		
		
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
	
	public static void main(String[] args){
		
		
		Main main = new Main();
	}
	
}
