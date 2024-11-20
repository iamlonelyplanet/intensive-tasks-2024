package com.walking.intensive.chapter1.task2;

/**
 * Реализуйте метод getFlatLocation(), который будет принимать параметрами следующие данные:
 * <ul>
 * <li> Количество этажей в доме;
 * <li> Количество подъездов;
 * <li> Номер нужной квартиры.
 * </ul>
 *
 * <p>Необходимо определить подъезд, этаж и расположение нужной квартиры относительно лифта,
 * руководствуясь следующими правилами:
 * <ul>
 * <li> На этаже 4 квартиры;
 * <li> Нумерация квартир возрастает по часовой стрелке.
 * </ul>
 *
 * <p>Примеры строки, возвращаемой из метода:
 * <ul>
 * <li> 1 кв – 1 подъезд, 1 этаж, слева от лифта, влево
 * <li> 2 кв – 1 подъезд, 1 этаж, слева от лифта, вправо
 * <li> 3 кв – 1 подъезд, 1 этаж, справа от лифта, влево
 * <li> 4 кв – 1 подъезд, 1 этаж, справа от лифта, вправо
 * </ul>
 *
 * <p>Если для дома с указанной этажностью и количеством подъездов квартиры с заданным номером не существует,
 * метод должен вернуть строку "Такой квартиры не существует".
 *
 * <p>Если хотя бы один из указанных параметров некорректный - например, отрицательное число или 0,
 * метод должен вернуть строку "Некорректные входные данные".
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task2 {
    public static void main(String[] args) {
        
        System.out.print(getFlatLocation(4, 3, 17));
    }

    static String getFlatLocation(int floorAmount, int entranceAmount, int flatNumber) {
        if (flatNumber <= 0 || flatNumber > floorAmount * entranceAmount * 4) {
            return "Такой квартиры не существует";
        }
        if (floorAmount <= 0 || entranceAmount <= 0) {
            return "Некорректные входные данные";
        }
        int block = flatNumber / 4; // block - площадка из 4 квартир на одном этаже
        int entrance = (flatNumber % (floorAmount * 4) != 0) ? (block / floorAmount + 1) : block / floorAmount;
        int floor = (flatNumber % 4 == 0 ? (block - (entrance - 1) * floorAmount) : (block - (entrance - 1) * floorAmount + 1));

        return flatNumber + " кв - " + entrance + " подъезд, " + floor + " этаж" + getFlatPosition(flatNumber);
    }

    static String getFlatPosition(int flatNumber) {
        return switch (flatNumber % 4) {
            case 1 -> ", слева от лифта, влево";
            case 2 -> ", слева от лифта, вправо";
            case 3 -> ", справа от лифта, влево";
            case 0 -> ", справа от лифта, вправо";
            default -> "";
        };
    }
}
