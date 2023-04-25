package pl;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Aukera3FormMB {
	private LocalDateTime noiztik;
	private LocalDateTime nora;
	
	
	
	public Aukera3FormMB() {
		super();
	}
	public Aukera3FormMB(LocalDateTime noiztik, LocalDateTime nora) {
		super();
		this.noiztik = noiztik;
		this.nora = nora;
	}
	
	
	public LocalDateTime getNoiztik() {
		return noiztik;
	}
	public void setNoiztik(LocalDateTime noiztik) {
		this.noiztik = noiztik;
	}
	public LocalDateTime getNora() {
		return nora;
	}
	public void setNora(LocalDateTime nora) {
		this.nora = nora;
	}
	public void	resetForm() {
		this.noiztik = null;	
		this.nora=null;

	}
	

}
