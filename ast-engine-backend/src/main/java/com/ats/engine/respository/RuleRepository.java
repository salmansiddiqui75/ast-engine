package com.ats.engine.respository;

import com.ats.engine.model.Node;
import com.ats.engine.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}

