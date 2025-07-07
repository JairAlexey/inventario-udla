package com.udla;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Inventory is an in-memory container of {@link Product} items.
 *
 * <p>All mutating operations locate a {@code Product} by its {@code name}
 * and delegate the state change to the entity, keeping this class focused on
 * orchestration.</p>
 *
 * <h2>Thread-safety</h2>
 * This implementation is <em>not</em> thread-safe. Wrap it with external
 * synchronisation if accessed concurrently.
 *
 * @author Your-Name
 * @since 1.0
 */
public final class Inventory {

    // ――――――――――――――――――――――――――――――――――――― Constantes demo ――――――――――――――――――――――――――――――――――――
    /** Demo stock for the Laptop product (used only in {@code main}). */
    private static final int DEMO_LAPTOP_QTY = 5;
    /** Demo price for the Laptop product. */
    private static final double DEMO_LAPTOP_PRICE = 1_000.0;
    /** Demo stock for the Mouse product. */
    private static final int DEMO_MOUSE_QTY = 10;
    /** Demo price for the Mouse product. */
    private static final double DEMO_MOUSE_PRICE = 25.5;
    /** Demo new stock used in the second step of {@code main}. */
    private static final int DEMO_LAPTOP_NEW_QTY = 3;
    /** Demo new price used in the second step of {@code main}. */
    private static final double DEMO_MOUSE_NEW_PRICE = 20.0;

    /** Message shown when a product key cannot be found. */
    private static final String MSG_NOT_FOUND = "Product not found";

    /** Holds every {@link Product} currently registered. */
    private final List<Product> products = new ArrayList<>();

    // ――――――――――――――――――――――――――――――― Core API ―――――――――――――――――――――――――――――――

    /**
     * Adds a brand-new product to the inventory.
     *
     * @param name  unique identifier of the product (cannot be blank)
     * @param qty   initial stock, must be {@code >= 0}
     * @param price unit price, must be {@code >= 0}
     */
    public void addProduct(final String name, final int qty, final double price) {
        products.add(new Product(name, qty, price));
        System.out.println("Product added.");
    }

    /**
     * Updates only the <em>quantity</em> of a given product.
     *
     * @param name product key
     * @param qty  new quantity, must be {@code >= 0}
     */
    public void updateQuantity(final String name, final int qty) {
        locate(name).ifPresentOrElse(
            p -> p.setQuantity(qty),
            () -> System.out.println(MSG_NOT_FOUND)
        );
    }

    /**
     * Updates only the <em>unit price</em> of a given product.
     *
     * @param name  product key
     * @param price new unit price, must be {@code >= 0}
     */
    public void updatePrice(final String name, final double price) {
        locate(name).ifPresentOrElse(
            p -> p.setPrice(price),
            () -> System.out.println(MSG_NOT_FOUND)
        );
    }

    /**
     * Calculates the monetary value of <strong>all</strong> products stored.
     *
     * @return aggregated value (quantity × price) for every product
     */
    public double getTotalValue() {
        return products.stream()
                       .mapToDouble(Product::totalValue)
                       .sum();
    }

    /** Prints a human-friendly snapshot of the current inventory state. */
    public void printInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        products.forEach(p ->
            System.out.printf("%s | Qty: %d | Price: $%.2f%n",
                              p.getName(), p.getQuantity(), p.getPrice()));
        System.out.printf("Total value: $%.2f%n%n", getTotalValue());
    }

    // ――――――――――――――――――――――――――――――― Internals ―――――――――――――――――――――――――――――――

    /**
     * Searches a product by name.
     *
     * @param name product key
     * @return {@link Optional} with the product if found
     */
    private Optional<Product> locate(final String name) {
        return products.stream()
                       .filter(p -> p.getName().equals(name))
                       .findFirst();
    }

    // ――――――――――――――――――――――――――――――― Demo (smoke test) ―――――――――――――――――――――――――――――――
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(final String[] args) {
        Inventory inv = new Inventory();
        inv.addProduct("Laptop", DEMO_LAPTOP_QTY, DEMO_LAPTOP_PRICE);
        inv.addProduct("Mouse",  DEMO_MOUSE_QTY,  DEMO_MOUSE_PRICE);
        inv.printInventory();

        inv.updateQuantity("Laptop", DEMO_LAPTOP_NEW_QTY);
        inv.updatePrice   ("Mouse",  DEMO_MOUSE_NEW_PRICE);
        inv.printInventory();
    }
}
