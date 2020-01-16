package calculator;

import javax.swing.*;
import calculator.Calculate;
import java.awt.*;
import java.util.ArrayList;


public class Log extends JFrame {

    private static final long serialVersionUID = 1L;

    private JFrame window;
    private JPanel panel;
    private JTextArea displayOps;
    private static ArrayList<String> log;

    public void logWindow() {
        window = new JFrame("Log");
        window.setMinimumSize(new Dimension(150,150));
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // Creates TextArea
        displayOps = new JTextArea();
        displayOps.setEditable(false);

        log = getlog();

        log.forEach( (op) -> displayOps.setText(displayOps.getText().trim() + "\n" + op));
        panel.add(displayOps);

        window.add(panel, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    private ArrayList<String> getlog() {
        Calculate getLog = new Calculate();
        log = getLog.getLog();
        return log;
    }
}