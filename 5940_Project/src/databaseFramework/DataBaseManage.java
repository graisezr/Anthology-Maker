package databaseFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataBaseManage {

    static HashMap<String, List<Poem>> map = CSVFileReader.readCSVFile("poem_data.csv");

    public static void main(String[] args) {
        // takes in user input
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu();
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                searchByAuthor(sc);
            } else if (choice == 2) {
                searchByTitle(sc);
            } else if (choice == 3) {
                searchByPoemContent(sc);
            } else if (choice == 4) {
                break;
            }
        }
    }

    public static void menu() {
        System.out.println("Please input 1-4:");
        System.out.println("1 - Search poems by author");
        System.out.println("2 - Search poems by title");
        System.out.println("3 - Search poems by poem content");
        System.out.println("4 - Exit");
    }

    public static void searchByAuthor(Scanner sc) {
        System.out.println("Please input author:");
        String author = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String key : map.keySet()) {
            if (key.toLowerCase().contains(author.toLowerCase())) {
                poems.addAll(map.get(key));
            }
        }
        //revisit for other methods
        if(poems.isEmpty()) {
            System.out.println("This author does not exist. Please try again");
        }

        String msg = "Search by author " + author + ":" + "\n";
        write(poems, msg);
    }

    public static void searchByTitle(Scanner sc) {
        System.out.println("Please input poem title:");
        String title = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String author : map.keySet()) {
            for (Poem p : map.get(author)) {
                if (p.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    poems.add(p);
                }
            }
        }
        String msg = "Search by title " + title + ":" + "\n";
        write(poems, msg);
    }

    public static void searchByPoemContent(Scanner sc) {
        System.out.println("Please input poem content:");
        String title = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String author : map.keySet()) {
            for (Poem p : map.get(author)) {
                if (p.getTextString().toLowerCase().contains(title.toLowerCase())) {
                    poems.add(p);
                }
            }
        }
        String msg = "Search by poem content " + title + ":" + "\n";
        write(poems, msg);
    }

    public static void write(List<Poem> poems, String msg) {
        try {
            FileWriter fw = new FileWriter(new File("poem_anthology.txt"), true);
            fw.write("\n");
            fw.write(msg);
            int index = 1;
            fw.write("Number of poems found in current search: " + poems.size() + "\n");
            for (Poem p : poems) {
                fw.write("-----------------" + index + "----------------\n");
                fw.write(p.toString() + "\n");
                index++;
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
