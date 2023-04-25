package dl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Erregistroa implements Serializable{


	private int id;
	private String gailuIzena;
	private LocalDateTime data;
	private float prezioa;
	
	private static final long serialVersionUID = 1L;
	
	public Erregistroa() {
		super();

	}
	public Erregistroa(String gailuIzena, LocalDateTime data, float prezioa) {
		super();
		this.gailuIzena = gailuIzena;
		this.data = data;
		this.prezioa = prezioa;
	}
	public Erregistroa(int id, String gailuIzena, LocalDateTime data, float prezioa) {
		super();
		this.id = id;
		this.gailuIzena = gailuIzena;
		this.data = data;
		this.prezioa = prezioa;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGailuIzena() {
		return gailuIzena;
	}
	public void setGailuIzena(String gailuIzena) {
		this.gailuIzena = gailuIzena;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public float getPrezioa() {
		return prezioa;
	}
	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}
	
	public String toString() {
		return "\n"+ this.gailuIzena + ":\tid: "+ this.id + "\tdata: " + this.data.toString() + "\tprezioa: "+ this.prezioa;
	}
	
	public String getHasieraOrdua() {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");
        String horaConFormato = data.format(formato);
		
		return horaConFormato;
	}
	public String getAmaieraOrdua() {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");
		
		LocalDateTime ordua = data.plusMinutes(90);
		String horaConFormato = ordua.format(formato);
		
		return horaConFormato;		
		
	}
	
}