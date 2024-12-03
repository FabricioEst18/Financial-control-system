package model;

import java.util.Objects;

public class Category {
    private String name;

    public Category() {
    }

    public Category(String name) {
        setName(name); // Usa o método setter para validar
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }
        this.name = name.trim(); // Remove espaços extras
    }

    @Override
    public String toString() {
        return "Category{name='" + name + "'}";
    }

    // Overriding equals to compare objects by name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    // Overwriting hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}