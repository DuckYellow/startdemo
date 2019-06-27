package com.example.startdemo.learndemo;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-19 13:53
 */
public class CalendarDemo {
    public static void main(String[] args) throws ParseException {
        aaa();
    }

    public static void aaa() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse("2019-01-02");
        Date date2 = format.parse("2019-03-02");
        List<Integer> list = getDateDiffList(date1, date2);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static List<Integer> getDateDiffList(Date past, Date present) {
        if (past == null || present == null || present.getTime() < past.getTime()) {
            return new ArrayList<>();
        }

        int year = 0, month = 0, day = 0;   //存储差值

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        int nowY = Integer.valueOf(format.format(present));
        int beforeY = Integer.valueOf(format.format(past));
        format = new SimpleDateFormat("MM");
        int nowM = Integer.valueOf(format.format(present));
        int beforeM = Integer.valueOf(format.format(past));
        format = new SimpleDateFormat("dd");
        int nowD = Integer.valueOf(format.format(present));
        int tempM = (nowY - beforeY) * 12 + nowM - beforeM;


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(past);
        calendar.add(Calendar.MONTH, tempM);
        int tempD = Integer.valueOf(format.format(calendar.getTime()));
        if (tempD <= nowD) {
            year = tempM / 12;
            month = tempM % 12;
            day = nowD - tempD;
        } else {
            year = (tempM - 1) / 12;
            month = (tempM - 1) % 12;
            calendar.add(Calendar.MONTH, -1);
            for (int i = 0; i < 31; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                if (Integer.valueOf(format.format(calendar.getTime())) == nowD) {
                    day = i + 1;
                    break;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        list.add(year);
        list.add(month);
        list.add(day);
        return list;
    }


    public static Date getDateAfterDay(Date startTime, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(startTime);
        now.set(5, now.get(5) + day);
        Date afterDate = now.getTime();
        return afterDate;
    }


    static class ActivityPhotoItemDTO {
        private Integer id;
        private Date time;

        public ActivityPhotoItemDTO() {

        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }
}
