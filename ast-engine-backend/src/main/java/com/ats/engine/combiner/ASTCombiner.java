package com.ats.engine.combiner;

import com.ats.engine.model.Node;

import java.util.List;

public class ASTCombiner {
    public static Node combine(List<Node> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("Rules cannot be null or empty");
        }

        Node root = new Node("operator", "AND");
        Node current = root;

        for (Node rule : rules) {
            Node newRoot = new Node("operator", "AND");
            newRoot.setLeft(current);
            newRoot.setRight(rule);
            current = newRoot;
        }
        return current;
    }
}
