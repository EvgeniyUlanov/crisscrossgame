package client;

import javax.swing.*;
import java.awt.*;

class SettingsWindow extends JDialog {

    private GameClientWindow ownerWindow;

    SettingsWindow(JFrame owner) {
        super(owner, true);
        ownerWindow = (GameClientWindow) owner;
        setSize(300, 150);
        setTitle("Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
        setResizable(false);
        setLayout(new GridBagLayout());
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        //init components
        JLabel labelHost = new JLabel("Host");
        labelHost.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel labelPort = new JLabel("Port");
        labelPort.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField textFieldHost = new JTextField("localhost");
        JTextField textFieldPort = new JTextField("8082");
        JButton buttonSet = new JButton("Set settings");
        JButton buttonSetDefault = new JButton("Set default");

        //add components
        add(labelHost, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        add(labelPort, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        add(textFieldHost, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        add(textFieldPort, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        add(buttonSet, new GridBagConstraints(0, 3, 1, 1, 1, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        add(buttonSetDefault, new GridBagConstraints(1, 3, 1, 1, 1, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        //init button action listeners
        buttonSet.addActionListener(e -> {
                    ownerWindow.setSettings(textFieldHost.getText(), textFieldPort.getText());
                    dispose();
                }
        );
        buttonSetDefault.addActionListener(e -> {
                    textFieldHost.setText("localhost");
                    textFieldPort.setText("8082");
                    ownerWindow.setSettings(textFieldHost.getText(), textFieldPort.getText());
                    dispose();
                }
        );
    }
}
