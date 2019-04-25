package Model;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
 

public class Main1 extends JFrame{
	
	private RedBlackTree exchanges;
	private TreeAVL capital;
	private File selected;
	private ArrayList<Market> markets;
	

public Main1() {
	
		this.exchanges = new RedBlackTree<String>();
		this.capital = new TreeAVL<String>();
		 markets = new ArrayList<>();
		selected = null;
}


	public void modifyMarket() {
		
	}
	
	
	public void deleteMarket() {
		
	}
	
	
	public void addFile() throws IOException{
		
		markets.clear();
		String data = "";
		int iterator = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de "
				+ "datos que contiene su archivo."));
			
				
		for(int i=0; i<iterator;i++){
			data += JOptionPane.showInputDialog("Por favor ingrese los datos del mercado de divisas, de la forma:"
					+ " nombre, fecha(DD/MM/AAAA) tiempo(mm:ss), precio");
			data += "\n";
		}
	
	FileWriter fw = new FileWriter("Data/New_Info_Exchange");
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	
	selected = new File("Data/New_Info_Exchange");
	readData();
	}	
	
	public File selectFile() throws IOException {
		
		markets.clear();
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
		
		double higher= 0;
		
		if(markets.isEmpty()){
			
			JOptionPane.showMessageDialog(null, "No se ha seleccionado o cargado ningún archivo."
					+ "\nPor favor, seleccione uno.");
		}else{
			
			for(int i=0; i< markets.size(); i++){
				
			double value1 = Double.parseDouble(markets.get(i).getValue());
			
				if(higher < value1) {
					higher = value1;
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "El precio más alto del mercado es"
					+ ": " + higher);
			
		}
		
		
		
	}
	
	public void consultLessPrice() {
		
double less= 0;
		
		if(markets.isEmpty()){
			
			JOptionPane.showMessageDialog(null, "No se ha seleccionado o cargado ningún archivo."
					+ "\nPor favor, seleccione uno.");
		}else{
			
			for(int i=1; i< markets.size(); i++){
				
				less = Double.parseDouble(markets.get(0).getValue());
				
			double value1 = Double.parseDouble(markets.get(i).getValue());
			
				if(less > value1) {
					less = value1;
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "El precio más bajo del mercado es"
					+ ": " + less);
			
		}
	}
	
	public void watchGraphics() {
		
	}
	
	public void watchStatistics() {
		
	}
	
	//Método encargado de leer los datos y crear cada uno de los mercados con
	// la información del archivo actual.
	public void readData() throws IOException{
		System.out.println("EL ES : " + selected.getName());
		FileReader fr = new FileReader("Data/"+selected.getName());
		BufferedReader br = new BufferedReader(fr);		

		
		while(br.ready()){
			
			String[] market = new String[3];
			market = br.readLine().split(", ");
			System.out.println(market[0] + market[1] + market[2]);
				
				String[] auxDate = new String[2];
				auxDate = market[1].split(" ");
				
				Market mk = new Market(market[0],auxDate[0],auxDate[1],market[2]);
				markets.add(mk);
		}
	}
	
	public static void main(String[] args){
		
		
		Main1 main = new Main1();
	}
	
}
