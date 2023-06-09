package pl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bl.ErabiltzaileaEJB;
import dl.Erregistroa;
import dl.GailuaJB;
import dl.GailuaOrokorra;
import dl.PrezioaJB;

public class main {

    private static ErabiltzaileaEJB eEJB;
    private static Scanner scanner;

    public static void main(String args[]) {   

        int aukera = 1;

        scanner = new Scanner(System.in);

        eEJB = new ErabiltzaileaEJB();

        while(0<aukera && aukera<5){
        
        System.out.println("Ongi etorri berriro erabiltzaile agurra.");
        System.out.println("MENU NAGUSIA:");
        System.out.println("\t00 - IRTEN\n\t01 - programa berria\n\t02 - eguneko programak\n\t03 - historiala\n\t04 - gailuak editatu\n");
        System.out.println("Aukera:");

        aukera = scanner.nextInt();

        switch(aukera){

            case 1:
                aukera01();
                break;
            case 2:
                aukera02();
                break;
            case 3:
                //aukera03();
                break;
            case 4:
                aukera04();
                break;
            case 5:
                aukera05();
                break;
            
        }
        
        }

        System.out.println("programa amaituta");
    } 
    static void aukera01() {

        System.out.println("\n\t\tPROGRAMA BERRIA");

        System.out.println("\n\n\tAukeratu ordu bat edo sakatu 25 menu nagusira itzultzeko.\n");

        List<PrezioaJB> prezioak = eEJB.egunekoPrezioakLortu();

        for(int i=0;i<prezioak.size();i++){

        	if(i<10){
            	System.out.println("\n\t0"+i+":00 - " + prezioak.get(i).getPrezioa()+"€ - "+prezioak.get(i).getKolorea());
            }
        	
        	else{
        		System.out.println("\n\t"+i+":00 - " + prezioak.get(i).getPrezioa()+"€ - "+prezioak.get(i).getKolorea());
        	}

        }

        System.out.println("\n\t25:00 - IRTEN");

        int ordua = scanner.nextInt();

        if(ordua!=25){

        	List<GailuaJB> gailuakDB;
            gailuakDB=eEJB.etxekoGailuakLortu();

            System.out.println("\n\n\tAukeratu gailu bat bat edo sakatu 0 menu nagusira itzultzeko.\n");

            for(int i=0;i<gailuakDB.size();i++){

                System.out.println("\n\t" + gailuakDB.get(i));
    
            }

            String izena = scanner.next();

            eEJB.programaBerriaGorde(izena, ordua);

        }
        return;

    }
//    static void aukera02() {

//        System.out.println("\n\t\tEGUNEKO PROGRAMAK");
//
//        // LISTA DENBORAREN MENPE EGON BEHAR DA ORDENATUTA
//
//        List<Erregistroa> egunekoak =  eEJB.egunekoProgramakLortu();
//
//        int ordua = 0;
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("\n0:00 ->\n");
//        
//
//        // PAUSUAK: printeatu orduka programak -> begiratu erregistro bakoitzean ordua, ordua aldatzen bata -> printeatu ordu berria eta gero jarraitu programak printeatzen
//
//        for(int i = 0;i<24;i++){ // ALDATU i<egunekoak.size()
//
//            System.out.println(ordua);
//
//            if(ordua == now.getHour()){
//
//                // System.out.println("\tGailua: " + egunekoak.get(i).getGailuarenIzena());
//                System.out.println("Barruan");
//                ordua = ordua + 1; //KENDU
//            }
//            else{
//
//                for(int j = ordua+1;j<now.getHour();j++){
//
//                    System.out.println("\n"+j+":00 ->\n");
//                    System.out.println("Kanpoan FOR");
//
//                }
//                // KENDU
//                if(ordua < now.getHour()){
//                    ordua = now.getHour();
//                }
//                else{
//                    ordua = ordua+1;
//                }
//                System.out.println("\n"+ordua+":00 ->\n");
//                // System.out.println("\tGailua: " + egunekoak.get(i).getGailuarenIzena());
//                System.out.println("Kanpoan");
//
//            }
//
//        }
//
//        System.out.println("\n\n\tAukeratu gailu bat edo sakatu 0 menu nagusira itzultzeko.\n");
//        ordua = scanner.nextInt();
//        int gailua = scanner.nextInt();
//
//        if(ordua != 25){
//
//            eEJB.programaEditatu(ordua, gailua);
//
//        }
        
//    }
    
