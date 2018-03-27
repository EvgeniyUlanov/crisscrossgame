package crissCross.model;

import crissCross.model.Game.Type;

import java.util.ArrayList;
import java.util.List;

class Field {
    private int fieldSize;
    private List<Point> field = new ArrayList<Point>();
    private List<Point> nonePointList = new ArrayList<Point>();

    Field(int fieldSize) {
        this.fieldSize = fieldSize;
        init();
    }

    private void init() {
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                Point point = new Point(x, y);
                field.add(point);
                nonePointList.add(point);
            }

        }
    }

    List<Point> getClearPoints() {
        clearPointList();
        return nonePointList;
    }

    List<Point> getFieldPoint() {
        return field;
    }

    private void clearPointList() {
        for (Point point : field) {
            if (point.getType() != Type.NONE) {
                nonePointList.remove(point);
            }
        }
    }

    boolean checkWinner(Type type) {
        boolean result = false;
        for (int j = 0; j < fieldSize; j++) {
            for (int i = 0; i < fieldSize; i++) {
                if (field.get(j + i * fieldSize).getType() == type) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
            for (int i = 0; i < fieldSize; i++) {
                if (field.get(j * fieldSize + i).getType() == type) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < fieldSize; i++) {
                if (field.get(i + fieldSize * i).getType() == type) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            for (int i = 0; i < fieldSize; i++) {
                if (field.get((i + 1) * (fieldSize - 1)).getType() == type) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}