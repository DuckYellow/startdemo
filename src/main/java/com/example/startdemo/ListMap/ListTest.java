package com.example.startdemo.ListMap;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> list1 = new LinkedList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.remove(2);
        System.out.println(list1.toString());

    }

    private static void sort() {

        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Student student = new Student();
            student.setId(i);
            studentList.add(student);
        }
        Student student = new Student();
        student.setId(1);
        studentList.add(student);

        studentList.sort((a, b) -> {
            if (a.getId() < b.getId()) {
                return -1;
            }
            return 1;
        });
        for (Student student1 : studentList) {
            System.out.println("res=" + student1.getId());
        }
    }

    private String testA() {
        String a = "1";
        List<String> bbList = new ArrayList<>();
        for (String s : bbList) {
            System.out.println(s);
        }
        bbList.forEach(s -> {
            System.out.println(s);
        });
        for (int i = 0; i < bbList.size(); i++) {
            System.out.println(bbList.get(i));
        }
        return a;
    }

    private static void testB() {
        List<Integer> list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list = list.subList(0, 2);

        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    @Data
    public static class Student {
        private Integer id;

        private String name;

        Student() {

        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Data
    public static class StudentSub extends Student {
        private Integer sex;

    }
}
