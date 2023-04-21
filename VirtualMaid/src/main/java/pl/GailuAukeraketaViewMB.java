package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.GailuaJB;
import dl.PrezioaJB;

@Named
@ViewScoped
public class GailuAukeraketaViewMB implements Serializable {

	private List<GailuaJB> gailuakDB;
	@EJB private ErabiltzaileaEJB eEJB;

    public List<GailuaJB> etxekoGailuakLortu() {
        
    	if(gailuakDB==null) {
    		
    		gailuakDB = eEJB.etxekoGailuakLortu();
    		
    	}
    	
    	return gailuakDB;
    }
	
	public void resetView() {
		
		gailuakDB=null; 
		
	}

}
