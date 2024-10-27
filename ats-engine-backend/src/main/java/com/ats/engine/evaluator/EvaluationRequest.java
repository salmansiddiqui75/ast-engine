package com.ats.engine.evaluator;

import com.ats.engine.model.Node;
import lombok.Getter;

import java.util.Map;

public class EvaluationRequest {
    private Node ruleAST;
    @Getter
    private Map<String, Object> attributes;

    public EvaluationRequest(Node ruleAST, Map<String, Object> attributes) {
        this.ruleAST = ruleAST;
        this.attributes = attributes;
    }

    public Node getRule() {
        return ruleAST;
    }

}
