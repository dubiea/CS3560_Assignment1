package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

    @Test
    public void testIsValid() {
        ValidParentheses vp = new ValidParentheses();
        assertTrue(vp.isValid("()"));
        assertTrue(vp.isValid("()[]{}"));
        assertFalse(vp.isValid("(]"));
        assertFalse(vp.isValid("([)]"));

        // Edge Cases
        assertTrue(vp.isValid("")); // Empty string
        assertTrue(vp.isValid("()[]{}")); // Multiple types of valid non-nested brackets
    }

}