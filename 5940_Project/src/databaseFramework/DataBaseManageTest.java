package databaseFramework;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DataBaseManageTest {
    
    // Create new database of poems
    DataBaseManage db = new DataBaseManage();

    @Before
    public void setUp() throws Exception {
        System.out.println("Test");
    }
    
    /* ----------------------------------------------------------------------
     * Tests for DataBaseManage()
     * ----------------------------------------------------------------------
     */
    
    @Test
    public void testDataBaseManage() {
        // Check that the initial poems are correct
        
        // Poem 1
        String expectedTitle = "I Do Not Love You Except Because I Love You";
        assertEquals(expectedTitle, db.getAllPoems().get(0).getTitle());
        
        // Poem 2
        expectedTitle = "Love Sonnet XVII";
        assertEquals(expectedTitle, db.getAllPoems().get(1).getTitle());
    }
   
    /* ----------------------------------------------------------------------
     * Tests for createThemeMap()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testCreateThemeMap() {
        List<Poem> poems = CSVFileReader.readCSVFile("poem_data.csv");
        HashMap<String, List<Poem>> map=DataBaseManage.createAuthorMap(poems);
        assertTrue(map.size() > 0);
    }
    
    /* ----------------------------------------------------------------------
     * Tests for determineThemes()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testDetermineThemes() {
        // Check that the themes of the initial poems are correct
        
        // Poem 1: "I Do Not Love You Except Because I Love You" (should contain word love in it)
        Set<String> expectedThemes = new HashSet<>();
        expectedThemes.add("love");
        assertEquals(expectedThemes, db.getAllPoems().get(0).getThemes());
        
        // Poem 2: "Love Sonnet XVII" (should contain word love and soul (deathWords))
        expectedThemes.add("death");
        assertEquals(expectedThemes, db.getAllPoems().get(1).getThemes());
    }

    /* ----------------------------------------------------------------------
     * Tests for createAuthorMap()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testCreateAuthorMap() {
        List<Poem> poems =CSVFileReader.readCSVFile("poem_data.csv");
        HashMap<String, List<Poem>> map=DataBaseManage.createAuthorMap(poems);
        assertTrue(map.size()>0);
    }
    
    /* ----------------------------------------------------------------------
     * Tests for createFormMap()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testCreateFormMap() {
        fail("Not yet implemented"); // TODO
    }
    
    /* ----------------------------------------------------------------------
     * Tests for searchByTheme()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testSearchByTheme() {
        fail("Not yet implemented"); // TODO
    }

    /* ----------------------------------------------------------------------
     * Tests for searchByForm()
     * ----------------------------------------------------------------------
     */
    
    @Test
    public void testSearchByForm() {
        fail("Not yet implemented"); // TODO
    }
    
    /* ----------------------------------------------------------------------
     * Tests for searchByAuthor()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testSearchByAuthor() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testSearchByTitle() {
        fail("Not yet implemented"); // TODO
    }
    
    /* ----------------------------------------------------------------------
     * Tests for searchByPoemContent()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testSearchByPoemContent() {
        fail("Not yet implemented"); // TODO
    }
    
    /* ----------------------------------------------------------------------
     * Tests for write()
     * ----------------------------------------------------------------------
     */

    @Test
    public void testWrite() {
        fail("Not yet implemented"); // TODO
    }

}
