package com.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        test();
    }

    static void test() {


    }

    private static String getNextLetter() {
        return Character.valueOf((char)ThreadLocalRandom.current().nextInt('a', 'z')).toString();
    }

}
