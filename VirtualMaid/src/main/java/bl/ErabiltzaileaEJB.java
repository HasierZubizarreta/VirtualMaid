package bl;

import java.time.LocalDateTime;
import java.util.List;

import dl.Erregistroa;
import dl.GailuakB;
import dl.HistorialaB;
import dl.PrezioakOrdukoB;

public class ErabiltzaileaEJB {
	
	HistorialaB hB = new HistorialaB();



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
    public List<GailuakB> etxekoGailuakLortu() {
       
        return null;
        
    }
    public void programaBerriaGorde(int ordua, int gailuaId) {

        
    }
    public void programaEditatu(int ordua, int gailuaId) {

        
    }
    public void gailuBerriaSortu(GailuakB gailua) {

        
    }
    public void gailuaEzabatu(int gailuaId) {

        
    }
    public void gailuaEditatu(int gailuaId) {

        
    }

}
