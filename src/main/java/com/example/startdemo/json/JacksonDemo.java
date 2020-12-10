package com.example.startdemo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.Arrays;

/**
 * @author xuweihang@qbb.com
 * @date 2019-06-28 13:38
 */
public class JacksonDemo {

    private Gson gson = new Gson();

    public static void main(String[] args) throws JsonProcessingException {
//        Student student = new Student();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(student);
//        System.out.println(json);

        String a="修改了供应商 原先的id=[{\"id\":2,\"spuId\":560,\"supplierId\":1}] 新的id=[2,3]";
        System.out.println(Arrays.asList(a));
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
