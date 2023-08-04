package controller;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstantDateTimeInfo {
    public static void main(String[] args) {
//        Instant instant = Instant.now();
//
//        // Lấy giờ từ Instant
//        String hour = getTimeStringToInstance(instant, 1);
//        System.out.println("Hour: " + hour);
//
//        // Lấy ngày từ Instant
//        String day = getTimeStringToInstance(instant, 2);
//        System.out.println("Day: " + day);
//
//        // Lấy năm từ Instant
//        String year = getTimeStringToInstance(instant, 3);
//        System.out.println("Year: " + year);
//
//        ZonedDateTime localDateTime = ZonedDateTime.now();
//        System.out.println(localDateTime);

        System.out.println(getByDayAndHour("2023-08-04","16:39"));
        System.out.println(getNow());
    }

    public static String getTimeStringToInstance(Instant instant, int infoType) {
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

    public static Instant getNow(){
        return Instant.now();
    }

    public static Instant getByStringDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        // Đặt giờ, phút, giây và millisecond thành 0 để có ngày giờ cụ thể (00:00:00)
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static Instant getByDayAndHour(String dateStr, String timeStr){
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Chuyển đổi định dạng thời gian và ngày thành LocalDateTime
            LocalTime time = LocalTime.parse(timeStr, timeFormatter);
            LocalDate date = LocalDate.parse(dateStr, dateFormatter);
            LocalDateTime localDateTime = LocalDateTime.of(date, time);

            // Chuyển đổi LocalDateTime thành ZonedDateTime với múi giờ địa phương (ZoneId.systemDefault())
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

            // Chuyển đổi ZonedDateTime thành Instant
            return zonedDateTime.toInstant();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}
