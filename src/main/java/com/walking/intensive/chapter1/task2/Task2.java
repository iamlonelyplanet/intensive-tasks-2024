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
        int flatAtFloor = 4; // flatAtFloor - количество квартир на этаже
        if (flatNumber > floorAmount * entranceAmount * flatAtFloor) {
            return "Такой квартиры не существует";
        }
        if (floorAmount <= 0 || entranceAmount <= 0 || flatNumber <= 0) {
            return "Некорректные входные данные";
        }
        int block = flatNumber / flatAtFloor; // block - площадка из 4 квартир на одном этаже
        int entranceNumber = block / floorAmount; // вычисляем номер подъезда
        int entrance = flatNumber % (floorAmount * flatAtFloor) != 0 // entrance - номер подъезда, начиная с первого
                ? entranceNumber + 1
                : entranceNumber;
        int floorNumber = block - (entrance - 1) * floorAmount; // вычисляем номер этажа
        int floor = flatNumber % flatAtFloor != 0 // номер этажа, начиная с первого
                ? floorNumber + 1
                : floorNumber;

        return flatNumber + " кв - " + entrance + " подъезд, " + floor + " этаж" + getFlatPosition(flatNumber, flatAtFloor);
    }

    static String getFlatPosition(int flatNumber, int flatAtFloor) {
        return switch (flatNumber % flatAtFloor) {
            case 1 -> ", слева от лифта, влево";
            case 2 -> ", слева от лифта, вправо";
            case 3 -> ", справа от лифта, влево";
            case 0 -> ", справа от лифта, вправо";
            default -> "";
        };
    }
}