    static void aukera02() {
    	   List<Erregistroa> historiala=eEJB.egunekoProgramakLortu();
    	      //List<GailuakB> etxekoGailuak = eEJB.etxekoGailuakLortu();
    	      for (int i = 0; i < 24; i++) {
    	       StringBuilder sb = new StringBuilder();
    	       for (Erregistroa programa : historiala) {
    	           if (programa.getData().getHour() == i) {
    	               if (sb.length() > 0) {
    	                   sb.append(", ");
    	               }
    	               sb.append(programa.getId());
    	           }
    	       }
    	       if (sb.length() > 0) {
    	        if(i<10){
    	               System.out.println("\n0" + i + ":00 -> " + sb.toString());
    	                }
    	           
    	            else{
    	               System.out.println("\n" + i + ":00 -> " + sb.toString());
    	            }
    	       } else {
    	       
    	        if(i<10){
    	        System.out.println("\n0" + i + ":00 -> ");                
    	        }
    	           
    	            else{
    	            System.out.println("\n" + i + ":00 -> ");            
    	            }
    	       
    	       }
    	    }
    	     
    	      System.out.println("\n\n\tZer egin nahi duzu?\n\n\t\t00 - Ezer.\n\n\t\t01 - Editatu.\n\n\t\t02 - Ezabatu.\n");

    	      int aukera = scanner.nextInt();
    	      int ordua;
    	 
    	      switch(aukera){

    	              default:
    	                  break;
    	              case 1:
//    	                  ordua = lortuOrdua();
//    	                  System.out.println("\nZer editatu nahi duzu? \n\t 01 - Id-a.\n\t 02 - Ordua.\n");
//    	                  int aldaketa = scanner.nextInt();
//    	                  System.out.println("\nSartu balio berria:\n\t");
//    	                  int balioB = scanner.nextInt();
//    	                  eEJB.programaEditatu(ordua, aldaketa, balioB);
    	                  break;
    	              case 2:
    	                  ordua = scanner.nextInt();
    	                  eEJB.programaEzabatu(ordua);
    	                  break;
    	      }
    }
//    static void aukera03() {
//
//        System.out.println("\n\t\tHISTORIALA");
//
//        eEJB.laburpenaLortu();
//
//        System.out.println("\n\n\tSakatu edozein tekla menu nagusira itzultzeko.\n");
//        
//        scanner.nextByte();
//        
//    }
    static void aukera04() {

        System.out.println("\n\t\tGAILUAK EDITATU");
        List<GailuaJB> gailuakDB;
        gailuakDB=eEJB.etxekoGailuakLortu();
        //List<GailuakB> etxekoGailuak = eEJB.etxekoGailuakLortu();

        for(int i=0;i<gailuakDB.size();i++){

            System.out.println("\n\t" + gailuakDB.get(i));

        }
        
        System.out.println("\n\n\tZer egin nahi duzu?\n\n\t\t00 - Ezer.\n\n\t\t01 - Editatu.\n\n\t\t02 - Ezabatu.\n\n\t\t03 - Berria\n");

        int aukera = scanner.nextInt();
        String gailuaID;
    
        switch(aukera){

                default:
                    break;
                case 3:
                    //GailuakB gailua = new GailuakB();
                    // Gailu berria hasieratu
                    GailuaJB gailuakB;
                    System.out.println("\nSartu gailu berriaren izena:\n\t");
                    String izena = scanner.next();	
                    System.out.println("\nZe motatako gailua da?:\n\t");
                    // APLIKAZIOA HOBETZERAKOAN, HAMEN LISTA AGERTU BEHAR DA GAILU MOTEKIN
                    String mota = scanner.next();	
                    System.out.println("\nSartu gailu berriaren iraupena:\n\t");
                    String iraupena = scanner.next();	
                    System.out.println("\nSartu gailu berriaren kontsumoa:\n\t");	
                    String kontsumoa = scanner.next();	
                    System.out.println("\nGailu berria gordetzen...\n");

                    gailuakB=new GailuaOrokorra(Integer.parseInt("1"), izena, Integer.parseInt(iraupena), Float.parseFloat(kontsumoa));
              	  
                    eEJB.gailuBerriaSortu(gailuakB);
                    break;
                case 1:
                    gailuaID = lortuID();
                    System.out.println("\nZer editatu nahi duzu? \n\t 01 - Izena.\n\t 02 - Gailu mota.\n\t 03 - Iraupena.\n\t 04 - Kontsumoa.\n");
                    int aldaketa = scanner.nextInt();
                    System.out.println("\nSartu balio berria:\n\t");	
                    String balioB = scanner.next();	
                    eEJB.gailuaEditatuM(gailuaID, aldaketa, balioB);
                    break;
                case 2:
                    gailuaID = lortuID();
                    eEJB.gailuaEzabatu(gailuaID);
                    break;
        }
    }
    
