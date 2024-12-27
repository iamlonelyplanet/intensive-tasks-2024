package com.walking.intensive.chapter4.task17;

import java.util.Arrays;

/**
 * Смауг, живущий в пещере с золотом, был заперт внутри горы.
 * Чтобы занять свое время, он развлекал себя следующей игрой.
 * Он складывал произвольное количество одинаковых монеток по мешочкам,
 * расставлял их в ряд произвольным образом и придумывал алгоритмы,
 * которыми он будет пользоваться для того, чтобы расставить мешочки в порядке возрастания ценности.
 * Времени было много и у него получилось придумать десятки алгоритмов
 * с целью выбрать лучший, который справится с сортировкой за минимальное количество действий.
 *
 * <p>Сортировка — алгоритм расположения элементов массива по неубыванию (возрастанию, если элементы не повторяются).
 *
 * <p>Создайте два метода сортировки: пузырьком и quicksort. Описание алгоритмов вы найдете ниже.
 *
 * <p>
 * При использовании встроенных методов сортировок, коллекций, Stream API и иного материала,
 * выходящего за рамки пройденного курса, задача не принимается к проверке.
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task17 {
    public static void main(String[] args) {
        int[] array = {5, 1, 0, 3, -18, 44, 2, 0, 5, -5, 6};
        int[] array2 = {5, 1, 0, 3, -18, 44, 2, 0, 5, -5, 6};
        System.out.println("Результат пузырьковой сортировки: " + Arrays.toString(sortByBubble(array)));
        System.out.println("Результат QuickSort: " + Arrays.toString(sortByQuicksort(array2)));
        long benchmarkOn1000 = getBenchmarkOn1000();
        long benchmarkOn10000 = getBenchmarkOn10000();
        System.out.println("Это быстрее пузырькового на " + benchmarkOn1000 + " мс.");
        System.out.println("Это быстрее пузырькового на " + benchmarkOn10000 + " мс.");
        if (benchmarkOn1000 <= 0 || benchmarkOn10000 <= 0) {
            System.out.println("Результат отрицательный, значит, я накосячил, т.к. QuickSort по определению должен " +
                    "быть быстрее пузырькового");

        }
    }

    /**
     * Сортировка пузырьком:
     *
     * <ol>
     * <li> Метод заключается в попарном сравнении соседних элементов в массиве слева направо.
     *      Сначала сравнивается 0 и 1 индексы в массиве.
     *      Если значения элемента с 0-м индексом больше элемента с 1-м индексом -
     *      элементы меняются местами.
     * <li> Потом сравниваются 1 и 2 индексы, и так последовательно попарно сравниваются все элементы массива.
     *      При этом максимальный элемент массива окажется самым правым в массиве.
     * <li> Далее массивом будем считать неотсортированную часть массива,
     *      то есть без последнего самого правого элемента.
     * <li> Повторяем шаги 1 и 2 до полной сортировки массива.
     * </ol>
     */
    static int[] sortByBubble(int[] array) {
        if (array == null) {
            return new int[0];
        }

        for (int i = 0; i < array.length - 1; i++) {
            findMax(array);
        }

        return array;
    }

    /**
     * Быстрая сортировка, она же QuickSort:
     *
     * <p>Это рекурсивный метод, основанный на разделении 1 массива на 2 подмассива
     * по принципу поиска опорного элемента. Далее каждый из двух массивов снова
     * рекурсивно вызывает тот же метод сортировки.
     *
     * <p>Разбиение 1 массива на 2 подмассива происходит поиском «опорного элемента».
     *
     * <p>Опорный элемент делит массив таким образом, что элементы, меньшие опорного,
     * помещаются перед ним(левее), а большие или равные — после(правее).
     * При этом сам опорный элемент не обязан быть элементом массива.
     *
     * <p>Вопрос выбора лучшего опорного элемента пока остается открытым в математике.
     * Цель опорного элемента — попытаться разделить массив пополам,
     * тогда сортировка пройдет максимально быстро.
     * В задаче опорный элемент = (max + min) / 2
     * (считается каждый раз для каждого нового подмассива).
     * Где max и min — максимальный и минимальный элементы массива (подмассива).
     *
     * <ol>
     * <li> В одном цикле два итератора: i слева направо от left до right,
     *      j – справа налево от right до left, где left и right индексы,
     *      вставляемые в метод в качестве аргументов. Ищем значение опорного элемента.
     * <li> Пока i <= j: двигаем i, пока не встретим элемент, который >= опорного элемента.
     *      Двигаем j, пока не встретим элемент, который <= опорного элемента.
     *      Если i <= j, то делаем обмен элементов по этим индексам.
     *      Нужно добиться, чтобы каждый элемент слева от опорного элемента
     *      был <= опорного элемента, а каждый элемент справа от
     *      опорного элемента был >= опорного элемента.
     *      Таким способом мы найдем индекс опорного элемента в массиве или
     *      2 соседних индекса, если опорного элемента в массиве нет.
     * <li> Мы узнали индекс опорного элемента и добились того, что опорный элемент
     *      поделил массив на 2 массива. Осталось каждый подмассив поставить
     *      в качестве аргумента вызывая рекурсию.
     * <li> Выход из рекурсии: массив длины 2 – если нужно,
     *      меняем эти два элемента местами.
     *      Если длина входного массива меньше двух, выходим.
     * </ol>
     */
    static int[] sortByQuicksort(int[] array, int... borders) {
        if (array == null) {
            return new int[0];
        }

        int left = 0;
        int right = array.length - 1;

        if (borders.length != 0) {
            left = borders[0];
            right = borders[1];
        }

        if (left >= right) {
            return array;
        }

        int basicValue = (findMin(array, left, right) + findMax(array, left, right)) / 2;
        int i = left;
        int j = right;

        while (i <= j) {
            while (array[i] < basicValue) {
                i++;
            }

            while (array[j] > basicValue) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        sortByQuicksort(array, left, j);
        sortByQuicksort(array, i, right);

        return array;
    }

    static int findMin(int[] array, int left, int right) {
        for (int i = left; i < right; i++) {
            int minOfTwo = Math.min(array[i], array[i + 1]);
            int maxOfTwo = Math.max(array[i], array[i + 1]);
            array[i] = maxOfTwo;
            array[i + 1] = minOfTwo;
        }

        return array[right];
    }

    static int findMax(int[] array, int... borders) {
        int left = 0;
        int right = array.length - 1;

        if (borders.length != 0) {
            left = borders[0];
            right = borders[1];
        }

        for (int i = left; i < right; i++) {
            int minOfTwo = Math.min(array[i], array[i + 1]);
            int maxOfTwo = Math.max(array[i], array[i + 1]);
            array[i] = minOfTwo;
            array[i + 1] = maxOfTwo;
        }

        return array[right];
    }

    /**
     * Создайте массив случайных целых чисел из 1 000 элементов и сравните время,
     * которое потребуются для каждой из сортировок.
     * Ожидаемое возвращаемое значение - разница в выполнении сортировки в миллисекундах.
     *
     * <p>Для получения текущего UNIX-времени (в миллисекундах) можно использовать `System.currentTimeMillis()`.
     * Время выполнения - разность времени после работы алгоритма и времени до работы алгоритма
     */
    static long getBenchmarkOn1000() {
        int[] array = getRandomArray(1000);

        long startTime = System.currentTimeMillis();
        sortByBubble(array);
        long finishTime = System.currentTimeMillis();
        long bubble = finishTime - startTime;
        System.out.println("Время пузырьком на 1000 элементов: " + bubble + " мс.");

        startTime = System.currentTimeMillis();
        sortByQuicksort(array);
        finishTime = System.currentTimeMillis();
        long quickSort = finishTime - startTime;
        System.out.println("Время быстрой сортировкой на 1000 элементов: " + quickSort + " мс.");

        return bubble - quickSort;
    }

    /**
     * Повторите предыдущие вычисления из метода getBenchmarkOn1000() для массива в 10 000 элементов.
     */
    static long getBenchmarkOn10000() {
        int[] array = getRandomArray(10000);

        long startTime = System.currentTimeMillis();
        sortByBubble(array);
        long bubble = System.currentTimeMillis() - startTime;
        System.out.println("Время пузырьком на 10000 элементов: " + bubble + " мс.");

        startTime = System.currentTimeMillis();
        sortByQuicksort(array);
        long quickSort = System.currentTimeMillis() - startTime;
        System.out.println("Время быстрой сортировкой на 10000 элементов: " + quickSort + " мс.");

        return bubble - quickSort;
    }

    static int[] getRandomArray(int length) {
        int[] randomArray = new int[length];
        int x1 = -1_000_000;
        int x2 = 1_000_000;

        for (int i = 0; i < length; i++) {
            double f = Math.random() / Math.nextDown(1.0);
            int x = (int) (x1 * (1.0 - f) + x2 * f);
            randomArray[i] = x;
        }

        return randomArray;
    }
}
