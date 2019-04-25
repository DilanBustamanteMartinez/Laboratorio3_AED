package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 

public class Main1 extends JFrame{
	
	private RedBlackTree exchanges;
	private TreeAVL capital;
	private File selected;
	

public Main1() {
	
		this.exchanges = new RedBlackTree<String>();
		this.capital = new TreeAVL<String>();
		selected = null;
}





public void modifyMarket() {
		
	}
	

	
	public void deleteMarket() {
		
	}
	
	
	public void addFile() throws IOException{
		
		String data = "";
		int iterator = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de "
				+ "datos que contiene su archivo."));
			
				
		for(int i=0; i<iterator;i++){
			data += JOptionPane.showInputDialog("Por favor ingrese los datos del mercado de divisas, de la forma:"
					+ " nombre, fecha(DD/MM/AAAA) tiempo(mm:ss), precio");
			data += "/n";
		}
	
	FileWriter fw = new FileWriter("Data/New_Info_Exchange");
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	
	selected = new File("Data/New_Info_Exchange");		
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
	
	public static void main(String[] args){
		
		
		Main1 main = new Main1();
	}
	
}
