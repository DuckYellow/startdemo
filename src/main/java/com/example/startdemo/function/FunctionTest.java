package com.example.startdemo.function;

import org.apache.poi.ss.formula.functions.T;

import java.util.Objects;
import java.util.function.Function;

public class FunctionTest<In, Out> {

    private Function<In, Out> processor = new Function<In, Out>() {
        @Override
        public Out apply(In in) {
            return (Out) new String("apply:" + in);
        }
    };



    public static void main(String[] args) {
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);

        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);
    }
}
