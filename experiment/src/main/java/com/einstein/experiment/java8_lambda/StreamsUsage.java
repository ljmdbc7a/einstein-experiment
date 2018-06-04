package com.einstein.experiment.java8_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class StreamsUsage {

//    Stream<String> stringStream = new


    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");

        long[] arrayOfLong = new long[20000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.sort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
    }
}
