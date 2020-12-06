import assignment7.JarUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.jar.JarEntry;

public class JarUtilityTest {
    private List<JarEntry> listAll,
                       listFiltered;

    private List<JarEntry> listContentAll,
                       listContentFiltered;

    @Before
    public void init() {
        listAll = JarUtility.searchByName(new File("src/assignment7/TestData/JarUtility/test.jar"), "", true);
        listFiltered = JarUtility.searchByName(new File("src/assignment7/TestData/JarUtility/test.jar"), "a", true);

        listContentAll = JarUtility.searchByContent(new File("src/assignment7/TestData/JarUtility/test.jar"), "", true);
        listContentFiltered = JarUtility.searchByContent(new File("src/assignment7/TestData/JarUtility/test.jar"), "text", true);
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
