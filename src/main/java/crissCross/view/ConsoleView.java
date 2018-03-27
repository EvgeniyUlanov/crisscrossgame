package crissCross.view;

import crissCross.model.Point;

import java.util.List;

public class ConsoleView {

    public static void showField(List<Point> field, int fieldSise) {
        StringBuilder str = new StringBuilder();
        str.append("y\\x " );
        for (int i = 0; i < fieldSise; i++) {
            str.append(i).append(" ");
        }
        str.append("\n");
        for (int i = 0; i < field.size(); i++) {
            if (i % fieldSise == 0 || i == 0) {
                str.append("  ").append(i / fieldSise).append(" ");
            }
            switch (field.get(i).getType()) {
                case O:
                    str.append("0 ");
                    break;
                case X:
                    str.append("X ");
                    break;
                case NONE:
                    str.append("* ");
                    break;
            }
            if ((i + 1) % fieldSise == 0) {
                str.append("\n");
            }
        }
        System.out.println(str.toString());
    }


}
