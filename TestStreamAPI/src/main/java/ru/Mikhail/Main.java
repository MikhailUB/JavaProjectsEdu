package ru.Mikhail;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        List<A> listAs = Arrays.asList(new A(1, Mode.First), new A(2, Mode.Second), new A(3, Mode.Second));

        List<A> aList = listAs.stream()
                .filter(a -> a.getIntField() >= 2)
                .collect(Collectors.toList());
        System.out.println(aList);

        List<A> descSort = listAs.stream()
                .sorted((o1, o2) -> o2.getIntField() - o1.getIntField())
                .collect(Collectors.toList());
        System.out.println(descSort);

        A maxA = listAs.stream()
                .max(Comparator.comparing(A::getIntField))
                .orElse(null);
        System.out.println(maxA);

        Map<Mode, List<A>> map = listAs.stream().collect(Collectors.groupingBy(a -> a.getMode()));
        System.out.println(map.entrySet());

        boolean allMatch = listAs.stream().allMatch(a -> a.getMode().equals(Mode.First));
        System.out.println("allMatch " + allMatch);

        boolean anyMatch = listAs.stream().anyMatch(a -> a.getMode().equals(Mode.First));
        System.out.println("anyMatch " + anyMatch);
    }
}

enum Mode {
    None, First, Second
}

class A {
    private final int intField;
    private final Mode mode;

    public A(int intField, Mode mode) {
        this.intField = intField;
        this.mode = mode;
    }

    public int getIntField() {
        return intField;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "A{" + intField + ", " + mode + "}";
    }
}
