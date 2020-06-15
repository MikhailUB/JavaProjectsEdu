package com.company;

import java.io.*;
import java.util.*;

/*
Реализовать:
Исходные данные: текстовый файл со средней длиной строки равной 10 символам (файл или прошить текст в коде).
В реализациях используйте наиболее подходящие имплементации коллекций!
Задание 1: Подсчитайте количество различных слов в файле.
Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).
Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
Задание 4: Выведите на экран все строки файла в обратном порядке.
Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
*/
public class Main {

    public static void main(String[] args) throws IOException {
        //task1();
        //task2();
        task3();
        //task4();
        //task5();
        //task6();
    }

    private static void task1() throws IOException {
        _<Integer> allCount = new _<Integer>(0);
        Set<String> wordsSet = getUniqueWords(allCount);
        System.out.printf("Задание 1. количество различных слов = %d (%d)\n", wordsSet.size(), allCount.ref);
    }

    private static void task2() throws IOException {
        _<Integer> allCount = new _<Integer>(0);
        Set<String> wordsSet = getUniqueWords(allCount);

        List<String> words = new ArrayList<>(wordsSet);
        //words.sort(new StringComparator());
        words.sort((str1, str2) -> {
            int res = str1.length() - str2.length();
            if (res != 0)
                return res;

            return str1.compareTo(str2);
        });
        System.out.println("Задание 2. слова отсортированы по длине");
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }
    }

    static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            int res = str1.length() - str2.length();
            if (res != 0)
                return res;

            return str1.compareTo(str2);
        }
    }

    private static Set<String> getUniqueWords(_<Integer> allCount) throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        Set<String> wordsSet = new HashSet<>();
        allCount.ref = 0;
        while (scanner.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
            while (tokenizer.hasMoreTokens()) {
                wordsSet.add(tokenizer.nextToken());
                allCount.ref++;
            }
        }
        scanner.close();
        file.close();
        return wordsSet;
    }

    private static void task3() throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        Map<String, Integer> wordsCounts = new HashMap<>();
        while (scanner.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
            while (tokenizer.hasMoreTokens()) {
                String key = tokenizer.nextToken();
                Integer count = wordsCounts.getOrDefault(key, 0);
                wordsCounts.put(key, count + 1);
            }
        }
        scanner.close();
        file.close();
        System.out.println("Задание 3. количество каждого слова");
        wordsCounts.forEach((k, v) -> System.out.printf("%s = %d\n", k, v));
    }

    private static void task4() throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        file.close();

        System.out.println("Задание 4. строки файла в обратном порядке");
        ListIterator<String> iterator = lines.listIterator(lines.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    private  static void task5() throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        file.close();

        System.out.println("Задание 5. свой Iterator для обхода списка в обратном порядке");
        Iterator<String> iterator = new DescendingIterator<>(lines).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void task6() throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        file.close();

        scanner = new Scanner(System.in);
        System.out.println("Задание 6. вывод произвольных строк");
        System.out.printf("вводите номер строки не больше %d или пусто чтобы закончить\n", lines.size());
        String str;
        while ((str = scanner.nextLine()) != null && !str.isEmpty()) {
            int num = Integer.parseInt(str);
            if (num <= lines.size())
                System.out.println(lines.get(num - 1));
            else
                System.out.printf("вводите номер строки не больше %d или пусто чтобы закончить\n", lines.size());
        }
    }

}
