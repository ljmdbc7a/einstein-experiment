package com.einstein.experiment.java8_datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUsage {

    public static void main(String[] args) {
        // LocalDate and LocalTime is human easy reading without timeZone info.
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localDate);
        System.out.println(localTime);

        // Instant is computer reading (timestamp) without timeZone info.
        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());

        // ZoneId is present for timeZone.
        ZoneId zoneId = ZoneId.of("UTC");
        ZoneId.getAvailableZoneIds().forEach(e-> System.out.println(e));
        System.out.println(zoneId);

        // ZonedDateTime is combine LocalDateTime with ZoneId.
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.getZone());
        System.out.println(zonedDateTime);

        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(zonedDateTime1);
        ZonedDateTime zonedDateTime2 = zonedDateTime1.withZoneSameLocal(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime2);

        // DateTimeFormatter
        System.out.println("---------------DateTimeFormatter--------------");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.getZone());
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);

        LocalDateTime localDateTime1 = LocalDateTime.parse("2017-11-29 14:58:19", dateTimeFormatter);
        System.out.println(localDateTime1);
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(localDateTime1, ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime3);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime4 = ZonedDateTime.parse("2017-11-11T10:20:20.000Z", dateTimeFormatter1);
        System.out.println(zonedDateTime4);

        // How to convert ZoneId
        System.out.println("---------------withZoneSameInstant--------------");
        ZonedDateTime zonedDateTime5 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime5);
        ZonedDateTime utcDate = zonedDateTime5.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println(utcDate.getZone());

        // Convert to Date class from JDK8
        ZonedDateTime zonedDateTime6 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai"));
        Date date = new Date(zonedDateTime6.toInstant().toEpochMilli());
        System.out.println(date);

    }
}
