package crissCross.controller;

import crissCross.model.Game;
import crissCross.model.PlayerHuman;
import crissCross.model.Point;
import crissCross.model.Game.Type;
import client.GameClientWindow;

import java.util.List;

public class GameController {

    private Game game;
    private GameClientWindow gameClientWindow;
    private PlayerHuman player;
    private GameController controller;
    private Thread thread;
    private int fieldSize;
    private final Object key = new Object();

    public void makeShoot(int x, int y) {
        player.setX(x);
        player.setY(y);
        synchronized (key) {
            key.notifyAll();
        }
    }

    public void upDateView() {
        List<Point> field = game.getField();
        for (Point point : field) {
            gameClientWindow.updateField(point.getX(), point.getY(), point.getType());
        }
    }

    public void setView(GameClientWindow gameClientWindow) {
        this.gameClientWindow = gameClientWindow;
    }

    public void startNewGame(int size) {
        if (thread != null) {
            thread.interrupt();
        }
        controller = this;
        fieldSize = size;
        thread = new Thread(new Runnable() {
            public void run() {
                game = new Game(fieldSize);
                game.setController(controller);
                player = game.getPlayer();
                player.setKey(key);
                try {
                    game.start();
                } catch (InterruptedException e) {
                    System.out.println("Game was interrupted");;
                }
            }
        });
        thread.start();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void showMessageWinner(Type type) {
        gameClientWindow.showWinner(type);
    }

    public void showMeassagePlayerTurn(Type type) {
        gameClientWindow.showMessageTurn(type);
    }
}
