package pl;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;

@Named
@ViewScoped
public class HistorialaViewMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Erregistroa> erregistroakDB;
	private List<List<String>> unitateak;
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
    
    public List<List<String>> egunekoProgramakBatutaLortu() {
    	
    	if(erregistroakDB==null || unitateak==null) {
    		
    		erregistroakDB = eEJB.egunekoProgramakLortu();
    		unitateak = new ArrayList<>(); 
    		
    		for(int i=0; i<24;i++) {

    			List<String> orduka = new ArrayList<>();
	    		for(int j=0; j<erregistroakDB.size();j++) {
	    			
	    			LocalTime timeH = LocalTime.parse(erregistroakDB.get(j).getHasieraOrdua(), DateTimeFormatter.ofPattern("HH:mm"));
	    			LocalTime timeA = LocalTime.parse(erregistroakDB.get(j).getAmaieraOrdua(), DateTimeFormatter.ofPattern("HH:mm"));
	    			LocalTime ordua = LocalTime.of(i, 1);
	    			
	    			if(timeH.getHour()==ordua.getHour() || (timeA.getHour()==ordua.getHour() && timeA.getMinute()!=0) || (ordua.isAfter(timeH)&&ordua.isBefore(timeA)) && orduka.size()==0) {
	    				
	    				if(i<9) {
	    					orduka.add("0"+i+":00");
	    					orduka.add("0"+(i+1)+":00");
	    				}
	    				else if(i==9) {
	    					orduka.add("0"+i+":00");
	    					orduka.add(+(i+1)+":00");
	    				}
	    				else {
	    					orduka.add(i+":00");
	    					orduka.add(+(i+1)+":00");
	    				}
	    				orduka.add(erregistroakDB.get(j).getGailuIzena());
	    				
	    			}
	    			else if(timeH.getHour()==ordua.getHour() || (timeA.getHour()==ordua.getHour() && timeA.getMinute()!=0) || (ordua.isAfter(timeH)&&ordua.isBefore(timeA)) && orduka.get(2).length()<30) {
	    				
	    				String balioa = orduka.get(2);
	    				orduka.set(2,balioa+", "+erregistroakDB.get(j).getGailuIzena());
	    				
	    			}
	    			else if(timeH.getHour()==ordua.getHour() || (timeA.getHour()==ordua.getHour() && timeA.getMinute()!=0) || (ordua.isAfter(timeH)&&ordua.isBefore(timeA)) && orduka.get(2).length()>=30) {
	    				
	    				String balioa = orduka.get(2);
	    				orduka.set(2,balioa+", ...");
	    				break;
	    				
	    			}
    			
	    		}
	    		if(orduka.size()!=0) {
	    			
	    			unitateak.add(orduka);
	    			
	    		}
	    		
    		}
    		
    	}
    	
    	return unitateak;
    }
    public List<Erregistroa> ordukaEgunekoProgramakLortu(String ordua) {
    	
    	return eEJB.ordukaEgunekoProgramakLortu(LocalTime.parse(ordua).getHour());
    	
    }
	public void resetView() {
		
		erregistroakDB=null; 
		unitateak=null;
		
	}

}
