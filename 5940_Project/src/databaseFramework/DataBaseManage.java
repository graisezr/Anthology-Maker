package databaseFramework;

//Build 3 Hashmaps 

//Hashmap 1: Maps authors to list of poems of that author [DONE]
//Hashmap 2: Maps themes to lisf of poems of that theme
//Hashmap 3: Maps forms to list of poems of that form

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DataBaseManage {

    // we will build our hashmaps off of this list
    private static List<Poem> allPoems = CSVFileReader.readCSVFile("poem_data.csv");

    // Hashmap 1
    static HashMap<String, List<Poem>> authorMap;

    // Hashmap 2
    private static HashMap<String, List<Poem>> themeMap;

    // Hashmap 3
    private static HashMap<String, List<Poem>> formMap;

    // set of poems that were already written
    private static Set<Poem> writtenPoems = new HashSet<Poem>();

    public DataBaseManage() {
        this.authorMap = createAuthorMap(allPoems);
        this.themeMap = createThemeMap(allPoems);
        this.formMap = createFormMap(allPoems);

    }

    public static HashMap<String, List<Poem>> createAuthorMap(List<Poem> poems) {
        return authorMap;
//        if (map.containsKey(author)) {
//            poems = map.get(author);
//        } else {
//            //if author does not exist yet, create a new list of poems 
//            poems = new ArrayList<Poem>();
//        }
//        //create new Poem object and add it to list of poems associated with that author
//        poems.add(new Poem(author, title, content));
//        map.put(author, poems);
//    }
    }

    public static HashMap<String, List<Poem>> createThemeMap(List<Poem> poems) {
        return themeMap;
        // theme variable: this.theme

    }

    public static HashMap<String, List<Poem>> createFormMap(List<Poem> poems) {
        // create a hashmap that maps theme to poem
        HashMap<String, List<Poem>> map = new HashMap<String, List<Poem>>();

        // iterate through poem list that was created in CSVFileReader
        for (Poem poem : poems) {
            String form = poem.getForm();
            List<Poem> poemList = new ArrayList<Poem>();
//            System.out.println(poem.getForm());
            if (map.containsKey(form)) {
                poemList = map.get(form);
            } else {
                poemList = new ArrayList<Poem>();
//                System.out.println("New form");
            }
            poemList.add(poem);
            map.put(form, poemList);
        }

        return map;
    }

    public static void searchByTheme(Scanner sc) {
        // search through theme hashmap
    }

    public static void searchByForm(Scanner sc) {
        System.out.println("Please input form:");
        String form = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String key : formMap.keySet()) {
            if (key.toLowerCase().contains(form.toLowerCase())) {
                poems.addAll(formMap.get(key));
            }
        }
        // revisit for other methods
        if (poems.isEmpty()) {
            System.out.println("This form does not exist. Please try again");
        }

        String msg = "Search by form " + form + ":" + "\n";
        write(poems, msg);
    }

    // search through form hashmap

    public static void searchByAuthor(Scanner sc) {
        System.out.println("Please input author:");
        String author = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String key : authorMap.keySet()) {
            if (key.toLowerCase().contains(author.toLowerCase())) {
                poems.addAll(authorMap.get(key));
            }
        }
        // revisit for other methods
        if (poems.isEmpty()) {
            System.out.println("This author does not exist. Please try again");
        }

        String msg = "Search by author " + author + ":" + "\n";
        write(poems, msg);
    }

    public static void searchByTitle(Scanner sc) {
        System.out.println("Please input poem title:");
        String title = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String author : authorMap.keySet()) {
            for (Poem p : authorMap.get(author)) {
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
        String word = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String author : authorMap.keySet()) {
            for (Poem p : authorMap.get(author)) {
                if (p.getTextString().toLowerCase().contains(word.toLowerCase())) {
                    poems.add(p);
                }
            }
        }
        String msg = "Search by poem content " + word + ":" + "\n";
        write(poems, msg);
    }

    public static void write(List<Poem> poems, String msg) {
        // check if poem has already been written
        for (Poem poem : poems) {
            if (!writtenPoems.contains(poem)) {
                try {
                    FileWriter fw = new FileWriter(new File("poem_anthology.txt"), true);
                    fw.write("\n");
                    fw.write(msg);
                    int index = 1;
                    fw.write("Number of poems found in current search: " + poems.size() + "\n");
                    fw.write("-----------------" + index + "----------------\n");
                    fw.write(poem.toString() + "\n");
                    index++;

                    // add poem to poems that are already written in our anthology

                    writtenPoems.add(poem);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
//            else {
//                //add message: "The poem is already in your anthology"
//                System.out.println(poem.getTitle() + " by " + poem.getAuthor() + " is already in your anthology.");
//            }

        }

    }

}
