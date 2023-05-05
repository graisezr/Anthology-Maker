package databaseFramework;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PoemTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
      List<Poem> poems = CSVFileReader.readCSVFile("poem_data.csv");

    }

    @Test
    public void testPoem() {
    }

    @Test
    public void testFindTheme() {
    }

    @Test
    public void testFindStanzas() {
        List<Poem> poems = CSVFileReader.readCSVFile("poem_data.csv");
        
        //I Do Not Love You Except Because I Love You
        Poem pabloNerudaPoem1 = poems.get(0);
        
        assertEquals(4, pabloNerudaPoem1.findStanzas(pabloNerudaPoem1.getTextString()));

        
        //If You Forget Me
        Poem pabloNerudaPoem3 = poems.get(2);
        
        assertEquals(6, pabloNerudaPoem3.findStanzas(pabloNerudaPoem3.getTextString()));
        
        //
        Poem pabloNerudaPoem23 = poems.get(22);
        
        assertEquals(17, pabloNerudaPoem23.findStanzas(pabloNerudaPoem23.getTextString()));



    }

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
