package com.ats.engine.service;

import com.ats.engine.combiner.ASTCombiner;
import com.ats.engine.evaluator.RuleEvaluator;
import com.ats.engine.model.Node;
import com.ats.engine.parser.RuleParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RuleService {
    private final RuleParser parser = new RuleParser();
    private final RuleEvaluator evaluator = new RuleEvaluator();

    public Node createRule(String ruleString) {
        return parser.parseRuleString(ruleString);
    }

    public Node combineRules(List<String> rules) {
        List<Node> nodes = new ArrayList<>();
        for (String rule : rules) {
            nodes.add(createRule(rule));
        }
        return ASTCombiner.combine(nodes);
    }

    public boolean evaluateRule(Node ruleAST, Map<String, Object> attributes) {
        return evaluator.evaluate(ruleAST, attributes);
    }
}
