package databaseFramework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class CSVFileReader {

    String csvFile = "poem_data.csv";

    public CSVFileReader() {
    }

    public static void readFile(String csvFile) {
        
        //Database db = new Database();

        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFile));

            List<String[]> rows = csvReader.readAll();

            for (String[] row : rows) {
                String author = row[0];
                String title = row[1];
                String text = row[2];
                
                //create poem object here
                // Poem poem = new Poem(author, title, text);
                
                //add it to a database [Abigail]
               
                
                System.out.println();

                System.out.println(author);
                System.out.println(title);
                
                System.out.println("------------------");

                System.out.println(text);
            }

//            while ((nextRecord = csvReader.readNext()) != null) {
//                for (String cell : nextRecord) {
//                    System.out.print(cell + "\t");
//                }
//            }

            // don't read the header file
//            br.readLine();

//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] values = line.split(csvSeparator);
//                
//                System.out.println(Arrays.toString(values));
//
//                System.out.println("Author: " + values[0] + "\n" + " Title: " + values[1] + "\n" + " Content: "
//                        + values[2] + "\n");
//            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CsvValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CsvException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String csvFile = "poem_data.csv";
        CSVFileReader.readFile(csvFile);

    }
}
