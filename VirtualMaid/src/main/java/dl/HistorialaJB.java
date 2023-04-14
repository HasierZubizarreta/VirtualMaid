package dl;

import java.time.LocalDateTime;

public class HistorialaJB {

	private LocalDateTime data;
	private String gailua;
	private float prezioa;
	
	public HistorialaJB() {
	}

	public HistorialaJB(LocalDateTime data, String gailua, float prezioa) {
		this.data = data;
		this.gailua = gailua;
		this.prezioa = prezioa;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getGailua() {
		return gailua;
	}

	public void setGailua(String gailua) {
		this.gailua = gailua;
	}

	public float getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}	
}