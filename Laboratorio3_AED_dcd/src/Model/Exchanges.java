package Model;

public class Exchanges {
	
	private String date;
	private String hour;
	private String value;
	private String tipe;
	
	public Exchanges(String tipe,String date, String time, String value){
		
		this.tipe = tipe;
		this.date = date;
		this.hour = time;
		this.value = value;
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	
	
	

}
