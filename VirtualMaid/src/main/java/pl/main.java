package pl;

import java.util.List;
import java.util.Scanner;

import bl.ErabiltzaileaEJB;
import dl.GailuakB;
import dl.PrezioakOrdukoB;

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
        System.out.println("\t00 - IRTEN\n\t01 - Iprograma berria\n\t02 - Ieguneko programak\n\t03 - Ihistoriala\n\t04 - Igailuak editatu\n");
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
                aukera03();
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

        List<PrezioakOrdukoB> prezioak = eEJB.egunekoPrezioakLortu();

        for(int i=0;i<prezioak.size();i++){

            System.out.println("\n\t" + prezioak.get(i));

        }

        System.out.println("\n\t25:00 - IRTEN");

        int ordua = scanner.nextInt();

        if(ordua!=25){

            List<GailuakB> gailuak = eEJB.etxekoGailuakLortu();

            System.out.println("\n\n\tAukeratu gailu bat bat edo sakatu 0 menu nagusira itzultzeko.\n");

            for(int i=0;i<gailuak.size();i++){

                System.out.println("\n\t" + gailuak.get(i));
    
            }

            int gailuaId = scanner.nextInt();

            eEJB.programaBerriaGorde(ordua,gailuaId);

        }
        return;

    }
    static void aukera02() {

        System.out.println("\n\t\tEGUNEKO PROGRAMAK");
        
    }
    static void aukera03() {

        System.out.println("\n\t\tHISTORIALA");
        
    }
    static void aukera04() {

        System.out.println("\n\t\tGAILUAK EDITATU");
        
    }
    
}
