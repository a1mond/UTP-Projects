package assignment7;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtility {
    private FileUtility() {}

    public static List<File> searchByName(File dir, String name, boolean isParallel) {
        try {
            if (check(dir))
                throw new Exception("Directory is not a directory or access is forbidden to it");

            Predicate<Path> filterByName = e -> e.toFile().getName().contains(name);

            return searchHelper(dir, filterByName, isParallel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<File> searchByContent(File dir, String content, boolean isParallel) {
        try {
            if (check(dir))
                throw new Exception("Directory is not a directory or access is forbidden to it");

            Predicate<Path> filterByContent = e -> containsContent(e.toFile(), content);

            return searchHelper(dir, filterByContent, isParallel);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static List<File> searchHelper(File dir, Predicate<Path> filter, boolean isParallel) {
        try {
            if (isParallel) {
                return Files
                        .walk(dir.toPath())
                        .parallel()
                        .filter(filter)
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .collect(Collectors.toList());
            } else {
                return Files
                        .walk(dir.toPath())
                        .sequential()
                        .filter(filter)
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static boolean containsContent(File f, String content) {
        try {
            if (f.isFile()) {
                InputStream inputStream = new FileInputStream(f);
                return FileContentUtility.has(inputStream, content, (int) f.length());
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    private static boolean check(File file) {
        return !file.isDirectory() || !file.canRead();
    }
}
