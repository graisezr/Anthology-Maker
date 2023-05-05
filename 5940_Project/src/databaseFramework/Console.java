package databaseFramework;

import java.util.Scanner;

// console: only user interaction
// calls methods in DataBaseManage Class


public class Console {

    public static void main(String[] args) {
        // takes in user input
        //consider invalid input
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu();
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                DataBaseManage.searchByAuthor(sc);
            } else if (choice == 2) {
                DataBaseManage.searchByTitle(sc);
            } else if (choice == 3) {
                DataBaseManage.searchByPoemContent(sc);
            } else if (choice == 4) {
                DataBaseManage.searchByTheme(sc);
            } else if (choice == 5) {
                DataBaseManage.searchByForm(sc);
            } else if (choice == 6) {
                break;
            }
        }
    }
    
    public static void menu() {
        System.out.println("Please input 1-4:");
        System.out.println("1 - Search poems by author");
        System.out.println("2 - Search poems by title");
        System.out.println("3 - Search poems by poem content");
        System.out.println("4 - Search poems by poem theme");
        System.out.println("5 - Search poems by poem form");
        System.out.println("6 - Exit");
    }

}
