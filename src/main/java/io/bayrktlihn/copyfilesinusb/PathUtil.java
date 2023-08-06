package io.bayrktlihn.copyfilesinusb;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {
    public static Path getPathFromApplicationPath(String pathStr) {
        try {
            Path path = Paths.get(CopyFilesInUsbApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return path.resolve("../" + pathStr).normalize();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
