package com.vedant.MultiPart.repository;

import com.vedant.MultiPart.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
