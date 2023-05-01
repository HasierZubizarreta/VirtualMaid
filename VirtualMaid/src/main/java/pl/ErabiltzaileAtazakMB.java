package pl;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.GailuaJB;

@Named
@SessionScoped
public class ErabiltzaileAtazakMB implements Serializable {
	
	@EJB private ErabiltzaileaEJB eEJB;
	private int ordua;
	private boolean editatu;
	private String gailuIzena;

	public ErabiltzaileAtazakMB() {
		// TODO Auto-generated constructor stub
	}
	
	public int getOrdua() {
		return ordua;
	}

	public boolean isEditatu() {
		return editatu;
	}

	public String getGailuIzena() {
		return gailuIzena;
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
	public void programaEditatu01(int idErregistroa) throws IOException {
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuak.xhtml");
		
	}
	public void gailuBerriaSortu(GailuaFormMB gailuaMB, GailuAukeraketaViewMB gaViewMB) {

		GailuaJB gailuakB=new GailuaJB(1, gailuaMB.getIzena(), gailuaMB.getMota(), gailuaMB.getIraupena(), gailuaMB.getKontsumoa());
        eEJB.gailuBerriaSortu(gailuakB);
        gaViewMB.resetView();
		
    }
	public void gailuaEditatu(String gailuIzena, GailuAukeraketaViewMB gaViewMB) throws IOException {

		this.gailuIzena=gailuIzena;
		gaViewMB.resetView();
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu2.xhtml");
		
    }
	public void gailuaEditatu02(GailuaJB gailua, GailuAukeraketaViewMB gaViewMB) throws IOException {

		GailuaJB jatorrizkoGailua = eEJB.etxekoGailuaLortu(gailuIzena);
		
//		GORDE GAILU BERRIA, ALDATU ERABILTZEN DEN METODOA
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu.xhtml");
		
    }
	public void egoeraAldatu() {
		
		editatu=true;
	}
	public void gailuaEzabatu(String gailuIzena) throws IOException {

        eEJB.gailuaEzabatu(gailuIzena);
        this.gailuIzena=null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu.xhtml");
        
    }

}
