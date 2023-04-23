package pl;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;

@Named
@SessionScoped
public class ErabiltzaileAtazakMB implements Serializable {
	
	@EJB private ErabiltzaileaEJB eEJB;
	private int ordua;

	public ErabiltzaileAtazakMB() {
		// TODO Auto-generated constructor stub
	}
	
	public int getOrdua() {
		return ordua;
	}

	public void programaBerriaGorde(String gailuIzena, GailuAukeraketaViewMB gViewMB) throws IOException{
	    
		eEJB.programaBerriaGorde(gailuIzena, ordua); 
		gViewMB.resetView();
		ordua = 0;
		FacesContext.getCurrentInstance().getExternalContext().redirect("../");
	    
	}
	public void orduaMantendu(int ordua, PrezioAukeraketaViewMB pViewMB) throws IOException{
		
		this.ordua = ordua;
		pViewMB.resetView();
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuak.xhtml");
	}
	

}
