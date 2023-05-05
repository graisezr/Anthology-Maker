package databaseFramework;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Poem object class.
 * 
 * @author
 *
 */
public class Poem {
    private String author;
    private String title;
    private String text;
    private List<String> themes;
    private String form;

    private int lineCount;

    public Poem(String author, String title, String textString) {
        super();
        this.author = author;
        this.title = title;
        this.text = textString;
        this.themes = findTheme(textString);
        this.form = findPoemForm(textString);
    }

    public List<String> findTheme(String text) {
        // list of constant words
        List<String> listOfThemes = new ArrayList<String>();
        return listOfThemes;
    }
    
    
    // FINDING A POEM'S FORM
    
    public static String findPoemForm(String poem) {
        List<String> lines = Arrays.asList(poem.split("\\r?\\n"));
        List<Integer> stanzaLineCounts = new ArrayList<>();
        List<List<Integer>> syllableCountsPerLine = new ArrayList<>();
        List<Integer> currentStanzaSyllableCounts = new ArrayList<>();
        
        int lineCount = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                stanzaLineCounts.add(lineCount);
                syllableCountsPerLine.add(currentStanzaSyllableCounts);
                currentStanzaSyllableCounts = new ArrayList<>();
                lineCount = 0;
            } else {
                currentStanzaSyllableCounts.add(countSyllables(line));
                lineCount++;
            }
        }
        
        stanzaLineCounts.add(lineCount);
        syllableCountsPerLine.add(currentStanzaSyllableCounts);

        return "Stanza line counts: " + stanzaLineCounts + "\nSyllable counts per line: " + syllableCountsPerLine;
    }

    private static int countSyllables(String line) {
        String[] words = line.split(" ");
        int syllableCount = 0;
        
        for (String word : words) {
            syllableCount += countWordSyllables(word);
        }
        
        return syllableCount;
    }

    private static int countWordSyllables(String word) {
        // This is a simple syllable counting method that does not account for all linguistic rules.
        // You can replace this with a more accurate syllable counting library or API.
        int count = 0;
        word = word.toLowerCase();
        
        if (word.length() == 0) {
            return 0;
        }
        
        if (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o' || word.charAt(0) == 'u') {
            count++;
        }
        
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u') {
                if (!(word.charAt(i - 1) == 'a' || word.charAt(i - 1) == 'e' || word.charAt(i - 1) == 'i' || word.charAt(i - 1) == 'o' || word.charAt(i - 1) == 'u')) {
                    count++;
                }
            }
        }
        
        if (word.charAt(word.length() - 1) == 'e') {
            count--;
        }
        
        return count;
    }
    
    ///////////////////////////
    
    public int findStanzas(String text) {
        //assume there's at least 1 stanza
        int stanzaCount = 1; 
        
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n' && i != 0 && text.charAt(i - 1) == '\n') {
                stanzaCount++;
            }
        }
        
        return stanzaCount;
    }
    
    public int findWordSyllables(String word) {
        
        word = word.toLowerCase();
        int syllables = 0;
        boolean lastCharVowel = false;

        for (char c : word.toCharArray()) {
            boolean isVowel = "aeiouy".indexOf(c) >= 0;

            if (isVowel && !lastCharVowel) {
                syllables++;
            }

            lastCharVowel = isVowel;
        }

        if (word.endsWith("e")) {
            syllables--;
        }

        if (syllables == 0) {
            syllables = 1;
        }
        
        return syllables;
    }

    public String findForm(String text) {

        int numberOfLines = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                numberOfLines++;
            }
        }
        // add 1 to account for the last line
        numberOfLines++;

        switch (numberOfLines) {
        case 1:
            return "Monostich";
        case 2:
            return "Couplet";
        case 3:
            return "Tercet";
        case 4:
            return "Quatrain";
        case 5:
            return "Cinquain";
        case 6:
            return "Sestet";
        case 8:
            return "Octave";
        case 14:
            return "Sonnet";
        default:
            return "Free verse";
        }

    }
    
    
    
    ///////////////////////////


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getTextString() {
        return text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTextString(String textString) {
        this.text = textString;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\nTitle: " + title + "\n" + "\n" + text;
    }
}