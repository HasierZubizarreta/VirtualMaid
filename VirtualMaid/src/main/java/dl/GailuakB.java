package dl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GailuakB {

	
	public List<GailuaJB> guztiakIrakurri() {
		String fName = PaketekoKonstanteak.gailuakFitxategiIzena;
		List <GailuaJB> gailuak = new ArrayList<GailuaJB>();
		
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fName)))){
			int size = in.readInt();
			
			for (int i=0; i<size; i++) {
				GailuaJB g = (GailuaJB) in.readObject();
				gailuak.add(g);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gailuak;
	}
	
	
	
	public void guztiakIdatzi(List<GailuaJB> gailuak) {
		String fName = PaketekoKonstanteak.gailuakFitxategiIzena;
        
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fName)))){
			
        	out.writeInt(gailuak.size());
        	
        	for (GailuaJB g : gailuak)
        		out.writeObject(g);
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void persist(GailuaJB gailuaJB) {
		List<GailuaJB> gailuakJB=guztiakIrakurri();

		int idBerria;
		if(gailuakJB.size()==0)
			idBerria=1;
		else {
			GailuaJB azkenGailua=gailuakJB.get(gailuakJB.size()-1);
			idBerria=azkenGailua.getIdGailua()+1;
		}
		
		gailuaJB.setIdGailua(idBerria);
		gailuakJB.add(gailuaJB);

		guztiakIdatzi(gailuakJB);
		
		return;
	}


	public GailuaJB find(String gailuIzena) {
		GailuaJB topatutakoaJB=null;
		
		List<GailuaJB> gailuakJB=guztiakIrakurri();		
		
		boolean topatua=false;
		GailuaJB gailuaJB=null;
		int i=0;
		while(i<gailuakJB.size()&&!topatua) {
			gailuaJB=gailuakJB.get(i);
			if(gailuaJB.getIzena().equals(gailuIzena))
				topatua=true;
			else
				i++;
		}
		
		if(topatua)
			topatutakoaJB=gailuaJB;
		
		return topatutakoaJB;		
	}

	public void update(GailuaJB aldatua) {	
		List<GailuaJB> gailuakJB=guztiakIrakurri();
		
		boolean topatua=false;
		GailuaJB gailuaJB=null;
		int i=0;
		while(i<gailuakJB.size()&&!topatua) {
			gailuaJB=gailuakJB.get(i);
			if(gailuaJB.getIdGailua()==aldatua.getIdGailua())
				topatua=true;
			else
				i++;
		}

		if(topatua) {
			gailuakJB.set(i, aldatua);
			guztiakIdatzi(gailuakJB);
		}
		
		return;
	}
	
	public void gailuaEzabatuDB(String gailuIzena) {
		List<GailuaJB> gailuakJB = guztiakIrakurri();
	   
	    Iterator<GailuaJB> iterator = gailuakJB.iterator();
	    while (iterator.hasNext()) {
	        GailuaJB gailuaJB = iterator.next();
	        if (gailuaJB.getIzena().equals(gailuIzena)) {
	            iterator.remove();
	            break;
	        }
	    }
		guztiakIdatzi(gailuakJB);

	}
	
	public List<GailuaJB> queryFindAll() {
		return guztiakIrakurri();
	}

}