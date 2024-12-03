package service;

import model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinancialManager {
    private final List<Transaction> transactions = new ArrayList<>();

    // Add a transaction after validation
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
        transactions.add(transaction);
    }

    // Calculates the current balance
    public double calculateBalance() {
        return transactions.stream()
                .mapToDouble(t -> t.getType().equalsIgnoreCase("income") ? t.getAmount() : -t.getAmount())
                .sum();
    }

    // Returns an immutable copy of the transaction list
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    // Filter transactions by type
    public List<Transaction> filterByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    // Filter transactions by category
    public List<Transaction> filterByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Filter transactions by date range
    public List<Transaction> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> t.getDate().isAfter(startDate.minusDays(1)) && t.getDate().isBefore(endDate.plusDays(1)))
                .collect(Collectors.toList());
    }
}
