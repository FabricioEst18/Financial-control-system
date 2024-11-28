package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FinancialManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public double calculateBalance() {
        double balance = 0.0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income")) {
                balance += t.getAmount();
            } else if (t.getType().equalsIgnoreCase("expense")) {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
