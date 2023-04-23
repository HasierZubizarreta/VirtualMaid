package pl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ProgramaFormMB {
	
	private int ordua;
	private String gailuIzena;

	public ProgramaFormMB() {
		// TODO Auto-generated constructor stub
	}

	public ProgramaFormMB(int ordua, String gailuIzena) {
		this.ordua = ordua;
		this.gailuIzena = gailuIzena;
	}

	public int getOrdua() {
		return ordua;
	}

	public void setOrdua(int ordua) {
		this.ordua = ordua;
	}

	public String getGailuIzena() {
		return gailuIzena;
	}

	public void setGailuIzena(String gailuIzena) {
		this.gailuIzena = gailuIzena;
	}
	public void clearForm() {
	    this.ordua = 0;
	    this.gailuIzena = "";
	}

}
