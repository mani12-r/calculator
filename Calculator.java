import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField inputField;
    double num1, num2, result;
    char operator;

    public Calculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(350, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Input field
        inputField = new JTextField();
        inputField.setBounds(30, 40, 270, 40);
        inputField.setEditable(false);
        add(inputField);

        // Buttons
        String[] buttonLabels = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
        };

        JButton[] buttons = new JButton[16];
        int x = 30, y = 100;

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(x, y, 60, 40);
            add(buttons[i]);
            buttons[i].addActionListener(this);
            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 50;
            }
        }

        // Clear button
        JButton clearButton = new JButton("C");
        clearButton.setBounds(30, 300, 270, 40);
        add(clearButton);
        clearButton.addActionListener(e -> inputField.setText(""));

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(inputField.getText());
            operator = command.charAt(0);
            inputField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0)
                        result = num1 / num2;
                    else
                        inputField.setText("Error");
                    break;
            }

            if (!command.equals("/") || num2 != 0)
                inputField.setText("" + result);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}