package main.java.com.amanuel.governance_service.controller;


import com.example.governance_service.model.Policy;
import com.example.governance_service.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // አዲስ ፖሊሲ ለመፍጠር (POST)
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyService.createPolicy(policy);
        return ResponseEntity.ok(savedPolicy);
    }

    // ሁሉንም ፖሊሲዎች ለማየት (GET)
    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }
}