package main.java.com.amanuel.governance_service.service;


import com.example.governance_service.model.Policy;
import com.example.governance_service.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository repository;

    public Policy createPolicy(Policy policy) {
        return repository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return repository.findAll();
    }
}