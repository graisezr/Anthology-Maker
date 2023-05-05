package databaseFramework;

import java.util.ArrayList;
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
        this.form = findForm(textString);
    }

    public List<String> findTheme(String text) {
        // list of constant words
        List<String> listOfThemes = new ArrayList<String>();
        return listOfThemes;
    }

    public String findForm(String text) {
        
        String form = "";
        
        return form;
    }

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