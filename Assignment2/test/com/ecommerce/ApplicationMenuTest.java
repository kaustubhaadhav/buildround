package com.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Application Integration Tests")
public class ApplicationMenuTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayOutputStream testOut;
    private ByteArrayOutputStream testErr;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        testErr = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        System.setErr(new PrintStream(testErr));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Application should run main summary and exit")
    void testMainSummary() {
        // Simulate inputs: 
        // 1 (Overall Summary Statistics)
        // [Enter] (Press Enter to continue)
        // 10 (Exit)
        String input = "1\n\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        // Run application
        Application.main(new String[]{});

        String output = testOut.toString(StandardCharsets.UTF_8);
        
        // Validation
        assertTrue(output.contains("Total Revenue"), "Output should contain Total Revenue");
        assertTrue(output.contains("Sales Data Analysis Menu"), "Output should show menu");
        assertTrue(output.contains("Thank you"), "Output should show exit message");
    }

    @Test
    @DisplayName("Application should run category revenue and exit")
    void testCategoryRevenue() {
        // Simulate inputs:
        // 2 (Revenue by Category)
        // [Enter] (Press Enter to continue)
        // 10 (Exit)
        String input = "2\n\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        // Run application
        Application.main(new String[]{});

        String output = testOut.toString(StandardCharsets.UTF_8);

        // Validation
        assertTrue(output.contains("Revenue by Category"), "Output should contain Revenue by Category");
        assertTrue(output.contains("Electronics"), "Output should contain Electronics category");
        assertTrue(output.contains("TOTAL"), "Output should contain TOTAL");
    }
}
