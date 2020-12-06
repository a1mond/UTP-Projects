package assignment7;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JarUtility {
    private JarUtility() {}

    public static List<JarEntry> searchByName(File jar, String name, boolean isParallel) {
        try {
            if (!isJarFile(jar))
                throw new Exception("Passed file is not a jar-file");
            JarFile jarFile = new JarFile(jar);
            Predicate<JarEntry> filterByName = e -> e.getName().contains(name);

            return searchHelper(jarFile, filterByName, isParallel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<JarEntry> searchByContent(File jar, String content, boolean isParallel) {
        try {
            if (!isJarFile(jar))
                throw new Exception("Passed file is not a jar-file");
            JarFile jarFile = new JarFile(jar);
            Predicate<JarEntry> filterByContent = e -> containsContent(jarFile, e, content);

            return searchHelper(jarFile, filterByContent, isParallel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static List<JarEntry> searchHelper(JarFile file, Predicate<JarEntry> filter, boolean isParallel) {
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
    private static boolean containsContent(JarFile j, JarEntry f, String content) {
        try {
            InputStream inputStream = j.getInputStream(f);
            return FileContentUtility.has(inputStream, content, (int) f.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isJarFile(File f) {
        try {
            if (f.isFile()) {
                JarFile tmp = new JarFile(f);
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
