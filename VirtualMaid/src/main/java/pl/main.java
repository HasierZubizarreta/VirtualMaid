package pl;


import java.util.List;
import java.util.Scanner;

import bl.ErabiltzaileaEJB;
import dl.GailuaJB;

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
                //aukera02();
                break;
            case 3:
                //aukera03();
                break;
            case 4:
                aukera04();
                break;
            
        }
        
        }

        System.out.println("programa amaituta");
    } 
    static void aukera01() {

        System.out.println("\n\t\tPROGRAMA BERRIA");

        System.out.println("\n\n\tAukeratu ordu bat edo sakatu 25 menu nagusira itzultzeko.\n");

        List<Float> prezioak = eEJB.egunekoPrezioakLortu();

        for(int i=0;i<prezioak.size();i++){

        	if(i<10){
            	System.out.println("\n\t0"+i+":00 - " + prezioak.get(i)+"€");
            }
        	
        	else{
        		System.out.println("\n\t"+i+":00 - " + prezioak.get(i)+"€");
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

            int gailuaId = scanner.nextInt();

            eEJB.programaBerriaGorde(ordua,gailuaId);

        }
        return;

    }
//    static void aukera02() {
//
//        System.out.println("\n\t\tEGUNEKO PROGRAMAK");
//
//        // LISTA DENBORAREN MENPE EGON BEHAR DA ORDENATUTA
//
//        List<HistorialaB> egunekoak =  eEJB.egunekoProgramakLortu();
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
//        
//    }
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
        int gailuaID;
    
        switch(aukera){

                default:
                    break;
                case 3:
                    //GailuakB gailua = new GailuakB();
                    // Gailu berria hasieratu
                    GailuaJB gailuakB;
              	  	String [] datuak = {"1","ayman","sadiki","0","22"};
              	  	gailuakB=new GailuaJB(Integer.parseInt(datuak[0]), datuak[1], datuak[2], Integer.parseInt(datuak[3]), Float.parseFloat(datuak[4]));
              	  
                    eEJB.gailuBerriaSortu(gailuakB);
                    break;
                case 1:
                    gailuaID = lortuID();
                    eEJB.gailuaEditatu(gailuaID);
                    break;
                case 2:
                    gailuaID = lortuID();
                    eEJB.gailuaEzabatu(gailuaID);
                    break;
        }
    }
    
    static int lortuID(){
        
        System.out.println("\n\n\tAukeratu gailu bat edo sakatu 0 menu nagusira itzultzeko.\n");

        return scanner.nextInt();


    }

}

