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
		
		int higher= 0;
		if(markets.isEmpty()){
			
			JOptionPane.showMessageDialog(null, "No se ha seleccionado o cargado ningún archivo."
					+ "\nPor favor, seleccione uno.");
		}else{
			
			for(int i=0; i< markets.size()-1; i++){
				
			int value1=Integer.parseInt(markets.get(i).getValue());
			int value2=Integer.parseInt(markets.get(i+1).getValue());
			
				if(value1 < value2){
					higher = value2;
				}else{
					higher =  value1;
				}
				
			}
			
			JOptionPane.showMessageDialog(null, "El precio más alto del mercado es"
					+ ": " + higher);
			
		}
		
		
		
	}
	
	public void consultLessPrice() {
		
		if(markets.isEmpty()){
			
			JOptionPane.showMessageDialog(null, "No se ha seleccionado o cargado ningún archivo."
					+ "\nPor favor, seleccione uno.");
		}else{
			
			
		}
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
				markets.add(mk);
			}else{
				
				esFin=true;
			}
		}
	}
	
	public static void main(String[] args){
		
		
		Main1 main = new Main1();
	}
	
}
