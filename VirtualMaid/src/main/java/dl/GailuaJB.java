package dl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GailuaJB implements Serializable{

	private int idGailua;
	private String izena;	
	private String mota;
	private int iraupena;	
	private float kontsumoa;
	
	//Semeentzako aldagaiak
	protected List<String> programak = new ArrayList<String>();
	protected String irudia;
	private int iraupenaPertsonala;
	
	//semeek erabiltzeko aldagaiak;
	private float auxf=0.0f;
	private String auxs="";
	private int auxi=0;
	
	private static final long serialVersionUID = 1L;
	
	public GailuaJB() {
	}
	
	public GailuaJB(int idGailua, String izena, String mota, int iraupena, float kontsumoa) {
		this.idGailua = idGailua;
		this.izena = izena;
		this.mota = mota;
		this.iraupena = iraupena;
		this.kontsumoa = kontsumoa;
	}
	

	protected abstract void programakBete();
	public abstract void setIraupena(String programa);
	
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
	
	
	public void setIraupenaPertsonala(int iraupenaPertsonala) {
		this.iraupenaPertsonala = iraupenaPertsonala;
	}
	
	public int getIraupenaPertsonala() {
		return iraupenaPertsonala;
	}

	public List<String> getProgramak() {
		return programak;
	}

	public String getIrudia() {
		return irudia;
	}


	public String toString() {
		return "GailuaJB [idGailua=" + idGailua + ", izena=" + izena + ", mota=" + mota + ", iraupena=" + iraupena + ", kontsumoa=" + kontsumoa+ "]";
	}

	//semeek erabiltzeko getter eta setter
	
	public float getAuxf() {
		return auxf;
	}

	public void setAuxf(float auxf) {
		this.auxf = auxf;
	}

	public String getAuxs() {
		return auxs;
	}

	public void setAuxs(String auxs) {
		this.auxs = auxs;
	}

	public int getAuxi() {
		return auxi;
	}

	public void setAuxi(int auxi) {
		this.auxi = auxi;
	}
	
}
