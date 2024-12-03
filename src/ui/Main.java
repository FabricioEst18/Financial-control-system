package ui;

import model.Transaction;
import service.FinancialManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final FinancialManager manager = new FinancialManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("Welcome to the Financial Manager!");
        System.out.println("Here you can track your finances easily and efficiently.");

        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> displayBalance();
                case 3 -> listTransactions();
                case 4 -> {
                    System.out.println("\nThank you for using the Financial Manager! See you next time.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\n❌ Invalid option. Please choose an option between 1 and 4.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Add a new transaction");
        System.out.println("2. View current balance");
        System.out.println("3. List all transactions");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Please enter a valid number.");
            scanner.next(); // Discard invalid input
        }
        return scanner.nextInt();
    }

    private static void addTransaction() {
        System.out.println("\nLet's add a new transaction.");
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Transaction description: ");
        String description = scanner.nextLine();

        double amount = getTransactionAmount();

        System.out.print("Category (e.g., food, transport, leisure): ");
        String category = scanner.nextLine();

        System.out.print("Type (income for earnings or expense for spending): ");
        String type = scanner.nextLine();

        LocalDate date = getTransactionDate();

        Transaction transaction = new Transaction(description, amount, category, type, date.toString());
        manager.addTransaction(transaction);
        System.out.println("\n✅ Transaction added successfully!");
    }

    private static double getTransactionAmount() {
        while (true) {
            System.out.print("Amount (use a dot for decimals, e.g., 10.50): ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Please enter a numeric value.");
            }
        }
    }

    private static LocalDate getTransactionDate() {
        while (true) {
            System.out.print("Transaction date (format dd/MM/yyyy): ");
            String dateInput = scanner.nextLine();
            try {
                return LocalDate.parse(dateInput, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Please use dd/MM/yyyy.");
            }
        }
    }

    private static void displayBalance() {
        System.out.println("\nYour current balance is: $ " + manager.calculateBalance());
    }

    private static void listTransactions() {
        System.out.println("\nHere are all your transactions:");
        if (manager.getTransactions().isEmpty()) {
            System.out.println("No transactions recorded yet.");
        } else {
            manager.getTransactions().forEach(t ->
                    System.out.printf("- %s | Amount: $%.2f | Type: %s | Category: %s | Date: %s%n",
                            t.getDescription(), t.getAmount(), t.getType(), t.getCategory(), t.getDate()));
        }
    }
}