package com.example.governance_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "policies")
@Data
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status = "DRAFT";
}