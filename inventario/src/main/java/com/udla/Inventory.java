package com.udla;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Contenedor de productos.  Todas las operaciones internas
 * usan la lista de Product; ya no hay listas paralelas.
 */
public class Inventory {

    private final List<Product> products = new ArrayList<>();

    // ── Operaciones principales ────────────────────────────────────

    /** Agrega un producto nuevo al inventario. */
    public void addProduct(String name, int qty, double price) {
        products.add(new Product(name, qty, price));
        System.out.println("Product added.");
    }

    /** Cambia la cantidad de un producto existente. */
    public void updateQuantity(String name, int qty) {
        locate(name).ifPresentOrElse(
            p -> p.setQuantity(qty),
            () -> System.out.println("Product not found")
        );
    }

    /** Cambia el precio de un producto existente. */
    public void updatePrice(String name, double price) {
        locate(name).ifPresentOrElse(
            p -> p.setPrice(price),
            () -> System.out.println("Product not found")
        );
    }

    /** Valor monetario total del inventario. */
    public double getTotalValue() {
        return products.stream()
                       .mapToDouble(Product::totalValue)
                       .sum();
    }

    /** Imprime un resumen legible. */
    public void printInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        products.forEach(p -> System.out.printf(
            "%s | Quantity: %d | Price: $%.2f%n",
            p.getName(), p.getQuantity(), p.getPrice()
        ));
        System.out.printf("Total value: $%.2f%n%n", getTotalValue());
    }

    // ── Helpers ────────────────────────────────────────────────────
    private Optional<Product> locate(String name) {
        return products.stream()
                       .filter(p -> p.getName().equals(name))
                       .findFirst();
    }

    // ── Demo rápida ────────────────────────────────────────────────
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.addProduct("Laptop", 5, 1_000.0);
        inv.addProduct("Mouse", 10, 25.5);
        inv.printInventory();

        inv.updateQuantity("Laptop", 3);
        inv.updatePrice("Mouse", 20.0);
        inv.printInventory();
    }
}
