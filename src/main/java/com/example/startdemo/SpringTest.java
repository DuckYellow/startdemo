package com.example.startdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweihang@qbb.com
 * @date 2019-05-18 21:23
 */
public class SpringTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.id = 1L;
        student.name = "1";
        update(student);
        System.out.println(student.id);
    }

    public static void update(Student student) {
        student.id = 3L;
    }

    static class Student {

        public Long id;
        public String name;
    }
}
