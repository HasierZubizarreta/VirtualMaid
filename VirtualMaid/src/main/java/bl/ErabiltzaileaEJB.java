package bl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dl.Erregistroa;
import dl.GailuaJB;
import dl.GailuakB;
import dl.HistorialaB;
import dl.KontsumoaJB;
import dl.PrezioaJB;
import dl.PrezioakOrdukoB;

@Stateless
@LocalBean
public class ErabiltzaileaEJB {
	
	//HistorialaB hB = new HistorialaB();
	private GailuakB gailuakB=new GailuakB();
	private List<PrezioaJB> prezioakOrduko = PrezioakOrdukoB.queryFindAllDB();
	//aratz eta hasier
	HistorialaB hB = new HistorialaB();
	//HistorialenTaulaJB hB = new HistorialenTaulaJB();


	//public void historialaBorratu() {
    	//LocalDateTime data = LocalDateTime.now();
    	//int yearNow = data.getYear();
    	//
    	//hB.historialZaharraBorratu(yearNow);
    //}
	public List<Erregistroa> historialaLortu(LocalDateTime data1,LocalDateTime data2) {
    	return hB.queryFindData(data1,data2);
    }
	
	//aurreko bertsioa
    //public void historialZaharraBorratu() {    }
    //public List<Erregistroa> historialaSortu(LocalDateTime data1,LocalDateTime data2) { return hB.queryFindData(data1,data2);}  
   
	  
	public void laburpenaLortu() {   
    	
    }
    public List<Erregistroa> egunekoProgramakLortu() {
        LocalDate now=LocalDateTime.now().toLocalDate();
        LocalDateTime has=LocalDateTime.of(now, LocalTime.of(0, 0));
        LocalDateTime buk=LocalDateTime.of(now, LocalTime.of(23, 59));
    	
        return hB.queryFindData(has, buk);
       
        
    }
    public List<PrezioaJB> egunekoPrezioakLortu() {
    
    	System.out.print("Luzera: "+prezioakOrduko.size());
        return prezioakOrduko;   
    }
    public List<GailuaJB> etxekoGailuakLortu() {
       
        return gailuakB.queryFindAll();
        
    }
    public GailuaJB etxekoGailuaLortu(String gailuIzena) {
        
        return gailuakB.find(gailuIzena);
        
    }
    public void programaBerriaGorde(String gailuIzena, int ordua) {
    	GailuaJB g = gailuakB.find(gailuIzena);
    	float prezioaOrduko = PrezioakOrdukoB.findPrezioa(ordua);
    	float prezioTotala = prezioaOrduko * g.getIraupena(); //hau hobetu behar da
    	
    	LocalDateTime data = LocalDateTime.of(LocalDate.now(), LocalTime.of(ordua, 0));
    	
    	Erregistroa e = new Erregistroa(gailuIzena, data, prezioTotala);
    	hB.persistDB(e);
    }
    public void programaEditatu(int ordua, int programaId) {
   	 Erregistroa e = hB.find(programaId);
   	 LocalDateTime data = LocalDateTime.of(e.getData().toLocalDate(), LocalTime.of(ordua, 0));
   	 hB.erregistroaEzabatuDB(e);
   	 e.setData(data);
   	 hB.persistDB(e);
   }
    public Erregistroa programarenInformazioaLortu(int programaId) {
    	
    	return hB.find(programaId);
    	
    }
    public void gailuBerriaSortu(GailuaJB gailuakDB) {

    	gailuakB.persist(gailuakDB);
    }
    
    public void gailuaEzabatu(String gailuIzena) {

        gailuakB.gailuaEzabatuDB(gailuIzena);
    }
    
    public void gailuaEditatu(String gailuIzena, int aldaketa, String balioB) {

    	GailuaJB gailuaDB= gailuakB.find(gailuIzena);
        switch(aldaketa){

            case 1:
                gailuaDB.setIzena(balioB);
                break;
            case 2:
                gailuaDB.setMota(balioB);
                break;
            case 3:
                gailuaDB.setIraupena(Integer.parseInt(balioB));
                break;
            case 4:
                gailuaDB.setKontsumoa(Float.parseFloat(balioB));
                break;
            
        }
    	
    	gailuakB.update(gailuaDB);
    }
    public void programaEzabatu(int programaId) {
		
		Erregistroa e = hB.find(programaId);
		hB.erregistroaEzabatuDB(e);
		
	}

    public List <KontsumoaJB> kontsumoaKalkulatu(LocalDateTime data1,LocalDateTime data2) {
    	
    		List <Erregistroa> erregistroak= new ArrayList<Erregistroa>();
    		List <KontsumoaJB> kontsumoak;
    		LocalDateTime data = data1;
    		float kontsumoa;
    		float prezioa;
    		long egunak = 0;
    		
    		erregistroak = hB.queryFindData(data1,data2);
    		
    		
    		while(data.isBefore(data2)) {
    			data.plusDays(egunak);
    			for(Erregistroa erregistroa : erregistroak) {
    				if(erregistroa.getData().getDayOfMonth()==data.getDayOfMonth()) {
    					//kontsumoa+=erregistroa
    				}
    			}
    			egunak++;
    		}
    		//plusdays		
    		return kontsumoak;
    }

}
 