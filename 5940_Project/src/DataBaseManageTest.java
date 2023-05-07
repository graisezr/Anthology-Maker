package databaseFramework;

import org.junit.Test;

public class DataBaseManageTest {

    @Test
    public void testCreateAuthorMap() {
        List<Poem> poems =CSVFileReader.readCSVFile("poem_data.csv");
        HashMap<String, List<Poem>> map=DataBaseManage.createAuthorMap(poems);
        assertTrue(map.size()>0)
  //testing fo databasemanage     