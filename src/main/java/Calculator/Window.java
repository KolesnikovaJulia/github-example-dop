package Calculator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {

    public static void main(String[] args) {
        WindowFrame frame = new WindowFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        }
    }

    class WindowFrame extends JFrame {
        public WindowFrame() {
            setTitle("Calculator");
            ButtPanel panel = new ButtPanel();
            add(panel);
            pack();
        }
    }

    class ButtPanel extends JPanel {
        private final JButton display;
        private final JPanel panel;
        private double result;
        private String lastCommand;
        private boolean start;

        public ButtPanel() {
            setLayout(new BorderLayout());

            result = 0;
            lastCommand = "=";

            start = true;
            display = new JButton("0");
            display.setEnabled(false);
            add(display, BorderLayout.NORTH);

            ActionListener insert = new InsertAction();
            ActionListener command = new CommandAction();

            panel = new JPanel();
            panel.setLayout(new GridLayout(3, 5));

            addButton("1", insert);
            addButton("2", insert);
            addButton("3", insert);
            addButton("-", command);
            addButton("+", command);

            addButton("4", insert);
            addButton("5", insert);
            addButton("6", insert);
            addButton("/", command);
            addButton("*", command);


            addButton("7", insert);
            addButton("8", insert);
            addButton("9", insert);
            addButton("0", insert);
            addButton("=", command);

            add(panel, BorderLayout.CENTER);
        }

        public void calculate(double x) {
            switch (lastCommand) {
                case "+" -> result += x;
                case "-" -> result -= x;
                case "*" -> result *= x;
                case "/" -> result /= x;
                case "=" -> result = x;
            }
            display.setText("" + result);
        }

        private void addButton(String label, ActionListener listener) {
            JButton button = new JButton(label);
            button.addActionListener(listener);
            panel.add(button);
        }

        private class InsertAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                String input = event.getActionCommand();
                if (start) {
                    display.setText("");
                    start = false;
                }
                display.setText(display.getText() + input);
            }
        }

        private class CommandAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event) {
                String command = event.getActionCommand();
                if (start) {
                    if (command.equals("-")) {
                        display.setText(command);
                        start = false;
                    } else {
                        lastCommand = command;
                    }
                } else {
                    calculate(Double.parseDouble(display.getText()));
                    lastCommand = command;
                    start = true;
                }
            }
        }
    }