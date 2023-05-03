package pl;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
	
    public List<Erregistroa> egunekoProgramakLortu() {
        
    	if(erregistroakDB==null) {
    		
    		erregistroakDB = eEJB.egunekoProgramakLortu();
    		
    	}
    	
    	return erregistroakDB;
    }
    
    public Erregistroa programarenInformazioaLortu(int programaId) {
    	
    	return eEJB.programarenInformazioaLortu(programaId);
    	
    }
    public String astekoEgunaLortu() {
    	
		Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
    	String eguna = "eguna";
    	 
    	if(dayOfWeek==1) {
    	
    		eguna="ASTELEHENA";
    		
    	}
    	else if(dayOfWeek==2){
    		
    		eguna="ASTEARTEA";
    	}
    	else if(dayOfWeek==3){
    		
    		eguna="ASTEAZKENA";
    	}
    	else if(dayOfWeek==4){
    		
    		eguna="OSTEGUNA";
    	}
    	else if(dayOfWeek==5){
    		
    		eguna="OSTIRALA";
    	}
    	else if(dayOfWeek==6){
    		
    		eguna="LARUNBATA";
    	}
    	else if(dayOfWeek==7){
    		
    		eguna="IGANDEA";
    	}
    	return eguna;
    	
    }
    
    public List<Erregistroa> egunekoProgramakBatutaLortu() {
        
    	if(erregistroakDB==null) {
    		
    		erregistroakDB = eEJB.egunekoProgramakLortu();
    		
    		for(int i=0; i<erregistroakDB.size();i++) {
    			
    			String hasieraOrdua = erregistroakDB.get(i).getData().getHour()+":00";
    		}
    		
    	}
    	
//    	Sartu erregistroak taldetan, orduka jarrita -> String bat barruan izenak jarriz (if string length > 50 jarri 3 puntu)
//    	on click bistaratu orduan daudenak
//    	bideratu erregistroa 
    	
    	return erregistroakDB;
    }
    public List<Erregistroa> ordukaEgunekoProgramakLortu(int ordua) {
    	
    	return eEJB.ordukaEgunekoProgramakLortu(ordua);
    	
    }
	public void resetView() {
		
		erregistroakDB=null; 
		
	}

}
