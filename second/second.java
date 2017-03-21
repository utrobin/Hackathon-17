package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] input = readInput();
        int[] result = calculate(input);
        printResult(result);
    }

    private static String[] readInput() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String[] array = new String[N];
        for (int i = 0; i < N; ++i) {
            array[i] = in.nextLine();
        }
        return array;
    }

    private static int[] calculate(String[] input) {
        int size = input.length;
        int[] result = new int[size];
        for (int i = 0; i < size; ++i) {
            String[] parsed = input[i].split("\\s+");
            int arg1 = Integer.valueOf(parsed[0]);
            int arg2 = Integer.valueOf(parsed[1]);
            result[i] = reverseSummary(arg1, arg2);
        }
        return result;
    }

    private static int reverseSummary(int arg1, int arg2) {
        String str1, str2;
        if (arg1 < arg2) {      // str1 - меньшее или равное по длине слагаемое
            str1 = String.valueOf(arg1);
            str2 = String.valueOf(arg2);
        }
        else {
            str1 = String.valueOf(arg2);
            str2 = String.valueOf(arg1);
        }
        StringBuilder result = new StringBuilder(str2.length() + 1);
        boolean overflow = false;
        for (int i = 0; i < str1.length(); ++i) {
            int addendum1 = Character.getNumericValue(str1.charAt(i));
            int addendum2 = Character.getNumericValue(str2.charAt(i));
            int sum = addendum1 + addendum2;
            if (overflow) {
                sum++;
            }
            result.append(sum % 10);
            overflow = sum >= 10;
        }

        for (int i = str1.length(); i < str2.length(); ++i) {
            int addendum = Character.getNumericValue(str2.charAt(i));
            if (overflow) {   // Если с прошлого цикла осталась единица для переноса
                addendum++;
                overflow = false;
            }
            result.append(addendum);
        }

        if (overflow) { // Если второй цикл не прошел
            result.append('1');
        }

        return Integer.parseInt(result.toString());
    }

    private static void printResult(int[] input) {
        for (int i: input) {
            System.out.println(i);
        }
    }
}
