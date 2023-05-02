package dl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Erregistroa implements Serializable, Comparable<Erregistroa>{


	private int id;
	private String gailuIzena;
	private LocalDateTime data;
	private float prezioa;
	private float kontsumoa;
	
	private static final long serialVersionUID = 1L;
	
	public Erregistroa() {
		super();

	}
	public Erregistroa(String gailuIzena, LocalDateTime data, float prezioa, float kontsumoa) {
		super();
		this.gailuIzena = gailuIzena;
		this.data = data;
		this.prezioa = prezioa;
		this.kontsumoa = kontsumoa;
	}
	public Erregistroa(int id, String gailuIzena, LocalDateTime data, float prezioa, float kontsumoa) {
		super();
		this.id = id;
		this.gailuIzena = gailuIzena;
		this.data = data;
		this.prezioa = prezioa;
		this.kontsumoa = kontsumoa;
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
	
	public float getKontsumoa() {
		return kontsumoa;
	}
	
	public void setKontsumoa(float kontsumoa) {
		this.kontsumoa = kontsumoa;
	}
	
	
	public String toString() {
		return "\n"+ this.gailuIzena + ":\tid: "+ this.id + "\tdata: " + this.data.toString() + "\tprezioa: "+ this.prezioa;
	}
	
	public String getHasieraOrdua() {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        String horaConFormato = data.format(formato);
		
        System.out.print(horaConFormato);
        
		return horaConFormato;
	}
	public int getHasieraOrduaInt() {
		
		return data.getHour();
	}
	public String getAmaieraOrdua() {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
		
		LocalDateTime ordua = data.plusMinutes(90);
		String horaConFormato = ordua.format(formato);
		
		System.out.print(horaConFormato);
		
		return horaConFormato;		
		
	}
	@Override
	public int compareTo(Erregistroa e) {
		
		return this.getData().compareTo(e.getData());
	}
	
}