package io.bayrktlihn.copyfilesinusb;


import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CopyFilesInUsbApplication {
    public static void main(String[] args) {

        String usbPath = JOptionPane.showInputDialog("Usb Path");
        String copyPath = JOptionPane.showInputDialog("Copy Path");
        String containKey = JOptionPane.showInputDialog("Which key contains in file name");

        try {
            if (usbPath != null && copyPath != null && containKey != null) {
                boolean flag = true;
                while (flag) {
                    int timeout;
                    File usbRoot = new File("D:\\");

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


}

