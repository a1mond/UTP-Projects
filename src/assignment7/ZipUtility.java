package assignment7;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtility {
    private ZipUtility() {}

    public static List<ZipEntry> searchByName(File zip, String name, boolean isParallel) {
        try {
            if (!isZipFile(zip))
                throw new Exception("Passed file is not a zip-file");
            ZipFile zipFile = new ZipFile(zip);
            Predicate<ZipEntry> filterByName = e -> e.getName().contains(name);

            return searchHelper(zipFile, filterByName, isParallel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<ZipEntry> searchByContent(File zip, String content, boolean isParallel) {
        try {
            if (!isZipFile(zip))
                throw new Exception("Passed file is not a zip-file");
            ZipFile zipFile = new ZipFile(zip);
            Predicate<ZipEntry> filterByContent = e -> containsContent(zipFile, e, content);

            return searchHelper(zipFile, filterByContent, isParallel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static List<ZipEntry> searchHelper(ZipFile file, Predicate<ZipEntry> filter, boolean isParallel) {
        if (isParallel) {
            return file
                    .stream()
                    .parallel()
                    .filter(filter)
                    .collect(Collectors.toList());
        } else {
            return file
                    .stream()
                    .sequential()
                    .filter(filter)
                    .collect(Collectors.toList());
        }
    }
    private static boolean containsContent(ZipFile z, ZipEntry f, String content) {
        try {
            InputStream inputStream = z.getInputStream(f);
            return FileContentUtility.has(inputStream, content, (int) f.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isZipFile(File f) {
        try {
            if (f.isFile()) {
                ZipFile tmp = new ZipFile(f);
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
