package dl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GailuakB {
	/**
	 * Produktuen taula den CSV fitxategitik, produktu guztiak irakurtzen ditu eta zerrenda modura itzuli
	 * @return Fitxategiko produktu guztiekin osatutako zerrenda
	 */
	//Este con csv
	/*public static List<GailuaJB> guztiakIrakurri() {
	    List<GailuaJB> gailuakJB = new ArrayList<GailuaJB>();
	    File fitxategiaCSV = new File(PaketekoKonstanteak.gailuakFitxategiIzena);
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(fitxategiaCSV));
	        String lerroa;
	        while ((lerroa = br.readLine()) != null) {
	            String[] datuak = lerroa.split(";");
	            //int idGailua = Integer.parseInt(datuak[0]);
	            //String izena = datuak[1];
	            //String mota = datuak[2];
	            //int iraupena = Integer.parseInt(datuak[3]);
	            //float konsumoa = Float.parseFloat(datuak[4]);
	            GailuaJB gailuaJB = new GailuaJB(Integer.parseInt(datuak[0]), datuak[1], datuak[2], Integer.parseInt(datuak[3]), Float.parseFloat(datuak[4]));
	            gailuakJB.add(gailuaJB);
	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return gailuakJB;
	}*/
	
	public List<GailuaJB> guztiakIrakurri() {
	    List<GailuaJB> gailuakJB = new ArrayList<>();
	    try {
	        FileInputStream fis = new FileInputStream(PaketekoKonstanteak.gailuakFitxategiIzena);
	        DataInputStream dis = new DataInputStream(fis);

	        while(dis.available() > 0) {
	            int intField = dis.readInt();
	            String stringField1 = dis.readUTF();
	            String stringField2 = dis.readUTF();
	            int intField2 = dis.readInt();
	            float floatField = dis.readFloat();
	            gailuakJB.add(new GailuaJB(intField, stringField1, stringField2, intField2, floatField));
	        }

	        dis.close();
	        fis.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return gailuakJB;
	}
	
	
	/**
	 * Produktuen taula den CSV fitxategian, produktuen zerrenda bateko guztiak idazten ditu, aurretik zegoen guztia ezabatu ondoren
	 * @param gailuaJB  Fitxategiak eduki behar dituen produktu guztien zerrenda
	 */	
	/*public void guztiakIdatzi(List<GailuaJB> gailuakJB) {
		try {
			PrintWriter wr = new PrintWriter(new FileWriter(PaketekoKonstanteak.gailuakFitxategiIzena));

			GailuaJB gailuaJB;
			for(int i=0;i<gailuakJB.size();i++) {
				gailuaJB=gailuakJB.get(i);
				wr.println(gailuaJB.toCSV());
			}
			
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public void guztiakIdatzi(List<GailuaJB> gailuakJB) {
	    try {
	        FileOutputStream fos = new FileOutputStream(PaketekoKonstanteak.gailuakFitxategiIzena);
	        DataOutputStream dos = new DataOutputStream(fos);

	        for(GailuaJB gailuaJB : gailuakJB) {
	            dos.writeInt(gailuaJB.getIdGailua());
	            dos.writeUTF(gailuaJB.getIzena());
	            dos.writeUTF(gailuaJB.getMota());
	            dos.writeInt(gailuaJB.getIraupena());
	            dos.writeFloat(gailuaJB.getKontsumoa());
	        }

	        dos.close();
	        fos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Produktuen taularen amaieran produktu berri bat sartzen du, bere idProduktua eremurako balio berria kalkulatuz (taulako azkenarena + 1)	
	 * @param gailuaJB  Produktuen taularen amaieran sartu beharreko produktu berria
	 */	
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

	/**
	 * Produktuen taulan idProduktua balio zehatz bat duena bilatzen du eta egotekotan, itzuli egiten du; ez badago, null itzultzen du
	 * @param idProduktua  Bilaketa egiteko balio zehatza
	 * @return Topatutako produktua edo null
	 */
	public GailuaJB find(int idGailua) {
		GailuaJB topatutakoaJB=null;
		
		List<GailuaJB> gailuakJB=guztiakIrakurri();		
		
		boolean topatua=false;
		GailuaJB gailuaJB=null;
		int i=0;
		while(i<gailuakJB.size()&&!topatua) {
			gailuaJB=gailuakJB.get(i);
			if(gailuaJB.getIdGailua()==idGailua)
				topatua=true;
			else
				i++;
		}
		
		if(topatua)
			topatutakoaJB=gailuaJB;
		
		return topatutakoaJB;		
	}
	
	/**
	 * Produktuen taulan produktu zehatz bat aldatzen du, parametro bezala jasotakoarekin; produktua taulan ez badago, ez du ezer egiten
	 * @param aldatua  Aldatu beharreko produktua, balio berriekin (idProduktua eremuan izan ezik)
	 */
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
	
	public void gailuaEzabatuDB(int idGailua) {
	    List<GailuaJB> gailuakJB = guztiakIrakurri();

	    Iterator<GailuaJB> iterator = gailuakJB.iterator();
	    while (iterator.hasNext()) {
	        GailuaJB gailuaJB = iterator.next();
	        if (gailuaJB.getIdGailua()==idGailua) {
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
