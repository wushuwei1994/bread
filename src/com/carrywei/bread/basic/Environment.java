package com.carrywei.bread.basic;


import java.util.Arrays;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/6
 **/
public class Environment {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        System.out.println(System.getenv().get("name1"));
        System.out.println(System.getenv().get("name2"));
        System.out.println(System.getenv().get("name3"));

    }
}
