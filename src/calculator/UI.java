package calculator;

import calculator.Calculate;
import calculator.Preferences;
import calculator.Log;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JFrame implements ActionListener, KeyListener {

    // Default serailization ID
    private static final long serialVersionUID = 1L;

    // Variables
    private JFrame frame;
    private JPanel panel, buttonsContainer;
    private JTextField io, op;
    private JButton add, subtract, multiply, divide, equal, zero, dot, C, CE, j;
    private JMenuBar topBar;
    private JMenu file, edit;
    private JMenuItem newWindow, exit, preferences, log;
    private Float num1, num2;
    private String operator, result;
    private Integer i;
    private String actionCommand;

    public UI() {

        // Creates the frame
        frame = new JFrame("Calculator");
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.pack();

        // Creates a Panel that Displays Input/Output and Numbers&Operators
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Creates a Menu
        topBar = new JMenuBar();
        frame.setJMenuBar(topBar);

        // Creates Menus
        file = new JMenu("File");
        topBar.add(file);

        edit = new JMenu("Edit");
        topBar.add(edit);

        // Creates Menu Items
        log = new JMenuItem("Log");
        file.add(log);

        newWindow = new JMenuItem("New Window");
        file.add(newWindow);

        exit = new JMenuItem("Exit");
        file.add(exit);

        preferences = new JMenuItem("Preferences");
        edit.add(preferences);

        // Creates TextFields: Operation and Input
        //// TextField which shows Operations
        op = new JTextField();
        op.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        op.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 100;
        c.weighty = 100;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(op, c);
        op.setEditable(false);

        //// TextField which takes Input
        io = new JTextField("0");
        io.setHorizontalAlignment(JTextField.CENTER);
        io.addKeyListener(this);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 100;
        c.weighty = 100;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(io, c);

        // Creates a Container for numbers and operators
        buttonsContainer = new JPanel();
        buttonsContainer.setBackground(Color.WHITE);
        buttonsContainer.setLayout(new GridLayout(6, 3, 0, 0));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 500;
        c.weighty = 500;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(buttonsContainer, c);

        //// Add button numbers to container and call add the listeners
        for (i = 1; i <= 9; i++) {
            String text = String.valueOf(i);
            this.j = new JButton(text);
            buttonsContainer.add(this.j);
            j.addActionListener(this);
        }

        // Creates operators
        C = new JButton("C");
        zero = new JButton("0");
        CE = new JButton("CE");
        add = new JButton("+");
        dot = new JButton(".");
        subtract = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        equal = new JButton("=");

        //// Add operators to Container
        buttonsContainer.add(C);
        buttonsContainer.add(zero);
        buttonsContainer.add(CE);
        buttonsContainer.add(add);
        buttonsContainer.add(dot);
        buttonsContainer.add(subtract);
        buttonsContainer.add(multiply);
        buttonsContainer.add(divide);
        buttonsContainer.add(equal);

        // Add Listeners for operators

        dot.addActionListener(this);
        zero.addActionListener(this);
        CE.addActionListener(this);
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        C.addActionListener(this);
        equal.addActionListener(this);
        preferences.addActionListener(this);
        log.addActionListener(this);
        newWindow.addActionListener(this);
        exit.addActionListener(this);

        // Organize Panels in Frame
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        io.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent ae) {

        // Defines actions for operators
        this.actionCommand = ae.getActionCommand();
        if (ae.getSource() instanceof JButton) {
            if ("+".equals(actionCommand) || "-".equals(actionCommand) || "*".equals(actionCommand)
                    || "/".equals(actionCommand)) {
                if (this.io.getText().isEmpty()) {
                    num1 = 0f;
                } else {
                    num1 = (Float.parseFloat(this.io.getText().trim()));
                    this.io.setText("0");
                }
                operator = this.actionCommand;
            } else if ("C".equals(actionCommand)) {
                this.num1 = null;
                this.num2 = null;
                this.operator = null;
                this.io.setText("0");
                this.op.setText("");
            } else if ("CE".equals(actionCommand)) {
                this.num2 = null;
                this.io.setText("0");
            } else if ("=".equals(actionCommand)) {
                if (operator == null) {
                    if (this.io.getText().trim().isEmpty()) {
                        this.io.setText("0");
                    } else {
                        if ("0.".equals(this.io.getText())) {
                            this.io.setText("0");
                        } else {
                            this.op.setText(this.io.getText());
                        }
                    }
                } else {
                    if (result == null) {
                        num2 = (Float.parseFloat(this.io.getText().trim()));
                    } else if (result.equals(this.io.getText())) {
                        num1 = Float.parseFloat(result);
                    } else {
                        num2 = (Float.parseFloat(this.io.getText().trim()));
                    }
                    result = calculate(operator, num1, num2);
                    this.io.setText("" + result);
                    this.op.setText("" + getOperation(operator, num1, num2));
                }
            }
        }

        // Define actions for Numbers, decimal point
        if (ae.getSource() instanceof JButton) {
            Integer k;
            String l;
            for (k = 0; k <= 9; k++) {
                l = Integer.toString(k);
                if (l.equals(actionCommand)) {
                    if (result == null) {
                        if ("0".equals(this.io.getText())) {
                            this.io.setText(l);
                        } else {
                            this.io.setText(this.io.getText() + l);
                        }
                    } else {
                        if ("0".equals(this.io.getText()) || "NaN".equals(this.io.getText())
                                || "Infini".equals(this.io.getText()) || result.equals(this.io.getText())) {
                            this.io.setText("" + l);
                        } else {
                            this.io.setText(this.io.getText() + l);
                        }
                    }
                }
            }
            if (".".equals(actionCommand)) {
                if (".".equals(
                        this.io.getText().substring(this.io.getText().length() - 1, this.io.getText().length()))) {
                    this.io.setText(this.io.getText());
                } else {
                    if ("NaN".equals(this.io.getText()) || "Infini".equals(this.io.getText())) {
                        this.io.setText("0");
                    } else {
                        if (result == null) {
                            this.io.setText(this.io.getText() + ".");
                        } else if (result.equals(this.io.getText())) {
                            this.io.setText("0.");
                        } else {
                            this.io.setText(this.io.getText() + ".");
                        }
                    }
                }
            }
        }
        // Define actions for MenuItems
        if (ae.getSource() instanceof JMenuItem) {
            if ("Preferences".equals(actionCommand)) {
                Preferences();
            } else if ("New Window".equals(actionCommand)) {
                frame.dispose();
                new UI();
            } else if ("Exit".equals(actionCommand)) {
                System.exit(0);
            } else if ("Log".equals(actionCommand)) {
                Log();
            }
        }
    }

    // Call method which gives the Result in Input TextField
    private String calculate(String operator, Float num1, Float num2) {
        Calculate calculate = new Calculate();
        return calculate.calculate(operator, num1, num2);
    }

    // Call method which shows the operation in Operation TextField
    private String getOperation(String operator, Float num1, Float num2) {
        Calculate getOperation = new Calculate();
        return getOperation.getOperation(operator, num1, num2);
    }

    // Call method that Preferences Menu Item calls when it is pressed
    private void Preferences() {
        Preferences Preferences = new Preferences();
        Preferences.preferences();
    }

    // Call method that Preferences Menu Item calls when it is pressed
    private void Log() {
        Log Log = new Log();
        Log.logWindow();
    }

    Action numberAction = new AbstractAction() {
        private static final long serialVersionUID = 1L;
        @Override
            public void actionPerformed(ActionEvent e)
            {
                io.replaceSelection(e.getActionCommand());
            }
        };

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if ("0".equals(this.io.getText())) {
            this.io.setText("");
            keyPressed(ke);
        } else if (ke.getKeyChar() == '+' || ke.getKeyChar() == '-' || ke.getKeyChar() == '*' || ke.getKeyChar() == '/'
                || ke.getKeyChar() == '=') {
                    this.actionCommand = String.valueOf(ke.getKeyChar());

        } else {
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.') {
                io.setEditable(true);
            } else {
                io.setEditable(false);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}