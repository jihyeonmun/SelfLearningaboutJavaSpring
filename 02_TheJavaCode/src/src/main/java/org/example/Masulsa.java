package org.example;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {
    public static void main(String[] args) {
//        try {
//            new ByteBuddy().redefine(Moja.class)
//                    .method(named("pullOUt")).intercept(FixedValue.value("Rabbit!"))
//                    .make().saveIn(new File("/Users/Programming/Java/JavaSpringGit/02_TheJavaCode/src/target/classes/"));
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        System.out.println(new Moja().pullOut());
    }
}