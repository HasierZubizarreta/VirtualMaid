package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;
import dl.KontsumoaJB;

@Named
@ViewScoped
public class Aukera3ViewMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Erregistroa> erregistroaDB;
	private List <KontsumoaJB> kontsumoaJB;
	
	@EJB private ErabiltzaileaEJB eEJB;
	
	 public List<Erregistroa> historialaLortu(Aukera3FormMB a3FormMB ) {
	        
	    	if(erregistroaDB==null) {
	    		
	    		erregistroaDB = eEJB.historialaLortu(a3FormMB.getNoiztik(),a3FormMB.getNora() );
	    		
	    	}
	    	
	    	return erregistroaDB;
	    }
	 
	
	
	

}
