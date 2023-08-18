package com.example.startdemo.shejimoshi;

import com.example.startdemo.reflect.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demo {

    public static void main(String[] args) {
        Map<String, List<Car>> map = new HashMap<>();

        List<Car> list = new ArrayList<>();
        Car car1 = new Car();
        car1.setColor("a");

        list.add(car1);
        Car car2 = new Car();
        car1.setColor("b");
        list.add(car2);

        map.put("a", list);

        List<Car> lia = map.get("a");
        lia.remove(1);

        System.out.println(map);
    }
}
