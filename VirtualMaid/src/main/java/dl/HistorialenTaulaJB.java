package dl;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialenTaulaJB {
	
	public List<HistorialaJB> queryFindData(LocalDateTime data1,LocalDateTime data2){
		return null;
		
		
	}
	
	public List<HistorialaJB> guztiakIrakurri() {
		return null;
	}
	
	public void historialZaharraBorratu (int year) {
		List<HistorialaJB> historialZaharra = new ArrayList<HistorialaJB>();
		List<HistorialaJB> historialBerria = new ArrayList<HistorialaJB>();
		
		historialZaharra=guztiakIrakurri();
		
		for(HistorialaJB hist: historialZaharra) {
			if(hist.getData().getYear()==year) {
				historialBerria.add(hist);
			}
		}
		
		try(DataOutputStream out = new DataOutputStream(new FileOutputStream("Historiala.bin"))){
			for(HistorialaJB hist: historialBerria) {
				out.writeInt(hist.getData().getYear());
				out.writeInt(hist.getData().getMonthValue());
				out.writeInt(hist.getData().getDayOfMonth());
				out.writeInt(hist.getData().getHour());
				out.writeInt(hist.getData().getMinute());
				out.writeInt(hist.getData().getSecond());
				out.writeUTF(hist.getGailua());
				out.writeFloat(hist.getPrezioa());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<HistorialaJB> egunekoProgramaLortu() {
		return null;
	}
}