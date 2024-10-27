package com.ats.engine.controller;

import com.ats.engine.combiner.ASTCombiner;
import com.ats.engine.evaluator.EvaluationRequest;
import com.ats.engine.model.Node;
import com.ats.engine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rules")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/create")
    public ResponseEntity<Node> createRule(@RequestBody String ruleString) {
        Node rule = ruleService.createRule(ruleString);
        return ResponseEntity.ok(rule);
    }

    @PostMapping("/combine")
    public ResponseEntity<?> combineRules(@RequestBody Map<String, List<Node>> request) {
        try {
            List<Node> rules = request.get("rules");
            if (rules == null || rules.isEmpty()) {
                return ResponseEntity.badRequest().body("Rules cannot be empty");
            }

            Node combinedRule = ASTCombiner.combine(rules);
            return ResponseEntity.ok(combinedRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody EvaluationRequest request) {
        boolean result = ruleService.evaluateRule(request.getRule(), request.getAttributes());
        return ResponseEntity.ok(result);
    }
}
