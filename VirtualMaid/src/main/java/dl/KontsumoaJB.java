package dl;

import java.io.Serializable;
import java.time.LocalDateTime;

public class KontsumoaJB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime data;
	private float kontsumoa;
	private float prezioa;
	
	public KontsumoaJB(LocalDateTime data, float kontsumoa, float prezioa) {
		super();
		this.data = data;
		this.kontsumoa = kontsumoa;
		this.prezioa = prezioa;
	}
	
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
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
