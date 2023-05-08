package dl;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrezioakOrdukoB {
	
	public static List <PrezioaJB> queryFindAllDB() {
		List <PrezioaJB> prezioak = new ArrayList<PrezioaJB>();
		PrezioaJB prezioa;
		String k;
		String[] d;
		try {
			FileReader f = new FileReader(PaketekoKonstanteak.prezioakFitxategiIzena); 
			BufferedReader b = new BufferedReader(f); 
			
			b.readLine();
			int  ordua = 0;
			while((k = b.readLine())!=null) { 
				d=k.split(",");
				prezioa = new PrezioaJB(ordua,Float.parseFloat(d[1]),d[2]);
				prezioak.add(prezioa);
				ordua++;
			} 
			b.close(); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prezioak;
	}
	
	public static float findPrezioa(int ordua) {
		String k;
		String[] d;
		float a=0;
		try {
			FileReader f = new FileReader(PaketekoKonstanteak.prezioakFitxategiIzena); 
			BufferedReader b = new BufferedReader(f); 
			
			for (int i=0; i<=ordua; i++) {
				b.readLine();
			}
			k=b.readLine();
			d=k.split(",");
			a=Float.parseFloat(d[1]);
			b.close(); 
			f.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return a;
	}

}