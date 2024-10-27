package com.ats.engine.parser;

import com.ats.engine.model.Node;

import java.util.Stack;

public class RuleParser {
    public Node parseRuleString(String ruleString) {
        ruleString = ruleString.trim();
        Stack<Node> nodeStack = new Stack<>();
        String[] tokens = ruleString.split(" ");

        for (String token : tokens) {
            if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
                Node operatorNode = new Node("operator", token);

                if (!nodeStack.isEmpty()) {
                    Node rightOperand = nodeStack.pop();
                    Node leftOperand = nodeStack.pop();
                    operatorNode.setLeft(leftOperand);
                    operatorNode.setRight(rightOperand);
                }
                nodeStack.push(operatorNode);
            } else {
                Node operandNode = createOperandNode(token);
                nodeStack.push(operandNode);
            }
        }
        return nodeStack.isEmpty() ? null : nodeStack.pop();
    }

    private Node createOperandNode(String token) {
        Node operandNode = new Node("operand", token.trim());
        return operandNode;
    }
}
