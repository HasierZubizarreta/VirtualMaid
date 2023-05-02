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
    public int programaBerriaGorde(String gailuIzena, int ordua) {
    	GailuaJB g = gailuakB.find(gailuIzena);
    	if((LocalTime.now().getHour()*60 + g.getIraupena() <= 1440)) {
	    	if (LocalTime.now().getHour() < ordua) {
				float prezioaOrduko;
				float iraupenaOrduko = g.getIraupena() / 60.0f;
				int orduak = (int) Math.floor(iraupenaOrduko);
				float prezioTotala = 0.0f;
				int i;
				for (i = 0; i < orduak; i++) {
					prezioaOrduko = PrezioakOrdukoB.findPrezioa(ordua + i);
					prezioTotala += prezioaOrduko;
				}
				float orduaDezimala = iraupenaOrduko - orduak;
				prezioaOrduko = PrezioakOrdukoB.findPrezioa(ordua + i);
				prezioTotala += orduaDezimala * prezioaOrduko;
				//LocalDateTime data = LocalDateTime.of(LocalDate.now(), LocalTime.of(ordua, 0));
				LocalDateTime data = LocalDateTime.of(LocalDate.of(2023, 05, 01), LocalTime.of(ordua, 0));
				float kontsumoTotala = g.getKontsumoa() * g.getIraupena();
				Erregistroa e = new Erregistroa(gailuIzena, data, prezioTotala, kontsumoTotala);
				hB.persistDB(e);
				return 1; //programa gehitzen da
				}
	    	else
	    		return 2; //iraganean ezin da programatu
	    	}
    	else
    		return 0; //hurrengo eguneko prezioak ez ditugu
	}
    
    public void programaEditatu(Erregistroa eBerria) {

   	 Erregistroa eZaharra = hB.find(eBerria.getId());
//   	 LocalDateTime data = LocalDateTime.of(LocalDate.now(), LocalTime.parse());
//   	 e.setData(data);
 	 hB.updateDB(eBerria); 	 
 	 
   	 
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
    
    public void gailuaEditatuM(String gailuIzena, int aldaketa, String balioB) {

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
    public void gailuaEditatu(GailuaJB gailuaDB) {

    	gailuakB.update(gailuaDB);
    }
    public void programaEzabatu(int programaId) {
		
		hB.erregistroaEzabatuDB(programaId);
		
	}

    public List <KontsumoaJB> egunOsokoKontsumoaKalkulatu(LocalDateTime data1,LocalDateTime data2) {
    	
    		List <Erregistroa> erregistroak = new ArrayList<Erregistroa>();
    		List <KontsumoaJB> kontsumoak = new ArrayList<KontsumoaJB>();
    		LocalDateTime data = data1;
    		
    		erregistroak = hB.queryFindData(data1,data2);
    		
    		
    		while(data.isBefore(data2)) {
    			float egunOsokoKontsumoa = 0;
        		float egunOsokoprezioa = 0;
    			for(Erregistroa erregistroa : erregistroak) {
    				if(erregistroa.getData().getDayOfMonth()==data.getDayOfMonth()) {
    					egunOsokoKontsumoa+=erregistroa.getKontsumoa();
    					egunOsokoprezioa+=erregistroa.getPrezioa();
    				}
    			}
    			KontsumoaJB kontsumoa = new KontsumoaJB(data, egunOsokoKontsumoa, egunOsokoprezioa);
    			kontsumoak.add(kontsumoa);
    			data = data.plusDays(1);
    		}
    		
    		return kontsumoak;
    }

}
 