package crissCross.model;

import crissCross.model.Game.Type;

import java.util.List;

public abstract class Player {

    private Type type;

    public Player(Type type) {
        this.type = type;
    }

    public abstract Point choosePoint(List<Point> nonePointList) throws InterruptedException;

    Type getType() {
        return type;
    }
}
