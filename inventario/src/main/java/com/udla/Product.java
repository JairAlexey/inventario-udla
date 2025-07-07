package com.udla;

import java.util.Objects;

/**
 * Entidad que representa un artículo del inventario.
 * <p>
 * Solo el nombre es inmutable; la cantidad y el precio pueden
 * modificarse mediante setters validados.
 * </p>
 */
public final class Product {

    // ── Constantes ────────────────────────────────────────────

    /** Precio mínimo permitido. */
    private static final double MIN_PRICE = 0.0;

    /** Cantidad mínima permitida. */
    private static final int MIN_QUANTITY = 0;

    // ── Atributos ─────────────────────────────────────────────

    /** Nombre único del producto; no cambia durante su vida. */
    private final String name;

    /** Unidades disponibles en stock. */
    private int quantity;

    /** Precio unitario en dólares. */
    private double price;

    // ── Constructores ─────────────────────────────────────────

    /**
     * Crea un producto.
     *
     * @param productName nombre no vacío
     * @param initialQuantity cantidad &ge; {@value #MIN_QUANTITY}
     * @param initialPrice precio &ge; {@value #MIN_PRICE}
     * @throws IllegalArgumentException si algún argumento viola las reglas
     */
    public Product(final String productName,
                   final int    initialQuantity,
                   final double initialPrice) {
        validateName(productName);
        validateQuantity(initialQuantity);
        validatePrice(initialPrice);
        this.name     = productName;
        this.quantity = initialQuantity;
        this.price    = initialPrice;
    }

    // ── Getters ───────────────────────────────────────────────

    /** @return nombre único */
    public String getName() {
        return name;
    }

    /** @return unidades en stock */
    public int getQuantity() {
        return quantity;
    }

    /** @return precio unitario */
    public double getPrice() {
        return price;
    }

    // ── Setters validados ─────────────────────────────────────

    /**
     * Ajusta la cantidad disponible.
     *
     * @param newQuantity cantidad &ge; {@value #MIN_QUANTITY}
     */
    public void setQuantity(final int newQuantity) {
        validateQuantity(newQuantity);
        this.quantity = newQuantity;
    }

    /**
     * Ajusta el precio unitario.
     *
     * @param newPrice precio &ge; {@value #MIN_PRICE}
     */
    public void setPrice(final double newPrice) {
        validatePrice(newPrice);
        this.price = newPrice;
    }

    // ── Utilitarios ───────────────────────────────────────────

    /** @return valor total (precio × cantidad). */
    public double totalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("%s [qty=%d, $%.2f]", name, quantity, price);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;          // cast explícito
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // ── Validaciones internas ────────────────────────────────

    private static void validateName(final String n) {
        if (n == null || n.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
    }

    private static void validateQuantity(final int q) {
        if (q < MIN_QUANTITY) {
            throw new IllegalArgumentException("La cantidad debe ser ≥ 0");
        }
    }

    private static void validatePrice(final double p) {
        if (p < MIN_PRICE) {
            throw new IllegalArgumentException("El precio debe ser ≥ 0");
        }
    }
}
