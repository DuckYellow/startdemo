package com.example.startdemo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-28 13:38
 */
public class JacksonDemo {
    public static void main(String[] args) throws JsonProcessingException {
        Student student = new Student();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(student);
        System.out.println(json);
    }


    static class Student {

        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getID() {
            return id;
        }
    }
}
