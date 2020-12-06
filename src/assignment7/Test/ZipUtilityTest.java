import assignment7.ZipUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.zip.ZipEntry;

public class ZipUtilityTest {
    private List<ZipEntry> listAll,
                           listFiltered;

    private List<ZipEntry> listContentAll,
                           listContentFiltered;

    @Before
    public void init() {
        listAll = ZipUtility.searchByName(new File("src/assignment7/TestData/ZipUtility/test.zip"), "", true);
        listFiltered = ZipUtility.searchByName(new File("src/assignment7/TestData/ZipUtility/test.zip"), "a", true);

        listContentAll = ZipUtility.searchByContent(new File("src/assignment7/TestData/ZipUtility/test.zip"), "", true);
        listContentFiltered = ZipUtility.searchByContent(new File("src/assignment7/TestData/ZipUtility/test.zip"), "text", true);
    }

    @Test
    public void testSearchByName() {
        Assert.assertEquals(4,
                listAll.size());
        Assert.assertEquals(2,
                listFiltered.size());
    }

    @Test
    public void testSearchByContent() {
        Assert.assertEquals(4,
                listContentAll.size());
        Assert.assertEquals(1,
                listContentFiltered.size());
    }
}