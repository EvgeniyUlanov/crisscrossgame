package crissCross.model;

import crissCross.model.Game.Type;

import java.util.List;
import java.util.Random;

class PlayerComp extends Player {

    PlayerComp(Type type) {
        super(type);
    }

    public Point choosePoint(List<Point> nonePointList) {
        Random rn = new Random();
        return nonePointList.get(rn.nextInt(nonePointList.size()));
    }
}
