package client;

import crissCross.controller.GameController;
import crissCross.model.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameClientWindow extends JFrame {

    private final int STANDARD_FIELD_SIZE = 3;
    private List<MyButton> buttons = new ArrayList<MyButton>();
    private GameController controller;
    private JMenuBar jMenuBar = new JMenuBar();
    private GameClientWindow window;
    private ImageIcon iconX;
    private ImageIcon iconO;
    private ImageIcon iconNone;
    private Icon iconWin;
    private Icon iconLose;
    private Icon iconNobodyWin;
    private String playerName = "player";
    private String host = "localhost";
    private String port = "8082";
    private SettingsWindow settingsWindow;

    public void init() {
        setSize(440, 550);
        setResizable(false);
        setTitle("Cris Cross Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        createMenuBar();
        initFieldPanel();
        initControlPanel();
        window = this;
        setVisible(true);
        loadIcons();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void createMenuBar() {
        JMenu game = new JMenu("Game");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        game.add(exit);
        jMenuBar.add(game);
        setJMenuBar(jMenuBar);
    }

    private void initFieldPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(STANDARD_FIELD_SIZE, STANDARD_FIELD_SIZE, 3, 3));

        for (int i = 0; i < STANDARD_FIELD_SIZE; i++) {
            for (int j = 0; j < STANDARD_FIELD_SIZE; j++) {
                final MyButton button = new MyButton(i, j);
                button.addActionListener(e -> controller.makeShoot(button.getXpoint(), button.getYpoint()));
                button.setIcon(iconNone);
                panel.add(button);
                buttons.add(button);
                button.setBackground(Color.white);
            }
        }
        panel.setBackground(Color.black);
        add(panel,BorderLayout.CENTER);
    }

    private void initControlPanel() {
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridBagLayout());
        JLabel labelPlayerName = new JLabel("Player name: ");
        labelPlayerName.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel labelPlayOffline = new JLabel("Play offline");
        labelPlayOffline.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel labelPlayOnlineComp = new JLabel("Play online with computer");
        labelPlayOnlineComp.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel labelPlayOnlineHuman = new JLabel("Play online with player");
        labelPlayOnlineHuman.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField textFieldPlayerName = new JTextField("Player");
        JButton buttonStart = new JButton("Start New Game");
        JButton buttonSetOnlineSettings = new JButton("Set Online settings");
        JRadioButton rButtonPlayOffline = new JRadioButton();
        rButtonPlayOffline.setFocusPainted(true);
        JRadioButton rButtonPlayOnlineComp = new JRadioButton();
        rButtonPlayOffline.setSelected(true);
        JRadioButton rButtonPLayOnlineHuman = new JRadioButton();
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(rButtonPlayOffline);
        radioButtonGroup.add(rButtonPlayOnlineComp);
        radioButtonGroup.add(rButtonPLayOnlineHuman);

        panelControl.add(labelPlayerName, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(labelPlayOffline, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(labelPlayOnlineComp, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(labelPlayOnlineHuman, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(textFieldPlayerName, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(rButtonPlayOffline, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(rButtonPlayOnlineComp, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(rButtonPLayOnlineHuman, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(buttonStart, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        panelControl.add(buttonSetOnlineSettings, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        buttonSetOnlineSettings.addActionListener(e -> {
            if (settingsWindow != null) {
                settingsWindow.setVisible(true);
            } else {
                settingsWindow = new SettingsWindow(window);
            }
        });
        buttonStart.addActionListener(e -> controller.startNewGame(STANDARD_FIELD_SIZE));

        add(panelControl, BorderLayout.SOUTH);
    }

    public void updateField(int x, int y, Game.Type type) {
        ImageIcon icon;
        if (type == Game.Type.X) {
            icon = iconX;
        } else if (type == Game.Type.O){
            icon = iconO;
        } else {
            icon = iconNone;
        }
        for (MyButton button : buttons) {
            if (button.getXpoint() == x && button.getYpoint() == y) {
                button.setIcon(icon);
                break;
            }
        }
    }

    public void showWinner(Game.Type winner) {
        if (winner == Game.Type.X) {
            JOptionPane.showMessageDialog(window, "player win",
                    "winner", JOptionPane.INFORMATION_MESSAGE, iconWin);
        } else if (winner == Game.Type.O) {
            JOptionPane.showMessageDialog(window, "computer win",
                    "loser", JOptionPane.INFORMATION_MESSAGE, iconLose);
        } else {
            JOptionPane.showMessageDialog(window, "nobody win",
                    "nobody", JOptionPane.INFORMATION_MESSAGE, iconNobodyWin);
        }
    }

    public void showMessageTurn(Game.Type type) {
        if (type == Game.Type.X) {
            JOptionPane.showMessageDialog(window, "Player's move");
        } else {
            JOptionPane.showMessageDialog(window, "Computer's move");
        }
    }

    private void loadIcons() {
        iconX = new ImageIcon("src\\main\\resources\\x.png");
        iconO = new ImageIcon("src\\main\\resources\\0.png");
        iconNone = new ImageIcon("src\\main\\resources\\none.png");
        iconWin = new ImageIcon("src\\main\\resources\\winner.png");
        iconLose = new ImageIcon("src\\main\\resources\\lose.png");
        iconNobodyWin = new ImageIcon("src\\main\\resources\\nobody.png");
    }


    public void setSettings(String host, String port) {
        this.host = host;
        this.port = port;
    }
}

class MyButton extends JButton {
    private int x;
    private int y;

    MyButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    int getXpoint() {
        return x;
    }

    int getYpoint() {
        return y;
    }
}
