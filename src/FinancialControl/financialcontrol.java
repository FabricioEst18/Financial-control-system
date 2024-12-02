package FinancialControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class financialcontrol {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createInterface());
    }

    public static void createInterface() {
        JFrame frame = new JFrame("Financial Control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JButton addButton = new JButton("Add Transaction");

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(new JLabel());
        panel.add(addButton);

        JTextArea transactionArea = new JTextArea(5, 30);
        transactionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText();
                String description = descriptionField.getText();
                transactionArea.append("Transaction: " + description + " - $" + amount + "\n");

                amountField.setText("");
                descriptionField.setText("");
            }
        });

        frame.setVisible(true);
    }
}
