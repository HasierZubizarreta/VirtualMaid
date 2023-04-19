package bl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import dl.Erregistroa;
import dl.GailuaJB;
import dl.GailuakB;
import dl.HistorialaB;
import dl.PrezioakOrdukoB;

public class ErabiltzaileaEJB {
	
	//HistorialaB hB = new HistorialaB();
	private GailuakB gailuakB=new GailuakB();
	private List<Float> prezioakOrduko = PrezioakOrdukoB.queryFindAllDB();
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
   public List<Float> egunekoPrezioakLortu() {
        
        return prezioakOrduko;   
    }
    public List<GailuaJB> etxekoGailuakLortu() {
       
        return gailuakB.queryFindAll();
        
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

}
