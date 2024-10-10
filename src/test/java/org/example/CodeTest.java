package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeTest {
    
    @Test
    public void testSayHallo() {
        Code code = new Code();
        assertEquals("Hello world!", code.sayHallo());
    }
}
