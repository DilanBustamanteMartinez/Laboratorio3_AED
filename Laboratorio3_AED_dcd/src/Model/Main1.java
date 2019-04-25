package Model;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
 

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
	readData();
	}	
	
	public File selectFile() throws IOException {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    selected = selectedFile;
		}
		
		readData();
		return selected;
		
	}
	
	public void consultHigherPrice() throws IOException {
		
		int higher = 0;
		selectFile();
		
		
	}
	
	public void consultLessPrice() {
		
	}
	
	public void watchGraphics() {
		
	}
	
	public void watchStatistics() {
		
	}
	
	//Método encargado de leer los datos y crear cada uno de los mercados con
	// la información del archivo actual.
	public void readData() throws IOException{
		
		FileReader fr = new FileReader(selected.getName());
		BufferedReader br = new BufferedReader(fr);
		
		boolean esFin = false;
		
		
		while(!esFin){
			
			String[] market = new String[3];
			market = br.readLine().split(", ");
			
			if(market[1]!=null){
				
				String[] auxDate = new String[2];
				auxDate = market[1].split(" ");
				
				Market mk = new Market(market[0],auxDate[0],auxDate[1],market[2]);
			}else{
				
				esFin=true;
			}
		}
	}
	
	public static void main(String[] args){
		
		
		Main1 main = new Main1();
	}
	
}
