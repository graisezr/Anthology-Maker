package databaseFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataBaseManage extends IPoem {

    // List that stores all poems by reading the CSVFile in CSVFileReader
    // We will build our hashmaps off of this list
    private static List<Poem> allPoems = CSVFileReader.readCSVFile("poem_data.csv");

    //Hashmap 1: Maps authors to list of poems of that author
    static HashMap<String, List<Poem>> authorMap;

    //Hashmap 2: Maps themes to list of poems of that theme
    private static HashMap<String, List<Poem>> themeMap;

    //Hashmap 3: Maps forms to list of poems of that form.
    private static HashMap<String, List<Poem>> formMap;

    // Initial HashMap mapping themes (e.g., love, death, etc.) to HashSet of words
    // (see IPoem)
    private static HashMap<String, HashSet<String>> themesToWords = new HashMap<>();

    private Map<String, HashSet<String>> themeToWords = new HashMap<>();
   
    //index used to write out "page" number for each poem in the poem_anthology.txt
    private static int index = 1;
    
    public DataBaseManage() {
        // Construct the initial map of themes to words
        setThemesToWords(themesToWords);
        // Create the map of authors to poems
        this.authorMap = createAuthorMap(allPoems);
//      this.themeMap = createThemeMap(allPoems);
        this.formMap = createFormMap(allPoems);

    }

    public static HashMap<String, List<Poem>> createAuthorMap(List<Poem> poems) {
        HashMap<String, List<Poem>> authorMap=new  HashMap<String, List<Poem>>();

        for(Poem p:poems){
            List<Poem> subPoems=null;
            if(authorMap.containsKey(p.getAuthor())){
                subPoems=authorMap.get(p.getAuthor());
            }else{
                subPoems=new ArrayList<Poem>();
            }
            subPoems.add(p.getAuthor());
            authorMap.put(p.getAuthor(),subPoems);
        }
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

    public static void createThemeMap(List<Poem> poems) {
        // Iterate through the list of poems
        for (Poem poem : poems) {
            // Iterate through this poem's theme(s)
            for (String theme : poem.getThemes()) {
                // If this theme already featured in themeMap, add this poem as value
                if (themeMap.containsKey(theme)) {
                    themeMap.get(theme).add(poem);
                } else {
                    // Otherwise add both the theme and poem to the themeMap
                    List<Poem> themePoemList = new ArrayList<>();
                    themePoemList.add(poem);
                    themeMap.put(theme, themePoemList);
                }
            }
        }
    }
    
    /**
     * Creates a HashMap from poem list that maps forms to list of poems of that form.
     * @param poems : list of Poem Objects generated in CSV filereader.
     * @return HashMap that maps forms to list of poems of that form.
     */
    public static HashMap<String, List<Poem>> createFormMap(List<Poem> poems) {
        // create a hashmap that maps theme to poem
        HashMap<String, List<Poem>> map = new HashMap<String, List<Poem>>();

        // create hashmap by iterating through poem list that was created in CSVFileReader
        for (Poem poem : poems) {
            String form = poem.getForm();
            List<Poem> poemList = new ArrayList<Poem>();
            if (map.containsKey(form)) {
                poemList = map.get(form);
            } else {
                poemList = new ArrayList<Poem>();
            }
            poemList.add(poem);
            map.put(form, poemList);
        }

        return map;
    }

    public static List<Poem> searchByTheme(Scanner sc) {
        return null;
    }
    
    /**
     * Searches the HashMap that maps poems to a specific poetic form and returns a list of poems of a specific form.
     * 
     * @param sc
     * @return list of poems that match the form specified by the user. 
     */
    public static List<Poem> searchByForm(Scanner sc) {
        System.out.println("Please input form:");
        String form = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String key : formMap.keySet()) {
            if (key.toLowerCase().contains(form.toLowerCase())) {
                poems.addAll(formMap.get(key));
            }
        }
        return poems;
    }

    public static List<Poem> searchByAuthor(Scanner sc) {
        System.out.println("Please input author:");
        String author = sc.nextLine();
        List<Poem> poems = new ArrayList<Poem>();
        for (String key : authorMap.keySet()) {
            if (key.toLowerCase().contains(author.toLowerCase())) {
                poems.addAll(authorMap.get(key));
            }
        }
        return poems;
    }

    public static List<Poem> searchByTitle(Scanner sc) {
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
        return poems;
    }

    public static List<Poem> searchByPoemContent(Scanner sc) {
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
       
        return poems;
    }
    
    /**
     * Writes out passed in list of poems into the poem anthology text file.
     * @param poems
     */
    public static void write(List<Poem> poems) {
        // check if poem has already been written
        if (poems.isEmpty()) {
            System.out.println("No poems have been written to your anthology");
            System.out.println("-----------------------------------------------------------");
            System.out.println();
            //otherwise, write out poem
        } else {
            for (Poem poem : poems) {
                try {
                    FileWriter fw = new FileWriter(new File("poem_anthology.txt"), true);
                    fw.write("\n");
                    fw.write("-----------------" + index + "----------------\n");
                    fw.write(poem.toString() + "\n");
                    index++;
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
