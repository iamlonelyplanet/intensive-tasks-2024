package com.walking.intensive.chapter1.task5;

import java.util.Arrays;

/**
 * Задача поиска площади, величин углов, длин высот, биссектрис, медиан, радиусов вписанной и описанной вокруг
 * треугольника окружностей является центральной в Геометрии.
 *
 * <p>Реализуйте представленные ниже методы в соответствии с заданными условиями.
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task5 {
    public static void main(String[] args) {
        double a = 3;
        double b = 4;
        double c = 5;

        System.out.println("Площадь по Герону: " + getAreaByHeron(a, b, c));
        System.out.println("Высоты: " + Arrays.toString(getHeights(a, b, c)));
        System.out.println("Медианы: " + Arrays.toString(getMedians(a, b, c)));
        System.out.println("Биссектрисы: " + Arrays.toString(getBisectors(a, b, c)));
        System.out.println("Углы: " + Arrays.toString(getAngles(a, b, c)));
        System.out.println("Радиус вписанной окружности: " + getInscribedCircleRadius(a, b, c));
        System.out.println("Радиус описанной окружности: " + getCircumradius(a, b, c));
        System.out.println("Площадь по-продвинутому: " + getAreaAdvanced(a, b, c));
    }

    /**
     * Частным случаем Tеоремы Брахмагупты является формула Герона.
     *
     * <p>Реализуйте метод поиска площади треугольника формулой Герона.
     *
     * <p>Входные параметры - длина сторон треугольника. Возвращаемое значение - площадь треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static boolean isTriangleImpossible(double a, double b, double c) {
        return a <= 0 || b <= 0 || c <= 0 || a + b < c || a + c < b || c + b < a;
    }

    static double getHalfPerimeter(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    static double getAreaByHeron(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return -1;
        }
        double p = getHalfPerimeter(a, b, c);
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * Реализуйте метод, который будет возвращать высоты треугольника по возрастанию.
     *
     * <p>Входные параметры - длина сторон треугольника. Возвращаемое значение - массив с высотами треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать пустой массив нулевой длины.
     */
    static double[] getHeights(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return new double[0];
        }
        double[] heights = new double[3];
        double twoAreas = 2 * getAreaByHeron(a, b, c); // h(a)=2S/a
        heights[0] = twoAreas / a;
        heights[1] = twoAreas / b;
        heights[2] = twoAreas / c;
        Arrays.sort(heights);
        return heights;
    }

    /**
     * Реализуйте метод, который будет возвращать медианы треугольника по возрастанию.
     *
     * <p>Входные параметры - длина сторон треугольника. Возвращаемое значение - массив с медианами треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать пустой массив нулевой длины.
     */
    static double[] getMedians(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return new double[0];
        }
        double[] medians = new double[3];
        medians[0] = 0.5 * Math.sqrt(2 * Math.pow(a, 2) + 2 * Math.pow(b, 2) - Math.pow(c, 2));
        medians[1] = 0.5 * Math.sqrt(2 * Math.pow(b, 2) + 2 * Math.pow(c, 2) - Math.pow(a, 2));
        medians[2] = 0.5 * Math.sqrt(2 * Math.pow(c, 2) + 2 * Math.pow(a, 2) - Math.pow(b, 2));
        Arrays.sort(medians);
        return medians;
    }

    /**
     * Реализуйте метод, который будет возвращать биссектрисы треугольника по возрастанию.
     *
     * <p>Входные параметры - длина сторон треугольника. Возвращаемое значение - массив с биссектрисами треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать пустой массив нулевой длины.
     */
    static double[] getBisectors(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return new double[0];
        }
        double[] bisectors = new double[3];
        double perimeter = 2 * getHalfPerimeter(a, b, c);
        bisectors[0] = (Math.sqrt(a * b * perimeter * (a + b - c))) / (a + b);
        bisectors[1] = (Math.sqrt(b * c * perimeter * (b + c - a))) / (b + c);
        bisectors[2] = (Math.sqrt(c * a * perimeter * (c + a - b))) / (c + a);
        Arrays.sort(bisectors);
        return bisectors;
    }

    /**
     * Реализуйте метод, который будет возвращать углы треугольника (в градусах) по возрастанию.
     *
     * <p>Входные параметры - длина сторон треугольника. Возвращаемое значение - массив с углами треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать пустой массив нулевой длины.
     */
    static double[] getAngles(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return new double[0];
        }
        double[] angles = new double[3];
        angles[0] = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(c, 2) - Math.pow(b, 2)) / (2 * a * c)));
        angles[1] = Math.toDegrees(Math.acos((Math.pow(b, 2) + Math.pow(a, 2) - Math.pow(c, 2)) / (2 * b * a)));
        angles[2] = Math.toDegrees(Math.acos((Math.pow(c, 2) + Math.pow(b, 2) - Math.pow(a, 2)) / (2 * c * b)));
        Arrays.sort(angles);
        return angles;
    }

    /**
     * Реализуйте метод, который будет возвращать длину радиуса вписанной в треугольник окружности.
     *
     * <p>Входные параметры - длина сторон треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static double getInscribedCircleRadius(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return -1;
        }
        return getAreaByHeron(a, b, c) / getHalfPerimeter(a, b, c);
    }

    /**
     * Реализуйте метод, который будет возвращать длину радиуса описанной вокруг треугольника окружности.
     *
     * <p>Входные параметры - длина сторон треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static double getCircumradius(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return -1;
        }
        return a * b * c / (4 * getAreaByHeron(a, b, c));
    }

    /**
     * Дополнительная задача по желанию.
     *
     * <p>Реализуйте метод, который будет возвращать площадь треугольника.
     *
     * <p>Расчет площади должен быть произведем через поиск косинуса угла через теорему косинусов,
     * далее нахождение синуса через основное тригонометрическое тождество
     * и подстановку синуса в нужную формулу для площади треугольника.
     * (Всего основных способов поиска площади треугольника 6)
     *
     * <p>Входные параметры - длина сторон треугольника.
     *
     * <p>Если входные данные некорректны - метод должен возвращать -1.
     */
    static double getAreaAdvanced(double a, double b, double c) {
        if (isTriangleImpossible(a, b, c)) {
            return -1;
        }
        double cosinusA = (Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c);
        double sinusA = Math.sqrt(1 - Math.pow(cosinusA, 2)); // в радианах!
        // S = 1/2ab sin(c) - площадь через 2 стороны и угол
        return b * c * sinusA / 2;
    }
}
