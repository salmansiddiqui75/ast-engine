package com.ats.engine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "rule_string", nullable = false, length = 1000)
    private String ruleString;

    public Rule() {}

    public Rule(String ruleString) {
        this.ruleString = ruleString;
    }
}
