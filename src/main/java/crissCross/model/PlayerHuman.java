package crissCross.model;

import crissCross.model.Game.Type;

import java.util.List;

public class PlayerHuman extends Player {

    private int x = -1;
    private int y = -1;
    private Object key;

    PlayerHuman(Type type) {
        super(type);
    }

    @Override
    public  Point choosePoint(List<Point> nonePointList) throws InterruptedException {
        Point result = null;
        if (key != null) {
            synchronized (key) {
                key.wait();
            }
        }
        for (Point point : nonePointList) {
            if (point.getX() == x && point.getY() == y) {
                result = point;
                break;
            }
        }
        x = -1;
        y = -1;
        return result;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setKey(Object key) {
        this.key = key;
    }
}
