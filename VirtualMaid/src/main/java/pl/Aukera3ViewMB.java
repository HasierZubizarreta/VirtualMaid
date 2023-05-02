package pl;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	
	 public List<Erregistroa> historialaLortu(Aukera3FormMB a3FormMB) {
	        
	    	if(erregistroaDB==null) {
	    		
	    		Instant instantH = a3FormMB.getNoiztik().toInstant();
	    		Instant instantA = a3FormMB.getNora().toInstant();
	    		LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());
	    		LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());
	    		erregistroaDB = eEJB.historialaLortu(hasiera,amaiera);
	    		
	    	}
	    	
	    	return erregistroaDB;
	    }
	 
	 public List<KontsumoaJB> kontsumoaLortu(Aukera3FormMB a3FormMB){
		 
		 if(kontsumoaJB==null) {
			 
			 	Instant instantH = a3FormMB.getNoiztik().toInstant();
	    		Instant instantA = a3FormMB.getNora().toInstant();
	    		LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());
	    		LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());
	    		kontsumoaJB = eEJB.egunOsokoKontsumoaKalkulatu(hasiera,amaiera);
			 
		 }
		 
		 return kontsumoaJB;
	 }
	 
	
	
	

}
