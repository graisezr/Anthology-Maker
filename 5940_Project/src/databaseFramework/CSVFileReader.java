package databaseFramework;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVFileReader {

    public CSVFileReader() {
        
    }

    public static List<Poem> readCSVFile(String fileName) {
        //key: author
        //value: list of poems associated with that author 
//        HashMap<String, List<Poem>> map = new HashMap<String, List<Poem>>();
        
        List<Poem> poems = new ArrayList<Poem>();

        try {
            
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            
            //read all data in CSV file at once
            List<String[]> rows = csvReader.readAll();
            
            //remove the header file (column titles)
            rows.remove(0);
            
            //iterate through each row in CSV file
            for (String[] arr : rows) {
                // obtain the author, title, and poem text for each row
                String author = arr[0].trim();
                String title = arr[1].trim();
                String content = arr[2];
                
                
//              //create new Poem object and add it to list of poems associated with that author
              poems.add(new Poem(author, title, content));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        
        //return list of poems
    
        return poems;
    }

//    public static void main(String[] args) {
//        HashMap<String, List<Poem>> map = readCSVFile("poem_data.csv");
//        for (String key : map.keySet()) {
//            List<Poem> poems = map.get(key);
//            for (Poem p : poems) {
//                System.out.println(p.getAuthor());
//            }
//        }
//    }
}
