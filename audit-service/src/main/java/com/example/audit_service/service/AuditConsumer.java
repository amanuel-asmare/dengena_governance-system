package com.example.audit_service.service;

import com.example.audit_service.model.AuditLog;
import com.example.audit_service.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditConsumer {

    @Autowired
    private AuditRepository auditRepository;

    @KafkaListener(topics = "policy-topic", groupId = "audit-group")
    public void consume(String message) {
        AuditLog log = new AuditLog();

        if (message.contains("Created")) {
            log.setAction("POLICY_CREATION");
        } else if (message.contains("Submitted")) {
            log.setAction("POLICY_SUBMISSION");
        } else if (message.contains("APPROVED")) {
            log.setAction("POLICY_APPROVAL");
        } else if (message.contains("REJECTED")) {
        log.setAction("POLICY_REJECTION");
    }
        else {
            log.setAction("GENERAL_ACTION");
        }

        log.setDetails(message);
        auditRepository.save(log);

        System.out.println("Log saved to audit_db!");
    }
}