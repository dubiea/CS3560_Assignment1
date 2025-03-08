package org.example;
import java.util.*;

public class ValidParentheses {

    /* checks whether a given string of brackets is valid. */
    public boolean isValid(String s) {

        // Creates a stack to track expected closing brackets
        Stack<Character> stack = new Stack<>();

        // loop through each char in string
        for (char c: s.toCharArray()){

            // if char is open parenthesis '(', push closing parenthsis ')' to stack
            if (c == '(')stack.push(')');

            // if char is open curly brace '{', push closing brace '}' to stack
            else if (c == '{')stack.push('}');

            // if char is open bracket '(', push closing bracket ')' to stack
            else if (c == '[')stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        // If the stack is empty at the end, all brackets were properly matched
        return stack.isEmpty();
    }
}

