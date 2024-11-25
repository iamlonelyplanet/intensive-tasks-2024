package com.walking.intensive.chapter2.task7;

/**
 * Пятиклассник Ваня придумал забаву. Он ввел понятие «дружественной пары» чисел.
 * Два различных натуральных числа N и M он назвал дружественными, если сумма делителей числа N
 * (включая 1, но исключая само N) равна числу M и наоборот.
 *
 * <p>Например, 220 и 284 – дружественные числа:
 *
 * <ul>
 * <li>Список делителей для 220: 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110. Их сумма равна 284;
 * <li>Список делителей для 284: 1, 2, 4, 71, 142. Их сумма равна 220.
 * </ul>
 *
 * <p>Реализуйте метод getFriendlyPair(), который принимает параметром число N,
 * где N - натуральное число не больше 1 000 000.
 *
 * <p>Метод должен вернуть наибольшее число из пары дружественных чисел,
 * сумма дружественных чисел которой максимальна среди всех пар дружественных
 * чисел, большее из которых меньше N.
 *
 * <p> Если входные данные некорректны - метод должен возвращать -1.
 *
 * <p>P.S. Решение не должно использовать массивы и прочие темы, которые пока не были затронуты в курсе.
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task7 {
    public static void main(String[] args) {
        int n = 1_000_000;
        System.out.println(getFriendlyPair(n));
    }

    static int getFriendlyPair(int number) {
        if (number < 2 || number > 1000000) {
            return -1;
        }
        while (number > 0) {
            int largestNumber = getDivisorsSum(number);
            if (getDivisorsSum(largestNumber) == number && largestNumber != number) {
                return number;
            }
            number--;
        }

        return number;
    }

    static int getDivisorsSum(int n) {
        int divisorsSum = 1; // Сумму делителей начнём с 1 вместо 0: делители включают в себя 1.
        int i;
        for (i = 2; i <= n / 2; i++) { // Делим n на два: для n невозможны делители выше n/2.
            if (divisorsSum > n) { // нет смысла проверять, когда сумма делителей превысила второе число.
                return 0;
            }
            if (n % i == 0) {
                divisorsSum = divisorsSum + i;
            }
        }

        return divisorsSum;
    }
}
