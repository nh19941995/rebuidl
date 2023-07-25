package controller;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstantDateTimeInfo {
    public static void main(String[] args) {
        Instant instant = Instant.now();

        // Lấy giờ từ Instant
        String hour = getTime(instant, 1);
        System.out.println("Hour: " + hour);

        // Lấy ngày từ Instant
        String day = getTime(instant, 2);
        System.out.println("Day: " + day);

        // Lấy năm từ Instant
        String year = getTime(instant, 3);
        System.out.println("Year: " + year);
    }

    public static String getTime(Instant instant, int infoType) {
        LocalDateTime localDateTime = instant.atZone(ZoneOffset.UTC).toLocalDateTime();


        switch (infoType) {
            case 1: // Lấy giờ
                return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            case 2: // Lấy ngày
                return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            case 3: // Lấy năm
                return String.valueOf(localDateTime.getYear());
            default:
                throw new java.lang.IllegalArgumentException("Invalid info type. Use 1 for hour, 2 for day, or 3 for year.");
        }
    }
}
