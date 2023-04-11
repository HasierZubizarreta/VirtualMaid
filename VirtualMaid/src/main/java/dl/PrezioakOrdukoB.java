package dl;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrezioakOrdukoB {
	
	public List <Float> queryFindAllDB() {
		List <Float> prezioak = new ArrayList<Float>();
		String k;
		String[] d;
		try {
			FileReader f = new FileReader(PaketekoKonstanteak.prezioakFitxategiIzena); 
			BufferedReader b = new BufferedReader(f); 
			
			b.readLine();
			while((k = b.readLine())!=null) { 
				d=k.split(",");
				prezioak.add(Float.parseFloat(d[1]));
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return a;
	}

}
