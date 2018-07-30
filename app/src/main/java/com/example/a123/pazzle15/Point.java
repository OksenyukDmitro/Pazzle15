package com.example.a123.pazzle15;

/**
 * Created by 123 on 13.07.2018.
 */

public class Point {
    public int x;
    public int y;

    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return (this.x == p.x) && (this.y == p.y);
        }
        return false;
    }
}
