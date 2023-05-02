package pl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;
import dl.GailuaJB;

@Named
@ViewScoped
public class HistorialaViewMB implements Serializable{

	private List<Erregistroa> erregistroakDB;
	@EJB private ErabiltzaileaEJB eEJB;

	public HistorialaViewMB() {
		// TODO Auto-generated constructor stub
	}
	
    public List<Erregistroa> etxekoGailuakLortu() {
        
    	if(erregistroakDB==null) {
    		
    		erregistroakDB = eEJB.egunekoProgramakLortu();
    		
    	}
    	
    	return erregistroakDB;
    }
    
    public Erregistroa programarenInformazioaLortu(int programaId) {
    	
    	return eEJB.programarenInformazioaLortu(programaId);
    	
    }
	
	public void resetView() {
		
		erregistroakDB=null; 
		
	}

}
