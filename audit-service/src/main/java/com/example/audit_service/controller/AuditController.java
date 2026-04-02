package com.example.audit_service.controller;

import com.example.audit_service.model.AuditLog;
import com.example.audit_service.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditRepository auditRepository;

    @GetMapping
    public List<AuditLog> getAllLogs() {
        return auditRepository.findAll();
    }
}