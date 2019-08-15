package com.jiepi.concurrency.fight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CAS", "CEA", "HNA");

    public static void main(String[] args) {
       List<String> results =     search("sh","bj");
        System.out.println("================");
        results.forEach(System.out::println);

    }

    private static List<String> search(String origial, String dest) {
        final List<String> result = new ArrayList<>();

        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(origial, dest, f)).collect(Collectors.toList());

        tasks.forEach(Thread::start);

        tasks.forEach(it -> {

            try {
                it.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;

    }

    private static FightQueryTask createSearchTask(String origin, String destination, String airline) {
        return new FightQueryTask(origin, destination, airline);
    }
}
