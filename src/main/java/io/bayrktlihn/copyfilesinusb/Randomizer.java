package io.bayrktlihn.copyfilesinusb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public Randomizer() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String generateByDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return dateTimeFormatter.format(LocalDateTime.now()) + ThreadLocalRandom.current().nextInt(1, 1_000_000);
    }
}