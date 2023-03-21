package pl;

import java.util.Scanner;

public class main {

    public static void main(String args[]) {   

        int aukera = 1;

        Scanner scanner = new Scanner(System.in);

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
