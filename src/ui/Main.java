package ui;
import model.Transaction;
import service.FinancialManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinancialManager manager = new FinancialManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Financial Manager!");
        System.out.println("Here you can track your finances easily and efficiently.");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a new transaction");
            System.out.println("2. View current balance");
            System.out.println("3. List all transactions");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("\nLet's add a new transaction.");
                    System.out.print("Transaction description: ");
                    String description = scanner.nextLine();

                    System.out.print("Amount (use a dot for decimals, e.g., 10.50): ");
                    double amount = scanner.nextDouble();

                    System.out.print("Category (e.g., food, transport, leisure): ");
                    scanner.nextLine(); // Consume the newline
                    String category = scanner.nextLine();

                    System.out.print("Type (income for earnings or expense for spending): ");
                    String type = scanner.nextLine();

                    System.out.print("Transaction date (format dd/MM/yyyy): ");
                    String date = scanner.nextLine();

                    Transaction transaction = new Transaction(description, amount, category, type, date);
                    manager.addTransaction(transaction);

                    System.out.println("\n✅ Transaction added successfully!");
                    break;

                case 2:
                    System.out.println("\nYour current balance is: $ " + manager.calculateBalance());
                    break;

                case 3:
                    System.out.println("\nHere are all your transactions:");
                    if (manager.getTransactions().isEmpty()) {
                        System.out.println("No transactions recorded yet.");
                    } else {
                        for (Transaction t : manager.getTransactions()) {
                            System.out.println("- " + t.getDescription() + " | Amount: $ " + t.getAmount() +
                                    " | Type: " + t.getType() + " | Category: " + t.getCategory() +
                                    " | Date: " + t.getDate());
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nThank you for using the Financial Manager! See you next time.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\n❌ Invalid option. Please choose an option between 1 and 4.");
            }
        }
    }
}
