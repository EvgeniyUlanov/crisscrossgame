package crissCross.model;

import crissCross.model.Game.Type;

public class Point {
    private int x;
    private int y;
    private Type type;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = Type.NONE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    void setType(Type type) {
        this.type = type;
    }
}
