package assignment7;

import java.io.InputStream;
import java.util.Scanner;

public class FileContentUtility {
    public static boolean has(InputStream inputStream, String content, int size) {
        return new Scanner(inputStream).findWithinHorizon(content, size) != null;
    }
}
