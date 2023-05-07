package databaseFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

// console: only user interaction
// calls methods in DataBaseManage Class

public class Console {

    private static HashSet<Poem> writtenOutPoems = new HashSet<Poem>();

    public static void main(String[] args) {
        // takes in user input
        // consider invalid input
        DataBaseManage db = new DataBaseManage();

        // ensures that the previous poetry anthology is cleared when creating a new one
        try {
            FileWriter fw = new FileWriter(new File("poem_anthology.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            menu();
            List<Poem> poemListSelection = new ArrayList<Poem>();
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

                poemListSelection = DataBaseManage.searchByForm(sc);

                String msg = "Search by: " + sc + ":" + "\n";

                if (poemListSelection.isEmpty()) {
                    System.out.println("This form does not exist.");
                } else {
                    List<Poem> poemListToWriteOut = displayPoemSelection(poemListSelection);
                    
                    //wirte out any poems
                    DataBaseManage.write(poemListToWriteOut, msg);
                }
                // while scanner is "yes", keep adding stuff to the poem

                // write out to file only if user selects Yes after choosing all of their poems

            } else if (choice == 6) {
                sc.close();
                break;
            }
        }
    }

    public static void menu() {
        System.out.println("Please input 1-4:");
        System.out.println("1 - Search poems by author");
        System.out.println("2 - Search poems by title");
        System.out.println("3 - Search poems by content");
        System.out.println("4 - Search poems by theme");
        System.out.println("5 - Search poems by poetic form");
        System.out.println("6 - Exit");
    }

    public static List<Poem> displayPoemSelection(List<Poem> poemListSelection) {

        Scanner sc = new Scanner(System.in);

        List<Poem> poemListToWriteOut = new ArrayList<Poem>();

        while (true) {

            // search by form should return a list

            // print out list
            for (Poem poem : poemListSelection) {
                System.out.println("[" + (poemListSelection.indexOf(poem) + 1) + "]" + "\"" + poem.getTitle()
                        + "\", by " + poem.getAuthor());
            }

            System.out.println();
            System.out.println("Please input a number between 1-" + poemListSelection.size()
                    + " to view a particular poem. Press 0 to return to the main menu.");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice > 0 && choice <= poemListSelection.size()) {

                // currently selected poem

                System.out.println(poemListSelection.get(choice - 1).getTitle() + "\", by "
                        + poemListSelection.get(choice - 1).getAuthor());
                System.out.println("\n" + poemListSelection.get(choice - 1).getTextString() + "\n");

                System.out.println("Would you like you to add this poem to your anthology (yes/no)?");

                String response = sc.nextLine().toLowerCase();

                if ("yes".equals(response)) {
                    if (!writtenOutPoems.contains(poemListSelection.get(choice - 1))) {
                        // add poem to poems to be written out
                        poemListToWriteOut.add(poemListSelection.get(choice - 1));
                        writtenOutPoems.add(poemListSelection.get(choice - 1));
                        System.out.println("Poem added to the new list.");
                        System.out.println("-----------------------------------------------------------");

                    } else {
                        System.out.println("This poem is already in your anthology");
                        System.out.println("-----------------------------------------------------------");

                    }
//                db.deletePoemFromMainList(poemListSelection.get(choice - 1));

                } else if ("no".equals(response)) {
                    continue;
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            } else {
                System.out.println("Returning back to main menu");
                break;
            }
        }
        return poemListToWriteOut;
    }

}
