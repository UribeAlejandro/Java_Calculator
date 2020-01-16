package calculator;

import javax.swing.*;
import java.awt.*;

public class Preferences extends JFrame {

    private static final long serialVersionUID = 1L;

    private JFrame window;
    private JPanel panel;
    private JToggleButton darkModeOnOff;

    public void preferences() {
        window = new JFrame("Preferences");
        window.setSize(500, 500);
        window.setLayout(new GridBagLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);

        panel = new JPanel();
        window.add(panel);

        // Creates checkbox
        darkModeOnOff = new JToggleButton("DarkMode");
        panel.add(darkModeOnOff);
    }
}