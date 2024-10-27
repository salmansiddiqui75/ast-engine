package com.ats.engine.evaluator;

import com.ats.engine.model.Node;

import java.util.Map;

public class RuleEvaluator {
    public static boolean evaluate(Node rule, Map<String, Object> inputs) {
        if (rule == null) {
            return false;
        }

        if ("operator".equals(rule.getType())) {
            if ("AND".equals(rule.getValue())) {
                return evaluate(rule.getLeft(), inputs) && evaluate(rule.getRight(), inputs);
            } else if ("OR".equals(rule.getValue())) {
                return evaluate(rule.getLeft(), inputs) || evaluate(rule.getRight(), inputs);
            }
        } else if ("operand".equals(rule.getType())) {
            String cleanValue = rule.getValue().trim();
            return evaluateCondition(cleanValue, inputs);
        }

        return false;
    }

    private static boolean evaluateCondition(String condition, Map<String, Object> inputs) {
        condition = condition.trim();

        if (condition.contains("AND")) {
            String[] parts = condition.split(" AND ");
            for (String part : parts) {
                if (!evaluateSingleCondition(part.trim(), inputs)) {
                    return false;
                }
            }
            return true;
        } else if (condition.contains("OR")) {
            String[] parts = condition.split(" OR ");
            for (String part : parts) {
                if (evaluateSingleCondition(part.trim(), inputs)) {
                    return true;
                }
            }
            return false;
        }

        return evaluateSingleCondition(condition, inputs);
    }

    private static boolean evaluateSingleCondition(String condition, Map<String, Object> inputs) {
        String[] conditionParts = condition.split(" ");
        if (conditionParts.length != 3) {
            return false;
        }

        String field = conditionParts[0];
        String operator = conditionParts[1];
        String value = conditionParts[2].replace("'", "");

        switch (field) {
            case "age":
                int ageValue = (int) inputs.get(field);
                return evaluateComparison(ageValue, Integer.parseInt(value), operator);
            case "department":
                String departmentValue = (String) inputs.get(field);
                return departmentValue.equals(value);
            case "salary":
                int salaryValue = (int) inputs.get(field);
                return evaluateComparison(salaryValue, Integer.parseInt(value), operator);
            case "experience":
                int experienceValue = (int) inputs.get(field);
                return evaluateComparison(experienceValue, Integer.parseInt(value), operator);
            default:
                return false;
        }
    }

    private static boolean evaluateComparison(int fieldValue, int comparisonValue, String operator) {
        switch (operator) {
            case ">":
                return fieldValue > comparisonValue;
            case "<":
                return fieldValue < comparisonValue;
            case "=":
                return fieldValue == comparisonValue;
            case ">=":
                return fieldValue >= comparisonValue;
            case "<=":
                return fieldValue <= comparisonValue;
            default:
                return false;
        }
    }
}
