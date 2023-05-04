package databaseFramework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class CSVFileReader {

    public CSVFileReader() {
        
    }

    public static HashMap<String, List<Poem>> readCSVFile(String fileName) {
        //key: author
        //value: list of poems associated with that author 
        HashMap<String, List<Poem>> map = new HashMap<String, List<Poem>>();

        try {
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            
            //read all data in CSV file at once
            List<String[]> rows = csvReader.readAll();
            
            //remove the header file (column titles)
            rows.remove(0);
            
            //iterate through each row in CSV file
            for (String[] arr : rows) {
                // obtain the author, title, and poem text for each row
                String author = arr[0];
                String title = arr[1];
                String content = arr[2];
                
                List<Poem> poems = null;
                if (map.containsKey(author)) {
                    poems = map.get(author);
                } else {
                    //if author does not exist yet, create a new list of poems 
                    poems = new ArrayList<Poem>();
                }
                //create new Poem object and add it to list of poems associated with that author
                poems.add(new Poem(author, title, content));
                map.put(author, poems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap<String, List<Poem>> map = readCSVFile("poem_data.csv");
        for (String key : map.keySet()) {
            List<Poem> poems = map.get(key);
            for (Poem p : poems) {
                System.out.println(p.getAuthor());
            }
        }
    }
}
