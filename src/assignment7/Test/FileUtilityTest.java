import assignment7.FileUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileUtilityTest {
    private List<File> listAll,
                       listFiltered;

    private List<File> listContentAll,
                       listContentFiltered;

    @Before
    public void init() {
        listAll = FileUtility.searchByName(new File("src/assignment7/TestData/FileUtility"), "", true);
        listFiltered = FileUtility.searchByName(new File("src/assignment7/TestData/FileUtility"), "a", true);

        listContentAll = FileUtility.searchByContent(new File("src/assignment7/TestData/FileUtility"), "", true);
        listContentFiltered = FileUtility.searchByContent(new File("src/assignment7/TestData/FileUtility"), "text", true);
    }

    @Test
    public void searchByName() {
        Assert.assertEquals(4,
                listAll.size());
        Assert.assertEquals(2,
                listFiltered.size());
    }
    @Test
    public void searchByContent() {
        Assert.assertEquals(4,
                listContentAll.size());
        Assert.assertEquals(1,
                listContentFiltered.size());
    }
}
