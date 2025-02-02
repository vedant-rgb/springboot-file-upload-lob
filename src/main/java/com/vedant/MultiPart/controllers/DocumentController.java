package com.vedant.MultiPart.controllers;

import com.vedant.MultiPart.entities.Document;
import com.vedant.MultiPart.services.DocumentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file")MultipartFile file){
        try{

            Document document = documentService.uploadFile(file);
            return new ResponseEntity<>("File uploaded Successfully, Document Id:"+document.getId(),HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> seeDocument(@PathVariable Long id){
        try {
            Document document = documentService.getFile(id);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(document.getFileType()));

            httpHeaders.setContentDisposition(ContentDisposition.().build());

            return new ResponseEntity<>(document.getFileData(),httpHeaders,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
