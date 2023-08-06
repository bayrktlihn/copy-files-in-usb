package io.bayrktlihn.copyfilesinusb;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CopyFilesInUsbApplication {
    public static void main(String[] args) {


        Properties properties = getApplicationProperties();


        String usbPath = properties.getProperty("usb_path") == null ? JOptionPane.showInputDialog("Usb Path") : properties.getProperty("usb_path");
        String copyPath = properties.getProperty("copy_path") == null ? JOptionPane.showInputDialog("Copy Path") : properties.getProperty("copy_path");
        String containKey = properties.getProperty("contain_key") == null ? JOptionPane.showInputDialog("Which key contains in file name") : properties.getProperty("contain_key");

        try {
            if (usbPath != null && copyPath != null && containKey != null) {
                boolean flag = true;
                while (flag) {
                    int timeout;
                    File usbRoot = new File(usbPath);

                    if (usbRoot.exists()) {
                        List<File> files = FileHelper.findFiles(usbRoot, containKey);

                        for (File file : files) {
                            Files.copy(file.toPath(), Paths.get(copyPath + "\\" + Randomizer.generateByDateTime() + file.getName()));
                        }

                        timeout = 30 * 60;


                    } else {
                        timeout = 7;
                    }

                    TimeUnit.SECONDS.sleep(timeout);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private static Properties getApplicationProperties() {
        try {
            Path pathFromApplicationPath = PathUtil.getPathFromApplicationPath("conf/application.properties");
            Properties properties = new Properties();
            if (Files.exists(pathFromApplicationPath)) {
                try (InputStream inputStream = Files.newInputStream(pathFromApplicationPath)) {
                    properties.load(inputStream);
                }
            }
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

