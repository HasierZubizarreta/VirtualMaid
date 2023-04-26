package dl;

import java.io.Serializable;

public class KontsumoaJB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float kontsumoa;
	private float prezioa;
	
	public KontsumoaJB(float kontsumoa, float prezioa) {
		super();
		this.kontsumoa = kontsumoa;
		this.prezioa = prezioa;
	}
	
	public float getKontsumoa() {
		return kontsumoa;
	}
	
	public void setKontsumoa(float kontsumoa) {
		this.kontsumoa = kontsumoa;
	}
	
	public float getPrezioa() {
		return prezioa;
	}
	
	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}
	
	

}
