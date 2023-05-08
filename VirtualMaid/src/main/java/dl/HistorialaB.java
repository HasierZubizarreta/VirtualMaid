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
import java.util.Collections;
import java.util.Iterator;
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

	public Erregistroa find(int idErregistroa) {
	
		List <Erregistroa> hist = erregistroGuztiakIrakurri();
		for (Erregistroa e : hist) {
			if(e.getId() == idErregistroa) {
				return e;
			}
		}
		Erregistroa topatugabe = null;
		return topatugabe;
			
	}
	
	public void persistDB(Erregistroa e){
		List <Erregistroa> hist = erregistroGuztiakIrakurri();
		int idBerria;
		if(hist.size()==0)
			idBerria=1;
		else {
			Erregistroa azkenE=hist.get(hist.size()-1);
			idBerria=azkenE.getId()+1;
		}
		
		e.setId(idBerria);
		hist.add(e);
		Collections.sort(hist);
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
	
	public int erregistroaEzabatuDB(int idErregistroa) {
		List <Erregistroa> hist = erregistroGuztiakIrakurri();
		Iterator<Erregistroa> iterator = hist.iterator();
		while (iterator.hasNext()) {
	        Erregistroa e2 = iterator.next();
	        //System.out.print(e2.toString());
	        if (e2.getId() == idErregistroa) {
	            iterator.remove();
	            erregistroGuztiakIdatzi(hist);
	            return 1;
	        }
	    }
	    return 0;
	    
	    
			
	}
		
	public void updateDB(Erregistroa eBerria) {
		List<Erregistroa> hist=erregistroGuztiakIrakurri();
		
		boolean topatua=false;
		Erregistroa e=null;
		int i=0;
		while(i<hist.size()&&!topatua) {
			e=hist.get(i);
			if(e.getId()==eBerria.getId())
				topatua=true;
			else
				i++;
		}
	
		if(topatua) {
			hist.set(i, eBerria);
			Collections.sort(hist);
			erregistroGuztiakIdatzi(hist);
		}
	}
	
}