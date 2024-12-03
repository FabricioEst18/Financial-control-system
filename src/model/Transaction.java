package model;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private String description;
    private double amount;
    private String category;
    private String type; // "income" ou "expense"
    private LocalDate date;

    // Construtor com validações
    public Transaction(String description, double amount, String category, String type, LocalDate date) {
        setDescription(description);
        setAmount(amount);
        setCategory(category);
        setType(type);
        setDate(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description.trim();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        this.category = category.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equalsIgnoreCase("income") && !type.equalsIgnoreCase("expense")) {
            throw new IllegalArgumentException("Type must be 'income' or 'expense'.");
        }
        this.type = type.toLowerCase();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be null or in the future.");
        }
        this.date = date;
    }

    // Sobrescrevendo toString
    @Override
    public String toString() {
        return String.format("Transaction{description='%s', amount=%.2f, category='%s', type='%s', date=%s}",
                description, amount, category, type, date);
    }

    // Sobrescrevendo equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(type, that.type) &&
                Objects.equals(date, that.date);
    }

    // Sobrescrevendo hashCode
    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category, type, date);
    }
}