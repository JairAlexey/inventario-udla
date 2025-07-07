package com.udla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void constructorRejectsInvalidArgs() {
        assertThrows(IllegalArgumentException.class,
            () -> new Product("", -1, -5));
    }

    @Test
    void totalValue_isPriceTimesQuantity() {
        Product p = new Product("Mouse", 4, 25.0);
        assertEquals(100.0, p.totalValue(), 0.0001);
    }

    @Test
    void settersUpdateState() {
        Product p = new Product("Pen", 1, 1.0);
        p.setQuantity(10);
        p.setPrice(0.5);
        assertEquals(10, p.getQuantity());
        assertEquals(0.5, p.getPrice(), 0.0001);
    }

    // ── equals() y hashCode() ─────────────────────────────────────

    @Test
    void equals_returnsTrueForSameName() {
        Product a = new Product("Book", 1, 10);
        Product b = new Product("Book", 99, 999);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void equals_returnsFalseForDifferentName() {
        Product a = new Product("A", 1, 1);
        Product b = new Product("B", 1, 1);
        assertNotEquals(a, b);
    }
}
