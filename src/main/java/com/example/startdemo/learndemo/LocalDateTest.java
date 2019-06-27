package com.example.startdemo.learndemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class LocalDateTest {
    public static void main(String[] args) {
        Date date = Date.from(getLocalDateSettled(new Date(), 0, 0, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date today = c.getTime();
        System.out.println(today);
    }

    public static LocalDate getLocalDateSettled(Date date, Integer year, Integer month, Integer day) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return getLocalDateSettled(localDate, year, month, day);
    }

    public static LocalDate getLocalDateSettled(LocalDate localDate, Integer year, Integer month, Integer day) {
        if (Objects.nonNull(year)) {
            localDate = localDate.plusYears(year);
        }
        if (Objects.nonNull(month)) {
            localDate = localDate.plusMonths(month);
        }
        if (Objects.nonNull(day)) {
            localDate = localDate.plusDays(day);
        }
        return localDate;
    }

    public static void test(String[] args) {


        String str = "2019-01-111";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.LocalDate localDate = java.time.LocalDate.parse(str, formatter);
        System.out.println(localDate);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(str);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Instant instant = Instant.now();
        System.out.println(instant);

    }


    public Date parse(String source) throws ParseException {
        int a = 1;
        if (source.equals("111"))
            throw new ParseException("Unparseable date: ", a);
        return new Date();
    }

    public String aaa() {
        try {
            parse("aaa");
        } catch (ParseException e) {

        } catch (DateTimeParseException ee) {
            throw ee;
        }
        return "a";
    }


}
