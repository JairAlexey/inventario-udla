package com.udla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    // ─────────── Casos felices ─────────────────────────────────────

    @Test
    @DisplayName("addProduct() incrementa el tamaño en 1")
    void addProduct_increasesSizeByOne() {
        inventory.addProduct("Laptop", 5, 1_000);
        assertEquals(1, inventory.size());
    }

    @Test
    @DisplayName("Se pueden agregar varios productos")
    void addSeveralProducts_sizeMatches() {
        inventory.addProduct("A", 1, 1);
        inventory.addProduct("B", 2, 2);
        inventory.addProduct("C", 3, 3);
        assertEquals(3, inventory.size());
    }

    @Test
    @DisplayName("totalInventoryValue() suma precio × cantidad")
    void totalInventoryValue_isCorrect() {
        inventory.addProduct("A", 2, 5);   // 10
        inventory.addProduct("B", 3, 4);   // 12
        assertEquals(22, inventory.totalInventoryValue(), 0.0001);
    }

    @Test
    @DisplayName("printInventory() muestra cada línea + total")
    void printInventory_outputsExpectedText() {
        inventory.addProduct("Laptop", 2, 1_000);
        inventory.addProduct("Mouse",  3,    10);

        // capturar System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf, true, StandardCharsets.UTF_8));

        inventory.printInventory();

        System.setOut(originalOut); // restaurar
        String out = buf.toString(StandardCharsets.UTF_8);

        assertAll(
            () -> assertTrue(out.contains("Laptop")),
            () -> assertTrue(out.contains("Qty: 2")),
            () -> assertTrue(out.contains("$1000")),
            () -> assertTrue(out.contains("Mouse")),
            () -> assertTrue(out.contains("Qty: 3")),
            () -> assertTrue(out.contains("Total value: $2030"))
        );
    }

    // ─────────── Validaciones de datos ────────────────────────────

    @Test
    @DisplayName("No admite cantidad negativa")
    void addProduct_rejectsNegativeQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> inventory.addProduct("Laptop", -1, 1_000));
    }

    @Test
    @DisplayName("No admite precio negativo")
    void addProduct_rejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> inventory.addProduct("Laptop", 5, -100));
    }

    @Test
    @DisplayName("No admite nombre vacío")
    void addProduct_rejectsBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> inventory.addProduct("  ", 1, 10));
    }

}
