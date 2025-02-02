package com.vedant.MultiPart.services;

import com.vedant.MultiPart.entities.Document;
import com.vedant.MultiPart.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadFile(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        Document document = Document.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileData(fileBytes)
                .build();
        return documentRepository.save(document);
    }

    public Document getFile(Long id){
        return documentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("File not found with id : "+id));
    }
}
