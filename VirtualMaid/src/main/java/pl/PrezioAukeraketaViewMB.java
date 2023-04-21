package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.PrezioaJB;

@Named
@ViewScoped
public class PrezioAukeraketaViewMB implements Serializable {

	private List<PrezioaJB> prezioakDB;
	@EJB private ErabiltzaileaEJB eEJB;

    public List<PrezioaJB> egunekoPrezioakLortu() {
        
    	return eEJB.egunekoPrezioakLortu();
    }
	
	public void resetView() {
		
		prezioakDB=null; 
		
	}

}
