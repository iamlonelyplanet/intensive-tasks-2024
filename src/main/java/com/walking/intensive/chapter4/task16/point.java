package com.walking.intensive.chapter4.task16;

import java.awt.*;

public class point {
    int x;
    int y;

    public void Point(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public static void main(String[] args) {
        int x1 = 1;
        int y1 = 2;
        int x2 = 5;
        int y2 = 2;

        Point begin = new Point(1, 2);
        Point end = new Point(3, 4);



        printPosition(begin);
        printPosition(end);
    }

    static void printPosition(Point point) {
        System.out.println("Координаты: " + point.x + ", " + point.y);
    }

}
