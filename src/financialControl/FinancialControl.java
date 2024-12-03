package financialControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialControl {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinancialControl::createInterface);
    }

    public static void createInterface() {
        JFrame frame = new JFrame("Financial Control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel for data inputs
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JButton addButton = new JButton("Add Transaction");

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(new JLabel()); // Placeholder
        panel.add(addButton);

        // Text area to display transactions
        JTextArea transactionArea = new JTextArea(10, 30);
        transactionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionArea);

        // Add components to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Listener for the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = amountField.getText();
                String description = descriptionField.getText();

                // Input validation
                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double amount = Double.parseDouble(amountText);
                    if (amount <= 0) {
                        throw new NumberFormatException("Amount must be positive.");
                    }
                    transactionArea.append("Transaction: " + description + " - $" + String.format("%.2f", amount) + "\n");

                    // Clear fields after addition
                    amountField.setText("");
                    descriptionField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Make visible
        frame.setVisible(true);
    }
}