package crissCross.model;

import crissCross.controller.GameController;
import crissCross.view.ConsoleView;

import java.util.List;
import java.util.Random;

public class Game {
    private Field field;
    private Player playerFirst;
    private Player playerSecond;
    private boolean endGame;
    private Player winner;
    private GameController controller;
    private int fieldSize;

    public Game(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new Field(fieldSize);
        playerFirst = new PlayerHuman(Type.X);
        playerSecond = new PlayerComp(Type.O);
    }

    public enum Type {
        X, O, NONE
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void start() throws InterruptedException {
        Random rn = new Random();
        int first = rn.nextInt(2);
        if (first == 0) {
            controller.showMeassagePlayerTurn(playerFirst.getType());
        } else {
            controller.showMeassagePlayerTurn(playerSecond.getType());
        }
        ConsoleView.showField(field.getFieldPoint(), fieldSize);
        controller.upDateView();
        while (field.getClearPoints().size() != 0 && !endGame) {
            if (first == 0) {
                boolean result;
                do {
                    result = makePlayerMove(playerFirst);
                } while (!result);
                first = 1;
                ConsoleView.showField(field.getFieldPoint(), fieldSize);
                controller.upDateView();
            } else {
                makePlayerMove(playerSecond);
                first = 0;
                ConsoleView.showField(field.getFieldPoint(), fieldSize);
                controller.upDateView();
            }
        }
        if (winner != null) {
            controller.showMessageWinner(winner.getType());
        } else {
            controller.showMessageWinner(Type.NONE);
        }
    }

    private boolean makePlayerMove(Player player) throws InterruptedException {
        boolean result = false;
        Point point = player.choosePoint(field.getClearPoints());
        if (point != null) {
            point.setType(player.getType());
            result = true;
            if (field.checkWinner(player.getType())) {
                winner = player;
                endGame = true;
            }
        }
        return result;
    }

    public PlayerHuman getPlayer() {
        return (PlayerHuman) playerFirst;
    }

    public List<Point> getField() {
        return field.getFieldPoint();
    }
}
