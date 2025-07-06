package com.udla;

import java.util.Objects;

/**
 * Entidad que representa un artículo del inventario.
 * Solo el nombre es inmutable; la cantidad y el precio pueden
 * modificarse mediante setters validados.
 */
public class Product {

    private final String name;
    private int quantity;
    private double price;

    // ── Constructores ───────────────────────────────────────────────

    public Product(String name, int quantity, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad debe ser ≥ 0");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio debe ser ≥ 0");
        }
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // ── Getters ────────────────────────────────────────────────────

    public String getName()     { return name; }
    public int    getQuantity() { return quantity; }
    public double getPrice()    { return price; }

    // ── Setters validados ──────────────────────────────────────────

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("quantity ≥ 0");
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("price ≥ 0");
        this.price = price;
    }

    // ── Utilitarios ────────────────────────────────────────────────

    /** Valor total (precio × cantidad). */
    public double totalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("%s [qty=%d, $%.2f]", name, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;      // cast clásico
        return Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