    static String lortuID(){
        
        System.out.println("\n\n\tAukeratu gailu bat edo sakatu 0 menu nagusira itzultzeko.\n");

        return scanner.next();


    }
    static void aukera05() {
    	
    	List<List<String>> unitateak = new ArrayList<>(); 
    	
    	List<Erregistroa> erregistroakDB = eEJB.egunekoProgramakLortu();
    	System.out.println("Programak: "+erregistroakDB+"\n");	
    		for(int i=0; i<24;i++) {

    			List<String> orduka = new ArrayList<>();
	    		for(int j=0; j<erregistroakDB.size();j++) {
	    			
	    			LocalTime timeH = LocalTime.parse(erregistroakDB.get(j).getHasieraOrdua(), DateTimeFormatter.ofPattern("HH:mm"));
	    			LocalTime timeA = LocalTime.parse(erregistroakDB.get(j).getAmaieraOrdua(), DateTimeFormatter.ofPattern("HH:mm"));
	    			
	    			System.out.println("Orduak "+i+" : "+timeH.getHour()+" "+timeA.getHour()+"\n");	
	    			
	    			if(timeH.getHour()<= i && i <= timeA.getHour() && orduka.size()==0) {
	    				
	    				if(i<9) {
	    					orduka.add("0"+i+":00");
	    					orduka.add("0"+(i+1)+":00");
	    				}
	    				else if(i==9) {
	    					orduka.add("0"+i+":00");
	    					orduka.add(+(i+1)+":00");
	    				}
	    				else {
	    					orduka.add(i+":00");
	    					orduka.add(+(i+1)+":00");
	    				}
	    				orduka.add(erregistroakDB.get(j).getGailuIzena());
	    				
	    			}
	    			else if(timeH.getHour()<= i && i <= timeA.getHour() && orduka.get(2).length()<30) {
	    				
	    				String balioa = orduka.get(2);
	    				orduka.set(2,balioa+", "+erregistroakDB.get(j).getGailuIzena());
	    				
	    			}
	    			else if(timeH.getHour()<= i && i <= timeA.getHour() && orduka.get(2).length()>=30) {
	    				
	    				String balioa = orduka.get(2);
	    				orduka.set(2,balioa+", ...");
	    				break;
	    				
	    			}
    			
	    		}
	    		if(orduka.size()!=0) {
	    			
	    			unitateak.add(orduka);
	    			System.out.println("Lerro berria gordeta: "+orduka.get(0)+orduka.get(1)+orduka.get(2)+"\n");
	    			
	    		}
	    		
    		}
    		
    	}
    	
}
