package bl;

import java.time.LocalDateTime;
import java.util.List;

import dl.Erregistroa;
import dl.GailuaJB;
import dl.GailuakB;
import dl.HistorialaB;
import dl.PrezioakOrdukoB;

public class ErabiltzaileaEJB {
	
	HistorialaB hB = new HistorialaB();
	private GailuakB gailuakB=new GailuakB();



    public void historialZaharraBorratu() {


    }
    public List<Erregistroa> sortuPDFa(LocalDateTime data1,LocalDateTime data2) {
    	return hB.queryFindData(data1,data2);
        
    }
    public void laburpenaLortu() {

        
    }
    public List<HistorialaB> egunekoProgramakLortu() {
        
        return null;
        
    }
    public List<PrezioakOrdukoB> egunekoPrezioakLortu() {
        
        return null;
        
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
    
    public void gailuaEditatu(int gailuaId) {

    	GailuaJB gailuaDB= gailuakB.find(gailuaId);
    	gailuaDB.setIraupena(8); //Esto hay que cambiar y coger el dato del formulario.
    	gailuakB.update(gailuaDB);
    }

}
