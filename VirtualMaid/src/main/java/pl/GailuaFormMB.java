package pl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class GailuaFormMB {

	private int idGailua;
	private String izena;	
	private String mota;
	private int iraupena;	
	private float kontsumoa;
	
	private static final long serialVersionUID = 1L;
	
	public GailuaFormMB() {
	}
	
	public GailuaFormMB(int idGailua, String izena, String mota, int iraupena, float kontsumoa) {
		this.idGailua = idGailua;
		this.izena = izena;
		this.mota = mota;
		this.iraupena = iraupena;
		this.kontsumoa = kontsumoa;
	}
	

	public int getIdGailua() {
		return idGailua;
	}

	public void setIdGailua(int idGailua) {
		this.idGailua = idGailua;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public int getIraupena() {
		return iraupena;
	}

	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}

	public float getKontsumoa() {
		return kontsumoa;
	}

	public void setKontsumoa(float kontsumoa) {
		this.kontsumoa = kontsumoa;
	}

}
