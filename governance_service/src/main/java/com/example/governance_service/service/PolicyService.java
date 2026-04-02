package com.example.governance_service.service;

import com.example.governance_service.model.Policy;
import com.example.governance_service.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Policy createPolicy(Policy policy) {
        Policy savedPolicy = policyRepository.save(policy);

        kafkaTemplate.send("policy-topic", "Policy Created: " + savedPolicy.getTitle() + " with ID: " + savedPolicy.getId());
        return savedPolicy;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}