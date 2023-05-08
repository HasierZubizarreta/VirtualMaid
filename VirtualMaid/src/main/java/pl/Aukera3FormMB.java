package pl;

import java.time.LocalDateTime;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Aukera3FormMB {
	private Date noiztik;
	private Date nora;
	
	public Aukera3FormMB() {
		super();
	}
	public Aukera3FormMB(Date noiztik, Date nora) {
		super();
		this.noiztik = noiztik;
		this.nora = nora;
	}
	
	public Date getNoiztik() {
		return noiztik;
	}
	public void setNoiztik(Date noiztik) {
		this.noiztik = noiztik;
	}
	public Date getNora() {
		return nora;
	}
	public void setNora(Date nora) {
		this.nora = nora;
	}
	public void	resetForm() {
		this.noiztik = null;	
		this.nora=null;
	}
	
}
