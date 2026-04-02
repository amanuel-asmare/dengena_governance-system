package com.example.governance_service.controller;

import com.example.governance_service.model.Policy;
import com.example.governance_service.service.PolicyService;
import com.example.governance_service.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private org.springframework.kafka.core.KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyService.createPolicy(policy);
        return ResponseEntity.ok(savedPolicy);
    }


    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }


    @PutMapping("/{id}/submit")
    public ResponseEntity<Policy> submitPolicy(@PathVariable Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow();
        policy.setStatus("PENDING_APPROVAL");
        Policy updatedPolicy = policyRepository.save(policy);


        kafkaTemplate.send("policy-topic", "Policy Submitted for Approval: " + updatedPolicy.getTitle());

        return ResponseEntity.ok(updatedPolicy);
    }


    @PutMapping("/{id}/approve")
    public ResponseEntity<Policy> approvePolicy(@PathVariable Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow();
        policy.setStatus("APPROVED");
        Policy updatedPolicy = policyRepository.save(policy);


        kafkaTemplate.send("policy-topic", "Policy APPROVED: " + updatedPolicy.getTitle());

        return ResponseEntity.ok(updatedPolicy);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Policy> rejectPolicy(@PathVariable Long id) {
        Policy policy = policyRepository.findById(id).orElseThrow();
        policy.setStatus("REJECTED");
        Policy updatedPolicy = policyRepository.save(policy);

        kafkaTemplate.send("policy-topic", "Policy REJECTED: " + updatedPolicy.getTitle() + " with ID: " + updatedPolicy.getId());

        return ResponseEntity.ok(updatedPolicy);
    }
}
