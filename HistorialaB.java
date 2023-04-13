package dl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialaB {
	
	
	private void erregistroGuztiakIdatzi(List<Erregistroa> historiala) {
		
		String fName = PaketekoKonstanteak.historialaFitxategiIzena;
        
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fName)))){
			
        	out.writeInt(historiala.size());
        	
        	for (Erregistroa e : historiala)
        		out.writeObject(e);
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public  List<Erregistroa> erregistroGuztiakIrakurri(){
		
		String fName = PaketekoKonstanteak.historialaFitxategiIzena;
		List <Erregistroa> historiala = new ArrayList<Erregistroa>();
		
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fName)))){
			int size = in.readInt();
			
			for (int i=0; i<size; i++) {
				Erregistroa e = (Erregistroa) in.readObject();
				historiala.add(e);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historiala;
	}
	
	public void persistDB(Erregistroa e){
		List <Erregistroa> hist = erregistroGuztiakIrakurri();
		hist.add(e);
		erregistroGuztiakIdatzi(hist);
	}
	
	public  List<Erregistroa> queryFindData(LocalDateTime hasiera, LocalDateTime bukaera){
		
		List<Erregistroa> hist = erregistroGuztiakIrakurri();
		List <Erregistroa> hist2 = new ArrayList<Erregistroa>();
		
		for (Erregistroa e : hist) {
			if (e.getData().isAfter(hasiera) && e.getData().isBefore(bukaera))
				hist2.add(e);
		}
		
		return hist2;
	}
	
	public int erregistroaEzabatuDB(Erregistroa e) {
		List <Erregistroa> hist = erregistroGuztiakIrakurri();
		
		if(hist.contains(e)) {
			hist.remove(e);
			erregistroGuztiakIdatzi(hist);
			return 1;
		}
		else {
			return 0;
		}
			
	}
		
		
	
	
	
	
	
}
