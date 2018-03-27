package crissCross;

import crissCross.controller.GameController;
import client.GameClientWindow;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameController controller = new GameController();
        GameClientWindow gameClientWindow = new GameClientWindow();
        gameClientWindow.setController(controller);
        controller.setView(gameClientWindow);
        gameClientWindow.init();
    }
}
