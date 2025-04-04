package aeds3;

import pucflix.view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner console;

        // Menu
        try {
            console = new Scanner(System.in);
            int option;
            do {
                System.out.println("\n\nPucFlix");
                System.out.println("-------");
                System.out.println("-> Start");
                System.out.println("\n1 - Movies");
                System.out.println("\n2 - Episodes");
                System.out.println("0 - Quit");

                System.out.print("\nOption: ");
                try {
                    option = Integer.valueOf(console.nextLine());
                } catch(NumberFormatException e) {
                    option = -1;
                }

                switch (option) {
                    case 1:
                        (new SeriesMenu()).menu();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (option != 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}