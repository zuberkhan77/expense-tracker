package expensetracker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel dateLabel, categoryLabel, amountLabel;
    private JTextField dateField, categoryField, amountField;
    private JButton addButton, showButton;
    private JTextArea expenseDisplayArea;

    private ArrayList<String> expenses;

    public Main() {
        setTitle("Expense Tracker");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dateLabel = new JLabel("Date:");
        categoryLabel = new JLabel("Category:");
        amountLabel = new JLabel("Amount:");

        dateField = new JTextField(10);
        categoryField = new JTextField(10);
        amountField = new JTextField(10);

        addButton = new JButton("Add Expense");
        showButton = new JButton("Show Expenses");

        expenseDisplayArea = new JTextArea(10, 30);
        expenseDisplayArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(showButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(expenseDisplayArea), BorderLayout.SOUTH);

        expenses = new ArrayList<>();

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showExpenses();
            }
        });
    }

    private void addExpense() {
        String date = dateField.getText();
        String category = categoryField.getText();
        String amount = amountField.getText();

        if (date.isEmpty() || category.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        String expense = "Date: " + date + ", Category: " + category + ", Amount: $" + amount;
        expenses.add(expense);
        clearFields();
    }

    private void clearFields() {
        dateField.setText("");
        categoryField.setText("");
        amountField.setText("");
    }

    private void showExpenses() {
        if (expenses.isEmpty()) {
            expenseDisplayArea.setText("No expenses added yet.");
            return;
        }

        StringBuilder displayText = new StringBuilder();
        for (String expense : expenses) {
            displayText.append(expense).append("\n");
        }
        expenseDisplayArea.setText(displayText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}