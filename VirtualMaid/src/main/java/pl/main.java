package pl;

import java.util.Scanner;

public class main {

    public static void main(String args[]) {   

        int aukera = 1;

        Scanner scanner = new Scanner(System.in);

        while(aukera != 0 || aukera<6){
        
        System.out.println("Ongi etorri berriro erabiltzaile agurra.");
        System.out.println("MENU NAGUSIA:");
        System.out.println("\t00 - IRTEN\n\t01 - Iprograma berria\n\t02 - Ieguneko programak\n\t03 - Ihistoriala\n\t04 - Igailuak editatu\n");
        System.out.println("Aukera:");

        aukera = scanner.nextInt();

        }

        System.out.println("programa amaituta");
    } 
    
}
