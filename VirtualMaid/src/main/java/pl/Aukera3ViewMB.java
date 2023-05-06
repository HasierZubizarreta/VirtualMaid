package pl;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;
import dl.KontsumoaJB;

@Named
@ViewScoped
public class Aukera3ViewMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Erregistroa> erregistroaDB;
	private List <KontsumoaJB> kontsumoaJB;
	
	@EJB private ErabiltzaileaEJB eEJB;
	
	 public List<Erregistroa> historialaLortu(Aukera3FormMB a3FormMB) {
	        
		 	
	    	if(erregistroaDB==null) {
	    		
	    		Instant instantH = a3FormMB.getNoiztik().toInstant();
	    		Instant instantA = a3FormMB.getNora().toInstant();
	    		LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());
	    		LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());
	    		erregistroaDB = eEJB.historialaLortu(hasiera,amaiera);
	    		
	    	}
	    	
	    	return erregistroaDB;
	    }
	 
	 public List<KontsumoaJB> kontsumoaLortu(Aukera3FormMB a3FormMB){
		 
		 
		 if(kontsumoaJB==null) {
			 
			 	Instant instantH = a3FormMB.getNoiztik().toInstant();
	    		Instant instantA = a3FormMB.getNora().toInstant();
	    		LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());
	    		LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());
	    		kontsumoaJB = eEJB.egunOsokoKontsumoaKalkulatu(hasiera,amaiera);
			 
		 }
		 
		 return kontsumoaJB;
	 }
	 public String kontsumoaHilabetekaLortu(){
		
//		LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());
//		LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());
//		kontsumoaJB = eEJB.egunOsokoKontsumoaKalkulatu(hasiera,amaiera);
		
		String balioak = "[5, 1, 0, 20, 120, 2, 23,1,32,19,56,69]";
		
		 return balioak;
	 }
	 public String hilabetekoKontsumoaLortu(){
			
		 LocalDate fechaActual = LocalDate.now();
		 LocalDate primerDiaDelMes = fechaActual.withDayOfMonth(1);
		 Instant instantH = primerDiaDelMes.atStartOfDay(ZoneId.systemDefault()).toInstant();
		 LocalDateTime hasiera = LocalDateTime.ofInstant(instantH, ZoneId.systemDefault());

		 int diasEnElMes = fechaActual.lengthOfMonth();
		 LocalDate ultimoDiaDelMes = fechaActual.withDayOfMonth(diasEnElMes);
		 Instant instantA = ultimoDiaDelMes.atStartOfDay(ZoneId.systemDefault()).toInstant();
		 LocalDateTime amaiera = LocalDateTime.ofInstant(instantA, ZoneId.systemDefault());

		 kontsumoaJB = eEJB.egunOsokoKontsumoaKalkulatu(hasiera,amaiera);
			
		 String balioak = "[5, 1, 0, 20, 120, 2, 23,1,32,19,56,69]";
		
		 return balioak;
		 }
	 
	 public List<KontsumoaJB> astekoKontsumoaLortu(){
			
		 LocalDate fechaActual = LocalDate.now();
		 DayOfWeek diaDeLaSemanaActual = fechaActual.getDayOfWeek();
		 int diasDesdeLunesHastaHoy = diaDeLaSemanaActual.getValue() - 1;
		 LocalDate lunesDeEstaSemana = fechaActual.minusDays(diasDesdeLunesHastaHoy);
		 LocalDate lunesDeLaSiguienteSemana = lunesDeEstaSemana.plusDays(7);
		 LocalDateTime lunesDeEstaSemanaDateTime = lunesDeEstaSemana.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
		 LocalDateTime lunesDeLaSiguienteSemanaDateTime = lunesDeLaSiguienteSemana.atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();

		 return eEJB.egunOsokoKontsumoaKalkulatu(lunesDeEstaSemanaDateTime, lunesDeLaSiguienteSemanaDateTime);
		 
		 }
	 public List <KontsumoaJB> hilabetekaKontsumoaKalkulatu() {
		 
		 return eEJB.hilabetekaKontsumoaKalkulatu();
		 
	 }
	 public void resetView(){
	 
		 kontsumoaJB=null;
		 erregistroaDB=null;
	 }

}
