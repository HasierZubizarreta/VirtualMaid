package pl;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;
import dl.GailuaJB;

@Named
@SessionScoped
public class ErabiltzaileAtazakMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private ErabiltzaileaEJB eEJB;
	private int ordua;
	private boolean editatu;
	private String gailuIzena;

	public void setGailuIzena(String gailuIzena) {
		this.gailuIzena = gailuIzena;
	}

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
	
	public String[] gailuMotakLortu() {
		return ErabiltzaileaEJB.gailuMotak;
	}

	public void programaBerriaGorde(String gailuIzena, String gailuPrograma, GailuAukeraketaViewMB gViewMB) throws IOException{
		eEJB.gailuaProgramaAukeratu(gailuIzena, gailuPrograma);
		int emaitza = eEJB.programaBerriaGorde(gailuIzena, ordua); 
		System.out.print("Arazoa: "+emaitza);
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
		
		this.ordua = idErregistroa;
		FacesContext.getCurrentInstance().getExternalContext().redirect("programaEditatu.xhtml");
		
	}
	public void programaEditatu02(Erregistroa e, HistorialaViewMB hVMB) throws IOException {

//		KONPROBATU EGIN BEHAR DA JATORRIZKO ORDUA > ORAINGO ORDUA ETA ORDU BERRIA
		eEJB.programaEditatu(e);
		editatu=false;
		this.ordua = 0;
		hVMB.resetView();
		FacesContext.getCurrentInstance().getExternalContext().redirect("historiala.xhtml");
		
    }
	public void gailuBerriaSortu(GailuaFormMB gailuaMB, GailuAukeraketaViewMB gaViewMB) {

		//GailuaJB gailuakB=new GailuaJB(1, gailuaMB.getIzena(), gailuaMB.getMota(), gailuaMB.getIraupena(), gailuaMB.getKontsumoa());
		GailuaJB gailuakB = eEJB.gailuBerriaLortu(gailuaMB.getIzena(), gailuaMB.getMota(), gailuaMB.getIraupena(), gailuaMB.getKontsumoa());
		eEJB.gailuBerriaSortu(gailuakB);
        gaViewMB.resetView();
        gailuaMB.clearForm();
		
    }
	public void gailuaEditatu(String gailuIzena, GailuAukeraketaViewMB gaViewMB) throws IOException {

		this.gailuIzena=gailuIzena;
		gaViewMB.resetView();
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu2.xhtml");
		
    }
	public void gailuaEditatu02(GailuaJB gailua, GailuAukeraketaViewMB gaViewMB) throws IOException {

		eEJB.gailuaEditatu(gailua);
		gaViewMB.resetView();
		FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu.xhtml");
		
    }
	public void egoeraAldatu() {
		
		editatu=true;
	}
	public void egoeraAldatu(int lok) {
		
		editatu=true;
		this.gailuIzena=eEJB.programarenInformazioaLortu(ordua).getHasieraOrdua();
	}
	public void gailuaEzabatu() throws IOException {

        eEJB.gailuaEzabatu(gailuIzena);
        this.gailuIzena=null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("gailuakEditatu.xhtml");
        
    }
	public Erregistroa programarenInformazioaLortu(int programaId) {
		
		return eEJB.programarenInformazioaLortu(programaId);
		
	}
	public void programaEzabatu() throws IOException {

        eEJB.programaEzabatu(ordua);
        this.ordua=0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("historiala.xhtml");
        
    }
	public int getCurrentHout() {

		return LocalTime.now().getHour();
        
    }
	public void clearSession() throws IOException {
		
		this.gailuIzena=null;
		this.ordua=0;
		editatu=false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("../");
		
	}
}
