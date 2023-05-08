package pl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import dl.Erregistroa;
import dl.GailuaJB;
import dl.GailuakB;
import dl.HistorialaB;
import dl.PrezioaJB;
import dl.PrezioakOrdukoB;

public class informazioaGehitu {
	
	private static GailuakB gailuakB = new GailuakB();
	private static List<PrezioaJB> prezioakOrduko = PrezioakOrdukoB.queryFindAllDB();
	static HistorialaB hB = new HistorialaB();
	public static final String[] gailuMotak = {"Labadora","Labea", "Bestelakoa"};
	private static final int ArrayList = 0;
	
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Random random = new Random();
        int minGailuKop = 0, maxGailuKop = 5, randomGailuKop;
        int minOrdua = 0, maxOrdua = 23, randomOrdua; 
        int minIdGailua = 1, maxIdGailua = 6, randomIdGailua; 

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            String formattedDate = date.format(formatter);
            
            randomGailuKop = random.nextInt((maxGailuKop - minGailuKop) + 1) + minGailuKop;
            randomOrdua = random.nextInt((maxOrdua - minOrdua) + 1) + minOrdua;
            randomIdGailua = random.nextInt((maxIdGailua - minIdGailua) + 1) + minIdGailua;
            
            for(int i=0; i<randomGailuKop;i++) {

//                System.out.println("Balioak: "+randomGailuKop+" "+randomIdGailua+" "+randomOrdua);

            	randomOrdua = random.nextInt((maxOrdua - minOrdua) + 1) + minOrdua;
            	randomIdGailua = random.nextInt((maxIdGailua - minIdGailua) + 1) + minIdGailua;
            	List<GailuaJB> gailuak = gailuakB.queryFindAll();
            	
            	int emaitza = programaBerriaGorde(gailuak.get(randomIdGailua-1).getIzena(), date, randomOrdua) ;
//            	System.out.println("Emaitza = "+emaitza);
            	
            	
            }
        }
        System.out.println("Amaituta");
    }
    public static int programaBerriaGorde(String gailuIzena, LocalDate eguna, int ordua) {
		Erregistroa e = erregistroaProgramatu(gailuIzena, eguna, ordua, 0);
    	if (e != null) {
			hB.persistDB(e);
			return 1; //programa gehitzen da
			}
    	else
    		return 2; //biharko prezioak ez ditugu
    }
    public static Erregistroa erregistroaProgramatu(String gailuIzena, LocalDate eguna, int ordua, int minutuak) {
    	Erregistroa e=null;
    	GailuaJB g = gailuakB.find(gailuIzena);
    	if((ordua*60 + minutuak + g.getIraupena() ) <= 1440){
	    	float prezioaOrduko;
			float iraupenaOrduko = g.getIraupena() / 60.0f;
			int orduak = (int) Math.floor(iraupenaOrduko);
			float prezioTotala = 0.0f;
			int i;
			for (i = 0; i < orduak; i++) {
				prezioaOrduko = PrezioakOrdukoB.findPrezioa(ordua + i);
				prezioTotala += prezioaOrduko;
			}
			float orduaDezimala = iraupenaOrduko - orduak;
			prezioaOrduko = PrezioakOrdukoB.findPrezioa(ordua ); // ERROREA EMATEN DU HONEK
			prezioTotala += orduaDezimala * prezioaOrduko;
			LocalDateTime data = LocalDateTime.of(eguna, LocalTime.of(ordua, minutuak));
			float kontsumoTotala = g.getKontsumoa() * g.getIraupena()/60.0f; 
			e = new Erregistroa(g.getIzena(), data, prezioTotala, kontsumoTotala); 
    	}
    	
		return e;
    }
}
