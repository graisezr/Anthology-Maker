package databaseFramework;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PoemTest {
    
    List<Poem> poems = CSVFileReader.readCSVFile("poem_data.csv");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPoem() {
    }

    @Test
    public void testFindTheme() {
    }

//    @Test
//    public void testFindStanzas() {
//        List<Poem> poems = CSVFileReader.readCSVFile("poem_data.csv");
//        
//        //I Do Not Love You Except Because I Love You, by Pablo Neurda
//        Poem pabloNerudaPoem0 = poems.get(0);
//        
//        assertEquals(14, pabloNerudaPoem0.getLineCount());
//        assertEquals("Sonnet", pabloNerudaPoem0.getForm());
//        
//        Poem pabloNerudaPoem1 = poems.get(1);
//        assertEquals(14, pabloNerudaPoem1.getLineCount());
//        assertEquals("Sonnet", pabloNerudaPoem1.getForm());
//
//        //If You Forget Me
//        Poem pabloNerudaPoem3 = poems.get(2);
//        assertEquals(48, pabloNerudaPoem3.getLineCount());
//        assertEquals("Free verse", pabloNerudaPoem3.getForm());
//
//        
////        Poem pabloNerudaPoem23 = poems.get(22);
//    }

    @Test
    public void testFindForm() {
    }

    @Test
    public void testGetAuthor() {
    }

    @Test
    public void testGetTitle() {
    }

    @Test
    public void testGetTextString() {
    }

    @Test
    public void testSetAuthor() {
    }

    @Test
    public void testSetTitle() {
    }

    @Test
    public void testSetTextString() {
    }

    @Test
    public void testToString() {
    }

}
