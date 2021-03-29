package com.example.startdemo.function;

abstract class Bank {

    public void takeNumber() {
        System.out.println("排队取号");
    }

    public abstract void transact();

    public void evaluate() {
        System.out.println("反馈、评价");
    }

    public final void process() {
        this.takeNumber();
        this.transact();
        this.evaluate();
    }

    public static void main(String[] args) {
        Person1 person1=new Person1();
        person1.process();
    }
}


class Person1 extends Bank {

    @Override
    public void transact() {
        System.out.println("我要存款");
    }

}

class Person2 extends Bank {

    @Override
    public void transact() {
        System.out.println("我要取款");

    }

}