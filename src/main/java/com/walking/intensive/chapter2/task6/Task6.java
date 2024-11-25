package com.walking.intensive.chapter2.task6;

import java.util.Arrays;

/**
 * Реализуйте представленные ниже методы для расчета
 * НОК (наименьшее общее кратное) и НОД (наибольший общий делитель).
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task6 {
    public static void main(String[] args) {
        int m = 515;
        int n = 516;
        System.out.println(Arrays.toString(getAllDivisors(m)));
        System.out.println(Arrays.toString(getAllDivisors(n)));
        System.out.println(getGcd(m, n));
        System.out.println(getLcm(m, n));
        System.out.println(getGcdByEuclideanAlgorithm(m, n));
        // Вот эти все sout - они для самопроверки.
    }

    /**
     * Реализуйте метод, который будет возвращать НОК для чисел, переданных параметрами.
     *
     * <p>Входные параметры - положительные целые числа.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static int getLcm(int m, int n) { // Это НОК
        if (m <= 0 || n <= 0) {
            return -1;
        }

        return m * n / getGcd(m, n); // Если знать НОД (а мы знаем!), то НОК = mn/НОД
    }

    /**
     * Реализуйте метод, который будет возвращать НОД для чисел, переданных параметрами.
     *
     * <p>Входные параметры - положительные целые числа.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static int getGcd(int m, int n) { // Это НОД
        if (m <= 0 || n <= 0) {
            return -1;
        }

        int minimalNumber = Math.min(m, n);
        int gcd = 1;
        for (int i = gcd; i <= minimalNumber; i++) {
            if (isDividedWithoutRemainder(m, i) && isDividedWithoutRemainder(n, i)) {
                gcd = i;
            }
        }
        return gcd;
    }

    /**
     * Реализуйте метод, который будет возвращать НОД для чисел, переданных параметрами.
     * Расчет должен производиться с помощью алгоритма Евклида
     *
     * <p>Входные параметры - положительные целые числа.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static int getGcdByEuclideanAlgorithm(int m, int n) {
        if (m <= 0 || n <= 0) {
            return -1;
        }
        int largestNumber = Math.max(m, n);
        int lowestNumber = Math.min(m, n);
        while (lowestNumber != largestNumber) {
            int temporaryGcd = largestNumber - lowestNumber;
            return getGcdByEuclideanAlgorithm(temporaryGcd, lowestNumber);
        }
        /*
        Ниже - второе решение, с рекурсией и for, оставляю для себя
        for (int i = lowestNumber; i > 1; i--) {
            if (lowestNumber == largestNumber) {
                return lowestNumber;
            }
            int temp = largestNumber - lowestNumber;
            return getGcdByEuclideanAlgorithm(temp, lowestNumber);
        }

        Ниже - первое решение, без рекурсии, оставлю для себя.
        while (largestNumber != lowestNumber) {
            int temporaryGcd = largestNumber - lowestNumber;
            largestNumber = Math.max(temporaryGcd, lowestNumber);
            lowestNumber = Math.min(temporaryGcd, lowestNumber);
        } */
        return lowestNumber;
    }

    static boolean isDividedWithoutRemainder(int n, int i) { // Проверка, делятся ли числа без остатка
        return (n % i == 0);
    }

    static int[] getAllDivisors(int number) { // Знаю про запрет массивов. Это чисто для себя и наглядности вычислений.
        if (number <= 1) {
            return new int[0];
        }

        int[] divisors = new int[number];
        int i;
        int counter = 0;
        for (i = 1; i <= number; i++) {
            if (isDividedWithoutRemainder(number, i)) {
                divisors[counter] = i;
                counter++;
            }
        }
        divisors = Arrays.stream(divisors).filter(num -> num != 0).toArray();
        return divisors;
    }

    /*  Оставлю это для себя в виде комментария, на будущее
        static boolean isWrong(int m, int n) { // Проверяем, корректны ли входные данные
        return (m <= 0 || n <= 0);
    } */
}
