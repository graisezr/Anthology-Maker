package databaseFramework;

//Build 3 Hashmaps

//Hashmap 1: Maps authors to list of poems of that author [DONE]
//Hashmap 2: Maps themes to lisf of poems of that theme
//Hashmap 3: Maps forms to list of poems of that form.

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
    
    /*
     * Private instance variables
     */

    // We will build our HashMaps off of this list
    private List<Poem> allPoems;

    // HashSet of poems to be written out as part of finalized anthology
    private HashSet<Poem> writtenOutPoems = new HashSet<>();

    // Maps authors to poems
    static HashMap<String, List<Poem>> authorMap;

    // Maps themes to poems.
    private static HashMap<String, List<Poem>> themeMap;

    // Maps poetic forms to poems.
    private static HashMap<String, List<Poem>> formMap;

    // Set of poems that were already written
    private static Set<Poem> writtenPoems = new HashSet<>();

    // Initial HashMap mapping themes (e.g., love, death, etc.) to HashSet of words (see IPoem)
    private HashMap<String, HashSet<String>> themesToWords = new HashMap<>();
    
    /*
     * Constructor
     */
    
    public DataBaseManage() {
        // Construct the initial map of themes to words
        setThemesToWords(themesToWords);
        // Construct the list of poems
        allPoems = CSVFileReader.readCSVFile("poem_data.csv");
        // Populate the poems' themes
        populateThemes();
        // Create the map of authors to poems
        DataBaseManage.authorMap = createAuthorMap(allPoems);
//      this.themeMap = createThemeMap(allPoems);
        DataBaseManage.formMap = createFormMap(allPoems);

    }
    
    /*
     * Methods
     */

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
    
    /**
     * Gets the themes of a poem by comparing each word of the poem with the
     * constant HashSet of words associated to a theme.
     * @param body of a given poem.
     * @return the list of a poem's theme(s).
     */
    public Set<String> determineThemes(String body) {
        // Initialize list of themes to be returned
        Set<String> themes = new HashSet<>();

        // Convert body into an array of words w/o non-alphanumeric characters
        String[] words = body.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");
        // Iterate through every word of the poem
        for (String word : words) {
            // Convert this word into lower case
            word.toLowerCase();
            // Iterate through the keys in the THEMES map
            for (String theme : this.themesToWords.keySet()) {
                // If current word belongs to current theme
                if (this.themesToWords.get(theme).contains(word)) {
                    // Add this theme to the list of themes
                    themes.add(theme);
                }
            }
        }

        // If no words belonged to any theme, add the theme 'other' to the list
        if (themes.isEmpty()) {
            themes.add(getThemesArray()[getThemesArray().length - 1]);
        }

        return themes;
    }
    
    /**
     * For each poem, sets its theme(s) by calling determineThemes().
     */
    public void populateThemes() {
        // Iterate through the list of poems in this database
        for (Poem poem : allPoems) {
            // Determine this poem's themes and store in a HashSet
            Set<String> poemThemes = determineThemes(poem.getTextString());
            // Set this poem's themes accordingly
            poem.setThemes(poemThemes);
        }
    }
    
    /**
     * Populates the map of themes to poems (private instance variable) given a list of poems.
     * @param poems whose themes are added to themeMap.
     */
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

    public static HashMap<String, List<Poem>> createFormMap(List<Poem> poems) {
        // create a hashmap that maps theme to poem
        HashMap<String, List<Poem>> map = new HashMap<>();

        // iterate through poem list that was created in CSVFileReader
        for (Poem poem : poems) {
            String form = poem.getForm();
            List<Poem> poemList = new ArrayList<>();
//            System.out.println(poem.getForm());
            if (map.containsKey(form)) {
                poemList = map.get(form);
            } else {
                poemList = new ArrayList<>();
//                System.out.println("New form");
            }
            poemList.add(poem);
            map.put(form, poemList);
        }

        return map;
    }
    
    /**
     * Returns a list of poems that contain a theme picked by the user.
     * The list of poems is then meant to be displayed in the console (titles are shown).
     * @param sc is user's input, represents the selection of a theme
     * @return a list of poems which contain the user's choice of theme.
     */
    public static List<Poem> searchByTheme(Scanner sc) {
        // Display message for user to pick  a theme
        System.out.println("Please input the theme of the poems you would like to add to your anthology:");
        // Get the user's choice
        String selectedTheme = sc.nextLine();
        // Add all poems containing this theme to a list of poems to be returned
        List<Poem> poems = new ArrayList<>();
        for (String theme : themeMap.keySet()) {
            if (theme.equals(selectedTheme)) {
                poems.addAll(themeMap.get(theme));
            }
        }
        
        return poems;
    }

    public static List<Poem> searchByForm(Scanner sc) {
        System.out.println("Please input form:");
        String form = sc.nextLine();
        List<Poem> poems = new ArrayList<>();
        for (String key : formMap.keySet()) {
            if (key.toLowerCase().contains(form.toLowerCase())) {
                poems.addAll(formMap.get(key));
            }
        }


//
//
//  String msg = "Search by form " + form + ":" + "\n";
//  write(poems, msg);

        return poems;
    }

    // search through form hashmap

    public static void searchByAuthor(Scanner sc) {
        System.out.println("Please input author:");
        String author = sc.nextLine();
        List<Poem> poems = new ArrayList<>();
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
        List<Poem> poems = new ArrayList<>();
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
        List<Poem> poems = new ArrayList<>();
        for (String author : authorMap.keySet()) {
            for (Poem p : authorMap.get(author)) {
                if (p.getTextString().toLowerCase().contains(word.toLowerCase())) {
                    poems.add(p);
                }
            }
        }
//        String msg = "Search by poem content " + word + ":" + "\n";
//        write(poems, msg);
    }

    public static void write(List<Poem> poems, String msg) {
        // check if poem has already been written

        if (poems.isEmpty()) {
            System.out.println("No poems have been written to your anthology");
            System.out.println();

        } else {
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
    
    /*
     * Getters and setters
     */
    
    public List<Poem> getAllPoems() {
        return this.allPoems;
    }

    public HashSet<Poem> getWrittenOutPoems(){
        return this.writtenOutPoems;
    }
    
    @Override
    public HashMap<String, HashSet<String>> getThemesToWords() {
        return this.themesToWords;
    }

//    public static void deletePoemFromMainList(Poem poem) {
//        allPoems.remove(poem);
//    }

}
