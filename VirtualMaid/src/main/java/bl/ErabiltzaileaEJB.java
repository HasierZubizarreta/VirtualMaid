package bl;

import java.time.LocalDateTime;
import java.util.List;

import dl.GailuaJB;
import dl.GailuakB;
import dl.HistorialaB;
import dl.HistorialaJB;
import dl.HistorialenTaulaJB;
import dl.PrezioakOrdukoB;

public class ErabiltzaileaEJB {
	
	//HistorialaB hB = new HistorialaB();
	private GailuakB gailuakB=new GailuakB();
	private PrezioakOrdukoB prezioakOrdukoB=new PrezioakOrdukoB();

	//aratz eta hasier
	HistorialenTaulaJB hB = new HistorialenTaulaJB();


	public void historialaBorratu() {
    	LocalDateTime data = LocalDateTime.now();
    	int yearNow = data.getYear();
    	
    	hB.historialZaharraBorratu(yearNow);
    }
	  public List<HistorialaJB> historialaLortu(LocalDateTime data1,LocalDateTime data2) {
	    	return hB.queryFindData(data1,data2);
	    }
	
	
	//aurreko bertsioa
    //public void historialZaharraBorratu() {    }
    //public List<Erregistroa> historialaSortu(LocalDateTime data1,LocalDateTime data2) { return hB.queryFindData(data1,data2);}  
   
	  
	  public void laburpenaLortu() {   
    	
    }
    public List<HistorialaB> egunekoProgramakLortu() {
        
        return null;
        
    }
    public List<Float> egunekoPrezioakLortu() {
        
        return prezioakOrdukoB.queryFindAllDB();
        
    }
    public List<GailuaJB> etxekoGailuakLortu() {
       
        return gailuakB.queryFindAll();
        
    }
    public void programaBerriaGorde(int ordua, int gailuaId) {

        
    }
    public void programaEditatu(int ordua, int gailuaId) {

        
    }
    public void gailuBerriaSortu(GailuaJB gailuakDB) {

    	gailuakB.persist(gailuakDB);
    }
    
    public void gailuaEzabatu(int gailuaId) {

        gailuakB.gailuaEzabatuDB(gailuaId);
    }
    
    public void gailuaEditatu(int gailuaId, int aldaketa, String balioB) {

    	GailuaJB gailuaDB= gailuakB.find(gailuaId);
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
